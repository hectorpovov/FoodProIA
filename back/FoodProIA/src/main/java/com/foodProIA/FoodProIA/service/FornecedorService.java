package com.foodProIA.FoodProIA.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.foodProIA.FoodProIA.dto.request.EnderecoRequestDTO;
import com.foodProIA.FoodProIA.dto.request.FornecedorRequestDTO;
import com.foodProIA.FoodProIA.dto.request.FraudeRequestDTO;
import com.foodProIA.FoodProIA.dto.response.FornecedorResponseDTO;
import com.foodProIA.FoodProIA.entity.EnderecoEntity;
import com.foodProIA.FoodProIA.entity.FornecedorEntity;
import com.foodProIA.FoodProIA.entity.FraudeEntity;
import com.foodProIA.FoodProIA.repository.FornecedorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;


    // =========================================================
    // INSERIR
    // =========================================================

    @Transactional
    public FornecedorResponseDTO inserir(
            FornecedorRequestDTO dto) {

        FornecedorEntity fornecedor =
                new FornecedorEntity();

        fornecedor.setNome(dto.getNome());
        fornecedor.setRegistroEmpresa(
                dto.getRegistroEmpresa()
        );
        fornecedor.setTelefone(
                dto.getTelefone()
        );
        fornecedor.setEmail(
                dto.getEmail()
        );
        fornecedor.setTipoInsumo(
                dto.getTipoInsumo()
        );

        /*
         * Um fornecedor começa ativo.
         */
        fornecedor.setStatus(true);

        /*
         * Documentos individuais.
         */
        fornecedor.setCertidaoDeNegativas(
                converterArquivo(
                        dto.getCertidaoDeNegativas()
                )
        );

        fornecedor.setLicensasDeFuncionamento(
                converterArquivo(
                        dto.getLicensasDeFuncionamento()
                )
        );

        /*
         * Lista de certificações.
         *
         * Cada MultipartFile é convertido para byte[]
         * e o JPA vai armazenar cada elemento na tabela
         * TB_FORNECEDOR_CERTIFICACAO.
         */
        fornecedor.setCertificacoesDeQualidade(
                converterArquivos(
                        dto.getCertificacoesDeQualidade()
                )
        );

        /*
         * Cria o endereço junto com o fornecedor.
         */
        EnderecoEntity endereco =
                criarEndereco(dto.getEndereco());

        fornecedor.setEndereco(endereco);

        FornecedorEntity salvo =
                fornecedorRepository.save(fornecedor);

        return new FornecedorResponseDTO(salvo);
    }


    // =========================================================
    // ALTERAR
    // =========================================================

    @Transactional
    public FornecedorResponseDTO alterar(
            Long id,
            FornecedorRequestDTO dto) {

        FornecedorEntity fornecedor =
                buscarEntidade(id);

        /*
         * Só altera os atributos enviados.
         * null significa "não alterar".
         */

        if (dto.getNome() != null) {
            fornecedor.setNome(
                    dto.getNome()
            );
        }

        if (dto.getRegistroEmpresa() != null) {
            fornecedor.setRegistroEmpresa(
                    dto.getRegistroEmpresa()
            );
        }

        if (dto.getTelefone() != null) {
            fornecedor.setTelefone(
                    dto.getTelefone()
            );
        }

        if (dto.getEmail() != null) {
            fornecedor.setEmail(
                    dto.getEmail()
            );
        }

        if (dto.getTipoInsumo() != null) {
            fornecedor.setTipoInsumo(
                    dto.getTipoInsumo()
            );
        }


        // -----------------------------------------------------
        // CERTIDÃO DE NEGATIVAS
        // -----------------------------------------------------

        if (dto.getCertidaoDeNegativas() != null
                && !dto.getCertidaoDeNegativas().isEmpty()) {

            fornecedor.setCertidaoDeNegativas(
                    converterArquivo(
                            dto.getCertidaoDeNegativas()
                    )
            );
        }


        // -----------------------------------------------------
        // LICENÇA DE FUNCIONAMENTO
        // -----------------------------------------------------

        if (dto.getLicensasDeFuncionamento() != null
                && !dto.getLicensasDeFuncionamento().isEmpty()) {

            fornecedor.setLicensasDeFuncionamento(
                    converterArquivo(
                            dto.getLicensasDeFuncionamento()
                    )
            );
        }


        // -----------------------------------------------------
        // CERTIFICAÇÕES DE QUALIDADE
        // -----------------------------------------------------

        /*
         * Se a lista vier null:
         *     não altera as certificações atuais.
         *
         * Se a lista vier preenchida:
         *     substitui a lista atual.
         */
        if (dto.getCertificacoesDeQualidade() != null) {

            fornecedor.setCertificacoesDeQualidade(
                    converterArquivos(
                            dto.getCertificacoesDeQualidade()
                    )
            );
        }


        // -----------------------------------------------------
        // ENDEREÇO
        // -----------------------------------------------------

        if (dto.getEndereco() != null) {

            atualizarEndereco(
                    fornecedor.getEndereco(),
                    dto.getEndereco()
            );
        }

        /*
         * Como o fornecedor está gerenciado pelo Hibernate
         * dentro da transação, não é obrigatório chamar save().
         */
        return new FornecedorResponseDTO(fornecedor);
    }


    // =========================================================
    // EXCLUIR
    // =========================================================

    
    @Transactional
    public void excluir(Long id) {

        FornecedorEntity fornecedor =
                fornecedorRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Fornecedor não encontrado: " + id
                                )
                        );

        fornecedorRepository.delete(fornecedor);
    }




    // =========================================================
    // BUSCAR POR ID
    // =========================================================

    @Transactional(readOnly = true)
    public FornecedorResponseDTO buscarPorId(
            Long id) {

        FornecedorEntity fornecedor =
                buscarEntidade(id);

        return new FornecedorResponseDTO(fornecedor);
    }


    // =========================================================
    // ADICIONAR FRAUDE
    // =========================================================

    @Transactional
    public FornecedorResponseDTO adicionarFraude(
            Long fornecedorId,
            FraudeRequestDTO dto) {

        FornecedorEntity fornecedor =
                buscarEntidade(fornecedorId);

        FraudeEntity fraude =
                new FraudeEntity();

        fraude.setDataEHorario(
                dto.getDataEHorario()
        );

        fraude.setClassificacao(
                dto.getClassificacao()
        );

        fraude.setDescricao(
                dto.getDescricao()
        );

        /*
         * A fraude é atribuída ao fornecedor pelo próprio
         * usuário.
         */
        fornecedor.adicionarFraude(fraude);

        return new FornecedorResponseDTO(fornecedor);
    }


    // =========================================================
    // REMOVER FRAUDE
    // =========================================================

    @Transactional
    public FornecedorResponseDTO removerFraude(
            Long fornecedorId,
            Long fraudeId) {

        FornecedorEntity fornecedor =
                buscarEntidade(fornecedorId);

        FraudeEntity fraude =
                fornecedor.getFraudes()
                        .stream()
                        .filter(
                                f -> f.getId().equals(fraudeId)
                        )
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Fraude "
                                                + fraudeId
                                                + " não encontrada "
                                                + "neste fornecedor."
                                )
                        );

        fornecedor.removerFraude(fraude);

        /*
         * orphanRemoval = true fará o Hibernate remover
         * a fraude do banco.
         */

        return new FornecedorResponseDTO(fornecedor);
    }


    // =========================================================
    // CRIAR ENDEREÇO
    // =========================================================

    private EnderecoEntity criarEndereco(
            EnderecoRequestDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException(
                    "O endereço é obrigatório."
            );
        }

        EnderecoEntity endereco =
                new EnderecoEntity();

        endereco.setComplemento(
                dto.getComplemento()
        );

        endereco.setNumero(
                dto.getNumero()
        );

        endereco.setRua(
                dto.getRua()
        );

        endereco.setBairro(
                dto.getBairro()
        );

        endereco.setCidade(
                dto.getCidade()
        );

        endereco.setEstado(
                dto.getEstado()
        );

        endereco.setPais(
                dto.getPais()
        );

        endereco.setCep(
                dto.getCep()
        );

        return endereco;
    }


    // =========================================================
    // ALTERAR ENDEREÇO
    // =========================================================

    private void atualizarEndereco(
            EnderecoEntity endereco,
            EnderecoRequestDTO dto) {

        if (endereco == null) {

            /*
             * Se por algum motivo o fornecedor não possuir
             * endereço, criamos um novo não é possível aqui
             * porque o método recebe somente a entidade.
             */
            throw new IllegalStateException(
                    "O fornecedor não possui um endereço."
            );
        }

        if (dto.getComplemento() != null) {
            endereco.setComplemento(
                    dto.getComplemento()
            );
        }

        if (dto.getNumero() != null) {
            endereco.setNumero(
                    dto.getNumero()
            );
        }

        if (dto.getRua() != null) {
            endereco.setRua(
                    dto.getRua()
            );
        }

        if (dto.getBairro() != null) {
            endereco.setBairro(
                    dto.getBairro()
            );
        }

        if (dto.getCidade() != null) {
            endereco.setCidade(
                    dto.getCidade()
            );
        }

        if (dto.getEstado() != null) {
            endereco.setEstado(
                    dto.getEstado()
            );
        }

        if (dto.getPais() != null) {
            endereco.setPais(
                    dto.getPais()
            );
        }

        if (dto.getCep() != null) {
            endereco.setCep(
                    dto.getCep()
            );
        }
    }


    // =========================================================
    // CONVERTER UM ARQUIVO
    // =========================================================

    private byte[] converterArquivo(
            MultipartFile arquivo) {

        if (arquivo == null || arquivo.isEmpty()) {
            return null;
        }

        try {

            return arquivo.getBytes();

        } catch (IOException e) {

            throw new IllegalArgumentException(
                    "Erro ao ler o arquivo enviado.",
                    e
            );
        }
    }


    // =========================================================
    // CONVERTER LISTA DE ARQUIVOS
    // =========================================================

    private List<byte[]> converterArquivos(
            List<MultipartFile> arquivos) {

        List<byte[]> resultado =
                new ArrayList<>();

        if (arquivos == null) {
            return resultado;
        }

        for (MultipartFile arquivo : arquivos) {

            if (arquivo != null
                    && !arquivo.isEmpty()) {

                resultado.add(
                        converterArquivo(arquivo)
                );
            }
        }

        return resultado;
    }


    // =========================================================
    // BUSCAR ENTIDADE
    // =========================================================

    private FornecedorEntity buscarEntidade(
            Long id) {

        return fornecedorRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Fornecedor não encontrado: "
                                        + id
                        )
                );
    }

}

