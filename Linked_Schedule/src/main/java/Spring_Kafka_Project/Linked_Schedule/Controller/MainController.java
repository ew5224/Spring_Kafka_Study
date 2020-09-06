package Spring_Kafka_Project.Linked_Schedule.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String LoginHome(){


        return "Loginhome";
    }

    @PostMapping("/login")
    public String Login(){

        return "Home";
    }



}
