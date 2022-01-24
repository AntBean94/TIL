// 15. 화살표 함수(Arrow Function)

// 기존의 함수 정의 방식
var x = function() {
    // ...
};

// 화살표 함수를 이용한 함수 정의
var x = () => {
    // ...
};

// 화살표 함수의 다양한 문법
/*
    화살표 함수를 정의하는 방식은 간단한 표현식부터
    {}를 이용한 선언 방식까지 여러 방법이 존재
*/

// 1) 단순 자바스크립트 표현식
() => 10 + 20;  // {} 필요 없음

// 2) 함수 선언 방식(복잡한 선언문이 들어갈 경우)
() => {
    print();
    log();
    return 10 + 20;
};

// 3) 전달 인자(parameter)가 하나인 경우
const a = num => {
    return num * 10;
};
const b = num => num * 10;
a(10);  // 100
b(10);  // 100

/*  this 바인딩의 변화
    화살표 함수에서 사용되는 this바인딩은 기존 함수의
    this와 다른 방식을 가지고 있다.
    화살표 함수의 this는 함수를 선언할 때의 상위 스코프의
    this로 바인딩 될 객체가 정해진다.
*/

const foo = {
    name: "gaudi",
    age: 29,
    printThis() {
        console.log(this);  // {name: "gaudi", age: 29, ...}
        const innerFunc = () => {
            return this;  // {name: "gaudi", age: 29, ...}
        };
        console.log(innerFunc());
    }
};

foo.printThis();
// innerFunc를 일반함수로 선언했으면 전역객체 출력

// setTimeout에서의 this
// 화살표 함수에서는 상위 스코프의 this로 값이 바인딩
const gwak = {
    name: "gaudi",
    age: 29,
    getThisArrowFunc: function() {
        console.log(this);
        setTimeout(() => {
            console.log(this);  // {name: "gaudi", ...}
        }, 1000);
    },

    getThisFunc: function() {
        console.log(this);
        setTimeout(function() {
            console.log(this); // window
        }, 1000);
    }
};

gwak.getThisArrowFunc();
gwak.getThisFunc();

// 그러나 엄격모드('use strict')일때는 바인딩 되지 않는다.
"use strict";
(function() {
    setTimeout(() => {
        console.log(this);  // window
    }, 1000);
})();

// addEventListener에서의 this
// addEventListener 두 번째 인자로 화살표 함수를 넣으면
// this는 상위 스코프의 this를 가리킨다.
const button = document.getElementById("this-button");
button.innerText = "함수 호출 버튼";

button.addEventListener("click", () => {
    console.log(this === window); // true
    console.log(this === innerText);  // undefined
})

// 따라서 addEventListener 함수에서 this를 사용하는 경우, 화살표 함수가 아닌
// 일반 함수를 사용한다.

// 주의!!!! 일반 함수의 this와 다르게 화살표 함수에서의 this는 
// call, bind, apply 메소드로 this에 바인딩 된 값을 변경할 수 없다.
this.foo = "bar";
const normalFunc = function() {
    return this.foo;
};
const arrowFunc = () => this.foo;

console.log(nomalFunc.call({ foo: "baz" })); // baz
console.log(arrowFunc.call({ foo: "baz" })); // bar

// 화살표 함수는 생성자 함수로 사용 불가능하다.
const Foo = () => {};
const foo = new Foo();  // Uncaught TypeError: Foo is not a constructor