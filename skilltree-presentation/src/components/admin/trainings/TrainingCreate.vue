<script>
import { useVuelidate } from '@vuelidate/core';
import { required, minValue, minLength, maxLength } from '@vuelidate/validators';
import { isDate, isBefore, isAfter } from '../../../utils/validators';

// FIXME: get pattern from I18N context:
const PATTERN = /(?<dd>\d{2})\/(?<mm>\d{2})\/(?<yyyy>\d{4})/;

export default {
    name: 'TrainingCreate',
    setup() {
        return {
            validator: useVuelidate({ $autoDirty: true })
        }
    }, data() {
        return {
            certifications: [],
            inputs: {
                certificationId: 0,
                name: null,
                startDate: null,
                endDate: null,
                description: null
            }, vuelidateExternalResults: {}
        }
    },
    validations() {
        return {
            inputs: {
                certificationId: { minValue: minValue(1) },
                name: { required, minLength: minLength(1), maxLength: maxLength(150) },
                startDate: {
                    required,
                    date(val, { endDate }) {
                        return isDate(val, PATTERN) && isBefore(val, endDate, PATTERN);
                    }
                },
                endDate: {
                    required,
                    date(val, { startDate }) {
                        return isDate(val, PATTERN) && isAfter(val, startDate, PATTERN);
                    }
                },
                description: { required, minLength: minLength(1), maxLength: maxLength(2000) }
            }
        }
    },
    methods: {
        async submit(event) {
            await this.$api.post(this, event, '/trainings', (res) => {
                Object.assign(this.inputs, this.$options.data().inputs);
                this.validator.$reset();
                this.$toaster.success(this.$t('admin.trainings.createSuccess'));
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
            <h1>{{ $t('admin.trainings.createTitle') }}</h1>
        </template>
        <template #content>
            <Form :submit="submit" :validator="validator" :globalErrors="vuelidateExternalResults.globals"
                buttonPath="save">
                <div class="mb-3">
                    <FormLabel for="certificationId" required="true" path="admin.trainings.certification" />
                    <select name="certificationId" id="certificationId" class="form-select"
                        v-model.number="inputs.certificationId" v-invalid-field>
                        <option selected disabled value="0">
                            {{ $t('admin.trainings.certificationOption') }}
                        </option>
                        <option v-for="item in certifications" :value="item.id">
                            {{ item.name + ' (' + item.code + ')' }}
                        </option>
                    </select>
                    <div class="form-text">{{ $t('admin.trainings.certificationHelp') }}</div>
                </div>
                <div class="mb-3">
                    <FormLabel for="name" required="true" path="admin.trainings.name" />
                    <input name="name" id="name" type="text" class="form-control" v-model.trim="inputs.name"
                        maxlength="150" v-invalid-field>
                    <div class="form-text">{{ $t('admin.trainings.nameHelp') }}</div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <FormLabel for="startDate" required="true" path="admin.trainings.startDate" />
                        <input name="startDate" id="startDate" type="text" class="form-control"
                            :placeholder="$t('datePatternPlaceHolder')" v-model.trim="inputs.startDate" maxlength="10"
                            v-invalid-field="'endDate'">
                    </div>
                    <div class="col-md-6 mb-3">
                        <FormLabel for="endDate" required="true" path="admin.trainings.endDate" />
                        <input name="endDate" id="endDate" type="text" class="form-control"
                            :placeholder="$t('datePatternPlaceHolder')" v-model.trim="inputs.endDate" maxlength="10"
                            v-invalid-field="'startDate'">
                        <div class="form-text">{{ $t('admin.trainings.endDateHelp') }}</div>
                    </div>
                </div>
                <div class="mb-3">
                    <FormLabel for="description" required="true" path="admin.trainings.description" />
                    <textarea name="description" id="description" rows="6" class="form-control"
                        v-model.trim="inputs.description" maxlength="2000" v-invalid-field></textarea>
                    <div class="form-text">{{ $t('admin.trainings.descriptionHelp') }}</div>
                </div>
            </Form>
        </template>
    </Card>
</template>
