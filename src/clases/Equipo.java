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
    public Equipo() {
        this.idEquipo = 0;
        this.nombre = "";
        this.tecnico = new Tecnico();
        this.jugadores = new Contenedor<>();
        this.puntos = 0;
        this.partidosJugados = 0;
        this.partidosGanados = 0;
        this.partidosEmpatados = 0;
        this.partidosPerdidos = 0;
        this.golesAFavor = 0;
        this.golesEnContra = 0;
        this.diferenciaGoles = 0;
    } //se usa en PartidoFutbol(), pero despues no se va a usar
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
    public void ganarPartido(int golesAFavorAux, int golesEnContraAux) {
        this.puntos = puntos + 3;
        this.partidosJugados = partidosJugados + 1;
        this.partidosGanados = partidosGanados + 1;
        actualizarGoles(golesAFavorAux, golesEnContraAux);
    } //por ahora no se usa
    public void empatarPartido(int golesAFavorAux, int golesEnContraAux) {
        this.puntos = puntos + 1;
        this.partidosJugados = partidosJugados + 1;
        this.partidosEmpatados = partidosEmpatados + 1;
        actualizarGoles(golesAFavorAux, golesEnContraAux);
    } //por ahora no se usa
    public void perderPartido(int golesAFavorAux, int golesEnContraAux) {
        this.partidosJugados = partidosJugados + 1;
        this.partidosPerdidos = partidosPerdidos + 1;
        actualizarGoles(golesAFavorAux, golesEnContraAux);
    } //por ahora no se usa
    public void actualizarGoles(int golesAFavorAux, int golesEnContraAux) {
        this.golesAFavor = golesAFavorAux + golesAFavorAux;
        this.golesEnContra = golesEnContra + golesEnContraAux;
        this.diferenciaGoles = golesAFavor - golesEnContra;
    } //por ahora no se usa
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