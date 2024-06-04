package clases;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class JsonUtiles {
    //
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
    ////////// unificar las 4 de abajo (todavia no)
    public static JSONArray informacionApiToJsonArrayJugadores(String informacionApi) {
        JSONObject jsonObj;
        JSONArray jsA;
        try {
            assert informacionApi != null;
            jsonObj = new JSONObject(informacionApi);
            jsA = jsonObj.getJSONArray("response");
        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }
        return jsA;
    } //pasa de string a jsonarray //podria usarse la misma que informacionApiToJsonArrayFixture
    public static JSONArray informacionApiToJsonArrayFixture(String informacionApi) {
        JSONObject jsonObj;
        JSONArray jsA;
        try {
            assert informacionApi != null;
            jsonObj = new JSONObject(informacionApi);
            jsA = jsonObj.getJSONArray("response");
        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }
        return jsA;
    } //pasa de string a jsonarray //podria usarse la misma que informacionApiToJsonArrayJugadores
    public static JSONArray informacionApiToJsonArrayTecnicos(String informacionApi) {
        JSONObject jsonObj;
        JSONArray jsA;
        try {
            assert informacionApi != null;
            jsonObj = new JSONObject(informacionApi);
            jsA = jsonObj.getJSONArray("response");
        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }
        return jsA;
    } //pasa de string a jsonarray
    public static JSONArray informacionApiToJsonArrayGoleadores(String informacionApi) {
        JSONObject jsonObj;
        JSONArray jsA;
        try {
            assert informacionApi != null;
            jsonObj = new JSONObject(informacionApi);
            jsA = jsonObj.getJSONArray("response");
        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }
        return jsA;
    } //pasa de string a jsonarray
    //
    public static void grabar(JSONArray jsonArray, String archivo) {
        try {
            FileWriter file = new FileWriter("json\\" + archivo +".json");
            file.write(jsonArray.toString());
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
    } //pasa de jsonobject a .json //por ahora no se usa
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
    //
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
    public static void grabarFixture() {
        JSONArray jsonFixture = JsonUtiles.informacionApiToJsonArrayFixture(ApiFootball.conectarApi("https://v3.football.api-sports.io/fixtures?league=128&season=2024", "d0745dc142c1ed133294934d257cb473"));
        JsonUtiles.grabar(jsonFixture, "apiFixture");
    } //descarga los datos de la api y los guarda en .json
    public static void grabarTecnicos(int id) {
        JSONArray jsonEquipo = JsonUtiles.informacionApiToJsonArrayTecnicos(ApiFootball.conectarApi("https://v3.football.api-sports.io/coachs?team=" + id, "d0745dc142c1ed133294934d257cb473"));
        try {
            JsonUtiles.grabar(jsonEquipo, "api" + jsonEquipo.getJSONObject(0).getJSONObject("team").getString("name").replace(" ", "") + "Coaches");
        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }
    } //descarga los datos de la api y los guarda en .json
    public static void grabarGoleadores() {
        JSONArray jsonFixture = JsonUtiles.informacionApiToJsonArrayGoleadores(ApiFootball.conectarApi("https://v3.football.api-sports.io/players/topscorers?league=128&season=2024", "d0745dc142c1ed133294934d257cb473"));
        JsonUtiles.grabar(jsonFixture, "apiTopScorers");
    } //descarga los datos de la api y los guarda en .json
    //
    public static Torneo jsonToTorneo(String archivo) {
        Torneo torneo = new Torneo("Liga Profesional de Futbol 2024");
        try {
            JSONArray array = new JSONArray(JsonUtiles.leer(archivo));
            for(int i = 0; i < array.length(); i++) {
                //Tecnico t = new Tecnico();
                //usar el constructor de equipo con el tecnico
                //no coinciden los nombres de los .json
                torneo.agregarEquipo(new Equipo(array.getJSONObject(i).getJSONObject("team").getInt("id"), //id
                        array.getJSONObject(i).getJSONObject("team").getString("name"), //nombre
                        jsonToJugadores(array.getJSONObject(i).getJSONObject("team").getString("name").replace(" ", "")), //jugadores
                        array.getJSONObject(i).getInt("points"), //puntos
                        array.getJSONObject(i).getJSONObject("all").getInt("played"), //partidos jugados
                        array.getJSONObject(i).getJSONObject("all").getInt("win"), //partidos ganados
                        array.getJSONObject(i).getJSONObject("all").getInt("draw"), //partidos empatados
                        array.getJSONObject(i).getJSONObject("all").getInt("lose"), //partidos perdidos
                        array.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("for"), //goles a favor
                        array.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("against"), //goles en contra
                        array.getJSONObject(i).getInt("goalsDiff"))); //diferencia de goles
            }
            torneo.agregarFixture(jsonToFixture("apiFixture")); //fixture
            torneo.agregarGoleadores(jsonToGoleadores("apiTopScorers", torneo)); //goleadores
        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }
        return torneo;
    } //pasa de .json a torneo
    public static Contenedor<Jugador> jsonToJugadores(String archivo) {
        Contenedor<Jugador> jugadores = new Contenedor<>();
        try {
            JSONArray jsonJugadores = new JSONArray(JsonUtiles.leer("api" + archivo + "Squad"));
            for(int i = 0; i < jsonJugadores.getJSONObject(0).getJSONArray("players").length(); i++) {
                if(!jsonJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).isNull("number") &&
                        !jsonJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(0).isNull("age")) {
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
    } //pasa de .json a contenedor de jugadores
    public static Contenedor<Fecha> jsonToFixture(String archivo) {
        Contenedor<Fecha> fixture = new Contenedor<>();
        try {
            JSONArray jsonFixture = new JSONArray(JsonUtiles.leer(archivo));
            int i = 0;
            for(int j = 0; j < jsonFixture.length() / 14; j++) { //las 14 fechas
                Contenedor<PartidoFutbol> partidos = new Contenedor<>();
                for(int k = 0; k < 14 && i < jsonFixture.length(); k++, i++) { //todos los partidos y me fijo de cortar cada 14 fechas
                    Equipo local = new Equipo(jsonFixture.getJSONObject(i).getJSONObject("teams").getJSONObject("home").getInt("id"),
                            jsonFixture.getJSONObject(i).getJSONObject("teams").getJSONObject("home").getString("name"));
                    Equipo visitante = new Equipo(jsonFixture.getJSONObject(i).getJSONObject("teams").getJSONObject("away").getInt("id"),
                            jsonFixture.getJSONObject(i).getJSONObject("teams").getJSONObject("away").getString("name"));
                    int golesL = -1;
                    int golesV = -1;
                    if(!(jsonFixture.getJSONObject(i).getJSONObject("goals").isNull("home")) &&
                            !(jsonFixture.getJSONObject(i).getJSONObject("goals").isNull("away"))) {
                        golesL = jsonFixture.getJSONObject(i).getJSONObject("goals").getInt("home");
                        golesV = jsonFixture.getJSONObject(i).getJSONObject("goals").getInt("away");
                    }
                    partidos.add(new PartidoFutbol(local, visitante, golesL, golesV));
                }
                fixture.add(new Fecha(j+1, partidos));
            }
        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }
        return fixture;
    } //pasa de .json a contenedor de fechas
    public static Tecnico jsonToTecnico(String archivo) {
        Tecnico tecnico = new Tecnico();
        try {
            JSONArray jsonTecnicos = new JSONArray(JsonUtiles.leer(archivo));
            String nombre = jsonTecnicos.getJSONObject(0).getString("name");
            int edad = jsonTecnicos.getJSONObject(0).getInt("age");
            Contenedor<String> trayectoria = new Contenedor<>();
            for(int i = 0; i < jsonTecnicos.length(); i++) {
                trayectoria.add(jsonTecnicos.getJSONObject(0).getJSONArray("career").getJSONObject(i).getJSONObject("team").getString("name"));
            }
        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
        return tecnico;
    } //pasa de .json a tecnico
    public static Contenedor<Jugador> jsonToGoleadores(String archivo, Torneo torneo) {
        Contenedor<Jugador> goleadores = new Contenedor<>();
        try {
            JSONArray jsonGoleadores = new JSONArray(JsonUtiles.leer(archivo));
            for(int i = 0; i < jsonGoleadores.length(); i++) {
                String equipo = jsonGoleadores.getJSONObject(i).getJSONArray("statistics").getJSONObject(0).getJSONObject("team").getString("name");
                String nombre = jsonGoleadores.getJSONObject(i).getJSONObject("player").getString("name");
                int goles = jsonGoleadores.getJSONObject(i).getJSONArray("statistics").getJSONObject(0).getJSONObject("goals").getInt("total");
                boolean jFlag = false;
                for(int j = 0; j < torneo.getTabla().size() && !jFlag; j++) {
                    if(torneo.getTabla().get(j).getNombre().equals(equipo)) {
                        jFlag = true;
                        boolean kFlag = false;
                        for(int k = 0; k < torneo.getTabla().get(j).getJugadores().size() && !kFlag; k++) {
                            if(torneo.getTabla().get(j).getJugadores().get(k).getNombre().equals(nombre)) {
                                kFlag = true;
                                torneo.getTabla().get(j).getJugadores().get(k).setGoles(goles);
                                goleadores.add(torneo.getTabla().get(j).getJugadores().get(k));
                            }
                        }
                    }
                }
            }
        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }
        return goleadores;
    } //pasa de .json a contenedor de jugadores (goleadores)
    //
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
    public static int buscarIndice(String nombre, int id) {
        Torneo torneo;
        int x = -1;
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("bin\\" + nombre.replace(" ", "") + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            torneo = (Torneo) objectInputStream.readObject();
            for(int i = 0; i < torneo.getTabla().size() && x == -1; i++) {
                if(torneo.getTabla().get(i).getIdEquipo() == id) {
                    x = i;
                }
            }
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
        return x;
    } //devuelve el indice de un equipo en la tabla
}