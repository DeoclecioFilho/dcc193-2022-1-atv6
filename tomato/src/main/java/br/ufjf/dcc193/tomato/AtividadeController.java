package br.ufjf.dcc193.tomato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AtividadeController {
    @Autowired
    AtividadeRepository atividadeRepo;
    
    @RequestMapping({"/atividade","/atividade/index.html"})
  //  @ResponseBody
    public String index(){
        return "atividade-index";
    }
}
