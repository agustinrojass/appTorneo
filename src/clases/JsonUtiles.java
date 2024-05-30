package clases;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonUtiles {
    public static JSONArray informacionApiToJsonArrayTabla(String informacionApi) {
        JSONObject jsonObj;
        JSONArray jsA;
        try {
            assert informacionApi != null;
            jsonObj = new JSONObject(informacionApi);
            jsA = jsonObj.getJSONArray("response").getJSONObject(0).getJSONObject("league").getJSONArray("standings").getJSONArray(0);
        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }
        return jsA;
    } //pasa de string a jsonarray
    public static JSONArray informacionApiToJsonArrayJugadores(String informacionAPI) {
        JSONObject jsonObj;
        JSONArray jsonResponse;
        try {
            assert informacionAPI != null;
            jsonObj = new JSONObject(informacionAPI);
            jsonResponse = jsonObj.getJSONArray("response");
        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }
        return jsonResponse;
    } //pasa de string a jsonarray
    public static void grabar(JSONArray array, String archivo) {
        try {
            FileWriter file = new FileWriter("json\\" + archivo +".json");
            file.write(array.toString());
            file.flush();
            file.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    } //pasa de jsonarray a .json
    public static void grabar(JSONObject jsonObject, String archivo) {
        try {
            FileWriter file = new FileWriter("json\\" + archivo + ".json");
            file.write(jsonObject.toString());
            file.flush();
            file.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    } //pasa de jsonobject a .json
    public static String leer(String archivo) {
        String s = "";
        try {
            s = new String(Files.readAllBytes(Paths.get("json\\" + archivo + ".json")));
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return s;
    } //pasa de .json a string
    public static void grabarTabla() {
        JSONArray jsonTabla = JsonUtiles.informacionApiToJsonArrayTabla(ApiFootball.conectarApi("https://v3.football.api-sports.io/standings?league=128&season=2024", "d0745dc142c1ed133294934d257cb473"));
        JsonUtiles.grabar(jsonTabla, "apiStandings");
    } //descarga los datos de la api y los guarda en .json
    public static void grabarJugadores(int id) {
        JSONArray jsonEquipo = JsonUtiles.informacionApiToJsonArrayJugadores(ApiFootball.conectarApi("https://v3.football.api-sports.io/players/squads?team=" + id, "d0745dc142c1ed133294934d257cb473"));
        try {
            JsonUtiles.grabar(jsonEquipo, "api" + jsonEquipo.getJSONObject(0).getJSONObject("team").getString("name").replace(" ", "") + "Squad");
        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }
    } //descarga los datos de la api y los guarda en .json
    public static void jsonToTorneo(Torneo torneo, String archivo) {
        try {
            JSONArray array = new JSONArray(JsonUtiles.leer(archivo));
            for(int i = 0; i < array.length(); i++) {
                torneo.agregarEquipo(new Equipo(array.getJSONObject(i).getJSONObject("team").getInt("id"), //id
                        array.getJSONObject(i).getJSONObject("team").getString("name"), //nombre
                        jsonToJugadores(array.getJSONObject(i).getJSONObject("team").getString("name").replace(" ", "")), //jugadores
                        array.getJSONObject(i).getInt("points"), //puntos
                        array.getJSONObject(i).getJSONObject("all").getInt("played"), //partidos jugados
                        array.getJSONObject(i).getJSONObject("all").getInt("win"), //partidos ganados
                        array.getJSONObject(i).getJSONObject("all").getInt("draw"), //partidos empatados
                        array.getJSONObject(i).getJSONObject("all").getInt("lose"), //partidos perdidos
                        array.getJSONObject(i).getInt("goalsDiff"), //diferencia de goles
                        array.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("for"), //goles a favor
                        array.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("against"))); //goles en contra
            }
        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }
    } //pasa de .json a torneo
    public static ArrayList<Jugador> jsonToJugadores(String archivo) {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        try {
            JSONArray jsonJugadores = new JSONArray(JsonUtiles.leer("api" + archivo + "Squad"));
            for(int i = 0; i < jsonJugadores.getJSONObject(0).getJSONArray("players").length(); i++)
            {
                if(!jsonJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).isNull("number") && !jsonJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(0).isNull("age"))
                {
                    Jugador ju = new Jugador(jsonJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).getString("name"), //nombre
                            jsonJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).getInt("age"), //edad
                            jsonJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).getString("position"), //posicion
                            jsonJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).getInt("number")); //numero
                    jugadores.add(ju);
                }
            }
        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }
        return jugadores;
    } //pasa de .json a arraylist de jugadores
    public static void torneoToBin(Torneo torneo) {
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("bin\\" + torneo.getNombre().replace(" ", "") + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(torneo);
        }
        catch(IOException ex) {
            throw new RuntimeException(ex);
        }
        finally {
            try {
                assert objectOutputStream != null;
                objectOutputStream.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    } //pasa de torneo a .bin
    public static Torneo binToTorneo(String nombre) {
        Torneo torneo;
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("bin\\" + nombre.replace(" ", "") + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            torneo = (Torneo) objectInputStream.readObject();
        }
        catch(IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        finally {
            try {
                assert objectInputStream != null;
                objectInputStream.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return torneo;
    } //pasa de .bin a torneo
}
