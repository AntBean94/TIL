# 7. watch 속성

특정 데이터의 변화를 감지해 자동으로 특정 로직을 수행하는 속성

## 코드 형식
```javascript
new Vue({
    data() {
        return {
            message: 'Hello'
        }
    },
    watch: {
        message: function(value, oldValue) {
            console.log(value);
        }
    }
})
```

## 실용 문법
1. watch 속성에 메서드 함수 연결
```javascript
new Vue({
    data() {
        return {
            message: 'Hello'
        }
    },
    methods: {
        logMessage() {
            console.log(this.message);
        }
    },
    watch: {
        'message': 'logMessage'  // 대상 속성과 메서드 함수 매칭
    }
})
```

2. 핸들러와 초기 실행 옵션
- watch 대상 속성에 아래와 같이 `handler()`와 `immediate`속성을 정의할 수 있다.
```javascript
new Vue({
    data() {
        return {
            message: 'Hello'
        }    
    },
    watch: {
        'message': {
            handler(value, oldValue) {
                console.log(value);
            },
            immediate: true  // 컴포넌트가 생성되자마자 즉시 실행
        }
    }
})
```