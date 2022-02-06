# 8. 뷰 필터

화면에 표시되는 텍스트의 형식을 쉽게 변환해주는 기능이다. 가장 간단하게는 단어의
대문자화부터 다국어, 국제 통화 표시 등 다양하게 사용이 가능하다.

## 사용방법
```html
<div id="app">
    {{ message | capitalize }}
</div>
<script>
new Vue({
    el: '#app',
    data: {
        message: 'hello'
    },
    filters: {
        capitalize: function(value) {
            if (!value) return ''
            value = value.toString()
            return value.charAt(0).toUpperCase() + value.slice(1)
        }
    }
})
</script>
```
위 코드의 실행결과로 Hello 텍스트가 화면에 출력된다.

## 필터 등록 패턴
위와 같이 fileters속성을 이용해 각 컴포넌트에 필터를 등록할 수 도 있지만, 실제로는
대부분 filters.js 파일을 별도록 분리해 사용한다.
```javascript
// filters.js
export function capitalize() {
    // ...
}

export function translate() {
    // ...
}
```
```javascript
// main.js
import Vue from 'vue';
import * as filters from 'filters.js';

// Vue에 key와 filter의 각 객체(함수)를 매핑
Object.keys(filters).forEach(function(key) {
    Vue.filters(key, filters[key]);
});

new Vue({
    // ..
})
```