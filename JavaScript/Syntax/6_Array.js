// 6. 배열 Array

// 배열은 객체와 더불어 실제로 웹 애플리케이션을 구현할 때
// 가장 많이 쓰이는 변수 타입이다.

// 아래는 배열 리터럴이라 하며 배열을 정의할 때 사용한다.
var arr = [];

// 배열의 요소
// 객체는 "속성 / 값"의 조합으로 데이터를 저장하지만,
// 배열은 "인덱스 / 값"의 조합으로 데이터를 저장한다.
arr[0] = 10;
console.log(arr);

// 배열 조작하기 - 1 (직접 인덱스에 접근해서 조작하기)
var arr1 = [];
arr1[0] = 100;
arr1[1] = 20;
arr1[0] = 1000;
console.log(arr1);

// 배열 조작하기 - 2 (내장 API를 사용하는 방법) => 추천
var arr2 = [100, 10, 20, 20, 100, 60];
arr2.push(100);
arr2.push(20);
arr2.splice(0, 1, 2);
console.log(arr2);

// 자주 사용하는 배열 API
var arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
// push(): 배열에 데이터 추가(맨 끝 인덱스부터 추가됨)
arr.push(100);
console.log(arr);
// slice(start, end): 배열의 특정 인덱스에 있는 값을 반환(배열의 내용이 변환되지 않음)
console.log(arr.slice(0, 3));
// splice(index, [0=삽입, 1=제거 or 대체], value)
// 배열의 특정 인덱스에 있는 값을 변경 또는 삭제(배열 변경)
arr.splice(0, 0, 94);
console.log(arr);
arr.splice(0, 1, 97);
console.log(arr);

// pop(): 배열의 마지막 인덱스의 값을 꺼냄
arr.pop();
console.log(arr);

// shift(): 배열의 첫 번째 인덱스의 값을 꺼냄
arr.shift();
console.log(arr);