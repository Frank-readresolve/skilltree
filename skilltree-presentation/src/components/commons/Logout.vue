<script>
import auth from '../../security/auth';

export default {
    name: 'Logout',
    methods: {
        logout() {
            auth.logout();
            this.$router.replace({ name: 'home' });
        }
    }
}
</script>

<template>
    <ul class="navbar-nav ms-auto">
        <li class="nav-item">
            <i class="nav-link p-0 fs-4 bi link bi-box-arrow-right" @click="logout"></i>
        </li>
    </ul>
</template>
