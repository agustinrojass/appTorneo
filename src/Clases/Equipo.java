package Clases;
import java.util.HashMap;

public class Equipo {
    //Atributos
    private String nombre;
    private Tecnico tecnico;
    private HashMap<Integer, Jugador> jugadores;
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
        setJugadores(new HashMap<>());

        setPuntos(0);

        setPartidosJugados(0);
        setPartidosGanados(0);
        setPartidosEmpatados(0);
        setPartidosPerdidos(0);

        setDiferenciaGoles(0);
        setGolesAFavor(0);
        setGolesEnContra(0);
    }
    public Equipo(String nombreAux) {
        setNombre(nombreAux);
        setTecnico(new Tecnico());
        setJugadores(new HashMap<>());

        setPuntos(0);

        setPartidosJugados(0);
        setPartidosGanados(0);
        setPartidosEmpatados(0);
        setPartidosPerdidos(0);

        setDiferenciaGoles(0);
        setGolesAFavor(0);
        setGolesEnContra(0);
    }
    //Metodos
    public void setNombre(String nombreAux) {
        this.nombre = nombreAux;
    }
    public String getNombre() {
        return nombre;
    }

    public void setTecnico(Tecnico tecnicoAux) {
        this.tecnico = tecnicoAux;
    }
    public Tecnico getTecnico() {
        return tecnico;
    }
    public void setJugadores(HashMap<Integer, Jugador> jugadoresAux) {
        this.jugadores = jugadoresAux;
    }
    public HashMap<Integer, Jugador> getJugadores() {
        return jugadores;
    }

    public void setPuntos(int puntosAux) {
        this.puntos = puntosAux;
    }
    public int getPuntos() {
        return puntos;
    }
    public void setPartidosJugados(int partidosJugadosAux) {
        this.partidosJugados = partidosJugadosAux;
    }
    public int getPartidosJugados() {
        return partidosJugados;
    }
    public void setPartidosGanados(int partidosGanadosAux) {
        this.partidosGanados = partidosGanadosAux;
    }
    public int getPartidosGanados() {
        return partidosGanados;
    }
    public void setPartidosEmpatados(int partidosEmpatadosAux) {
        this.partidosEmpatados = partidosEmpatadosAux;
    }
    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }
    public void setPartidosPerdidos(int partidosPerdidosAux) {
        this.partidosPerdidos = partidosPerdidosAux;
    }
    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }
    public void setDiferenciaGoles(int diferenciaGolesAux) {
        this.diferenciaGoles = diferenciaGolesAux;
    }
    public int getDiferenciaGoles() {
        return diferenciaGoles;
    }
    public void setGolesAFavor(int golesAFavorAux) {
        this.golesAFavor = golesAFavorAux;
    }
    public int getGolesAFavor() {
        return golesAFavor;
    }
    public void setGolesEnContra(int golesEnContraAux) {
        this.golesEnContra = golesEnContraAux;
    }
    public int getGolesEnContra() {
        return golesEnContra;
    }

    @Override
    public String toString() {
        return String.format("%-20s" ,getNombre()) + ;
    }
}
