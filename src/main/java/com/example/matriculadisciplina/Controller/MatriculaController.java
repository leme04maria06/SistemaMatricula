package com.example.matriculadisciplina.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.matriculadisciplina.Model.Aluno;
import com.example.matriculadisciplina.Model.OfertaDisciplina;
import com.example.matriculadisciplina.Repository.MatriculaRepository;
import com.example.matriculadisciplina.Repository.OfertaDisciplinaRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private OfertaDisciplinaRepository ofertaDisciplinaRepository;

    @PostMapping("/matricula")
    public String matricular(
            @RequestParam("idOferta") int idOferta,
            HttpSession session) {

        // Recupera o aluno que fez login
        Aluno aluno = (Aluno) session.getAttribute("aluno");

        if (aluno == null) {
            return "redirect:/usuario/login?tipo=ALUNO";
        }

        // Procura a oferta escolhida
        OfertaDisciplina oferta =
                ofertaDisciplinaRepository.findById(idOferta);

        if (oferta == null) {
            return "redirect:/aluno/disciplinas";
        }

        // Verifica se o aluno já está matriculado
        if (matriculaRepository.existeMatricula(aluno, oferta)) {
            return "redirect:/aluno/disciplinas";
        }

        // Faz a matrícula
       boolean sucesso = matriculaRepository.inserir(aluno, oferta);

        if (sucesso) {
            return "redirect:/aluno/disciplinas?sucesso=true";
        }

        return "redirect:/aluno/disciplinas?erro=true";
    }
} 
