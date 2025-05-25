package proyecto.bookcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import proyecto.bookcontrol.domain.Rol;
import proyecto.bookcontrol.domain.Usuario;
import proyecto.bookcontrol.exceptions.NotFoundException;
import proyecto.bookcontrol.repositories.UsuarioRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UsuarioServiceImplBD implements UsuarioService {

    @Autowired
    UsuarioRepository UsuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Usuario anadir(Usuario Usuario) {
        String passCrypted = passwordEncoder.encode(Usuario.getClave());
        Usuario.setClave(passCrypted);
        try {
            return UsuarioRepository.save(Usuario);
        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        usuario.setRol(Rol.USUARIO); 
        return UsuarioRepository.save(usuario);
    }

    public Usuario obtenerPorId(Long id) throws NotFoundException {
        return UsuarioRepository.findById(id).orElseThrow();
    }

    public Usuario editar(Usuario Usuario) throws NotFoundException {
        String passCrypted = passwordEncoder.encode(Usuario.getClave());
        Usuario.setClave(passCrypted);
        try {
            return UsuarioRepository.save(Usuario);
        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void borrar(Long id) throws NotFoundException {
        UsuarioRepository.deleteById(id);
    }

    public Page<Usuario> obtenerTodosPaginado(Pageable pageable) {
        return UsuarioRepository.findAll(pageable);
    }

}
