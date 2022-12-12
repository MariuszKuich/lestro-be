package pl.mariuszk.employeepanelservice.model.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.mariuszk.employeepanelservice.model.entities.EmployeeEntity;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class EmployeeUserPrincipal implements UserDetails {

    private final EmployeeEntity employeeEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return employeeEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return employeeEntity.getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
