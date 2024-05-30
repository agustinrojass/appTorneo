package clases;
import java.io.Serializable;
import java.util.ArrayList;

public class Torneo implements Serializable {
    //Atributos
    private String nombre;
    private ArrayList<Fecha> fechas;
    private ArrayList<Equipo> tabla;
    private ArrayList<Jugador> goleadores;
    //Constructores
    public Torneo() {
        setNombre("");
        setFechas(new ArrayList<>());
        setTabla(new ArrayList<>());
        setGoleadores(new ArrayList<>());
    }
    public Torneo(String nombreAux) {
        setNombre(nombreAux);
        setFechas(new ArrayList<>());
        setTabla(new ArrayList<>());
        setGoleadores(new ArrayList<>());
    }
    //Metodos
    private void setNombre(String nombreAux) {
        this.nombre = nombreAux;
    }
    public String getNombre() {
        return nombre;
    }
    private void setFechas(ArrayList<Fecha> fechasAux) {
        this.fechas = fechasAux;
    }
    private ArrayList<Fecha> getFechas() {
        return fechas;
    }
    private void setTabla(ArrayList<Equipo> tablaAux) {
        this.tabla = tablaAux;
    }
    public ArrayList<Equipo> getTabla() {
        return tabla;
    }
    private void setGoleadores(ArrayList<Jugador> goleadoresAux) {
        this.goleadores = goleadoresAux;
    }
    private ArrayList<Jugador> getGoleadores() {
        return goleadores;
    }
    public void agregarEquipo(Equipo equipo) {
        getTabla().add(equipo);
    }
    private String devolverTabla() {
        String s = "\u001B[30;100m" + " Pos | Equipo                         | Pts | PJ | PG | PE | PP | GF | GC | Dif | #ID    " + "\u001B[0m\n";
        //getTabla().sort(Equipo::compareTo); //ordena la tabla (lista)
        s += "\u001B[30;42m" + "   1 | " + getTabla().getFirst() + " | " + "\u001B[97;42m" + String.format("%7s", "[" + getTabla().getFirst().getIdEquipo() + "] ") + "\u001B[0m\n";
        for(int i = 1; i < getTabla().size(); i++) {
            if(i % 2 == 0) {
                s += "\u001B[30;100m" + "  " + String.format("%2s", (i + 1)) + " | " + getTabla().get(i) + " | " + "\u001B[97;100m" + String.format("%7s", "[" + getTabla().get(i).getIdEquipo() + "] ") + "\u001B[0m\n";
            }
            else {
                s += "\u001B[30;47m" + "  " + String.format("%2s", (i + 1)) + " | " + getTabla().get(i) + " | " + "\u001B[97;47m" + String.format("%7s", "[" + getTabla().get(i).getIdEquipo() + "] ") + "\u001B[0m\n";
            }
        }
        return s;
    }
    @Override
    public String toString() {
        String s = "\u001B[30;100m " + String.format("%-88s", getNombre()) +
                "\u001B[0m\n\u001B[30;47m " + String.format("%-88s", "Goleador: " /*+ getGoleadores().getFirst().getNombre()*/) +
                "\u001B[0m\n" + devolverTabla();
        return s;
    }
}