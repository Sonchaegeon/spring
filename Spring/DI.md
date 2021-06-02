# DI (Dependency Injection)

DI는 IoC 프로그래밍 모델을 수현하는 방식중에 하나이다. Spring에서는 IoC를 구체적으로 DI라는 방식을 통해서 의존성 역전 제어를 하고 있는 것이다.

### 의존성

프로그래밍에서 의존한다는 말은 서로 다른 객체간의 레퍼런스 참조가 되어 있다는 말이다. 이는 A -> B에 의존 관계에 있을 때, B객체에 변경사항이 생겼을 때, A 객체가 영향을 받는 구조인 것이다. 

```java
public class A {
  private B b = new B();
  
  public void anyMethod() {
    b.~~
  }
}
```

### 주입

외부로부터 객체의 주소(레퍼런스) 값을 전달 받게 되어 객체가 참조 되어지는 방식이다.

그럼 의존성 주입이라는 말은 어떤말일까? 의존관계에 있는 객체들이 있을 때, 외부(스프링 Container)에서 객체에 레퍼런스를 전달하여 사용하고자 하는 객체에서 코드를 작성 할 수 있게 한다는 의미가 될 수 있다.

토비스프링에서 말하는 DI에 3가지 조건은 다음과 같다.

- 클래스 모델이나 코드에는 런타임 시점의 의존관계가 드러나지 않는다. 그러기 위해서는 인터페이스에만 의존하고 있어야 한다.
- 런타임 시점의 의존관계는 컨테이너나 팩토리 같은 제3의 존재가 결정한다.
- 의존관계는 사용할 오브젝트에 대한 레퍼런스를 외부에서 제공해줌으로써 만들어진다.

외부로부터 인터페이스 타입으로 얼마든지 부품이 교체 될 수가 있으니 코드가 유연해 진다. 또한 의존성 제어로 인해 수많은 객체들에 의존관계를 맺어주고 개발하는 클라이언트 입장에서는 편하게 비즈니스로직에 집중할 수 있다. 이러한 DI를 잘 녹은 프레임워크가 스프링이라고 한다.

**핵심은 DI는 클래스타입이 고정되어 있지 않고 인터페이스 타입의 파라미터를 통해 다이나믹하게 구현 클래스를 결정해서 제공 받을 수 있어야 한다.**

이것이 DI의 핵심 개념이다.

DI를 코드를 통해 알아보자

```java
public class IronManController {
  
  private IronManService ironManService;
  
  public IronManController(IronManService ironManService) {
    this.ironManService = ironManService;
  }
}
```

IronManService라는 인터페이스를

```java
public interface IronManService {
  
  void attack();
  void flying();
}
```

```java
public class HulkBuster implements IronManService {
  
  @Override
  public void attack() {
    System.out.println("HulkBuster Attack");
  }
}
```

```java
public class Mark15 implements IronManService {
  
  @Override
  public void attack() {
    System.out.println("Mark15 Attack");
  }
}
```

