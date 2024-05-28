package Clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonUtiles {

    public static JSONArray informacionToJsonTabla(String informacionAPI) {

        JSONObject jsonObject;
        JSONArray jsonResponse;
        JSONObject jsonLiga;
        JSONArray jsonTabla;
        try {
            assert informacionAPI != null;
            jsonObject = new JSONObject(informacionAPI);
            jsonResponse = jsonObject.getJSONArray("response");

            jsonLiga = jsonResponse.getJSONObject(0).getJSONObject("league");
            jsonTabla = jsonLiga.getJSONArray("standings").getJSONArray(0);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonTabla;
    }

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

    public static JSONArray informacionAPItoJsonPlayers(String informacionAPI) {

        JSONObject jsonObject;
        JSONArray jsonResponse;
        JSONArray jsonPlayers;
        try {
            assert informacionAPI != null;
            jsonObject = new JSONObject(informacionAPI);
            jsonResponse = jsonObject.getJSONArray("response");
            jsonPlayers = jsonResponse.getJSONObject(0).getJSONArray("players");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonPlayers;
    }

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

    public static HashMap<Integer, Jugador> jsonToPlantelPorID(int idEquipo) throws Exception {

        HashMap<Integer, Jugador> hashMapEquipo = new HashMap<>();
        String urlStringEquipo = "https://v3.football.api-sports.io/players/squads?team=" + idEquipo;     //url con el id del equipo a acceder ----> ya no necesaria porque los planteles estan cargadas en archivos
        String apiKey = "e322d3134e96e5ca6f13792f4df66ed5";     //key personal de la API                    //por eso se pide el nombre del equipo en la v2 de esta funcion, para acceder al archivo del nombre del equipo

        String informacionAPI = APIFootball.conectarAPI(urlStringEquipo, apiKey);

        JSONArray jsonPlayers = informacionAPItoJsonPlayers(informacionAPI);

        for (int i = 0; i < jsonPlayers.length(); i++) {
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
                } else {
                    numCamiseta = jsonPlayers.getJSONObject(i).getInt("number");        //si no es null, lo guardo en la variable

                }

                jugador = new Jugador(nombre, edad, posicion, numCamiseta);

            } catch (JSONException e) {
                throw new JSONException("Excepcion de tipo JSON");
            }
            if (numCamiseta != -1) {
                hashMapEquipo.put(numCamiseta, jugador);
            }
        }

        return hashMapEquipo;
    }

    /*
    public static void grabarArchivo(JSONArray array, String nombre) throws IOException {
        //DataOutputStream dataOutputStream = null;
        FileWriter file = null;
        try {
            /*FileOutputStream fileOutputStream = new FileOutputStream(nombre + ".json");
            dataOutputStream = new DataOutputStream(fileOutputStream);

            dataOutputStream.writeUTF(array.toString());*/
 /*           file = new FileWriter(nombre +".json");
            file.write(array.toString());
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
   /*         reader = new BufferedReader(new FileReader(archivo + ".json"));
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
   /*             assert reader != null;
                reader.close();
            }
            catch (IOException ex)
            {
                throw new IOException("No se pudo cerrar el archivo");
            }
        }

        return informacion;
    }
*/

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




    public static void grabarPlantelToArchivo(Torneo torneo) throws IOException {
        DataOutputStream dataOutputStream = null;
        try {
            for (int i = 21 ; i < torneo.getTabla().size(); i++)
            {
                String urlStringEquipo = "https://v3.football.api-sports.io/players/squads?team=" + torneo.getTabla().get(i).getIdEquipo();
                String apiKey = "e322d3134e96e5ca6f13792f4df66ed5";     //key personal de la API
                String informacionAPI = APIFootball.conectarAPI(urlStringEquipo, apiKey);

                FileOutputStream fileOutputStream = new FileOutputStream("plantelDe" + torneo.getTabla().get(i).getNombre() + ".bin");
                dataOutputStream = new DataOutputStream(fileOutputStream);

                dataOutputStream.writeUTF(informacionAPI);
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Archivo no encontrado");
        } catch (IOException e) {
            throw new IOException("Error io");
        }
        finally {
            try
            {
                assert dataOutputStream != null;
                dataOutputStream.close();
            }
            catch (IOException ex)
            {
                throw new IOException("No se pudo cerrar el archivo");
            }
        }
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





}
