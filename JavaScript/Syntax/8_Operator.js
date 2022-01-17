// 8. 연산자(Operator)

// 연산자는 프로그래밍 로직을 구현할 때 논리식이나 산술식을 표현하기 위해
// 사용하는 기호들을 의미하며 주로 다음과 같은 기호들로 표현된다.
/*
    산술 연산자: +, -, *, /, %
    논리 연산자: ||, &&
    조건 연산자: ? :
    관계 연산자: >, <, ===
*/

// 연산자의 활용1 - 변수 초기화
function fetchData(data) {
    var receivedData;
    if (data === undefined) {
        receivedData = localStorage.getItem('item');
    }
}

// 위의 로직에 논리 연산자를 적용하면 더 깔끔하게 코드를 작성할 수 있다.
function fetchData(data) {
    var receivedData;
    receivedData = data || localStorage.getItem('item')
}

// 연산자의 활용2 - 조건문 대신 삼항 연산자
if (data !== undefined) {
    num = 50;
    if (num > 10) {
        num = 100;
    } else {
        num = 0;
    }
}

// 삼항 연산자 활용
if (data !== undefined) {
    num = 50;
    num = num > 10 ? 100 : 0;
}