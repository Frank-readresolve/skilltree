const PREFIX = '/trainer';

const ROLE = 'ROLE_TRAINER';

const routes = [
    {
        path: `${PREFIX}`,
        component: () => import('../layouts/DefaultLayout.vue'),
        children: [
            {
                path: `${PREFIX}`,
                name: 'trainer-home',
                components: {
                    navbar: () => import('../components/trainer/TrainerNavbar.vue'),
                    view: () => import('../components/trainer/TrainerHome.vue')
                },
                meta: {
                    role: ROLE
                }
            }
        ]
    }
];

export default routes;
