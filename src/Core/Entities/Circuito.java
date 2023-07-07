package Core.Entities;

public class Circuito {
   private int cantVueltas,cantCurvas,cantZonasSobrepaso;
   private long longitud,recordVueltaRapida;
   private Pais pais;
   private String nombre;
   
 public Circuito(int cantVueltas, int cantCurvas, int cantZonasSobrepaso, long longitud, long recordVueltaRapida, String nombre){
	 this.cantCurvas = cantCurvas;
	 this.cantZonasSobrepaso = cantZonasSobrepaso;
	 this.cantVueltas = cantVueltas;
	 this.longitud =  longitud;
	 this.recordVueltaRapida = recordVueltaRapida;
	 this.nombre = nombre;
 }
   public int getcantCurvas() {
	   return cantCurvas;
   }
   public int getcantVueltas() {
	   return cantVueltas;
   }
   public int getcantZonasSobrepaso() {
	   return cantZonasSobrepaso;
   }
   public long getLongitud() {
	   return longitud;
   }
   public long getrecordVueltaRapida() {
	   return recordVueltaRapida;
   }
   public String getNombre() {
	   return nombre;
   }
   
   
   
   
   
   
}
