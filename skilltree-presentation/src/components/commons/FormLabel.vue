<script>
export default {
    name: 'FormLabel',
    props: {
        path: null,
        required: false
    }
}
</script>
<template>
    <label class="form-label fw-semibold" :class="{ 'required': required }">{{
        $t(path)
    }}</label>
</template>