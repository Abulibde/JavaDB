package com.example.modelmaplab;

import com.example.modelmaplab.domain.DTO.AddressXmlDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

@Component
public class XmlTestMain implements CommandLineRunner {



    @Override
    public void run(String... args) throws Exception {

        AddressXmlDTO xmlDTO = new AddressXmlDTO(8,"Bulgaria", "Silistra");

        JAXBContext context = JAXBContext.newInstance(AddressXmlDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true );

        marshaller.marshal(xmlDTO, System.out);

    }
}
