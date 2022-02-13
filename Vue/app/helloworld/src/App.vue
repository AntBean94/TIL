<template>
  <div id="app">
    HelloWorld!
    <axios></axios>
    <br>
    <router-link to="/tab" @click.native="changeValue(true)">tab 이동</router-link>
    <br>
    <router-link to="/flexbox" @click.native="changeValue(false)">플렉스 박스</router-link>
    <br>
    <progress v-if="value" value="50" min="0" max="100"></progress>
    <progress v-else value="100" min="0" max="100"></progress>
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
  data() {
    return {
      value: true,
    }
  },
  methods: {
    changeValue(value) {
      console.log(value)
      this.value = value
    }
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
