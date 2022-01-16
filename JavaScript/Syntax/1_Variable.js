// 1. 변수 Variable

/* 
    변수에 값 할당하기
*/
var a
console.log(a) // undefined

var a
a = 10 // 타입을 지정해줄 필요 없음 => 자동 결정
console.log(a) // 10

a = 'hi'
console.log(a) // 'hi'

a = false
console.log(a) // false

// 위와 같이 유연하게 코드의 값을 바꿀 수 있다.


/*
    변수의 타입 확인하기 - typeof
*/
console.log(typeof a)