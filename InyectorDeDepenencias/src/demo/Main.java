package demo;

import java.io.IOException;

import proyecto.Fabrica;
/*
import frm.myspring.ComponentScan;
import frm.myspring.MySpring;

@ComponentScan("frm.myspring.demo")
public class DemoMySpring
{ }
*/
public class Main {
   public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException, IOException {
      Banda banda = Fabrica.crear(TheBeatles.class);
      System.out.println(banda);
   }
}
