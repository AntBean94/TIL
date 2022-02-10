<template>
    <div>
        <button-tab>
            <!-- slot 영역 -->
            <h1>First Header</h1>
        </button-tab>
        <button-tab>
            <!-- slot 영역 -->
            <h1>Second Header</h1>
        </button-tab>
        <button-tab>
            <!-- slot 영역 -->
            <h1>Third Header</h1>
        </button-tab>
    </div>
</template>

<script>
import ButtonTab from './ButtonTab.vue'

export default {
    components: {
        ButtonTab
    },
    created() {
        this.check()
    },  
    methods: {
        check: () => {
            console.log('체크')
        }
    }
}
</script>

<style>

</style>