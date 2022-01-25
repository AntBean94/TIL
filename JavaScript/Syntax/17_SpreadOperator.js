// 17. 스프레드 오퍼레이터(Spread Operator)

// 사용법
var obj = {
    a: 10,
    b: 20
};
var newObj = {...obj};
console.log(newObj);  // {a: 10, b: 20}

var arr = [1, 2, 3];
var newArr = [...arr];
console.log(newArr);  // [1, 2, 3]

// vuex에서도 적용시
import { mapGetters } from 'vuex';

export default {
    computed: {
        ...mapGetters(['getStrings', 'getNumbers']),
        anotherCount() {
            // ..
        }
    }
}