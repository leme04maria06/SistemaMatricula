package com.example.matriculadisciplina.Controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.matriculadisciplina.Model.Aluno;
import com.example.matriculadisciplina.Model.OfertaDisciplina;
import com.example.matriculadisciplina.Model.Pessoa;
import com.example.matriculadisciplina.Model.UF;
import com.example.matriculadisciplina.Repository.AlunoRepository;
import com.example.matriculadisciplina.Repository.CursoRepository;
import com.example.matriculadisciplina.Repository.MatriculaRepository;
import com.example.matriculadisciplina.Repository.OfertaDisciplinaRepository;

import java.util.List;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private OfertaDisciplinaRepository ofertaDisciplinaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("alunos", alunoRepository.findAll());
        return "formListarAluno";
    }

    @GetMapping("/cadastrar")
    public String novo(Model model) {
        model.addAttribute("aluno", new Aluno());
        model.addAttribute("cursos", cursoRepository.findAll());
        model.addAttribute("ufs", UF.values());
        return "formCadAlunoObjetoCurso";
    }

    @PostMapping("/salvar")
    public String salvar(
            @RequestParam("nome") String nome,
            @RequestParam("endereco") String endereco,
            @RequestParam("cidade") String cidade,
            @RequestParam("uf") String uf,
            @RequestParam("email") String email,
            @RequestParam("telefone") String telefone,
            @RequestParam("idade") int idade,

            @RequestParam("prontuario") String prontuario,
            @RequestParam("nomeResponsavel") String nomeResponsavel,
            @RequestParam("contatoResponsavel") String contatoResponsavel,
            @RequestParam("idCurso") int idCurso
    ) {

        // Dados da Pessoa
        Pessoa pessoa = new Pessoa();

        pessoa.setNome(nome);
        pessoa.setEndereco(endereco);
        pessoa.setCidade(cidade);
        pessoa.setUf(uf);
        pessoa.setEmail(email);
        pessoa.setTelefone(telefone);
        pessoa.setIdade(idade);

        // Dados específicos do Aluno
        Aluno aluno = new Aluno();

        aluno.setProntuario(prontuario);
        aluno.setNomeResponsavel(nomeResponsavel);
        aluno.setContatoResponsavel(contatoResponsavel);
        aluno.setIdCurso(idCurso);

        // Salva Pessoa + Aluno
        alunoRepository.insert(aluno, pessoa);

        return "sucessoCadAluno";
    }

    @GetMapping("/AreaAluno")
    public String areaAluno(Model model){
        return "pagAluno";
    }

    @GetMapping("/gerenciar/{id}")
    public String gerenciar(
        @PathVariable int id,
        Model model) {

    Aluno aluno = alunoRepository.findById(id);

    if (aluno == null) {
        return "redirect:/aluno/listar";
    }

    model.addAttribute("aluno", aluno);
    model.addAttribute("cursos", cursoRepository.findAll());
    model.addAttribute("ufs", UF.values());

    return "formGerenciarAluno";
}


        @PostMapping("/atualizar")
        public String atualizar(
                @RequestParam("idAluno") int idAluno,
                @RequestParam("nome") String nome,
                @RequestParam("endereco") String endereco,
                @RequestParam("cidade") String cidade,
                @RequestParam("uf") String uf,
                @RequestParam("email") String email,
                @RequestParam("telefone") String telefone,
                @RequestParam("idade") int idade,
                @RequestParam("prontuario") String prontuario,
                @RequestParam("nomeResponsavel") String nomeResponsavel,
                @RequestParam("contatoResponsavel") String contatoResponsavel,
                @RequestParam("idCurso") int idCurso
        ) {

            alunoRepository.update(
                idAluno,
                nome,
                endereco,
                cidade,
                uf,
                telefone,
                email,
                idade,
                prontuario,
                nomeResponsavel,
                contatoResponsavel,
                idCurso
            );

            return "redirect:/aluno/listar";
        }


        @PostMapping("/excluir")
        public String excluir(@RequestParam("idAluno") int idAluno) {

            alunoRepository.delete(idAluno);

            return "redirect:/aluno/listar";
        }


        @GetMapping("/disciplinas")
        public String disciplinas(HttpSession session, Model model) {
            Aluno aluno = (Aluno) session.getAttribute("aluno");

            if (aluno == null) {
                return "redirect:/usuario/login?tipo=ALUNO";
            }

            int idCurso = aluno.getIdCurso();

            List<OfertaDisciplina> ofertas =
                    ofertaDisciplinaRepository.findByCurso(idCurso);

            model.addAttribute("ofertas", ofertas);

            return "disciplinasAluno";
}

        @GetMapping("/matriculas")
            public String matriculas(
                HttpSession session,
                Model model) {

            Aluno aluno = (Aluno) session.getAttribute("aluno");

            if (aluno == null) {
                return "redirect:/usuario/login?tipo=ALUNO";
            }

            List<Object[]> matriculas =
                    matriculaRepository.findMatriculasByAluno(
                            aluno.getIdAluno()
                    );

            model.addAttribute("matriculas", matriculas);

            return "matriculasAluno";
}
}