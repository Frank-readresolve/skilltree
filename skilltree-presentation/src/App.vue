<script>
export default {
  name: 'App',
  created() {
    document.title = this.$t('brand');
  }
}
</script>
<template>
  <RouterView />
</template>
