import clases.*;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Torneo torneo = new Torneo("Liga Profesional de Futbol 2024");

        //JsonUtiles.grabarTabla();

        //JsonUtiles.grabarFixture();

        JsonUtiles.jsonToTorneo2(torneo, "apiStandings");

        System.out.println(torneo);

        /*Contenedor<Jugador> jugadorContenedor = torneo.getTabla().get(0).getJugadores();
        for (int i = 0; i < jugadorContenedor.size(); i++)
        {
            System.out.println(jugadorContenedor.get(i).toString());
        }*/

        Contenedor<Fecha> fechas = torneo.getFechas();


        for (int i = 0; i < fechas.size(); i++)
        {
            System.out.println();
            System.out.println("FECHA:" + fechas.get(i).getNumeroFecha());
            Contenedor<PartidoFutbol> partidos = fechas.get(i).getPartidos();
            for (int j = 0; j < partidos.size(); j++)
            {
                System.out.println(partidos.get(j).toString());
            }
        }

        //Menu.menu();
    }
}