# Java의 정석 정리

## 삼항 연산자
* 조건 연산자는 조건식, 식1, 식2 모두 세 개의 피연산자를 필요로 하는 삼항 연산자이다.
조건식 ? 식1 : 식2  => true:false 


## 향상된 for문
```
for (int tmp: arr) {
 System.out.println(tmp);
}
```
* 향사된 for문은 일반 for문 보다 간결하나 배열이나 컬렉션에 저장된 요소들을 읽어오는 용도로만 사용할 수 있다는 제약이 있다.

## 배열
* 배열이란? 같은 타입의 여러 변수를 하나의 묶음으로 다루는 것을 "배열" 이라고 한다.

* char charAt(int index): 문자열에서 해당 위치에 있는 문자를 반환한다.
* int length(): 문자열의 길이를 반환한다.
* String substring(int from, int to): 문자열에서 해당 범위에 있는 문자열을 반환한다.
* boolean equals(Object obj): 문자열의 내용이 obj와 같은지 확인한다.
* char[] toCharArray(): 문자열을 문자배열(char[])로 변환해서 반환한다.


