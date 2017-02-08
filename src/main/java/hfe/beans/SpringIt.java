package hfe.beans;

import hfe.injections.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
@RequestMapping("/nop")
public class SpringIt {
    @Inject
    private Value value;

    @GetMapping("/hello")
    @ResponseBody
    public String muffe() {
        return "SampleController " + value.name();
    }


    @GetMapping("/moli/{id}")
    @ResponseBody
    public String nock(@PathVariable("id") String id) {
        return "ID: " + id;
    }

    @GetMapping("/find")
    @ResponseBody
    public String findOwner(Model model) {
        return "find";
    }
}
