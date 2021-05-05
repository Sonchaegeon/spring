# 다형성

## 다형성(Polymorphism) 이란?

> 다형성이란 프로그램 언어 각 요소들(상수, 변수, 식, 객체, 메소드 등)이 다양한 자료형(type)에 속하는 것이 허가되는 성질을 말한다. - 위키피디아

- 하나의 코드가 여러 자료형으로 구현되어 실행되는 것
- 같은 코드에서 여러 다른 실행 결과가 나옴

다형성을 활용하면 기능을 확장하거나, 객체를 변경해야 할 때 타입 변경없이 객체 주입만으로 수정이 일어나게 할 수 있다. 또한 상속을 사용한다면 중복되는 코드까지 제거할 수 있으므로 더욱 객체 지향 설계와 가까워 질 수 있다.

## 다형성을 구현하는 방법

### 오버로딩(Overloading)

Calculator 예시

```java
public class Calculator  {
	public void sum(int a, int b) {
    System.out.println(a + b);
  }
  
  public void sum(float a, float b) {
    System.out.println(a + b);
  }
}
```



오버로딩은 여러 종류의 타입을 받아들여 결국엔 같은 기능을 하도록 만들기 위한 작업이다. 이 역시 메소드를 동적으로 호출할 수 있으니 다형성이라고 할 수 있다. 하지만 메소드를 오버로딩하는 경우 요구사항이 변경 되었을 때 모든 메소드에서 수정이 수반되므로 필요한 경우에만 적절히 고려하여 사용하자.

### 오버라이딩(Overriding)

오버라이딩은 상위 클래스의 메서드를 하위 클래스에 재정의하는 것을 말한다. 따러서 여기서는 상속의 개념이 추가된다.

```java
class Person {
    public String name;
    public int age;

    public void info() {
        System.out.println("사람의 이름은 " + name + ", 나이는 " + age + "살입니다.");
    }
}

class Man extends Person {
    String job;

    @Override
    public void info() {
        super.info();
        System.out.println("남자의 직업은 " + job + "입니다.");
    }
}

public class Test {
    public static void main(String[] args) {
        Man man = new Man();

        man.age = 18;
        man.name = "손채건";
        man.job = "프로그래머";

        man.info();
    }
}
```

오버라이드 다형성 방식을 잘 활용하면, 기능의 확장과 객체의 수정에 유연한 구조를 가져갈 수 있다.

### 함수형 인터페이스(Functional Interface)

마지막으로는 함수형 인터페이스 방식을 살펴보자. 함수형 인터페이스(Functional Interface)란, 람다식을 사용하기 위한 API로 자바에서 제공하는 인터페이스에 구현할 메소드가 하나 뿐인 인터페이스를 의미한다.

### 출처

https://woowacourse.github.io/javable/post/2020-10-27-polymorphism/