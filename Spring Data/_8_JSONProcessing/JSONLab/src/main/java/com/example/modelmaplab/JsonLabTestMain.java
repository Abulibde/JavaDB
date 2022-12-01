package com.example.modelmaplab;

import com.example.modelmaplab.domain.DTO.addresses.CreateAddressDTO;
import com.example.modelmaplab.domain.DTO.CreateEmployeeDTO;
import com.google.gson.*;
import org.springframework.boot.CommandLineRunner;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//@Component
public class JsonLabTestMain implements CommandLineRunner {

    static class LocalDateAdapter implements JsonSerializer<LocalDate> {

        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));//"yyyy-mm-dd"
        }
    }

    @Override
    public void run(String... args) throws Exception {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("YYYY-MM-DD")
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();


        CreateAddressDTO addressDTO = new CreateAddressDTO("Egypt", "Cairo");

        CreateEmployeeDTO employeeDTO = new CreateEmployeeDTO
                ("Poli", "Abulibde", BigDecimal.TEN, LocalDate.parse("2021-10-10"), addressDTO);

        String json = gson.toJson(employeeDTO);
        System.out.println(json);

    }
}
