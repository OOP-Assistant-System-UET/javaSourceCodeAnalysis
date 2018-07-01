package spring;

import service.PackageJson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class DemoApplication {
    @RequestMapping("/response")
    @ResponseBody
    public String response() throws IOException {
        String packagePath = "E:\\Download\\test2\\src\\claxxdiagramfinal";
        return PackageJson.getPackageJSon(packagePath);
    }
}


