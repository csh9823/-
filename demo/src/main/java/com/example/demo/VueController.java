package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VueController {
	
    @GetMapping("/testvue") // GET 요청과 "/greeting" 경로에 대한 매핑
    public String testvue() {
    	String id = "김말자";   	
        return id;
    }
    
    @GetMapping("/testvue2") // GET 요청과 "/greeting" 경로에 대한 매핑
    public String testvue2() {
    	String id = "김말자";   	
        return id;
    }
    
}
