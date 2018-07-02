package spring;

import service.GetJsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class DiagramController {
    @RequestMapping(value="node",  method = RequestMethod.GET)
    @ResponseBody
    public String response() throws IOException {
        String packagePath = "C:\\Users\\Admin\\IdeaProjects\\studyJDT\\src\\main\\java";
        return GetJsonObject.getPackageJSon(packagePath);
    }
}

