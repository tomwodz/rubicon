package pl.tomwodz.rubicon.services.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.tomwodz.rubicon.model.Client;
import pl.tomwodz.rubicon.services.IClientService;
import pl.tomwodz.rubicon.services.IFileLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
@Log4j2
public class FileLoaderFromCSVImpl implements IFileLoader {

    private final IClientService clientService;

    public FileLoaderFromCSVImpl(IClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void readDataFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dataFeb-2-2017.csv"));
            String line;
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                if (counter > 0) {
                    String[] objectData = line.split("\\|");
                    clientService.persistClient(
                            new Client(
                                    objectData[0],
                                    objectData[1],
                                    objectData[2],
                                    objectData[3],
                                    Integer.parseInt(objectData[4]),
                                    "CSV"));
                }
                counter++;
            }
        } catch (IOException e) {
            log.error("Error from read file CSV.");
        }
    }
}
