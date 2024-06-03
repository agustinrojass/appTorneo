package clases;
import java.util.Scanner;
public class Menu {
    static Scanner scanner;
    public static void menu() {
        scanner = new Scanner(System.in);
        Torneo torneo = JsonUtiles.binToTorneo("Liga Profesional de Futbol 2024");
        System.out.println(torneo.devolverGoleadores());
        char boton;
        do {
            boton = mostrarBotones(torneo);
            switch(boton) {
                case '1': //muestra el fixture
                    verFixture(torneo);
                    break;
                case '2': //muestra un equipo
                    verEquipos(torneo);
                    break;
                case '3': //muestra los goleadores
                    verGoleadores(torneo);
                    break;
                case '4': //administra el sistema
                    administrarSistema(torneo);
                    break;
            }
        } while(boton != '0');
    }
    private static char mostrarBotones(Torneo torneo) {
        char boton;
        do {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(torneo);
            System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 1 ] - Mostrar fixture") + "\u001B[0m");
            System.out.println("\u001B[30;47m " + String.format("%-88s", "[ 2 ] - Mostrar equipo") + "\u001B[0m");
            System.out.println("\u001B[30;100m " + String.format("%-88s",  "[ 3 ] - Mostrar goleadores") + "\u001B[0m");
            System.out.println("\u001B[30;47m " + String.format("%-88s",  "[ 4 ] - Administrador") + "\u001B[0m");
            System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Salir") + "\u001B[0m");
            System.out.print("\u001B[30;47m " + "Ingrese el boton: "+ "\u001B[0m ");
            boton = scanner.next().charAt(0);
        } while(boton != '0' && boton != '1' && boton != '2' && boton != '3' && boton != '4');
        return boton;
    } //muestra los botones disponibles del menu
    private static void verFixture(Torneo torneo) {
        String boton;
        System.out.println();
        do {
            boton = mostrarBotonesFecha();
            System.out.println();
            if(!boton.equals("0")) {
                System.out.println(torneo.getFixture().get(Integer.parseInt(boton) - 1));
            }
        } while(!boton.equals("0"));
    } //muestra una fecha del fixture
    private static String mostrarBotonesFecha() {
        String boton;
        do {
            System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Volver") + "\u001B[0m ");
            System.out.print("\u001B[30;47m " + "Ingrese la fecha: " + "\u001B[0m ");
            boton = scanner.next();
        } while(!boton.equals("0") && !boton.equals("1") && !boton.equals("2") && !boton.equals("3") && !boton.equals("4") && !boton.equals("5") && !boton.equals("6") && !boton.equals("7") && !boton.equals("8") && !boton.equals("9") && !boton.equals("10") && !boton.equals("11") && !boton.equals("12") && !boton.equals("13") && !boton.equals("14") && !boton.equals("15") && !boton.equals("16") && !boton.equals("17") && !boton.equals("18") && !boton.equals("19") && !boton.equals("20") && !boton.equals("21") && !boton.equals("22") && !boton.equals("23") && !boton.equals("24") && !boton.equals("25") && !boton.equals("26") && !boton.equals("27"));
        return boton;
    } //pide el ingreso de una fecha del fixture que se va a mostrar
    private static void verEquipos(Torneo torneo) {
        String boton;
        System.out.println();
        do {
            boton = ingresarEquipoAMostrar(torneo);
            if(!boton.equals("0")) {
                System.out.println();
                System.out.println(torneo.getTabla().get((Integer.parseInt(boton)) - 1).devolverEquipoCompleto((Integer.parseInt(boton))));
                String nombre = torneo.getTabla().get((Integer.parseInt(boton)) - 1).getNombre();
                System.out.println(torneo.devolverFixtureEquipo(nombre));
            }
        } while(!boton.equals("0"));
    } //muestra un equipo
    private static String ingresarEquipoAMostrar(Torneo torneo) {
        String boton;
        do {
            System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Volver") + "\u001B[0m ");
            System.out.print("\u001B[30;47m " + "Ingrese el id del equipo: " + "\u001B[0m ");
            boton = scanner.next();
            if(!boton.equals("0")) {
                boton = String.valueOf(JsonUtiles.buscarIndice(torneo.getNombre(), Integer.parseInt(boton)) + 1);
            }
        } while(!boton.equals("0") && !boton.equals("1") && !boton.equals("2") && !boton.equals("3") && !boton.equals("4") && !boton.equals("5") && !boton.equals("6") && !boton.equals("7") && !boton.equals("8") && !boton.equals("9") && !boton.equals("10") && !boton.equals("11") && !boton.equals("12") && !boton.equals("13") && !boton.equals("14") && !boton.equals("15") && !boton.equals("16") && !boton.equals("17") && !boton.equals("18") && !boton.equals("19") && !boton.equals("20") && !boton.equals("21") && !boton.equals("22") && !boton.equals("23") && !boton.equals("24") && !boton.equals("25") && !boton.equals("26") && !boton.equals("27") && !boton.equals("28") );
        return boton;
    } //pide el ingreso del id del equipo que se va a mostrar
    private static void verGoleadores(Torneo torneo) {
        String boton;
        System.out.println();
        do {
            System.out.println(torneo.devolverGoleadores());
            System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Volver") + "\u001B[0m ");
            System.out.print("\u001B[30;47m " + "Ingrese el boton: " + "\u001B[0m ");
            boton = scanner.next();
        } while(!boton.equals("0"));
    } //muestra los goleadores
    private static void administrarSistema(Torneo torneo) {
        char boton;
        do {
            boton = mostrarBotonesAdministrador();
            switch(boton) {
                case '1': //actualiza la tabla
                    JsonUtiles.grabarTabla();
                    torneo = JsonUtiles.jsonToTorneo("apiStandings");
                    JsonUtiles.torneoToBin(torneo);
                    break;
                case '2': //actualiza los jugadores de un equipo
                    System.out.print("Ingrese el id del equipo: ");
                    JsonUtiles.grabarJugadores(scanner.nextInt());
                    torneo = JsonUtiles.jsonToTorneo("apiStandings");
                    JsonUtiles.torneoToBin(torneo);
                    break;
                case '3': //actualiza el fixture
                    JsonUtiles.grabarFixture();
                    torneo = JsonUtiles.jsonToTorneo("apiStandings");
                    JsonUtiles.torneoToBin(torneo);
                    break;
                case '4': //actualiza la tabla goleadores
                    JsonUtiles.grabarGoleadores();
                    torneo = JsonUtiles.jsonToTorneo("apiStandings");
                    JsonUtiles.torneoToBin(torneo);
                    break;
                case '5':
                    System.out.print("Ingrese el id del equipo: ");
                    JsonUtiles.grabarTecnicos(scanner.nextInt());
                    torneo = JsonUtiles.jsonToTorneo("apiStandings");
                    JsonUtiles.torneoToBin(torneo);
                    break;
            }
        } while(boton != '0');
    } //
    private static char mostrarBotonesAdministrador() {
        char boton;
        do {
            System.out.println("\n" + "\u001B[30;100m " + String.format("%-88s", "[ 1 ] - Actualizar tabla") + "\u001B[0m");
            System.out.println("\u001B[30;47m " + String.format("%-88s", "[ 2 ] - Actualizar jugadores de un club") + "\u001B[0m");
            System.out.println("\u001B[30;100m " + String.format("%-88s",  "[ 3 ] - Actualizar fixture") + "\u001B[0m");
            System.out.println("\u001B[30;47m " + String.format("%-88s",  "[ 4 ] - Actualizar tabla goleadores") + "\u001B[0m");
            System.out.println("\u001B[30;100m " + String.format("%-88s",  "[ 5 ] - Actualizar tecnicos de un club") + "\u001B[0m");
            System.out.println("\u001B[30;47m " + String.format("%-88s", "[ 0 ] - Salir") + "\u001B[0m");
            System.out.print("\u001B[30;100m " + "Ingrese el boton: " + "\u001B[0m ");
            boton = scanner.next().charAt(0);
        } while(boton != '0' && boton != '1' && boton != '2' && boton != '3' && boton != '4' && boton != '5');
        return boton;
    } //muestra los botones disponibles del menu administrador
}