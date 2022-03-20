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
**콜백 함수와 클로저(1)**
```javascript
// --- 모든 예제 공통 코드 ---
var fruits = ['banana', 'apple', 'peach'];
var $ul = document.createElement('ul');
// -----------------------

fruits.forEach(function (fruit) {                   // (A)
    var $li = document.createElement('li');
    $li.innerText = fruit;
    $li.addEventListener('click', function () {     // (B)
        alert('your choice is ' + fruit);
    });
    $ul.appendChild($li);
});
document.body.appendChild($ul);
```
콜백 함수 (B)에는 fruit이라는 외부 변수를 참조하고 있으므로 클로저가 존재한다. (A)의 종료 여부와 부관하게 클릭 이벤트에 의해 각 컨텍스트의 (B)가 실행될 때는 (B)의 outerEnvironmentReference가 (A)의 LexicalEnvironment를 참조하므로 fruit에 대해서는 (A)가 종료된 후에도 GC대상에서 제외되어 계속 참조가 가능하다.

**콜백 함수와 클로저(2)**
```javascript
var alertFruit = function (fruit) {
    alert('your choice is' + fruit);
};
fruits.forEach(function (fruit) {
    var $li = document.createElement('li');
    $li.innerText = fruit;
    $li.addEventListener('click', alertFruit);
    $ul.appendChild($li);
});
document.body.appendChild($ul);

alertFruit(fruits[1]);
```
`alertFruit(fruits[1])`에서는 정상적으로 'apple'에 대한 알럿이 실행되는것에 반해 각 `li`를 클릭하면 대상의 과일명이 아닌 [object MouseEvent]라는 값이 출력된다. 이는 콜백 함수의 인자에 대한 제어권을 addEventListener가 가지기 때문이며, addEventListener는 콜백 함수를 호출할 때 첫 번째 인자에 '이벤트 객체'를 주입하기 때문이다. 이는 bind메서드를 활용하면 해결할 수 있다.

**콜백 함수와 클로저(3)**
```javascript
fruits.forEach(function (fruit) {
    var $li = document.createElement('li');
    $li.innerText = fruit;
    $li.addEventListener('click', alertFruit.bind(null, fruit));
    $ul.appendChild($li);
});
```
예제와 같이 bind 메서드를 사용해서 위의 문제를 해결할 수 있지만 이벤트 객체가 인자로 넘어오는 순서가 바뀌는 점과 함수 내부에서의 this가 원래의 그것과 달라지는 점을 감안해야 한다.(?)
이런 변경사항이 발생하지 않게 하려면 '고차함수'를 활용하는 방법이 있으며 함수형 프로그래밍에서 자주 사용하는 방식이다.

**콜백 함수와 클로저(4)**
```javascript
var alertFruitBuilder = function (fruit) {
    return function () {
        alert('your choice is ' + fruit);
    };
};
fruits.forEach(function (fruit) {
    var $li = document.createElement('li');
    $li.innerText = fruit;
    $li.addEventListener('click', alertFruitBuilder(fruit));
    $ul.appendChild($li);
});
```
- **고차함수**: 함수를 인자로 받거나 함수를 반환하는 함수
`alertFruitBuilder` 함수는 fruit를 인자로 받고 alert함수를 반환하는 '고차함수'이다. 반환된 함수는 외부함수인 `alertFruitBuilder`함수의 fruit 인자를 참조하고 있는 '클로저'이고 별도의 매개변수를 받지 않으므로 `addEventListener`의 콜백함수로 사용될때 이벤트 객체를 받는 인자의 순서도 바뀌지 않으므로 이전 예제의 문제를 해결할 수 있다.

위의 세가지 예제를 통해 각 상황에 맞는 방법을 적용하면 된다.

#### 2.접근 권한 제어(정보 은닉)
정보 은닉(information hiding)은 어떤 모듈의 내부 로직에 대해 외부로의 노출을 최소화해서 모듈간의 결합도를 낮추고 유연성을 높이고자 하는 현대 프로그래밍 언어의 중요한 개념 중 하나이다. 흔히 접근 권한에는 public, private, protected의 세 종류가 있는데, 각 단어의 의미 그대로 public은 외부에서 접근 가능한 것이고 private은 내부에서만 사용하며 외부에 노출하지 않는 것을 의미한다.

자바스크립트에서는 기본적으로 변수 자체에 접근 권한을 직접 부여하도록 설계돼 있지 않지만, 클로저를 이용해 함수 차원에서 public한 갑과 private한 값을 구분하는 것이 가능하다.

방법은 함수의 `return`을 활용하는 것인데, **공개(public)멤버는 return하고 비공개(private)멤버는 return하지 않음으로써 외부에서의 접근을 막을 수 있다.**

간단한 예시로 '자동차 게임'을 들 수 있다.
```javascript
var createCar = function () {
    var fuel = Math.ceil(Math.random() * 10 + 10);
    var power = Math.ceil(Math.random() * 3 + 2);
    var moved = 0;
    return {
        get moved () {
            return moved;
        },
        run: function () {
            var km = Math.ceil(Math.ramdom() * 6);
            var wasteFuel = km / power;
            if (fuel < wasteFuel) {
                console.log('이동불가');
                return;
            }
            fuel -= wasteFuel;
            moved += km;
            console.log(km + 'km 이동 (총 ' + moved + 'km). 남은 연료: ' + fuel);
        }
    };
};
var car = createCar();
```
fuel, power 변수는 비공개 멤버로 지정해 외부에서의 접근을 제한했고(자동차 게임의 공정성 확립을 위해) moved 변수는 getter만을 부여함으로써 읽기 전용 속성을 부여했다. 이렇게 함으로써 외부에서는 오직 run메서드를 실행하는 것과 현재의 moved값을 확인하는 두 가지 동작만 할 수 있다.

추가로 메서드를 덮어 씌우는 어뷰징을 막기 위해서는 `Object.feeze()`메서드를 활용해 객체를 동결하고 return하면 된다.

정리하자면 **클로저를 활용해 접근권한을 제어하는 방법**은
1. 함수에서 지역변수 및 내부함수를 생성한다.
2. 외부에 접근권한을 주고자 하는 대상들로 구성된 참조형 데이터(대상이 여럿일 때는 객체 또는 배열, 하나일 때는 함수)를 return한다.
    -> return한 변수들은 공개 멤버가 되고, 그렇지 않은 변수들은 비공개 멤버가 된다.

#### 3.부분 적용 함수
부분 적용 함수(partially applied funtion)란 n개의 인자를 받는 함수에 미리 m개의 인자만 넘겨 기억시켰다가, 나중에 (n-m)개의 인자를 넘기면 비로소 원래 함수의 실행 결과를 얻을 수 있게끔 하는 함수이다. this를 바인딩해야 하는 점을 제외하면 앞서 살펴본 bind메서드의 실행결과가 부분 적용 함수이다.
```javascript
var add = function () {
    var result = 0;
    for (var i = 0; i < arguments.length; i++) {
        result += arguments[i];
    }
    return result;
};
var addPartial = add.bind(null, 1, 2, 3, 4, 5);
console.log(addPartial(6, 7, 8, 9, 10));    // 55
```
bind를 사용하면 부분적용함수를 쉽게 구현할 수 있지만 this에 변경이 생기기때문에 메서드에는 사용할 수 없다.
```javascript
var dog = {
    name: '사모예드',
    greet: function (prefix, suffix) {
        return prefix + this.name + suffix;
    }
}

console.log(dog.greet('왈왈, ', '입니다.'))     // 왈왈, 사모예드입니다.
var partialGreet = dog.greet.bind(this, '왈왈, ');     // this => global or window (bind로 부분함수를 선언하는 시점의 this로 바뀜)
console.log(partialGreet('입니다.'))           // 왈왈, undefined입니다.

var dog = {
    name: '사모예드',
    greet: function () {
        var inner = function (prefix, suffix) {
            return prefix + this.name + suffix
        }.bind(this, '왈왈, ')
        return inner
    }
}

console.log(dog.greet()('입니다.'))     //  왈왈, 사모예드입니다. (bind로 구현은 가능하다, 재활용이 안됨)
```
따라서 this에 관여하지 않는 별도의 부분 적용함수를 구현하면 아래와 같다.

**this에 관여하지 않는 부분적용함수 구현**
```javascript
var partial = function () {
    var originalPartialArgs = arguments;
    var func = originalPartialArgs[0];
    if (typeof func !== 'function') {
        throw new Error('첫 번째 인자가 함수가 아닙니다!');
    }
    return function () {
        var partialArgs = Array.prototype.slice.call(originalPartialArgs, 1);
        var restArgs = Array.prototype.slice.call(arguments);
        return func.apply(this, partialArgs.concat(restArgs));
    }
}

var add = function () {
    var result = 0;
    for (var i = 0; i < arguments.length; i++) {
        result += arguments[i];
    }
    return result;
};

var addPartial = partial(add, 1, 2, 3, 4, 5);
console.log(addPartial(6, 7, 8, 9, 10));        // 55

var dog = {
    name: '강아지',
    greet: partial(function(prefix, suffix) {
        return prefix + this.name + suffix;
    }, '왈왈, ')
};
dog.greet('입니다.');       // 왈왈, 강아지입니다.
```

**this에 관여하지 않는 부분함수 구현(2) - 부분인자를 원하는 위치에 넣도록 구현**
```javascript
Object.defineProperty(window, '_', {
    value: 'EMPTY_SPACE',
    writable: false,
    configurable: false,
    enumerable: false
});

var partial2 = function () {
    var originalPartialArgs = arguments;
    var func = originalPartialArgs[0];
    if (typeof func !== 'function') {
        throw new Error('첫 번째 인자가 함수가 아닙니다.');
    }
    return function () {
        var partialArgs = Array.prototype.slice.call(originalPartialArgs, 1);
        var restArgs = Array.prototype.slice.call(arguments);
        for (var i = 0; i < partialArgs.length; i++) {
            if (partialArgs[i] === _) {
                partialArgs[i] = restArgs.shift();  // 가장 앞에있는 원소를 뽑아서 반환
            }
        }
        return func.apply(this, partialArgs.concat(restArgs));
    };
};

var add = function () {
    var result = 0;
    for (var i = 0; i < arguments.length; i++) {
        result += arguments[i];
    }
    return result;
};
var addPartial = partial2(add, 1, 2, _, 4, 5, _, _, 8, 9);
console.log(addPartial(3, 6, 7, 10));   // 55   

var dog = {
    name: '강아지',
    greet: partial2(function(prefix, suffix) {
        return prefix + this.name + suffix;
    }, '왈왈, ')
};
dog.greet(' 배고파요!');
```
예시(ES5)에서는 '_'를 '비워놓음'으로 사용하기 위해 전역공간을 침범했지만, ES6에서는 Symbol.for를 활용할 수 있다.

**Symbol.for** 메서드는 전역 심볼공간에 인자로 넘어온 문자열이 이미 있으면 해당 값을 참조하고, 선언돼 있지 않으면 새로 만드는 방식으로, 어디서든 접근 가능하면서 유일무이한 상수를 만들고자 할 때 적합하다.
**예시**
```javascript
var partial3 = function () {
    // ...생략...
    return function () {
        // ...생략...
        for(var i = 0; i < partialArgs.length; i++) {
            if (partialArgs[i] === Symbol.for('EMPTY_SPACE')) {
                partialArgs[i] = restArgs.shift();
            }
        }
        return func.apply(this, partialArgs.concat(restArgs))
    }
}

var _ = Symbol.for('EMPTY_SPACE');
var addPartial = partial3(add, 1, 2, _, 4, 5, _)
console.log(...)
```

**디바운스(debounce)**: 짧은 시간동안 동일한 이벤트가 많이 발생할 경우 이를 전부 처리하지 않고 처음 또는 마지막에 발생한 이벤트에 대해 한 번만 처리하는 것
(프런트엔드 성능 최적화에 큰 도움을 주는 기능으로 scroll, wheel, mousemove, resize 등에 적용하기 좋다.) 
```javascript
var debounce = function (eventName, func, wait) {
    var timeoutId = null;
    return function (event) {
        var self = this;
        console.log(eventName, 'event 발생');
        clearTimeout(timeoutId);
        timeoutId = setTimeout(func.bind(self, event), wait);
    };
};

var moveHandler = function (e) {
    console.log('move event 처리');
};
var wheelHandler = function (e) {
    console.log('wheel event 처리');
};
document.body.addEventListener('mousemove', debounce('move', moveHandler, 500));
document.body.addEventListener('mousewheel', debounce('wheel', wheelHandler, 700));
```

#### 4.커링 함수
커링 함수(currying function)란 여러 개의 인자를 받는 함수를 하나의 인자만 받는 함수로 나눠서 순차적으로 호출될 수 있게 체인 형태로 구성한 것을 말한다.

부분적용함수와 기본적인 맥락은 일치하지만 몇 가지 차이점이 존재한다.
- 커링은 한 번에 하나의 인자만 전달하는 것을 원칙으로 한다.(부분적용함수는 여러 개의 인자를 전달할 수 있다.)
- 중간 과정상의 함수를 실행한 결과는 그다음 인자를 받기 위해 대기만 할 뿐으로, 마지막 인자가 전달되기 전까지는 원본 함수가 실행되지 않는다.(부분적용함수는 실행결과를 재실행할 때 원본 함수가 무조건 실행된다.)

예제
```javascript
var curry = function (func) {
    return function (a) {
        return function (b) {
            return func(a, b);
        };
    };
};

var getMaxWith10 = curry(Math.max)(10);
console.log(getMaxWith10(8));   // 10
console.log(getMaxWith10(25));  // 25

var getMinWith10 = curry(Math.min)(10);
console.log(getMinWith10(8));   // 8
console.log(getMinWith10(15));  // 10
```
아래와 같이 필요한 인자 개수만큼 함수를 만들어 계속 리턴해주도록 만들수도 있다.
```javascript
var curry2 = function (func) {
    return function (a) {
        return function (b) {
            return function (c) {
                return function (d) {
                    return function (e) {
                        return func(a, b, c, d, e);
                    };
                };
            };
        };
    };
};
var getMax = curry2(Math.max);
console.log(getMax(1)(2)(3)(4)(5));     // 5
```
단, 위와같이 작성하면 가독성이 떨어지고 코드의양이 증가하므로 화살표함수를 사용해서 작성하면 더 간결하게 표현할 수 있다.
```javascript
var curry3 = func => a => b => c => d => e => func(a, b, c, d, e);
```

당장 필요한 정보만 받아서 전달하고 또 필요한 정보가 들어오면 전달하는 이러한 방식을 함수형 프로그래밍에서는 '지연실행'(lazy excution)이라고 한다.
함수를 원하는 시점까지 지연시켰다가 실행하는 것이 필요한 상황에서 요긴하게 쓰일 수 있으며, 프로젝트에서 자주 쓰이는 함수의 매개변수가 항상 비슷하고 일부만 바뀌는 경우에도 유용하게 쓰일 수 있다.
```javascript
var getInformation = function (baseUrl) {   // 서버에 요청할 주소의 기본 Url
    return function (path) {                // path 값
        return function (id) {              // id 값
            return fetch(baseUrl + path + '/' + id);    // 실제 서버에 정보를 요청
        };
    };
};
// ES6
var getInformation2 = baseUrl => path => id => fetch(baseUrl + path + '/' + id);
```

다음 예시는 Flux 아키텍처의 구현체 중 하나인 Redux의 미들웨어(middleware)에 쓰인 커링 함수이다.
```javascript
// Redux Middleware 'Logger'
const logger = store => next => action => {
    console.log('dispatching', action);
    console.log('next stage', store.getState());
    return next(action);
};
// Redux Middleware 'thunk'
const thunk = store => next => action => {
    return typeof action === 'function'
        ? action(dispatch, store.getState)
        : next(action);
};
```