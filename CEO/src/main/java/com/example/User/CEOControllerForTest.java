package com.example.CEO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CEOControllerForTest {

    private final TestwithEmployee testwithEmployee;

    @GetMapping("/test")
    public String fortest()
    {
        log.info("ceo서비스까지 닿았음");
        return "테스트용 CEO 서비스";
    }

    @GetMapping("/feign")
    public String feigntest()
    {
        return testwithEmployee.feigntestemployeeceo();
    }

    @GetMapping("/ceofeign")
    public String requesttoCEO()
    {
        return "ceo에서 finance에게 전달";
    }

}
