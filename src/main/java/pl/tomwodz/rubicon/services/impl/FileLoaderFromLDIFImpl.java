package pl.tomwodz.rubicon.services.impl;

import com.unboundid.ldap.sdk.DN;
import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldif.LDIFException;
import com.unboundid.ldif.LDIFReader;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.tomwodz.rubicon.model.Client;
import pl.tomwodz.rubicon.services.IClientService;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

@Service
@Log4j2

public class FileLoaderFromLDIFImpl {

    private final IClientService clientService;

    public FileLoaderFromLDIFImpl(IClientService clientService) {
        this.clientService = clientService;
    }

    public void readDataFromFile() {
        clientService.persistClient(
                new Client("Wendy Rutledge", "Estonia", "Spiriva Handihaler", "Single", 9,"LDIF"));
        clientService.persistClient(
                new Client("Pamela Collins", "Timor-Leste", "Diazepam", "Single", 6,"LDIF"));

/*        try {
            LDIFReader ldifReader = new LDIFReader(new File("dataFeb-2-2017.ldif"));
          Entry entry = new Entry("dn:",
                    "objectClass: Customer\n",
                    "objectClass: Country\n",
                    "objectClass: Order\n",
                    "objectClass: Status\n",
                    "objectClass: Group\n");

            entry = ldifReader.readEntry();
        } catch (IOException ex) {
            log.error("Error from read file LDIF.");
        } catch (LDIFException e) {
            throw new RuntimeException(e);
        }*/
    }
}





