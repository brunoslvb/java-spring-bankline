package bankline.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import bankline.model.User;
import bankline.repository.UserRepository;


public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    private TokenServ tokenServ;
    private UserRepository userRepository;

    public AutenticacaoTokenFilter(TokenServ tokenServ, UserRepository userRepository) {
        this.tokenServ = tokenServ;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        
        String token = recuperarToken(request);
        boolean valido = tokenServ.isTokenValido(token);
        if (valido) {
            autenticarCliente(token);
        }
        
        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        String idUser = tokenServ.getIdUser(token);
        User user = userRepository.findById(idUser).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }
    
}
