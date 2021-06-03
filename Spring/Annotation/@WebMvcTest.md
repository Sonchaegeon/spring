# @WebMvcTest

- MVC를 위한 테스트
- 웹에서 테스트하기 힘든 컨트롤러를 테스트하는 데 적합
- 웹상에서 요청과 응답에 대해 테스트할 수 있음
- 시큐리티, 필터까지 자동으로 테스트하며, 수동으로 추가/삭제 가능
- @SpringBootTest 어노테이션보다 가볍게 테스트할 수 있음
- @Controller, @ControllerAdvice, @JsonComponent, Converter, GenericConverter, Filter, HandlerInterceptor만 스캔하도록 제한함