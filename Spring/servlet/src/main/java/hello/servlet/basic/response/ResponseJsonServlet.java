package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Content-Type: application/json
        response.setHeader("content-type", "application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("gwak");
        helloData.setAge(29);

        // {"username": "gwak", "age": 29}
        // 객체를 JSON 문자로 변경(Jackson 라이브러리)
        String s = objectMapper.writeValueAsString(helloData);

//        response.getWriter().write(s);
        // application/json은 스펙상 utf-8 형식을 사용하도록 정의되어 있다.
        // application/json;charset=utf-8 로 전달하는것은 의미 없는 파라미터를 추가한 것이 된다.
        // getWriter()를 사용하면 charset=utf-8을 자동으로 추가한다.
        // getOutputStream()으로 출력하면 그런 문제를 해소할 수 있다.
        response.getOutputStream().println(s);
    }

}
