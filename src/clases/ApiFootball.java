package clases;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiFootball {
    public static String conectarApi(String urlString, String apiKey)
    {
        String informacionAPI = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .header("X-apisports-Key", apiKey) //utilizo la apikey para poder establecer la conexion y usar los datos del json de la api
                    .method("GET", HttpRequest.BodyPublishers.noBody()) //me conecto a la api con el metodo GET
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()); //obtengo la respuesta de la conexion
            int codigoDeRespuesta = response.statusCode(); //obtengo el codigo de respuesta
            if(codigoDeRespuesta != 200) //compruebo que codigo es
            {
                System.out.println("Ocurrio un error" + codigoDeRespuesta + ".");
            }
            else { //recibimos un codigo 200 por lo que la conexion es correcta
                System.out.println("Codigo " + codigoDeRespuesta + ". Conexion segura.");
                informacionAPI = response.body(); //pasamos la informacion de la respuesta a un string
            }
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
        return informacionAPI; //devolvemos la informacion en formato json de la api, para poder empezar a trabajarlo
    }
}