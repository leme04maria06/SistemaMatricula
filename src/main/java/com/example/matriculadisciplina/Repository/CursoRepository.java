package com.example.matriculadisciplina.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.matriculadisciplina.Model.Curso;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class CursoRepository {

    @PersistenceContext
    private EntityManager em;


    @Transactional
    public boolean insert(Curso curso) {

        try {

            String comando = """
                INSERT INTO curso
                (nome, ano_inicio)
                VALUES
                (:vnome, :vano_inicio)
                """;

            Query query = em.createNativeQuery(comando);

            query.setParameter("vnome", curso.getNome());
            query.setParameter("vano_inicio", curso.getAno_inicio());

            query.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    public List<Curso> findAll() {

        String comando = """
            SELECT *
            FROM curso
            ORDER BY nome
            """;

        Query query = em.createNativeQuery(comando, Curso.class);

        return query.getResultList();
    }


    public Curso findById(int idCurso) {

        String comando = """
            SELECT *
            FROM curso
            WHERE id_curso = :id
            """;

        Query query = em.createNativeQuery(comando, Curso.class);

        query.setParameter("id", idCurso);

        try {

            return (Curso) query.getSingleResult();

        } catch (Exception e) {

            return null;
        }
    }


    @Transactional
    public void update(
            int idCurso,
            String nome,
            int ano_inicio
    ) {

        String comando = """
            UPDATE curso
            SET nome = :nome,
                ano_inicio = :ano_inicio
            WHERE id_curso = :id
            """;

        Query query = em.createNativeQuery(comando);

        query.setParameter("nome", nome);
        query.setParameter("ano_inicio", ano_inicio);
        query.setParameter("id", idCurso);

        query.executeUpdate();
    }


    @Transactional
    public void delete(int idCurso) {

        String comando = """
            DELETE FROM curso
            WHERE id_curso = :id
            """;

        Query query = em.createNativeQuery(comando);

        query.setParameter("id", idCurso);

        query.executeUpdate();
    }
}