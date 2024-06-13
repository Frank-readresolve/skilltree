import auth from '../security/auth';

function resolveRequiresAuth(route) {
    const value = route.meta.requiresAuth;
    if (value === undefined) {
        return true; // PoLP
    }
    return (typeof value === 'boolean') ? value : true; // PoLP!
}

function hasRole(route) {
    const value = route.meta.role;
    if (value === undefined) {
        return true; // Any role (none)
    }
    return auth.hasRole(value);
}

const guard = (router, homeRoute) => {
    router.beforeEach((to, from) => {
        const requiresAuth = resolveRequiresAuth(to);
        if (requiresAuth && !auth.isAuthenticated()
            && to.name !== homeRoute) {
            return {
                name: homeRoute
            };
        } else if ((requiresAuth && auth.isAuthenticated()
            && !hasRole(to) && to.name !== homeRoute)
            || (auth.isAuthenticated() && to.name === homeRoute)) {
            return {
                name: auth.getHomeRoute()
            };
        }
    });
};

export default guard;
