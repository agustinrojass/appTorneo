package clases;
import java.util.Scanner;
public class Menu {
    static Scanner scanner;
    public static void menu() {
        scanner = new Scanner(System.in);
        Torneo torneo = JsonUtiles.binToTorneo("Liga Profesional de Futbol 2024");
        int boton;
        do {
            do {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println(torneo);
                System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 1 ] - Actualizar tabla") + "\u001B[0m");
                System.out.println("\u001B[30;47m " + String.format("%-88s", "[ 2 ] - Actualizar jugadores de un club") + "\u001B[0m");
                System.out.println("\u001B[30;100m " + String.format("%-88s",  "[ 0 ] - Salir") + "\u001B[0m");
                System.out.print("Ingrese el boton: ");
                boton = scanner.nextInt();
            } while(boton != 0 && boton != 1 && boton != 2);
            switch(boton) {
                case 1:
                    JsonUtiles.grabarTabla();
                    JsonUtiles.jsonToTorneo(torneo, "apiStandings");
                    JsonUtiles.torneoToBin(torneo);
                    break;
                case 2:
                    System.out.print("Ingrese el id del equipo: ");
                    JsonUtiles.grabarJugadores(scanner.nextInt());
                    JsonUtiles.jsonToTorneo(torneo, "apiStandings");
                    JsonUtiles.torneoToBin(torneo);
                    break;
            }
        } while(boton != 0);
    }
}