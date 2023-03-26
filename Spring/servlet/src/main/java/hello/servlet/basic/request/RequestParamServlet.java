package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 * <p>
 * 2. 동일한 파라미터 전송 가능
 * http://localhost:8080/request-param?username=hello&username=kim&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    /*
        String username = request.getParameter("username"); //단일 파라미터 조회
        Enumeration<String> parameterNames = request.getParameterNames(); //파라미터 이름들 모두 조회
        Map<String, String[]> parameterMap = request.getParameterMap(); //파라미터를 Map 으로 조회
        String[] usernames = request.getParameterValues("username"); //복수 파라미터 조회
     */

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");

//        Enumeration<String> parameterNames = request.getParameterNames();
//        while (parameterNames.hasMoreElements()) {
//            String paramName = parameterNames.nextElement();
//            System.out.println(paramName + "=" +
//                    request.getParameter(paramName));
//        }

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println("paramName = " + paramName));
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        System.out.println("request.getParameter(username) = " + username);

        String age = request.getParameter("age");
        System.out.println("request.getParameter(age) = " + age);
        System.out.println();
        /**
         * 하나의 이름에 복수의 파라미터가 할당된 경우
         * 단일 파라미터 조회(request.getParameter)시 첫번째 파라미터를 반환한다.
         */

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        System.out.println("request.getParameterValues(username)");
        String[] usernames = request.getParameterValues("username");
        for (String user : usernames) {
            System.out.println("username = " + user);
        }

        response.getWriter().write("ok");
    }
}
