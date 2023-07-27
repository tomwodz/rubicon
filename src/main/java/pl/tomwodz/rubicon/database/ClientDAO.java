package pl.tomwodz.rubicon.database;

import org.springframework.stereotype.Repository;
import pl.tomwodz.rubicon.model.Client;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDAO {
    private final List<Client> database = new ArrayList<>();

    public ClientDAO() {
    }

    public void persistClient(Client client) {
        this.database.add(client);
    }

    public List<Client> getAllClient() {
        return new ArrayList<>(this.database);
    }

}
