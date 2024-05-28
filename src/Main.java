import Clases.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

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
}