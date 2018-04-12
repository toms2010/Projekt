package pl.toms.aplisens.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * Generacja zlokalizowanych komunikatów aplikacji
 */
@Component
public class AppMessage
{

    @Autowired
    private MessageSource messageSource;

    /**
     * Zwraca zlokalizowany komunikat aplikacji. Jeśli nie zostanie znaleziony zlokalizowany komunikat, to zwracany jest komunikat domyślny.
     *
     * @param code kod komunikatu
     * @param message domyślna treść komunikatu, gdy nie zostanie znaleziony zlokalizowany komunikat
     * @param args tablica argumentów występujących w komunikacie
     * @return zlokalizowany komunikat aplikacji lub komunikat domyślny
     *
     */
    public String getAppMessage(final String code, final String message, final Object[] args)
    {
        return messageSource.getMessage(code, args, message, Locale.getDefault());
    }
}