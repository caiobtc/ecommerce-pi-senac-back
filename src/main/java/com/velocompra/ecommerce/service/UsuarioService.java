package com.velocompra.ecommerce.service;

import com.velocompra.ecommerce.model.Grupo;
import com.velocompra.ecommerce.model.Usuario;
import com.velocompra.ecommerce.repository.UsuarioRepository;
import com.velocompra.ecommerce.validacao.CpfValidador;
import com.velocompra.ecommerce.validacao.EmailValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CpfValidador cpfValidador;

    @Autowired
    private EmailValidador emailValidador;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuariosPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void cadastrarUsuario(String nome, String cpf, String email, String senha, Grupo grupo) {
        if (!cpfValidador.isValid(cpf)) {
            throw new RuntimeException("CPF inválido");
        }
        if (!emailValidador.isValid(email)) {
            throw new RuntimeException("Email inválido");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setGrupo(grupo);
        usuario.setHabilitado(true);

        usuarioRepository.save(usuario);
    }

    public void alterarUsuario(Long id, String nome, String cpf, Grupo grupo) {
        Usuario usuario = buscarUsuariosPorId(id);
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setGrupo(grupo);

        usuarioRepository.save(usuario);
    }

    public void habilitarDesabilitarUsuario(Long id) {
        Usuario usuario = buscarUsuariosPorId(id);
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        usuario.setHabilitado(!usuario.isHabilitado());
        usuarioRepository.save(usuario);
    }

    public void alterarSenhaUsuario(Long usuarioId, String novaSenha) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));


        String senhaCriptografada = passwordEncoder.encode(novaSenha);
        usuario.setSenha(senhaCriptografada);
        usuarioRepository.save(usuario);
        System.out.println("Senha alterada com sucesso para o usuário: " + usuario.getEmail());
    }
}
