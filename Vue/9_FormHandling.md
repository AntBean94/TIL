# 9. Form 다루기

폼(Form)은 웹 애플리케이션에서 가장 많이 사용되는 코드 형식으로 로그인이나
상품 결제 등 모든 곳에 사용자의 입력을 처리하는 폼이 필요하다.

## Form 제작하기
예시로 간단한 로그인 폼을 만들어 보자

1. HTML 태그 구성
```html
<form>
    <div>
        <label for="email">Email</label>
        <input id="email" type="text">
    </div>
    <div>
        <label for="password">Password</label>
        <input id="password" type="password">
    </div>
    <div>
        <button type="submit">Login</button>
    </div>
</form>
```

2. HTML 태그에 뷰 속성 추가
```html
<form v-on:submit.prevent="loginUser">
  <div>
    <label for="email">Email</label>
    <input id="email" type="text" ref="email">
  </div>
  <div>
    <label for="password">Password</label>
    <input id="password" type="password" ref="password">
  </div>
  <div>
    <button type="submit">Login</button>
  </div>
</form>
```

3. 로그인 버튼 클릭시 처리
로그인 버튼을 클릭하면 `loginUser()` 메서드가 실행된다.
```javascript
new Vue({
    methods: {
        loginUser() {
            var email = this.$refs.email.value;
            var password = this.$refs.password.value;
            axios.post('/login', {
                email: email,
                password: password
            });
        }
    }
})
```
이메일과 비밀번호 값을 받아 HTTP POST 요청을 날려 로그인 인증과정을 거친다.

## Form Validation
사용자의 입력이 유효한지 검사하는 작업으로 폼에서 반드시 수행해야 하는 작업이다.
뷰에서는 vee-validate, Vuelidate 등의 라이브러리로 폼의 유효성 검사를 
할 수 있다.

라이브러리 이외에도 뷰의 기본 속성을 활용해서 아래와 같이 간단하게 유효성 검사를
수행 할 수 있다.
```html
<div>
  <label for="email">email</label>
  <input id="email" v-model="email" type="text">
</div>
```
```javascript
new Vue({
  data: {
    email: ''
  }
})
```
인풋 박스의 입력값은 모두 v-model 디렉티브를 이용하여 email이라는 데이터 속성에
연결한다.

만약 이 인풋 박스에 최소 10글자 이상 입력해야 한다면 다음과 코드를 작성할 수 있다.
```html
<div>
  <label for="email">email</label>
  <input id="email" v-model="email" type="text">
  <small>{{ emailValidation }}</small>
</div>
```
```javascript
new Vue({
  data: {
    email: ''
  },
  computed: {
    emailValidation: function() {
      return this.email.length > 10 ? `` : `Length must be over 10`;
    }
  }
})
```
위와 같이 삼항 연산자를 이용해 글자가 10개 이하면 계속 경고 표시가 뜨도록 computed속성을
구현하면 된다.