package lk.grp.synergy.util;

/**
 * Created by isuru on 2/10/17.
 */
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateSerializer implements JsonSerializer<LocalDate>
{
    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context)
    {
        return new JsonPrimitive(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
