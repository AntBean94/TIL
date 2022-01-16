// 2. 문자열 String

/*
    문자열은 변수의 여러 타입 중 하나로서 일반적으로 스트링
    이라고 부르며 아래와 같이 선언한다.
    
    작은 따옴표('') 또는 큰 따옴표("")를 이용해 정의한다.
*/
var a = 'hello' // a라는 변수에 hello라는 문자열을 할당한 코드


// 숫자와의 구분
// 자바스크립트는 코드를 실행하는 시점에 변수의 타입을 결정 => 타입을 헷갈리게 하는 경우 발생
var a = 10;
var b = '10';
console.log(a); // 10 - 숫자
console.log(b); // 10 - 문자

// typeof 외에도 아래와 같은 방법으로 확인 가능
console.log(a.length); // undefined
console.log(b.length); // 2

// length는 자바스크립트 예약어로 문자열, 배열의 길이를 확인할 수 있다.