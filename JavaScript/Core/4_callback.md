# 4. 콜백 함수(callback function)

### 콜백 함수
callback은 '부르다', '호출(실행)하다'는 의미인 call과, '뒤돌아오다', '되돌다'는 의미인 back의 합성어로,
'되돌아 호출해달라'는 명령이다. 어떤 함수 X를 호출하면서 '특정 조건일 때 함수 Y를 실행해서 나에게 알려달라'는
요청을 함께 보내는 것이다.

### 제어권
콜백 함수는 다른 코드(함수 또는 메서드)에게 인자로 넘겨줌으로써 그 **제어권**도 함께 위임하는데
콜백 함수를 위임받은 코드는 자체적인 내부 로직에 의해 이 콜백 함수를 적절한 시점에 실행한다.

**호출 시점**
콜백함수 예제
```javascript
var count = 0;
var timer = scope.setInterval(function () {
    console.log(count);
    if (++count > 4) clearInterval(timer);
}, 300);
```
scope에는 Window 객체 또는 Worker의 인스턴스가 들어올 수 있다. 두 객체 모두 setInterval 메서드를
지원하기 때문이며, 일반적인 브라우저 환경에서는 window를 생략해서 함수처럼 사용이 가능하다.