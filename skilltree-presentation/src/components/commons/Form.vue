<script>
export default {
    name: 'Form',
    props: {
        buttonPath: null,
        validator: null,
        globalErrors: null,
        submit: null
    }
}
</script>
<template>
    <form @submit.prevent="submit" novalidate>
        <GlobalErrors :errors="globalErrors" />
        <slot />
        <div class="d-grid d-md-flex justify-content-md-end">
            <button type="submit" class="btn btn-dark" :disabled="validator.$invalid">{{ $t(buttonPath) }}</button>
        </div>
    </form>
</template>
