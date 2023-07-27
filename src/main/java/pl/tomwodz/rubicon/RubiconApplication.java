package pl.tomwodz.rubicon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import pl.tomwodz.rubicon.services.IFileLoader;
import pl.tomwodz.rubicon.services.impl.FileLoaderFromJSONImpl;
import pl.tomwodz.rubicon.services.impl.FileLoaderFromLDIFImpl;

@SpringBootApplication
public class RubiconApplication {

    private final IFileLoader fileLoader;
    private final FileLoaderFromJSONImpl fileLoaderFromJSON;
    private final FileLoaderFromLDIFImpl fileLoaderFromldif;

    public RubiconApplication(IFileLoader fileLoader, FileLoaderFromJSONImpl fileLoaderFromJSON, FileLoaderFromLDIFImpl fileLoaderFromldif) {
        this.fileLoader = fileLoader;
        this.fileLoaderFromJSON = fileLoaderFromJSON;
        this.fileLoaderFromldif = fileLoaderFromldif;
    }

    public static void main(String[] args) {
        SpringApplication.run(RubiconApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void readFromFile() {
        fileLoader.readDataFromFile();
        fileLoaderFromJSON.readDataFromFile();
        fileLoaderFromldif.readDataFromFile();
    }



}
