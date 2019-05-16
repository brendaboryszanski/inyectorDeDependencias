package proyecto;
import java.util.List;


@Component
public class Auto {

	@Injected(implementation = AsientoDeCuero.class)
	public InterfazAsiento asiento;
	

	@Injected
	public InterfazAsiento asientoAndaPorfis;
	
	@Injected
	public Motor motor;
	
	@Injected 
	public Palanca palanca;
	
	
	@Injected(count = 4)
	public List<Palanca> palanca2;

	@Injected
	public Palanca palancaNoInicializada;
	
	@Injected
	public Palanca palancaConS;
	
}
