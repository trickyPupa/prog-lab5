package technical.exceptions;

public class NoSuchCommandException extends RuntimeException{
    public NoSuchCommandException(){
        super();
    }
    public NoSuchCommandException(String message){
        super(message);
    }

    @Override
    public String toString(){
        return "";
    }
}
