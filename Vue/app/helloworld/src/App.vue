<template>
  <div id="app">
    HelloWorld!
    <axios></axios>
    <br>
    <router-link to="/tab">tab 이동</router-link>
    <router-link to="/flexbox">플렉스 박스</router-link>
    <hr>
    <router-view></router-view>
  </div>
</template>

<script>

import axios from '@/components/axios'

export default {
  name: 'App',
  components: {
    axios,
  },
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
