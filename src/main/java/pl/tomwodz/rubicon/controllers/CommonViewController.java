package pl.tomwodz.rubicon.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.tomwodz.rubicon.services.*;

@Controller
public class CommonViewController {

    private final IClientService clientService;
    private final ClientStatusService clientStatusService;
    private final ClientConsonatsService clientConsonatsService;
    private final ClientRankingService clientRankingService;
    private final ClientCountryService clientCountryService;

    public CommonViewController(IClientService clientService,
                                ClientStatusService clientStatusService,
                                ClientConsonatsService clientConsonatsService,
                                ClientRankingService clientRankingService,
                                ClientCountryService clientCountryService) {
        this.clientService = clientService;
        this.clientStatusService = clientStatusService;
        this.clientConsonatsService = clientConsonatsService;
        this.clientRankingService = clientRankingService;
        this.clientCountryService = clientCountryService;
    }

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("clients", this.clientService.getAllClient());
        return "index";
    }

    @GetMapping("/order")
    public String makeRankingByOrder(Model model){
        model.addAttribute("orders", this.clientRankingService.getRankingOrder());
        return "order";
    }

    @GetMapping("/country")
    public String makeCountryByGroup(Model model){
        model.addAttribute("countries", this.clientCountryService.getRankingCountryByGroup());
        return "country";
    }

    @GetMapping("/status")
    public String makeStatus(Model model){
        model.addAttribute("statuses", this.clientStatusService.getClientByStatus());
        return "/status";
    }

    @GetMapping("/consonants")
    public String makeAllConsonantsByCustomers(Model model){
        model.addAttribute("consonants", this.clientConsonatsService.getAllConsonantsByCustomers());
        return "/consonants";
    }
}
