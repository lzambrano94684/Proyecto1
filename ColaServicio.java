package ProjectTicket;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ColaServicio {
    // Atributos
    String nitSoporte;
    public static SimpleDateFormat fechaHora;
    String mensaje;
    String evento;
    Integer id;

    // Inicia Metodos Getter
    public String getEvento (Integer intEstado) {
        String[] estados = new String[4];
        estados[1] = "Creado";
        estados[2] = "En Proceso";
        estados[3] = "Escalado";
        estados[4] = "Resueldo";
        return evento = estados[intEstado];
    }
    public String getNitSoporte() {
        return nitSoporte;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Integer getId() {
        return id;
    }
    // Fin Metodos Getter

    // Inicia Metodos Setter
    public String setNitSoporte(String NitSoporte) {
        return this.nitSoporte = NitSoporte;
    }

    public String setMensaje(String Mensaje) {
        return this.mensaje = Mensaje;
    }

    public Integer setId(Integer Id) {
        return this.id = Id;
    }
    // Fin Metodos Setter

    public void estadoTicket(Integer fin){
        int t = getId();
        System.out.println("*********************************************************");
        System.out.println("*************** DETALLE DEL TICKET "+t+" *******************");
        System.out.println("*********************************************************");
        System.out.println("** NIT: "+getNitSoporte());
        System.out.println("** DESCRIPCION: "+getMensaje());
        System.out.println("***************** ¿QUE DESEA HACER? *********************");
        System.out.println("** [1] RESOLVER                                        **");
        if (fin == 0){
            System.out.println("** [2] ESCALAR                                         **");
            System.out.println("** [3] REGRESAR                                        **");
        }
        System.out.println("*********************************************************");
    }

    public void mostrarTicket(){
        int t = getId();
        System.out.println("*********************************************************");
        System.out.println("*************** DETALLE DEL TICKET "+t+" *******************");
        System.out.println("*********************************************************");
        System.out.println("** NIT: "+getNitSoporte());
        System.out.println("** DESCRIPCION: "+getMensaje());
        System.out.println("***************** ESCALAR TICKET A: *********************");
        System.out.println("** [1] MESA DE AYUDA                                   **");
        System.out.println("** [2] SOPORTE TECNICO                                 **");
        System.out.println("** [3] DESARROLLO                                      **");
        System.out.println("** [4] REGRESAR                                        **");
        System.out.println("*********************************************************");
    }
}
