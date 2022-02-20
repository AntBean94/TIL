# 1. 데이터 타입

### 기본형과 참조형
- 기본형: 숫자, 문자열, 불리언, null, undefined, 심볼(ES6)
- 참조형: 객체, 배열, 함수, 날짜, 정규표현식, ..

### 불변값과 변수, 상수
- 변수와 상수를 구분짓는 변경 가능성의 대상은 "변수 영역" 메모리
- 불변성 여부를 구분할 때의 변경 가능성의 대상은 "데이터 영역" 메모리

### 불변값
변경은 새로 만드는 동작을 통해서만 이뤄지며 이것이 불변값의 성질이다.
한 번 만들어진 값은 가비지 컬렉팅을 당하지 않는 한 영원히 변하지 않는다.
기본형 데이터는 모두 불변값이다.
(아래의 코드를 보고 변수영역과 데이터영역을 분리해서 할당절차를 그려보자)
```javascript
var a = "abc";
a = a + "def";

var b = 5;
var c = 5;
b = 7;
```

### 가변값
기본적인 성질은 가변값인 경우가 많지만 설정에 따라 변경 불가능한 경우도 있고, 아예 불변값으로 활용하는 방법도 있다.
기본형 데이터와의 차이는 '객체의 변수(프로퍼티) 영역'이 별도로 존재한다는 점이다.
데이터 영역은 기존의 메모리 공간을 그대로 활용하며 객체의 변수 영역만 다른값으로 할당이 가능하다.
이런점으로 인해 참조형 데이터는 불변하지 않다.
```javascript
var obj1 = {
    a: 1,
    b: "abc"
};
obj1.a = 2;
```

참조형 데이터의 속성에 다시 참조형 데이터를 할당하는 경우: 중첩 객체(nested object)
```javascript
var obj = {
    x: 3,
    arr: [1, 2, 3]
};
```
참조형 데이터는 별도의 변수영역을 가진다는것을 염두에 두고 메모리에 어떻게 할당되는지 그림을 그려보자.

```javascript
obj.arr = "str";
```
재할당 과정을 설명해 보자.

### 기본형 데이터와 참조형 데이터의 차이
**복사**
```javascript
var a = 15;
var obj1 = {
    x: 10,
    y: "ccc"
};

var b = a;
var obj2 = obj1;
```

**변경**
```javascript
b = 20;
obj2.x = 20;
```
1. 변수 복사 후 프로퍼티 변경
- 기본형은 참조하고있는 주소가 바뀌므로 서로 다른 값을 가지게 된다. (a != b)
- 참조형은 참조하고있는 주소는 그대로이며 그 주소의 변수영역의 참조값이 바뀌기 때문에 여전히 두 변수는 같은 객체를 바라본다. (obj1 = obj2)

**직접 변경**
```javascript
b = 20;
obj2 = {x: 20, y: "ccc"}
```
2. 값 직접 변경
- 기본형은 위와 동일하다.
- 이번에는 객체 자체를 새로 작성한것과 같으므로 obj1과 바라보는 주소(객체)가 달라진다.(obj1 != obj2)

### 불변 객체
앞서서 알아보았듯이 자바스크립트는 기본형, 참조형에 관계없이 기존 데이터는 변하지 않는다.(변수 영역의 할당값만 변할뿐)
따라서 내부 프로퍼티를 변경할 필요가 있을때마다 새로운 객체를 생성해 할당한다면 기존 객체의 불변성을 확보할 수 있다.
객체의 가변성에 따른 문제점
```javascript
var user1 = {
    name: 'gaudi',
    gender: 'male'
}

var changeName = function (user, newName) {
    var newUser = user;
    newUser.name = newName;
    return newUser;
}

var user2 = changeName(user1, 'gwak');

// user1 == user2 (불변성이 보장되지 않음)
```

객체의 가변성에 따른 문제점의 해결방법(light way)
```javascript
var user1 = {
    name: 'gaudi',
    gender: 'male'
};

var changeName = function(user, newName) {
    return {
        name: newName,
        gender: user.gender
    };
};

var user2 = changeName(user1, 'gwak');
// user != user2 (불변성)
```

프로퍼티의 갯수와 관계없이 복사하기
```javascript
var user1 = {
    name: 'gaudi',
    gender: 'male'
};

var copyObject = function(target) {
    var result = {};
    for (var prop in target) {
        result[prop] = target[prop];
    }
    return result;
};

var user2 = copyObject(user1);
user2.name = 'gwak'

// user1 != user2 (불변)
```
여전히 아쉬운점이 있다.(얕은 복사)

### 얕은 복사와 깊은 복사
중첩된 객체에서 참조형 데이터가 저장된 프로퍼티를 복사할 때는 그 주솟값만 복사한다. => 즉, 참조형데이터를 수정하면 원본과 사본 모두 변경
따라서 이러한 문제를 해결하려면 참조형 데이터를 한번더 복사해야한다.
객체가 객체를 가지고있는 구조에서는 재귀적 방법으로 복사를 하면 문제를 해결할 수 있다.
재귀적 방법의 깊은 복사
```javascript
var copyObject = function(target) {
    var result = {};
    if (target === 'object' && target != null) {
        for (var prop in target) {
            // 속성이 참조형일 경우 재귀적으로 깊이 들어간다. (아니면 바로 리턴)
            result[prop] = copyObject(target[prop]);
        }
    } else {
        result = target;
    }
    return result;
}
```

json을 활용해 간단하게 깊은 복사를 구현할 수도 있다.
```javascript
var copyObjectViaJSON = function(target) {
    return JSON.parse(JSON.stringify(target));
}

var obj = {
    a: 1,
    b: {
        c: null,
        d: [1, 2],
        func1: function () { console.log(3); }
    },
    func2: function () { console.log(4); }
};
var obj2 = copyObjectViaJSON(obj);;
obj2.b.c = "gaudi";

// obj1 != obj2 (잘 작동한다.)
```
다만 json을 활용한 방식은 **내부 함수등을 복사하지는 않으므로** 순수한 정보를 다룰 때 유용한 방식이다.

### undefined와 null
const, let을 사용해서 자바스크립트가 직접 undefined를 할당하지 않게 한다면
undefined는 오직 '값을 대입하지 않은 변수에 접근하고자 할 때 자바스크립트 엔진이 반환해주는 값'으로만 존재하고
'비어있음'을 명시적으로 나타내고자 할때 null을 사용하면 된다.
ES6를 사용해야 하는 이유.