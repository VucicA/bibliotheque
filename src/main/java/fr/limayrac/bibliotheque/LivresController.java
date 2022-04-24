package fr.limayrac.bibliotheque;

import fr.limayrac.model.User;
import fr.limayrac.model.Emprunt;
import fr.limayrac.repository.EmpruntRepository;
import fr.limayrac.repository.LivreRepository;
import fr.limayrac.service.CustomUserDetails;
import fr.limayrac.repository.UserRepository;
import fr.limayrac.service.UserService;
import fr.limayrac.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import fr.limayrac.model.Livre;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("/compte")
class LivresController {

    @Autowired
    private UserService userService;
    @Autowired
    private LivreService livreService;
    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private EmpruntRepository empruntRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/listeLivres")
    public ModelAndView listeLivres() {
        return new ModelAndView("listeLivres", "livres", livreService.getLivres());
    }

    @GetMapping("/livre/detailsLivre/{id}")
    public ModelAndView detailsUser(@PathVariable("id") final Long id) {
        Optional<Livre> livre = livreService.getLivre(id);
        return new ModelAndView("detailsLivre", "livre", livre.orElse(null));
    }

    @GetMapping("/livre/creerLivre")
    public String AddLivre (Model model){
        model.addAttribute("livre", new Livre());

        return "/creerLivre";
    }

    @PostMapping("/livre/creerLivre")
    public ModelAndView submit(@ModelAttribute("livre") Livre livre) {
        livreService.saveLivre(livre);
        return listeLivres();
    }

    @GetMapping("/Livre/deleteLivre/{id}")
    public RedirectView DeleteLivre(@PathVariable("id") final Long id) {
        livreService.deleteLivre(id);
        return new RedirectView("/compte/compteLister");
    }

    @GetMapping("/livre/empruntLivre")
    public String listLivresEmprunt (Model model){
        List<Emprunt> emprunts = empruntRepository.findAll();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long idUser = ((CustomUserDetails)principal).getUser().getId();

        model.addAttribute("emprunts", emprunts);
        return "/empruntLivre";
    }

    @GetMapping("/livre/emprunterLivre/{id}")
    public RedirectView registerEmprunt(@PathVariable Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User User = ((CustomUserDetails)principal).getUser();
        Emprunt emprunt = new Emprunt();
        emprunt.setLivre(livreRepository.findById(id).orElse(null));
        emprunt.setUser(userRepository.findById(User.getId()).orElse(null));
        emprunt.setDateEmprunt(LocalDateTime.now().toString());
        int livreQuatite = emprunt.getLivre().getQuantite() - 1;
        Livre livre = emprunt.getLivre();
        livre.setQuantite(livreQuatite);
        empruntRepository.save(emprunt);
        livreRepository.save(livre);

        return new RedirectView("/livre/empruntLivre");
    }

    @GetMapping("/livre/restituerLivre/{id}")
    public RedirectView rendreEmprunt (@PathVariable Long id){
        int livreQuatite = empruntRepository.getById(id).getLivre().getQuantite() + 1;
        Livre livre = empruntRepository.getById(id).getLivre();
        livre.setQuantite(livreQuatite);
        livreRepository.save(livre);
        empruntRepository.deleteById(id);
        return new RedirectView("/livre/empruntLivre");
    }

    @GetMapping("/livre/supprimerLivre/{id}")
    public RedirectView supprimerLivre (@PathVariable Long id){
        Long idEmprunt = empruntRepository.findByLivre(id).getId();
        empruntRepository.deleteById(idEmprunt);
        livreRepository.deleteById(id);
        return new RedirectView("/listeLivres");
    }
}