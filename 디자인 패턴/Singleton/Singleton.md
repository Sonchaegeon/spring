# Singleton

**Singleton 패턴은 어떠한 클래스(객체)가 유일하게 1개만 존재 할 때 사용한다.**

이를 주로 사용하는 곳은 서로 자원을 공유 할 때 사용하는데, 실물 세계에서는 프린터가 해당되며, 실제 프로그래밍에서는 TCP Socket 통신에서 서버와 연결된 connect 객체에 주로 사용한다.

생성자가 여러 차례 호출되더라도 실제로 생성되는 객체는 하나고 최초 생성 이후에 호출된 생성자는 최초에 생성한 객체를 반환한다.	

## Singleton 패턴을 쓰는 이유

고정된 메모리 영역을 얻으면서 한번의 new로 인스턴스를 사용하기 때문에 메모리 낭비를 방지할 수 있다. 또한 Singleton으로 만들어진 클래스의 인스턴스는 전역 인스턴스이기 때문에 다른 클래스의 인스턴스들이 데이터를 공유하기 쉽다.

## Singleton 패턴의 문제점

싱글톤 인스턴스가 너무 많은 일을 하거나 많은 데이터를 공유시킬 경우 다른 클래스의 인스턴스들 간에 결합도가 높아져 [OCP(Open Closed Principle)](https://github.com/Sonchaegeon/spring/blob/master/%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5/SOLID.md)을 위반하게 된다. 따라서 수정이 어려워지고 테스트하기 어려워진다. 또한 멀티쓰레드 환경에서 동기화처리를 안하면 인스턴스가 두개가 생성된다든지 하는 경우가 발생할 수 있다.



## 멀티쓰레드에서 안전한(Thread-safe) Singleton 클래스, 인스턴스 만드는 방법

### Thread safe lazy initialization

**게으른 초기화**

```java
public class ThreadSafeLazyInitialization{
 
    private static ThreadSafeLazyInitialization instance;
 
    private ThreadSafeLazyInitialization(){}
     
    public static synchronized ThreadSafeLazyInitialization getInstance(){
        if(instance == null){
            instance = new ThreadSafeLazyInitialization();
        }
        return instance;
    }
 
}
```

private static으로 인스턴스 변수를 만들고 private 생성자로 외부에서 생성을 막았으며 synchronized 키워드를 사용해서 thread-safe하게 만들었다.

하지만 synchronized 특성상 비교적 큰 성능 저하가 발생하므로 권장하지 않는 방법이다.

### Thread safe lazy initialization + Double-checked locking

**게으른 초기화의 성능저하를 완화시키는 방법**

```java
public class ThreadSafeLazyInitialization {
 
    private volatile static ThreadSafeLazyInitialization instance;
 
    private ThreadSafeLazyInitialization(){}
     
    public static ThreadSafeLazyInitialization getInstance(){
        
        if(instance == null){
            synchronized (ThreadSafeLazyInitialization.class) {
                if(instance == null)
                    instance = new ThreadSafeLazyInitialization();
            }
 
        }
        return instance;
    }
}
```

getInstance()에 synchronized를 사용하는 것이 아니라 첫 번째 if문으로 인스턴스의 존재여부를 체크하고 두 번째 if문에서 다시 한번 체크할 때 동기화 시켜서 인스턴스를 생성하므로 thread-safe하면서도 처음 생성 이후에 synchronized 블럭을 타지 않기 때문에 성능저하를 완화했다.

### Initialization on demand holder idiom 

**holder에 의한 초기화**

클래스안에 클래스(Holder)를 두어 JVM의 Class loader 매커니즘과 Class가 로드되는 시점을 이용한 방법

```java
public class Something {
    private Something() {
    }
 
    private static class LazyHolder {
        public static final Something INSTANCE = new Something();
    }
 
    public static Something getInstance() {
        return LazyHolder.INSTANCE;
    }
}
```

이 방법은 JVM에 클래스 초기화 과정에서 보장되는 원자적 특성을 이용하여 싱글턴의 초기화 문제에 대한 책임을 JVM에 떠넘긴다. 

holder안에 선언된 instance가 static이기 때문에 클래스 로디이점에 한번만 호출될 것이며 final을 사용해 다시 값이 하당되지 않도록 만든 방법이다.

**가장 많이 사용하고 일반적인 Singleton 클래스 사용 방법이다.**

