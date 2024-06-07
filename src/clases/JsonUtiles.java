//e322d3134e96e5ca6f13792f4df66ed5 jonas
//d0745dc142c1ed133294934d257cb473 agustin
//d1be930a252fbfb6c9f12ba5ec74de47 luca
package clases;
import excepciones.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.ConnectException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class JsonUtiles {
    public static JSONArray informacionApiToJsonArray(String informacionApi) throws JsonErrorException {
        JSONObject jsonObj;
        JSONArray jsA;
        try {
            assert informacionApi != null;
            jsonObj = new JSONObject(informacionApi);
            jsA = jsonObj.getJSONArray("response");
        }
        catch(JSONException ex) {
            throw new JsonErrorException();
        }
        return jsA;
    } //pasa de string a jsonarray
    public static void grabar(JSONArray jsonArray, String archivo) throws IOException {
        try {
            FileWriter file = new FileWriter("json\\" + archivo + ".json");
            file.write(jsonArray.toString());
            file.flush();
            file.close();
        }
        catch(IOException ex) {
            throw new IOException();
        }
    } //pasa de jsonarray a .json
    public static void grabar(JSONObject jsonObject, String archivo) throws IOException {
        try {
            FileWriter file = new FileWriter("json\\" + archivo + ".json");
            file.write(jsonObject.toString());
            file.flush();
            file.close();
        }
        catch(IOException ex) {
            throw new IOException();
        }
    } //pasa de jsonobject a .json //por ahora no se usa
    public static String leer(String archivo) throws IOException {
        String s;
        try {
            s = new String(Files.readAllBytes(Paths.get("json\\" + archivo + ".json")));
        }
        catch(IOException ex) {
            throw new IOException();
        }
        return s;
    } //pasa de .json a string
    public static void exportarTorneo(JSONObject joTorneo) throws JsonErrorException, CargaCanceladaException {
        try {
            grabar(joTorneo, joTorneo.getString("nombre").replace(" ", "") + joTorneo.getInt("temporada"));
        }
        catch(IOException ex) {
            throw new CargaCanceladaException(ex.getMessage());
        }
        catch(JSONException ex) {
            throw new JsonErrorException();
        }
    } //guarda el torneo en un .json
    public static void exportarEquipo(JSONObject joEquipo) throws JsonErrorException, CargaCanceladaException {
        try {
            grabar(joEquipo, joEquipo.getString("nombre").replace(" ", ""));
        }
        catch(IOException ex) {
            throw new CargaCanceladaException(ex.getMessage());
        }
        catch(JSONException ex) {
            throw new JsonErrorException();
        }
    } //guarda un equipo en un .json
    public static void grabarTabla(int liga, int temporada) throws DescargaCanceladaException {
        try {
            JSONArray jaTabla = JsonUtiles.informacionApiToJsonArray(ApiFootball.conectarApi("https://v3.football.api-sports.io/standings?league=" + liga + "&season=" + temporada, "e322d3134e96e5ca6f13792f4df66ed5"));
            JsonUtiles.grabar(jaTabla.getJSONObject(0).getJSONObject("league").getJSONArray("standings").getJSONArray(0), liga + "-" + temporada + "\\apiTabla");
        }
        catch(ConnectException ex) {
            throw new DescargaCanceladaException("Error de conexion. La tabla no ha sido actualizada.");
        }
        catch(StatusCodeException | JsonErrorException | DescargaCanceladaException | IOException ex) {
            throw new DescargaCanceladaException(ex.getMessage());
        }
        catch(JSONException ex) {
            throw new DescargaCanceladaException("Mal enrutamiento. Ver keys. Contacte al desarrollador del software.");
        }
    } //descarga la tabla de la api y los guarda en .json
    public static void grabarJugadores(int id, int liga, int temporada) throws DescargaCanceladaException {
        try {
            JSONArray jaJugadores = JsonUtiles.informacionApiToJsonArray(ApiFootball.conectarApi("https://v3.football.api-sports.io/players/squads?team=" + id, "e322d3134e96e5ca6f13792f4df66ed5"));
            JsonUtiles.grabar(jaJugadores, liga + "-" + temporada + "\\jugadores\\apiJugadores" + id);
        }
        catch(ConnectException ex) {
            throw new DescargaCanceladaException("Error de conexion. Los jugadores del equipo con #id " + id + " no han sido actualizado.");
        }
        catch(StatusCodeException | JsonErrorException | DescargaCanceladaException | IOException ex) {
            throw new DescargaCanceladaException(ex.getMessage());
        }
    } //descarga los jugadores de un equipo de la api y los guarda en .json
    public static void grabarFixture(int liga, int temporada) throws DescargaCanceladaException {
        try {
            JSONArray jaFixture = JsonUtiles.informacionApiToJsonArray(ApiFootball.conectarApi("https://v3.football.api-sports.io/fixtures?league=" + liga + "&season=" + temporada, "e322d3134e96e5ca6f13792f4df66ed5"));
            JsonUtiles.grabar(jaFixture, liga + "-" + temporada + "\\apiFixture");
        }
        catch(ConnectException ex) {
            throw new DescargaCanceladaException("Error de conexion. La tabla no ha sido actualizada.");
        }
        catch(StatusCodeException | JsonErrorException | DescargaCanceladaException | IOException ex) {
            throw new DescargaCanceladaException(ex.getMessage());
        }
    } //descarga el fixture de la api y los guarda en .json
    public static void grabarTecnicos(int id, int liga, int temporada) throws DescargaCanceladaException {
        try {
            JSONArray jaTecnicos = JsonUtiles.informacionApiToJsonArray(ApiFootball.conectarApi("https://v3.football.api-sports.io/coachs?team=" + id, "e322d3134e96e5ca6f13792f4df66ed5"));
            JsonUtiles.grabar(jaTecnicos, liga + "-" + temporada + "\\tecnicos\\apiTecnicos" + id);
        }
        catch(ConnectException ex) {
            throw new DescargaCanceladaException("Error de conexion. La tabla no ha sido actualizada.");
        }
        catch(StatusCodeException | JsonErrorException | DescargaCanceladaException | IOException ex) {
            throw new DescargaCanceladaException(ex.getMessage());
        }
    } //descarga los tecnicos de un equipo de la api y los guarda en .json
    public static void grabarGoleadores(int liga, int temporada) throws DescargaCanceladaException {
        try {
            JSONArray jaGoleadores = JsonUtiles.informacionApiToJsonArray(ApiFootball.conectarApi("https://v3.football.api-sports.io/players/topscorers?league=" + liga + "&season=" + temporada, "e322d3134e96e5ca6f13792f4df66ed5"));
            JsonUtiles.grabar(jaGoleadores, liga + "-" + temporada + "\\apiGoleadores");
        }
        catch(ConnectException ex) {
            throw new DescargaCanceladaException("Error de conexion. La tabla no ha sido actualizada.");
        }
        catch(StatusCodeException | JsonErrorException | DescargaCanceladaException | IOException ex) {
            throw new DescargaCanceladaException(ex.getMessage());
        }
    } //descarga los goleadores de la api y los guarda en .json
    public static Torneo jsonToTorneo(int liga, int temporada) throws JsonErrorException, DescargaCanceladaException {
        Torneo torneo;
        try {
            JSONArray jaTabla = new JSONArray(JsonUtiles.leer(liga + "-" + temporada + "\\apiTabla"));
            torneo = new Torneo(jaTabla.getJSONObject(0).getString("group"), temporada);
            for(int i = 0; i < jaTabla.length(); i++) {
                torneo.agregarEquipo(new Equipo(jaTabla.getJSONObject(i).getJSONObject("team").getInt("id"), //id
                        jaTabla.getJSONObject(i).getJSONObject("team").getString("name"), //nombre
                        jsonToTecnico(liga + "-" + temporada + "\\tecnicos\\apiTecnicos" + jaTabla.getJSONObject(i).getJSONObject("team").getInt("id")), //tecnico
                        jsonToJugadores(liga + "-" + temporada + "\\jugadores\\apiJugadores" + jaTabla.getJSONObject(i).getJSONObject("team").getInt("id")), //jugadores
                        jaTabla.getJSONObject(i).getInt("points"), //puntos
                        jaTabla.getJSONObject(i).getJSONObject("all").getInt("played"), //partidos jugados
                        jaTabla.getJSONObject(i).getJSONObject("all").getInt("win"), //partidos ganados
                        jaTabla.getJSONObject(i).getJSONObject("all").getInt("draw"), //partidos empatados
                        jaTabla.getJSONObject(i).getJSONObject("all").getInt("lose"), //partidos perdidos
                        jaTabla.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("for"), //goles a favor
                        jaTabla.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("against"), //goles en contra
                        jaTabla.getJSONObject(i).getInt("goalsDiff"))); //diferencia de goles
            }
            torneo.agregarFixture(jsonToFixture(liga + "-" + temporada + "\\apiFixture", torneo.getTabla().size())); //fixture
            torneo.agregarGoleadores(jsonToGoleadores(liga + "-" + temporada + "\\apiGoleadores", torneo)); //goleadores
        }
        catch(JSONException ex) {
            throw new JsonErrorException();
        }
        catch(IOException ex) {
            throw new DescargaCanceladaException(ex.getMessage());
        }
        return torneo;
    } //pasa de .json a torneo
    public static Contenedor<Jugador> jsonToJugadores(String archivo) throws JsonErrorException, DescargaCanceladaException {
        Contenedor<Jugador> jugadores = new Contenedor<>();
        try {
            JSONArray jaJugadores = new JSONArray(JsonUtiles.leer(archivo));
            for(int i = 0; i < jaJugadores.getJSONObject(0).getJSONArray("players").length(); i++) {
                if(!jaJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).isNull("number") &&
                        !jaJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).isNull("age")) {
                    Jugador ju = new Jugador(jaJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).getString("name"), //nombre
                            jaJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).getInt("age"), //edad
                            jaJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).getString("position"), //puesto
                            jaJugadores.getJSONObject(0).getJSONArray("players").getJSONObject(i).getInt("number")); //numero
                    jugadores.add(ju);
                }
            }
        }
        catch(JSONException ex) {
            throw new JsonErrorException();
        }
        catch(IOException ex) {
            throw new DescargaCanceladaException(ex.getMessage());
        }
        return jugadores;
    } //pasa de .json a contenedor de jugadores
    public static Contenedor<Fecha> jsonToFixture(String archivo, int equipos) throws JsonErrorException, DescargaCanceladaException {
        Contenedor<Fecha> fixture = new Contenedor<>();
        try {
            JSONArray jaFixture = new JSONArray(JsonUtiles.leer(archivo));
            int i = 0;
            for(int j = 0; j < jaFixture.length() / (equipos / 2); j++) { //las 27 fechas (los partidos se juegan de a 2 equipos)
                Contenedor<PartidoFutbol> partidos = new Contenedor<>();
                for(int k = 0; k < (equipos / 2) && i < jaFixture.length(); k++, i++) { //todos los partidos de cada fecha
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
            throw new JsonErrorException();
        }
        catch(IOException ex) {
            throw new DescargaCanceladaException(ex.getMessage());
        }
        return fixture;
    } //pasa de .json a contenedor de fechas (fixture)
    public static Tecnico jsonToTecnico(String archivo) throws JsonErrorException, DescargaCanceladaException {
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
        }
        catch(JSONException ex) {
            throw new JsonErrorException();
        }
        catch(IOException ex) {
            throw new DescargaCanceladaException(ex.getMessage());
        }
        return tecnico;
    } //pasa de .json a tecnico
    public static Contenedor<Jugador> jsonToGoleadores(String archivo, Torneo torneo) throws JsonErrorException, DescargaCanceladaException {
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
            throw new JsonErrorException();
        }
        catch(IOException ex) {
            throw new DescargaCanceladaException(ex.getMessage());
        }
        return goleadores;
    } //pasa de .json a contenedor de jugadores (goleadores)
    public static void torneoToBin(Torneo torneo) throws ArchivoNoCerradoException, CargaCanceladaException {
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("bin\\" + torneo.getNombre().replace(" ", "") + torneo.getTemporada() + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(torneo);
        }
        catch(IOException ex) {
            throw new CargaCanceladaException(ex.getMessage());
        }
        finally {
            try {
                assert objectOutputStream != null;
                objectOutputStream.close();
            }
            catch(IOException ex) {
                throw new ArchivoNoCerradoException();
            }
        }
    } //pasa de torneo a .bin
    public static Torneo binToTorneo(String archivo) throws DescargaCanceladaException, ArchivoNoCerradoException {
        Torneo torneo;
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("bin\\" + archivo + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            torneo = (Torneo) objectInputStream.readObject();
        }
        catch(IOException | ClassNotFoundException ex) {
            throw new DescargaCanceladaException(ex.getMessage());
        }
        finally {
            try {
                assert objectInputStream != null;
                objectInputStream.close();
            }
            catch(IOException ex) {
                throw new ArchivoNoCerradoException();
            }
        }
        return torneo;
    } //pasa de .bin a torneo
    public static int buscarIndice(Torneo torneo, int id) throws IdInexistenteException {
        int indice = -1;
        for(int i = 0; i < torneo.getTabla().size() && indice == -1; i++) {
            if(torneo.getTabla().get(i).getIdEquipo() == id) {
                indice = i;
            }
        }
        if(indice == -1) {
            throw new IdInexistenteException(id);
        }
        return indice;
    } //devuelve el indice de un equipo en la tabla
}