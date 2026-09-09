package com.example.matriculadisciplina.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.matriculadisciplina.Model.Curso;
import com.example.matriculadisciplina.Repository.CursoRepository;

@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping("/listar")
    public String listar(Model model) {

        model.addAttribute("cursos", cursoRepository.findAll());

        return "formListarCursos";
    }


    @GetMapping("/cadastrar")
    public String novo(Model model) {

        Curso curso = new Curso();

        curso.setAno_inicio(java.time.Year.now().getValue());

        model.addAttribute("curso", curso);

        return "formCadCurso";
    }


    @PostMapping("/salvar")
    public String salvar(
            @RequestParam("nome") String nome,
            @RequestParam("ano_inicio") int ano_inicio
    ) {

        Curso curso = new Curso();

        curso.setNome(nome);
        curso.setAno_inicio(ano_inicio);

        cursoRepository.insert(curso);

        return "sucessoCadCurso";
    }


    // ABRE A TELA DE GERENCIAMENTO
    @GetMapping("/gerenciar/{id}")
    public String gerenciar(
            @PathVariable int id,
            Model model
    ) {

        Curso curso = cursoRepository.findById(id);

        if (curso == null) {

            return "redirect:/curso/listar";
        }

        model.addAttribute("curso", curso);

        return "formGerenciarCurso";
    }


    // ATUALIZA O CURSO
    @PostMapping("/atualizar")
    public String atualizar(
            @RequestParam("idCurso") int idCurso,
            @RequestParam("nome") String nome,
            @RequestParam("ano_inicio") int ano_inicio
    ) {

        cursoRepository.update(
                idCurso,
                nome,
                ano_inicio
        );

        return "redirect:/curso/listar";
    }


    // EXCLUI O CURSO
    @PostMapping("/excluir")
    public String excluir(
            @RequestParam("idCurso") int idCurso
    ) {

        cursoRepository.delete(idCurso);

        return "redirect:/curso/listar";
    }
}