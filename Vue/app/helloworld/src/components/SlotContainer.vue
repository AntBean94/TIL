<template>
    <div>
        <h2>{{ char }}</h2>
        <Foo>
            <template v-slot:header>
                <h1>v-slot</h1>
            </template>

            <!-- default는 이름을 붙이지 않은 slot에 표현된다. -->
            <template v-slot:default>
                <b>
                    슬롯은 자식 컴포넌트를 부모컴포넌트에서 사용하고자 하는 시점에 내부를 정의할 수 있다는 점에서 유용하다.
                    이는 컴포넌트의 재사용성과도 밀접한 관계가 있다. 
                </b>
            </template>

            <!-- scoped slot -->
            <template v-slot:scoped="slotProps">
                <b>{{ slotProps.msg }}</b>
            </template>
            <!-- 아래와 같이 특수기호 #을 사용해서 v-slot을 표현할 수 있다. -->
            <template #special="slotProps">
                <b>{{ slotProps.msg }}</b>
            </template>

            <template v-slot:footer>
                <h5>Footer</h5>
            </template>
        </Foo>        
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