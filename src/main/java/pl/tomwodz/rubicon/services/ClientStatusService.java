package pl.tomwodz.rubicon.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.tomwodz.rubicon.model.Client;
import pl.tomwodz.rubicon.model.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ClientStatusService {

    private final IClientService clientService;

    public ClientStatusService(IClientService clientService) {
        this.clientService = clientService;
    }
    public List<Status> getClientByStatus() {
        List<Client> clients = this.clientService.getAllClient();
        Map<String, Long> json = clients
                .stream()
                .filter(client -> client.getTypeOfFile().equals("JSON"))
                .map(client -> client.getStatus())
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );

        Map<String, Long> csv = clients
                .stream()
                .filter(client -> client.getTypeOfFile().equals("CSV"))
                .map(client -> client.getStatus())
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );

        Map<String, Long> ldif = clients
                .stream()
                .filter(client -> client.getTypeOfFile().equals("LDIF"))
                .map(client -> client.getStatus())
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );

        List<Status> statusesSingle = new ArrayList<>();
        statusesSingle.add(new Status("Single", csv.get("Single"), "CSV"));
        statusesSingle.add(new Status("Single", json.get("Single"), "JSON"));
        statusesSingle.add(new Status("Single", ldif.get("Single"), "LDIF"));

        List<Status> statusesDivorced = new ArrayList<>();
        statusesDivorced.add(new Status("Divorced", csv.get("Divorced"), "CSV"));
        statusesDivorced.add(new Status("Divorced", json.get("Divorced"), "JSON"));
        statusesDivorced.add(new Status("Divorced", ldif.get("Divorced"), "LDIF"));

        List<Status>  statusesCommon = new ArrayList<>();
        statusesCommon.add(new Status("Common-Law", csv.get("Common-Law"), "CSV"));
        statusesCommon.add(new Status("Common-Law", json.get("Common-Law"), "JSON"));
        statusesCommon.add(new Status("Common-Law", ldif.get("Common-Law"), "LDIF"));

        List<Status>  statusesMarried = new ArrayList<>();
        statusesMarried.add(new Status("Married", csv.get("Married"), "CSV"));
        statusesMarried.add(new Status("Married", json.get("Married"), "JSON"));
        statusesMarried.add(new Status("Married", ldif.get("Married"), "LDIF"));

        List<Status> statuses = new ArrayList<>();
        statuses.addAll(statusesSingle);
        statuses.addAll(statusesCommon);
        statuses.addAll(statusesDivorced);
        statuses.addAll(statusesMarried);

        return statuses;
    }
}
