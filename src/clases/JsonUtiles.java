package clases;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class JsonUtiles {
    public static JSONArray informacionApiToJsonArray(String informacionApi) {
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
    public static void grabar(JSONArray jsonArray, String archivo) {
        try {
            FileWriter file = new FileWriter("json\\" + archivo +".json");
            file.write(jsonArray.toString());
            file.flush();
            file.close();
        }
        catch(IOException ex) {
            throw new RuntimeException(ex);
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
            throw new RuntimeException(ex);
        }
    } //pasa de jsonobject a .json //por ahora no se usa
    public static String leer(String archivo) {
        String s;
        try {
            s = new String(Files.readAllBytes(Paths.get("json\\" + archivo + ".json")));
        }
        catch(IOException ex) {
            throw new RuntimeException(ex);
        }
        return s;
    } //pasa de .json a string
    public static void grabarTorneo(JSONObject joTorneo) {
        try {
            grabar(joTorneo, joTorneo.getString("nombre").replace(" ", ""));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    } //guarda el torneo en un .json
    public static void grabarTabla() {
        JSONArray jaTabla = JsonUtiles.informacionApiToJsonArray(ApiFootball.conectarApi("https://v3.football.api-sports.io/standings?league=128&season=2024", "d0745dc142c1ed133294934d257cb473"));
        try {
            JsonUtiles.grabar(jaTabla.getJSONObject(0).getJSONObject("league").getJSONArray("standings").getJSONArray(0), "apiTabla");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    } //descarga la tabla de la api y los guarda en .json
    public static void grabarJugadores(int id) {
        JSONArray jaJugadores = JsonUtiles.informacionApiToJsonArray(ApiFootball.conectarApi("https://v3.football.api-sports.io/players/squads?team=" + id, "d0745dc142c1ed133294934d257cb473"));
        JsonUtiles.grabar(jaJugadores, "jugadores\\apiJugadores" + id);
    } //descarga los jugadores de un equipo de la api y los guarda en .json
    public static void grabarFixture() {
        JSONArray jaFixture = JsonUtiles.informacionApiToJsonArray(ApiFootball.conectarApi("https://v3.football.api-sports.io/fixtures?league=128&season=2024", "d0745dc142c1ed133294934d257cb473"));
        JsonUtiles.grabar(jaFixture, "apiFixture");
    } //descarga el fixture de la api y los guarda en .json
    public static void grabarTecnicos(int id) {
        JSONArray jaTecnicos = JsonUtiles.informacionApiToJsonArray(ApiFootball.conectarApi("https://v3.football.api-sports.io/coachs?team=" + id, "d0745dc142c1ed133294934d257cb473"));
        JsonUtiles.grabar(jaTecnicos, "tecnicos\\apiTecnicos" + id);
    } //descarga los tecnicos de un equipo de la api y los guarda en .json
    public static void grabarGoleadores() {
        JSONArray jaGoleadores = JsonUtiles.informacionApiToJsonArray(ApiFootball.conectarApi("https://v3.football.api-sports.io/players/topscorers?league=128&season=2024", "d0745dc142c1ed133294934d257cb473"));
        JsonUtiles.grabar(jaGoleadores, "apiGoleadores");
    } //descarga los goleadores de la api y los guarda en .json
    public static Torneo jsonToTorneo() {
        Torneo torneo = new Torneo("Liga Profesional de Futbol 2024");
        try {
            JSONArray jaTabla = new JSONArray(JsonUtiles.leer("apiTabla"));
            for(int i = 0; i < jaTabla.length(); i++) {
                torneo.agregarEquipo(new Equipo(jaTabla.getJSONObject(i).getJSONObject("team").getInt("id"), //id
                        jaTabla.getJSONObject(i).getJSONObject("team").getString("name"), //nombre
                        jsonToTecnico("tecnicos\\apiTecnicos" + jaTabla.getJSONObject(i).getJSONObject("team").getInt("id")), //tecnico
                        jsonToJugadores("jugadores\\apiJugadores" + jaTabla.getJSONObject(i).getJSONObject("team").getInt("id")), //jugadores
                        jaTabla.getJSONObject(i).getInt("points"), //puntos
                        jaTabla.getJSONObject(i).getJSONObject("all").getInt("played"), //partidos jugados
                        jaTabla.getJSONObject(i).getJSONObject("all").getInt("win"), //partidos ganados
                        jaTabla.getJSONObject(i).getJSONObject("all").getInt("draw"), //partidos empatados
                        jaTabla.getJSONObject(i).getJSONObject("all").getInt("lose"), //partidos perdidos
                        jaTabla.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("for"), //goles a favor
                        jaTabla.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("against"), //goles en contra
                        jaTabla.getJSONObject(i).getInt("goalsDiff"))); //diferencia de goles
            }
            torneo.agregarFixture(jsonToFixture("apiFixture")); //fixture
            torneo.agregarGoleadores(jsonToGoleadores("apiGoleadores", torneo)); //goleadores
        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }
        return torneo;
    } //pasa de .json a torneo
    public static Contenedor<Jugador> jsonToJugadores(String archivo) {
        Contenedor<Jugador> jugadores = new Contenedor<>();
        try {
            JSONArray jaJugadores = new JSONArray(JsonUtiles.leer(archivo));
            for(int i = 0; i < jaJugadores.getJSONObject(0).getJSONArray("players").length(); i++) {
                if(!jaJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).isNull("number") &&
                        !jaJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(0).isNull("age")) {
                    Jugador ju = new Jugador(jaJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).getString("name"), //nombre
                            jaJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).getInt("age"), //edad
                            jaJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).getString("position"), //posicion
                            jaJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).getInt("number")); //numero
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
            JSONArray jaFixture = new JSONArray(JsonUtiles.leer(archivo));
            int i = 0;
            for(int j = 0; j < jaFixture.length() / 14; j++) { //las 14 fechas
                Contenedor<PartidoFutbol> partidos = new Contenedor<>();
                for(int k = 0; k < 14 && i < jaFixture.length(); k++, i++) { //todos los partidos y me fijo de cortar cada 14 fechas
                    Equipo local = new Equipo(jaFixture.getJSONObject(i).getJSONObject("teams").getJSONObject("home").getInt("id"),
                            jaFixture.getJSONObject(i).getJSONObject("teams").getJSONObject("home").getString("name"));
                    Equipo visitante = new Equipo(jaFixture.getJSONObject(i).getJSONObject("teams").getJSONObject("away").getInt("id"),
                            jaFixture.getJSONObject(i).getJSONObject("teams").getJSONObject("away").getString("name"));
                    int golesL = -1;
                    int golesV = -1;
                    if(!(jaFixture.getJSONObject(i).getJSONObject("goals").isNull("home")) &&
                            !(jaFixture.getJSONObject(i).getJSONObject("goals").isNull("away"))) {
                        golesL = jaFixture.getJSONObject(i).getJSONObject("goals").getInt("home");
                        golesV = jaFixture.getJSONObject(i).getJSONObject("goals").getInt("away");
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
    } //pasa de .json a contenedor de fechas (fixture)
    public static Tecnico jsonToTecnico(String archivo) {
        Tecnico tecnico;
        try {
            JSONArray jaTecnicos = new JSONArray(JsonUtiles.leer(archivo));
            int edad = -1;
            if(!jaTecnicos.getJSONObject(0).isNull("age")) {
                edad = jaTecnicos.getJSONObject(0).getInt("age");
            }
            Contenedor<String> trayectoria = new Contenedor<>();
            for(int i = 0; i < jaTecnicos.getJSONObject(0).getJSONArray("career").length(); i++) {
                trayectoria.add(jaTecnicos.getJSONObject(0).getJSONArray("career").getJSONObject(i).getJSONObject("team").getString("name"));
            }
            tecnico = new Tecnico(jaTecnicos.getJSONObject(0).getString("name"), //nombre
                    edad, //edad
                    trayectoria); //trayectoria
        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
        return tecnico;
    } //pasa de .json a tecnico
    public static Contenedor<Jugador> jsonToGoleadores(String archivo, Torneo torneo) {
        Contenedor<Jugador> goleadores = new Contenedor<>();
        try {
            JSONArray jaGoleadores = new JSONArray(JsonUtiles.leer(archivo));
            for(int i = 0; i < jaGoleadores.length(); i++) {
                int idEquipo = jaGoleadores.getJSONObject(i).getJSONArray("statistics").getJSONObject(0).getJSONObject("team").getInt("id");
                String nombre = jaGoleadores.getJSONObject(i).getJSONObject("player").getString("name");
                int goles = jaGoleadores.getJSONObject(i).getJSONArray("statistics").getJSONObject(0).getJSONObject("goals").getInt("total");
                boolean jFlag = false;
                for(int j = 0; j < torneo.getTabla().size() && !jFlag; j++) {
                    if(torneo.getTabla().get(j).getIdEquipo() == idEquipo) {
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
    public static Torneo binToTorneo() {
        Torneo torneo;
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("bin\\LigaProfesionaldeFutbol2024.bin");
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
        int x = -2;
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("bin\\" + nombre.replace(" ", "") + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            torneo = (Torneo) objectInputStream.readObject();
            for(int i = 0; i < torneo.getTabla().size() && x == -2; i++) {
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