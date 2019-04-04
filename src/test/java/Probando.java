import org.junit.Assert;
import org.junit.Test;

public class Probando {
    @Test
    public void probar() throws InstantiationException, IllegalAccessException{
        Prueba prueba = new Prueba();
        Auto auto = prueba.crear(Auto.class);
        Assert.assertNotEquals(auto.auto1, null);

        Assert.assertEquals(auto.auto2, null);
    }
}
