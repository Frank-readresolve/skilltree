<script>
import { useVuelidate } from '@vuelidate/core';
import { required, minLength, maxLength, minValue, between } from '@vuelidate/validators';

export default {
    name: 'CertificationCreate',
    setup() {
        return {
            validator: useVuelidate({ $autoDirty: true })
        }
    }, data() {
        return {
            certificationLevels: [],
            inputs: {
                certificationLevelId: 0,
                code: null,
                name: null,
                acronym: null,
                startYear: null,
                description: null
            }, vuelidateExternalResults: {}
        }
    },
    validations() {
        return {
            inputs: {
                certificationLevelId: { minValue: minValue(1) },
                code: { required, minLength: minLength(1), maxLength: maxLength(10) },
                name: { required, minLength: minLength(1), maxLength: maxLength(50) },
                acronym: { required, minLength: minLength(2), maxLength: maxLength(4) },
                startYear: { required, between: between(2018, new Date().getFullYear()) },
                description: { minLength: minLength(1), maxLength: maxLength(5000) }
            }
        }
    },
    methods: {
        async submit(event) {
            await this.$api.post(this, event, '/certifications', (res) => {
                Object.assign(this.inputs, this.$options.data().inputs);
                this.validator.$reset();
                this.$toaster.success(this.$t('admin.referentials.certifications.createSuccess'));
            });
        },
        async initCertificationLevels() {
            await this.$api.get(this, '/certification-levels/label-values', (res) => {
                this.certificationLevels = res.body;
            });
        }
    },
    async beforeMount() {
        await this.initCertificationLevels();
    }
}
</script>

<template>
    <Card>
        <template #title>
            <h1>{{ $t('admin.referentials.certifications.createTitle') }}</h1>
        </template>
        <template #content>
            <Form :submit="submit" :validator="validator" :globalErrors="vuelidateExternalResults.globals"
                buttonPath="save">
                <div class="row mb-3">
                    <div class="col-3">
                        <FormLabel for="acronym" required="true" path="admin.referentials.certifications.acronym" />
                        <input name="acronym" id="acronym" type="text" class="form-control" maxlength="4"
                            v-model.trim="inputs.acronym" v-invalid-field>
                    </div>
                    <div class="col-9">
                        <FormLabel for="name" required="true" path="admin.referentials.certifications.name" />
                        <input name="name" id="name" type="text" class="form-control" v-model.trim="inputs.name"
                            maxlength="50" v-invalid-field>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6 col-lg-3 mb-3">
                        <FormLabel for="code" required="true" path="admin.referentials.certifications.code" />
                        <input name="code" id="code" type="text" class="form-control" v-model.trim="inputs.code"
                            maxlength="10" v-invalid-field>
                        <div class="form-text">{{ $t('admin.referentials.certifications.codeHelp') }}</div>
                    </div>
                    <div class="col-6 col-lg-3 mb-3">
                        <FormLabel for="startYear" required="true" path="admin.referentials.certifications.startYear" />
                        <input name="startYear" id="startYear" type="text" class="form-control"
                            v-model.trim.number="inputs.startYear" maxlength="4" v-invalid-field>
                        <div class="form-text">{{ $t('admin.referentials.certifications.startYearHelp') }}</div>
                    </div>
                    <div class="col-lg-6 mb-3">
                        <FormLabel for="certificationLevelId" required="true"
                            path="admin.referentials.certifications.level" />
                        <select name="certificationLevelId" id="certificationLevelId" class="form-select"
                            v-model.number="inputs.certificationLevelId" v-invalid-field>
                            <option selected disabled value="0">
                                {{ $t('admin.referentials.certifications.levelOption') }}
                            </option>
                            <option v-for="item in certificationLevels" :value="item.id">
                                {{ item.europeanLevel + ' (' + item.equivalence + ')' }}
                            </option>
                        </select>
                    </div>
                </div>
                <div class="mb-3">
                    <FormLabel for="description" path="admin.referentials.certifications.description" />
                    <textarea name="description" id="description" rows="10" class="form-control"
                        v-model.trim="inputs.description" maxlength="5000" v-invalid-field></textarea>
                    <div class="form-text">{{ $t('admin.referentials.certifications.descriptionHelp') }}</div>
                </div>
            </Form>
        </template>
    </Card>
</template>
