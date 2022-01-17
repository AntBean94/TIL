// 9. Function

// 함수란 특정 기능을 수행하는 코드의 단위
// 이 코드의 모음은 반드시 1개 이상의 목적이 있어야 한다.

function sumNumbers(a, b) {
    return a + b;
};

console.log(sumNumbers(10, 20)); // 30

// 함수 표현식과 함수 선언문
console.log(sumNumbers1); // undefined
console.log(sumNumbers2); // Function

// 함수 표현식
var sumNumbers1 = function(a, b) {
    return a + b;
};

// 함수 선언문
function sumNumbers2(a, b) {
    return a + b;
};

// 일반적으로 함수 선언문을 추천하지만 개인 선택에 따라 사용하면됨
// 위의 콘솔에서 서로 다르게 찍히는 이유는 호이스팅 개념에 대해 이해하고 있어야함


// 함수형 사고와 함수형 프로그래밍
/*
    함수를 작성할 때는 가급적 '단일 책임 원칙(Single Responsibility Principle)'을
    지켜주면 좋다. 단일 책임 원칙이란 1개의 함수는 1개의 기능만 담당해야한다는 원칙으로
    함수에 여러가지 기능이 들어가면 들어갈수록 재사용하기가 어려워지기 때문이다.
    이는 추후 프론트엔드 프레임워크의 컴포넌트를 설계할 때도 사고방식에 영향을 줄 수 있다.
*/
// 예시로 비교해보자

// 잘 설계된 함수
function sumNumbers(a, b) {
    return a + b;
};

// 단일 책임 원칙에 벗어나는 함수
function sumNumbers(a, b) {
    var num = 1000;
    var data = {};

    $.get('domain.com/products/1').then(response => {
        data = response.data;
    });

    var total = a + b + num;
    return total
}

// 바로 위의 함수는 'sumNumbers'라는 이름을 갖고 있지만 실제로 두 수를
// 더하는 로직 이외에도 데이터 요청이나 다른 숫자를 더하는 로직들이 뒤엉켜 있다.

// 위와 같은 코드는 단일 책임 원칙에 벗어나는 코드이며 재사용하기 쉽지 않다.

// 단일 책임 원칙의 관점에서 리팩토링 해보면 다음과 같이 작성할 수 있다.
function sumNumbers(a, b) {
    return a + b;
};

function sumAll() {
    var num = 1000;
    var total = sumNumbers(0, 0) + num;
    return total;
}

function fetchData() {
    var data = {};
    $.get('domain.com/products/1').then(response => {
        data = response.data;
        return data;
    });
}

// 위와 같이 함수를 작성하는 방법을 더 깊이있게 공부하고 싶다면
// 함수형 프로그래밍에 대해 공부해보자.