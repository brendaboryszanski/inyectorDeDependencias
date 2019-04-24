import java.util.List;

public class Auto {

	@Injected(implementation = AsientoDeCuero.class)
	public InterfazAsiento asiento;
	
	@Injected
	public Motor motor;
	
	@Injected 
	public Palanca palanca;
	
	
	@Injected(count = 4)
	public List<Palanca> palanca2;

	@Injected(singleton=true)
	public Palanca palancaNoInicializada;
	
	@Injected(singleton=true)
	public Palanca palancaConS;
	
}
