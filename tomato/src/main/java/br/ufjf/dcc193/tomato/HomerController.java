package br.ufjf.dcc193.tomato;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomerController {
    @GetMapping("/layout.html")
    public String layout()
    {
        return "layout-padrao";
    }
    @GetMapping("/erro.html")
    public String erro()
    {
        //throw new Exception("Teste de erro");
        int a =10/0;
        return "layout-padrao";
    }
}
