package excepciones;
public class StatusCodeException extends Exception {
    //Atributos
    private final int codigo;
    //Constructores
    public StatusCodeException(int codigoAux) {
        super();
        this.codigo = codigoAux;
    }
    //Metodos
    @Override
    public String getMessage() {
        return "Error " + codigo + ". Contacte al desarrollador del software.";
    }
}