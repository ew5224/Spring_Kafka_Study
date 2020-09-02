package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute( "data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value= "name", required = true) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    /// html의 Body부의 return 데이터를 직접 넣어주겠다
    /// ResponseBody를 붙여서 html 이딴거 없이 내용만 내려
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; // view 이런 거 없이 그대로 내려간다

   }

   @GetMapping("hello-api")
   @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
       Hello hello = new Hello();
       hello.setName(name);
       return hello;
   }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
