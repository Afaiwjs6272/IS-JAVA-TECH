package ru.Ukhanov.rest.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.Ukhanov.rest.config.MyUserDetails;
import ru.Ukhanov.rest.dao.OwnerDAO;
import ru.Ukhanov.rest.model.owner.Owner;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private OwnerDAO ownerDAO;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<Owner> ownerName = ownerDAO.findOwner(name);

        return ownerName.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(name + " not found"));
    }
}