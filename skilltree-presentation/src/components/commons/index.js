import Form from './Form.vue';
import FormLabel from './FormLabel.vue';
import GlobalErrors from './GlobalErrors.vue';
import Header from './Header.vue';
import Toaster from './Toaster.vue';
import Card from './Card.vue';
import Logout from './Logout.vue';

const components = (app) => {
    app.component('Form', Form);
    app.component('FormLabel', FormLabel);
    app.component('GlobalErrors', GlobalErrors);
    app.component('Header', Header);
    app.component('Toaster', Toaster);
    app.component('Card', Card);
    app.component('Logout', Logout);
};

export default components;
