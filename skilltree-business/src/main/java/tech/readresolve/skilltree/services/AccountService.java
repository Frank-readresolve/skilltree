package tech.readresolve.skilltree.services;

import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.readresolve.skilltree.config.AuthenticationHelper;
import tech.readresolve.skilltree.config.SecurityContextUtil;
import tech.readresolve.skilltree.dtos.in.AccountCreate;
import tech.readresolve.skilltree.dtos.in.SignIn;
import tech.readresolve.skilltree.dtos.out.AccountInfo;
import tech.readresolve.skilltree.dtos.out.AccountView;
import tech.readresolve.skilltree.dtos.out.AuthInfo;
import tech.readresolve.skilltree.entities.Account;
import tech.readresolve.skilltree.entities.Role;
import tech.readresolve.skilltree.entities.Trainer;
import tech.readresolve.skilltree.repositories.AccountReposiroty;
import tech.readresolve.skilltree.repositories.RoleRepository;
import tech.readresolve.skilltree.repositories.TrainerRepository;
import tech.readresolve.skilltree.services.helpers.Mailer;
import tech.readresolve.skilltree.services.helpers.PasswordUtil;

@Service
public class AccountService extends BaseService {

	private final AuthenticationHelper authentication;

	private final AccountReposiroty accounts;

	private final RoleRepository roles;

	private final TrainerRepository trainers;

	private final Mailer mailer;

	AccountService(AuthenticationHelper authentication,
			AccountReposiroty accounts, RoleRepository roles,
			TrainerRepository trainers, Mailer mailer) {
		this.authentication = authentication;
		this.accounts = accounts;
		this.roles = roles;
		this.trainers = trainers;
		this.mailer = mailer;
	}

	public AuthInfo signIn(SignIn inputs) throws BadCredentialsException {
		String username = inputs.username();
		Account entity = accounts.findByUsernameIgnoreCase(username)
				.orElseThrow(() -> new BadCredentialsException(String
						.format("no user found with username '%s'", username)));
		String password = inputs.password();
		if (!authentication.matches(password, entity.getPassword())) {
			throw new BadCredentialsException(String.format(
					"passwords do not match for username '%s'", username));
		}
		String role = entity.getRole().getCode();
		String token = authentication.createToken(username, List.of(role));
		return new AuthInfo(token, new AccountInfo(role, entity.getFirstname(),
				entity.getLastname()));
	}

	@Transactional
	public void create(AccountCreate inputs) {
		String rawPassword = PasswordUtil.randomPassword();
		Account account = toAccount(inputs, rawPassword);
		Trainer trainer = toTrainer(account);
		trainers.save(trainer);
		mailer.sendAccountCreated(inputs.username(), inputs.firstname(),
				rawPassword); // async
	}

	private Account toAccount(AccountCreate inputs, String rawPassword) {
		Account entity = new Account();
		// Role is expected to be present in db, optional.get() is ok:
		Role role = roles.findByCode("ROLE_TRAINER").get();
		entity.setUsername(inputs.username());
		entity.setRole(role);
		entity.setFirstname(inputs.firstname());
		entity.setLastname(inputs.lastname());
		String encoded = authentication.encode(rawPassword);
		entity.setPassword(encoded);
		return entity;
	}

	private Trainer toTrainer(Account account) {
		String nextCode = trainers.nextTrainerCode();
		Trainer entity = new Trainer();
		entity.setAccount(account);
		entity.setCode(nextCode);
		return entity;
	}

	public Collection<AccountView> views() {
		String username = SecurityContextUtil.principal();
		return accounts.findAllProjectedBy(username);
	}

	@Transactional
	public void resetPassword(Long id) {
		Account entity = findByIdOrNotFound(accounts, id);
		String rawPassword = PasswordUtil.randomPassword();
		String encodedPassword = authentication.encode(rawPassword);
		entity.setPassword(encodedPassword);
		mailer.sendResetPassword(entity.getUsername(), entity.getFirstname(),
				rawPassword); // async
	}

}
