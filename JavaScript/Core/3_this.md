# 3. this

함수와 객체의 구분이 모호한 javascript에서는 this가 이 둘을 구분하는 거의 유일한 기능이다.
javascript에서 this는 실행컨텍스트가 생성될 때 함께 결정된다. 실행 컨텍스트는 함수를 호출할 때
생성되므로 바꿔말하면 **this는 함수를 호출할 때 결정된다**고 말할 수 있다.

### (1) 전역 공간에서의 this
전역 공간에서 this는 전역객체를 가리킨다. 자바스크립트 런타임 환경에 따라 다른 이름과 정보를 가지는데
브라우저 환경에서 window, Node.js 환경에서 global이다.

```javascript
// browser
var a = 1;
console.log(a);         // 1
console.log(window.a);  // 1
console.log(this.a);    // 1

// Node.js
var a = 1;
console.log(a);         // 1
console.log(global.a);  // 1
console.log(this.a);    // 1
```
위와 같이 전역변수를 선언했음에도 불구하고 전역객체의 프로퍼티로 같은 값이 출력되는 이유는
**전역변수를 선언하면 자바스크립트 엔진은 이를 전역객체의 프로퍼티로 할당**하기 때문이다.
이는 자바스크립트 엔진이 var 연산자로 선언한 변수를 특정 객체의 프로퍼티로 저장하기 때문이다.
그리고 이 특정 객체가 실행 컨텍스트의 Lexical Environment이다.

따라서 var 연산자가 아닌 전역객체의 프로퍼티로 값을 할당해도 전역 변수로 접근이 가능함을 알 수 있다.
```javascript
global.a = 1000;
console.log(a);         // 1000
console.log(global.a);  // 1000
console.log(this.a);    // 1000
```

전역변수 선언과 전역객체 프로퍼티 할당은 거의 동일하게 작동하는 것처럼 보이지만
그렇지 않은 경우도 있다.**(변수 삭제, 호이스팅)**

```javascript
var a = 1;
delete window.a;                    // false
console.log(a, window.a, this.a);   // 1 1 1

var b = 2;
delete b;                           // false
console.log(b, window.b, this.b);   // 2 2 2

window.c = 1;
delete c;                           // true
console.log(c, window.c, this.c);   // 1 1 1

window.d = 2;
delete window.d;                    // true
console.log(d, window.d, this.d);   // 2 2 2
```
삭제의 경우 var 연산자로 선언한 전역변수는 삭제가 되지 않고
글로벌 객체의 속성으로 할당한 값만 삭제가 되는것을 확인할 수 있다.
(이는 사용자가 의도치 않게 삭제하는 것을 방지하는 차원에서 바련한 나름의 방어 전략이라고 볼 수도 있다.)
이는 전역 변수 선언시 자바스크립트 엔진이 이를 자동으로 전역객체의 프로퍼티로 할당하며
추가적으로 해당 프로퍼티의 **configurable** 속성을 false로 정의하기 때문이다.

이외에도 var로 선언한 전역변수는 실행컨텍스트의 environmentRecord 수집대상이기 때문에 호이스팅이 발생하지만,
전역 객체의 프로퍼티로 할당하는 값은 호이스팅이 발생하지 않는다.

### (2) 메서드로 호출할 때 그 메서드 내부에서의 this
**함수와 메서드의 차이**
함수와 메서드를 구별하는 차이는 **독립성**에 있다.
함수는 그 자체로 독립적인 기능을 수행하는 반면, 메서드는 자신을 호출한 대상 객체에 관한 동작을 수행한다.
javascript는 상황별로 this를 다르게 적용하여 이 개념을 구현했다.

javascript는 어떤 함수를 객체의 프로퍼티에 할당한다고 해서 그 자체로 무조건 메서드가 되는 것이 아니라
**객체의 메서드로서 호출**한 경우에만 메서드로 동작하고, 이외에는 함수로 동작한다.

**'함수로서의 호출'과 '메서드로서의 호출'은 함수앞에 점(.)의 유무로 구별한다.**
```javascript
var func = function (x) {
    console.log(this, x);
};

// 함수 호출
func(1);  // Window { ... } 1

// 메서드로 호출
var obj = {
    method: func
};
obj.method(1);  // { method: func } 1
```

메서드로 호출하는 경우는 함수의 이름앞에 점(.)이 있거나 대괄호 표기법으로 호출한 경우이다.
이외의 모든 경우는 함수로 호출한 것이다.

**메서드 내부에서의 this**
this에는 호출한 주체에 대한 정보가 담기는데 메서드로서 호출한 경우 호출 주체는 바로 함수명
앞의 객체이다.

### (3) 함수로서 호출할 때 그 함수 내부에서의 this
어떤 함수를 함수로서 호출할 경우에는 this가 지정되지 않는다. this가 지정되지 않는 경우
실행 컨텍스트를 활성화 시점에서 자동으로 전역객체를 바라보게 된다.(설계상의 오류)

따라서 함수가 객체 내부에 정의되어있고 객체 내부에서 호출한다 하더라도 함수로서 호출하면
this는 전역객체를 가리킨다.(매우 혼란스러운 부분)
```javascript
var obj1 = {
    outer: function () {
        console.log(this);          // (1)
        var innerFunc = function () {
            console.log(this);      // (2, 함수호출) (3, 메서드호출)
        }
        innerFunc();

        var obj2 = {
            innerMethod: innerFunc
        };
        obj2.innerMethod();
    }
};
obj1.outer();
```
위의 코드에서 this가 가리키는 객체는 각각 아래와 같다.
(1) obj1
(2) global, window
(3) obj2

결국 this 바인딩에 관해서는 함수를 실행하는 당시의 주변 환경(메서드 재부인지, 함수 내부인지)등은
중요하지 않고, 오직 해당 함수를 호출하는 구문 앞에 점 또는 대괄호 표기가 있는지 없는지가 관건이다.

메서드 내부 함수에서 this를 우외하는 방법이 있다.
별도의 변수에 객체를 저장하고 이를 객체 내부의 함수에 전달하는 것이다.
```javascript
var obj1 = {
    var self = this
    outer: function () {
        console.log(self)
    };
    outer();
}
```
위와 같이 함수를 호출하면 지정한 객체를 함수에 전달할 수 있다.
이때는 일반적으로 self 변수명(이외에도 _this, that, _ 등)을 사용한다.

**화살표 함수(arrow function, this를 바인딩하지 않는 함수)**
일반함수의 경우 this를 지정하지 않으면 전역객체를 바인딩하지만 arror function의 경우
이 this지정과정이 아예 빠지기 때문에 상위 스코프의 this를 그대로 활용할 수 있다.

### (4) 콜백 함수 호출 시 그 함수 내부에서의 this
콜백 함수에서의 this는 '무조건 이거다!'라고 정의할 수 없다. 콜백 함수의 제어권을 가지는 함수(메서드)가
콜백 함수에서의 this를 무엇으로 할지 결정하며, 특별히 정의하지 않는 경우 전역객체를 가리킨다.

### (5) 생성자 함수 내부에서의 this
자바스크립트는 함수에 생성자로서의 역할을 함께 부여했는데 new 명령어와 함께 함수를 호출하면 해당 함수가
생성자로 동작하게 된다.

어떤 함수가 생성자 함수로서 호출된 경우 **내부에서의 this는 곧 새로 만들 구체적인 인스턴스 자신**이 된다.
생성자 함수를 호출하면 우선 `생성자의 prototype` 속성을 참조하는 `__proto__`라는 속성이 있는 
객체(인스턴스)를 만들고, 미리 준비된 공통 속성 및 개성을 해당 객체(this)에 부여한다. 이렇게 인스턴스가 생성된다.
```javascript
var Cat = function (name, age) {
    this.bark = '야옹';
    this.name = name;
    this.age = age;
};

var choco = new Cat('초코', 7);
var navi = new Cat('나비', 5);
console.log(choco, navi);

/* 결과
 * Cat { bark: '야옹', name: '초코', age: 7 }
 * Cat { bark: '야옹', name: '나비', age: 5 }
 */
```

### 명시적으로 this를 바인딩하는 방법

**call 메서드**

**apply 메서드**

**bind 메서드**

**arrow function**

**별도의 인자로 this를 받는 경우**
