package Clases;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIFootball {

    public static String conectarAPI (String urlString, String apiKey)
    {
        String informacionAPI = null;
        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .header("X-apisports-Key", apiKey)          //utilizo la apiKey para poder establecer la conexion y usar los datos del json de la api
                    .method("GET", HttpRequest.BodyPublishers.noBody())         //me conecto a la API con el metodo GET
                    .build();


            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());     //obtengo la respuesta de la conexion

            int codigoDeRespuesta = response.statusCode();      //obtengo el codigo de respuesta

            if(codigoDeRespuesta != 200)        //compruebo que codigo es
            {
                System.out.println("Ocurrio un error" + codigoDeRespuesta);
            }
            else        //recibimos un codigo 200 por lo que la conexion es correcta
            {
                System.out.println("Codigo " + codigoDeRespuesta + ". Conexion segura");

                informacionAPI = response.body();  //Pasamos la informacion de la respuesta a un string


            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return informacionAPI;          //devolvemos la informacion en formato json de la API, para poder empezar a trabajarlo
    }

}
