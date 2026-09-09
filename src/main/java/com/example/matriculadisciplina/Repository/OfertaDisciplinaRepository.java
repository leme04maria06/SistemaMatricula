package com.example.matriculadisciplina.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.matriculadisciplina.Model.OfertaDisciplina;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class OfertaDisciplinaRepository {

    @PersistenceContext
    private EntityManager em;


    @Transactional
    public boolean insert(OfertaDisciplina ofertaDisciplina) {

        try {

            String comando = """
                INSERT INTO oferta_disciplina
                (id_disciplina, id_professor, ano, semestre)
                VALUES
                (:id_disciplina, :id_professor, :ano, :semestre)
                """;

            Query query = em.createNativeQuery(comando);

            query.setParameter(
                "id_disciplina",
                ofertaDisciplina.getIdDisciplina()
            );

            query.setParameter(
                "id_professor",
                ofertaDisciplina.getIdProfessor()
            );

            query.setParameter(
                "ano",
                ofertaDisciplina.getAno()
            );

            query.setParameter(
                "semestre",
                ofertaDisciplina.getSemestre()
            );

            query.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    public List<OfertaDisciplina> findAll() {

        String comando = """
            SELECT *
            FROM oferta_disciplina
            ORDER BY ano, semestre
            """;

        Query query = em.createNativeQuery(
            comando,
            OfertaDisciplina.class
        );

        return query.getResultList();
    }

    public List<OfertaDisciplina> findByCurso(int idCurso) {

        String comando = """
            SELECT od.*
            FROM oferta_disciplina od
            INNER JOIN disciplina d
                ON od.id_disciplina = d.id_disciplina
            WHERE d.id_curso = :idCurso
            ORDER BY od.ano, od.semestre
            """;

        Query query = em.createNativeQuery(
            comando,
            OfertaDisciplina.class
        );

        query.setParameter("idCurso", idCurso);

        return query.getResultList();
    }

    public OfertaDisciplina findById(int idOferta) {

        String comando = """
            SELECT *
            FROM oferta_disciplina
            WHERE id_oferta = :idOferta
            """;

        Query query = em.createNativeQuery(
            comando,
            OfertaDisciplina.class
        );

        query.setParameter("idOferta", idOferta);

        try {
            return (OfertaDisciplina) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}