<script>
export default {
    name: 'GlobalErrors',
    props: {
        errors: null
    }
}
</script>
<template>
    <div v-if="errors && errors.length > 0" class="alert border-danger text-danger m-0 mb-3">
        <ul v-for="error of errors" class="m-0">
            <li>{{ $t(`errors.badRequest.${error.code}`) }}</li>
        </ul>
    </div>
</template>
<style scoped>
ul {
    list-style: square;
}
</style>