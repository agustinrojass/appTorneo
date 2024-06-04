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
    //Construtores
    public Equipo(int idEquipoAux, String nombreAux, Contenedor<Jugador> jugadoresAux, int puntosAux, int partidosJugadosAux, int partidosGanadosAux, int partidosEmpatadosAux, int partidosPerdidosAux, int golesAFavorAux, int golesEnContraAux, int diferenciaGolesAux) {
        this.idEquipo = idEquipoAux;
        this.nombre = nombreAux;
        this.tecnico = new Tecnico();
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
    public Equipo(int idEquipo, String nombre) {        //se usa en jsonToFixture pq era la unica info que traia la api
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
    } //por ahora no se usa
    public Contenedor<Jugador> getJugadores() {
        return jugadores;
    } //por ahora no se usa
    public String devolverEquipoCompleto(int pos) {
        String s = "\u001B[30;100m" + " Pos | Equipo                         | Pts | PJ | PG | PE | PP | GF | GC | Dif | #ID    " + "\u001B[0m\n";
        s += "\u001B[30;47m " + String.format("%3s", pos) + " | " + toString() + " | " +"\u001B[97;47m" + String.format("%7s", "[" + idEquipo + "] ") + "\u001B[0m\n\n";
        s += "\u001B[30;100m " + String.format("%-88s", "Puesto | Num | Nombre (Edad)") + "\u001B[0m\n";
        for(int i = 0; i < jugadores.size(); i++) {
            if(i % 2 == 0) {
                s += "\u001B[30;47m " + String.format("%-88s", jugadores.get(i)) + String.format("%s", jugadores.get(i).getGoles()) + "\u001B[0m\n";
            }
            else {
                s += "\u001B[30;100m " + String.format("%-88s", jugadores.get(i)) + String.format("%s", jugadores.get(i).getGoles()) + "\u001B[0m\n";
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
    public int compareTo(Object obj) {
        int rta = 0;
        if(obj != null) {
            if(obj instanceof Equipo e) {
                if(puntos > e.puntos) {
                    rta = -1;
                }
                else if(puntos < e.puntos) {
                    rta = 1;
                }
                else {
                    if(diferenciaGoles > e.diferenciaGoles) {
                        rta = -1;
                    }
                    else if(diferenciaGoles < e.diferenciaGoles) {
                        rta = 1;
                    }
                    else {
                        if(golesAFavor > e.golesAFavor) {
                            rta = -1;
                        }
                        else if (golesAFavor < e.golesAFavor) {
                            rta = 1;
                        }
                    }
                }
            }
        }
        return rta;
    } //por ahora no se usa
}