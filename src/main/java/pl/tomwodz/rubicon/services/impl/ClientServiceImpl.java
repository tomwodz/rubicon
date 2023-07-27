package pl.tomwodz.rubicon.services.impl;

import org.springframework.stereotype.Service;
import pl.tomwodz.rubicon.database.ClientDAO;
import pl.tomwodz.rubicon.model.*;
import pl.tomwodz.rubicon.services.IClientService;
import java.util.*;


@Service
public class ClientServiceImpl implements IClientService {

    private final ClientDAO clientDAO;

    public ClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
    @Override
    public void persistClient(Client client) {
        this.clientDAO.persistClient(client);
    }
    @Override
    public List<Client> getAllClient() {
        return this.clientDAO.getAllClient();
    }

}
