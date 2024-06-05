package clases;
import interfaces.IExportarJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
public class Torneo implements Serializable, IExportarJson {
    //Atributos
    private String nombre;
    private Contenedor<Fecha> fixture;
    private Contenedor<Equipo> tabla;
    private Contenedor<Jugador> goleadores;
    //Constructores
    public Torneo(String nombreAux) {
        this.nombre = nombreAux;
        this.fixture = new Contenedor<>();
        this.tabla = new Contenedor<>();
        this.goleadores = new Contenedor<>();
    }
    //Metodos
    public String getNombre() {
        return nombre;
    }
    public Contenedor<Fecha> getFixture() {
        return fixture;
    }
    public Contenedor<Equipo> getTabla() {
        return tabla;
    }
    public void agregarEquipo(Equipo equipo) {
        tabla.add(equipo);
    }
    public void agregarFixture(Contenedor<Fecha> fixtureAux) {
        this.fixture = fixtureAux;
    }
    public void agregarGoleadores(Contenedor<Jugador> goleadoresAux) {
        goleadores = goleadoresAux;
    }
    private String devolverTabla() {
        String s = "\u001B[30;100m" + " Pos | Equipo                         | Pts | PJ | PG | PE | PP | GF | GC | Dif | #ID    " + "\u001B[0m\n";
        s += "\u001B[30;42m" + "   1 | " + tabla.getFirst() + " | " + "\u001B[97;42m" + String.format("%7s", "[" + tabla.getFirst().getIdEquipo() + "] ") + "\u001B[0m\n";
        for(int i = 1; i < tabla.size(); i++) {
            if(i % 2 == 0) {
                s += "\u001B[30;100m" + "  " + String.format("%2s", (i + 1)) + " | " + tabla.get(i) + " | " + "\u001B[97;100m" + String.format("%7s", "[" + tabla.get(i).getIdEquipo() + "] ") + "\u001B[0m\n";
            }
            else {
                s += "\u001B[30;47m" + "  " + String.format("%2s", (i + 1)) + " | " + tabla.get(i) + " | " + "\u001B[97;47m" + String.format("%7s", "[" + tabla.get(i).getIdEquipo() + "] ") + "\u001B[0m\n";
            }
        }
        return s;
    }
    public String devolverFixtureEquipo(String nombre) {
        String s = "\u001B[30;100m " + String.format("%-88s", "Partidos") + "\u001B[0m\n";
        s += "\u001B[30;47m" + "                                      Local - Visitante                                  " + "\u001B[0m\n";
        for(int i = 0; i < fixture.size(); i++) {
            for(int j = 0; j < fixture.get(i).getPartidos().size(); j++) {
                if(fixture.get(i).getPartidos().get(j).getLocal().getNombre().equals(nombre) || fixture.get(i).getPartidos().get(j).getVisitante().getNombre().equals(nombre)) {
                    if(i % 2 == 0) {
                        s += "\u001B[30;100m " + String.format("%-4s", "F" + (i + 1)) + fixture.get(i).getPartidos().get(j) + "\u001B[0m\n";
                    }
                    else {
                        s += "\u001B[30;47m " + String.format("%-4s", "F" + (i + 1)) + fixture.get(i).getPartidos().get(j)+ "\u001B[0m\n";
                    }
                }
            }
        }
        return s;
    }
    public String devolverGoleadores() {
        String s = "\u001B[30;100m " + String.format("%-88s", "Pos | Puesto | Num | Nombre (Edad)                            | Goles") + "\u001B[0m\n";
        for(int i = 0; i < goleadores.size(); i++) {
            if(i % 2 == 0) {
                s += "\u001B[30;47m" + "  " + String.format("%2s", (i + 1)) + " | " + String.format("%-82s", goleadores.get(i)) + "\u001B[0m\n";
            }
            else {
                s += "\u001B[30;100m" + "  " + String.format("%2s", (i + 1)) + " | " + String.format("%-82s", goleadores.get(i)) + "\u001B[0m\n";
            }
        }
        return s;
    }
    @Override
    public String toString() {
        return "\u001B[30;100m " + String.format("%-88s", nombre) +
                "\u001B[0m\n\u001B[30;47m " + String.format("%-88s", "Goleador: " + goleadores.getFirst().getNombre()) +
                "\u001B[0m\n" + devolverTabla();
    }
    @Override
    public JSONObject exportarJson() {
        JSONObject joTorneo = new JSONObject();
        try {
            joTorneo.put("nombre",nombre); //nombre
            JSONArray jaFixture = new JSONArray();
            for(int i = 0; i < fixture.size(); i++) {
                JSONObject joFecha = new JSONObject();
                joFecha.put("fecha", fixture.get(i).getNumeroFecha()); //numero de fecha
                JSONArray jaPartidos = new JSONArray();
                for(int j = 0; j < fixture.get(i).getPartidos().size(); j++) {
                    JSONObject joPartido = new JSONObject();
                    joPartido.put("local", fixture.get(i).getPartidos().get(j).getLocal().getNombre()); //local
                    joPartido.put("visitante", fixture.get(i).getPartidos().get(j).getVisitante().getNombre()); //visitante
                    joPartido.put("golesL", fixture.get(i).getPartidos().get(j).getGolesL()); //goles local
                    joPartido.put("golesV", fixture.get(i).getPartidos().get(j).getGolesV()); //goles visitante
                    jaPartidos.put(joPartido); //partido
                }
                joFecha.put("partidos", jaPartidos); //partidos
                jaFixture.put(joFecha); //fecha
            }
            joTorneo.put("fixture", jaFixture); //fixture
            JSONArray jaTabla = new JSONArray();
            for(int i = 0; i < tabla.size(); i++) {
                JSONObject joEquipo = new JSONObject();
                joEquipo.put("equipo", tabla.get(i).getNombre()); //equipo
                joEquipo.put("puntos", tabla.get(i).getPuntos()); //puntos
                JSONObject joTecnico = new JSONObject();
                joTecnico.put("nombre", tabla.get(i).getTecnico().getNombre()); //nombre
                joTecnico.put("edad", tabla.get(i).getTecnico().getEdad()); //edad
                JSONArray jaTrayectoria = new JSONArray();
                for(int j = 0; j < tabla.get(i).getTecnico().getTrayectoria().size(); j++) {
                    jaTrayectoria.put(tabla.get(i).getTecnico().getTrayectoria().get(j)); //equipo
                }
                joTecnico.put("trayectoria", jaTrayectoria); //trayectoria
                joEquipo.put("tecnico", joTecnico); //tecnico
                JSONArray jaJugadores = new JSONArray();
                for(int j = 0; j < tabla.get(i).getJugadores().size(); j++) {
                    JSONObject joJugador = new JSONObject();
                    joJugador.put("nombre", tabla.get(i).getJugadores().get(j).getNombre()); //nombre
                    joJugador.put("edad", tabla.get(i).getJugadores().get(j).getEdad()); //edad
                    joJugador.put("puesto", tabla.get(i).getJugadores().get(j).getPuesto()); //puesto
                    joJugador.put("numero", tabla.get(i).getJugadores().get(j).getNumero()); //numero
                    joJugador.put("goles", tabla.get(i).getJugadores().get(j).getGoles()); //goles
                    jaJugadores.put(joJugador); //jugador
                }
                joEquipo.put("jugadores", jaJugadores); //jugadores
                joEquipo.put("partidosJugados", tabla.get(i).getPartidosJugados()); //partidos jugados
                joEquipo.put("partidosGanados", tabla.get(i).getPartidosGanados()); //partidos ganados
                joEquipo.put("partidosEmpatados", tabla.get(i).getPartidosEmpatados()); //partidos empatados
                joEquipo.put("partidosPerdidos", tabla.get(i).getPartidosPerdidos()); //partidos perdidos
                joEquipo.put("golesAFavor", tabla.get(i).getGolesAFavor()); //goles a favor
                joEquipo.put("golesEnContra", tabla.get(i).getGolesEnContra()); //goles en contra
                joEquipo.put("diferenciaGoles", tabla.get(i).getDiferenciaGoles()); //diferencia de goles
                jaTabla.put(joEquipo); //equipo
            }
            joTorneo.put("tabla", jaTabla); //tabla
            JSONArray jaGoleadores = new JSONArray();
            for(int i = 0; i < goleadores.size(); i++) {
                JSONObject jaGoleador = new JSONObject();
                jaGoleador.put("nombre", goleadores.get(i).getNombre()); //nombre
                jaGoleador.put("edad", goleadores.get(i).getEdad()); //edad
                jaGoleador.put("puesto", goleadores.get(i).getPuesto()); //puesto
                jaGoleador.put("numero", goleadores.get(i).getNumero()); //numero
                jaGoleador.put("goles", goleadores.get(i).getGoles()); //goles
                jaGoleadores.put(jaGoleador); //goleador
            }
            joTorneo.put("goleadores", jaGoleadores); //goleadores
        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
        return joTorneo;
    }
}