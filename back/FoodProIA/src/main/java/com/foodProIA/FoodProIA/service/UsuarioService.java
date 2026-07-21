package com.foodProIA.FoodProIA.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.UsuarioRequestDTO;
import com.foodProIA.FoodProIA.dto.response.UsuarioResponseDTO;
import com.foodProIA.FoodProIA.entity.UsuarioEntity;
import com.foodProIA.FoodProIA.exception.CpfJaCadastradoException;
import com.foodProIA.FoodProIA.exception.EmailUsuarioJaCadastradoException;
import com.foodProIA.FoodProIA.exception.UsuarioNaoEncontradoException;
import com.foodProIA.FoodProIA.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    
    private final UsuarioRepository usuarioRepository;
      
    private final PasswordEncoder passwordEncoder;

    public List<UsuarioResponseDTO>  listarTodos(){
        List<UsuarioEntity> usuarios =  usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioResponseDTO::new).toList();
    }

    @Transactional
    public UsuarioResponseDTO inserir(UsuarioRequestDTO usuario){

        if(usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new EmailUsuarioJaCadastradoException();
        }

        if(usuarioRepository.existsByCpf(usuario.getCpf())){
            throw new CpfJaCadastradoException();
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        String hash = passwordEncoder.encode(usuario.getSenha());
        usuarioEntity.setHashSenha(hash);

        usuarioEntity.setAtivo(true);
        copiarDados(usuario, usuarioEntity);      

        usuarioRepository.save(usuarioEntity);

        return new UsuarioResponseDTO(usuarioEntity);

    }

    @Transactional
    public UsuarioResponseDTO alterar(Long id, UsuarioRequestDTO usuario){

        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException());


        if (!usuarioEntity.getEmail().equals(usuario.getEmail())
                && usuarioRepository.existsByEmail(usuario.getEmail())) {

            throw new EmailUsuarioJaCadastradoException();
        }

        if (!usuarioEntity.getCpf().equals(usuario.getCpf())
                && usuarioRepository.existsByCpf(usuario.getCpf())) {

            throw new CpfJaCadastradoException();
        }   


        if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
            usuarioEntity.setHashSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        copiarDados(usuario, usuarioEntity);   

        usuarioRepository.save(usuarioEntity);

        return new UsuarioResponseDTO(usuarioEntity);
    }

    @Transactional
    public void excluir(Long id){
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException());
        usuarioRepository.delete(usuario);
    }

    public UsuarioResponseDTO buscarPorId(Long id){
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException());
        return new UsuarioResponseDTO(usuario);
    }

    private void copiarDados(UsuarioRequestDTO usuario, UsuarioEntity usuarioEntity){
        usuarioEntity.setCpf(usuario.getCpf());
        usuarioEntity.setNome(usuario.getNome());
        usuarioEntity.setDataNascimento(usuario.getDataNascimento());
        usuarioEntity.setEmail(usuario.getEmail());
        usuarioEntity.setPapel(usuario.getPapel());
        usuarioEntity.setTelefone(usuario.getTelefone());   
    }

}
