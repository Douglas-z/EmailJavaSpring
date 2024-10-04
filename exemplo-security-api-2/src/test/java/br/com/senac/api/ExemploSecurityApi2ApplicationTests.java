package br.com.senac.api;

import br.com.senac.api.services.EmailService;
import br.com.senac.api.useCases.usuarios.UsuariosService;
import br.com.senac.api.useCases.usuarios.domains.UsuariosRequestDom;
import br.com.senac.api.useCases.usuarios.domains.UsuariosResponseDom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ExemploSecurityApi2ApplicationTests {

	@Autowired
	private UsuariosService usuariosService;

	@Autowired
	private EmailService emailService;

	@Test
	@DisplayName("Caso de Teste - Usuário (Cadastro)")
	void testeCadastroUsuario() {
		UsuariosRequestDom usuarioPersist =
				new UsuariosRequestDom();
		usuarioPersist.setLogin("teste");
		usuarioPersist.setSenha("1234");

		UsuariosResponseDom usuarioResult =
				usuariosService.criarUsuario(usuarioPersist);


		assertEquals("teste", usuarioResult.getLogin());
	}

	@Test
	@DisplayName("Caso de Teste - Usuário (Login)")
	void testeLoginUsuario() throws Exception {

		System.out.println("--- Inicio ---");

		System.out.println("--- Montando objeto de input ---");

		UsuariosRequestDom usuarioPersist =
				new UsuariosRequestDom();
		usuarioPersist.setLogin("teste");
		usuarioPersist.setSenha("1234");

		System.out.println("--- Chamando service ---");

		UsuariosResponseDom usuarioResult =
				usuariosService.loginUsuario(usuarioPersist);

		System.out.println("--- Retorno service ---");

		assertNotNull(usuarioResult.getToken());

		System.out.println("--- Fim ---");
	}

	@Test
	public void testeEmail() {
		emailService.enviarEmail("douglasboss98@gmail.com",
				"patrimony.douglas@gmail.com",
				"teste",
				"enviar");
	}
}
