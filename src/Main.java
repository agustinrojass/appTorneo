import Clases.*;
import org.json.JSONArray;
import java.io.IOException;
import java.util.*;

public class Main {
    static Scanner scanner;
    public static void main(String[] args) {
        //agus
        menu();
        //jonas
        /*
        String urlStringTabla = "https://v3.football.api-sports.io/standings?league=128&season=2024";
         String apiKey = "e322d3134e96e5ca6f13792f4df66ed5";     //key personal de la API

        String informacionAPITabla = null;

        informacionAPITabla = APIFootball.conectarAPI(urlStringTabla, apiKey);

        JSONArray jsonTabla = JsonUtiles.informacionToJsonTabla(informacionAPITabla);

                              //Guardar archivo api
        JSONObject object = null;
        try {
            object = new JSONObject(informacionAPITabla);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String nombreArchivoPrincipal = "ArchivoPrincipal";
        try {
            JsonUtiles.grabarArchivo(object, nombreArchivoPrincipal);       //Guardar archivo api
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        String nombreArchivoPrincipal = "ArchivoPrincipal";
        String informacion;
        try {
            informacion = JsonUtiles.leerArchivo(nombreArchivoPrincipal);           //leo el archivo de la api .json guardado
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JSONArray jsonTabla = JsonUtiles.informacionToJsonTabla(informacion);       //connvierto la informacion del archivo a un jsonArray para trabajar el torneo
        Torneo torneo = new Torneo("Liga Profesional de Futbol 2024");
        ArrayList<Equipo> equipos = new ArrayList<>();
        JsonUtiles.jsonToEquipos(torneo, jsonTabla, equipos);
        System.out.println(torneo);
        /*
        try {
            JsonUtiles.grabarPlantelToArchivo(torneo);            //NO USAR MAS. SOLO UTILIZADA PARA GUARDAR LOS EQUIPOS EN ARCHIVOS ASI NO GASTAR EL NUMERO DE LLAMADOS A LA API POR DIA -> 10 por minuto
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        Iterator it;                                                //Muestro jugadores de todos los equipos
        for (int i = 0; i < torneo.getTabla().size(); i++)         //Hago un for para recorrer los equipos
        {
            System.out.println();
            System.out.println();
            HashMap<Integer, Jugador> jugadores = torneo.getTabla().get(i).getJugadores();      //agarro el hashMap del equipo en posicion i en la tabla
            if (jugadores != null) {

                it = jugadores.entrySet().iterator();
                System.out.println(torneo.getTabla().get(i).getNombre());
                while (it.hasNext()) {
                    Map.Entry me = (Map.Entry) it.next();
                    System.out.println(me.getValue().toString());               //muestro el valor del hashMap (Jugador)
                }
            } else {
                System.out.println("El conjunto de jugadores es nulo para el equipo " + torneo.getTabla().get(i).getNombre());      //comprobacion por si hay error de la api
            }
        }
    }
    public static void menu() {
        scanner = new Scanner(System.in);
        Torneo torneo = new Torneo("Liga Profesional de Futbol 2024");
        JSONArray jsonTabla;
        JSONArray jsonJugadores;
        JsonUtiles.descargarTablaBin(torneo);
        JsonUtiles.descargarTodosJugadoresBin(torneo);
        int boton;
        do {
            do {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println(torneo);
                System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 1 ] - Actualizar tabla") + "\u001B[0m");
                System.out.println("\u001B[30;47m " + String.format("%-88s", "[ 2 ] - Actualizar jugadores") + "\u001B[0m");
                System.out.println("\u001B[30;100m " + String.format("%-88s", "[ 3 ] - Ver equipo") + "\u001B[0m");
                System.out.println("\u001B[30;47m " + String.format("%-88s", "[ 0 ] - Salir") + "\u001B[0m");
                System.out.print("Ingrese el boton: ");
                boton = scanner.nextInt();
            } while(boton != 0 && boton != 1 && boton != 2 && boton != 3);
            switch(boton) {
                case 1:
                    try {
                        String apiUrl = "https://v3.football.api-sports.io/standings?league=128&season=2024";
                        String apiKey = "d0745dc142c1ed133294934d257cb473"; //apikey personal de agus
                        jsonTabla = JsonUtiles.informacionApiToJsonArrayTabla(ApiFootball.conectarApi(apiUrl, apiKey));
                        JsonUtiles.actualizarTablaBin(jsonTabla);
                        JsonUtiles.descargarTablaBin(torneo);
                    }
                    catch(IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el id del equipo: ");
                    //String equipo = scanner.next();
                    int id = scanner.nextInt();
                    try {
                        //String apiUrl = "https://v3.football.api-sports.io/players/squads?team=" + JsonUtiles.devloverId(equipo);
                        String apiUrl = "https://v3.football.api-sports.io/players/squads?team=" + id;
                        String apiKey = "d0745dc142c1ed133294934d257cb473";
                        jsonJugadores = JsonUtiles.informacionApiToJsonArrayJugadores(ApiFootball.conectarApi(apiUrl, apiKey));
                        JsonUtiles.actualizarJugadoresBin(jsonJugadores);
                        JsonUtiles.descargarTodosJugadoresBin(torneo);
                    }
                    catch(IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 3:
                    //JsonUtiles.descargarJugadoresBin();
                    //System.out.print("Ingrese el id del equipo que quiere ver: ");
                    //int id = scanner.nextInt();
                    //metodo()
                    break;
            }
        } while(boton != 0);
        scanner.close();
    }
}