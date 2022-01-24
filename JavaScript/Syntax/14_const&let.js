// 14. const & let

// let
// let 예약어는 한번 선언하면 다시 선언할 수 없다.
let a = 10;
// let a = 20; // Uncaught SyntaxError: ...

// const
// const 예약어는 한번 할당한 값을 변경할 수 없다.
const b = 10;
// a = 20; // Uncaught TypeError: ...

// 단, 객체 {} 또는 배열 []로 선언했을 때는 객체의 속성(property)과
// 배열의 요소(element)를 변경할 수 있다.

// 객체로 선언하고 속성 값을 변경
const c = {
    num: 1000,
    name: "gaudi"
};
console.log(c.num);  // 1000

c.num = 20;
console.log(c.num);  // 20

c.age = 29;
console.log(c);

// 블록 유효범위
/*
    ES5의 var를 이용한 변수 선언 방식과 let&const를 이용한 변수 선언 방식의
    가장 큰 차이점은 블록 유효범위이다.
*/

// var의 유효 범위 - 함수의 블록 단위로 제한(함수 스코프)
var x = 100;
function print() {
    var x = 10;
    console.log(x);
}
print();  // 10

// for 반복문에서의 var 유효 범위
var x = 10;
for (var x = 0; x < 5; x++) {
    console.log(x);
}
console.log(x);  // 5


// const & let => 블록 스코프
// for 반복문에서의 let 유효 범위
let y = 10;
for (let y = 0; y < 5; y++) {
    console.log(y);
}
console.log(y);  // 10

// let은 변수의 유효 범위가 for 반복문의 {} 블록 안으로 제한된다.