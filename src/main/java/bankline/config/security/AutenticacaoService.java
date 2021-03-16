package bankline.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bankline.model.User;
import bankline.repository.UserRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(arg0);
        return user;
    }
    
}
