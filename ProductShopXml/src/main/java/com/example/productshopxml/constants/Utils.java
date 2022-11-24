package com.example.productshopxml.constants;

import com.example.productshopxml.entitities.dtos.users.wrappers.UserWithSoldProductsWrapperDto;
import org.modelmapper.ModelMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.productshopxml.constants.Paths.USERS_WITH_SOLD_PRODUCTS_XML_PATH;

public enum Utils {
    ;
    public static final ModelMapper MODEL_MAPPER = new ModelMapper();


    public static <T> void writeXmlToFile(T data, Path filePath) throws JAXBException {

        final File file = filePath.toFile();

        final JAXBContext context = JAXBContext.newInstance(data.getClass());

        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(data, file);
    }
}
