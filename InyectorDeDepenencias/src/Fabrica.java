import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Hashtable;

public class Fabrica {

	private static HashMap<Class<?>, Object> singletons = new HashMap<Class<?>,  Object>();

	public static <T,P> T crear(Class<T> clase) throws InstantiationException, IllegalAccessException {
		T cosa = clase.newInstance();
		//boolean a = false;
		for (Field atributo : clase.getFields()) {
			if (atributo.getAnnotation(Injected.class) != null) {
				if(atributo.getAnnotation(Injected.class).singleton()){
					
					
					if(buscarPath(atributo.getType())!=null) {
						
						atributo.set(cosa, buscarPath(atributo.getType()));
						
						
					}else {
						/* P a = atributo.getClass().newInstance();
						P singleton=(P) crear(atributo.getClass()); */
						singletons.put(atributo.getType(), crear(atributo.getType()));
						atributo.set(cosa, (buscarPath(atributo.getType())));
						
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
	
	public static  Object buscarPath(Class<?> path){
		return singletons.get(path);
		
	}
	
	/*public static singleton() {
		
		
		
		
	}*/
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
