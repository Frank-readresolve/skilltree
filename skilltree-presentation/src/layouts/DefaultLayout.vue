<script>
export default {
    name: 'DefaultLayout'
}
</script>

<template>
    <Header>
        <RouterView name="navbar" />
    </Header>
    <main class="container-xl mt-4 mb-4">
        <RouterView name="view" />
    </main>
</template>
