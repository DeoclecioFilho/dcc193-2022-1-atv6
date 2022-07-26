package br.ufjf.dcc193.tomato.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    @Around("execution(* *..*.*Controller.*(..))")
    private Object antesLog(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("O metodo vai ser chamado: " + jp.getSignature());
        Object retorno;
        try {
            retorno = jp.proceed();
            // System.out.println("O metodo terminou " + jp.getSignature());
            // return retorno;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("O metodo disparou " + jp.getSignature());
            throw e;
        } finally {
            System.out.println("O metodo terminou " + jp.getSignature());
        }
        return retorno;
    }
    // @Before("execution(*
    // br.ufjf.dcc193.ivanylsonhg.tarefeitotomato.HomeController.index())")
    // @Before("execution(* *..*.*Controller.*(..))")
    /*
     * private void antesLog(JoinPoint jp)
     * {
     * System.out.println("O metodo vai ser chamado: "+jp.getSignature());
     * 
     * }
     * //@
     * After("execution(* br.ufjf.dcc193.ivanylsonhg.tarefeitotomato.HomeController.index())"
     * )
     * 
     * @After("execution(* *..*.*Controller.*(..))")
     * private void depoisLog(JoinPoint jp)
     * {
     * System.out.println("O metodo terminou "+jp.getSignature());
     * }
     */
}
