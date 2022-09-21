package com.dh.ctd.groupIV.consultorioodontologico.config.security;
import com.dh.ctd.groupIV.consultorioodontologico.entity.Usuario;
import com.dh.ctd.groupIV.consultorioodontologico.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);

        boolean valido = tokenService.verificaToken(token);


        if(valido){
            autenticarUsuario(token);
        }

        filterChain.doFilter(request,response);
    }

    private void autenticarUsuario(String token) {
        String username = tokenService.getUsernameUsuario(token);
        Usuario usuario = usuarioRepository.findByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String recuperarToken(HttpServletRequest request) {
        String getToken = request.getHeader("Authorization");
        if(getToken == null || getToken.isEmpty() || !getToken.startsWith("Bearer ")){
            return null;
        }
        return getToken.substring(7,getToken.length());
    }
}
