import java.lang.reflect.Field;

public class Prueba{
    public  <T> T crear(Class<T> clase) throws InstantiationException, IllegalAccessException{
        T cosa = clase.newInstance();
        for (Field atributo: clase.getFields()
             ) {
            if(atributo.getAnnotation(Injected.class) != null){
                atributo.set(cosa, this.crear(atributo.getType()));
            }
        }
        return cosa;
    }

}
