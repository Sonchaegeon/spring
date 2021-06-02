# IoC(Inversion of Control)이란?

IoC란 Inversion of Control의 약자로 해석하자면 **제어의 역전**이다. 해석하자면 제어, 즉 제어권이 역전되었다는 뜻인데 제어권이 뒤 바꼈다는 뜻이다. 이말은 기존의 제어 방식을 뒤집었다는 말이 된다.

그렇다면 기존의 방식은 어떠했냐?

자바가 등장하고 자바 기반의 어플리케이션이 개발되던 초기에는 자바 객체를 생성하고 객체간의 의존관꼐를 연결하는 등의 제어권을 개발자가 직접 가지고 있었다고 한다. 그러나 서블릿, EJB가 등장하면서 개발자가 독점적으로 가지고 있던 제어권이 서블릿과 EJB를 관리하는 외부의 컨테이너로 넘어갔고 객체의 생성부터 생명주기의 관리까지 모든 객체에 대한 제어권이 바뀐 것을 IoC, 제어의 역전이라 하는 것이다.

IOC는 객체의 **의존성**을 역전시켜 객체 간의 **결합도**를 줄이고 **유연한 코드**를 작성할 수 있게 하여 **가독성** 및 **코드 중복**, **유지보수**를 편하게 할 수 있게 한다.

기존에는 다음과 같은 순서로 객체가 만들어지고 실행되었다.

1. 객체 생성

2. 의존성 객체 생성

   (클래스 내부에서 생성)

3. 의존성 객체 메소드 호출

하지만, 스프링에서는 다음과 같은 순서로 객체가 만들어지고 실행된다.

1. 객ㅊ체 생성

2. 의존성 객체 주입

   (스스로가 만드는 것이 아니라 제어권을 스프링에게 위임하여 스프링이 만들어놓은 객체를 주입한다.)

3. 의존성 객체 메소드 호출

스프링이 모든 의존성 객체를 스프링이 실행될 때 다 만들어주고 필요한 곳에 주입시켜줌으로서 Bean들은 `Singleton Pattern`의 특징을 가지며, 제어의 프름을 사용자가 컨트롤 하는 것이 아니라 스프링에게 맡겨 작업을 처리하게 된다.

## IoC Container

모든 작업을 사용하는 쪽에서 제어하게 되면서 IoC 컨테이너에서 제어하게 되는데, 기본적으로 컨테이너는 객체를 생성하고 객체간의 의존성을 이어주는 역할을 한다.

### BeanFactory

BeanFactory 인터페이스는 IoC컨테이너의 기능을 정의하고 있는 인터페이스이며, Bean의 생성 및 의존성 주입, 생성주기(lifecycle) 관리 등의 기능을 제공한다. 여기서 Bean이란 IoC컨테이너에 의해 생성되고 관리되는 객체를 의미한다.

### ApplicationContext

BeanFactory 인터페이스를 상속받는 ApplicationContext는 BeanFactory가 제공하는 기능 외에 AOP, 메시지처리, 이벤트 처리 등의 기능을 제공한다.

모든 ApplcationContext 구현체는 BeanFactory의 기능을 모두 제공하므로, 특별한 경우를 제외하고는 ApplicationContext를 사용하는 것이 바람직하다.

Spring 프레임워크에서는 다수의 ApplicationContext를 제공하는데 아래는 ClassPathXmlApplicationContext를 생성하는 예시이다.

```java
ApplicationContext context = new ClassPathXmlApplicationContext("config/bean.xml");
MyBean bean = context.getBean("myBean");
```

