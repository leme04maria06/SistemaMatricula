package com.example.matriculadisciplina.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.matriculadisciplina.Model.Aluno;
import com.example.matriculadisciplina.Model.OfertaDisciplina;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class MatriculaRepository {

    @PersistenceContext
    private EntityManager em;

    public boolean existeMatricula(
            Aluno aluno,
            OfertaDisciplina oferta) {

        String comando = """
            SELECT COUNT(*)
            FROM matricula
            WHERE id_aluno = :idAluno
            AND id_oferta = :idOferta
            """;

        Query query = em.createNativeQuery(comando);

        query.setParameter("idAluno", aluno.getIdAluno());
        query.setParameter("idOferta", oferta.getIdOferta());

        Number resultado = (Number) query.getSingleResult();

        return resultado.intValue() > 0;
    }

    @Transactional
    public boolean inserir(
            Aluno aluno,
            OfertaDisciplina oferta) {

        try {

            String comando = """
                INSERT INTO matricula
                (id_aluno, id_oferta)
                VALUES
                (:idAluno, :idOferta)
                """;

            Query query = em.createNativeQuery(comando);

            query.setParameter("idAluno", aluno.getIdAluno());
            query.setParameter("idOferta", oferta.getIdOferta());

            query.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public List<Object[]> findMatriculasByAluno(int idAluno) {

        String comando = """
            SELECT
                m.id_matricula,
                d.nome AS disciplina,
                p.nome AS professor,
                od.ano,
                od.semestre
            FROM matricula m

            INNER JOIN oferta_disciplina od
                ON m.id_oferta = od.id_oferta

            INNER JOIN disciplina d
                ON od.id_disciplina = d.id_disciplina

            INNER JOIN professor pr
                ON od.id_professor = pr.id_pessoa

            INNER JOIN pessoa p
                ON pr.id_pessoa = p.id_pessoa

            WHERE m.id_aluno = :idAluno

            ORDER BY od.ano, od.semestre, d.nome
            """;

        Query query = em.createNativeQuery(comando);

        query.setParameter("idAluno", idAluno);

        return query.getResultList();
    }


    public List<Object[]> findMatriculasByProfessor(int idProfessor) {

        String comando = """
            SELECT
                m.id_matricula,
                d.nome AS disciplina,
                p.nome AS aluno,
                a.prontuario,
                od.ano,
                od.semestre
            FROM matricula m

            INNER JOIN oferta_disciplina od
                ON m.id_oferta = od.id_oferta

            INNER JOIN disciplina d
                ON od.id_disciplina = d.id_disciplina

            INNER JOIN aluno a
                ON m.id_aluno = a.id_pessoa

            INNER JOIN pessoa p
                ON a.id_pessoa = p.id_pessoa

            WHERE od.id_professor = :idProfessor

            ORDER BY od.ano, od.semestre, d.nome, p.nome
            """;

        Query query = em.createNativeQuery(comando);

        query.setParameter("idProfessor", idProfessor);

        return query.getResultList();
    }
}