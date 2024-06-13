import InvalidField from './InvalidField';

const directives = (app) => {
    app.directive('invalid-field', InvalidField);
};

export default directives;
