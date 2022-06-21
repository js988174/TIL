# Vue.js

* vue.js란 컨트롤러 대신 뷰 모델을 가지는 MVVM(Model-View-ViewModel) 패턴을 기반으로 디자인된,
자바스크립트 프레임워크이다.

## 특징

* MVVM 패턴을 사용한다.
* 가상 DOM을 지원하여 아주 빠른 UI 렌더링 속도를 제공한다.

## 📣 설치 도구

* Node.js를 설치하고 node.js 터미널에서 npm install -g npm 실행
* Visual Studio Code에서 Vue 확장 프로그램 설치
* 크롬 확장 프로그램 Vue.js devtools 설치
* node.js 터미널에서 npm install -g vue-cli 실행

### vue 확장 프로그램 정리

* View in Browser: html 파일을 기본 브라우저로 볼 수 있게 해준다.
* HTML Snippets: HTML 태그 조각을 빠르게 작성할 수 있도록 도와줍니다.
* JS-CSS-HTML Formatters: JS, CSS, HTML의 코드 자동완성 기능 제공
* Vue 2 snippets: vue 지원 문법 강조 기능
* Vue-beautify: Vue.js 코드에 대한 정리 배치 기능을 제공
* ESLint: 자바스크립트 코드 스타일, 문법 체크 기능 제공
* vetur: 코드 문법 강조, 자동완성, 디버깅 제공
 

## vue 시작하기

1. vue init simple todoList 
2. 컨트롤 + F1로 실행
3. vue create 이름 으로 vue 폴더 생성


## vue 컴포넌트 구성

* App.vue : Vue 최상위 컴포넌트 

## ES6란?

* Front-End Framework에서 권고하는 언어 형식
* ESC5에 비해 문법이 간결하다

## Vue.js 생명주기
 beforeCreate: 인스턴스가 생성되고 실행되는 라이프 사이클 이 떄는 data와 methods 속성이 정의되어 있지 않고 요소에도 접근할 수 없음.
   
 created: data와 methods 속성이 정의되어 있기 떄문에 속성값에 접근하는 로직을 구현 해도 된다. 하지만 인스턴스가 부착전이라 돔 요소는 접근할 수 없다.

 beforeMount : render()함수가 호출되기 직전 단계. created 이후에 template 속성에 지정한 마크업 속성을 render() 함수로 변환 후 el 속성에 지정한 화면 요소에 인스턴스를 부착하기 직전에 호출됨 (화면에 붙이기 전 실행해야 할 코드 구현)

 mounted: e1 속성에서 지정한 화면 요소에 인스턴스가 부착되고 난 후 호출되는 단계 화면 요소를 제어하는 코드 구현

 beforeUpdate: 변경 예정인 데이터 값을 이용해 작업을 해야할 때 로직 구현하기 

 updated: beforeUpdate가 끝나고 화면에 다시 그려지는 단계 데이터가 변경되고 화면 요소를 제어하는 것을 구현하고 싶을 때 사용 (이 단계에서 값을 또 변경하면 무한 루프에 빠지기 떄문에 조심)

 beforeDestroy: 아직 인스턴스가 없어지지 않았기 때문에 접근 가능하며 인스턴스가 사라지기 직ㅈ번 해야하는 작업 구현                         

 destroyed: 뷰 인스턴스에 정의한 모든 속성, 하위에 선언했던 인스턴스들 모두 제거
 
## vue 문법

- 1-1
    * v-text, {{}} : 태그 문자열을 HTML 인코딩하여 나타내기 떄문에 화면에는 태그 문자여일 그대로 나타난다.
    * v-html : 태그 문자여을 파싱하여 화면에 나타냄.
    * v-bind : 요소 객체의 속성들을 바인딩하기 위해 사용
    * v-model : 양방향 데이터 바인딩이 필요할 경우 사용
    * v-if : 조건에 부합하지 않으면 랜더링 하지않음
    * v-show : 조건에 부합하지 않더라도 랜더링 함 (if문 보다 자주 사용)
    * 

