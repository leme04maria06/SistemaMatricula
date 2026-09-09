package com.example.matriculadisciplina.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.matriculadisciplina.Model.Professor;
import com.example.matriculadisciplina.Model.TipoUsuario;
import com.example.matriculadisciplina.Model.Usuario;
import com.example.matriculadisciplina.Repository.ProfessorRepository;
import com.example.matriculadisciplina.Repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProfessorRepository professorRepository;


    @GetMapping("/login")
    public String login(
            @RequestParam TipoUsuario tipo,
            Model model) {

        model.addAttribute("tipoUsuario", tipo);

        if (tipo == TipoUsuario.PROFESSOR) {
            model.addAttribute("cor", "primary");

        } else if (tipo == TipoUsuario.ALUNO) {
            model.addAttribute("cor", "success");

        } else {
            model.addAttribute("cor", "danger");
        }

        return "login";
    }

    @PostMapping("/validar")
    public String validarLogin(
            @RequestParam String login,
            @RequestParam String senha,
            @RequestParam TipoUsuario tipo,
            Model model,
            HttpSession session) {

        Usuario usuario =
                usuarioRepository.findByLoginAndSenha(login, senha);

        if (usuario == null) {

            model.addAttribute("tipoUsuario", tipo);
            model.addAttribute("erro",
                    "Login ou senha inválidos.");

            return "login";
        }


        if (usuario.getTipoUsuario() != tipo) {

            model.addAttribute("tipoUsuario", tipo);
            model.addAttribute("erro",
                    "Este usuário não possui acesso como "
                    + tipo.getNome() + ".");

            return "login";
        }


        // Guarda o usuário logado
        session.setAttribute("usuario", usuario);


        // ALUNO

        if (tipo == TipoUsuario.ALUNO) {

            session.setAttribute(
                    "aluno",
                    usuario.getPessoa()
            );

            return "redirect:/aluno/AreaAluno";
        }

        // PROFESSOR

        if (tipo == TipoUsuario.PROFESSOR) {

            Professor professor =
                    professorRepository.findById(
                            usuario.getPessoa().getId_pessoa()
                    );

            if (professor == null) {

                model.addAttribute("tipoUsuario", tipo);
                model.addAttribute("erro",
                        "Professor não encontrado.");

                return "login";
            }

            session.setAttribute(
                    "professor",
                    professor
            );

            return "redirect:/professor/AreaProfessor";
        }

        // ADMINISTRADOR

        if (tipo == TipoUsuario.ADMINISTRADOR) {

            return "redirect:/AreaAdmin";
        }


        return "login";
    }
}