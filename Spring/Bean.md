# 빈(Bean)

Spring IoC 컨테이너가 관리하는 자바 객체를 빈(Bean)이라는 용어로 부른다. 우리가 new 연산자로 어떤 객체를 생성했을 때 그 객체는 빈이 아니다. ```ApplicationContext.getBean()```으로 얻어질 수 있는 객체는 빈이다. 즉 Spring에서의 빈은 ApplicationContext가 알고있는 객체, 즉 ApplicationContext가 만들어서 그 안에 담고 있는 객체를 의미한다.

