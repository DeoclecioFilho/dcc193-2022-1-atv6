package br.ufjf.dcc193.tomato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import javax.validation.Valid;



@Controller
@RequestMapping("/atividade")
public class AtividadeController {
  @Autowired
  AtividadeRepository atividadeRepo;

  @RequestMapping({ "/", "/index.html" })
  // @ResponseBody
  public String index() {
    return "atividade-index";
  }
/*
  @RequestMapping(value = "/nova.html", method = RequestMethod.GET)
  public ModelAndView criar() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("atividade-form");
    mv.addObject("atividade", new Atividade(null, null, null));
    return mv;
  }

  @RequestMapping(value = "/nova.html", method = RequestMethod.POST)
  public ModelAndView criar(Atividade atividade) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:listar.html");
    atividadeRepo.save(atividade);
    mv.addObject("atividade", new Atividade(null, null, null));
    return mv;
  }
*/
 
 @GetMapping("/nova.html")
 public ModelAndView criar() {
     ModelAndView mv = new ModelAndView();
     mv.setViewName("atividade-form");
     mv.addObject("atividade", new Atividade());
     return mv;
    }
    
    
    @PostMapping("/nova.html")
    public ModelAndView criar(Atividade atividade) {
      ModelAndView mv = new ModelAndView();
      mv.setViewName("redirect:listar.html");
      atividadeRepo.save(atividade);
      mv.addObject("atividade", atividade);
      return mv;
    }
    
    
   
  @GetMapping("/listar.html")
  public ModelAndView listar() {
    List<Atividade> atividades = atividadeRepo.findAll();
    ModelAndView mv = new ModelAndView();
    mv.setViewName("atividade-listar");
    mv.addObject("atividade",atividades);
    return mv;
  }

  @GetMapping("/editar/{id}")
  public ModelAndView editar(@PathVariable Long id) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("atividade-form-edit");
    Atividade atividade = atividadeRepo.findById(id).get();
    mv.addObject("atividade",atividade);
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
