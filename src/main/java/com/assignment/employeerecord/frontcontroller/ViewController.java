package com.assignment.employeerecord.frontcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
   
   @GetMapping("/")
   public String landingpage(){
      return "/landingpage";
   }
}
