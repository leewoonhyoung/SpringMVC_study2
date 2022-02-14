package hello.springmvc.basic.request;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param/v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age={}" ,username,age);

        response.getWriter().write("OK");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String username,
            @RequestParam("age") int age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam("username") String username,
            @RequestParam("age") int age){

        log.info("username = {}, age={}",username,age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){
        log.info("username = {}, age={}",username,age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, // required =true 하면 필수로 username을 가져와야 한다. 없으면 400 오류 발생
            @RequestParam(required = false) Integer age){
        log.info("username = {}, age={}",username,age);
        return "OK";
        // 기본형 int 에서는 null 이 사용 금지 ,, 객체형인 Integer 에는 null이 사용 가능하다. 500 오류 발생
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username, // required =true 하면 필수로 username을 가져와야 한다. 없으면 400 오류 발생
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username = {}, age={}", username, age);
        return "OK";

    }
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, String> paramMap) {
        log.info("username={}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "OK";

    }
    @ResponseBody
    @RequestMapping("/model-attribute-v1-beta")
    public String modelAttributeV1_beta(
            @RequestParam String username,
            @RequestParam int age
    ){
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);
        log.info("username={}, age = {}", helloData.getUsername(), helloData.getAge());

        return helloData.getUsername()+"님 안녕하세요";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(
            @ModelAttribute HelloData helloData){ // helloProperties 를 찾는다 properties 는 getter , setter와 같은 애들

        log.info("username = {} ,age = {}", helloData.getUsername() ,helloData.getAge());
        return helloData.getUsername() +"님 안녕하세요";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username = {} ,age = {}", helloData.getUsername() ,helloData.getAge());
        return helloData.getUsername() +"님 안녕하세요";
    }
    //단순한 Type 이면 @RequestParam 을 사용하고 그 외는 @ModelAttribute 를 사용한다.(예외는 argument resolver 를 지정해둔 타입제외)
}
