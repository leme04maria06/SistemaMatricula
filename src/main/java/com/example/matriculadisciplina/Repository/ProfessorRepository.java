package com.example.matriculadisciplina.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.matriculadisciplina.Model.Disciplina;
import com.example.matriculadisciplina.Model.Pessoa;
import com.example.matriculadisciplina.Model.Professor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.persistence.Query;

@Repository
public class ProfessorRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean insert (Professor professor, Pessoa pessoax) {
        try {
            String comando;
            Query query;
            comando = "insert into pessoa (nome, endereco, cidade, uf, telefone, email, idade) values";
            comando += "(:vnome, :vendereco, :vcidade, :vuf, :vtelefone, :vemail, :vidade)";
            query = em.createNativeQuery(comando);
            query.setParameter("vnome", pessoax.getNome());
            query.setParameter("vendereco", pessoax.getEndereco());
            query.setParameter("vcidade", pessoax.getCidade());
            query.setParameter("vuf", pessoax.getUf());
            query.setParameter("vtelefone", pessoax.getTelefone());
            query.setParameter("vemail", pessoax.getEmail());
            query.setParameter("vidade", pessoax.getIdade());
            query.executeUpdate();

            Number idGerado = (Number) em
                    .createNativeQuery("SELECT LAST_INSERT_ID()")
                    .getSingleResult();

            comando = "INSERT INTO professor (";
            comando +=  "id_pessoa, siape, area, formacao) VALUES (";
            comando += ":vid, :vsiape, :varea, :vformacao)";
            query = em.createNativeQuery(comando);
            query.setParameter("vid", idGerado.intValue());
            query.setParameter("vsiape", professor.getSiape());
            query.setParameter("varea", professor.getArea());
            query.setParameter("vformacao", professor.getFormacao());
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Professor> findAll() {
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
                p.siape,
                p.area,
                p.formacao
            FROM pessoa pe
            INNER JOIN professor p ON pe.id_pessoa = p.id_pessoa
            ORDER BY pe.nome
            """;

        Query query = em.createNativeQuery(comando, Professor.class);

        return query.getResultList();
    }

// BUSCAR UM PROFESSOR PELO ID
    public Professor findById(int id) {
        try {
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
                    p.siape,
                    p.area,
                    p.formacao
                FROM pessoa pe
                INNER JOIN professor p ON pe.id_pessoa = p.id_pessoa
                WHERE pe.id_pessoa = :id
                """;

            Query query = em.createNativeQuery(comando, Professor.class);

            query.setParameter("id", id);

            return (Professor) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }


    // ATUALIZAR PROFESSOR
    @Transactional
    public void update(
            int idProfessor,
            String nome,
            String endereco,
            String cidade,
            String uf,
            String telefone,
            String email,
            int idade,
            String siape,
            String area,
            String formacao) {

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
        queryPessoa.setParameter("id", idProfessor);

        queryPessoa.executeUpdate();


        String comandoProfessor = """
            UPDATE professor
            SET siape = :siape,
                area = :area,
                formacao = :formacao
            WHERE id_pessoa = :id
            """;

        Query queryProfessor = em.createNativeQuery(comandoProfessor);

        queryProfessor.setParameter("siape", siape);
        queryProfessor.setParameter("area", area);
        queryProfessor.setParameter("formacao", formacao);
        queryProfessor.setParameter("id", idProfessor);

        queryProfessor.executeUpdate();
    }


    @Transactional
    public void delete(int idProfessor) {

        // 1. Excluir o usuário associado à pessoa
        String comandoUsuario = """
            DELETE FROM usuario
            WHERE id_pessoa = :id
            """;

        Query queryUsuario = em.createNativeQuery(comandoUsuario);

        queryUsuario.setParameter("id", idProfessor);

        queryUsuario.executeUpdate();


        // 2. Excluir o professor
        String comandoProfessor = """
            DELETE FROM professor
            WHERE id_pessoa = :id
            """;

        Query queryProfessor = em.createNativeQuery(comandoProfessor);

        queryProfessor.setParameter("id", idProfessor);

        queryProfessor.executeUpdate();


        // 3. Excluir a pessoa
        String comandoPessoa = """
            DELETE FROM pessoa
            WHERE id_pessoa = :id
            """;

        Query queryPessoa = em.createNativeQuery(comandoPessoa);

        queryPessoa.setParameter("id", idProfessor);

        queryPessoa.executeUpdate();
    }
}

