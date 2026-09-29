package com.example.matriculadisciplina.Repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.matriculadisciplina.Model.DisciplinaLivro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@Repository
public class DisciplinaLivroRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean insert(DisciplinaLivro disciplinaLivro) {
        String comando = """
            INSERT INTO disciplina_livro (id_disciplina, id_livro, tipo)
            VALUES (:idDisciplina, :idLivro, :tipo)
            """;
        Query query = em.createNativeQuery(comando);
        query.setParameter("idDisciplina", disciplinaLivro.getDisciplina().getIdDisciplina());
        query.setParameter("idLivro", disciplinaLivro.getLivro().getIdLivro());
        query.setParameter("tipo", disciplinaLivro.getTipo().name());
        return query.executeUpdate() == 1;
    }

    @SuppressWarnings("unchecked")
    public List<DisciplinaLivro> findAll() {
        String comando = """
            SELECT * FROM disciplina_livro ORDER BY id_disciplina, id_livro
            """;
        return em.createNativeQuery(comando, DisciplinaLivro.class).getResultList();
    }

    public DisciplinaLivro findById(int idDisciplina, int idLivro) {
        String comando = """
            SELECT * FROM disciplina_livro
            WHERE id_disciplina = :idDisciplina AND id_livro = :idLivro
            """;
        try {
            return (DisciplinaLivro) em.createNativeQuery(comando, DisciplinaLivro.class)
                    .setParameter("idDisciplina", idDisciplina)
                    .setParameter("idLivro", idLivro).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void update(int idDisciplina, int idLivro, DisciplinaLivro disciplinaLivro) {
        String comando = """
            UPDATE disciplina_livro
            SET id_disciplina = :novoIdDisciplina,
                id_livro = :novoIdLivro,
                tipo = :tipo
            WHERE id_disciplina = :idDisciplina AND id_livro = :idLivro
            """;
        Query query = em.createNativeQuery(comando);
        query.setParameter("novoIdDisciplina", disciplinaLivro.getDisciplina().getIdDisciplina());
        query.setParameter("novoIdLivro", disciplinaLivro.getLivro().getIdLivro());
        query.setParameter("tipo", disciplinaLivro.getTipo().name());
        query.setParameter("idDisciplina", idDisciplina);
        query.setParameter("idLivro", idLivro);
        query.executeUpdate();
    }

    @Transactional
    public void delete(int idDisciplina, int idLivro) {
        String comando = """
            DELETE FROM disciplina_livro
            WHERE id_disciplina = :idDisciplina AND id_livro = :idLivro
            """;
        em.createNativeQuery(comando)
                .setParameter("idDisciplina", idDisciplina)
                .setParameter("idLivro", idLivro).executeUpdate();
    }
}
