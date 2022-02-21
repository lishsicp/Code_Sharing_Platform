package platform.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.entities.Code;
import platform.services.CodeService;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@RestController
public class ControllerApi {

    private final CodeService codeService;

    public ControllerApi(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping(value = "/api/code/new")
    public Map<String, ?> postNewCode(HttpServletResponse response, @RequestBody Code code) {
        response.addHeader("Content-Type","application/json");
        codeService.saveCode(code);
        return Map.of(
                "id", code.getId()
        );
    }

    @GetMapping("/api/code/{id}")
    public Map<String, ?> getById(HttpServletResponse response, @PathVariable String id) {
        response.addHeader("Content-Type","application/json");
        Code code = codeService.findCodeByUUID(id);
        return Map.of(
                "code", code.getCode(),
                "date", code.getDate(),
                "time", code.getTime(),
                "views", code.getViews()
        );
    }

    @GetMapping("api/code/latest")
    public ResponseEntity<List<Code>> getLatest(HttpServletResponse response) {
        response.addHeader("Content-Type","application/json");
        return new ResponseEntity<>(codeService.findTenLatest(), HttpStatus.OK);
    }




}
