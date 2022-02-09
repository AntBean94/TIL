<template>
  <div id="app">
    HelloWorld!
    <axios></axios>
    <tab-container></tab-container>
    <br>
    <hr>
    <flexbox></flexbox>
  </div>
</template>

<script>

import axios from '@/components/axios'
import TabContainer from './components/TabContainer.vue'
import Flexbox from './components/Flexbox'

export default {
  name: 'App',
  components: {
    axios,
    TabContainer,
    Flexbox,
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
