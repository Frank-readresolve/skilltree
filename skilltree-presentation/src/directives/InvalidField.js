function updateFieldState(event, serverError, binding) {
    const el = event.target;
    const classes = el.classList;
    let invalid = true;
    if (!serverError) {
        const comp = binding.instance;
        invalid = comp.validator.inputs[el.name].$error;
        const otherElementId = binding.value;
        if (otherElementId) {
            const otherElement = document.getElementById(otherElementId);
            const otherElementInvalid = comp.validator.inputs[otherElement.name].$error;
            const otherElementClasses = otherElement.classList;
            otherElementInvalid ? otherElementClasses.add('is-invalid') : otherElementClasses.remove('is-invalid');
        }
    }
    invalid ? classes.add('is-invalid') : classes.remove('is-invalid');
}

const InvalidField = {
    beforeMount(el, binding, vnode, prevVnode) {
        el.addEventListener('input', (event) => {
            updateFieldState(event, false, binding);
        });
        el.addEventListener('st:server-error', (event) => {
            updateFieldState(event, true, binding);
        });
    }
};

export default InvalidField;
