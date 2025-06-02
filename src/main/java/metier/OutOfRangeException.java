package metier;

public class OutOfRangeException extends Exception {

    public OutOfRangeException( String msg ) {
        super( msg );
    }

    public OutOfRangeException( String msg, Throwable e ) {
        super( msg, e );
    }

}
