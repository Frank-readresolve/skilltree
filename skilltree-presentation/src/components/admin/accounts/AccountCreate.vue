<script>
import { useVuelidate } from '@vuelidate/core';
import { required, email, minLength, maxLength } from '@vuelidate/validators';

export default {
    name: 'AccountCreate',
    setup() {
        return {
            validator: useVuelidate({ $autoDirty: true })
        }
    }, data() {
        return {
            inputs: {
                username: null,
                firstname: null,
                lastname: null
            }, vuelidateExternalResults: {}
        }
    },
    validations() {
        return {
            inputs: {
                username: { required, email },
                firstname: { required, minLength: minLength(1), maxLength: maxLength(100) },
                lastname: { required, minLength: minLength(1), maxLength: maxLength(100) }
            }
        }
    },
    methods: {
        async submit(event) {
            await this.$api.post(this, event, '/accounts', (res) => {
                Object.assign(this.inputs, this.$options.data().inputs);
                this.validator.$reset();
                this.$toaster.success(this.$t('admin.accounts.createSuccess'));
            });
        }
    }
}
</script>

<template>
    <Card>
        <template #title>
            <h1>{{ $t('admin.accounts.createTitle') }}</h1>
        </template>
        <template #content>
            <Form :submit="submit" :validator="validator" :globalErrors="vuelidateExternalResults.globals"
                buttonPath="save">
                <div class="mb-3">
                    <FormLabel for="username" required="true" path="admin.accounts.username" />
                    <input name="username" id="username" type="text" class="form-control" v-model.trim="inputs.username"
                        v-invalid-field>
                    <div class="form-text">{{ $t('admin.accounts.usernameHelp') }}</div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <FormLabel for="firstname" required="true" path="admin.accounts.firstname" />
                        <input name="firstname" id="firstname" type="text" class="form-control"
                            v-model.trim="inputs.firstname" maxlength="100" v-invalid-field>
                    </div>
                    <div class="col-md-6 mb-3">
                        <FormLabel for="lastname" required="true" path="admin.accounts.lastname" />
                        <input name="lastname" id="lastname" type="text" class="form-control"
                            v-model.trim="inputs.lastname" maxlength="100" v-invalid-field>
                    </div>
                </div>
            </Form>
        </template>
    </Card>
</template>
