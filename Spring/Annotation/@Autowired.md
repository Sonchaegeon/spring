# @Autowired

## Autowired란

필요한 의존 객체의 **타입**에 해당하는 빈을 찾아 주입한다.

- 생성자
- setter
- 필드

위의 3가지의 경우에 Autowired를 사용할 수 있다. 그리고 Autowired는 기본값이 true이기 때문에 의존성 주입을 할 대상을 찾지 못한다면 애플리케이션 구동에 실패한다.

## Autowired의 편리함

### @Autowired 사용전

```java
@Service
public class BookService {
  
  private BookRepository bookRepository;
  
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }
}
```

BookService Class는 생성자로 `BookRepository` 를 전달받고 인스턴스 변수에 할당하는 코드를 가지고 있다.

```xml
<bean id="bookRepository" class="com.spring.BookRepository" />

<bean id="bookService" class="com.spring.BookService">
	<constructor-arg name="bookRepository" ref="bookRepository" />
</bean>
```

이때 원래의 경우라면 BookService에 BookRepository를 주입하기 위해서 XML파일에 다음과 같이 Bean 설정을 해주어야 했다.

```java
@Configuration
public class ApplicationConfig {
  
  @Bean
  public BookRepository bookRepository() {
    return new BookRepository();
  }
  @Bean
  public BookService bookService() {
    return new BookService(bookRepository());
  }
}
```

또는 위처럼 XML 설정 파일을 대신하는 Java Class에 Bean설정을 만들어주어 해결할 수도 있다.

### @Autowired 사용

```java
@Service
public class BookService {
  
  private BookRepository bookRepository;
  
  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }
}
```

 `@Autowired` 어노테이션을 사용하는 경우 위와 같이 객체의 의존성을 가지는 부분에 간단히 `@Autowired` 어노테이션을 사용하면 쉽게 의존성 주입을 받을 수 있게 된다.

```java
@Repository
public class BookRepository { ... }
```

의존성 주입 타겟이 되는 Class 역시 당연히 Bean으로 등록이 되기위한 `@Repository` 어노테이션이 부여되어 있는것을 주목해야 한다.

### @Autowired Setter에서 사용하기

setter 메소드에서도 `@Autowired` 어노테이션을 부여하여 사용할 수 있다. 아래 코드는 실제 @Autowired 어노테이션 인터페이스이다.

```java
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(HetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
  
  /**
   * Declares whether the annotated dependency is required.
   * <p> Defaults to { @code ture }.
  */
  boolean required() default true;
}
```

​	`@Target`을 보면 `CONSTRUCTOR, METHOD, PARAMETER, FIELD, ANNOTATION_TYPE` 등에 부여가 가능한것이 보인다. 여기서 `@Autowired` 어노테이션 인터페이스가 가지고 있는 required() 메소드에 주목해야 한다.

```java
@Service
public class BookService {
  
  private BookRepository bookRepository;
  
  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }
}
```

위에서 보았던 `BookService` Class이다. 이번에는 생성자가 아닌 Setter 메소드에 `@Autowired` 어노테이션을 부여한 것을 볼 수 있다. 앞서 말했듯이 `METHOD`에도 부여가 가능하다고 했다.

```java
public class BookRepository { ... }
```

`BookRepository` Class의 Bean등록 어노테이션을 제거 하면 오류가 난다. **setter로 주입되는 의존성의 경우에는 생성자처럼 필수적으로 의존성 주입이 되어야하는 것이 아니다, 필요에 의해서 주입이 되는 것**이다.

아까전에 살펴보았던 `@Autowired` 어노테이션 인터페이스에서 보았던 `required()` 때문이다. `Default` 값이 `True`가 되어있었기 때문이다. `required`가 `true`인 경우에는 **해당 의존성은 꼭 필요한 대상이므로 주입을 반드시 받아야 한다**라는 의미가 되어버린다.

그러면 required를 false로 설정해보자. Bean에 등록이 되어있지 않음에도 bookRepository를 Autowired한것을 볼 수 있다.

### @Autowired의 생략

### 다형성 @Autowired

