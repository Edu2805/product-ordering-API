package com.springbootexpert.vendas.user;

import com.springbootexpert.vendas.exception.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserData save(UserData userData){ //
        return userRepository.save(userData);
    }

    public UserDetails authenticate(UserData userData){
        var userDetails = loadUserByUsername(userData.getLogin());
        var samePassword = passwordEncoder.matches(userData.getPassword(), userDetails.getPassword());

        if (samePassword){
            return userDetails;
        }
        throw new InvalidPasswordException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados!"));

        String[] roles = userData.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};

        return User
                .builder()
                .username(userData.getLogin())
                .password(userData.getPassword())
                .roles(roles)
                .build();
    }
}
