package co.inventorsoft.academy.musicmanager.controller.bonustask;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080") // Allow requests from http://localhost:8080
@RequestMapping("/api/localhost")
public class LocalhostController {

    @GetMapping("/data")
    public String getData() {
        return "Data for localhost";
    }
}
