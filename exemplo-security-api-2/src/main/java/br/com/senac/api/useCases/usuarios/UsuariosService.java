package br.com.senac.api.useCases.usuarios;

import br.com.senac.api.entitys.Usuarios;
import br.com.senac.api.jwt.TokenService;
import br.com.senac.api.useCases.usuarios.domains.UsuariosRequestDom;
import br.com.senac.api.useCases.usuarios.domains.UsuariosResponseDom;
import br.com.senac.api.useCases.usuarios.mappers.UsuariosMapper;
import br.com.senac.api.useCases.usuarios.repositorys.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public UsuariosResponseDom
        criarUsuario(UsuariosRequestDom usuario){

        Usuarios usuariosPersist = UsuariosMapper
                .usuariosRequestDomToUsuarios(usuario);

        usuariosPersist
                .setSenha(passwordEncoder.encode(usuario.getSenha()));

        Usuarios usuariosResult =
                usuariosRepository.save(usuariosPersist);

        return UsuariosMapper
                .usuariosToUsuariosResponseDom(usuariosResult);
    }

    public UsuariosResponseDom
        loginUsuario(UsuariosRequestDom usuario) throws Exception {
        Optional<Usuarios> usuarioResult =
                usuariosRepository.findByLogin(usuario.getLogin());
        if(usuarioResult.isPresent()){
            if(passwordEncoder.matches(
                    usuario.getSenha(),
                    usuarioResult.get().getSenha())){

                // gera token jwt
                String token = tokenService
                        .gerarToken(usuarioResult.get());

                // transforma Usuarios para UsuariosResponseDom
                UsuariosResponseDom usuarioRetorno = UsuariosMapper
                        .usuariosToUsuariosResponseDom(usuarioResult.get());

                usuarioRetorno.setToken(token);

                return usuarioRetorno;

            }

            throw new Exception("Senha invalida");
        }

        throw new Exception("Usuário não encontrado!");
    }
}
