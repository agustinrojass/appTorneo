package clases;
import enumeraciones.Puesto;
public class Jugador extends Persona {
    //Atributos
    private Puesto puesto;
    private int numero;
    private int goles;
    //Constructores
    public Jugador(String nombreAux, int edadAux, String puestoAux, int numeroAux) {
        super(nombreAux, edadAux);
        this.puesto = establecerPuesto(puestoAux);
        this.numero = numeroAux;
        this.goles = 0;
    }
    public Jugador(String nombreAux, int edadAux, String puestoAux, int numeroAux, int golesAux) {
        super(nombreAux, edadAux);
        this.puesto = establecerPuesto(puestoAux);
        this.numero = numeroAux;
        this.goles = golesAux;
    }
    //Metodos
    public Puesto getPuesto() {
        return puesto;
    }
    public int getNumero() {
        return numero;
    }
    public void setGoles(int golesAux) {
        this.goles = golesAux;
    }
    public int getGoles() {
        return goles;
    }
    private Puesto establecerPuesto(String puestoAux) {
        Puesto p;
        if(puestoAux.equalsIgnoreCase("Attacker")) {
            p = Puesto.DEL;
        }
        else if(puestoAux.equalsIgnoreCase("Midfielder")) {
            p = Puesto.MED;
        }
        else if(puestoAux.equalsIgnoreCase("Defender")) {
            p = Puesto.DEF;
        }
        else {
            p = Puesto.ARQ;
        }
        return p;
    }
    @Override
    public String toString() {
        String s = "[" + puesto + "]  | " + String.format("%3s", numero) + " | " + super.toString() + " | ";
        if(goles != 0) {
            s += String.format("%5s", goles) + " ";
        }
        else {
            s += "      ";
        }
        return s;
    }
}