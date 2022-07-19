package br.ufjf.dcc193.tomato;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/atividade")
public class AtividadeController {
  @Autowired
  AtividadeRepository atividadeRepo;

  @ResponseBody
  @RequestMapping({ "/", "/index.html" })
  public String index() {
    return "atividade-index";
  }

  @GetMapping("/nova.html")
  public ModelAndView criar() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("atividade-form");
    mv.addObject("atividade", new Atividade("Teste"));
    return mv;
  }

  @PostMapping("/nova.html")
  public ModelAndView criar(@Valid Atividade atividade, BindingResult binding) {
    ModelAndView mv = new ModelAndView();
    if (binding.hasErrors()) {
      mv.setViewName("atividade-form");
      mv.addObject("atividade", atividade);
      return mv;
    }
    atividadeRepo.save(atividade);
    mv.setViewName("redirect:listar.html");
    return mv;
  }

  @GetMapping("/listar.html")
  public ModelAndView listar() {
    List<Atividade> atividades = atividadeRepo.findAll();
    ModelAndView mv = new ModelAndView();
    mv.setViewName("atividade-listar");
    mv.addObject("atividades", atividades);
    return mv;
  }

  @GetMapping("/editar/{id}")
  public ModelAndView editar(@PathVariable Long id) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("atividade-form-edit");
    Atividade atividade = atividadeRepo.findById(id).get();
    mv.addObject("atividade", atividade);
    return mv;
  }

  @PostMapping("/editar/{id}")
  public ModelAndView editar(@PathVariable Long id, @Valid Atividade atividade, BindingResult binding) {
    ModelAndView mv = new ModelAndView();
    if (binding.hasErrors()) {
      mv.setViewName("atividade-form-edit");
      mv.addObject("atividade", atividade);
      return mv;
    }
    atividadeRepo.save(atividade);
    mv.setViewName("redirect:../listar.html");
    return mv;
  }
}
