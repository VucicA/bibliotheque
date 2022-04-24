package fr.limayrac.bibliotheque;

import fr.limayrac.model.User;
import fr.limayrac.repository.UserRepository;
import fr.limayrac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
//@RequestMapping("/client")
class UsersController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepo;

    /*
    @GetMapping("/client/clientLister")
    public ModelAndView listeClients() {
        return new ModelAndView("clientLister", "clients", clientService.getClients());
    }


    @GetMapping("/client/clientLister/{id}")
    public ModelAndView detailsClient(@PathVariable("id") final Long id) {
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
    public RedirectView effacerClient(@PathVariable("id") final Long id){
        clientService.deleteClient(id);
        return new RedirectView("/client/clientLister");
    }
    */

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        //logger.info(client.toString());
        userRepo.save(user);

        return "register_success";
    }

    @GetMapping("/listeUsers")
    public String listeUsers(Model model) {
        List<User> listeUsers = userRepo.findAll();
        model.addAttribute("user", listeUsers);

        return "listeLivres";
    }
}
