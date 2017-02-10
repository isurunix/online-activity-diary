package lk.grp.synergy.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by isuru on 2/10/17.
 */
public class TimeDeserializer implements JsonDeserializer<LocalTime> {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Override
    public LocalTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            return LocalTime.from(dateFormat.parse(jsonElement.getAsString()).toInstant());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
