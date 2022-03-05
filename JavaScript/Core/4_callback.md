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

위의 코드를 좀 더 보기좋게 수정해보면 다음과 같이 작성이 가능하다.
```javascript
var count = 0;
var cbFunc = function () {
    console.log(count);
    if (++count > 4) clearInterval(timer);
};
var timer = setInterval(cbFunc, 300);

// 실행 결과
// 0 (0.3초)
// 1 (0.6초)
// 2 (0.9초)
// 3 (1.2초)
// 4 (1.5초)
```
실행 결과를 통해 **코드 실행 방식**과 **제어권**에 대해 정리해보면
- `cbFunc`: 호출주체(사용자), 제어권(사용자)
- `setInterval(cbFunc, 300);`: 호출주체(setInterval), 제어권(setInterval)
콜백 함수의 제어권을 넘겨받은 코드가 콜백 함수 호출 시점에 대한 제어권을 가짐을 알 수 있다.

**인자**
map메서드를 통해 콜백함수의 인자가 어떻게 제어되는지 확인해보자.
- map메서드의 구조
```javascript
Array.prototype.map(callback[, thisArg])
callback: function(currentValue, index, array)
```
map메서드는 첫 번째 인자로 callback 함수를 받고, 생략 가능한 두 번째 인자로 콜백 함수
내부에서 this로 인식할 대상을 특정할 수 있다.
(thisArg를 생략할 경우 전역객체가 바인딩 - 일반적인 함수의 동작과 같다.)

map 메서드는 메서드의 대상이 되는 배열의 모든 요소를 하나씩 모두 꺼내어 콜백 함수를 반복 호출하고
콜백 함수의 실행 결과들을 모아 새로운 배열을 만든다.

콜백 함수의 첫 번째 인자에는 배열의 요소 중 **현재값**이, 두 번째 인자에는 현재값의 **인덱스**가,
세 번째 인자에는 map메서드의 대상이 되는 **배열 자체**가 담긴다.

```javascript
var newArr = [10, 20, 30].map(function (currentValue, index) {
    console.log(currentValue, index);
    return currentValue + 5;
});
console.log(newArr);
// -- 실행 결과 --
// 10 0
// 20 1
// 30 2
// [15, 25, 35]
```
위의 예제를 통해 callback 함수의 첫 번째와 두 번째 매개변수로 각 배열의 순회결과와 인덱스가 담기는 것을
확인할 수 있다.

이는 제이쿼리에서의 메서드에 첫 번째 인자로 인덱스를, 두 번째 인자로 currentValue가 오는것과 다르게 작동
하는 것을 확인할 수 있는데 제이쿼리에서처럼 인자의 순서를 바꿔서 출력해보면 다음과 같은 결과를 얻는다.
```javascript
var newArr = [10, 20, 30].map(function (index, currentValue) {
    console.log(currentValue, index);
    return currentValue + 5;
});
console.log(newArr);
// -- 실행 결과 --
// 0 10
// 1 20
// 2 30
// [5, 6, 7]
```
실행 결과를 통해 **콜백함수의 인자로 이름과 상관없이 각 순서에 정해진 값이 전달되는 사실**을 확인할 수 있다.

띠라서, map 메서드를 호출해서 원하는 배열을 얻으려면 map메서드에 정의된 규칙에 따라 함수를 작성해야 한다.
map메서드에 정의된 규칙에는 콜백 함수의 인자로 넘어올 값들 및 그 순서도 포함되어 있다.

즉, **콜백 함수를 호출하는 주체가 사용자가 아닌 map메서드이므로 map메서드가 콜백 함수를 호출할 때 인자에 어떤 값들을**
**어떤 순서로 넘길 것인지가 전적으로 map메서드에 달린 것이다.**

**this**
콜백함수도 함수이기 때문에 기본적으로 this가 전역객체를 참조하지만, 제어권을 넘겨받을 코드에서 콜백 함수에 별도로 this
가 될 대상을 지정한 경우에는 그 대상을 참조하게 된다.

map메서드의 기능을 구현해 별도의 this를 지정하는 방식에 대해 파악해보자.
```javascript
Array.prototype.map = function (callback, thisArg) {
    var mappedArr = [];
    for (var i = 0; i < this.length; i++) {
        var mappedValue = callback.call(thisArg || window, this[i], i, this);
        mappedArr[i] = mappedValue;
    }
    return mappedArr;
};
```
메서드 구현의 핵심은 call메서드에 있다. this에는 thisArg값이 존재하는 경우 그 값을 바인딩하며
없을 경우 전역객체(window)를 바인딩한다. 첫 번째 인자에는 메서드의 this가 배열을 가리킬 것이므로 배열의
i 번째 요소 값을, 두 번째 인자에는 index 값을, 세 번째 인자에는 배열 자체를 지정해 호출한다.

이를 통해 **this에 다른값이 담기는 이유**를 정확히 알 수 있다. **제어권을 넘겨받을 코드에서 call/apply 메서드의**
**첫 번째 인자에 콜백 함수 내부에서의 this가 될 대상을 명시적으로 바인딩**하기 때문이다.

### 콜백 함수 -> 함수
콜백 함수로 어떤 객체의 메서드를 전달하더라도 그 메서드는 메서드가 아닌 **함수로서 호출**된다.
- 예제(콜백 함수로 메서드를 전달하는 경우)
```javascript
var obj = {
    vals: [1, 2, 3],
    logValues: function(v, i) {
        console.log(this, v, i);
    }
};
obj.logValues(1, 2);                // { vals: [1, 2, 3], logValues: f } 1 2
[4, 5, 6].forEach(obj.logValues);   // Window { ... } 4 0
// Window 5 1
// Window 6 2
```
예시를 보면 forEach 함수의 콜백 함수로 logValues 메서드를 전달했다.
이때, obj를 this로 하는 메서드를 그대로 전달한 것이 아니라 **obj.logValues가 가리키는 함수**를
전달한 것이다. 따라서, 이 함수는 메서드로서 호출할 때가 아닌 한 obj와의 직접적인 연관이 없어진다.
forEach에 의해 콜백이 함수로서 호출되고, 별도로 this를 지정하는 인자를 지정하지 않았으므로 함수 내부에서의
this는 전역객체를 바라보게 된다.

결론은 **어떤 함수의 인자에 객체의 메서드를 전달하더라도 이는 결국 메서드가 아닌 함수일 뿐이다.**

### 콜백 함수 내부의 this에 다른 값 바인딩하기
콜백 함수 내부의 this에 다른 값을 바인딩하고자 할때 별도의 인자로 this를 받는 함수의 경우에는
여기에 원하는 값을 넘겨주면 된다. 하지만 그렇지 않은 경우에는 this의 제어권도 넘겨주게 되므로
사용자가 임의로 값을 바꿀수 없게 된다.

이를 해결하기 위해 이전에는 this를 다른 변수에 담아 콜백함수로 활용할 함수에서는 this 대신 그 변수를
사용하게 하고, 이를 클로저로 만드는 방식이 많이 쓰였다.

하지만, 기존 방식은 코드를 재사용하기 어려울뿐더러 코드의 복잡성을 증가시키기 때문에 아쉬운 부분이있다.
이를 보완하기 위해 ES5에서 소개된 bind 메서드(선언과 호출이 분리)를 이용한다.

```javascript
var obj1 = {
    name: 'obj1',
    func: function () {
        console.log(this.name);
    }
};
setTimeout(obj1.func.bind(obj1), 1000);

var obj2 = { name: 'obj2' };
setTimeout(obj1.func.bind(obj2), 1500);
```

### 콜백 지옥과 비동기 제어
콜백 지옥(callback hell)은 콜백 함수를 익명 함수로 전달하는 과정이 반복되어 코드의 들여쓰기 수준이
감당하기 힘들 정도로 깊어지는 현상으로, 자바스크립트에서 흔히 발생하는 문제이다. 주로 이벤트 처리나 서버 통신과
같이 비동기적인 작업을 수행하기 위해 이런 형태가 자주 등장하곤 하는데, 가독성이 떨어질분더러 코드를 수정하기도 어렵다.

**비동기 VS 동기**
비동기(asynchronous)는 동기(synchronous)의 반댓말이다. 
- 동기적인 코드: 현재 실행 중인 코드가 완료된 후에야 다음 코드를 실행하는 방식, **CPU의 계산에 의해 처리가 가능한 대부분의 코드**
- 비동기적인 코드: 현재 실행 중인 코드의 완료 여부와 무관하게 즉시 다음 코드로 넘어가는 방식, **특정시간의 소요, 사용자의 개입 등이 필요한 코드(외부의존)**

**대표적인 콜백 지옥 예제**
```javascript
setTimeout(function (name) {
    var coffeeList = name;
    console.log(coffeeList);

    setTimeout(function (name) {
        coffeeList += ', ' + name;
        console.log(coffeeList);

        setTimeout(function (name) {
            coffeeList += ', ' + name;
            console.log(coffeeList);

            setTimeout(function (name) {
                coffeeList += ', ' + name;
                console.log(coffeeList);
            }, 500, '카페라떼');
        }, 500, '카페모카');
    }, 500, '아메리카노');
}, 500, '에스프레소');
```
=> 값이 전달되는 순서가 '아래에서 위로'향하고 있어 어색하고 가독성이 좋지 않다.

이를 해결하기 위한 방법으로
- 기명함수 전환
- ES6에서 등장한 Promise, Generator등의 사용
- ES2017에서 등장한 **async, await** 사용

**Promise + Async/await** 사용 예제
```javascript
var addCoffee = function (name) {
    return new Promise(function (resolve) {
        setTimeout(function () {
            resolve(name);
        }, 500);
    });
};
var coffeeMaker = async function () {
    var coffeeList = '';
    var _addCoffee = async function (name) {
        coffeeList += (coffeeList ? ', ' : '') + await addCoffee(name);
    };
    await _addCoffee('에스프레소');
    console.log(coffeeList);
    await _addCoffee('아메리카노');
    console.log(coffeeList);
    await _addCoffee('카페모카');
    console.log(coffeeList);
    await _addCoffee('카페라떼');
    console.log(coffeeList);
};
coffeeMaker();
```