package com.example.cardealer.domain.entities.constants;

import com.google.gson.*;
import org.modelmapper.ModelMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

public enum Utils {
    ;
    public static final ModelMapper MODEL_MAPPER = new ModelMapper();
    public static final JsonDeserializer<LocalDateTime> toLocalDate =
            (json, t, c) -> LocalDateTime.parse(json.getAsString());

    public static final JsonSerializer<String> fromLocalDate =
            (date, t, c) -> new JsonPrimitive(date);

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDateTime.class, toLocalDate)
            .registerTypeAdapter(LocalDateTime.class, fromLocalDate)
            .create();

    public static <T> void writeJsonIntoFile(List<T> objects, Path filePath) throws IOException {
        final FileWriter fileWriter = new FileWriter(filePath.toFile());

        GSON.toJson(objects, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }
}
