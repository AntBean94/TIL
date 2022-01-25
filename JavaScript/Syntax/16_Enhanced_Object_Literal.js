// 16. 향상된 객체 리터럴(Enhanced Object Literal)

// 향상된 객체 리터럴이란 기존 자바스크립트에서 사용하던 객체 정의 방식을
// 개선한 문법이다. 자주 사용하던 문법들을 간결하게 사용할 수 있다.

var gaudi = {
    // 속성: 값
    language: 'javascript',
    conding: function() {
        console.log('Hello World');
    }
};

// 축약 문법 1 - 속성과 값이 같으면 1개만 기입
var language = 'javascript';

var gaudi = {
    // language: language,
    language
};

console.log(gaudi);  // {language: 'javascript'};

// 뷰 컴포넌트 등록방식에도 적용된다.
const myComponent = {
    template: `<p>My Component</p>`
};

new Vue({
    components: {
        // myComponent: myComponent
        myComponent
    }
});

// 축약 문법 2 - 속성에 함수를 정의할 때 function 예약어 생략
// 기존 문법
var gaudi = {
    // 속성: 함수
    conding: function() {
        console.log('Hello World');
    }
};
gaudi.conding();  // Hello World

// 축약형
var gaudi = {
    // 속성: 함수
    conding() {
        console.log('Hello World');
    }
};
gaudi.conding();  // Hello World