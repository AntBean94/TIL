<template>
  <div id="app">
    HelloWorld!
    <axios></axios>
    <br>
    <router-link to="/tab" @click.native="changeValue(0)">tab 이동</router-link>
    <br>
    <router-link to="/flexbox" @click.native="changeValue(50)">플렉스 박스</router-link>
    <br>
    <router-link to="/vslot" @click.native="changeValue(100)">v-슬롯</router-link>
    <br>
    <progress :value="value" min="0" max="100"></progress>
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
      value: 0,
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
