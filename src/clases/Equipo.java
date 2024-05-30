package clases;
import java.io.Serializable;
import java.util.ArrayList;

public class Equipo implements Serializable {
    //Atributos
    private int idEquipo;
    private String nombre;
    private Tecnico tecnico;
    private ArrayList<Jugador> jugadores;
    private int puntos;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int diferenciaGoles;
    private int golesAFavor;
    private int golesEnContra;
    //Construtores
    public Equipo() {
        setNombre("");
        setTecnico(new Tecnico());
        setJugadores(new ArrayList<>());
        setPuntos(0);
        setPartidosJugados(0);
        setPartidosGanados(0);
        setPartidosEmpatados(0);
        setPartidosPerdidos(0);
        setDiferenciaGoles(0);
        setGolesAFavor(0);
        setGolesEnContra(0);
    }
    public Equipo(int idEquipoAux, String nombreAux, ArrayList<Jugador> jugadoresAux, int puntosAux, int partidosJugadosAux, int partidosGanadosAux, int partidosEmpatadosAux, int partidosPerdidosAux, int diferenciaGolesAux, int golesAFavorAux, int golesEnContraAux) {
        setIdEquipo(idEquipoAux);
        setNombre(nombreAux);
        setTecnico(new Tecnico());
        setJugadores(jugadoresAux);
        setPuntos(puntosAux);
        setPartidosJugados(partidosJugadosAux);
        setPartidosGanados(partidosGanadosAux);
        setPartidosEmpatados(partidosEmpatadosAux);
        setPartidosPerdidos(partidosPerdidosAux);
        setDiferenciaGoles(diferenciaGolesAux);
        setGolesAFavor(golesAFavorAux);
        setGolesEnContra(golesEnContraAux);
    }
    //Metodos
    private void setIdEquipo(int idEquipoAux) {
        this.idEquipo = idEquipoAux;
    }
    public int getIdEquipo() {
        return idEquipo;
    }
    private void setNombre(String nombreAux) {
        this.nombre = nombreAux;
    }
    public String getNombre() {
        return nombre;
    }
    private void setTecnico(Tecnico tecnicoAux) {
        this.tecnico = tecnicoAux;
    }
    private Tecnico getTecnico() {
        return tecnico;
    }
    private void setJugadores(ArrayList<Jugador> jugadoresAux) {
        this.jugadores = jugadoresAux;
    }
    private ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    private void setPuntos(int puntosAux) {
        this.puntos = puntosAux;
    }
    private int getPuntos() {
        return puntos;
    }
    private void setPartidosJugados(int partidosJugadosAux) {
        this.partidosJugados = partidosJugadosAux;
    }
    private int getPartidosJugados() {
        return partidosJugados;
    }
    private void setPartidosGanados(int partidosGanadosAux) {
        this.partidosGanados = partidosGanadosAux;
    }
    private int getPartidosGanados() {
        return partidosGanados;
    }
    private void setPartidosEmpatados(int partidosEmpatadosAux) {
        this.partidosEmpatados = partidosEmpatadosAux;
    }
    private int getPartidosEmpatados() {
        return partidosEmpatados;
    }
    private void setPartidosPerdidos(int partidosPerdidosAux) {
        this.partidosPerdidos = partidosPerdidosAux;
    }
    private int getPartidosPerdidos() {
        return partidosPerdidos;
    }
    private void setDiferenciaGoles(int diferenciaGolesAux) {
        this.diferenciaGoles = diferenciaGolesAux;
    }
    private int getDiferenciaGoles() {
        return diferenciaGoles;
    }
    private void setGolesAFavor(int golesAFavorAux) {
        this.golesAFavor = golesAFavorAux;
    }
    private int getGolesAFavor() {
        return golesAFavor;
    }
    private void setGolesEnContra(int golesEnContraAux) {
        this.golesEnContra = golesEnContraAux;
    }
    private int getGolesEnContra() {
        return golesEnContra;
    }
    public void ganarPartido(int golesAFavorAux, int golesEnContraAux) {
        setPuntos(getPuntos() + 3);
        setPartidosJugados(getPartidosJugados() + 1);
        setPartidosGanados(getPartidosGanados() + 1);
        actualizarGoles(golesAFavorAux, golesEnContraAux);
    } //probablemente no se use
    public void empatarPartido(int golesAFavorAux, int golesEnContraAux) {
        setPuntos(getPuntos() + 1);
        setPartidosJugados(getPartidosJugados() + 1);
        setPartidosEmpatados(getPartidosEmpatados() + 1);
        actualizarGoles(golesAFavorAux, golesEnContraAux);
    } //probablemente no se use
    public void perderPartido(int golesAFavorAux, int golesEnContraAux) {
        setPartidosJugados(getPartidosJugados() + 1);
        setPartidosPerdidos(getPartidosPerdidos() + 1);
        actualizarGoles(golesAFavorAux, golesEnContraAux);
    } //probablemente no se use
    public void actualizarGoles(int golesAFavorAux, int golesEnContraAux) {
        setGolesAFavor(getGolesAFavor() + golesAFavorAux);
        setGolesEnContra(getGolesEnContra() + golesEnContraAux);
        setDiferenciaGoles(getGolesAFavor() - getGolesEnContra());
    } //probablemente no se use
    @Override
    public String toString() {
        String s = String.format("%-30s", getNombre()) + " | " + String.format("%3s", getPuntos()) + " | " + String.format("%2s", getPartidosJugados()) +
                " | " + String.format("%2s", getPartidosGanados()) + " | " + String.format("%2s", getPartidosEmpatados()) + " | " + String.format("%2s", getPartidosPerdidos()) +
                " | " + String.format("%2s", getGolesAFavor()) + " | " + String.format("%2s", getGolesEnContra()) + " | ";
        if(getDiferenciaGoles() > 0) {
            s += String.format("%3s", "+" + getDiferenciaGoles());
        }
        else {
            s += String.format("%3s", getDiferenciaGoles());
        }
        return s;
    }
    public int compareTo(Object obj) {
        int rta = 0;
        if(obj != null) {
            if(obj instanceof Equipo e) {
                if(getPuntos() > e.getPuntos()) {
                    rta = -1;
                } else if (getPuntos() < e.getPuntos()) {
                    rta = 1;
                } else if (getPuntos() == e.getPuntos()) {
                    if(getDiferenciaGoles() > e.getDiferenciaGoles()) {
                        rta = -1;
                    } else if (getDiferenciaGoles() < e.getDiferenciaGoles()) {
                        rta = 1;
                    } else if (getDiferenciaGoles() == e.getDiferenciaGoles()) {
                        if(getGolesAFavor() > e.getGolesAFavor()) {
                            rta = -1;
                        } else if (getGolesAFavor() < e.getGolesAFavor()) {
                            rta = 1;
                        }
                    }
                }
            }
        }
        return rta;
    }
}
