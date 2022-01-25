// 18. 구조 분해 문법(Destructuring)

// 구조 분해 문법(?)

// 기존 자바스크립트에서 '구조'
var arr = [1, 2, 3, 4, 5];
var obj = {
    a: 10,
    b: 20,
    c: 30
};

// => 구조 분해
var {a, b, c} = obj;

console.log(a);  // 10
console.log(b);  // 20
console.log(c);  // 30

// 특정 객체의 값을 꺼내오는 방법
var gaudi = {
    language: 'javascript',
    position: 'front-end',
    area: 'pangyo',
    hobby: 'game',
    age: '1070'
};

var language = gaudi.language
var position = gaudi.position
var area = gaudi.area
var hobby = gaudi.hobby
var age = gaudi.age

// 코드의 양을 비교해보자
var { language, position, area, hobby, age } = gaudi


// 뷰엑스에 적용하는 구조 분해 문법 1
// actions 속성은 모두 context라는 인자를 받는다.
actions: {
    fetchData({ commit }) {
        commit('developProducts');
    }
}
// context라는 객체에 commit 속성이 있으므로 위와같이 사용

// 뷰에 적용하는 구조 분해 문법 1
methods = {
    id: Number,
    title: String, 
    created: String,
    author: String,

    savePost(postId) {
        // ..
    },
    submitForm() {
        // 구조 분해 문법 미적용
        this.savePost(this.id);

        // 구조 분해 문법 적용
        const { id, savePost } = this;
        savePost(id);
    }
}

// 단, 데이터의 출처(컴포넌트, 외부)가 헷갈릴 수 있으므로
// 프로젝트에서 정해진 컨벤션에 따라 사용한다.

// 뷰에 적용하는 구조 분해 문법 2
// v-for 디렉티브에 적용
/*
<ul>
    <li v-for="{ title, author, id} in posts">
        {{ title }} - {{ author }}
    </li>
</ul>
*/