package pl.tomwodz.rubicon.services;

import org.springframework.stereotype.Service;
import pl.tomwodz.rubicon.model.Client;
import pl.tomwodz.rubicon.model.Order;
import pl.tomwodz.rubicon.services.IClientService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientRankingService {

    private final IClientService clientService;

    public ClientRankingService(IClientService clientService) {
        this.clientService = clientService;
    }

    public List<Order> getRankingOrder() {
        List<Client> clientsBox = this.clientService.getAllClient();
        List<Order> response = new ArrayList<>();
        List<String> orders = clientsBox.stream()
                .map(client -> client.getOrder())
                .distinct()
                .sorted()
                .toList();

        for(int i = 0; i < orders.size(); i++){
            int counter = 0;
            for(int j = 0; j < clientsBox.size(); j++){
                if(orders.get(i).equals(clientsBox.get(j).getOrder())){
                    counter++;
                }
            }
            response.add(new Order(orders.get(i), counter));
        }
        response = response.stream()
                .sorted((i1, i2) -> i2.getQuantity()-i1.getQuantity())
                .limit(30)
                .toList();
        return response;
    }
}
