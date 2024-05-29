package Clases;
import java.util.ArrayList;
import java.util.HashMap;

public class Torneo {
    //Atributos
    private String nombre;
    private HashMap<Integer, Fecha> fechas;
    private ArrayList<Equipo> tabla;
    private ArrayList<Jugador> goleadores;
    private Equipo primero;
    private Jugador goleador;
    //Constructores
    public Torneo() {
        setNombre("");
        setFechas(new HashMap<>());
        setTabla(new ArrayList<>());
        setGoleadores(new ArrayList<>());
        setPrimero(new Equipo());
        setGoleador(new Jugador());
    }
    public Torneo(String nombreAux) {
        setNombre(nombreAux);
        setFechas(new HashMap<>());
        setTabla(new ArrayList<>());
        setGoleadores(new ArrayList<>());
        setPrimero(new Equipo());
        setGoleador(new Jugador());
    }
    //Metodos
    private void setNombre(String nombreAux) {
        this.nombre = nombreAux;
    }
    private String getNombre() {
        return nombre;
    }
    private void setFechas(HashMap<Integer, Fecha> fechasAux) {
        this.fechas = fechasAux;
    }
    private HashMap<Integer, Fecha> getFechas() {
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
    private void setPrimero(Equipo campeonAux) {
        this.primero = campeonAux;
    }
    private Equipo getPrimero() {
        return primero;
    }
    private void setGoleador(Jugador goleadorAux) {
        this.goleador = goleadorAux;
    }
    private Jugador getGoleador() {
        return goleador;
    }
    public void agregarEquipo(Equipo equipo) {
        getTabla().add(equipo);
    }
    public void sacarEquipo(Equipo equipo) {
        for(int i = 0; i < getTabla().size(); i++) {
            if(getTabla().get(i).equals(equipo)) {
                getTabla().remove(equipo); //ver si queda el indice colgado o se corren todos los siguientes 1 posicion para atras
            }
        }
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
    private void calcularPrimero() {
        setPrimero(getTabla().getFirst());
    }
    @Override
    public String toString() {
        calcularPrimero();
        return "\u001B[30;47m " + String.format("%-88s", getNombre()) + "\u001B[0m\n\u001B[30;100m " + String.format("%-88s", "Primero: " +
                getPrimero().getNombre()) + "\u001B[0m\n\u001B[30;47m " + String.format("%-88s", "Goleador: " + getGoleador().getNombre()) + "\u001B[0m\n" + devolverTabla();
    }
}
