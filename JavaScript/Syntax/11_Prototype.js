// 11. 프로토타입(Prototype)

/*
    자바스크립트는 '프로토타입 기반 언어(prototype-based language)'이다.
    프로토타입 기반 언어에서는 어떤 객체를 원형(prototype)으로 삼고 이를
    참조함으로써 상속과 비슷한 효과를 얻는다. 
*/

// 프로토타입 개념
var simpleArray = [];
simpleArray  
// [] => 브라우저에서 자세히 확인해보면 어떤 프로토타입을
// 기반으로 생성되었는지 확인 가능

/*
    __proto__를 읽을 때는 'dunder proto(던더 프로토)'라고 읽는다.
    dunder = double underscore의 줄임말
*/

/* 주의
    __proto__와 [[Prototype]]
    자바스크립트 명세에는 __proto__가 아닌 [[Protype]]으로 정의되어 있다.
    __proto__속성은 단지 브라우저에서 [[Prototype]]을 구현한 대상이며
    명세에서 권장하는 방식이 아니다.
    다만, 기존 레거시 코드에 대응하기 위해 브라우저에서 __proto__를 통해 직접
    접근하는 방식을 포기하지 않는 것.

    권장되는 방식은 __proto__를 통해 설정하기보다 
    Object.getPrototypeOf() / Object.create()
    등을 이용하는 것이다.
*/

// 위의 코드는 다음 코드와 같다.
var simpleArray2 = new Array();

// 위 문장을 실행하면 다음의 일이 일어난다.
/*
    1. 생성자 함수 Array를 new 키워드와 함께 호출하면
    2. 생성자 함수 Array에 정의된 내용을 바탕으로 새로운 인스턴스
        simpleArray2 가 생성된다.
    3. 이때 생성된 인스턴스인 simpleArray2에는 __proto__라는 속성이
        자동으로 부여되는데
    4. __proto__속성은 생성자 함수 Array의 prototype 속성을 참조한다.

    최신 브라우저(ex. chrome)에서는 __proto__속성이 아닌 [[Prototype]]
    속성으로 쓰여있다.
*/

console.log(Array.prototype === simpleArray2.__proto__); // true


// prototype 속성 사용 예제
function Person(name) {
    this.name = name;
}

// 모든 함수는 prototype 속성을 자동으로 가지고 있다.
Person.prototype.printName = function() {
    console.log(this.name);
}

var ironMan = new Person("Tony Stark");
ironMan.__proto__.printName(); // undefined
ironMan.printName();  // Tony Stark

/*
    위의 두 코드의 결과가 차이나는 이유는
    printName 메서드를 호출한 경우
    각 메서드의 바로 앞에 있는 개체의 this를 호출하게 되기 때문이다.

    ironMan.__proto__.printName() => ironMan.__proto__ 참조
    ironMan.printName() => ironMan 참조

    ironMan.__proto__ 객체에 printName이라는 함수는 존재하지만
    name이라는 속성은 없기 때문이다.

    ironMan 객체에는 생성자를 통해 name이라는 속성이 생겼다.
*/

var captainAmerica = new Person("Steve Rogers");
captainAmerica.printName();

// 위와 같이 실행할 수 있는 이유는 __proto__ 속성이 "생략 가능한 속성"
// 이기 때문이다.

/*
    captainAmerica.__proto__.printName();
    -> captainAmerica(.__proto__.)printName();
    -> captainAmerica.printName();
*/

/* 개념 정리
    정리하면, 자바스크립트는 함수에 자동으로 prototype 이라는 객체 타입의 속성을
    생성한다.
    new 연산자와 함께 함수를 호출하여 함수를 생성자 함수로서 사용할 경우, 생성된
    인스턴스에는 숨겨진 속성인 __proto__ 속성이 자동으로 생성된다.
    그리고 __proto__ 속성은 생성자 함수의 prototype 속성을 참조한다.
    __proto__ 속성은 생략이 가능하기 때문에 인스턴스에서 생상자 함수의 prototype
    에 정의된 메서드나 속성에 접근할 수 있다.
*/


// 프로토타입 체인

/* 메서드 오버라이드
    __proto__ 속성에 있는 메서드와 같은 이름의 메서드를 선언하면
    메서드 오버라이드가 일어난다.

    정리하면 메서드 위에 메서드를 덮어씌우는 현상으로 원본을 제거하고 다른
    대상으로 교체한 것이 아니라 원본 그대로 있는 상태에서 다른 대상을 위에
    얹는 개념이다.

    자바스크립트 엔진은 printName이라는 메서드를 찾을 때 먼저 실행 컨텍스트의
    속성들을 검색하고, 존재하지 않는다면 __proto__ 를 순차적으로 검색하는
    순서로 진행한다.
*/
function Person(name) {
    this.name = name;
}

Person.prototype.printName = function() {
    console.log(this.name);
}

var ironMan = new Person("Tony Stark");

// printName 메서드 오버라이드
ironMan.printName = function() {
    console.log(`I am ${this.name}`);
}

ironMan.printName();

// 프로토타입 체이닝
var simpleArray2 = new Array(1, 2);
simpleArray2.push(3);
console.log(simpleArray2.hasOwnProperty(2));

/*
    simpleArray2에 hasOwnProperty 함수를 정의하지 않았지만
    위와 같이 사용할 수 있는 이유는 simpleArray2의 프로토타입인
    Array의 프로토타입 Object에서 참조하기 때문이다.

    이와 같이 자바스크립트 엔진이 실행 컨텍스트에서 어떤 값을 참조하고자 할때
    각 객체의 프로토타입을 따라 올라가는 과정을 프로토타입 체이닝이라고 한다.
*/