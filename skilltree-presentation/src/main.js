import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import plugins from './plugins';
import directives from './directives';
import components from './components/commons';
import './assets/skilltree.css';

const app = createApp(App);

app.use(router);
plugins(app);
directives(app);
components(app);

app.config.errorHandler = (err, comp) => {
    const messagePath = 'errors.unhandledError';
    comp.$toaster.error(comp.$t(messagePath));
    console.error(err);
};

app.mount('#app');
