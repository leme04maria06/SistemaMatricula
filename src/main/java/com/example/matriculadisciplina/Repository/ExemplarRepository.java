package com.example.matriculadisciplina.Repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.matriculadisciplina.Model.Exemplar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@Repository
public class ExemplarRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean insert(Exemplar exemplar) {
        String comando = """
            INSERT INTO exemplar (id_livro, tombo, status)
            VALUES (:livro, :tombo, :status)
            """;
        Query query = em.createNativeQuery(comando);
        query.setParameter("livro", exemplar.getLivro().getIdLivro());
        query.setParameter("tombo", exemplar.getTombo());
        query.setParameter("status", exemplar.getStatus().name());
        return query.executeUpdate() == 1;
    }

    @SuppressWarnings("unchecked")
    public List<Exemplar> findAll() {
        String comando = """
            SELECT * FROM exemplar ORDER BY tombo
            """;
        return em.createNativeQuery(comando, Exemplar.class).getResultList();
    }

    public Exemplar findById(int id) {
        String comando = """
            SELECT * FROM exemplar WHERE id_exemplar = :id
            """;
        try {
            return (Exemplar) em.createNativeQuery(comando, Exemplar.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void update(Exemplar exemplar) {
        String comando = """
            UPDATE exemplar
            SET id_livro = :livro,
                tombo = :tombo,
                status = :status
            WHERE id_exemplar = :id
            """;
        Query query = em.createNativeQuery(comando);
        query.setParameter("livro", exemplar.getLivro().getIdLivro());
        query.setParameter("tombo", exemplar.getTombo());
        query.setParameter("status", exemplar.getStatus().name());
        query.setParameter("id", exemplar.getIdExemplar());
        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        String comando = """
            DELETE FROM exemplar WHERE id_exemplar = :id
            """;
        em.createNativeQuery(comando).setParameter("id", id).executeUpdate();
    }
}
