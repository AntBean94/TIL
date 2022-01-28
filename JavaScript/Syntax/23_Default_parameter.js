// 23. 기본값 매개변수(Default parameter)

// 구문
/*
    function functionName(param1 = defaultValue1, ..., paramN = defaultValueN) {
        // ...
    }
*/
// 예시
function foo(param1 = 1, param2 = {}, param3 = 'korean') {
    console.log(param1, param2, param3);
};

foo(30, { name: 'amy' }, 'american'); // 30, { name: 'amy' }, 'american'
foo();  // 1, {}, 'korean'

// 설명
/*
    ES6 이전 문법에서는 or 연산자인 ||를 이용하여 입력받은 파라미터를 
    지역변수에 재정의 하는 방식을 사용했다. or 연산자의 특성으로 인하여 
    파라미터의 값이 falsy value(false 로 평가되는 값, false, null, 
    0, undefined...)인 경우에 || 연산자 우항의 값을 기본값으로 사용한다.
*/

// ||를 이용한 기본값 할당 방식
function printPersonInfo(height, weight, age) {
    var height = height || 180;
    var weight = weight || 60;
    var age = age || 66;

    console.log(height, weight, age);
};

printPersonInfo(10, 200, 300); // 10, 200, 300
printPersonInfo(); // 180, 100, 66

// 기본값 매개변수 사용 방식
function printPersonInfo2(height = 180, weight = 60, age = 66) {
    console.log(height, weight, age);
};

printPersonInfo2(178, 58, 29);  // 178, 58, 29
printPersonInfo2();  // 180, 60, 66

// 주의사항
/*
    ||를 이용한 방식과 기본값 매개변수 사용방식은 그 결과과 유사하게 보이지만
    실제로는 동작이 다르므로 주의해야 한다.
    ||를 이용한 방식과 다르게 기본값 매개변수 사용방식은 ??작동 방식을 기준으로
    동작하므로 undefined, null 인 경우에만 기본값을 할당한다.
    따라서 falsy한 모든 경우에 기본값을 할당하는 || 방식과 차이가 있다.

    정리
    1. ||를 이용한 방식: || 기준
    2. 기본값 매개변수 사용방식: ?? 기준
*/