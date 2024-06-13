<script>
export default {
    name: 'AccountsList',
    data() {
        return {
            accounts: []
        }
    },
    methods: {
        async resetPassword(id) {
            await this.$api.patch(this, `/accounts/${id}/password`, (res) => {
                this.$toaster.success(this.$t('admin.accounts.resetSuccess'));
            });
        },
        async initAccounts() {
            await this.$api.get(this, '/accounts', (res) => {
                this.accounts = res.body;
            });
        }
    },
    async beforeMount() {
        await this.initAccounts();
    }
}
</script>
<template>
    <h1>{{ $t('admin.accounts.listTitle') }}</h1>
    <div class="table-responsive">
        <table class="table table-hover text-nowrap">
            <thead>
                <tr class="text-center align-middle">
                    <th scope="col">{{ $t('admin.accounts.lastname') }}</th>
                    <th scope="col">{{ $t('admin.accounts.firstname') }}</th>
                    <th class="w-100" scope="col">{{ $t('admin.accounts.username') }}</th>
                    <th scope="col">{{ $t('admin.accounts.password') }}</th>
                </tr>
            </thead>
            <tbody class="align-middle">
                <tr v-for="account in accounts">
                    <td>{{ account.lastname }}</td>
                    <td>{{ account.firstname }}</td>
                    <td>{{ account.username }}</td>
                    <td class="text-center"><a href="#" @click="resetPassword(account.id)">
                            <i class="bi bi-incognito text-danger"></i></a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>