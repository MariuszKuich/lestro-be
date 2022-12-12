package pl.mariuszk.employeepanelservice.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mariuszk.employeepanelservice.model.security.EmployeeUserPrincipal;
import pl.mariuszk.employeepanelservice.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository.findByMail(username)
                .map(EmployeeUserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
