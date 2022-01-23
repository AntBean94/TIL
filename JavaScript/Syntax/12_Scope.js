// 12. 스코프(Scope)

// 스코프란 변수의 유효 범위를 의미한다.

// 글로벌 스코프
/*
    자바스크립트의 변수는 유효 범위가 전역으로 시작한다.
    예를 들어 아래와 같은 변수 선언은 자바스크립트로 접근할 수 있는
    모든 영역에서 같은 값을 가진다.
*/
var a = 10;

function getA() {
    console.log(a);
}

getA();

// 로컬 스코프
/*
    기본적으로 변수의 유효 범위는 전역 범위를 갖는다고 하지만, 함수 안에서
    새로 선언하는 경우 함수 단위의 지역 범위인 함수 스코프를 갖는다.
*/
var a = 10;

function getA() {
    var a = 20;
    console.log(a);
}

getA();  // 20
console.log(a);  // 10

// 스코프 체인
// 변수를 찾을 때 먼저 자신이 속한 스코프에서 찾고 없으면 상위 스코프에서 찾는
// 현상을 의미한다.

// 예제 1
// 글로벌 스코프
var a = 10;

function getA() {
    // 로컬 스코프
    console.log(a);
}

getA();  // 10

// 예제 2
// 글로벌 스코프
var a = 10;

function outer() {
    // 외부 함수 스코프
    var b = 20;

    function inner() {
        // 로컬 함수 스코프
        var c = 30;
        console.log(a);
        console.log(b);
        console.log(c);
    }
    inner();
};
outer();

// 결과
// 10
// 20
// 30


// 렉시컬 스코프
/*
    자바스크립트는 함수를 어디서 "선언" 하였는지에 따라서 상위 스코프를 결정하는
    렉시컬 스코프(Lexical Scope) 규칙을 따른다.
*/

// 글로벌 스코프
var a = 10;
var b = 20;
function getA() {
    var b = a;
    getB();
}

function getB() {
    // 로컬 함수 스코프
    console.log(b);
}

getB();  // 20
getA();  // 20

// getB가 전역 범위에 선언되었기 때문에 호출위치와 상관없이 상위 스코프로 갖는
// 글로벌 스코프와 자기 자신인 로컬 함수 스코프를 최종 스코프로 가지게 되어서
// 둘다 20을 출력한다.

// 이와 반대되는 개념이 다이나믹 스코프(Dynamic Scope)