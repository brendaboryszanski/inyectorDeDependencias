package demo;

import proyecto.Injected;
import proyecto.Component;

@Component
public class TheBeatles implements Banda
{
   @Injected(implementation=GeorgeHarrison.class)
   public Guitarrista primeraGuitarra;

   @Injected(implementation=JohnLennon.class)
   public Guitarrista segundaGuitarra;

   @Injected
   public Bajista bajista;

   @Injected
   public Baterista baterista;

   public String toString()
   {
      String ret = "";
      ret+="The Beatles { \n";
      ret+="       "+primeraGuitarra+"\n";
      ret+="      ,"+segundaGuitarra+"\n";
      ret+="      ,"+bajista+"\n";
      ret+="      ,"+baterista+" }\n";
      return ret;
   }
}
