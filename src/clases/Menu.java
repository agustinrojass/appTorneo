package clases;
import java.util.Scanner;
public class Menu {
    static Scanner scanner;
    public static void menu() {
        scanner = new Scanner(System.in);
        Torneo torneo = JsonUtiles.binToTorneo();
        //Torneo torneo = JsonUtiles.jsonToTorneo();
        //JsonUtiles.torneoToBin(torneo);
        int boton;
        do {
            boton = mostrarBotones(torneo);
            switch(boton) {
                case 1: //muestra el fixture
                    verFixture(torneo);
                    break;
                case 2: //muestra un equipo
                    verEquipos(torneo);
                    break;
                case 3: //muestra los goleadores
                    verGoleadores(torneo);
                    break;
                case 4: //administra el sistema
                    torneo = administrarSistema();
                    break;
            }
        } while(boton != 0);
    }
    private static int mostrarBotones(Torneo torneo) {
        int boton;
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(torneo);
        do {
            System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 1 ] - Mostrar fixture") + "\u001B[0m");
            System.out.println("\u001B[30;47m " + String.format("%-88s", "[ 2 ] - Mostrar equipo") + "\u001B[0m");
            System.out.println("\u001B[30;100m " + String.format("%-88s",  "[ 3 ] - Mostrar goleadores") + "\u001B[0m");
            System.out.println("\u001B[30;47m " + String.format("%-88s",  "[ 4 ] - Administrador") + "\u001B[0m");
            System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Salir") + "\u001B[0m");
            System.out.print("\u001B[30;47m " + "Ingrese el boton: "+ "\u001B[0m ");
            String s = scanner.next();
            boton = validarEntero(s);
            System.out.println();
        } while(boton < 0 || boton > 4);
        return boton;
    } //muestra los botones disponibles del menu
    private static void verFixture(Torneo torneo) {
        int boton;
        do {
            System.out.println("\n\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Volver") + "\u001B[0m ");
            System.out.print("\u001B[30;47m " + "Ingrese la fecha: " + "\u001B[0m ");
            String s = scanner.next();
            boton = validarEntero(s);
        } while(boton < 0  || boton > 27);
        if(boton != 0) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(torneo.getFixture().get(boton - 1));
            do {
                System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Volver") + "\u001B[0m ");
                System.out.print("\u001B[30;47m " + "Ingrese el boton: " + "\u001B[0m ");
                String s = scanner.next();
                boton = validarEntero(s);
                System.out.println();
            } while(boton != 0);
        }
        System.out.println();
    } //muestra una fecha del fixture
    private static void verEquipos(Torneo torneo) {
        int boton;
        do {
            System.out.println("\n\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Volver") + "\u001B[0m ");
            System.out.print("\u001B[30;47m " + "Ingrese el id del equipo: " + "\u001B[0m ");
            String s = scanner.next();
            boton = validarEntero(s);
            if(boton != 0 && boton != -1) {
                boton = JsonUtiles.buscarIndice(torneo.getNombre(), boton) + 1;
                if(boton == -1) {
                    System.out.println("\n\u001B[30;41m " + "Id inexistente. " + "\u001B[0m");
                }
            }
        } while(boton < 0  || boton > 28);
        if(boton != 0) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(torneo.getTabla().get(boton - 1).devolverEquipoCompleto(boton));
            String nombre = torneo.getTabla().get((boton) - 1).getNombre();
            System.out.println(torneo.devolverFixtureEquipo(nombre));
            do {
                System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Volver") + "\u001B[0m ");
                System.out.print("\u001B[30;47m " + "Ingrese el boton: " + "\u001B[0m ");
                String s = scanner.next();
                boton = validarEntero(s);
                System.out.println();
            } while(boton != 0);
        }
    } //muestra un equipo
    private static void verGoleadores(Torneo torneo) {
        int boton;
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(torneo.devolverGoleadores());
        do {
            System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Volver") + "\u001B[0m ");
            System.out.print("\u001B[30;47m " + "Ingrese el boton: " + "\u001B[0m ");
            String s = scanner.next();
            boton = validarEntero(s);
            System.out.println();
        } while(boton != 0);
    } //muestra los goleadores
    private static Torneo administrarSistema() {
        int boton;
        Torneo torneo = JsonUtiles.jsonToTorneo();
        do {
            boton = mostrarBotonesAdministrador();
            switch(boton) {
                case 1: //actualiza el .json de la tabla
                    JsonUtiles.grabarTabla();
                    break;
                case 2: //actualiza el .json del fixture
                    JsonUtiles.grabarFixture();
                    break;
                case 3: //actualiza el .json de la tabla goleadores
                    JsonUtiles.grabarGoleadores();
                    break;
                case 4: //actualiza el .json de los jugadores de un equipo
                    System.out.print("Ingrese el id del equipo: ");
                    JsonUtiles.grabarJugadores(scanner.nextInt());
                    break;
                case 5: //actualiza el .json de los tecnicos de un equipo
                    System.out.print("Ingrese el id del equipo: ");
                    JsonUtiles.grabarTecnicos(scanner.nextInt());
                    break;
                case 6: //exporta el torneo a un .json
                    JsonUtiles.grabarTorneo(torneo.exportarJson());
                    break;
            }
            if(boton != 0) {
                torneo = JsonUtiles.jsonToTorneo();
                JsonUtiles.torneoToBin(torneo);
            }
        } while(boton != 0);
        return torneo;
    } //administra el sistema
    private static int mostrarBotonesAdministrador() {
        int boton;
        do {
            System.out.println("\n" + "\u001B[30;100m " + String.format("%-88s", "[ 1 ] - Actualizar tabla") + "\u001B[0m");
            System.out.println("\u001B[30;47m " + String.format("%-88s", "[ 2 ] - Actualizar fixture") + "\u001B[0m");
            System.out.println("\u001B[30;100m " + String.format("%-88s",  "[ 3 ] - Actualizar tabla goleadores") + "\u001B[0m");
            System.out.println("\u001B[30;47m " + String.format("%-88s",  "[ 4 ] - Actualizar jugadores de un club") + "\u001B[0m");
            System.out.println("\u001B[30;100m " + String.format("%-88s",  "[ 5 ] - Actualizar tecnicos de un club") + "\u001B[0m");
            System.out.println("\u001B[30;47m " + String.format("%-88s",  "[ 5 ] - Exportar torneo a .json") + "\u001B[0m");
            System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Salir") + "\u001B[0m");
            System.out.print("\u001B[30;47m " + "Ingrese el boton: " + "\u001B[0m ");
            String s = scanner.next();
            boton = validarEntero(s);
        } while(boton < 0 || boton > 6);
        return boton;
    } //muestra los botones disponibles del menu administrador
    private static int validarEntero(String s) {
        int boton = -1;
        try {
            boton = Integer.parseInt(s);
        }
        catch(NumberFormatException ex) {
            System.out.println("\n\u001B[30;41m " + "Ingrese un entero. " + "\u001B[0m");
        }
        return boton;
    } //valida que un string sea un entero
}