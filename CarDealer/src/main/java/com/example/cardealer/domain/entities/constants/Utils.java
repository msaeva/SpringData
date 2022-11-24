package com.example.cardealer.domain.entities.constants;

import com.google.gson.*;
import org.modelmapper.ModelMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
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
    public static <T> void writeXmlToFile(T data, Path filePath) throws JAXBException {

        final File file = filePath.toFile();

        final JAXBContext context = JAXBContext.newInstance(data.getClass());

        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(data, file);
    }
}
