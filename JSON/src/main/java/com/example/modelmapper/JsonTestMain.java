package com.example.modelmapper;

import com.example.modelmapper.entities.dtos.CreateAddressDTO;
import com.example.modelmapper.entities.dtos.CreateEmployeeDTO;
import com.google.gson.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//@Component
public class JsonTestMain implements CommandLineRunner {
    //{
    //  "firstName": "First",
    //  "salary": 1,
    //  "address": {
    //    "country": "Bulgaria",
    //    "city": "Burgas"
    //  }
    //}

    private final Scanner scanner;

    public JsonTestMain(Scanner scanner) {
        this.scanner = scanner;
    }

    public static class LocalDateAdapter implements JsonSerializer<LocalDate>{

        @Override
        public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_DATE));
        }
    }

    @Override
    public void run(String... args) throws Exception {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation() // взима само полетата, които имат анотация @Expose
                //.setPrettyPrinting() // форматиран json
                .setDateFormat("YYYY-MM-DD")
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();



        CreateAddressDTO addressDTO = new CreateAddressDTO("Bulgaria", "Burgas");
        String json = gson.toJson(addressDTO);

      //  System.out.println(json);

        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO(
                "First", "Last", BigDecimal.ONE, LocalDate.now(), addressDTO);

        String json2= gson.toJson(createEmployeeDTO);
      System.out.println(json2);

        String input = this.scanner.nextLine();

        CreateEmployeeDTO parsedDTO = gson.fromJson(input, CreateEmployeeDTO.class);

        System.out.println();


    }
}
