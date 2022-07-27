package br.ufjf.dcc193.tomato;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Component
@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler
    public ModelAndView trataDivisaoPorZero(NullPointerException e) {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("status", 500);
        mv.addObject("error", "Deu ruim");
        mv.addObject("message", "Não chame operações em null");
        return mv;
    }
}
