import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Injected {
	
	
	public int count() default 1;
	public boolean singleton() default false;
	public Class<? extends Object> implementation() default Object.class;

}
