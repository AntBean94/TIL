// 19. Promise

// 프로미스 객체를 활용하면 다음과 같은 에러처리가 가능하다.
function loadScript(src) {
    return new Promise((resolve, reject) => {
        const script = document.createElement('script');
        script.src = src;

        script.onload = () => resolve(script);
        script.onerror = () => reject(new Error('Error!'));

        document.head.append(script);
    });
}

loadScript('callback.js')
    .then(console.log)
    .catch(console.log);

// 기본 문법
const promise1 = new Promise((resolve, reject) => {
    // executor: 실행함수, 객체 생성 후 자동적으로 실행된다.
})

// 프로미스 객체 프로퍼티
/*
    프로미스 객체는 두 가지 프로퍼티(properties)를 가진다.

    1. 상태(state): pending으로 초기화되며 resolve가 호출될 시 fulfilled로,
        reject가 호출될 시 rejected로 바뀐다.
        rejected, resolved 두 상태를 통칭해 settled 상태라고 한다.
    2. 결과(result): undefined로 초기화되며 resolve(value) 메서드가 호출될
        시 value로, reject(error) 메서드가 호출될 시 error로 바뀐다.
*/

// 예제
const promise2 = new Promise((resolve, reject) => {
    setTimeout(() => {
        resolve('success');
    }, 1000);
});
console.log(promise2);

// 주의사항
// 1) 프로미스객체의 실행 함수는 단 하나의 resolve 또는 reject만 처리할 수 있다.
const promise3 = new Promise((resolve, reject) => {
    resolve('done!');

    reject(new Error('error'));  // ignored
    setTimeout(() => {
        resolve('..');
    }, 1000);
})
// 2) 프로미스의 reject는 resolve와 마찬가지로 인자에 어떠한 타입이 와도
// 상관없지만, Error 객체와 함께 처리하는 것이 권장된다.
// 이유: https://github.com/petkaantonov/bluebird/blob/master/docs/docs/warning-explanations.md#warning-a-promise-was-rejected-with-a-non-error

// 3) resolve와 reject는 꼭 비동기적으로 호출되어야 하는 것은 아니다.
const promise4 = new Promise((resolve, reject) => {
    resolve(123);
});

// 4) 프로미스 객체의 state와 result는 외부에서 접근할 수 없다.
// .then, .catch, .finally 메서드를 통해 다뤄질 수 있다.

// then
// .then 메서드는 첫 번째 인자로 resolved 상태를 처리하는 함수를 받고, 
// 두 번째 인자로는 rejected 상태를 처리하는 함수를 받는다.
promise4.then(
    function(result) {
        // resolved!
    },
    function(error) {
        // rejected!
    }
);

// catch
// 프로미스객체의 에러를 처리할 때 (rejected된 경우) 사용된다.
// .catch 메서드는 .then 메서드의 첫 번째 인자에 null을 전달한 것과 마찬가지로
// 작동한다.
const asyncThing = new Promise((resolve, reject) => {
    setTimeout(() => reject(new Error('Error!')), 1000);
});

asyncThing.catch(alert);  // same as promise.then(null, alert)

// finally
// .finally 메서드는 프로미스가 settled 상태일 때 호출된다. Promise 객체 정의
// 후, 작업 처리의 성공 및 실패 여부에 상관없이, .finally 메서드를 사용하기만 하면
// 무조건 호출되는 메서드이다. => 인자를 받지 않는다.
const promise5 = new Promise((resolve, reject) => {
    setTimeout(() => resolve('result!'), 1000);
});
promise5
    .then(console.log)
    .finally(() => {    // 작업 처리 완료후 반드시 실행
        alert('promise ready!');
    })

// 프로미스를 활용한 비동기처리 예시
function getTodo() {
    return new Promise((resolve, reject) => {
        $.get('https://jsonplaceholder...', (response) => {
            resolve(response);
        });
    });
}
getTodo().then(console.log);