package pl.toms.aplisens.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * Generacja komunikatów aplikacji
 */
@Component
public class AppMessage
{
    /**
     * Język aplikacji (pl_PL)
     */
    private static final Locale LOCALE = Locale.getDefault();
    
    @Autowired
    private MessageSource messageSource;

    /**
     * Zwraca komunikat aplikacji. Jeśli nie zostanie znaleziony zlokalizowany komunikat, to zwracany jest komunikat domyślny.
     *
     * @param code kod komunikatu
     * @param message domyślna treść komunikatu, gdy nie zostanie znaleziony zlokalizowany komunikat
     * @param args tablica argumentów występujących w komunikacie
     * @return zlokalizowany komunikat aplikacji lub komunikat domyślny
     *
     */
    public String getAppMessage(final String code, final String message, final Object[] args)
    {
        return messageSource.getMessage(code, args, message, LOCALE);
    }
    
    /**
     * Zwraca zlokalizowany komunikat aplikacji.
     *
     * @param code kod komunikatu
     * @param args tablica argumentów występujących w komunikacie
     * @return zlokalizowany komunikat aplikacji
     * @throws NoSuchMessageException - jeśli zlokalizowany komunikat aplikacji ie zostanie znaleziony
     *
     */
    public String getAppMessage(final String code, final Object[] args)
    {
        return messageSource.getMessage(code, args, LOCALE);
    }
    
    /**
     * Zwraca zlokalizowany komunikat aplikacji. Jeśli nie zostanie znaleziony zlokalizowany komunikat, to zwracany jest komunikat "Wystąpił błąd".
     *
     * @param code kod komunikatu
     * @param args tablica argumentów występujących w komunikacie
     * @return zlokalizowany komunikat aplikacji lub komunikat "Wystąpił błąd"
     *
     */
    public String getErrorMessage(final String code, final Object[] args)
    {
        return messageSource.getMessage(code, args, "Wystąpił błąd", LOCALE);
    }
}