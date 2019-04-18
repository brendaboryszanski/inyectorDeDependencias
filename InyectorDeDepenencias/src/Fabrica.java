import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Fabrica {

	private static List<Object> listaDeSingletons = new ArrayList<>();

	public static <T> T crear(Class<T> clase) throws InstantiationException, IllegalAccessException {
		T cosa = clase.newInstance();
		boolean a = false;
		for (Field atributo : clase.getFields()) {
			if (atributo.getAnnotation(Injected.class) != null) {
				if(atributo.getAnnotation(Injected.class).singleton()){
					
					Iterator<Object> it = listaDeSingletons.iterator();
					while(it.hasNext()) {
						Object o = it.next();
						if(o.getClass().equals(atributo.getClass())) {
							atributo.set(cosa, o);
							a = true;
							break;
						}
					}
					if(!a) {
						//Si no lo encontras, lo creas y lo metes
						atributo.set(cosa, crear(atributo.getType()));
						listaDeSingletons.add(atributo);
					}
					
				} else {

					if (Collection.class.isAssignableFrom(atributo.getType())) { //Es una lista?
						atributo.set(cosa, crearLista(atributo, atributo.getAnnotation(Injected.class).count()));
					} else {
						if(!atributo.getAnnotation(Injected.class).implementation().equals(Object.class)) {
						atributo.set(cosa, crear(atributo.getAnnotation(Injected.class).implementation()));
						} else {
						atributo.set(cosa, crear(atributo.getType()));
						}
					}

				}
			}
		}
		return cosa;
	}

	public static <T> List<T> crearLista(Field field, Integer cantidad) throws InstantiationException, IllegalAccessException {
		ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
		Class<T> stringListClass = (Class<T>) stringListType.getActualTypeArguments()[0];
		List<T> lista = new ArrayList<T>();
		for(int i = 0; i < cantidad; i++) {
			lista.add(crear(stringListClass));
		}
		return lista; 
	}
}
/*
 * habria que hacer un if anidado? necesitamos saber si dice injected y si dice
 * count para difereciar arrays lo mismo para implemetation y para singleton
 * 
 * podria ser un metodo que sea el nombre de la annotation y de ahi delegarlo a
 * otro metodo
 * 
 * 
 * 
 * 
 */
