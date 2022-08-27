package ProjectTicket;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Principal extends Ticket {
    public static String[][] ticket = new String[100][3];
    public static Integer[][] ticketEstado = new Integer[100][1];
    public static String[][][] ticketBitacora = new String[100][100][6];
    public static Integer corrBitacora = 0;

    public static void menuPrincipal() {
        System.out.println("*********************************************************");
        System.out.println("** PROYECTO 1 OPEN SOURCE TICKET REQUEST SYSTEM (OTRS) **");
        System.out.println("*********************************************************");
        System.out.println("** [1] Crear Ticket                                    **");
        System.out.println("** [2] Trabajar Ticket                                 **");
        System.out.println("** [3] Reporteria                                      **");
        System.out.println("** [4] Salir                                           **");
        System.out.println("*********************************************************");
        System.out.println("*********************************************************");
        System.out.print("** ELIJA UNA OPCION: ");
    }

    public static void main(String[] args) throws IOException, InterruptedIOException, InterruptedException {
        int opcion = 1, opcionFin = 4, corr = 0;
        repiteMenu(opcion, opcionFin, corr);

    }

    public static void repiteMenu(Integer opcion, Integer opcionFin, Integer corr) {
        Scanner entrada = new Scanner(System.in);
        while (opcion > 0 && opcion <= opcionFin) {
            menuPrincipal();
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    corr++;
                    crearTicket(corr);
                    break;
                case 2:
                    trabajarTicket(corr);
                    break;
                case 3:
                    reporteriaMenu();
                    break;
                case 4:
                    opcion = 0;
                    System.out.println("Adios :-)");
                    break;
                default:
                    break;
            }
        }
    }

    public static void crearTicket(Integer corr) {
        // instancia a la clase ticket
        Ticket t = new Ticket();
        String Titulo = "CREADO";
        ticketEstado[corr][0] = 1;
        t.setEstado(Titulo);
        t.setId(corr);

        System.out.print("INRESE NIT: ");
        String Nit = new Scanner(System.in).nextLine();
        t.setNitUsuario(Nit);
        ticket[corr][0] = t.getNitUsuario();

        System.out.print("INRESE DESCRIPCION: ");
        String Desc = new Scanner(System.in).nextLine();
        t.setProblema(Desc);
        ticket[corr][1] = t.getProblema();

        t.mostrarTicket(Titulo);
        bitacotaTrigger(corr, Titulo, "", Nit, Desc);

    }

    public static void trabajarTicket(Integer corr) {
        System.out.println("*********************************************************");
        System.out.println("******************* LISTADO DE TICKETS ******************");
        for (int i = 0; i < ticket.length; i++) {
            if (ticket[i][1] != null) {
                System.out.println("** [" + i + "] " + ticket[i][1]);
            }
        }
        System.out.println("*********************************************************");
        System.out.print("SELECTIONE TICKET A TRABAJAR: ");
        Scanner entrada = new Scanner(System.in);
        int tick = entrada.nextInt();
        String buscaTick = ticket[tick][0];
        ticketEstado[corr][0] = 2;
        if (buscaTick == null) {
            if (tick == 0) {
                repiteMenu(1, 6, corr);
            } else {
                System.out.print("Ticket no encontrado");
                trabajarTicket(corr);
            }
        } else {
            bitacotaTrigger(corr, "Escala", "En mesa de Ayuda", "", "Ticket escalado a mesa de ayuda");
            ColaServicio c = new ColaServicio();
            c.setNitSoporte(buscaTick);
            c.setMensaje(buscaTick);
            c.setId(corr);
            c.estadoTicket(0);
            int tipoTrabajo = new Scanner(System.in).nextInt();
            switch (tipoTrabajo) {
                case 1:
                    ticketEstado[corr][0] = 22;
                    System.out.println("TICKET RESUELTO EN MESA DE AYUDA :-)");
                    System.out.print("Presione enter para continuar");
                    bitacotaTrigger(corr, "Resuelto", "En mesa de Ayuda", "", "TICKET RESUELTO EN MESA DE AYUDA :-)");
                    new Scanner(System.in).nextLine();
                    break;
                case 2:
                    ticketEstado[corr][0] = 3;
                    System.out.println("TICKET ESCALADO A SOPORTE TECNICO");
                    bitacotaTrigger(corr, "Escala", "En Soporte Tecnico", "", "TICKET ESCALADO A SOPORTE TECNICO");
                    c.estadoTicket(0);
                    switch (new Scanner(System.in).nextInt()) {
                        case 1:
                            ticketEstado[corr][0] = 33;
                            System.out.println("TICKET RESUELTO EN SOPORTE TECNICO :-)");
                            System.out.print("Presione enter para continuar");
                            bitacotaTrigger(corr, "Resuelto", "En Soporte Tecnico", "", "TICKET RESUELTO EN SOPORTE TECNICO :-)");
                            new Scanner(System.in).nextLine();
                            break;
                        case 2:
                            ticketEstado[corr][0] = 4;
                            System.out.println("TICKET ESCALADO A DESARROLLO");
                            bitacotaTrigger(corr, "Escala", "En Desarrollo", "", "TICKET ESCALADO A DESARROLLO");
                            c.estadoTicket(1);
                            if (new Scanner(System.in).nextInt() == 1) {
                                System.out.println("TICKET RESUELTO EN DESARROLLO :-)");
                                bitacotaTrigger(corr, "Resuelto", "En Desarrollo", "", "TICKET RESUELTO EN DESARROLLO :-)");
                            }
                            ticketEstado[corr][0] = 44;
                            break;
                        default:
                            System.out.println("OPCION NO VALIDA");
                            trabajarTicket(corr);
                            break;
                    }
                    break;
                case 3:
                    trabajarTicket(corr);
                    new Scanner(System.in).nextLine();
                    break;
                default:
                    break;
            }
        }
    }

    public static void reporteriaMenu() {
        System.out.println("*********************************************************");
        System.out.println("********************** REPORTERIA ***********************");
        System.out.println("*********************************************************");
        System.out.println("** [1] Listado de tickets                              **");
        System.out.println("** [2] Listado de tickets EN COLA                      **");
        System.out.println("** [3] Listado de tickets POR USUARIO                  **");
        System.out.println("** [4] Regresar                                        **");
        System.out.println("*********************************************************");
        switch (new Scanner(System.in).nextInt()) {
            case 1:
                ListadoTickets();
                break;
            case 2:
                ListadoEnCola();
                break;
            case 3:
                Listado();
                break;
            case 4:
                break;
            default:
                System.out.println("Opcion no valida");
                reporteriaMenu();
                break;
        }
    }

    public static void ListadoTickets() {
        String[] ticketEstadoLocal = new String[100];
        System.out.println("*********************************************************");
        System.out.println("[Id del ticket] | [SOPORTE] |  [NIT] | [FECHA Y HORA]");
        System.out.println("*********************************************************");

        for (int ii = 0; ii < ticketBitacora.length; ii++) {

            for (int i = 0; i < ticketBitacora[ii][i].length; i++) {
                if (ticketBitacora[ii][i][0] != null)
                {
                    System.out.println("[" + i + "] | [" + ticketBitacora[ii][i][1] + "] | [" + ticketBitacora[ii][i][3] + "] | [" + ticketBitacora[ii][i][5] + "]");
                }
            }

        }

        System.out.println("*********************************************************");
        System.out.println("Presione enter para regresar");
        new Scanner(System.in).nextLine();
        reporteriaMenu();
    }

    public static void ListadoEnCola() {
        String[] ticketEstadoLocal = new String[100];
        System.out.println("*********************************************************");
        System.out.println("[Id del ticket] | [Mensaje] |  [NIT] | [FECHA Y HORA]");
        System.out.println("*********************************************************");

        for (int ii = 0; ii < ticketBitacora.length; ii++) {

            for (int i = 0; i < ticketBitacora[ii][i].length; i++) {
                if (ticketBitacora[ii][i][0] != null && ticketBitacora[ii][i][0] == "CREADO")
                {
                    System.out.println("[" + i + "] | [" + ticketBitacora[ii][i][4] + "] | [" + ticketBitacora[ii][i][3] + "] | [" + ticketBitacora[ii][i][5] + "]");
                }
            }

        }

        System.out.println("*********************************************************");
        System.out.println("Presione enter para regresar");
        new Scanner(System.in).nextLine();
        reporteriaMenu();
    }

    public static void Listado() {
        System.out.println("*********************************************************");
        System.out.println("******************* LISTADO DE TICKETS ******************");
        for (int i = 0; i < ticket.length; i++) {
            if (ticket[i][1] != null) {
                String estado = "";
                if (ticketEstado[i][0] == 1)
                {
                    estado = "CREADO";
                }
                if (ticketEstado[i][0] > 1 && ticketEstado[i][0] < 10 )
                {
                    estado = "EN TRABAJO";
                }
                if (ticketEstado[i][0]  > 10)
                {
                    estado = "FINALIZADO";
                }

                System.out.println("** [" + i + "] [" + ticket[i][0]+"] ["+estado+"]");
            }
        }
        System.out.println("*********************************************************");
        System.out.println("Presione enter para regresar");
        new Scanner(System.in).nextLine();
        reporteriaMenu();
    }

    public static void bitacotaTrigger(Integer corr, String Titulo, String Soporte, String Nit, String Desc) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        ticketBitacora[corrBitacora][corr][0] = Titulo;
        ticketBitacora[corrBitacora][corr][1] = Soporte;
        ticketBitacora[corrBitacora][corr][3] = Nit;
        ticketBitacora[corrBitacora][corr][4] = Desc;
        ticketBitacora[corrBitacora][corr][5]= formatter.format(date);
        corrBitacora++;
    }
}


