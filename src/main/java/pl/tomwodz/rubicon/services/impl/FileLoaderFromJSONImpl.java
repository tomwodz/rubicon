package pl.tomwodz.rubicon.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.tomwodz.rubicon.model.Client;
import pl.tomwodz.rubicon.services.IClientService;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Service
@Log4j2
public class FileLoaderFromJSONImpl {
    private final IClientService clientService;

    public FileLoaderFromJSONImpl(IClientService clientService) {
        this.clientService = clientService;
    }

    public void readDataFromFile() {
     try {
            ObjectMapper mapper = new ObjectMapper();
            Data data = mapper.readValue(new File("dataFeb-2-2017.json"), Data.class);
            List<String[]> box = data.getData().stream().toList();
            for(int i =0 ; i <data.data.size(); i++){
                List<String> boxSample = Arrays.stream(box.get(i)).toList();
                clientService.persistClient(
                        new Client(
                                boxSample.get(0),
                                boxSample.get(1),
                                boxSample.get(2),
                                boxSample.get(3),
                                Integer.parseInt(boxSample.get(4)),
                                "JSON"));
            }
        } catch (IOException e) {
         log.error("Error from read file JSON.");
        }
    }



}
