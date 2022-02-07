<template>
  <div class="tab panel">
      <!-- 탭 헤더 -->
      <!-- 이 컴포넌트를 등록한 상위 컴포넌트에서 아래 슬롯 태그 영역을 구현하지 않으면 해당 부분은 공백으로 표시된다. -->
      <slot></slot>
      <!-- 탭 본문 -->
      <div class="content">
          Tab Contents
      </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>