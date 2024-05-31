package clases;
import Enumeraciones.Puesto;
public class Jugador extends Persona {
    //Atributos
    private Puesto puesto;
    private int numero;
    //Constructores
    public Jugador(String nombreAux, int edadAux, String puestoAux, int numeroAux) {
        super(nombreAux, edadAux);
        this.puesto = establecerPuesto(puestoAux);
        this.numero = numeroAux;
    }
    //Metodos
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
        return "[" + puesto + "] | " + String.format("%3s", numero) + " | " + super.toString() + " ";
    }
}