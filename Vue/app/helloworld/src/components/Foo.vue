<template>
  <div>
      <!-- 헤더 영역 -->
      <slot name="header"></slot>
      <!-- 본문 영역 -->
      <slot></slot>
      <br>
      <br>
      <!-- scoped slot -->
      <slot name="scoped" :msg="msg"></slot>
      <br>
      <slot name="special" :msg="msg2"></slot>
      <!-- 푸터 영역 -->
      <slot name="footer"></slot>
  </div>
</template>

<script>
export default {
    data() {
        return {
            msg: "지금 보시는 이 문구는 자식 컴포넌트에 정의되어있습니다. scoped-slot은 자식컴포넌트에 정의되어있는 데이터를 부모 컴포넌트에서 보여줄 수 있도록 합니다.",
            msg2: "hi there"
        }
    }
}
</script>

<style>

</style>