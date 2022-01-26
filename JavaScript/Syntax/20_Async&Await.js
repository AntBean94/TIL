// 20. Async & Await

// 자바스크립트 비동기 처리 패턴의 최신 문법
// 비동기적 사고 방식에서 벗어나 동기적(절차적)으로 코드를 작성 할 수 있도록
// 도와준다.

// 기본 문법
async function fetchData() {
    await getUserList();
}
/*  설명
    async 함수는 함수의 앞에 async를 붙여주고 함수의 내부 로직 중 비동기 처리
    로직 앞에 await를 붙여주면 된다. 좀 더 정확하게 말하면 Promise 객체를 반환하는
    API 호출 함수 앞에 await를 붙인다.
*/

// 기본 예제
async function fetchData() {
    var list = await getUserList();
    console.log(list);
}

function getUsetList() {
    return new Promise(function(resolve, reject) {
        var userList = ['user1', 'user2', 'user3'];
        resolve(userList);
    });
}

// Async & Await 문법을 사용하기 위한 바벨 플러그인 설정
// 뷰 최신 CLI 도구는 별도의 바벨 플러그인을 설치하지 않아도 바로 async & await
// 문법을 사용할 수 있지만, 뷰 CLI 2.x 버전은 추가 설정이 필요하다.
/*
    1. 바벨 플러그인 설치 
    $npm i babel-plugin-transform-runtime

    2. .babelrc 파일에 아래 설정을 추가
    "plugins": ["transform-runtime"]
*/