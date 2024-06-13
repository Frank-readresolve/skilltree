<script>
export default {
    name: 'Header'
}
</script>
<template>
    <header class="bg-dark sticky-top">
        <nav class="container-xl navbar-dark navbar navbar-expand-md">
            <div class="container-fluid">
                <RouterLink :to="{ name: 'home' }" class="st-brand navbar-brand">{{ $t('brand') }}</RouterLink>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div id="navbar" class="collapse navbar-collapse">
                    <slot />
                </div>
            </div>
        </nav>
        <Toaster />
    </header>
</template>
<style scoped>
.st-brand {
    font-family: 'Bangers', cursive;
    letter-spacing: .2rem;
    color: var(--st-colors-yellow);
}

.st-brand:hover {
    transform: rotate(-2deg);
}

header {
    box-shadow: 0 0.1rem .5rem rgb(0 0 0 / 15%), inset 0 -1px 0 rgb(255 255 255 / 15%);
}

header:deep() .nav-item,
header:deep() .nav-link {
    color: var(--st-colors-greylight);
}

header:deep() .nav-item a,
header:deep() .nav-item a:visited {
    text-decoration: none;
}

header:deep() .nav-item>a:active,
header:deep() .nav-item>a:hover,
header:deep() .nav-link:active,
header:deep() .nav-link:hover {
    color: var(--st-colors-yellow);
}
</style>