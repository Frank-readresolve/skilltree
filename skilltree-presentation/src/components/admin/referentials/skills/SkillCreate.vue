<script>
import { useVuelidate } from '@vuelidate/core';
import { required, minValue, minLength, maxLength } from '@vuelidate/validators';

export default {
    name: 'SkillCreate',
    setup() {
        return {
            validator: useVuelidate({ $autoDirty: true })
        }
    }, data() {
        return {
            certifications: [],
            certificationId: 0,
            activities: [],
            inputs: {
                activityId: 0,
                code: null,
                name: null,
                description: null
            }, vuelidateExternalResults: {}
        }
    },
    validations() {
        return {
            inputs: {
                activityId: { minValue: minValue(1) },
                code: { required, minLength: minLength(1), maxLength: maxLength(15) },
                name: { required, minLength: minLength(1), maxLength: maxLength(150) },
                description: { minLength: minLength(1), maxLength: maxLength(5000) }
            }
        }
    },
    methods: {
        async submit(event) {
            await this.$api.post(this, event, '/skills', (res) => {
                Object.assign(this.inputs, this.$options.data().inputs);
                this.validator.$reset();
                this.$toaster.success(this.$t('admin.referentials.skills.createSuccess'));
            });
        },
        async initCertifications() {
            await this.$api.get(this, '/certifications/label-values', (res) => {
                this.certifications = res.body;
            });
        },
        async refreshActivities(event) {
            await this.$api.get(this, `/certifications/${this.certificationId}/activities/label-values`, (res) => {
                this.activities = res.body;
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
            <h1>{{ $t('admin.referentials.skills.createTitle') }}</h1>
        </template>
        <template #content>
            <Form :submit="submit" :validator="validator" :globalErrors="vuelidateExternalResults.globals"
                buttonPath="save">
                <div class="mb-3">
                    <FormLabel for="certificationId" path="admin.referentials.skills.certification" />
                    <select name="certificationId" id="certificationId" class="form-select"
                        v-model.number="certificationId" @change="refreshActivities">
                        <option selected disabled value="0">
                            {{ $t('admin.referentials.skills.certificationOption') }}
                        </option>
                        <option v-for="item in certifications" :value="item.id">
                            {{ item.name + ' (' + item.code + ')' }}
                        </option>
                    </select>
                    <div class="form-text">{{ $t('admin.referentials.skills.certificationHelp') }}</div>
                </div>
                <hr>
                <div class="mb-3">
                    <FormLabel for="activityId" required="true" path="admin.referentials.skills.activity" />
                    <select name="activityId" id="activityId" class="form-select" v-model.number="inputs.activityId"
                        v-invalid-field>
                        <option selected disabled value="0">
                            {{ $t('admin.referentials.skills.activityOption') }}
                        </option>
                        <option v-for="item in activities" :value="item.id">
                            {{ item.name + ' (' + item.code + ')' }}
                        </option>
                    </select>
                    <div class="form-text">{{ $t('admin.referentials.skills.activityHelp') }}</div>
                </div>
                <div class="row">
                    <div class="col-md-9 mb-3">
                        <FormLabel for="name" required="true" path="admin.referentials.skills.name" />
                        <input name="name" id="name" type="text" class="form-control" v-model.trim="inputs.name"
                            maxlength="150" v-invalid-field>
                    </div>
                    <div class="col-md-3 mb-3">
                        <FormLabel for="code" required="true" path="admin.referentials.skills.code" />
                        <input name="code" id="code" type="text" class="form-control" v-model.trim="inputs.code"
                            maxlength="15" v-invalid-field>
                        <div class="form-text">{{ $t('admin.referentials.skills.codeHelp') }}</div>
                    </div>
                </div>
                <div class="mb-3">
                    <FormLabel for="description" path="admin.referentials.skills.description" />
                    <textarea name="description" id="description" rows="10" class="form-control"
                        v-model.trim="inputs.description" maxlength="5000" v-invalid-field></textarea>
                    <div class="form-text">{{ $t('admin.referentials.skills.descriptionHelp') }}</div>
                </div>
            </Form>
        </template>
    </Card>
</template>
