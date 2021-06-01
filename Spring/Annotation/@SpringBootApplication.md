# @SpringBootApplication

@SpringBootApplication 어노테이션은 스프링부트의 가장 기본적인 설정을 선언해준다.

해댕 어노테이션을 보면 아래와 같은 어노테이션이 다시 선언되어 있다.

```java
@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
    @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {TypeExcludeFilter.class}),
    @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {AutoConfigurationExcludeFilter.class})})
public @interface SpringBootApplication {

    @AliasFor(annotation = EnableAutoConfiguration.class)
    public Class[] exclude() default {};

    @AliasFor(annotation = EnableAutoConfiguration.class)
    public String[] excludeName() default {};

    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
    public String[] scanBasePackages() default {};

    @AliasFor(annotation = ComponentScan.class, attribute = "basePackageClasses")
    public Class[] scanBasePackageClasses() default {};

    @AliasFor(annotation = ComponentScan.class, attribute = "nameGenerator")
    public Class nameGenerator() default BeanNameGenerator.class;

    @AliasFor(annotation = Configuration.class)
    public boolean proxyBeanMethods() default true;
}
```

위와 같이 구성되어 있다. 이렇듯 @SpringBootApplication은

- @SpringBootConfiguration
- @ComponentScan
- @EnableAutoConfiguration

3가지의 역할을 수행한다. 또한 내부적으로 2단계에 걸쳐서 Bean을 등록한다.

1. @ComponentScan
   - @Component @Configuration @Repository @Service @Controller @RestController
   - 해당 어노테이션이 선언된 하위 패키지에서 위와 같은 Annotaition을 찾아서 Bean으로 등록한다.
2. @EnableAutoConfigure
   - 사전에 정의한 라이브러리들을 Bean으로 등록해 주는 어노테이션이다.
   - 사전에 정의한 라이브러리들 모두가 등록되는 것은 아니고 특정 Condition(조건)이 만족될 경우에 Bean으로 등록한다.
