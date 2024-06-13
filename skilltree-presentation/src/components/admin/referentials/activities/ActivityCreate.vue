<script>
import { useVuelidate } from '@vuelidate/core';
import { required, minValue, minLength, maxLength } from '@vuelidate/validators';

export default {
    name: 'ActivityCreate',
    setup() {
        return {
            validator: useVuelidate({ $autoDirty: true })
        }
    }, data() {
        return {
            certifications: [],
            inputs: {
                certificationId: 0,
                code: null,
                name: null,
                description: null
            }, vuelidateExternalResults: {}
        }
    },
    validations() {
        return {
            inputs: {
                certificationId: { minValue: minValue(1) },
                code: { required, minLength: minLength(1), maxLength: maxLength(15) },
                name: { required, minLength: minLength(1), maxLength: maxLength(150) },
                description: { minLength: minLength(1), maxLength: maxLength(5000) }
            }
        }
    },
    methods: {
        async submit(event) {
            await this.$api.post(this, event, '/activities', (res) => {
                Object.assign(this.inputs, this.$options.data().inputs);
                this.validator.$reset();
                this.$toaster.success(this.$t('admin.referentials.activities.createSuccess'));
            });
        },
        async initCertifications() {
            await this.$api.get(this, '/certifications/label-values', (res) => {
                this.certifications = res.body;
            });
        }
    },
    async beforeMount() {
        await this.initCertifications();
    }
}
</script>

<template>
    <Card>
        <template #title>
            <h1>{{ $t('admin.referentials.activities.createTitle') }}</h1>
        </template>
        <template #content>
            <Form :submit="submit" :validator="validator" :globalErrors="vuelidateExternalResults.globals"
                buttonPath="save">
                <div class="mb-3">
                    <FormLabel for="certificationId" required="true"
                        path="admin.referentials.activities.certification" />
                    <select name="certificationId" id="certificationId" class="form-select"
                        v-model.number="inputs.certificationId" v-invalid-field>
                        <option selected disabled value="0">
                            {{ $t('admin.referentials.activities.certificationOption') }}
                        </option>
                        <option v-for="item in certifications" :value="item.id">
                            {{ item.name + ' (' + item.code + ')' }}
                        </option>
                    </select>
                    <div class="form-text">{{ $t('admin.referentials.activities.certificationHelp') }}</div>
                </div>
                <div class="row">
                    <div class="col-md-9 mb-3">
                        <FormLabel for="name" required="true" path="admin.referentials.activities.name" />
                        <input name="name" id="name" type="text" class="form-control" v-model.trim="inputs.name"
                            maxlength="150" v-invalid-field>
                    </div>
                    <div class="col-md-3 mb-3">
                        <FormLabel for="code" required="true" path="admin.referentials.activities.code" />
                        <input name="code" id="code" type="text" class="form-control" v-model.trim="inputs.code"
                            maxlength="15" v-invalid-field>
                        <div class="form-text">{{ $t('admin.referentials.activities.codeHelp') }}</div>
                    </div>
                </div>
                <div class="mb-3">
                    <FormLabel for="description" path="admin.referentials.activities.description" />
                    <textarea name="description" id="description" rows="10" class="form-control"
                        v-model.trim="inputs.description" maxlength="5000" v-invalid-field></textarea>
                    <div class="form-text">{{ $t('admin.referentials.activities.descriptionHelp') }}</div>
                </div>
            </Form>
        </template>
    </Card>
</template>
