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
```javascript
Function.prototype.call(thisArg[, arg1[, arg2[, ...]]])
```
call 메서드는 메서드의 호출 주체인 함수를 즉시 실행하도록 하는 명령이다.
이 때 call 메서드의 첫 번재 인자를 this로 바인딩하고, 이후의 인자들을 호출할 함수의 매개변수로 한다.
함수를 그냥 실행하면 this는 전역객체를 참조하지만 call 메서드를 이용하면 임의의 객체를 this로 지정할 수 있다.

예제
```javascript
var func = function (a, b, c) {
    console.log(this, a, b, c)
}

func(1, 2, 3);                  // Window{ ... } 1 2 3
func.call({ x : 1}, 1, 2, 3);   // { x : 1 } 1 2 3
```
함수와 마찬가지로 메서드를 호출할때 역시 call 메서드를 활용하면 메서드를 특정 객체에 바인딩 할 수 있다.
```javascript
var obj1 = {
    a: 1,
    method: function (x, y) {
        console.log(this. a, x, y)
    }
}

obj1.method(1, 2);                  // 1 1 2
obj1.method.call({ a: 4 }, 3, 4);   // 4 3 4
```

**apply 메서드**
```javascript
Function.prototype.apply(thisArg[, argsArray])
```
apply 메서드는 call 메서드와 기능적으로 완전히 동일하며 인자를 받는 방식에서만 차이가 있다.
call 메서드는 첫 번째 인자를 제외한 나머지 모든 인자들을 호출할 함수의 매개변수로 지정하는 반면,
apply 메서드는 두 번째 인자를 **배열**로 받아 그 배열의 요소들을 호출할 함수의 매개변수로 지정한다.

예제
```javascript
var func = function (a, b, c) {
    console.log(this, a, b, c);
}
func.apply({ x: 1 }, [1, 2, 3]); // { x: 1 } 1 2 3
```

**call/apply 메서드의 활용**
1. 유사배열객체(array-like object)에 배열 메서드를 적용
객체에는 배열 메서드를 직접 적용할 수 없지만 **키가 0 또는 양의 정수인 프로퍼티가 존재**하고
**lenth 프로퍼티의 값이 0 또는 양의 정수**인 객체, 즉 배열의 구조와 유사한 객체의 경우(유사배열객체)
call 또는 apply 메서드를 이용해 배열 메서드를 차용할 수 있다.

```javascript
var obj = {
    0: "a",
    1: "b",
    2: "c",
    lenth: 3
};
Array.prototype.push.call(obj, 'd');
console.log(obj);   // { 0: "a", ..., 3: "d", lenth: 4 }

var arr = Array.prototype.slice.call(obj);
console.log(arr);   // ['a', ..., 'd' ] - slice 메서드는 배열을 반환
```
이외에도 함수 내부에서 접근이 가능한 **'arguments'**객체와 `querySelectorAll`, 
`getElementsByClassName` 등의 Node 선택자로 선택한 결과인 NodeList 등에 적용이 가능하다.
배열처럼 인덱스와 length 프로퍼티를 가진 문자열에도 적용이 가능하다. 단, 문자열의 경우 length 프로퍼티가
'읽기'전용이기 때문에 원본 문자열에 변경을 가하는 메서드(push, pop, shift 등)는 에러를 던지며, concat처럼
대상이 반드시 배열이어야 하는 경우에 에러는 나지 않지만 제대로 된 결과를 얻을 수 없다.

ES6에는 유사배열객체 또는 순회 가능한 모든 종류의 데이터 타입을 배열로 전환하는 Array.from 메서드를 새로
도입했다.
```javascript
var obj = {
    0: 'a',
    1: 'b',
    2: 'c',
    length: 3
};
var arr = Array.from(obj);
console.log(arr);           // ['a', 'b', 'c']
```

2. 생성자 내부에서 다른 생성자 호출
생성자 내부에 다른 생성자와 공통된 내용이 있을 경우 call 또는 apply를 이용해 다른 
생성자를 호출하면 반복을 줄일 수 있다.
```javascript
function Person(name, gender) {
    this.name = name;
    this.gender = gender;
};

function Student(name, gender, school) {
    Person.call(this, name, gender);
    this.school = school;
}

function Employee(name, gender, company) {
    Person.call(this, name, gender);
    this.company = company;
}
var gd = new Student('가우디', 'female', '한국대')
var ct = new Employee('캐슬', 'male', '엔프')
```

3. 여러 인수를 묶어 하나의 배열로 전달하고 싶을 때 - apply 활용
여러 개의 인수를 받는 메서드에게 하나의 배열로 인수들을 전달하고 싶을 때 apply 메서드를 사용하면
편리하다.
```javascript
var numbers = [10, 20, 30];
var max = Math.max.apply(null, numbers);
var min = Math.min.apply(null, numbers);
console.log(numbers);

// ES6 펼치기 연산자는 더 간편하다.
var numbers = [10, 20, 30];
var max = Math.max(...numbers);
var min = Math.min(...numbers);
```
call / apply 메서드는 명시적으로 별도의 this를 바인딩하면서 함수 또는 메서드를 실행하는
좋은 방법이지만 오히려 이로인해 this를 예측하기 어렵게 만들수도 있기 때문에 코드 해석에 방해가
될 수 있다.

**bind 메서드**
```javascript
Function.prototype.bind(thisArg[, arg1[, arg2[, ...]]])
```
bind 메서드는 ES5에서 추가된 기능으로, call과 비슷하지만 즉시 호출하지는 않고 넘겨받은
this 및 인수들을 바탕으로 새로운 함수를 반환하기만 하는 메서드이다.
다시 새로운 함수를 호출할 때 인수를 넘기면 그 인수들은 기존 bind 메서드를 호출할 때 전달했던
인수들의 뒤에 이어서 등록된다. 
즉, **bind 메서드는 함수에 this를 미리 적용하는 것과 부분 적용함수를 구현하는 두 가지 목적**을 모두 지닌다.
```javascript
var func = function (a, b, c) {
    console.log(this, a, b, c);
};
func(1, 2, 3);

var bindFunc1 = func.bind({ x: 1 });
bindFunc1(5, 6, 7);      // { x: 1 } 5 6 7

var bindFunc2 = func.bind({ x: 3 }, 4, 5);
bindFunc2(9);            // { x: 3 } 4 5 9 
```

bind 메서드를 적용해서 새로 만든 함수는 name 프로퍼티에 동사 bind의 수동태인 'bound'라는 접두어가 붙는다.
이를 통해 원본 메서드에 bind를 적용한 함수라는 것을 쉽게 추적할 수 있다.

apply, call, bind 등의 메서드를 활용하면 상위 컨텍스트의 this를 우회할 필요없이 바인딩 할 수 있다.
```javascript
var obj = {
    outer: function () {
        console.log(this);
        // call
        var innerFunc = function () {
            console.log(this);
        };
        innerFunc.call(this);

        // bind
        var innerFunc = function () {
            console.log(this);
        }.bind(this);
        innerFunc();
    }
}
obj.outer();
```
또한 콜백함수에도 bind 메서드를 사용해 특정 객체에 this를 바인딩 할 수 있다.

**arrow function**
ES6에서 새롭게 도입되었으며 실행 컨텍스트 생성 시 this를 바인딩 하는 과정 자체가 없다.
즉, 이 함수 내부에는 this가 아예 없으며, 접근하고자 하면 스코프체인상 가장 가까운 this에 
접근하게 된다.

**별도의 인자로 this를 받는 경우**
콜백 함수를 인자로 받는 메서드 중 일부는 추가로 this로 지정할 객체(thisArg)를
인자로 지정할 수 있는 경우가 있다. 이러한 메서드의 thisArg 값을 지정하면 콜백 함수 내부에서
this 값을 원하는 대로 변경할 수 있다.
이러한 형태는 여러 내부 요소에 대해 같은 동작을 반복 수행해야 하는 **배열 메서드**에 많이 있으며,
ES6에서 새로 등장한 Set, Map등의 메서드에도 일부 존재한다.

```javascript
var report = {
    sum: 0,
    count: 0,
    add: function () {
        var args = Array.prototype.slice.call(arguments);
        args.forEach(function (entry) {
            this.sum += entry;
            ++this.count;
        }, this);
    },
    average: function () {
        return this.sum / this.count;
    }
};
report.add(60, 85, 95);
console.log(report.sum, report.count, report.average());
```

이외에도 thisArg를 인자로 받는 메서드를 정리하면 다음과 같다.
```javascript
Array.prototype.forEach(callback[, thisArg])
Array.prototype.map(callback[, thisArg])
Array.prototype.filter(callback[, thisArg])
Array.prototype.some(callback[, thisArg])
Array.prototype.every(callback[, thisArg])
Array.prototype.find(callback[, thisArg])
Array.prototype.findIndex(callback[, thisArg])
Array.prototype.flatMap(callback[, thisArg])
Array.prototype.from(callback[, thisArg])
Set.prototype.forEach(callback[, thisArg])
Map.prototype.forEach(callback[, thisArg])
```