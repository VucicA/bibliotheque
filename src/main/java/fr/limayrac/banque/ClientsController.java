package fr.limayrac.banque;

import fr.limayrac.model.Client;
import fr.limayrac.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
//@RequestMapping("/client")
class ClientsController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/client/clientLister")
    public ModelAndView listeClients() {
        return new ModelAndView("clientLister", "clients", clientService.getClients());
    }


    @GetMapping("/client/clientLister/{id}")
    public ModelAndView detailsClient(@PathVariable("id") final Integer id) {
        Optional<Client> client = clientService.getClient(id);
        return new ModelAndView("clientDetails", "client", client.orElse(null));
    }

    @GetMapping("/client/clientCreate")
    public ModelAndView clientCreation(){
        Client c = new Client();
        return new ModelAndView("clientCreate", "client", c);
    }

    @PostMapping("/client/clientCreate")
    public RedirectView submit(@ModelAttribute("client") Client client, ModelMap model) {
        model.addAttribute("nom", client.getNom());
        model.addAttribute("prenom", client.getPrenom());
        clientService.saveClient(client);
        return new RedirectView("/client/clientLister");
    }

    @GetMapping("/client/clientDelete/{id}")
    public RedirectView effacerClient(@PathVariable("id") final Integer id){
        clientService.deleteClient(id);
        return new RedirectView("/client/clientLister");
    }

    @PostMapping("/process_register")
    public String processRegister(Client client) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        logger.info(client.toString());
        clientRepo.save(client);

        return "register_success";
    }
}
