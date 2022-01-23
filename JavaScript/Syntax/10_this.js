// 10. this

/*
    this는 함수의 실행 컨텍스트를 가리키는 예약어이다.
    실행 컨텍스트의 서전적 정의는 '함수가 실행되는 환경'이며
    좀 더 쉽게 접근하자면 '함수가 실행될 때의 컨텍스트'로 이해할 수 있다.

    js에서 this가 어렵게 다가오는 이유는 this가 상황에 따라 
    다른값을 가리키기 때문이다.
*/

// 첫 번째 this
console.log(this);  // 브라우저 콘솔창에서 입력하면 window 객체 출력
// this의 가장 기본적인 컨텍스트는 글로벌(전역) 컨텍스트이다.

// 두 번째 this
var obj = {
    num: 10,
    printNum: function() {
        console.log(this.num)
    }
}
obj.printNum(); // 10
// => 객체 속성 함수 안에서의 this는 기본적으로 해당 객체를 가리킨다.

// 세 번째 this
function showComment() {
    console.log(this);
}

showComment();  // window

function Developer() {
    console.log(this);
}
var dev = new Developer();  // Developer {}
// 생성자를 통해 생성된 객체 내부의 메서드는 객체를 가리킨다.

// 네 번째 this
function fetchData() {
    axios.get('domain.com/products').then(function() {
        console.log(this);
    });
}

fetchData();  // window
// 기본적으로 HTTP 요청과 같은 비동기 처리 코드는 전역 컨텍스트를 갖는다.