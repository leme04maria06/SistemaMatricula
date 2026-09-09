package com.example.matriculadisciplina.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.matriculadisciplina.Model.Aluno;
import com.example.matriculadisciplina.Model.Pessoa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class AlunoRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean insert(Aluno aluno, Pessoa pessoa) {
        try {

            // 1. Insere os dados da Pessoa
            String comando = """
                INSERT INTO pessoa
                (nome, endereco, cidade, uf, telefone, email, idade)
                VALUES
                (:vnome, :vendereco, :vcidade, :vuf, :vtelefone, :vemail, :vidade)
                """;

            Query query = em.createNativeQuery(comando);

            query.setParameter("vnome", pessoa.getNome());
            query.setParameter("vendereco", pessoa.getEndereco());
            query.setParameter("vcidade", pessoa.getCidade());
            query.setParameter("vuf", pessoa.getUf());
            query.setParameter("vtelefone", pessoa.getTelefone());
            query.setParameter("vemail", pessoa.getEmail());
            query.setParameter("vidade", pessoa.getIdade());

            query.executeUpdate();

            // 2. Pega o ID gerado pela tabela pessoa
            Number idGerado = (Number) em
                    .createNativeQuery("SELECT LAST_INSERT_ID()")
                    .getSingleResult();

            // 3. Insere os dados específicos do aluno
            comando = """
                INSERT INTO aluno
                (id_pessoa, prontuario, nome_responsavel,
                 contato_responsavel, id_curso)
                VALUES
                (:vid, :vprontuario, :vnome_responsavel,
                 :vcontato_responsavel, :vid_curso)
                """;

            query = em.createNativeQuery(comando);

            query.setParameter("vid", idGerado.intValue());
            query.setParameter("vprontuario", aluno.getProntuario());
            query.setParameter("vnome_responsavel", aluno.getNomeResponsavel());
            query.setParameter("vcontato_responsavel", aluno.getContatoResponsavel());
            query.setParameter("vid_curso", aluno.getIdCurso());

            query.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Aluno> findAll() {

        String comando = """
            SELECT
                pe.id_pessoa,
                pe.nome,
                pe.idade,
                pe.email,
                pe.telefone,
                pe.endereco,
                pe.cidade,
                pe.uf,
                a.prontuario,
                a.nome_responsavel,
                a.contato_responsavel,
                a.id_curso
            FROM pessoa pe
            INNER JOIN aluno a ON pe.id_pessoa = a.id_pessoa
            ORDER BY pe.nome
            """;

        Query query = em.createNativeQuery(comando, Aluno.class);

        return query.getResultList();
    }

    public Aluno findById(int idAluno) {

    String comando = """
        SELECT
            pe.id_pessoa,
            pe.nome,
            pe.idade,
            pe.email,
            pe.telefone,
            pe.endereco,
            pe.cidade,
            pe.uf,
            a.prontuario,
            a.nome_responsavel,
            a.contato_responsavel,
            a.id_curso
        FROM pessoa pe
        INNER JOIN aluno a ON pe.id_pessoa = a.id_pessoa
        WHERE pe.id_pessoa = :id
        """;

    Query query = em.createNativeQuery(comando, Aluno.class);
    query.setParameter("id", idAluno);

    try {
        return (Aluno) query.getSingleResult();
    } catch (Exception e) {
        return null;
    }
}


    @Transactional
    public void update(
            int idAluno,
            String nome,
            String endereco,
            String cidade,
            String uf,
            String telefone,
            String email,
            int idade,
            String prontuario,
            String nomeResponsavel,
            String contatoResponsavel,
            int idCurso
    ) {

    // Atualiza os dados da Pessoa
    String comandoPessoa = """
        UPDATE pessoa
        SET nome = :nome,
            endereco = :endereco,
            cidade = :cidade,
            uf = :uf,
            telefone = :telefone,
            email = :email,
            idade = :idade
        WHERE id_pessoa = :id
        """;

    Query queryPessoa = em.createNativeQuery(comandoPessoa);

    queryPessoa.setParameter("nome", nome);
    queryPessoa.setParameter("endereco", endereco);
    queryPessoa.setParameter("cidade", cidade);
    queryPessoa.setParameter("uf", uf);
    queryPessoa.setParameter("telefone", telefone);
    queryPessoa.setParameter("email", email);
    queryPessoa.setParameter("idade", idade);
    queryPessoa.setParameter("id", idAluno);

    queryPessoa.executeUpdate();


    // Atualiza os dados específicos do Aluno
    String comandoAluno = """
        UPDATE aluno
        SET prontuario = :prontuario,
            nome_responsavel = :nomeResponsavel,
            contato_responsavel = :contatoResponsavel,
            id_curso = :idCurso
        WHERE id_pessoa = :id
        """;

    Query queryAluno = em.createNativeQuery(comandoAluno);

    queryAluno.setParameter("prontuario", prontuario);
    queryAluno.setParameter("nomeResponsavel", nomeResponsavel);
    queryAluno.setParameter("contatoResponsavel", contatoResponsavel);
    queryAluno.setParameter("idCurso", idCurso);
    queryAluno.setParameter("id", idAluno);

    queryAluno.executeUpdate();
}


    @Transactional
    public void delete(int idAluno) {

        // Primeiro exclui o usuário ligado ao aluno
        String comandoUsuario = """
            DELETE FROM usuario
            WHERE id_pessoa = :id
            """;

        Query queryUsuario = em.createNativeQuery(comandoUsuario);
        queryUsuario.setParameter("id", idAluno);
        queryUsuario.executeUpdate();


        // Depois exclui o aluno
        String comandoAluno = """
            DELETE FROM aluno
            WHERE id_pessoa = :id
            """;

        Query queryAluno = em.createNativeQuery(comandoAluno);
        queryAluno.setParameter("id", idAluno);
        queryAluno.executeUpdate();


        // Por último exclui a pessoa
        String comandoPessoa = """
            DELETE FROM pessoa
            WHERE id_pessoa = :id
            """;

        Query queryPessoa = em.createNativeQuery(comandoPessoa);
        queryPessoa.setParameter("id", idAluno);
        queryPessoa.executeUpdate();
    }
}