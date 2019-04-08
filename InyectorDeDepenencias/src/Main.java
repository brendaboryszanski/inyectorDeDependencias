
public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		System.out.println("Arrancamo");
		Auto auto = Fabrica.crear(Auto.class);
		System.out.println("Creamos el auto");
		System.out.println("El auto tiene palanca " + auto.palanca);
		System.out.println("El auto tiene motor " + auto.motor);
		System.out.println("El auto no tiene iniciallizada la otra palanca " + auto.palancaNoInicializada);
	}
}
