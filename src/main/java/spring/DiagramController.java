package spring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.GetJsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import service.ParsePackage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

@Controller
public class DiagramController {

    @RequestMapping(value = "/diagram", method = RequestMethod.GET)
    public String getDiagram() {
        return "diagram";
    }

    @RequestMapping(value="/class",  method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<ParsePackage> response() throws IOException {
        String packagePath = "C:\\Users\\Nguyen Hieu\\IdeaProjects\\javaSourceCodeAnalysis\\src\\main\\java\\model";
        ParsePackage pp = new ParsePackage();
        pp.parseFilesInPackage(packagePath);
        return new ResponseEntity<>(pp, HttpStatus.OK);
    }
    @RequestMapping(value="/relationship",  method = RequestMethod.GET)
    @ResponseBody
    public String responseRela() throws IOException {
        String packagePath = "C:\\Users\\Nguyen Hieu\\IdeaProjects\\javaSourceCodeAnalysis\\src\\main\\java\\model";
        return GetJsonObject.getRelationshipListJson(packagePath);
    }
    /*public String getClasses() throws IOException {
        String packagePath = "C:\\Users\\Nguyen Hieu\\IdeaProjects\\jlickr\\src\\main\\java\\com\\jcia\\jlickr\\dao";

        return GetJsonObject.getPackageJSon(packagePath);
    }

    public String responseRela() throws IOException {
        String packagePath = "C:\\Users\\Nguyen Hieu\\IdeaProjects\\jlickr\\src\\main\\java\\com\\jcia\\jlickr\\dao";
        return GetJsonObject.getRelationshipListJson(packagePath);
    }

    @RequestMapping(value={"/diagram"}, method = RequestMethod.GET)
    public String diagram( Model model) throws IOException {

        model.addAttribute("classes", this.getClasses());
        model.addAttribute("relationship", this.responseRela());
        return "diagram";
    }*/
}

