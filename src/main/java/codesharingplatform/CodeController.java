package codesharingplatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class CodeController {
    @Autowired
    CodeService codeService;

    @PostMapping("/api/code/new")
    public ResponseEntity<Object> newCode(@RequestBody Map<String, Object> snippet) {
        Code newCode = new Code(String.valueOf(snippet.get("code")),
                Long.parseLong(String.valueOf(snippet.get("time"))),
                Long.parseLong(String.valueOf(snippet.get("views")))
        );

        this.codeService.save(newCode);
        return new ResponseEntity<>(
                Map.of("id", String.valueOf(newCode.getId())),
                HttpStatus.OK
        );
    }

    @GetMapping("/api/code/{id}")
    public ResponseEntity<Object> getCode(@PathVariable String id) {
        Code code = this.codeService.findById(id);
        return new ResponseEntity<>(
                code,
                HttpStatus.OK
        );
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<Object> latestCode() {
        List<Code> listCode = this.codeService.findLatest10();
        return new ResponseEntity<>(
                listCode,
                HttpStatus.OK
        );
    }

    @GetMapping("/code/{id}")
    public String getWebCode(Model model, @PathVariable String id) {
//        try {
//            UUID uuid = UUID.fromString(id);
//        } catch (Error error) {
//            System.out.println("hello");
//        }
        System.out.println(id);
//        Code code = this.codeService.findById(UUID.randomUUID());
        Code code = this.codeService.findById(id);
        model.addAttribute("snippet",
                Map.of("code", code.getCode(),
                        "date", code.getDate(),
                        "time_restriction", code.getTime(),
                        "views_restriction", code.getViews(),
                        "time_expirable", code.isTimeExpirable(),
                        "views_expirable", code.isViewsExpirable()
                ));
//        model.addAttribute("snippet", code);
        return "code";
    }

    @GetMapping("/code/new")
    public String newWebCode() {
        return "new";
    }

    @GetMapping("/code/latest")
    public String latestWebCode(Model model) {
        System.out.println("/code/latest");
        List<Code> listCode = this.codeService.findLatest10();
        List<Map<String, Object>> listStringString = new ArrayList<>();
        for (Code code: listCode) {
            listStringString.add(Map.of(
                    "code", code.getCode(),
                    "date", code.getDate(),
                    "time_restriction", code.getTime(),
                    "views_restriction", code.getViews(),
                    "time_expirable", code.isTimeExpirable(),
                    "views_expirable", code.isViewsExpirable()
            ));
        }
        model.addAttribute("snippets", listStringString);
        return "latest";
    }
}