# @RunWith

- @RunWith 어노테이션은 JUnit에 내장된 기본 테스트 러너인 BlockJUnit4ClassRunner 대신에 @RunWith로 지정된 클래스를 이용해 클래스 내의 테스트 메소드들을 수행하도록 지정해주는 어노테이션이다.
- JUnit 프레임워크의 확장이다. 이를 이용하여 자신에게 필요한 테스트 러너를 직접 만들어서 자신만의 고유한 기능을 추가해 테스트를 수행할 수 있다.
- 스프링 프레임워크에서 제공하는 SpringJUnit4ClassRunner 클래스가 이 확장 기능을 이용한 대표적인 사례이다. @RunWith로 SpringJUnit4ClassRunner class를 지정하면 @Repeat, @ProfileValueSourceConfiguration, @IfProfileValue 등 스프링에서 자체적으로 만들어 놓은 추가적인 테스트 기능을 할 수 있다.
- JUnit 내부에서도 기본 러너를 확장한 클래스가 있는데 대표적인 것인 SuiteClasses이다.
- 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.