package clases;
import java.io.Serializable;
public class Torneo implements Serializable {
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
    public Contenedor<Jugador> getGoleadores() {
        return goleadores;
    } //por ahora no se usa
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
        String s = "\u001B[30;100m " + String.format("%-88s", "Pos | Puesto | Num | Nombre (Edad)                             | Goles") + "\u001B[0m\n";
        for(int i = 0; i < goleadores.size(); i++) {
            if(i % 2 == 0) {
                s += "\u001B[30;47m" + "  " + String.format("%2s", (i + 1)) + " | " + goleadores.get(i) + " | " + String.format("%5s", goleadores.get(i).getGoles()) + "                  \u001B[0m\n";
            }
            else {
                s += "\u001B[30;100m" + "  " + String.format("%2s", (i + 1)) + " | " + goleadores.get(i) + " | " + String.format("%5s", goleadores.get(i).getGoles()) + "                  \u001B[0m\n";
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
}