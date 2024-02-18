package technical.exceptions;

public class PartlyCorrectDataFileException extends FileException{
    public PartlyCorrectDataFileException() {
    }

    public PartlyCorrectDataFileException(String message) {
        super(message);
    }
}
