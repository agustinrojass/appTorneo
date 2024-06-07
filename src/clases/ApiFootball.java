package clases;
import excepciones.DescargaCanceladaException;
import excepciones.StatusCodeException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class ApiFootball {
    public static String conectarApi(String urlString, String apiKey) throws StatusCodeException, ConnectException, DescargaCanceladaException {
        String informacionAPI;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .header("X-apisports-Key", apiKey) //utilizo la apikey para poder establecer la conexion y usar los datos del json de la api
                    .method("GET", HttpRequest.BodyPublishers.noBody()) //me conecto a la api con el metodo GET
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()); //obtengo la respuesta de la conexion
            if(response.statusCode() != 200) { //compruebo que codigo es
                throw new StatusCodeException(response.statusCode());
            }
            else { //recibimos un codigo 200 por lo que la conexion es correcta
                informacionAPI = response.body(); //pasamos la informacion de la respuesta a un string
            }
        }
        catch(ConnectException ex) {
            throw new ConnectException();
        }
        catch(IOException | InterruptedException ex) {
            throw new DescargaCanceladaException(ex.getMessage());
        }
        return informacionAPI; //devolvemos la informacion en formato json de la api, para poder empezar a trabajarlo
    }
}