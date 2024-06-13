<script>
export default {
    name: 'Card',
    props: {
        imgSrc: null
    }
}
</script>
<template>
    <div class="card rounded-0 h-100 shadow-sm">
        <img v-if="imgSrc" :src="imgSrc" class="rounded-0 card-img-top">
        <div class="card-body">
            <slot name="title" />
            <hr>
            <slot name="content" />
        </div>
    </div>
</template>
<style scoped>
.card-body hr {
    margin: 1.2rem 0 1.2rem;
}
</style>