package excepciones;
public class ArchivoNoCerradoException extends Exception{
    //Constructores
    public ArchivoNoCerradoException() {
        super();
    }
    //Metodos
    @Override
    public String getMessage() {
        return "Archivo no cerrado.";
    }
}
