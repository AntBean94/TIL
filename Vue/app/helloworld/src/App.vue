<template>
  <div id="app">
    HelloWorld!
    <axios></axios>
    <tab-container></tab-container>
  </div>
</template>

<script>

import axios from '@/components/axios'
import TabContainer from './components/TabContainer.vue'

export default {
  name: 'App',
  components: {
    axios,
    TabContainer
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
