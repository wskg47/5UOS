<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  components: {},
}
</script>

<style lang="scss">
body,
html {
  padding: 10px;
  margin: 0px;
  overflow-x: hidden;
  // overflow-y: hidden;
  background-color: rgb(220, 223, 234);
  #app {
    border-radius: 20px;
    overflow: hidden;
    height: 100%;
  }
}
</style>
