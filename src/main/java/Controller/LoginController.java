package Controller;

import model.DatabaseManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("userName")String userName, @RequestParam("password")String password){
        DatabaseManager db = new DatabaseManager();
        if(db.checkUser(userName, password)){
            System.out.println("tuananh a");
            return "redirect: /home";
        }
        else{
            System.out.println("tuan anh b");
            return "redirect:/";
        }
    }
}
