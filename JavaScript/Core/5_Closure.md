# 5. 클로져(Closure)

### 클로저의 의미
- 자신을 내포하는 함수의 컨텍스트에 접근할 수 있는 함수 - 더글라스 크록포드, <<자바스크립트 핵심 가이드>>
- 함수가 특정 스코프에 접근할 수 있도록 의도적으로 그 스코프에서 정의하는 것 - 에단 브라운, <<러닝 자바스크립트>>
- **함수를 선언할 때 만들어지는 유효범위가 사라진 후에도 호출할 수 있는 함수** - 존 레식, <<자바스크립트 닌자 비급>>
- **이미 생명 주기상 끝난 외부 함수의 변수를 참조하는 함수** - 송형주 고현준, <<인사이드 자바스크립트>>
- 자유변수가 있는 함수와 자유변수를 알 수 있는 환경의 결합 - 에릭 프리먼, <<Head First Javascript Programming>>
- 로컬 변수를 참조하고 있는 함수 내의 함수 - 야마다 요시히로, <<자바스크립트 마스터북>>
- **자신이 생성될 때의 스코프에서 알 수 있었던 변수들 중 언젠가 자신이 실행될 때 사용할 변수들만을 기억하여 유지시키는 함수** - 유인동, <<함수형 자바스크리트 프로그래밍>>

**MDN(Mozilla Developer Network)문서에서 closure의 정의**
"A closure is the combination of a function and the lexical environment within which that function was declared."
- 직역: "클로저는 함수와 그 함수가 선언될 당시의 lexical environment의 상호관계에 따른 현상"

선언될 당시의 lexical environment는 실행 컨텍스트의 구성 요소 중 하나인 `outerEnvironmentReference`에 해당한다.
내부함수 B가 A의 LexicalEnvironment를 언제나 사용하는 것은 아니므로 내부함수에서 외부 변수를 참조하는 경우가 'combination'의 경우에
해당함을 알 수 있다. 이를 정리하면 클로저란 "어떤 함수에서 선언한 변수를 참조하는 내부함수에서만 발생하는 현상"이라고 볼 수 있다.

**외부 함수의 변수를 참조하는 내부 함수(1) - 클로저X**
```javascript
var outer = function () {
    var a = 1;
    var inner = function () {
        console.log(++a);
    };
    inner();
};
outer();
```
outer 함수의 실행 컨텍스트가 종료되면 LexicalEnvironment에 저장된 식별자들(a, inner)에 대한 참조를 지운다. 따라서 참조값이 0이 된 대상은 가비지 컬렉터의 수집대상이 된다.

**외부 함수의 변수를 참조하는 내부 함수(2) - 클로져**
```javascript
var outer = function () {
    var a = 1;
    var inner = function () {
        return ++a;
    };
    return inner;
};
var outer2 = outer();
console.log(outer2());  // 2
console.log(outer2());  // 3
```
6번째 줄에서 inner 함수의 실행 결과가 아닌 inner 함수 자체를 반환했다. 따라서 8번째 줄 outer2 변수는 outer 함수의 실행 결과인 inner함수를 참조하게 된다. 이후 outer2를 호출하면 앞서 반환된 inner함수가 실행된다. 여기서 inner함수 내부에는 a변수가 없기 때문에 `environmentRecord`에는 아무 값도 담기지 않으며 `outerEnvironmentRecord`에 outer함수의 `lexicalEnvironment` 정보가 담기게 되고 여기서 a 변수를 참조하게 된다.

여기서 확인할 수 있는 독특한 점은 outer함수의 실행컨텍스트가 이미 종료된 상황임에도 불구하고 내부함수인 inner함수에서 outer함수의 내부변수에 접근할 수 있다는 점이다. 이는 **가비지 콜렉터의 동작 방식**때문이다.

가비지 컬렉터는 어떤 값을 참조하는 변수가 하나라도 있다면 그 값은 수집 대상에 포함시키지 않는다. 예제에서 outer 함수는 실행 종료 시점에 inner 함수를 반환한다. 외부함수인 outer의 실행이 종료되더라도 내부함수인 inner 함수는 언젠가 outer2를 실행함으로써 호출될 가능성이 생긴 것이다. 언젠가 inner함수의 실행 컨텍스트가 활성화되면 `outerEnvironmentReference`가 outer 함수의 `LexicalEnvironment`를 필요로 할 것이므로 수집 대상에서 제외된다.

이처럼 함수의 실행 컨텍스트가 종료된 후에도 `LexicalEnvironment`가 가비지 컬렉터의 수집 대상에서 제외되는 경우는 위의 예제와 같이 **지역변수를 참조하는 내부함수가 외부로 전달된 경우**가 유일하다. 따라서 "어떤 함수에서 선언한 변수를 참조하는 내부함수에서만 발생하는 현상""이란 "외부 함수의 `LexicalEnvironment`가 가비지 컬렉팅되지 않는 현상"을 말하는 것이다.

이를 바탕으로 **클로저에 대한 정의**를 다시 고쳐보면 다음과 같다.
**클로저란 어떤 함수 A에서 선언한 변수 a를 참조하는 내부함수 B를 외부로 전달할 경우 A의 실행 컨텍스트가 종료된 이후에도 변수 a가 사라지지 않는 현상**

**주의**
'외부로 전달'이 return만을 의미하진 않음
예제
```javascript
(function () {
    var a = 0;
    var intervalId = null;
    var inner = function () {
        if (++a >= 10) {
            clearInterval(intervalId);
        }
        console.log(a);
    };
    intervalId = setInterval(inner, 1000);
})();
```
별도의 외부객체인 window의 메서드(setTimeout 또는 setInterval)에 전달할 콜백 함수 내부에서 지역변수를 참조하고 있다. 이러한 경우에도 지역변수를 참조하는 내부함수를 외부에 전달했기 때문에 '클로져'이다.

### 클로저와 메모리 관리
**메모리 소모는 클로저의 본질적인 특성이다.**
본디 '메모리 누수'라는 표현은 개발자의 의도와 달리 어떤 값의 참조 카운트가 0이 되지 않아 GC(Garbage Collector)의 수거 대상이 되지 않는 경우를 의미한다.
따라서 개발자가 의도적으로 참조 카운트를 0이 되지 않게 설계한 경우는 '누수'라고 할 수 없다.

메모리를 관리하는 방법은 클로저의 필요성이 사라진 경우 클로저의 식별자에 참조형 데이터가 아닌 기본형데이터를 할당함으로써 GC가 수거할 수 있도록 하는 것이다.

**메로리 관리 예제**
```javascript
// (1) return에 의한 클로저의 메모리 해제
var outer = (function () {
    var a = 1;
    var inner = function () {
        return ++a;
    };
    return inner;
})();
console.log(outer);     // 2
console.log(outer);     // 3
outer = null;           // outer 식별자의 inner 함수 참조를 끊음
```
```javascript
// (2) setInterval에 의한 클로저의 메모리 해제
(funciton () {
    var a = 0;
    var intervalId = null;
    var inner = function () {
        if (++a >= 10) {
            clearInterval(intervalId);
            inner = null;               // inner 식별자의 함수 참조를 끊음
        }
        console.log(a);
    };
    intervalId = setInterval(inner, 1000);
})();
```
```javascript
// (3) eventListener에 의한 클로저의 메모리 해제
(function () {
    var count = 0;
    var button = document.createElement('button');
    button.innerText = 'click';

    var clickHandler = function () {
        console.log(++count, 'times clicked');
        if (count >= 10) {
            button.removeEventListener('click', clickHandler);
            clickHandler = null;        // clickHandler 식별자의 함수 참조를 끊음
        }
    };
    button.addEventListener('click', clickHandler);
    document.body.appendChild(button);
})();
```
위의 예시를 보면 모두 '클로저'를 담고 있는 식별자에 대한 참조를 끊음으로서 메모리를 관리하는 것을 확인할 수 있다.

### 클로저 활용 사례
#### 1.콜백 함수 내부에서 외부 데이터를 사용하고자 할 때

#### 2.접근 권한 제어(정보 은닉)

#### 3.부분 적용 함수

#### 4.커링 함수
