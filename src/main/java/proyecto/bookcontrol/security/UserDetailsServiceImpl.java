package proyecto.bookcontrol.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import proyecto.bookcontrol.domain.Usuario;
import proyecto.bookcontrol.repositories.UsuarioRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Intentamos buscar el usuario por nombre
        Usuario usuario = usuarioRepository.findByNombre(username);

        // Si no se encuentra por nombre, buscamos por email
        if (usuario == null) {
            usuario = usuarioRepository.findByEmail(username); 
        }
        
        // Si no se encuentra el usuario ni por nombre ni por email
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado!");
        }
        // Retorna un objeto User con los detalles del usuario
        return User
                .withUsername(username)
                .roles(usuario.getRol().toString()) 
                .password(usuario.getClave())
                .build();
    }
}
