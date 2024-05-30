package Clases;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonUtiles {
    ////////// se usa para descargar de la api a json
    public static JSONArray informacionApiToJsonArrayTabla(String informacionApi) {
        JSONObject jsonObj;
        JSONArray jsonResponse;
        JSONObject jsonLiga;
        JSONArray jsonTabla;
        try {
            assert informacionApi != null;
            jsonObj = new JSONObject(informacionApi);
            jsonResponse = jsonObj.getJSONArray("response");
            jsonLiga = jsonResponse.getJSONObject(0).getJSONObject("league");
            jsonTabla = jsonLiga.getJSONArray("standings").getJSONArray(0);
        }
        catch(JSONException e) {
            throw new RuntimeException(e);
        }
        return jsonTabla;
    }
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
    }
    ////////// metodos para el archivo de equipos
    public static ArrayList<Equipo> descargarEquipos(JSONArray jsonTabla) {
        ArrayList<Equipo> equipos = new ArrayList<>();
        for(int i = 0; i < jsonTabla.length(); i++)
        {
            try {
                Equipo eq = new Equipo(jsonTabla.getJSONObject(i).getJSONObject("team").getString("name"),
                        jsonToPlantel(jsonTabla.getJSONObject(i).getJSONObject("team").getString("name")),
                        jsonTabla.getJSONObject(i).getInt("points"),
                        jsonTabla.getJSONObject(i).getJSONObject("all").getInt("played"),
                        jsonTabla.getJSONObject(i).getJSONObject("all").getInt("win"),
                        jsonTabla.getJSONObject(i).getJSONObject("all").getInt("draw"),
                        jsonTabla.getJSONObject(i).getJSONObject("all").getInt("lose"),
                        jsonTabla.getJSONObject(i).getInt("goalsDiff"),
                        jsonTabla.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("for"),
                        jsonTabla.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("against"),
                        jsonTabla.getJSONObject(i).getJSONObject("team").getInt("id"));
                equipos.add(eq);
            }
            catch(JSONException e) {
                throw new RuntimeException("Excepcion de tipo JSON");
            }
            catch(Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return equipos;
    }
    public static void actualizarTablaBin(JSONArray jsonTabla) throws IOException {

        ObjectOutputStream objectOutputStream = null;
        try {
            ArrayList<Equipo> equipos = descargarEquipos(jsonTabla);
            FileOutputStream fileOutputStream = new FileOutputStream("archivos\\tabla.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for(Equipo equipo : equipos) {
                objectOutputStream.writeObject(equipo);
            }
        }
        catch(FileNotFoundException ex) {
            throw new FileNotFoundException("Archivo no encontrado.");
        }
        catch(IOException ex) {
            ex.printStackTrace();
            throw new IOException("Error IO.");
        }
        finally {
            try
            {
                assert objectOutputStream != null;
                objectOutputStream.close();
            }
            catch(IOException ex)
            {
                throw new IOException("No se pudo cerrar el archivo.");
            }
        }
    }
    public static void descargarTablaBin(Torneo torneo) {
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("archivos\\tabla.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            while(true) {
                Equipo equipo = (Equipo) objectInputStream.readObject();
                torneo.agregarEquipo(equipo);
            }
        }
        catch(EOFException ex)
        {
            System.out.println("EOF");
        }
        catch(InvalidClassException ex)
        {
            ex.printStackTrace();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex)
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            try
            {
                assert objectInputStream != null;
                objectInputStream.close();
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    ////////// metodos para los archivos de jugadores
    public static ArrayList<Jugador> descargarJugadores(JSONArray jsonJugadores) {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        for(int i = 0; i < jsonJugadores.length(); i++)
        {
            try {
                if(!jsonJugadores.getJSONObject(i).isNull("number") && !jsonJugadores.getJSONObject(i).isNull("age"))
                {
                    Jugador ju = new Jugador(jsonJugadores.getJSONObject(i).getString("name"), //nombre
                            jsonJugadores.getJSONObject(i).getInt("age"), //edad
                            jsonJugadores.getJSONObject(i).getString("position"), //posicion
                            jsonJugadores.getJSONObject(i).getInt("number")); //numero
                    jugadores.add(ju);
                }
            }
            catch(JSONException ex) {
                throw new RuntimeException("Excepcion de tipo JSON.");
            }
            catch(Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }
        return jugadores;
    }
    public static void actualizarJugadoresBin(JSONArray response) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            JSONArray jsonJugadores = response.getJSONObject(0).getJSONArray("players");
            ArrayList<Jugador> jugadores = descargarJugadores(jsonJugadores);
            FileOutputStream fileOutputStream = new FileOutputStream("archivos\\jugadores" + response.getJSONObject(0).getJSONObject("team").getString("name").replace(" ", "") + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for(Jugador jugador : jugadores) {
                objectOutputStream.writeObject(jugador);
                System.out.println(jugador);
            }
        }
        catch(FileNotFoundException ex) {
            throw new FileNotFoundException("Archivo no encontrado.");
        }
        catch(IOException ex) {
            ex.printStackTrace();
            throw new IOException("Error IO.");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } finally {
            try
            {
                assert objectOutputStream != null;
                objectOutputStream.close();
            }
            catch(IOException ex)
            {
                throw new IOException("No se pudo cerrar el archivo.");
            }
        }
    }
    public static void descargarTodosJugadoresBin(Torneo torneo) {
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("archivos\\tabla.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            int i = 0;
            //while(true) {
            while(true && i < 1) {
                Equipo equipo = (Equipo) objectInputStream.readObject(); //tengo "Racing Club"
                descargarJugadoresBin(torneo.getTabla().get(i).getJugadores(), equipo.getNombre().replace(" ", ""));
                i++;
            }
        }
        catch(EOFException ex)
        {
            System.out.println("EOF");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex)
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            try
            {
                assert objectInputStream != null;
                objectInputStream.close();
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    public static void descargarJugadoresBin(HashMap<Integer, Jugador> jugadores, String eq) {
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("archivos\\jugadores" + eq + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            while(true) {
                Jugador jugador = (Jugador) objectInputStream.readObject();
                jugadores.put(jugador.getNumero(), jugador);
            }
        }
        catch(EOFException ex)
        {
            System.out.println("EOF");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex)
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            try
            {
                assert objectInputStream != null;
                objectInputStream.close();
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    ////////// metodo que busca el id de un equipo por el nombre
    public static int devloverId(String nombreEquipo) {
        int id = -1;
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("tabla.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            while(true && id == -1) {
                Equipo equipo = (Equipo) objectInputStream.readObject();
                if(equipo.getNombre().equals(nombreEquipo)) {
                    id = equipo.getIdEquipo();
                }
            }
        }
        catch(EOFException ex)
        {
            System.out.println("FIN");
        }
        catch(InvalidClassException ex)
        {
            ex.printStackTrace();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex)
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            try
            {
                assert objectInputStream != null;
                objectInputStream.close();
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return id;
    }
    //////////
    public static void jsonToEquipos(Torneo torneo, JSONArray jsonTabla, ArrayList<Equipo> equipos) {

        for (int i = 0; i < jsonTabla.length(); i++) //cargo todos los equipos
        {
            String nombre;
            HashMap<Integer, Jugador> plantel;
            int puntos;
            int partidosJugados;
            int partidosGanados;
            int partidosEmpatados;
            int partidosPerdidos;
            int diferenciaGoles;
            int golesAFavor;
            int golesEnContra;
            int idEquipo;

            try {
                idEquipo = jsonTabla.getJSONObject(i).getJSONObject("team").getInt("id");
                nombre = jsonTabla.getJSONObject(i).getJSONObject("team").getString("name");
                plantel = jsonToPlantel(nombre);
                //plantel = jsonToPlantelPorID(idEquipo);
                //plantel = new HashMap<>();
                puntos = jsonTabla.getJSONObject(i).getInt("points");
                partidosJugados = jsonTabla.getJSONObject(i).getJSONObject("all").getInt("played");
                partidosGanados = jsonTabla.getJSONObject(i).getJSONObject("all").getInt("win");
                partidosEmpatados = jsonTabla.getJSONObject(i).getJSONObject("all").getInt("draw");
                partidosPerdidos = jsonTabla.getJSONObject(i).getJSONObject("all").getInt("lose");
                diferenciaGoles = jsonTabla.getJSONObject(i).getInt("goalsDiff");
                golesAFavor = jsonTabla.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("for");
                golesEnContra = jsonTabla.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("against");

            } catch (JSONException e) {
                throw new RuntimeException("Excepcion de tipo JSON");
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

            Equipo equipo = new Equipo(nombre, plantel, puntos, partidosJugados, partidosGanados, partidosEmpatados, partidosPerdidos, diferenciaGoles, golesAFavor, golesEnContra, idEquipo);

            equipos.add(equipo);
        }

        for (Equipo equipo : equipos) {
            torneo.agregarEquipo(equipo);
        }


    }
    ////////// metodos que se usan en los metodos de archivos
    public static HashMap<Integer, Jugador> jsonToPlantel(String nombreEquipo) throws Exception {

        HashMap<Integer, Jugador> hashMapEquipo = new HashMap<>();
        /*String urlStringEquipo = "https://v3.football.api-sports.io/players/squads?team=" + idEquipo;     //url con el id del equipo a acceder ----> ya no necesaria porque los planteles estan cargadas en archivos
        String apiKey = "e322d3134e96e5ca6f13792f4df66ed5";     //key personal de la API                    //por eso se pide el nombre del equipo en la v2 de esta funcion, para acceder al archivo del nombre del equipo

        String informacionAPI = APIFootball.conectarAPI(urlStringEquipo, apiKey);*/       ///NO ES NECESARIO LLAMAR A LA API, YA CARGUE LOS PLANTELES EN UN ARCHIVOS

        String informacionAPI = leerArchivoPlanteles(nombreEquipo);
        JSONArray jsonPlayers = informacionAPItoJsonPlayers(informacionAPI);


        for (int i = 0; i < jsonPlayers.length(); i++) {
            String nombre;
            int edad;
            int numCamiseta;
            String posicion;

            Jugador jugador;

            try {
                nombre = jsonPlayers.getJSONObject(i).getString("name");
                //edad = jsonPlayers.getJSONObject(i).getInt("age");
                posicion = jsonPlayers.getJSONObject(i).getString("position");

                if (jsonPlayers.getJSONObject(i).isNull("number"))          //compruebo que numero de camiseta no sea null ----> los numeros de camiseta null son los jugadores de reserva en la api, no forman parte del plantel titula
                {
                    numCamiseta = -1;                                           //si es null le pongo valor -1 para despues a la hora de ingresarlo al hash map, no hacerlo
                } else {
                    numCamiseta = jsonPlayers.getJSONObject(i).getInt("number");        //si no es null, lo guardo en la variable

                }

                if (jsonPlayers.getJSONObject(i).isNull("age"))          //compruebo que numero de camiseta no sea null ----> los numeros de camiseta null son los jugadores de reserva en la api, no forman parte del plantel titula
                {
                    edad = -1;                                           //si es null le pongo valor -1 para despues a la hora de ingresarlo al hash map, no hacerlo
                } else {
                    edad = jsonPlayers.getJSONObject(i).getInt("age");        //si no es null, lo guardo en la variable

                }

                jugador = new Jugador(nombre, edad, posicion, numCamiseta);

            } catch (JSONException e) {
                throw new JSONException("Excepcion de tipo JSON");
            }
            if ((numCamiseta != -1) && (edad != -1)) {
                hashMapEquipo.put(numCamiseta, jugador);
            }
        }

        return hashMapEquipo;
    }
    public static String leerArchivoPlanteles(String nombre) throws Exception {
        DataInputStream dataInputStream = null;
        String informacionJSON;
        try {
            FileInputStream fileInputStream = new FileInputStream("plantelDe" + nombre + ".bin");
            dataInputStream = new DataInputStream(fileInputStream);
            informacionJSON = dataInputStream.readUTF();
        }
        catch (FileNotFoundException ex)
        {
            throw new FileNotFoundException("Archivo no encontrado");
        } catch (EOFException ex)
        {
            throw new EOFException("Fin del archivo");
        }
        catch (IOException ex)
        {
            throw new IOException("Error io");
        }catch (Exception ex)
        {
            throw new Exception(ex);
        }
        finally {
            try
            {
                assert dataInputStream != null;
                dataInputStream.close();
            }
            catch (IOException ex)
            {
                throw new IOException("No se pudo cerrar el archivo");
            }
        }
        return informacionJSON;
    }
    //////////
    public static void grabarArchivo(JSONObject object, String nombre) throws IOException {
        //DataOutputStream dataOutputStream = null;
        FileWriter file = null;
        try {
            /*FileOutputStream fileOutputStream = new FileOutputStream(nombre + ".json");
            dataOutputStream = new DataOutputStream(fileOutputStream);

            dataOutputStream.writeUTF(array.toString());*/
            file = new FileWriter(nombre +".json");
            file.write(object.toString());
            file.flush();
            //file.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //assert dataOutputStream != null;
                //dataOutputStream.close();
                assert file != null;
                file.close();
            } catch (IOException ex) {
                throw new IOException("No se pudo cerrar el archivo");
            }
        }
    }
    public static String leerArchivo(String archivo) throws Exception {
        //DataInputStream dataInputStream = null;
        String informacion;
        BufferedReader reader = null;
        try
        {
            /*FileInputStream fileInputStream = new FileInputStream(archivo + ".json");
            dataInputStream = new DataInputStream(fileInputStream);
            informacion = dataInputStream.readUTF();*/
            reader = new BufferedReader(new FileReader(archivo + ".json"));
            informacion = reader.readLine();
        }
        catch (FileNotFoundException ex)
        {
            throw new FileNotFoundException("Archivo no encontrado");
        } catch (EOFException ex)
        {
            throw new EOFException("Fin del archivo");
        }
        catch (IOException ex)
        {
            throw new IOException("Error io");
        }catch (Exception ex)
        {
            throw new Exception(ex);
        }

        finally {
            try
            {
                /*assert dataInputStream != null;
                dataInputStream.close();*/
                assert reader != null;
                reader.close();
            }
            catch (IOException ex)
            {
                throw new IOException("No se pudo cerrar el archivo");
            }
        }

        return informacion;
    }
    ////////// no se usa por ahora
    public static void jsonToEquipos(Torneo torneo, JSONArray jsonTabla) {
        //ArrayList<Equipo> equipos = new ArrayList<>();
        for(int i = 0; i < jsonTabla.length(); i++) //cargo todos los equipos
        {
            try {
                Equipo eq = new Equipo(jsonTabla.getJSONObject(i).getJSONObject("team").getString("name"),
                        jsonToPlantel(jsonTabla.getJSONObject(i).getJSONObject("team").getString("name")),
                        jsonTabla.getJSONObject(i).getInt("points"),
                        jsonTabla.getJSONObject(i).getJSONObject("all").getInt("played"),
                        jsonTabla.getJSONObject(i).getJSONObject("all").getInt("win"),
                        jsonTabla.getJSONObject(i).getJSONObject("all").getInt("draw"),
                        jsonTabla.getJSONObject(i).getJSONObject("all").getInt("lose"),
                        jsonTabla.getJSONObject(i).getInt("goalsDiff"),
                        jsonTabla.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("for"),
                        jsonTabla.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("against"),
                        jsonTabla.getJSONObject(i).getJSONObject("team").getInt("id"));
                //equipos.add(eq);
                torneo.agregarEquipo(eq);
            }
            catch(JSONException e) {
                throw new RuntimeException("Excepcion de tipo JSON");
            }
            catch(Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            //Equipo equipo = new Equipo(nombre, plantel, puntos, partidosJugados,partidosGanados,
            // partidosEmpatados, partidosPerdidos, diferenciaGoles, golesAFavor, golesEnContra, idEquipo);
            //equipos.add(equipo);
        }
        /*for(Equipo equipo : equipos) {
            torneo.agregarEquipo(equipo);
        }*/

        //guardar el arraylist de equipos en un archivo binario aca adentro
    }
    public static JSONArray informacionAPItoJsonPlayers(String informacionAPI) { //trae los datos de los jugadores a jsonArray
        JSONObject jsonObj;
        JSONArray jsonResponse;
        JSONArray jsonPlayers;
        try {
            assert informacionAPI != null;
            jsonObj = new JSONObject(informacionAPI);
            jsonResponse = jsonObj.getJSONArray("response");
            jsonPlayers =  jsonResponse.getJSONObject(0).getJSONArray("players");
        }
        catch(JSONException e) {
            throw new RuntimeException(e);
        }
        return jsonPlayers;
    }
    public static HashMap<Integer, Jugador> jsonToPlantelPorID(int idEquipo) throws Exception {

        HashMap<Integer,Jugador> hashMapEquipo = new HashMap<>();
        String urlStringEquipo = "https://v3.football.api-sports.io/players/squads?team=" + idEquipo; //url con el id del equipo a acceder ----> ya no necesaria porque los planteles estan cargadas en archivos
        String apiKey = "d0745dc142c1ed133294934d257cb473"; //apikey personal de agus //por eso se pide el nombre del equipo en la v2 de esta funcion, para acceder al archivo del nombre del equipo

        String informacionAPI = ApiFootball.conectarApi(urlStringEquipo, apiKey);

        JSONArray jsonPlayers = informacionAPItoJsonPlayers(informacionAPI);

        for (int i = 0; i < jsonPlayers.length(); i++)
        {
            String nombre;
            int edad;
            int numCamiseta;
            String posicion;

            Jugador jugador;

            try {
                nombre = jsonPlayers.getJSONObject(i).getString("name");
                edad = jsonPlayers.getJSONObject(i).getInt("age");
                posicion = jsonPlayers.getJSONObject(i).getString("position");

                if (jsonPlayers.getJSONObject(i).isNull("number"))          //compruebo que numero de camiseta no sea null ----> los numeros de camiseta null son los jugadores de reserva en la api, no forman parte del plantel titula
                {
                    numCamiseta = -1;                                           //si es null le pongo valor -1 para despues a la hora de ingresarlo al hash map, no hacerlo
                }
                else {
                    numCamiseta = jsonPlayers.getJSONObject(i).getInt("number");        //si no es null, lo guardo en la variable

                }

                jugador = new Jugador(nombre,edad,posicion,numCamiseta);

            } catch (JSONException e) {
                throw new JSONException("Excepcion de tipo JSON");
            }
            if (numCamiseta != -1)
            {
                hashMapEquipo.put(numCamiseta, jugador);
            }
        }

        return hashMapEquipo;
    }
    public static void grabarPlantelToArchivo(Torneo torneo) throws IOException {
        DataOutputStream dataOutputStream = null;
        try {
            for(int i = 21 ; i < torneo.getTabla().size(); i++) {
                String urlStringEquipo = "https://v3.football.api-sports.io/players/squads?team=" + torneo.getTabla().get(i).getIdEquipo();
                String apiKey = "d0745dc142c1ed133294934d257cb473"; //apikey personal de agus
                String informacionAPI = ApiFootball.conectarApi(urlStringEquipo, apiKey);
                FileOutputStream fileOutputStream = new FileOutputStream("plantelDe" + torneo.getTabla().get(i).getNombre() + ".bin");
                dataOutputStream = new DataOutputStream(fileOutputStream);
                dataOutputStream.writeUTF(informacionAPI);
            }
        }
        catch(FileNotFoundException ex) {
            throw new FileNotFoundException("Archivo no encontrado.");
        }
        catch(IOException ex) {
            throw new IOException("Error IO.");
        }
        finally {
            try {
                assert dataOutputStream != null;
                dataOutputStream.close();
            }
            catch(IOException ex) {
                throw new IOException("No se pudo cerrar el archivo.");
            }
        }
    }
    ////////// menu2
    public static JSONArray informacionApiToJsonArrayTabla2(String informacionApi) {
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
    }
    public static JSONArray informacionApiToJsonArrayJugadores2(String informacionAPI) {
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
    }
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
    }
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
    }
    public static String leer(String archivo) {
        String s = "";
        try {
            s = new String(Files.readAllBytes(Paths.get("json\\" + archivo + ".json")));
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return s;
    }
    public static String devolverStringFormateado(String archivo) {
        String s = " Equipo                         | Pts | PJ | PG | PE | PP | GF | GC | Dif \n";
        try {
            JSONArray array = new JSONArray(JsonUtiles.leer(archivo));
            for(int i = 0; i < array.length(); i++) {
                s += " " + String.format("%-30s", array.getJSONObject(i).getJSONObject("team").getString("name")) + " | ";
                s += String.format("%3s", array.getJSONObject(i).getInt("points")) + " | ";
                s += String.format("%2s", array.getJSONObject(i).getJSONObject("all").getInt("played")) + " | ";
                s += String.format("%2s", array.getJSONObject(i).getJSONObject("all").getInt("win")) + " | ";
                s += String.format("%2s", array.getJSONObject(i).getJSONObject("all").getInt("draw")) + " | ";
                s += String.format("%2s", array.getJSONObject(i).getJSONObject("all").getInt("lose")) + " | ";
                s += String.format("%2s", array.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("for")) + " | ";
                s += String.format("%2s", array.getJSONObject(i).getJSONObject("all").getJSONObject("goals").getInt("against")) + " | ";
                s += String.format("%3s", array.getJSONObject(i).getInt("goalsDiff")) + " \n";
            }
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }
        return s;
    }
    public static void grabarTabla() {
        JSONArray jsonTabla = JsonUtiles.informacionApiToJsonArrayTabla2(ApiFootball.conectarApi("https://v3.football.api-sports.io/standings?league=128&season=2024", "01954488b25482303751a41d84b764a7"));
        JsonUtiles.grabar(jsonTabla, "apiStandings");
    }
    public static void grabarJugadores(int id) {
        JSONArray jsonEquipo = JsonUtiles.informacionApiToJsonArrayJugadores2(ApiFootball.conectarApi("https://v3.football.api-sports.io/players/squads?team=" + id, "01954488b25482303751a41d84b764a7"));
        try {
            JsonUtiles.grabar(jsonEquipo, "api" + jsonEquipo.getJSONObject(0).getJSONObject("team").getString("name").replace(" ", "") + "Squad");
        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }
    }
}
