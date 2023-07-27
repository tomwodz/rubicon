package pl.tomwodz.rubicon.services;

import org.springframework.stereotype.Service;
import pl.tomwodz.rubicon.model.Client;
import pl.tomwodz.rubicon.model.Country;
import pl.tomwodz.rubicon.model.CountrySample;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientCountryService {

    private final IClientService clientService;

    public ClientCountryService(IClientService clientService) {
        this.clientService = clientService;
    }
    public List<Country> getRankingCountryByGroup() {
        List<Client> clientsBox = this.clientService.getAllClient();
        List<Country> response = new ArrayList<>();
        List<Integer> groups = clientsBox.stream()
                .map(client -> client.getGroup())
                .distinct()
                .sorted()
                .toList();

        for(int i = 0; i < groups.size(); i++) {
            List<String> countryByGroup = new ArrayList<>();
            for(int j =0; j < clientsBox.size(); j++){
                if(groups.get(i)==clientsBox.get(j).getGroup()){
                    countryByGroup.add(clientsBox.get(j).getCountry());
                }
            }

            List<String> countrySampleByGroup = countryByGroup.stream().distinct().toList();
            countrySampleByGroup.get(0);

            List<CountrySample> countrySamples = new ArrayList<>();

            for(int x = 0; x < countrySampleByGroup.size(); x++){
                int counter = 0;
                for(int y = 0; y < countryByGroup.size(); y++){
                    if(countrySampleByGroup.get(x).equals(countryByGroup.get(y))){
                        counter++;
                    }
                }
                countrySamples.add(new CountrySample(countrySampleByGroup.get(x), counter));
            }
            countrySamples = countrySamples.stream().sorted((i1, i2) -> i2.getQuantity()-i1.getQuantity()).toList();
            final int max = countrySamples.get(0).getQuantity();
            countrySamples = countrySamples.stream().filter(countrySample -> countrySample.getQuantity() >= max).toList();
            countryByGroup.clear();
            for(int z = 0 ; z < countrySamples.size(); z++){
                countryByGroup.add(countrySamples.get(z).getCountry());
            }
            response.add(new Country(groups.get(i), countryByGroup));
        }
        return response;
    }

}
