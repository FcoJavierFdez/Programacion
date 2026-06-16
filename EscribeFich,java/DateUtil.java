import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    // Definimos el formato: día/mes/año
    private static final String PATTERN = "dd/MM/yyyy";
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(PATTERN);
    private static Date localDate;

    /**
     * Convierte una fecha en un texto con formato legible.
     */
    public static String format(java.sql.Date date) {
        if (date == null) return "";
        return FORMATTER.format(localDate);
    }
}