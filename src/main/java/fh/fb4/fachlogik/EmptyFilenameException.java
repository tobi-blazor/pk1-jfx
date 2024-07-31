package fh.fb4.fachlogik;

public class EmptyFilenameException extends Exception{
    public EmptyFilenameException() {
        super();
    }
    public EmptyFilenameException(String s) {
        super(s);
    }
}
