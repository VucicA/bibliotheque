package fr.limayrac.service;

import fr.limayrac.model.Client;
import fr.limayrac.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomClientDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepo.findByEmail(username);
        if (client == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomClientDetails(client);
    }
}
