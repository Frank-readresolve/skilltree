<script>
export default {
    name: 'Toaster'
}
</script>
<template>
    <div id="toaster" class="toast-container p-3 top-0 start-50 translate-middle-x">
        <div class="toast shadow-sm">
            <div class="toast-header">
                <strong class="text-dark me-auto">{{ $t('toaster') }}</strong>
                <button type="button" class="fs-6 btn-close" data-bs-dismiss="toast"></button>
            </div>
            <div class="toast-body"></div>
        </div>
    </div>
</template>