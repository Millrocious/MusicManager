package co.inventorsoft.academy.musicmanager.controller.bonustask;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    @GetMapping("/user")
    public String getSomeText() {
        return "User is authenticated!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getSomeTextForAdmin() {
        return "Admin is authenticated!";
    }
}
