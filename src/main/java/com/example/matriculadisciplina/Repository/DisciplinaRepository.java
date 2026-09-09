package com.example.matriculadisciplina.Repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.matriculadisciplina.Model.Disciplina;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
@Repository

public class DisciplinaRepository {
    @PersistenceContext 
    private EntityManager em;

    @Transactional
public int insert(Disciplina disciplina) {

    try {

        String comando = """
            INSERT INTO disciplina
            (nome, carga_horaria, area, descricao, id_curso)
            VALUES
            (:vnome, :vcarga_horaria, :varea, :vdescricao, :vid_curso)
            """;

        Query query = em.createNativeQuery(comando);

        query.setParameter("vnome", disciplina.getNome());
        query.setParameter("vcarga_horaria", disciplina.getCargaHoraria());
        query.setParameter("varea", disciplina.getArea());
        query.setParameter("vdescricao", disciplina.getDescricao());
        query.setParameter("vid_curso", disciplina.getIdCurso());

        query.executeUpdate();

        // Pega o ID da disciplina que acabou de ser cadastrada
        Query queryId = em.createNativeQuery(
            "SELECT LAST_INSERT_ID()"
        );

        Number resultado = (Number) queryId.getSingleResult();

        return resultado.intValue();

    } catch (Exception e) {

        e.printStackTrace();

        return 0;
    }
}

    public List<Disciplina> findAll() {
        String comando = "SELECT * FROM disciplina ORDER BY nome";
        Query query = em.createNativeQuery(comando, Disciplina.class);
        return query.getResultList();
    }

    public Disciplina findById(int id) {

    try {

        String comando = """
            SELECT *
            FROM disciplina
            WHERE id_disciplina = :id
        """;

        Query query = em.createNativeQuery(comando, Disciplina.class);

        query.setParameter("id", id);

        return (Disciplina) query.getSingleResult();

    } catch (Exception e) {

        return null;

    }
    }

        @Transactional
        public void update(
                int idDisciplina,
                String nome,
                String cargaHoraria,
                String area,
                String descricao) {

            String comando = """
                UPDATE disciplina
                SET nome = :nome,
                    carga_horaria = :carga_horaria,
                    area = :area,
                    descricao = :descricao
                WHERE id_disciplina = :id
            """;

            Query query = em.createNativeQuery(comando);

            query.setParameter("id", idDisciplina);
            query.setParameter("nome", nome);
            query.setParameter("carga_horaria", cargaHoraria);
            query.setParameter("area", area);
            query.setParameter("descricao", descricao);

            query.executeUpdate();
    }

    @Transactional
public void delete(int idDisciplina) {

    String comando = """
        DELETE FROM disciplina
        WHERE id_disciplina = :id
    """;

    Query query = em.createNativeQuery(comando);

    query.setParameter("id", idDisciplina);

    query.executeUpdate();
}

}
