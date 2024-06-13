import auth from '../security/auth';
import { toIsoStringDate } from '../utils/converters';

const ERR_EVENT = new Event('st:server-error');

// FIXME: get pattern from I18N context:
const PATTERN = /(?<dd>\d{2})\/(?<mm>\d{2})\/(?<yyyy>\d{4})/;

function getHttpConfig(url) {
    let httpConfig = null;
    if (url !== '/accounts/sign-in') {
        const token = auth.getToken();
        httpConfig = {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        };
    }
    return httpConfig;
}

function convert(inputs) {
    const data = {};
    if (inputs) {
        Object.assign(data, inputs);
        for (const property in data) {
            const value = data[property];
            const type = typeof value;
            if (type === 'string' && value !== null && value === '') {
                data[property] = null;
            } else if (type === 'string' && property.endsWith('Date')) {
                data[property] = toIsoStringDate(value, PATTERN);
            }
        }
    }
    return data;
}

export default {
    install: (app) => {
        app.config.globalProperties.$api = {
            post: async (comp, event, url, success) => {
                // FIXME: change design
                comp.validator.$clearExternalResults();
                const data = convert(comp.inputs);
                const httpConfig = getHttpConfig(url);
                const response = await comp.$http.post(url, data, httpConfig);
                if (response.status === 422 || response.status === 401) {
                    const errors = response.body.errors;
                    Object.assign(comp.vuelidateExternalResults, errors);
                    if (errors.fields) {
                        const elements = event.target.elements;
                        Object.keys(errors.fields).forEach((k) => {
                            elements[k].dispatchEvent(ERR_EVENT);
                        });
                    }
                } else if (response.ok && success) {
                    success(response);
                }
            },
            get: async (comp, url, success) => {
                const httpConfig = getHttpConfig(url);
                const response = await comp.$http.get(url, httpConfig);
                if (response.ok && success) {
                    success(response);
                }
            },
            patch: async (comp, url, success) => {
                const httpConfig = getHttpConfig(url);
                const response = await comp.$http.patch(url, null, httpConfig);
                if (response.ok && success) {
                    success(response);
                }
            }
        }
    }
}
