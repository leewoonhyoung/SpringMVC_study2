package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController // 문자를 반환하면 String 이 바로 반환이 된다. controller 하면 viewName이 반환되여 viewResolver를 반환하게된다.
public class LogTestController {

 //   private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("log-test")
    public String logTest(){
        String name = "spring";

        log.info("info log = {}", name);
        log.trace("trace={}" , name);
        log.debug("debug={}", name);
        log.warn("warm={}", name);
        log.error("error={}", name);
        return "OK";
    }
}
