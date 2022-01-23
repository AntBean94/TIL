// 13. 클로져(Closure)

/*
    클로져는 함수의 실행이 끝난 뒤에도 함수에 선언된 변수의 값을 접근할 수 있는
    자바스크립트의 성질이다.
    다른 언어와 구별되는 유일한 특징이다.
*/

function addCounter() {
    var counter = 0;

    return function() {
        return counter++;
    };
}

// 위 코드는 addCounter라는 함수를 하나 생성하고 counter 변수를 선언한 코드이다.
// counter 변수는 함수 안에서만 유효한 범위를 가지게 된다.

function addCounter() {
    var counter = 0;
}

addCounter();
// console.log(counter);   // Uncaught ReferenceError: ...

// 다시 클로져 코드를 확인해보면

function addCounter() {
    var counter = 0;

    return function() {
        return counter++;
    };
}

// 위의 코드에서 함수를 변수나 인자로 넘길 수 있는 이유는 자바스크립트에서
// 함수는 일급 객체이기 때문이다.

addCounter();

console.log(addCounter());
// console.log(counter);    // Uncaught ReferenceError: ...

var add = addCounter();
add(); // 1
add(); // 2
add(); // 3

// 위와 같이 코드를 실행했을 때 동작하는 이유는 
// addCounter()라는 함수가 반환한 함수를 add라는 변수에 담아놨기 때문에
// add 변수 자체가 함수처럼 동작하는 것이다.
// 정확한 표현으론 "add 변수가 addCounter()가 반환한 함수를 참조하고 있다."

// 이처럼 함수의 실행이 끝나고 나서도 함수 안의 변수를 참조할 수 있는게
// 클로져 이다.
// 이러한 패턴을 응용하면 private 변수를 만들거나 함수형 프로그래밍을 할 수 있다.


// private 변수
// 일반적으로 프로그래밍에서 외부에서 사용하지 않거나 접근하면 안되는 변수와
// 함수는 Private로 선언하여 사용한다.
// 자바스크립트에서는 Closure를 응용해 private 변수를 구현하고 방법은 다음과 같다.

var fund = (function() {
    var money = 0;
    return {
        deposit: function(amount) {
            money += amount;
        },
        withdraw: function(amount) {
            money -= amount;
        },
        getMoney: function() {
            return money;
        }
    }
}());

fund.deposit(100);  // 100
fund.deposit(100);  // 200
console.log(fund.getMoney());    // 200
fund.money = 100000;  // private 변수로 변경되지 않는다.
console.log(fund.getMoney());    // 200

// 위 코드에서 호출된 함수 내부의 money 변수는 함수 내에서 제공한 
// deposit, withdraw, getMoney 를 사용하는 것 외에는 접근하는
// 방법이 없다.

// 이렇게 클로져를 활용하면 외부에서 변수에 직접 접근하는 것을 제한하는
// private 변수를 구현할 수 있다.

// 2021년 Class에서 private 필드와 메서드를 지원하는 새로운 표준이 추가되었다.
// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes/Private_class_fields


// 함수형 프로그래밍
/*
    함수형 프로그래밍이란 특정 기능을 구현하기 위해서 함수의 내부 로직은 변경하지
    않은 상태로 여러 개의 함수를 조합하여 결과 값을 도출하는 프로그래밍 패턴을
    의미한다.
    커링(currying)이 함수형 프로그래밍의 대표적인 예로 아래의 코드를 확인해보자.
*/
function add(num1, num2) {
    return num1 + num2;
}

function minus(num1, num2) {
    return num1 - num2;
}

function curry(fn, a) {
    return function(b) {
        return fn(a, b);
    };
}

var add3 = curry(add, 3);
console.log(add3(4));  // 7
var min3 = curry(minus, 5);
console.log(min3(2));  // 3