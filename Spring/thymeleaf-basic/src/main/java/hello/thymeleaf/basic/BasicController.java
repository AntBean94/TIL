package hello.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping(value = "text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello Spring");
        return "basic/text-basic";
    }

    /**
     * 타임리프가 제공하는 th:text, [[...]] 표현식은 기본적으로
     * 이스케이프(escape)를 제공한다.
     * 따라서, 이스케이프를 적용하지 않으려면 별도의 표현식을 사용해야한다.
     * => th:utext, [(...)]
     */
    @GetMapping(value = "text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped.html";
    }


}
