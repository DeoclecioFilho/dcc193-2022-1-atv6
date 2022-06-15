package br.ufjf.dcc193.tomato;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "<h1> Olá </h1>";
    }
}
