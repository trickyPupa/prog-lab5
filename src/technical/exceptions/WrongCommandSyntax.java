package technical.exceptions;

public class WrongCommandSyntax extends RuntimeException {
    public WrongCommandSyntax(String message) {
        super(message);
    }

    public WrongCommandSyntax() {
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
