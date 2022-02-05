# 5. Methods

특정 기능들로 묶인 자바스크립트 함수

### 역할
1. 특정 기능을 수행하는 메서드
```html
<button v-bind:click="clickButton">click me</button>

<script>
new Vue({
  methods: {
    clickButton() {
      alert('clicked');
    }
  }
})
</script>
```

2. 특정 기능을 묶은 저장소
```html
<button v-bind:click="displayProducts">Refresh</button>

<script>
new Vue({
  data: {
    products: []
  },
  methods: {
    displayProducts() {
      this.fetchData();
      // ..
    },
    fetchData() {
      axios.get('/products').then(function(response) {
        this.products = response.data;
      }).catch(function(error) {
        alert(error);
      });
    }
  }
})
</script>
```