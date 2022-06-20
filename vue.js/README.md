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

### vue 문법

- 1-1
    * v-text, {{}} : 태그 문자열을 HTML 인코딩하여 나타내기 떄문에 화면에는 태그 문자여일 그대로 나타난다.
    * v-html : 태그 문자여을 파싱하여 화면에 나타냄.



## vue 컴포넌트 구성

* App.vue : Vue 최상위 컴포넌트 