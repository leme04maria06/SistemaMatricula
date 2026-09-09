package com.example.matriculadisciplina.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.matriculadisciplina.Model.OfertaDisciplina;
import com.example.matriculadisciplina.Repository.DisciplinaRepository;
import com.example.matriculadisciplina.Repository.OfertaDisciplinaRepository;
import com.example.matriculadisciplina.Repository.ProfessorRepository;

@Controller
@RequestMapping("/ofertadisciplina")
public class OfertaDisciplinaController {
    @Autowired
    private OfertaDisciplinaRepository oferta_disciplinaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ProfessorRepository professorRepository;
    
    @GetMapping("cadastrar")
    public String novo(Model model) {
        model.addAttribute("oferta_disciplina", new OfertaDisciplina());
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        
        return "formCadOfertaDisciplina"; // sem ".html" - o Thymeleaf resolve isso sozinho
    }
    // Salva (cria ou atualiza) um aluno -> POST /alunos/salvar
    @PostMapping("/salvar")
    public String salvar(
        @RequestParam("id_disciplina") int id_disciplina,
        @RequestParam("id_professor") int id_professor,
        @RequestParam("ano") int ano,
        @RequestParam("semestre") int semestre
    ) {

    OfertaDisciplina oferta_disciplina = new OfertaDisciplina();

    oferta_disciplina.setIdDisciplina(id_disciplina);
    oferta_disciplina.setIdProfessor(id_professor);
    oferta_disciplina.setAno(ano);
    oferta_disciplina.setSemestre(semestre);

    oferta_disciplinaRepository.insert(oferta_disciplina);

    return "sucessoCadOfertaDisciplina";
    }
    
}
