// 5. Set
// Set 객체는 중복되지 않는 값들의 집합이다.
// 교집합, 합집합, 차집합 등 수학적 집합 표현이 가능하다.

// Set 객체의 생성
const set = new Set();

// set 값 추가
set.add('javascript');
set.add('vue');
set.add('node');

// set 값 추가 #2
set.add('javascript').add('vue').add('node');

// Set은 중복된 값의 추가를 허용하지 않는다.
console.log(set);
// 결과
// {'javascript', 'vue', 'node'}

// Set 값 삭제
// Set.prototype.delete
// Set.prototype.clear
const set2 = new Set(['apple', 'banana', 'orange']);

// set 값 삭제
set2.delete('apple');
console.log(set2);

// set 값 모두 삭제
set2.clear();
console.log(set2);

// Set 반복문
// Set.prototype.forEach
// 콜백함수의 인수는 (요소 값, 요소 값, Set)
const set3 = new Set(['apple', 'banana', 'orange', 'fineapple']);

// set 값 순회
set3.forEach((val, val2, set3) => {
    console.log(val, val2, set3);
});
// 결과
// apple apple Set(4) { 'apple', 'banana', 'orange', 'fineapple' }
// banana banana Set(4) { 'apple', 'banana', 'orange', 'fineapple' }
// orange orange Set(4) { 'apple', 'banana', 'orange', 'fineapple' }
// fineapple fineapple Set(4) { 'apple', 'banana', 'orange', 'fineapple' }

for (const val of set3) {
    console.log(val);
};
// 결과
// apple
// banana
// orange
// fineapple

// key값 가져오기
for (const key of set3.keys()) {
    console.log(key);
};
// 결과(위와 동일)

// value값 가져오기
// set에서 values()는 keys()와 같다.
for (const val of set3.values()) {
    console.log(val);
};
// 결과(위와 동일)

// 추가된 순서대로 반환한다.
for (const [key, val] of set3.entries()) {
    console.log("key" + " = " + key);
    console.log("value" + " = " + val);
};
// 결과
// key = apple
// value = apple
// key = banana
// value = banana
// key = orange
// value = orange
// key = fineapple
// value = fineapple

// Set의 주요 메서드와 프로퍼티
const value = "apple"
set3.has(value);    // value가 존재하면 true, 존재하지 않으면 false
set3.size();    // Set 객체가 가진 원소의 수를 나타낸다.