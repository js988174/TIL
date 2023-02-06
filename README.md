# Restful-Service

* DispatcherServlet 
  * 클라이언트의 모든 요청을 한곳으로 받아서 처리
  * 요청에 맞는 Handler로 요청을 전달
  * Handler의 실행 결과를 Http의 실행 결과를 Http Response 형태로 만들어서 반환

* RestController
  * @Controller + @ResponseBody
  * View를 갖지 않는 Rest Data를 반환