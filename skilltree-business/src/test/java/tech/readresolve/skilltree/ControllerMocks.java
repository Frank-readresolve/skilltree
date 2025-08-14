package tech.readresolve.skilltree;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import tech.readresolve.skilltree.controllers.AccountController;
import tech.readresolve.skilltree.controllers.ActivityController;
import tech.readresolve.skilltree.controllers.CertificationController;
import tech.readresolve.skilltree.controllers.CertificationLevelController;
import tech.readresolve.skilltree.controllers.SkillController;
import tech.readresolve.skilltree.controllers.TrainingController;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MockitoBean(types = { AccountController.class, ActivityController.class,
	CertificationLevelController.class, CertificationController.class,
	SkillController.class, TrainingController.class })
public @interface ControllerMocks {
    //
}
