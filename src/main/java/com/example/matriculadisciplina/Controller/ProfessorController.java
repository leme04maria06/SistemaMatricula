package com.example.matriculadisciplina.Controller;

import org.springframework.ui.Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.matriculadisciplina.Model.Pessoa;
import com.example.matriculadisciplina.Model.Professor;
import com.example.matriculadisciplina.Model.UF;
import com.example.matriculadisciplina.Repository.ProfessorRepository;
import com.example.matriculadisciplina.Repository.MatriculaRepository;



import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    // Exibe o formulário de cadastro -> GET /aluno/cadastrar
    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("professor", new Professor());
         model.addAttribute("professores", professorRepository.findAll());
        return "formListarProfessor"; // sem ".html" - o Thymeleaf resolve isso sozinho
    }
    // Exibe o formulário de cadastro -> GET /aluno/cadastrar
    @GetMapping("/cadastrar")
    public String novo(Model model) {
        model.addAttribute("professor", new Professor());
        model.addAttribute("ufs", UF.values());
        return "formCadProfessor"; // sem ".html" - o Thymeleaf resolve isso sozinho
    }
    // Salva (cria ou atualiza) um aluno -> POST /alunos/salvar
    @PostMapping("/salvar")
    public String salvar(
            //@RequestParam("idAluno") int idAluno,
            @RequestParam("nome") String nome,
            @RequestParam("endereco") String endereco,
            @RequestParam("cidade") String cidade,
            @RequestParam("uf") String uf,
            @RequestParam("email") String email,
            @RequestParam("telefone") String telefone,
            @RequestParam("idade") int idade,
            @RequestParam("siape") String siape,
            @RequestParam("area") String area,
            @RequestParam("formacao") String formacao,
            @RequestParam("senha") String senha
    ) {
        Pessoa pessoa = new Pessoa();
        Professor professor = new Professor();
        pessoa.setNome(nome);
        pessoa.setEndereco(endereco);
        pessoa.setCidade(cidade);
        pessoa.setUf(uf);
        pessoa.setEmail(email);
        pessoa.setTelefone(telefone);
        pessoa.setIdade(idade);
        professor.setSiape(siape);
        professor.setArea(area);
        professor.setFormacao(formacao);
        professorRepository.insert(professor, pessoa);
        return "sucessoCadProfessor";
    }

    @GetMapping("/AreaProfessor")
    public String areaProfessor(Model model){
        return ("pagProf");
    }

     @GetMapping("/gerenciar/{id}")
    public String gerenciar(
            @PathVariable int id,
            Model model) {

        Professor professor = professorRepository.findById(id);

        if (professor == null) {
            return "redirect:/professor/listar";
        }

        model.addAttribute("professor", professor);

        model.addAttribute("ufs", UF.values());

        return "formGerenciarProfessor";
    }


    // ATUALIZAR
    @PostMapping("/atualizar")
    public String atualizar(
            @RequestParam("idProfessor") int idProfessor,
            @RequestParam("nome") String nome,
            @RequestParam("endereco") String endereco,
            @RequestParam("cidade") String cidade,
            @RequestParam("uf") String uf,
            @RequestParam("email") String email,
            @RequestParam("telefone") String telefone,
            @RequestParam("idade") int idade,
            @RequestParam("siape") String siape,
            @RequestParam("area") String area,
            @RequestParam("formacao") String formacao
    ) {

        professorRepository.update(
                idProfessor,
                nome,
                endereco,
                cidade,
                uf,
                telefone,
                email,
                idade,
                siape,
                area,
                formacao
        );

        return "redirect:/professor/listar";
    }


    // EXCLUIR
    @PostMapping("/excluir")
    public String excluir(
            @RequestParam("idProfessor") int idProfessor) {

        professorRepository.delete(idProfessor);

        return "redirect:/professor/listar";
    }

       @GetMapping("/matriculas")
    public String matriculas(
        HttpSession session,
        Model model) {

    Professor professor =
            (Professor) session.getAttribute("professor");

    if (professor == null) {
        return "redirect:/usuario/login?tipo=PROFESSOR";
    }

    System.out.println("PROFESSOR LOGADO: " + professor.getId_pessoa());

    List<Object[]> matriculas =
            matriculaRepository.findMatriculasByProfessor(
                    professor.getId_pessoa()
            );

    System.out.println("MATRÍCULAS ENCONTRADAS: " + matriculas.size());

    model.addAttribute("matriculas", matriculas);

    return "matriculasProfessor";
}

}