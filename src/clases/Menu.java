package clases;
import excepciones.*;
import java.util.Scanner;
public class Menu {
    static Scanner scanner;
    public static void menu() {
        scanner = new Scanner(System.in);
        int liga;
        do {
            int temporada = 2023;
            String s = "";
            do {
                System.out.println("\u001B[30;100m " + String.format("%-88s", "appTorneo") + "\u001B[0m ");
                System.out.println("\u001B[30;47m " + String.format("%-88s", "[ 1 ] - Liga Profesional Argentina") + "\u001B[0m");
                System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 2 ] - Premier League") + "\u001B[0m");
                System.out.println("\u001B[30;47m " + String.format("%-88s", "[ 3 ] - La liga") + "\u001B[0m");
                System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 4 ] - Serie A") + "\u001B[0m");
                System.out.println("\u001B[30;47m " + String.format("%-88s", "[ 5 ] - Bundesliga") + "\u001B[0m");
                System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 6 ] - Ligue 1") + "\u001B[0m");
                System.out.println("\u001B[30;47m " + String.format("%-88s", "[ 0 ] - Salir") + "\u001B[0m");
                System.out.print("\u001B[30;100m " + "Ingrese el boton: " + "\u001B[0m ");
                String respuesta = scanner.next();
                liga = validarEntero(respuesta);
                if(liga < -1 || liga > 6) {
                    System.out.println();
                }
            } while(liga < 0 || liga > 6);
            switch(liga) {
                case 1:
                    liga = 128;
                    temporada = 2024;
                    s = "LigaProfesionalArgentina" + temporada;
                    break;
                case 2:
                    liga = 39;
                    s = "PremierLeague" + temporada;
                    break;
                case 3:
                    liga = 140;
                    s = "PrimeraDivisión" + temporada;
                    break;
                case 4:
                    liga = 135;
                    s = "SerieA" + temporada;
                    break;
                case 5:
                    liga = 78;
                    s = "Bundesliga" + temporada;
                    break;
                case 6:
                    liga = 61;
                    s = "Ligue1" + temporada;
                    break;
            }
            if(liga != 0) {
                try {
                    Torneo torneo = JsonUtiles.binToTorneo(s);
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
                                torneo = administrarSistema(liga, temporada);
                                break;
                        }
                    } while(boton != 0);
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                }
                catch(DescargaCanceladaException | ArchivoNoCerradoException ex) {
                    System.out.println("\n\u001B[30;41m " + ex.getMessage() + " \u001B[0m");
                }
            }
        } while(liga != 0);
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
            System.out.print("\u001B[30;47m " + "Ingrese el boton: " + "\u001B[0m ");
            String s = scanner.next();
            boton = validarEntero(s);
            if(boton < -1 || boton > 4) {
                System.out.println();
            }
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
        } while(boton < 0 || boton > torneo.getFixture().size());
        if(boton != 0) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(torneo.getFixture().get(boton - 1));
            volver();
        }
        System.out.println();
    } //muestra una fecha del fixture
    private static void verEquipos(Torneo torneo) {
        int boton;
        System.out.println();
        do {
            System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Volver") + "\u001B[0m ");
            System.out.print("\u001B[30;47m " + "Ingrese el id del equipo: " + "\u001B[0m ");
            String s = scanner.next();
            boton = validarEntero(s);
            if(boton != 0 && boton != -1) {
                try {
                    boton = JsonUtiles.buscarIndice(torneo, boton) + 1;
                }
                catch(IdInexistenteException ex) {
                    System.out.println();
                    System.out.println("\u001B[30;41m " + ex.getMessage() + " \u001B[0m");
                    System.out.println();
                    boton = -1;
                }
            }
        } while(boton < 0  || boton > 28);
        if(boton != 0) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(torneo.getTabla().get(boton - 1).devolverEquipoCompleto(boton));
            int id = torneo.getTabla().get(boton - 1).getIdEquipo();
            System.out.println(torneo.devolverFixtureEquipo(id));
            volver();
        }
    } //muestra un equipo
    private static void verGoleadores(Torneo torneo) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(torneo.devolverGoleadores());
        volver();
    } //muestra los goleadores
    private static Torneo administrarSistema(int liga, int temporada) {
        String contrasena;
        do {
            System.out.print("\u001B[30;100m " + "Ingrese la contrasena: " + "\u001B[0m ");
            contrasena = scanner.next();
            if(!contrasena.equals("1994")) {
                System.out.println("\u001B[30;41m" + "La contraseña es incorrecta" + ".\u001B[0m\n");
            }
        } while(!contrasena.equals("1994"));
        int boton;
        Torneo torneo = null;
        try {
            torneo = JsonUtiles.jsonToTorneo(liga, temporada);
            System.out.println();
            do {
                boton = mostrarBotonesAdministrador();
                switch(boton) {
                    case 1: //actualiza el .json de la tabla
                        try {
                            JsonUtiles.grabarTabla(liga, temporada);
                        }
                        catch(DescargaCanceladaException ex) {
                            System.out.println("\n\u001B[30;41m " + ex.getMessage() + " \u001B[0m");
                        }
                        break;
                    case 2: //actualiza el .json del fixture
                        try {
                            JsonUtiles.grabarFixture(liga, temporada);
                        }
                        catch(DescargaCanceladaException ex) {
                            System.out.println("\n\u001B[30;41m " + ex.getMessage() + " \u001B[0m");
                        }
                        break;
                    case 3: //actualiza el .json de la tabla goleadores
                        try {
                            JsonUtiles.grabarGoleadores(liga, temporada);
                        }
                        catch(DescargaCanceladaException ex) {
                            System.out.println("\n\u001B[30;41m " + ex.getMessage() + " \u001B[0m");
                        }
                        break;
                    case 4: //actualiza el .json de los jugadores de un equipo
                        do {
                            System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Volver") + "\u001B[0m ");
                            System.out.print("\u001B[30;47m " + "Ingrese el id del equipo: " + "\u001B[0m ");
                            String s = scanner.next();
                            boton = validarEntero(s);
                            if(boton != 0 && boton != -1) {
                                try {
                                    JsonUtiles.buscarIndice(torneo, boton);
                                }
                                catch(IdInexistenteException ex) {
                                    System.out.println("\n\u001B[30;41m " + ex.getMessage() + " \u001B[0m");
                                    boton = -1;
                                }
                                System.out.println();
                            }
                        } while(boton == -1);
                        if(boton != 0) {
                            try {
                                JsonUtiles.grabarJugadores(boton, liga, temporada);
                            }
                            catch(DescargaCanceladaException ex) {
                                System.out.println("\n\u001B[30;41m " + ex.getMessage() + " \u001B[0m");
                            }
                        }
                        boton = 4;
                        break;
                    case 5: //actualiza el .json de los tecnicos de un equipo
                        do {
                            System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Volver") + "\u001B[0m ");
                            System.out.print("\u001B[30;47m " + "Ingrese el id del equipo: " + "\u001B[0m ");
                            String s = scanner.next();
                            boton = validarEntero(s);
                            if(boton != 0 && boton != -1) {
                                try {
                                    JsonUtiles.buscarIndice(torneo, boton);
                                }
                                catch(IdInexistenteException ex) {
                                    System.out.println("\n\u001B[30;41m " + ex.getMessage() + " \u001B[0m");
                                    boton = -1;
                                }
                            }
                        } while(boton == -1);
                        if(boton != 0) {
                            try {
                                JsonUtiles.grabarTecnicos(boton, liga, temporada);
                            }
                            catch(DescargaCanceladaException ex) {
                                System.out.println("\n\u001B[30;41m " + ex.getMessage() + " \u001B[0m");
                            }
                        }
                        boton = 5;
                        break;
                    case 6: //exporta el torneo a un .json
                        try {
                            JsonUtiles.exportarTorneo(torneo.exportarJson());
                        }
                        catch(JsonErrorException | CargaCanceladaException ex) {
                            System.out.println("\n\u001B[30;41m " + ex.getMessage() + " \u001B[0m");
                        }
                        break;
                    case 7: //exporta un equipo a un .json
                        do {
                            System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Volver") + "\u001B[0m ");
                            System.out.print("\u001B[30;47m " + "Ingrese el id del equipo: " + "\u001B[0m ");
                            String s = scanner.next();
                            boton = validarEntero(s);
                            if(boton != 0 && boton != -1) {
                                try {
                                    boton = JsonUtiles.buscarIndice(torneo, boton);
                                }
                                catch(IdInexistenteException ex) {
                                    System.out.println("\n\u001B[30;41m " + ex.getMessage() + " \u001B[0m");
                                    boton = -1;
                                }
                                System.out.println();
                            }
                        } while(boton == -1);
                        if(boton != 0) {
                            try {
                                JsonUtiles.exportarEquipo(torneo.getTabla().get(boton).exportarJson());
                            }
                            catch(JsonErrorException | CargaCanceladaException ex) {
                                System.out.println("\n\u001B[30;41m " + ex.getMessage() + " \u001B[0m");
                            }
                        }
                        break;
                }
                if(boton != 0 && boton != 6) {
                    try {
                        torneo = JsonUtiles.jsonToTorneo(liga, temporada);
                        JsonUtiles.torneoToBin(torneo);
                    }
                    catch(JsonErrorException | DescargaCanceladaException | CargaCanceladaException | ArchivoNoCerradoException ex) {
                        System.out.println("\n\u001B[30;41m " + ex.getMessage() + " \u001B[0m");
                    }
                }
            } while(boton != 0);
        }
        catch(JsonErrorException | DescargaCanceladaException ex) {
            System.out.println("\n\u001B[30;41m " + ex.getMessage() + " \u001B[0m");
        }
        return torneo;
    } //administra el sistema
    private static int mostrarBotonesAdministrador() {
        int boton;
        do {
            System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 1 ] - Actualizar tabla") + "\u001B[0m");
            System.out.println("\u001B[30;47m " + String.format("%-88s", "[ 2 ] - Actualizar fixture") + "\u001B[0m");
            System.out.println("\u001B[30;100m " + String.format("%-88s",  "[ 3 ] - Actualizar tabla goleadores") + "\u001B[0m");
            System.out.println("\u001B[30;47m " + String.format("%-88s",  "[ 4 ] - Actualizar jugadores de un club") + "\u001B[0m");
            System.out.println("\u001B[30;100m " + String.format("%-88s",  "[ 5 ] - Actualizar tecnicos de un club") + "\u001B[0m");
            System.out.println("\u001B[30;47m " + String.format("%-88s",  "[ 6 ] - Exportar torneo a .json") + "\u001B[0m");
            System.out.println("\u001B[30;100m " + String.format("%-88s",  "[ 7 ] - Exportar un equipo a .json") + "\u001B[0m");
            System.out.println("\u001B[30;47m " + String.format("%-88s", "[ 0 ] - Salir") + "\u001B[0m");
            System.out.print("\u001B[30;100m " + "Ingrese el boton: " + "\u001B[0m ");
            String s = scanner.next();
            boton = validarEntero(s);
            if(boton != 0 && boton != -1) {
                System.out.println();
            }
        } while(boton < 0 || boton > 7);
        return boton;
    } //muestra los botones disponibles del menu administrador
    private static int validarEntero(String s) {
        int boton = -1;
        try {
            boton = Integer.parseInt(s);
        }
        catch(NumberFormatException ex) {
            System.out.println();
            System.out.println("\u001B[30;41m " + "Ingrese un entero. " + "\u001B[0m");
            System.out.println();
        }
        return boton;
    } //valida que un string sea un entero
    private static void volver() {
        int boton;
        do {
            System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 0 ] - Volver") + "\u001B[0m ");
            System.out.print("\u001B[30;47m " + "Ingrese el boton: " + "\u001B[0m ");
            String s = scanner.next();
            boton = validarEntero(s);
            if(boton != 0 && boton != -1) {
                System.out.println();
            }
        } while(boton != 0);
    }
}