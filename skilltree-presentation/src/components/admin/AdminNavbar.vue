<script>
export default {
    name: 'AdminNavbar'
}
</script>
<template>
    <ul class="navbar-nav">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                {{ $t('admin.navbar.referentials.dropdown') }}
            </a>
            <ul class="dropdown-menu">
                <li>
                    <RouterLink :to="{ name: 'admin-referentials-certification-create' }" class="dropdown-item">
                        {{ $t('admin.navbar.referentials.certifications') }}
                    </RouterLink>
                </li>
                <li>
                    <RouterLink :to="{ name: 'admin-referentials-activity-create' }" class="dropdown-item">
                        {{ $t('admin.navbar.referentials.activities') }}
                    </RouterLink>
                </li>
                <li>
                    <RouterLink :to="{ name: 'admin-referentials-skill-create' }" class="dropdown-item">
                        {{ $t('admin.navbar.referentials.skills') }}
                    </RouterLink>
                </li>
            </ul>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                {{ $t('admin.navbar.trainings.dropdown') }}
            </a>
            <ul class="dropdown-menu">
                <li>
                    <RouterLink :to="{ name: 'admin-training-create' }" class="dropdown-item">
                        {{ $t('admin.navbar.trainings.create') }}
                    </RouterLink>
                </li>
            </ul>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                {{ $t('admin.navbar.accounts.dropdown') }}
            </a>
            <ul class="dropdown-menu">
                <li>
                    <RouterLink :to="{ name: 'admin-account-create' }" class="dropdown-item">
                        {{ $t('admin.navbar.accounts.create') }}
                    </RouterLink>
                </li>
                <li>
                    <RouterLink :to="{ name: 'admin-accounts' }" class="dropdown-item">
                        {{ $t('admin.navbar.accounts.list') }}
                    </RouterLink>
                </li>
            </ul>
        </li>
    </ul>
    <Logout />
</template>
