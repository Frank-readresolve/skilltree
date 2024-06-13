import axios from 'axios';

const ACCEPTED_STATUS = [200, 201, 202, 204, 400, 401, 403, 404, 409];

export default {
    install: (app) => {
        const http = axios.create({
            baseURL: import.meta.env.VITE_API_BASE_URL,
            validateStatus: (status) => {
                return ACCEPTED_STATUS.includes(status);
            }
        });
        http.interceptors.response.use((response) => {
            const status = response.status;
            const ok = status >= 200 && status < 300;
            const data = response.data;
            const body = data != '' ? data : null;
            return { status: status, ok: ok, body: body };
        }, (error) => {
            return Promise.reject(error);
        });
        app.config.globalProperties.$http = http;
    }
};
