package fr.limayrac.banque;

import fr.limayrac.model.Client;
import fr.limayrac.service.ClientService;
import fr.limayrac.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import fr.limayrac.model.Compte;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
//@RequestMapping("/compte")
class ComptesController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private CompteService compteService;

    @GetMapping("/compte/compteLister")
    public ModelAndView listeComptes() {
        return new ModelAndView("compteLister", "comptes", compteService.getComptes());
    }


    @GetMapping("/compte/compteLister/{id}")
    public ModelAndView detailsClient(@PathVariable("id") final Integer id) {
        Optional<Compte> compte = compteService.getCompte(id);
        return new ModelAndView("compteDetails", "compte", compte.orElse(null));
    }

    @GetMapping("/compte/compteCreate")
    public ModelAndView clientCreation(ModelMap model){
        Iterable<Client> clients = clientService.getClients();
        model.addAttribute("clients",clients);
        return new ModelAndView("compteCreate", "compte", new Compte());
    };

    @PostMapping("/compte/compteCreate")
    public RedirectView submit(@ModelAttribute("compte") Compte compte, ModelMap model) {
        model.addAttribute("numeroCompte", compte.getNumeroCompte());
        model.addAttribute("client", compte.getClient());
        compteService.saveCompte(compte);
        return new RedirectView("/compte/compteLister");
    }

    @GetMapping("/compte/compteDelete/{id}")
    public RedirectView DeleteCompte(@PathVariable("id") final Integer id) {
        compteService.deleteCompte(id);
        return new RedirectView("/compte/compteLister");
    }
}