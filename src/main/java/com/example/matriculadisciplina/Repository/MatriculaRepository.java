package com.example.matriculadisciplina.Repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.matriculadisciplina.Model.Matricula;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@Repository
public class MatriculaRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean insert(Matricula matricula) {
        String comando = """
            INSERT INTO matricula (id_aluno, id_oferta, data_matricula, status)
            VALUES (:aluno, :oferta, :dataMatricula, :status)
            """;
        Query query = em.createNativeQuery(comando);
        query.setParameter("aluno", matricula.getAluno().getIdAluno());
        query.setParameter("oferta", matricula.getOferta().getIdOferta());
        query.setParameter("dataMatricula", matricula.getDataMatricula());
        query.setParameter("status", matricula.getStatus());
        return query.executeUpdate() == 1;
    }

    @SuppressWarnings("unchecked")
    public List<Matricula> findAll() {
        String comando = """
            SELECT * FROM matricula ORDER BY data_matricula
            """;
        return em.createNativeQuery(comando, Matricula.class).getResultList();
    }

    public Matricula findById(int id) {
        String comando = """
            SELECT * FROM matricula WHERE id_matricula = :id
            """;
        try {
            return (Matricula) em.createNativeQuery(comando, Matricula.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void update(Matricula matricula) {
        String comando = """
            UPDATE matricula
            SET id_aluno = :aluno,
                id_oferta = :oferta,
                data_matricula = :dataMatricula,
                status = :status
            WHERE id_matricula = :id
            """;
        Query query = em.createNativeQuery(comando);
        query.setParameter("aluno", matricula.getAluno().getIdAluno());
        query.setParameter("oferta", matricula.getOferta().getIdOferta());
        query.setParameter("dataMatricula", matricula.getDataMatricula());
        query.setParameter("status", matricula.getStatus());
        query.setParameter("id", matricula.getIdMatricula());
        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        String comando = """
            DELETE FROM matricula WHERE id_matricula = :id
            """;
        em.createNativeQuery(comando).setParameter("id", id).executeUpdate();
    }
}
