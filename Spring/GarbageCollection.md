# 자바 가비지 컬렉션(Java Garbage Collection)

더 이상 사용되지 않는 메모리를 뜻하는 **가비지(Garbage)** 와 이를 청소하는 **가비지 컬렉션(Garbage Collection)** 에 대해 알아보자.

## 가비지(Garbage)란 무엇일까?

가비지란 무효영역이라고도 하며, **더 이상 사용되지 않는 메모리** 를 뜻한다. 자바의 `new`연산자를 이용하여 시스템으로부터힙 영역 메모리를 할당받아 사용되어지다가 더 이상 사용되지 않는 객체(Object)나 배열(Array) 메모리가 가비지에 해당된다.

여기서, "더이상 사용되지 않는다."라는 뜻은 객체나 배열을 가리키는 레퍼런스가 하나도 없음을 의미한다.

## Java Virtual Machine(JVM)

C나 C++ 에서는 OS 레벨의 메모리에 직접 접근하기 때문에 **free()** 라는 메소드를 호출하여 할당받았던 메모리를 명시적으로 해제해주어야 한다. 그렇지 않으면 **memory leak**이 발생하게 되고, 현재 실행중인 프로그램에서 **memory leak**이 발생하면 다른 프로그램에도 영향을 끼칠 수 있다.

반면, 자바는 OS의 메모리 영역에 직접적으로 접근하지 않고 **JVM** 이라는 가상머신을 이용해서 간접적으로 접근한다. JVM은 C로 쓰여진 또 다른 프로그램인데, 오브젝트가 필요해지지 않는 시점에서 알아서 free()를 수행하여 메모리를 확보한다. 웹 애플리케이션을 만들 때 모든 것을 다 직접 개발하여 쓰기보다 검증된 라이브러리나 프레임워크를 이용하는 것이 더 안전하고 편리한 것처럼, 메모리 관리라는 까다로운 부분을 자바 가상머신에 모두 맡겨버리는 것이다.

프로그램 실행 시 JVM 옵션을 주어서 OS에 요청한사이즈 만큼의 메모리를 할당 받아서 실행하게된다. 할당받은 이상의 메모리를 사용하게 되면 에러가 나면서 자동으로 프로그램이 종료된다. 그러므로 현재 프로세스에서 메모리 누수가 발생하더라도 현재 실행중인 것만 죽고, 다른 것에는 영향을 주지 않는다.

이렇게 자바는 가상머신을 사용함으로써 (운영체제로부터 독립적이라는 장점 외에도) OS 레벨에서의 memory leak은 불가능하게 된다는 장점이 있다.

자바가 메모리 누수현상을 방지하는 또 다른 방법이 가비지 컬렉션이다.

## 가비지 발생 사례 (Unreachable object)

> Stack에서 도달할 수 없는 Heap 영역의 객체를 말한다.

Java application에서 unreachable object를 보여주는 예제이다.

```java
Integer i = new Integer(4);
// Refrence 변수 i는 새로운 Integer 객체 인스턴스를 가르킨다.
i = null;
// Refrence 변수 i는 null로 할당됨
// 이전에 만든 Integer 객체 인스턴스는 unreachable object가 된다.
```

heap memory 영역에 object가 선언된 이후 i에 null이 선언되면서 Integer(4)는 어느 누구도 참조하지 않는 unreachable object가 되었다. 이 object는 곧 JVM의 GC(Garbage Collections)에 의해 반환된다.

## Garbage Collection

> Heap 영역의 오브젝트 중 stack에서 Unreachable object들은 가비지 컬렉션의 대상이 된다.

프로그래머는 heap을 사용할 수 있는 만큼 자유롭게 사용하고, 더 이상 사용되지 않는 오브젝트들은 가비지 컬렉션을 담당하는 프로세스가 자동으로 메모리에서 제거하도록 하는 것이 가비지 컬렉션의 기본 개념이다.

JVM의 Garbage Collector는 Unreachable Object를 우선적으로 메모리에서 제거하여 메모리 공간을 확보한다. 

Garbage Collection 과정은 `Mark and Sweep`이라고도 한다. JVM의 Garbage Collector가 스택의 모든 변수를 스캔하면서 각각 어떤 오브젝트를 레퍼런스하고 있는지 찾는 과정이 Mark이다. Reachable 오브젝트가 레퍼런스하고 있는 오브젝트 또한 marking한다. 첫 번째 단계인 marking 작업을 위해 모든 스레드는 중단되는데 이를 `Stop the World`라고 부르기도 한다. (System.gc()를 생각없이 호출하면 안되는 이유이기도 하다.)

그리고 나서 mark 되어있지 않은 모든 오브젝트들을 힙에서 제거하는 과정이 Sweep이다.

Garbage Collection이라고 하면 garbage들을 수집할 것 같지만 실제로는 garbage를 수집하여 제거하는 것이 아니라, garbage가 아닌 것을 따로 mark하고 그 외의 것은 모두 지우는 것이다. 만약 힙에 garbage만 가득하다면 제거 과정은 즉각적으로 이루어진다.

### 출처

[https://blog.voidmainvoid.net/190](https://blog.voidmainvoid.net/190)

[https://yaboong.github.io/java/2018/06/09/java-garbage-collection/](https://yaboong.github.io/java/2018/06/09/java-garbage-collection/)

[https://madplay.github.io/post/java-garbage-collection](https://madplay.github.io/post/java-garbage-collection)
