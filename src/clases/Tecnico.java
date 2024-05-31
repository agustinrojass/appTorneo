package clases;
public class Tecnico extends Persona {
    //Atributos
    private String formacionFavorita;
    //Constructores
    public Tecnico() {
        super();
        this.formacionFavorita = "";
    } //por ahora se usa, pero despues no se va a usar
    public Tecnico(String nombreAux, int edadAux, String formacionFavoritaAux) {
        super(nombreAux, edadAux);
        this.formacionFavorita = formacionFavoritaAux;
    }
    //Metodos
    @Override
    public String toString() {
        return super.toString() + " - " + formacionFavorita;
    }
}