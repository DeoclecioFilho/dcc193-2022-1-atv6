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
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioRepository repo;

    @GetMapping("/list.html")
    @ResponseBody
    public String indexOld() {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1> Bem vindo!!</h1>");
        Usuario t = new Usuario(null, null, null);
        t.setNome("Criando em: " + new Date());
        repo.save(t);
        List<Usuario> tc = repo.findAll();
        for (Usuario usuario : tc) {
            sb.append("<li>" + usuario + "</li>");
        }
        return sb.toString();
    }

    @GetMapping({ "", "/index.html" })
    // @ResponseBody
    public ModelAndView index() {
        // return "Usuario controller";
        ModelAndView mv = new ModelAndView("usuario-index");
        // mv.addObject("mensagem", "Olá mundo");
        // return "Usuario-index";
        return mv;
    }

    // @RequestMapping(path = "/Usuario/nova.html", method = RequestMethod.GET)
    @GetMapping(path = "/nova.html")
    public ModelAndView novaGET() {
        ModelAndView mv = new ModelAndView("usuario-new");
        Usuario t = new Usuario();
        mv.addObject("usuario", t);
        return mv;
    }

    // @RequestMapping(path = "/Usuario/nova.html", method = RequestMethod.POST)
    /*
     * @PostMapping(path ="/nova.html")
     * public ModelAndView novaPost(Usuario t) {
     * ModelAndView mv = new ModelAndView();
     * repo.save(t);
     * mv.addObject("Usuario", t);
     * mv.setViewName("redirect:listar.html");
     * return mv;
     * }
     */
    @PostMapping(path = "nova.html")
    public ModelAndView novaPost(@Valid Usuario t, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("usuario-new");
            mv.addObject("usuario", t);
            return mv;
        }
        repo.save(t);
        mv.addObject("usuario", t);
        mv.setViewName("redirect:listar.html");
        return mv;
    }

    @GetMapping(path = "/listar.html")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("usuario-list");
        List<Usuario> tl = repo.findAll();
        mv.addObject("usuarios", tl);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarGET(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();

        Optional<Usuario> Usuarioop = repo.findById(id);
        if (Usuarioop.isPresent()) {
            Usuario t = Usuarioop.get();
            mv.setViewName("usuario-edit");
            mv.addObject("usuario", t);
            return mv;
        }

        mv.setViewName("redirect:../listar.html");
        return mv;
    }

    @PostMapping("/editar/{id}")
    public ModelAndView editarPOST(@Valid Usuario t, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("usuario-edit.html");
            mv.addObject("usuario", t);
            return mv;
        }
        repo.save(t);
        mv.setViewName("redirect:../listar.html");
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:../listar.html");

        Optional<Usuario> Usuarioop = repo.findById(id);
        if (Usuarioop.isPresent()) {
            Usuario t = Usuarioop.get();
            repo.delete(t);
            return mv;
        }
        return mv;
    }
}