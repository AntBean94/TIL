// Nullish coalescing operator (??)

/*
    널 병합 연산자(Nullish coalescing operator)는 연산자 (??)의
    왼쪽 피연산자가 null 또는 undefined일 때 오른쪽 피연산자를 반환하고,
    그렇지 않으면 왼쪽 피연산자를 반환하는 논리 연산자이다.
*/

// 기존 문자열 할당 방식
function printTitle(text) {
    let title = text;
    if (text == null || text == undefined) {
        title = 'Gaudi is good developer';
    }
    console.log(title)
}

printTitle('Gaudi is ...');   // Gaudi is ...
printTitle();   // Gaudi is good developer

// 널 병합 연산자를 이용한 방식
function printTitle2(text) {
    let title = text ?? 'Guadi is good developer';
    console.log(title);
}

printTitle('Gaudi');    // Gaudi
printTitle();   // Gaudi is good developer
// 코드의 양이 줄어들고 간결해진것을 확인할 수 있다.


// 논리 연산자 OR(||) 과의 차이점
/*
    널 병합 연산자(??)와 비슷한 논리 연산자 OR(||)가 있다.
    논리 연산자 OR(||) 또한 왼쪽의 피연산자가 null 또는 undefined인 경우
    오른쪽의 피연산자를 반환한다.

    하지만, 논리 연산자 OR(||)는 null과 undefined를 포함한 falsy한 값인
    0, '', NaN 의 경우에도 오른쪽 피연산자를 반환한다.

    논리 연산자 OR(||)은 0, '', NaN 과 같은 값을 유효한 값이라고 설정한 경우에
    문제가 발생할 수 있다.

    이럴 때 널 병합 연산자(??)를 사용하면 문제를 해결할 수 있다.
*/

function printTitle3(text) {
    const title = text || "hi gaudi";
    console.log(title);
}

printTitle('hi');  // hi
printTitle();  // hi gaudi

function getCount(count) {
    return count || 'There is no record.';
}

getCount(0);  // There is no record

function getCount2(count) {
    return count ?? 'There is no record.';
}

getCount2(0);  // 0