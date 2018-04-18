package pl.toms.aplisens.util;

/**
 * Błąd w trakcie działania aplikacji.
 */
@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {
    /**
     * Błąd w trakcie działania aplikacji.
     */
    public ApplicationException() {
    }

    /**
     * Błąd w trakcie działania aplikacji.
     * 
     * @param message komunikat o błędzie
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * Błąd w trakcie działania aplikacji.
     * 
     * @param cause cause
     */
    public ApplicationException(Throwable cause) {
        super(cause);
    }

    /**
     * Błąd w trakcie działania aplikacji.
     * 
     * @param message komunikat o błędzie
     * @param cause cause
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}