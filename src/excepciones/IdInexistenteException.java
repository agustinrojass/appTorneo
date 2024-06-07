package excepciones;
public class IdInexistenteException extends Exception {
    //Atributos
    private final int id;
    //Constructores
    public IdInexistenteException(int idAux) {
        super();
        this.id = idAux;
    }
    //Metodos
    @Override
    public String getMessage() {
        return "El #id " + id + " no existe.";
    }
}
