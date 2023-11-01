package co.inventorsoft.academy.musicmanager.controller.bonustask;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/otherdomain")
public class OtherDomainController {

    @GetMapping("/data")
    public String getData() {
        return "Data for any other domain";
    }
}
