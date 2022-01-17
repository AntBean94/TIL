// 7. 반복문(Loop)

// 반복문은 특정 로직을 반복할 때 사용하는 문법이다.
for (var i = 0; i < 10; i++) {
    console.log(i);
};

// for Loop의 문법
/*

for (반복할 변수 초기화, 반복 조건, 반복할 변수의 증감식) {
    // 반복할 연산
};

*/

// 자바스크립트에서는 최적의 성능 튜닝을 위해 고의로 for문을 사용하는 경우를 제외하고
// 다음과 같은 반복문을 주고 사용한다.
/*
    forEach 반복문
    for in 반복문
    for of 반복문
*/

// forEach()
var arr = [10, 20, 30];
arr.forEach((value, index) => {
    console.log('array index : ' + index + ' value : ' + value);
});

// 결과
// array index : 0 value : 10
// array index : 1 value : 20
// array index : 2 value : 30

// for in
// 배열과 객체 모두에 사용이 가능하다.
var arr = [10, 20, 30];
var obj = {
    num: 10,
    str: 'hi',
    arr: [],
};

// 배열의 인덱스를 1개씩 접근하여 순차적으로 순회
for (var key in arr) {
    console.log(arr[key]);
};

// 객체의 키를 1개씩 접근하여 순차적으로 순회
for (var key in obj) {
    console.log(obj[key]);
};

// 배열 반복문의 콘솔
// 10
// 20
// 30
// 객체 반복문의 콘솔
// 10
// hi
// []

// for of
// for of 반복문은 ES6에 추가된 구문으로, 반복 가능한(iterable) 속성을
// 가지는 컬렉션에 사용하기 좋은 반복문이다.

// 다음의 두 조건을 만족합니다.
// 1. 객체가 [Symbol.iterator] 메서드를 가지고 있어야 한다.
// 2. [Symbol.iterator] 메서드는 iterator 객체를 반환해야 합니다.

// 반복 가능하지 않은 객체(obj)에 대해서는 아래와 같은 에러를 불러 일으킨다.
var obj = {
    num: 10,
    str: 'hi',
    arr: [],
};

// for (var prop of obj) {
//     console.log(prop, obj[prop]);
// };

// 결과
// TypeError: obj is not iterable

// for of vs for in
var arr = [10, 20, 30]

// 배열을 순회
for (var num of arr) {
    console.log(num);
};

for (var num in arr) {
    console.log(num);
};

// 결과는 각각 아래와 같다.
// 10 20 30 
// 0 1 2

// for in은 배열의 인덱스에 접근하는 반면
// for of는 값 자체에 접근하는 것을 알 수 있다.