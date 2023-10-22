package hello.thymeleaf.basic;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping(value = "/text-basic")
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
    @GetMapping(value = "/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped.html";
    }

    @GetMapping(value = "/variable")
    public String variable(Model model) {

        User userA = new User("userA", 15);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("list", list);
        model.addAttribute("map", map);

        return "basic/variable";
    }

    /**
     * test url : http://localhost:8080/basic/basic-objects?paramData=HelloParam
     */
    @GetMapping(value = "/basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    /**
     * 스프링부트 3.0 이상 버전이라면 다음과 같이 사용한다.
     * - 기본객체(request, response, session, servletContext)를 지원하지 않음.
     */
    @GetMapping("/basic-objects/V3")
    public String basicObjectsV3(Model model,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 HttpSession session) {
        session.setAttribute("sessionData", "Hello Session!");
        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());
        return "basic/basic-objects";
    }

    @GetMapping(value = "/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping(value = "/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    /**
     * 타임리프에서 문자 리터럴은 항상 '(작은 따옴표)로 감싸야 한다.
     * 하지만, 아래의 룰에 따라 공백없이 쭉 이어지는 문자에 대해서는 하나의 의미있는 토큰으로 인지해서
     * 작은 따옴표를 생략할 수 있다.
     * rule: A-Z, a-z, 0-9, [], ., -, _
     *
     * 위의 룰이 아닌 케이스(공백이 포함된 문장 등)에는 작은 따옴표를 생략하면 예외발생한다.
     * 따라서 리터럴 대체 문법을 사용하자.
     * <span th:text="|hello ${data}|">
     */
    @GetMapping(value = "literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    @GetMapping(value = "/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    @GetMapping("/attribute")
    public String attribute() {
        return "basic/attribute";
    }

    @GetMapping("/each")
    public String each(Model model) {
        addUser(model);
        return "basic/each";
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello" + data;
        }
    }

    @Data
    static class User {

        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }

    private void addUser(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));

        model.addAttribute("users", list);
    }


}
