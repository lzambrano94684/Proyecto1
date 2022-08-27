package ProjectTicket;

import java.util.Scanner;

public class Ticket extends ColaServicio{
    // Atributos
    String nitUsuario;
    Integer id;
    String problema;
    String estado;

    // Inicia Metodos Getter
    public String getNitUsuario() {
        return nitUsuario;
    }

    public Integer getId() {
        return id;
    }

    public String getProblema() {
        return problema;
    }

    public String getEstado() {
        return estado;
    }

    // Fin Metodos Getter

    // Inicia Metodos Setter
    public String setNitUsuario(String nitUsuario) {
        return this.nitUsuario = nitUsuario;
    }

    public Integer setId(Integer id) {
        return this.id = id;
    }

    public String setProblema(String problema) {
        return this.problema = problema;
    }

    public String setEstado(String estado) {
        return this.estado = estado;
    }
    // Fin Metodos Setter

    public void mostrarTicket(String estado){
        System.out.println("*********************************************************");
        System.out.println("*************** TICKET "+estado+" CON EXITO *****************");
        System.out.println("*********************************************************");
        System.out.println("** ESTADO: "+getEstado());
        System.out.println("** NIT: "+getNitUsuario());
        System.out.println("** DESCRIPCION: "+getProblema());
        System.out.println("** TICKET: "+getId());
        System.out.println("*********************************************************");
        System.out.println("*********************************************************\n");
        System.out.print("Presione enter para continuar");
        new Scanner(System.in).nextLine();
    }
}
