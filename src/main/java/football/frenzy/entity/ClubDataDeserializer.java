package football.frenzy.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import football.frenzy.entity.ClubData;
import football.frenzy.entity.PlayerData;

import java.io.IOException;
import java.util.List;

public class ClubDataDeserializer extends JsonDeserializer<ClubData> {
    @Override
    public ClubData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getText();
        // Assuming value is the name, adjust this if it's an ID or other identifier
        return new ClubData(value);
    }
}
