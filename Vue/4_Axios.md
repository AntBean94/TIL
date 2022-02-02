# 4. Axios

뷰에서 권고하는 HTTP 통신 라이브러리는 액시오스(Axios)이다.
Promise 기반의 HTTP 통신 라이브러리이며 상대적으로 다른 HTTP 통신
라이브러리들에 비해 문서화가 잘되어 있고 API가 다양하다.

## 설치
1. CDN 방식
```html
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
```

2. NPM 방식
```bash
$npm install axios
```

## 사용방법
라이브러리를 설치하면 axios라는 변수에 접근할 수 있게 된다.
axios 변수를 이용해 아래와 같이 HTTP 요청을 날리는 코드를 작성할 수 있다.
```html
<div id="app">
  <button v-on:click="fetchData">get data</button>
</div>

<script> 
new Vue({
  el: '#app',
  methods: {
    fetchData: function() {
      axios.get('https://jsonplaceholder.typicode.com/users/')
        .then(function(response) {
          console.log(response);
        })
        .catch(function(error) {
          console.log(error);
        });
    }
  }
})
</script>
```

## Axios 응답 제어
.then
비동기 통신이 성공했을 경우, `.then()`은 콜백을 인자로 받아 결과값을 처리할 수 있다.

.catch
`.catch()`를 통해 오류를 처리한다. error 객체에서는 오류에 대한 주요 정보를 확인할 
수 있다.
```javascript
axios.get('/hello')
    .catch(function (error) {
        if (error.response) {
            console.log(error.response.status);
            console.log(error.response.headers);
        }
    }
```

## 액시오스 HTTP 요청 메서드 종류
### axios.get(url[, config])
서버에서 데이터를 가져올 때 사용하는 메서드. 두 번째 파라미터 config 객체에는 헤더
(header), 응답 초과시간(timeout), 인자 값(params) 등의 요청 값을 같이 넘길 수 
있다.
```javascript
axios.get('user/1')
```

### axios.post(url[, data[, config]])
서버에 데이터를 새로 생성할 때 사용하는 메서드. 두 번째 파라미터 `data`에 생성할 데이터를
넘긴다.
```javascript
axios.post('/books', { title: '1984' })
```

### axios.put(url[, data[, config]])
특정 데이터를 수정할 때 요청하는 메서드.
`put`은 새로운 리소스를 생성하거나, 이미 존재하는 데이터를 대체할 때 사용한다.
따라서 클라이언트에서 리소스를 식별한다. 중복 요청에 대한 결과가 동일하다.(멱등)
post 요청과의 차이점이다.
```javascript
axios.put('users/2', { name: 'Iron Man' })
```

### axios.delete(url[, config])
특정 데이터나 값을 삭제할 때 요청하는 메서드.
```javascript
axios.delete('books/3')
```

## axios HTTP요청 Config 옵션
### method
사용할 요청 메서드를 결정한다. 기본값은 get

### url
사용될 서버의 URL을 의미한다.

### baseURL
액시오스 인스턴스 생성시 인스턴스의 기본 URL을 정할 수 있는 속성.
```javascript
baseURL: 'https://도메인.com/api/'
```

### headers
헤더를 수정해서 보내야하면 headers를 사용한다.
```javascript
headers: {'X-Requested-With': 'XMLHttpRequest' }
```

### params
`params`는 HTTP 요청에 붙일 URL 파라미터를 의미한다. 예를 들어, 아래 예시
코드는 `https://domain.com`이라는 URL로 HTTP요청을 보냈을 때 
`https://domain.com?id=123`으로 전달하는 것과 같은 효과를 가진다.
여기서 params가 null이나 undefined인 경우에는 파라미터가 조합되지 않는다.
```javascript
params: {
    id: 123
}
```

### data
`data`는 HTTP 요청 바디에 실어서 보낼 데이터를 의미한다. 주로 데이터를 조작해야
하는 PUT, POST, DELETE, PATCH 등의 메서드에서 사용한다.
```javascript
data: {
    firstName: 'Gaudi'
},

// 아래의 data config 설정은 POST 메서드에서만 사용이 가능하다.

data: 'Age=29&City=PanGyo'
```

### timeout
`timeout`은 HTTP 요청을 보내고 응답을 받기까지의 제한 시간을 설정하는 속성이다.
요청 시간이 지정된 값을 초과하면 에러가 발생한다.
```javascript
// 단위 (millisecond)
timeout: 5000
```

### responseType
`responseType`은 서버로부터 어떠한 데이터 형식으로 응답받을지 지정하는 것이다.
옵션으로는 arraybuffer, document, json, text, stream이 가능하다.
기본값은 json이다.
```javascript
responseType: 'json'
```