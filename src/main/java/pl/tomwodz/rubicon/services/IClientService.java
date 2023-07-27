package pl.tomwodz.rubicon.services;

import pl.tomwodz.rubicon.model.Client;
import pl.tomwodz.rubicon.model.Country;
import pl.tomwodz.rubicon.model.Order;

import java.util.List;
import java.util.Map;

public interface IClientService {

    void persistClient(Client client);

    List<Client> getAllClient();


}
