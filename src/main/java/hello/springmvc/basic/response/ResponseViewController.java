package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1") // response-view-1 url 이 실행이 되면
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello") // response/hello 경로가 실행이 되면서
                .addObject("data", "hello!"); // "data"에 "hello"라는 값을 넣어라.

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "hello");
        return "response/hello";

    }
    //절대 권장하지 않는다.
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!!");
    }
}
