package excepciones;
public class JsonErrorException extends Exception {
    //Constructores
    public JsonErrorException() {
        super();
    }
    //Metodos
    @Override
    public String getMessage() {
        return "Clave o indice inexistente.";
    }
}
