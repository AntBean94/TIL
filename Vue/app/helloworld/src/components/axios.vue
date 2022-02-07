<template>
  <div>
    <button v-if="!result" @click="fetchData">
        clickme!
    </button>
    <div v-else>
        {{ result }}
        <br>
        <button @click="initializer">
            back
        </button>
    </div>
  </div>
</template>

<script>

import axios from 'axios'

export default {
    data() {
        return {
            result: ""
        }
    },
    methods: {
        fetchData: function() {
            axios.get('https://jsonplaceholder.typicode.com/users/')
            .then((res) => {
                console.log(res)
                this.result = res.data[0]["name"]
            })
            .catch(err => {
                console.log(err)
            })
        },
        initializer: function() {
            this.result = ""
        }
    }
}
</script>

<style>

</style>