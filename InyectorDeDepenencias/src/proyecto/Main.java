package proyecto;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException, IOException {
		System.out.println("Arrancamo");
		Auto auto = Fabrica.crear(Auto.class);
		System.out.println("Creamos el auto");
		System.out.println("El auto tiene palanca " + auto.palanca);
		System.out.println("El auto tiene motor " + auto.motor);
		System.out.println("El auto no tiene iniciallizada la otra palanca " + auto.palancaNoInicializada);
		System.out.println("La lista esta en "+ auto.palanca2);
		System.out.println("La interfaz se hizo " + auto.asiento);
		System.out.println("segunda palanca con singleton"+auto.palancaConS);
		System.out.println("La implementacion de asiento sin annotation"+auto.asientoAndaPorfis);
	}
}
