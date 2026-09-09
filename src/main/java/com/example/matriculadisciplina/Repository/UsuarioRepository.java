package com.example.matriculadisciplina.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.matriculadisciplina.Model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean insert(Usuario usuario) {
        try {
            String comando;
            Query query;

            comando = "INSERT INTO usuario (login, senha, tipo_usuario) VALUES ";
            comando += "(:vlogin, :vsenha, :vtipoUsuario)";

            query = em.createNativeQuery(comando);

            query.setParameter("vlogin", usuario.getLogin());
            query.setParameter("vsenha", usuario.getSenha());
            query.setParameter("vtipoUsuario", usuario.getTipoUsuario().getSigla());

            query.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Usuario> findAll() {
        String comando = "SELECT * FROM usuario ORDER BY login";

        Query query = em.createNativeQuery(comando, Usuario.class);

        return query.getResultList();
    }

    public Usuario findByLoginAndSenha(String login, String senha) {
        try {
            String comando = """
                SELECT * FROM usuario
                WHERE login = :login
                AND senha = :senha
            """;

            Query query = em.createNativeQuery(comando, Usuario.class);

            query.setParameter("login", login);
            query.setParameter("senha", senha);

            return (Usuario) query.getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }
}