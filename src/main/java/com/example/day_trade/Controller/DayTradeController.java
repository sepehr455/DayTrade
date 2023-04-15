package Controller;

import Entities.User;
import Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class DayTradeController {

    @Autowired
    private UserService userService;


    // Adding a new user
    @PostMapping("/user/add")
    public ResponseEntity<String> getUserInfo(@RequestBody String newUserName){
        User newUser = new User();
        newUser.setFullName(newUserName);
        userService.saveUser(newUser);
        return ResponseEntity.ok("New user added successfully");
    }


}
