package pl.tomwodz.rubicon.services;

import org.springframework.stereotype.Service;
import pl.tomwodz.rubicon.model.Client;
import pl.tomwodz.rubicon.services.IClientService;
import java.util.List;

@Service
public class ClientConsonatsService {

    String[] consonants = {"b", "c", "ć", "d", "f", "g", "h", "j", "k", "l", "ł", "m", "n", "ń", "p", "r", "s", "ś", "t", "w", "z", "ź", "ż", "a"};

    private final IClientService clientService;

    public ClientConsonatsService(IClientService clientService) {
        this.clientService = clientService;
    }

    public Integer getAllConsonantsByCustomers() {
        List<Client> clientsBox = this.clientService.getAllClient();
        int counter = 0;
        for(int i = 0; i < clientsBox.size(); i++){
            counter += calculateConsonantsBySampleWord(clientsBox.get(i).getCustomer());
        }
        return counter;
    }

    private int calculateConsonantsBySampleWord(String word){
        int counter = 0;
        for(int i = 0 ; i <word.length(); i++){
            String subWord = word.substring(i,i+1);
            for (int j = 0; j < consonants.length; j++) {
                if (subWord.contains(consonants[j])) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
