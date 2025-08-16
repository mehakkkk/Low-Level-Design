import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    private List<Client> clients;

    public ClientRepository()
    {
        clients = new ArrayList<>();
    }

    public boolean addClient(Client client)
    {
        clients.add(client);
        return true;
    }
    public List<Client> getClients() {
        return clients;
    }
}
