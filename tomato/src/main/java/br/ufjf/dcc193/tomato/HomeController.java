package br.ufjf.dcc193.tomato;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/tarefa")
public class HomeController {
    @Autowired
    TarefaRepository repo;

    @GetMapping("/list.html")
    @ResponseBody
    public String indexOld() {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1> Bem vindo!!</h1>");
        Tarefa t = new Tarefa();
        t.setTitulo("Criando em: " + new Date());
        repo.save(t);
        List<Tarefa> tc = repo.findAll();
        for (Tarefa tarefa : tc) {
            sb.append("<li>" + tarefa + "</li>");
        }
        return sb.toString();
    }

    @GetMapping({ "", "/index.html" })
    // @ResponseBody
    public ModelAndView index() {
        // return "Tarefa controller";
        ModelAndView mv = new ModelAndView("tarefa-index");
        // mv.addObject("mensagem", "Olá mundo");
        // return "tarefa-index";
        return mv;
    }

    // @RequestMapping(path = "/tarefa/nova.html", method = RequestMethod.GET)
    @GetMapping(path = "/nova.html")
    public ModelAndView novaGET() {
        ModelAndView mv = new ModelAndView("tarefa-new");
        Tarefa t = new Tarefa("Criando em: " + new Date());
        mv.addObject("tarefa", t);
        return mv;
    }

    // @RequestMapping(path = "/tarefa/nova.html", method = RequestMethod.POST)
    /*
     * @PostMapping(path ="/nova.html")
     * public ModelAndView novaPost(Tarefa t) {
     * ModelAndView mv = new ModelAndView();
     * repo.save(t);
     * mv.addObject("tarefa", t);
     * mv.setViewName("redirect:listar.html");
     * return mv;
     * }
     */
    @PostMapping(path = "nova.html")
    public ModelAndView novaPost(@Valid Tarefa t, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("tarefa-new.html");
            mv.addObject("tarefa", t);
            return mv;
        }
        repo.save(t);
        mv.addObject("tarefa", t);
        mv.setViewName("redirect:listar.html");
        return mv;
    }

    @GetMapping(path = "/listar.html")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("tarefa-list");
        List<Tarefa> tl = repo.findAll();
        mv.addObject("tarefas", tl);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarGET(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();

        Optional<Tarefa> tarefaop = repo.findById(id);
        if (tarefaop.isPresent()) {
            Tarefa t = tarefaop.get();
            mv.setViewName("tarefa-edit");
            mv.addObject("tarefa", t);
            return mv;
        }

        mv.setViewName("redirect:../listar.html");
        return mv;
    }

    @PostMapping("/editar/{id}")
    public ModelAndView editarPOST(@Valid Tarefa t, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("tarefa-edit.html");
            mv.addObject("tarefa", t);
            return mv;
        }
        repo.save(t);
        mv.setViewName("redirect:../listar.html");
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:../listar.html");

        Optional<Tarefa> tarefaop = repo.findById(id);
        if (tarefaop.isPresent()) {
            Tarefa t = tarefaop.get();
            repo.delete(t);
            return mv;
        }
        return mv;
    }
}
