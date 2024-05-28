package Clases;

public class Jugador extends Persona {
    //Atributos
    private String posicion;
    private int numero;
    private int goles;
    //Constructores
    public Jugador() {
        super();
        setPosicion("");
        setNumero(0);
        setGoles(0);
    }
    public Jugador(String nombreAux, int edadAux, String posicionAux, int numeroAux) {
        super(nombreAux, edadAux);
        setPosicion(posicionAux);
        setNumero(numeroAux);
        setGoles(0);
    }
    //Metodos
    private void setPosicion(String posicionAux) {
        this.posicion = posicionAux;
    }
    private String getPosicion() {
        return posicion;
    }
    private void setNumero(int numeroAux) {
        this.numero = numeroAux;
    }
    public int getNumero() {
        return numero;
    }
    private void setGoles(int golesAux) {
        this.goles = golesAux;
    }
    private int getGoles() {
        return goles;
    }
    @Override
    public String toString() {
        String pos = "";
        if(getPosicion().equalsIgnoreCase("Attacker")) {
            pos = "[DEL] ";
        }
        else if(getPosicion().equalsIgnoreCase("Midfielder")) {
            pos = "[MED] ";
        }
        else if(getPosicion().equalsIgnoreCase("Defender")) {
            pos = "[DEF] ";
        }
        else if(getPosicion().equalsIgnoreCase("Goalkeeper")) {
            pos = "[ARQ] ";
        }
        return pos + String.format("%2s", getNumero()) + " - " + super.toString() + " - " + getGoles();
    }
}

