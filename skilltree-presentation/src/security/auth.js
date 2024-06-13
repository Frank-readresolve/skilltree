const HOME_ROUTES = new Map();
HOME_ROUTES.set('ROLE_ADMIN', 'admin-home');
HOME_ROUTES.set('ROLE_TRAINER', 'trainer-home');

let token = null;

let accountInfo = null;

const auth = {
    authenticate: (data) => {
        token = data.token;
        accountInfo = data.accountInfo;
    },
    isAuthenticated: () => {
        return token !== null;
    },
    logout: () => {
        token = null;
        accountInfo = null;
    },
    hasRole: (roleName) => {
        return roleName === accountInfo.role;
    },
    getToken: () => {
        return token;
    },
    getAccountInfo: () => {
        return accountInfo;
    },
    getRole: () => {
        return accountInfo.role;
    },
    getHomeRoute: () => {
        return HOME_ROUTES.get(accountInfo.role);
    }
};

export default auth;
