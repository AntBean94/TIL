// 24. Optional Chaining(?.)

/*
    옵셔널 체이닝 문법은 객체의 속성 값이 유효한지 검증하는데 사용할 수 있다.
    
    정의
    옵셔널 체이닝(?.) 앞의 평가 대상이 undefined나 null이면 에러가 발생하지
    않고 undefined를 반환한다.

    중첩된 객체의 하위 속성을 찾기 위한 용도로 사용된다.
*/

// 옵셔널 체이닝 추가전 객체의 하위 속성 찾기
var userInfo = {
    name: {
        first: 'Gwak',
        last: 'Gaudi',
    },
    address: {
        city: 'Seoul',
        postcode: '04377',
    },
};

var postcode = userInfo.address && userInfo.address.postcode

// && 연산자를 사용해서 userInfo에 address 속성이 있는지 확인을 하고
// postcode에 접근하는 것을 확인할 수 있다.

// 옵셔널 체이닝 추가 후
var userInfo = {
    name: {
        first: 'Gwak',
        last: 'Gaudi',
    },
    address: {
        city: 'Seoul',
        postcode: '12345'
    },
    getInfo: () => userInfo,
};

var postcode = userInfo.address?.postcode;

// 객체의 하위 속성뿐만 아니라 메서드의 존재 여부를 확인하고 호출할 때도 사용가능
userInfo.getInfo?.();
// userInfo object

userInfo.setInfo?.();
// undefined

// . 대신 대괄호 []를 사용해 객체의 속성에 접근하는 경우에도 옵셔널 체이닝을 사용할 수 있다.

// 널 병합 연산자(??)와 같이 활용하기
const city = userInfo.address?.city ?? 'New York';
// 옵셔널 체이닝으로 검증한 객체의 속성 값이 null 또는 undefined이면
// 널 병합 연산자(??)의 오른쪽 항인 New York이 기본 값으로 적용된다.

// 옵셔널 체이닝을 남용하지 않도록 주의해야한다.
/*
    옵셔널 체이닝은 존재하지 않아도 괜찮은 대상에만 사용해야 한다. 
    위 예제 코드에서 userInfo는 반드시 존재해야 하지만 address는
    필수값이 아니다. 그러므로 
    userInfo.address?.city (바람직함)
    userInfo?.address?.city (바람직하지 않음)
    이렇게 사용하게 되면 userInfo의 값이 올바르지 않을 때
    즉시 에러를 발생시키지 못해 추후 디버깅에 어려움을 겪을 수 있다.
*/