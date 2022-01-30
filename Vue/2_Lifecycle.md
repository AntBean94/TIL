# 2. 인스턴스 라이프 사이클

인스턴스 라이프 사이클이란 뷰의 인스턴스가 생성되어 소멸되기까지 거치는
과정을 의미한다. 인스턴스 생성 뒤 라이브러리 내부적으로 다음의 과정이 진행
- data 속성의 초기화 및 관찰(Reactivity, 반응성 주입)
  - 인스턴스 생성 후에 추가된 데이터에 대해 반응성이 없는 이유
- 뷰 템플릿 코드 컴파일(Virtual DOM -> DOM 변환)
- 인스턴스를 DOM에 부착
  
## 라이프 사이클 다이어 그램
아래의 링크 참고(Vue document)
- https://vuejs.org/v2/guide/instance.html

주요 훅(Vue 2.x)
https://vuejs.org/v2/api/#Options-Lifecycle-Hooks
- beforeCreate: Called synchronously immediately after the instance has been initialized, before data observation and event/watcher setup.
- created: Called synchronously after the instance is created. At this stage, the instance has finished processing the options which means the following have been set up: data observation, computed properties, methods, watch/event callbacks. However, the mounting phase has not been started, and the $el property will not be available yet.
- beforeMount: Called right before the mounting begins: the render function is about to be called for the first time.

This hook is not called during server-side rendering.
- mounted: Called after the instance has been mounted, where el is replaced by the newly created vm.$el. If the root instance is mounted to an in-document element, vm.$el will also be in-document when mounted is called.

Note that mounted does not guarantee that all child components have also been mounted. If you want to wait until the entire view has been rendered, you can use vm.$nextTick inside of mounted:
```javascript
mounted: function() {
    this.$nextTick(function () {
        // Code that will run only after the
        // entire view has been rendered
    })
}

```
This hook is not called during server-side rendering.
- beforeUpdate: Called when data changes, before the DOM is patched. This is a good place to access the existing DOM before an update, e.g. to remove manually added event listeners.

This hook is not called during server-side rendering, because only the initial render is performed server-side.
- updated: Called after a data change causes the virtual DOM to be re-rendered and patched.

The component’s DOM will have been updated when this hook is called, so you can perform DOM-dependent operations here. However, in most cases you should avoid changing state inside the hook. To react to state changes, it’s usually better to use a computed property or watcher instead.

Note that updated does not guarantee that all child components have also been re-rendered. If you want to wait until the entire view has been re-rendered, you can use vm.$nextTick inside of updated:
```javascript
updated: function () {
  this.$nextTick(function () {
    // Code that will run only after the
    // entire view has been re-rendered
  })
}
```
This hook is not called during server-side rendering.
- beforeDestroy: Called right before a Vue instance is destroyed. At this stage the instance is still fully functional.

This hook is not called during server-side rendering.
- destroyed: Called after a Vue instance has been destroyed. When this hook is called, all directives of the Vue instance have been unbound, all event listeners have been removed, and all child Vue instances have also been destroyed.

This hook is not called during server-side rendering.

이외에도
- errorCaptured
- activated
와 같은 훅이 있다.
자세한 내용은 아래 링크를 참고
https://vuejs.org/v2/api/#Options-Lifecycle-Hooks