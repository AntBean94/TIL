<template>
    <div>
        <h2>{{ char }}</h2>
        <Foo></Foo>        
    </div>
</template>

<script>
import Foo from "@/components/Foo"

export default {
    data() {
        return {
            char: "여기는 v-slot 학습공간입니다."
        }
    },
    components: {
        Foo
    }
}
</script>

<style>

</style>