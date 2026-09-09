package com.example.matriculadisciplina.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.matriculadisciplina.Model.Disciplina;
import com.example.matriculadisciplina.Model.OfertaDisciplina;
import com.example.matriculadisciplina.Repository.CursoRepository;
import com.example.matriculadisciplina.Repository.DisciplinaRepository;
import com.example.matriculadisciplina.Repository.OfertaDisciplinaRepository;
import com.example.matriculadisciplina.Repository.ProfessorRepository;

@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private OfertaDisciplinaRepository ofertaDisciplinaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

        @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        return "formListarDisciplina";
    }

    @GetMapping("/cadastrar")
    public String novo(Model model) {

    model.addAttribute(
        "disciplina",
        new Disciplina()
    );

    model.addAttribute(
        "cursos",
        cursoRepository.findAll()
    );

    model.addAttribute(
        "professores",
        professorRepository.findAll()
    );

    return "formCadDisciplina";
}
    // Salva (cria ou atualiza) um aluno -> POST /alunos/salvar
    @PostMapping("/salvar")
    public String salvar(

        @RequestParam("nome")
        String nome,

        @RequestParam("carga_horaria")
        String cargaHoraria,

        @RequestParam("area")
        String area,

        @RequestParam("descricao")
        String descricao,

        @RequestParam("idCurso")
        int idCurso,

        @RequestParam("id_professor")
        int idProfessor,

        @RequestParam("ano")
        int ano,

        @RequestParam("semestre")
        int semestre
) {

    // 1. CRIA A DISCIPLINA


    Disciplina disciplina = new Disciplina();

    disciplina.setNome(nome);
    disciplina.setCargaHoraria(cargaHoraria);
    disciplina.setArea(area);
    disciplina.setDescricao(descricao);
    disciplina.setIdCurso(idCurso);

    // Insere e recebe o ID gerado
    int idDisciplina =
            disciplinaRepository.insert(disciplina);


    // Se não conseguiu cadastrar
    if (idDisciplina == 0) {
        return "erroCadDisciplina";
    }

    // 2. CRIA A OFERTA

    OfertaDisciplina oferta =
            new OfertaDisciplina();

    oferta.setIdDisciplina(idDisciplina);
    oferta.setIdProfessor(idProfessor);
    oferta.setAno(ano);
    oferta.setSemestre(semestre);


    // Insere a oferta
    boolean ofertaSalva =
            ofertaDisciplinaRepository.insert(oferta);


    if (!ofertaSalva) {
        return "erroCadDisciplina";
    }

    // 3. SUCESSO

    return "sucessoCadDisciplina";
}
    @GetMapping("/gerenciar/{id}")
        public String gerenciar(
                @PathVariable int id,
                Model model) {

            Disciplina disciplina = disciplinaRepository.findById(id);

            if (disciplina == null) {
                return "redirect:/disciplina/listar";
            }

            model.addAttribute("disciplina", disciplina);

            return "formGerenciarDisciplina";
            }

    @PostMapping("/atualizar")
        public String atualizar(
                @RequestParam int idDisciplina,
                @RequestParam String nome,
                @RequestParam String carga_horaria,
                @RequestParam String area,
                @RequestParam String descricao) {

            disciplinaRepository.update(
                idDisciplina,
                nome,
                carga_horaria,
                area,
                descricao
            );

            return "redirect:/disciplina/listar";
        }

    @PostMapping("/excluir")
        public String excluir(@RequestParam int idDisciplina) {

            disciplinaRepository.delete(idDisciplina);

            return "redirect:/disciplina/listar";
        }
    
}
