
public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		System.out.println("Arrancamo");
		Auto auto = Fabrica.crear(Auto.class);
		System.out.println("Creamos el auto");
		System.out.println("El auto tiene palanca " + auto.palanca);
		System.out.println("El auto tiene motor " + auto.motor);
		System.out.println("El auto no tiene iniciallizada la otra palanca " + auto.palancaNoInicializada);
		System.out.println("La lista esta en "+ auto.palanca2);
		System.out.println("La interfaz se hizo " + auto.asiento);
		System.out.println("segunda palanca con singleton"+auto.palancaConS);
	}
}
