package proyecto;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.reflections.Reflections;

import java.util.Hashtable;

public class Fabrica {
	private static HashMap<Class<?>, Object> singletons = new HashMap<Class<?>,  Object>();

	public static <T,P> T crear(Class<T> clase) throws InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException, IOException {
		//Chequeo que tenga singleton
		if(clase.getAnnotation(Component.class) != null && clase.getAnnotation(Component.class).singleton()){
			if(buscarObjeto(clase) != null) {
				
				return (T)buscarObjeto(clase);
				
			}else {
				T objeto = llenarObjeto(clase);
				singletons.put(clase, objeto);
				return objeto;
				
			}
		}else {
			return llenarObjeto(clase);
			//No tiene la anotation, devuelvo una nueva instancia
		}
		//boolean a = false;
	}

	public static <T> T llenarObjeto(Class<T> clase) throws InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException, IOException {
		T atributoPadre = clase.newInstance();
		for (Field atributo : clase.getFields()) {
			
			if(atributo.getType().equals(clase)) {
				
				throw new RuntimeException("Recursividad infinita :D");				
				
			}
			if (atributo.getAnnotation(Injected.class) != null) {
				//Si es una lista
				if (Collection.class.isAssignableFrom(atributo.getType())) { //Es una lista?
					atributo.set(atributoPadre, crearLista(atributo, atributo.getAnnotation(Injected.class).count()));
				} else {
					//Es un atributo
					
					if(atributo.getType().isInterface() && !isImplementation(atributo)){
						//Buscar si hay solo uno y crearlo o explotar en caso de que haya varios.
						crearImplementacionDeInterfaz(atributo, atributoPadre);
					}else {
						//Si hay implementacion, osea que no es el default = Object
						if(isImplementation(atributo)) {
							//Tiene implementation
							atributo.set(atributoPadre, crear(atributo.getAnnotation(Injected.class).implementation()));
						} else {
							//No es interfaz
						atributo.set(atributoPadre, crear(atributo.getType()));
						}	
					}
				}

			}
		} 
		return atributoPadre;
	}
	public static <T,W, P> void crearImplementacionDeInterfaz(Field atributo, T padre) throws IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {
		List<Class> classes = Arrays.asList(BuscadorDePaquetes.getClasses("proyecto"));
		
		Class<P>interfaz= (Class<P>) atributo.getType();
		
		List<Class> clasesQueImplementan = classes.stream().filter(clase->pertenece(clase.getInterfaces(),interfaz)).collect(Collectors.toList());
		if(clasesQueImplementan.size() != 1) {
			throw new RuntimeException("Mas de una implementacion para el atributo"+ atributo.getName());
		}else {
			
		Class instancia =clasesQueImplementan.get(0) ;
			atributo.set(padre, crear(instancia));
			}
	}
	public static boolean isImplementation(Field atributo) {
		return !atributo.getAnnotation(Injected.class).implementation().equals(Object.class);
	}
	public static <T> List<T> crearLista(Field field, Integer cantidad) throws InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException, IOException {
		ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
		Class<T> stringListClass = (Class<T>) stringListType.getActualTypeArguments()[0];
		List<T> lista = new ArrayList<T>();
		for(int i = 0; i < cantidad; i++) {
			lista.add(crear(stringListClass));
		}
		return lista; 
	}
	
	public static <T>boolean pertenece (T array[],T clase){ 
		
		boolean condicion=false;
		int i=0;
		while(!condicion&&i<array.length) {	
			condicion= array[i].toString().equals(clase.toString());
			i++;
			}
		return condicion;
			
			
		}
		
		

	public static Object buscarObjeto(Class<?> objeto){
		return singletons.get(objeto);
		
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
