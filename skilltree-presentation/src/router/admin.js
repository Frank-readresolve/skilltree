const PREFIX = '/admin';

const ROLE = 'ROLE_ADMIN';

const routes = [
    {
        path: `${PREFIX}`,
        component: () => import('../layouts/DefaultLayout.vue'),
        children: [
            {
                path: `${PREFIX}`,
                name: 'admin-home',
                components: {
                    navbar: () => import('../components/admin/AdminNavbar.vue'),
                    view: () => import('../components/admin/AdminHome.vue')
                },
                meta: {
                    role: ROLE
                }
            },
            {
                path: `${PREFIX}/referentials/certification/create`,
                name: 'admin-referentials-certification-create',
                components: {
                    navbar: () => import('../components/admin/AdminNavbar.vue'),
                    view: () => import('../components/admin/referentials/certifications/CertificationCreate.vue')
                },
                meta: {
                    role: ROLE
                }
            },
            {
                path: `${PREFIX}/referentials/activity/create`,
                name: 'admin-referentials-activity-create',
                components: {
                    navbar: () => import('../components/admin/AdminNavbar.vue'),
                    view: () => import('../components/admin/referentials/activities/ActivityCreate.vue')
                },
                meta: {
                    role: ROLE
                }
            },
            {
                path: `${PREFIX}/referentials/skill/create`,
                name: 'admin-referentials-skill-create',
                components: {
                    navbar: () => import('../components/admin/AdminNavbar.vue'),
                    view: () => import('../components/admin/referentials/skills/SkillCreate.vue')
                },
                meta: {
                    role: ROLE
                }
            },
            {
                path: `${PREFIX}/training/create`,
                name: 'admin-training-create',
                components: {
                    navbar: () => import('../components/admin/AdminNavbar.vue'),
                    view: () => import('../components/admin/trainings/TrainingCreate.vue')
                },
                meta: {
                    role: ROLE
                }
            },
            {
                path: `${PREFIX}/account/create`,
                name: 'admin-account-create',
                components: {
                    navbar: () => import('../components/admin/AdminNavbar.vue'),
                    view: () => import('../components/admin/accounts/AccountCreate.vue')
                },
                meta: {
                    role: ROLE
                }
            },
            {
                path: `${PREFIX}/accounts`,
                name: 'admin-accounts',
                components: {
                    navbar: () => import('../components/admin/AdminNavbar.vue'),
                    view: () => import('../components/admin/accounts/AccountsList.vue')
                },
                meta: {
                    role: ROLE
                }
            }
        ]
    }
];

export default routes;
