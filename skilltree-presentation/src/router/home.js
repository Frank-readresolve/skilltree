const routes = [
    {
        path: '',
        component: () => import('../layouts/DefaultLayout.vue'),
        children: [
            {
                path: '',
                name: 'home',
                components: {
                    view: () => import('../components/SignIn.vue')
                }
            }
        ],
        meta: {
            requiresAuth: false
        }
    }
];

export default routes;
