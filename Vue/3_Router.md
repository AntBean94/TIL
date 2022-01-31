# 3. 뷰 라우터(Vue Router)

## 등록
```javascript
// 라우터 인스턴스 생성
var router = new VueRouter({
    // 라우터 옵션
})

// 뷰 인스턴스에 라우터 인스턴스를 등록
new Vue({
    router: router
})
```

## 뷰 라우터 옵션
라우터 등록 이후에는 라우터에 옵션을 정의한다.
대부분의 SPA 앱에서는 아래와 같이 2개 옵션을 필수로 지정한다.
- routes: 라우팅 할 URL과 컴포넌트 값 지정
- mode: URL의 해쉬 값 제거 속성

```javascript
new VueRouter({
    mode: 'history',
    routes: [
        { path: '/login', component: LoginComponent },
        { path: '/home', component: HomeComponent }
    ]
})
```

## router-view
브라우저의 주소 창에서 URL이 변경되면, 앞에서 정의한 routes 속성에 따라
해당 컴포넌트가 화면에 뿌려진다. 이 때 뿌려지는 지점이 템플릿의 `<router-view>`이다.
```HTML
<div id="app">
    <router-view></router-view>
</div>
```

## router-link
일반적으로 웹 페이지에서 페이지 이동을 할 때는 사용자가 url을 다 쳐서 이동하지
않는다. 이 때 화면에서 특정 링크를 클릭해서 페이지를 이동할 수 있게 해줘야 하는데
그게 바로 `<router-link>`이다.
```HTML
<router-link to="이동할 URL"></router-link>
```

## 기타 라우터 옵션과 기법
### 동적 라우트 매칭
동적 라우트 매칭(Dynamic Route Matching)은 특정 패턴을 가진 경로들을 동일한
컴포넌트에 매핑해야 할 경우 사용하는 기법니다.
예시를 들어 확인해보자.
```javascript
new VueRouter({
    routes: [
        { path: '/resume/:year', component: ResumeComponent}
    ]
})
```
위와 같이 라우터를 정의하면 path에서 콜론`:` 으로 시작하는 부분(동적 세그먼트)이 다르더라도 같은 경로에 매핑된다.
따라서 `/resume/2020`과 `/resume/2021`같은 URL이 모두 `ResumeComponent`
컴포넌트를 보여주게 된다.
또한 `:year`에 해당하는 값은 다음과 같이 해당 컴포넌트에서 `this.$route.params`
를 통해 가져와서 활용할 수 있다.
```HTML
<template>
    <h1>{{ $route.params.year }}</h1>
</template>
```

### 컴포넌트에 props 전달하기
동적 라우트 매칭에서 살펴본 것처럼 컴포넌트에서 `$route`를 사용하는 경우, 컴포넌트가
라우터 속성에 의존하게 되어 특정 URL에서만 사용된다는 문제점이 있다.
이 의존성 해제를 위해 컴포넌트와 라우터 속성을 분리하려면 속성 `props`에 `true`를 
전달한다.
```javascript
new VueRouter({
    routes: [
        { path: '/resume/:year', component: ResumeComponent, props: true }
    ]
})
```
위 코드와 같이 `props`가 `true`로 적용되면 :year에 해당하는 부분이 year라는 props
로 전달된다.
이를 통해 컴포넌트를 라우터 속성에 의존하지 않고 재사용할 수 있다.
```HTML
<template>
    <h1>{{ year }}</h1>
</template>

<script>
export default {
    props: ['year']
}
</script>
```

### 라우터 네비게이션 메서드
라우터 API에는 `<router-link>`로 웹 페이지 이동을 위한 `<a>`태그를 만드는 방법
이외에도 프로그래밍적으로 페이지를 이동할 수 있는 메서드가 있다.
Vue 인스턴스 내부에서 라우터 인스턴스에 접근하려면 $router를 사용하면 된다.
#### router.push()
```javascript
router.push(location, onComplete?, onAbort?);
```
`<router-link>`의 to속성과 같다. 히스토리에 현재 페이지를 저장한 뒤 인자로
전달받은 URL(location)로 이동한다.
```javascript
// string 전달
this.$router.push('home');

// object 전달
this.$router.push({ path: 'home' });

const postId = "1";
this.$router.push({ path: `/posts/${postId}` }); // -> /posts/1

// query와 함께 전달
this.$router.push({ path: 'user', query: { id: 'captain' } });
```

`onComplete`와 `onAbort`는 콜백 함수로, onComplete는 네비게이션이 성공적으로
완료되었을 경우 호출되고, obAbort는 현재 네비게이션이 완료되기 전 동일한 경로로 이동
하거나 다른 경로로 이동될 때 호출된다.

```javascript
export default {
    methods: {
        click() {
            this.$router.push("/home", this.completeHandler, this.abortHandler)
        },
        completeHandler() {
            console.log("complete");
        },
        abortHandler() {
            console.log("abort");
        },
    },
};
```

#### router.replace()
```javascript
router.replace(location, onComplete?, onAbort?);
```
히스토리에 현재 페이지를 저장하지 않고 이동한다. 따라서, 히스토리에 경로가 남지
않으므로 백스페이스키를 눌렀을 때 원래 페이지로 돌아올 수 없다. 이름에 나타나듯이
현재 페이지가 다른 페이지로 대체되는 것이다.

#### router.go()
```javascript
router.go(n)
```
히스토리 스택에서 앞 또는 뒤로 전달된 인자(정수)만큼 이동한다.

### 라우터 메타 필드
라우터를 정의할 때 `meta` 필드를 통해 원하는 메타 정보를 입력할 수 있다.
로그인이 필요한 라우팅인지 아닌지 구분해야 하는 경우에 활용될 수 있다.
로그인 필요 여부 처리 방법에 대해서는
https://joshua1988.github.io/vue-camp/advanced/navigation-guard.html#%E1%84%82%E1%85%A6%E1%84%87%E1%85%B5%E1%84%80%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%89%E1%85%A7%E1%86%AB-%E1%84%80%E1%85%A1%E1%84%83%E1%85%B3-%E1%84%92%E1%85%A9%E1%84%8E%E1%85%AE%E1%86%AF-%E1%84%89%E1%85%AE%E1%86%AB%E1%84%89%E1%85%A5-flow
링크 참조
```javascript
new VueRouter({
    routes: [
        // authRequired: true 라는 메타 필드 입력
        { path: '/orders', component: OrdersComponent, meta: { authRequired: true } }
    ]
})
```

### 코드 분할
Webpack과 같은 번들러를 이용해 앱을 제작하면 파일이 커져 페이지 로드 시간이 오래 걸릴 
수 있다. 처음에 렌더링에 필요한 모든 파일을 로드하는 것보다 경로에 맞춰 당장 렌더링이 필요한 파일만 로드하는 것이 효율적일 수 있다. 이때 사용하는 기법이 코드 분할이다.
https://joshua1988.github.io/vue-camp/advanced/code-splitting.html#%E1%84%80%E1%85%A2%E1%84%87%E1%85%A1%E1%86%AF-%E1%84%92%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A7%E1%86%BC-%E1%84%8C%E1%85%A9%E1%84%80%E1%85%A5%E1%86%AB
링크 참조