package clases;
import java.io.Serializable;
public class Equipo implements Serializable {
    //Atributos
    private int idEquipo;
    private String nombre;
    private Tecnico tecnico;
    private Contenedor<Jugador> jugadores;
    private int puntos;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesAFavor;
    private int golesEnContra;
    private int diferenciaGoles;
    //Constructores
    public Equipo(int idEquipoAux, String nombreAux, Tecnico tecnicoAux, Contenedor<Jugador> jugadoresAux, int puntosAux, int partidosJugadosAux, int partidosGanadosAux, int partidosEmpatadosAux, int partidosPerdidosAux, int golesAFavorAux, int golesEnContraAux, int diferenciaGolesAux) {
        this.idEquipo = idEquipoAux;
        this.nombre = nombreAux;
        this.tecnico = tecnicoAux;
        this.jugadores = jugadoresAux;
        this.puntos = puntosAux;
        this.partidosJugados = partidosJugadosAux;
        this.partidosGanados = partidosGanadosAux;
        this.partidosEmpatados = partidosEmpatadosAux;
        this.partidosPerdidos = partidosPerdidosAux;
        this.golesAFavor = golesAFavorAux;
        this.golesEnContra = golesEnContraAux;
        this.diferenciaGoles = diferenciaGolesAux;
    }
    public Equipo(int idEquipo, String nombre) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
    }
    //Metodos
    public int getIdEquipo() {
        return idEquipo;
    }
    public String getNombre() {
        return nombre;
    }
    public Tecnico getTecnico() {
        return tecnico;
    }
    public Contenedor<Jugador> getJugadores() {
        return jugadores;
    }
    public int getPuntos() {
        return puntos;
    }
    public int getPartidosJugados() {
        return partidosJugados;
    }
    public int getPartidosGanados() {
        return partidosGanados;
    }
    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }
    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }
    public int getGolesAFavor() {
        return golesAFavor;
    }
    public int getGolesEnContra() {
        return golesEnContra;
    }
    public int getDiferenciaGoles() {
        return diferenciaGoles;
    }
    public String devolverEquipoCompleto(int pos) {
        String s = "\u001B[30;100m " + "Pos | Equipo                         | Pts | PJ | PG | PE | PP | GF | GC | Dif | #ID    " + "\u001B[0m\n";
        s += "\u001B[30;47m " + String.format("%3s", pos) + " | " + this + " | " +"\u001B[97;47m" + String.format("%7s", "[" + idEquipo + "] ") + "\u001B[0m\n";
        s += "\u001B[30;100m " + "Tecnico: " + String.format("%-79s", tecnico) + "\u001B[0m\n\n";
        s += "\u001B[30;100m " + String.format("%-88s", "Puesto | Num | Nombre (Edad)                            | Goles") + "\u001B[0m\n";
        for(int i = 0; i < jugadores.size(); i++) {
            if(i % 2 == 0) {
                s += "\u001B[30;47m " + String.format("%-88s", jugadores.get(i)) + "\u001B[0m\n";
            }
            else {
                s += "\u001B[30;100m " + String.format("%-88s", jugadores.get(i)) + "\u001B[0m\n";
            }
        }
        return s;
    }
    @Override
    public String toString() {
        String s = String.format("%-30s", nombre) + " | " + String.format("%3s", puntos) + " | " + String.format("%2s", partidosJugados) +
                " | " + String.format("%2s", partidosGanados) + " | " + String.format("%2s", partidosEmpatados) + " | " + String.format("%2s", partidosPerdidos) +
                " | " + String.format("%2s", golesAFavor) + " | " + String.format("%2s", golesEnContra) + " | ";
        if(diferenciaGoles > 0) {
            s += String.format("%3s", "+" + diferenciaGoles);
        }
        else {
            s += String.format("%3s", diferenciaGoles);
        }
        return s;
    }
}