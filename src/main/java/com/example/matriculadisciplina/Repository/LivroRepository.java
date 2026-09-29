package com.example.matriculadisciplina.Repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.matriculadisciplina.Model.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@Repository
public class LivroRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean insert(Livro livro) {
        String comando = """
            INSERT INTO livro (titulo, autor, editora, isbn, edicao, ano, sinopse, capa_imagem)
            VALUES (:titulo, :autor, :editora, :isbn, :edicao, :ano, :sinopse, :capaImagem)
            """;
        Query query = em.createNativeQuery(comando);
        query.setParameter("titulo", livro.getTitulo());
        query.setParameter("autor", livro.getAutor());
        query.setParameter("editora", livro.getEditora());
        query.setParameter("isbn", livro.getIsbn());
        query.setParameter("edicao", livro.getEdicao());
        query.setParameter("ano", livro.getAno());
        query.setParameter("sinopse", livro.getSinopse());
        query.setParameter("capaImagem", livro.getCapaImagem());
        return query.executeUpdate() == 1;
    }

    @SuppressWarnings("unchecked")
    public List<Livro> findAll() {
        String comando = """
            SELECT * FROM livro ORDER BY titulo
            """;
        return em.createNativeQuery(comando, Livro.class).getResultList();
    }

    public Livro findById(int id) {
        String comando = """
            SELECT * FROM livro WHERE id_livro = :id
            """;
        try {
            return (Livro) em.createNativeQuery(comando, Livro.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void update(Livro livro) {
        String comando = """
            UPDATE livro
            SET titulo = :titulo,
                autor = :autor,
                editora = :editora,
                isbn = :isbn,
                edicao = :edicao,
                ano = :ano,
                sinopse = :sinopse,
                capa_imagem = :capaImagem
            WHERE id_livro = :id
            """;
        Query query = em.createNativeQuery(comando);
        query.setParameter("titulo", livro.getTitulo());
        query.setParameter("autor", livro.getAutor());
        query.setParameter("editora", livro.getEditora());
        query.setParameter("isbn", livro.getIsbn());
        query.setParameter("edicao", livro.getEdicao());
        query.setParameter("ano", livro.getAno());
        query.setParameter("sinopse", livro.getSinopse());
        query.setParameter("capaImagem", livro.getCapaImagem());
        query.setParameter("id", livro.getIdLivro());
        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        String comando = """
            DELETE FROM livro WHERE id_livro = :id
            """;
        em.createNativeQuery(comando).setParameter("id", id).executeUpdate();
    }
}
