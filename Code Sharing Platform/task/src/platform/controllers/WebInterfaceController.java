package platform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.entities.Code;
import platform.services.CodeService;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class WebInterfaceController {

    private final CodeService codeService;

    public WebInterfaceController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/code/{id}")
    public String getCodeById(@PathVariable String id, Model model, HttpServletResponse response) {
        response.addHeader("Content-Type","text/html");
        Code code = codeService.findCodeByUUID(id);
        model.addAttribute("codeSnippet", code);
        return "code";
    }

    @GetMapping("/code/new")
    public ModelAndView getSubmitCode(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        return new ModelAndView("submit_code");
    }

    @GetMapping("/code/latest")
    public String getLatestCode(Model model, HttpServletResponse response) {
        response.addHeader("Content-Type","text/html");
        List<Code> codeList = codeService.findTenLatest();
        model.addAttribute("codeList", codeList);
        return "latest";
    }
}
