import java.lang.reflect.Field;

public class Fabrica {
    public  static <T> T crear(Class<T> clase) throws InstantiationException, IllegalAccessException{
        T cosa = clase.newInstance();
        for (Field atributo: clase.getFields()
             ) {
            if(atributo.getAnnotation(Injected.class) != null){
                atributo.set(cosa, crear(atributo.getType()));
            }
        }
        return cosa;
    }
}
