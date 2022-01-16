// 4. Map
// Map 객체는 키와 값을 한 쌍으로 이루어진 컬렉션이다.

// Map 객체 생성
const map = new Map();  // Map 생성자 함수로 생성

// Map 값 추가
// map 값 추가 #1
map.set('key1', 'value1');
map.set('key2', 'value2');
console.log(map);

// map 값 추가 #2
map
    .set('key3', 'value3')
    .set('key4', 'value4');
console.log(map);

// 중복된 키가 있을 때는 Map객체에 새로운 키로 저장되지 않고,
// 값을 덮어쓴다.
map.set('key1', 'value100');
console.log(map)

// Map 값 삭제
/*
    특정 값 삭제: Map.prototype.delete 메서드 사용
    모든 값 삭제: Map.prototype.clear 메서드 사용
*/
const map2 = new Map([['key1', 'value1'], ['key2', 'value2'], ['key3', 'value3']])

// map 값 삭제
map2.delete('key1');
console.log(map2);

// map 모든 값 삭제
map2.clear();
console.log(map2);

// Map 반복문
/*
    Map 객체의 값들을 반복할때는
    Map.prototype.forEach 메서드를 사용한다.

    forEach가 받는 콜백함수의 인수는 3가지(값, 키, map)이다.
*/

const map3 = new Map([['key1', 'value1'], ['key2', 'value2']]);

map3.forEach((val, key) => {
    console.log(val + ", " + key);
})

// 결과
// value1, key1
// value2, key2

// key값 가져오기
for (let key of map3.keys()) {
    console.log("key : " + key);
}
// 결과
// key : key1
// key : key2

// value값 가져오기
for (let val of map3.values()) {
    console.log("val : " + val);
}
// 결과
// val : value1
// val : value2

// entries 반복문
for (let[key, val] of map3.entries()) {
    console.log(key + " : " + val);
}
// 결과
// key1 : value1
// key2 : value2

// Map의 주요메서드와 프로퍼티
const key = 'key1'
console.log(map.get(key));   // key에 해당하는 값을 반환한다.
console.log(map.has(key));   // key가 존재하면 true, 존재하지 않으면 false를 반환
console.log(map.size);   // 요소의 개수 반환