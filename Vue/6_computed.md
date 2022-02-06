# 6. computed 속성

데이터 표현을 더 직관적이고 간결하게 도와주는 속성이다.

## 예시
```html
<div>{{ message.split('').reverse().join('') }}</div>
```

위 코드는 message라는 데이터 속성의 문자열 순서를 역으로 변환해주는 코드이다.
message값이 만약 Hello라면 olleH를 출력한다. 이처럼 콧수염 괄호 안에서 데이터
속성의 값만 표시할 뿐만 아니라 자바스크립트의 내장 API를 활용해 가공이 가능하다.

하지만, 이와같은 계산식을 사용하면 템플릿의 가독성이 현저히 떨어진다. 따라서
computed속성을 사용한다.
```javascript
computed: {
    reverseMessage() {
        return this.message.split('').reverse().join('');
    }
}
```
```html
<div>{{ reverseMessage }}</div>
```

## 장점
컴퓨티드 속성은 단순히 뷰 템플릿 코드의 가독성만 높여줄 뿐만 아니라 컴퓨티드 속성의 대상
으로 정한 data 속성이 변했을 때 이를 감지하고 자동으로 다시 연산해주는 장점이 있다.

## 주의사항
1. 컴퓨티드 속성은 인자를 받지 않는다.
2. HTTP 통신과 같이 컴퓨팅 리소스가 많이 필요한 로직을 정의하지 않는다.
    - 리소스가 많이 필요한 로직은 Methods나 watch에 넣자.
