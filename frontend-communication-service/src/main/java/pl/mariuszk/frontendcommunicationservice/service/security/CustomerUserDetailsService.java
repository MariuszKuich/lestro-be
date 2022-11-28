package pl.mariuszk.frontendcommunicationservice.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mariuszk.frontendcommunicationservice.model.security.CustomerUserPrincipal;
import pl.mariuszk.frontendcommunicationservice.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findByMail(username)
                .map(CustomerUserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
