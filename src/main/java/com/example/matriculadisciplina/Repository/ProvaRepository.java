package com.example.matriculadisciplina.Repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.matriculadisciplina.Model.Prova;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@Repository
public class ProvaRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean insert(Prova prova) {
        String comando = """
            INSERT INTO prova (id_oferta, data, conteudo, peso)
            VALUES (:oferta, :data, :conteudo, :peso)
            """;
        Query query = em.createNativeQuery(comando);
        query.setParameter("oferta", prova.getOferta().getIdOferta());
        query.setParameter("data", prova.getData());
        query.setParameter("conteudo", prova.getConteudo());
        query.setParameter("peso", prova.getPeso());
        return query.executeUpdate() == 1;
    }

    @SuppressWarnings("unchecked")
    public List<Prova> findAll() {
        String comando = """
            SELECT * FROM prova ORDER BY data
            """;
        return em.createNativeQuery(comando, Prova.class).getResultList();
    }

    public Prova findById(int id) {
        String comando = """
            SELECT * FROM prova WHERE id_prova = :id
            """;
        try {
            return (Prova) em.createNativeQuery(comando, Prova.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void update(Prova prova) {
        String comando = """
            UPDATE prova
            SET id_oferta = :oferta,
                data = :data,
                conteudo = :conteudo,
                peso = :peso
            WHERE id_prova = :id
            """;
        Query query = em.createNativeQuery(comando);
        query.setParameter("oferta", prova.getOferta().getIdOferta());
        query.setParameter("data", prova.getData());
        query.setParameter("conteudo", prova.getConteudo());
        query.setParameter("peso", prova.getPeso());
        query.setParameter("id", prova.getIdProva());
        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        String comando = """
            DELETE FROM prova WHERE id_prova = :id
            """;
        em.createNativeQuery(comando).setParameter("id", id).executeUpdate();
    }
}
