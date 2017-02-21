package lk.grp.synergy.util;

/**
 * Created by isuru on 2/10/17.
 */
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

public class DateDeserializer implements JsonDeserializer<LocalDate>
{
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public LocalDate deserialize(JsonElement dateStr, Type typeOfSrc, JsonDeserializationContext context)
    {
        return LocalDate.parse(dateStr.getAsString(),FORMATTER);
    }
}
