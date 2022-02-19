# 2. 실행 컨텍스트

### 목차
1. 실행 컨텍스트란?
2. Variable Environment
3. Lexical Environment
4. this
5. 정리

### 실행 컨텍스트란?
실행 컨텍스트(execution context): 실행할 코드에 제공할 환경 정보들을 모아놓은 객체

자바스크립트는 어떤 실행 컨텍스트가 활성화되는 시점에 선언된 변수를 위로 끌어올리고(호이스팅), 외부 환경 정보를 구성하고,
this값을 설정하는 등의 동작을 수행하는데, 이로 인해 다른 언어데서는 발견할 수 없는 특이한 현상들이 발생한다.

처음 자바스크립트 코드를 실행하면 전역 컨텍스트가 자바스크립트 콜스택에 쌓이며 이후 코드가 순차적으로 실행되고
새로운 함수가 호출되면 해당 함수의 실행 컨텍스트가 콜스택의 상부에 추가적으로 쌓이고 실행 우선순위를 가져간다.

이렇게 실행 컨텍스트가 새로 생성될 때마다 해당 컨텍스트에 관련된 코드들을 실행하는 데 필요한 환경 정보들을 수집해서
실행 컨텍스트 객체에 저장한다. 여기에 담기는 정보는 다음과 같다.
- Variable Environment: 현재 컨텍스트 내의 식별자들에 대한 정보 + 외부 환경 정보. 선언 시점의 Lexical Environment
    스냅샷으로, 변경 사항은 반영되지 않는다.
- Lexical Environment: 처음에는 Variable Environment와 같지만 변경 사항이 실시간으로 반영된다.
- This Binding: this 식별자가 바라보아야 할 객체.

### Variable Environment
최초 실행시 Lexical Environment에 담기는 내용과 완전히 같지만 이후에 달라진다.
실행 컨텍스트를 생성할 때 Variable Encironment에 정보를 먼저 담고, 이를 그대로 복사해서 Lexical Environment를
만들고, 이후에는 Lexical Environment를 주로 활용하게 된다.

내부에는 environmentRecord와 outerEnvironmentReference로 구성되어 있다.

### Lexical Environment
어휘적 환경, 정적 환경으로도 사용하지만 원어 그대로 'Lexical Environment'로 사용하는것이 가장 권장됨(정확한 의미전달 측면)

Lexical Environment 역시 environmentRecord와 outerEnvironmentReference로 구성되어 있는데 각각의 구성항목으로
다음의 개념에 대해 알아본다.
- environmentRecord: 호이스팅(hoisting)
- outerEnvironmentReference: 스코프, 스코프체인

### environmentRecord와 호이스팅
environmentRecord에는 현재 컨텍스트와 관련된 코드의 식별자 정보들이 저장된다.
- 컨텍스트를 구성하는 함수에 지정된 매개변수 식별자
- 선언한 함수가 있는경우 함수 자체
- var로 선언된 변수의 식별자 등
이러한 식별자는 컨텍스트 내부 전체를 처음부터 끝까지 **순서대로** 수집된다.
이렇게 식별자를 수집하는 과정은 실제 코드가 실행되기 전에 이루어지는데 이로 인해 **호이스팅**이라는 개념이 등장한다.
위의 방식대로 실행이 되면 자바스크립트 엔진은 코드가 실행되기 전에 이미 해당 환경에 속한 변수명들을 모두 알고 있는 상태가 된다.
이는 "자바스크립트 엔진은 식별자들을 최상단으로 끌어올려놓은 다음에 실제 코드를 실행"하는 것처럼 보이기 때문에
일반적으로 이러한 방식으로 "호이스팅"이라는 개념을 설명한다.

**호이스팅 규칙**
environmentRecord에는 매개변수의 이름, 함수 선언, 변수명 등이 담긴다.
아래의 예제 코드를 통해 자바스크립트가 어떻게 동작하는지 확인해보자.

**예제1**
```javascript
function a (x) {
    console.log(x);
    var x;
    console.log(x);
    var x = 2;
    console.log(x);
}
a(1);
```

예상 출력(호이스팅 개념을 모른다는 가정하에)
```
1
undefined
2
```

실제 출력(호이스팅)
```
1
1
2
```

두번째 출력에서 undefined가 아닌 1이 출력되는 이유는 다음과 실행되는 것처럼 보이기때문이다.
```javascript
function a (x) {
    var x;
    var x;
    var x;

    x = 1;
    console.log(x);
    console.log(x);
    x = 2;
    console.log(x);
}
a(1);
```

environmentRecord는 현재 실행될 컨텍스트의 대상 코드 내에 **어떤 식별자들이 있는지**에만 관심이 있다.
각 식별자에 어떤 값이 할당될 것인지는 관심이 없기 때문에 위와 같은 동작방식으로 실행된다.

**즉, 변수를 호이스팅할 때 변수명만 끌어올리고 할당 과정은 원래 자리에 그대로 남겨둔다.**

함수 선언의 경우는 다른방식으로 동작하는데
environmentRecord가 선언된 함수는 함수 그 자체까지 수집하므로
마치 함수가 최상단에 **선언**된 것처럼 작동한다.

**예제2**
```javascript
function a () {
    console.log(b);
    var b = 'bbb';
    console.log(b);
    function b () {};
    console.log(b);
}
a();
```
위의 예제가 실제로 어떻게 동작할지 생각해보자.

**함수 선언문과 함수 표현식**
- 함수 선언문(function declaration): function 정의부만 존재하며 별도의 할당 명령이 없는 것
- 함수 표현식(function expression): 정의한 function을 별도의 변수에 할당하는 것을 말한다.

함수 선언문의 경우 반드시 함수명이 정의돼야 하지만, 함수 표현식의 경우 없어도 된다. 케이스로 구분하자면
1. 함수 선언문
2. (익명) 함수 표현식
3. 기명 함수 표현식
```javascript
function a () {};

var b = function () {};

var c = function d () {};
// 실행 d => 에러 발생(d라는 이름은 함수 내부에서만 호출 가능)
```

**함수 선언문과 함수 표현식의 중요한 차이는 호이스팅에서 발생**
함수 선언문은 함수 전체를 호이스팅하는 반면 함수 표현식은 변수만 호이스팅하고 함수는 하나의 값으로 취급해
나중에 할당한다.

함수 선언문을 사용하는 경우 아래에서 정의한 함수가 이전에 정의한 함수와 식별자가 같을 경우
함수를 덮어쓰고 상단에 있는 코드의 실행에 까지 영향을 주므로 디버깅을 어렵게 할 수 있다.
따라서 **함수 표현식이 상대적으로 안전**하다.

### 스코프, 스코프 체인, outerEnvironmentReference
스코프(scope)란 식별자에 대한 유효범위이다.
어떤 경계 A의 외부에서 선언한 변수는 A의 외부뿐 아니라 A의 내부에서도 접근이 가능하지만,
A의 내부에서 선언한 변수는 오직 A내부에서만 접근이 가능하다.

ES5까지의 자바스크립트는 전역공간을 제외하면 오직 함수에 의해서만 스코프가 생성된다.
(ES6 부터는 let, const등을 활용해 블록 단위의 스코프도 사용이 가능하다.)

**스코프 체인**
outerEnvironmentReference는 현재 호출된 함수가 선언될 당시의 LexicalEnvironment를 
참조한다.

예를 들어, A함수 내부에 B함수를 선언하고 다시 B함수 내부에 C함수를 선언한 경우
- C의 outerEnvironmentReference => B의 LexicalEnvironment
- B의 outerEnvironmentReference => A의 LexicalEnvironment

위와같이 outerEnvironmentReference는 연결리스트형태를 띈다.
이러한 구조적 특성으로 인해 여러 스코프에서 동일한 식별자를 선언한 경우에는 
스코프체인 상에서 가장 먼저 발견된 식별자에만 접근이 가능하다.

예제3 (예제를 통해 스코프체인의 개념을 이해해보자)
```javascript
var a = 1;
var outer = function () {
    var inner = function () {
        console.log(a);
        var a = 3;
    };
    inner();
    console.log(a);
};
outer();
console.log(a);
```

* 변수 은닉화: inner 함수 내부에서 a 변수를 선언해서 전역공간의 동일한 a 변수에 접근할 수 없도록 하는 것

**전역변수와 지역변수**
- 전역변수: 전역 공간에서 선언한 변수
- 지역변수: 함수 내부에서 선언한 변수

외부에서 접근할 수 없는 지역변수의 특성을 활용하면
중복된 식별자를 가진 함수로 인해 생길 수 있는 문제를 예방할 수 있다.
따라서, 가급적이면 전역변수의 사용을 줄이고 지역변수를 활용하는것을 지향한다.

### this
실행 컨텍스트의 thisBinding에는 this로 지정된 객체가 저장된다.
자세한 내용은 chapter3에서 학습