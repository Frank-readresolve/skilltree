<script>
import { useVuelidate } from '@vuelidate/core';
import { required } from '@vuelidate/validators';
import auth from '../security/auth';

export default {
    name: 'SignIn',
    setup() {
        return {
            validator: useVuelidate({ $autoDirty: true })
        }
    }, data() {
        return {
            inputs: {
                username: null,
                password: null
            }, vuelidateExternalResults: {}
        }
    },
    validations() {
        return {
            inputs: {
                username: { required },
                password: { required }
            }
        }
    },
    methods: {
        async submit(event) {
            await this.$api.post(this, event, '/accounts/sign-in', (res) => {
                auth.authenticate(res.body);
                this.$router.replace({ name: auth.getHomeRoute() });
            });
        }
    }
}
</script>

<template>
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <Card>
                <template #title>
                    <h1 class="text-center">{{ $t('signIn.title') }}</h1>
                </template>
                <template #content>
                    <Form :submit="submit" :validator="validator" :globalErrors="vuelidateExternalResults.globals"
                        buttonPath="signIn.submit">
                        <div class="mb-3">
                            <FormLabel for="username" path="signIn.username" />
                            <input name="username" id="username" type="text" class="form-control"
                                v-model.trim="inputs.username" v-invalid-field>
                            <div class="form-text">{{ $t('signIn.usernameHelp') }}</div>
                        </div>
                        <div class="mb-3">
                            <FormLabel for="password" path="signIn.password" />
                            <input name="password" id="password" type="password" class="form-control"
                                v-model.trim="inputs.password" v-invalid-field>
                        </div>
                    </Form>
                </template>
            </Card>
        </div>
    </div>
</template>
