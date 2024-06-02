package clases;
import java.io.Serializable;
import java.util.ArrayList;

public class Torneo implements Serializable {
    //Atributos
    private String nombre;
    private Contenedor<Fecha> fechas;
    private Contenedor<Equipo> tabla;
    private Contenedor<Jugador> goleadores;
    //Constructores
    public Torneo(String nombreAux) {
        this.nombre = nombreAux;
        this.fechas = new Contenedor<>();
        this.tabla = new Contenedor<>();
        this.goleadores = new Contenedor<>();
    }
    //Metodos
    public String getNombre() {
        return nombre;
    }
    public Contenedor<Fecha> getFechas() {
        return fechas;
    } //por ahora no se usa
    public Contenedor<Equipo> getTabla() {
        return tabla;
    } //por ahora no se usa
    public Contenedor<Jugador> getGoleadores() {
        return goleadores;
    } //por ahora no se usa
    public void agregarEquipo(Equipo equipo) {
        tabla.add(equipo);
    }
    public void agregarFixture(ArrayList<Fecha> fixtureFechas) {
        fechas = new Contenedor<>(fixtureFechas);           //agrego el fixture que es un Array a el contenedor de fechas
    }
    private String devolverTabla() {
        String s = "\u001B[30;100m" + " Pos | Equipo                         | Pts | PJ | PG | PE | PP | GF | GC | Dif | #ID    " + "\u001B[0m\n";
        s += "\u001B[30;42m" + "   1 | " + tabla.getContenedor().getFirst() + " | " + "\u001B[97;42m" + String.format("%7s", "[" + tabla.getFirst().getIdEquipo() + "] ") + "\u001B[0m\n";
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
    @Override
    public String toString() {
        String s = "\u001B[30;100m " + String.format("%-88s", nombre) +
                "\u001B[0m\n\u001B[30;47m " + String.format("%-88s", "Goleador: " /*+ goleadores.devolverPrimero().getNombre()*/) +
                "\u001B[0m\n" + devolverTabla();
        return s;
    }
}