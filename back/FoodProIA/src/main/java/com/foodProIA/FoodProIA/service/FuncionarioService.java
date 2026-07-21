package com.foodProIA.FoodProIA.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.FuncionarioRequestDTO;
import com.foodProIA.FoodProIA.dto.response.FuncionarioResponseDTO;
import com.foodProIA.FoodProIA.entity.EmpresaEntity;
import com.foodProIA.FoodProIA.entity.EnderecoEntity;
import com.foodProIA.FoodProIA.entity.FuncionarioEntity;
import com.foodProIA.FoodProIA.entity.SetorEntity;
import com.foodProIA.FoodProIA.exception.CpfJaCadastradoException;
import com.foodProIA.FoodProIA.exception.EmailUsuarioJaCadastradoException;
import com.foodProIA.FoodProIA.exception.EmpresaNaoEncontradaException;
import com.foodProIA.FoodProIA.exception.FuncionarioNaoEncontradoException;
import com.foodProIA.FoodProIA.exception.SetorNaoEncontradoException;
import com.foodProIA.FoodProIA.repository.EmpresaRepository;
import com.foodProIA.FoodProIA.repository.EnderecoRepository;
import com.foodProIA.FoodProIA.repository.FuncionarioRepository;
import com.foodProIA.FoodProIA.repository.SetorRepository;
import com.foodProIA.FoodProIA.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;
    private final SetorRepository setorRepository;
    private final EnderecoRepository enderecoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<FuncionarioResponseDTO> listarTodos(){
        List<FuncionarioEntity> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream().map(FuncionarioResponseDTO::new).toList();
    }


    @Transactional
    public FuncionarioResponseDTO inserir(FuncionarioRequestDTO dto) {

        if (usuarioRepository.existsByCpf(dto.getCpf())) {
            throw new CpfJaCadastradoException();
        }

        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new EmailUsuarioJaCadastradoException();
        }

        EmpresaEntity empresa = empresaRepository.findById(dto.getIdEmpresa())
                .orElseThrow(() -> new EmpresaNaoEncontradaException());

        SetorEntity setor = setorRepository.findById(dto.getIdSetor())
                .orElseThrow(() -> new SetorNaoEncontradoException());

        EnderecoEntity endereco = new EnderecoEntity();

        endereco.setComplemento(dto.getEndereco().getComplemento());
        endereco.setNumero(dto.getEndereco().getNumero());
        endereco.setRua(dto.getEndereco().getRua());
        endereco.setBairro(dto.getEndereco().getBairro());
        endereco.setCidade(dto.getEndereco().getCidade());
        endereco.setEstado(dto.getEndereco().getEstado());
        endereco.setPais(dto.getEndereco().getPais());
        endereco.setCep(dto.getEndereco().getCep());

        enderecoRepository.save(endereco);

        FuncionarioEntity funcionario = new FuncionarioEntity();

        // Dados de Usuario
        funcionario.setNome(dto.getNome());
        funcionario.setCpf(dto.getCpf());
        funcionario.setTelefone(dto.getTelefone());
        funcionario.setEmail(dto.getEmail());
        funcionario.setDataNascimento(dto.getDataNascimento());
        funcionario.setPapel(dto.getPapel());
        funcionario.setHashSenha(passwordEncoder.encode(dto.getSenha()));
        funcionario.setAtivo(true);

        // Dados de Funcionario
        funcionario.setCargo(dto.getCargo());
        funcionario.setCustoPorHora(dto.getCustoPorHora());
        funcionario.setEmpresa(empresa);
        funcionario.setSetor(setor);
        funcionario.setEndereco(endereco);

        funcionarioRepository.save(funcionario);

        return new FuncionarioResponseDTO(funcionario);
    }

    @Transactional
    public FuncionarioResponseDTO alterar(Long id, FuncionarioRequestDTO dto) {

        FuncionarioEntity funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException());

        if (!funcionario.getCpf().equals(dto.getCpf())
                && usuarioRepository.existsByCpf(dto.getCpf())) {
            throw new CpfJaCadastradoException();
        }

        if (!funcionario.getEmail().equals(dto.getEmail())
                && usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new EmailUsuarioJaCadastradoException();
        }

        EmpresaEntity empresa = empresaRepository.findById(dto.getIdEmpresa())
                .orElseThrow(() -> new EmpresaNaoEncontradaException());

        SetorEntity setor = setorRepository.findById(dto.getIdSetor())
                .orElseThrow(() -> new SetorNaoEncontradoException());

        // Dados de Usuario
        funcionario.setNome(dto.getNome());
        funcionario.setCpf(dto.getCpf());
        funcionario.setTelefone(dto.getTelefone());
        funcionario.setEmail(dto.getEmail());
        funcionario.setDataNascimento(dto.getDataNascimento());
        funcionario.setPapel(dto.getPapel());

        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            funcionario.setHashSenha(passwordEncoder.encode(dto.getSenha()));
        }

        // Dados de Funcionario
        funcionario.setCargo(dto.getCargo());
        funcionario.setCustoPorHora(dto.getCustoPorHora());
        funcionario.setEmpresa(empresa);
        funcionario.setSetor(setor);

        EnderecoEntity endereco = funcionario.getEndereco();

        endereco.setComplemento(dto.getEndereco().getComplemento());
        endereco.setNumero(dto.getEndereco().getNumero());
        endereco.setRua(dto.getEndereco().getRua());
        endereco.setBairro(dto.getEndereco().getBairro());
        endereco.setCidade(dto.getEndereco().getCidade());
        endereco.setEstado(dto.getEndereco().getEstado());
        endereco.setPais(dto.getEndereco().getPais());
        endereco.setCep(dto.getEndereco().getCep());

        enderecoRepository.save(endereco);

        funcionarioRepository.save(funcionario);

        return new FuncionarioResponseDTO(funcionario);
    }

    @Transactional
    public void excluir(Long id){
        FuncionarioEntity funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new FuncionarioNaoEncontradoException());
        funcionarioRepository.delete(funcionario);
    }

    @Transactional(readOnly = true)
    public FuncionarioResponseDTO buscarPorId(Long id){
        FuncionarioEntity funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new FuncionarioNaoEncontradoException());
        return new FuncionarioResponseDTO(funcionario);
    }

}
