import toaster from './toaster';
import axios from './axios';
import api from './api';
import i18n from './i18n';

const plugins = (app) => {
    app.use(i18n);
    app.use(toaster);
    app.use(axios);
    app.use(api);
};

export default plugins;
