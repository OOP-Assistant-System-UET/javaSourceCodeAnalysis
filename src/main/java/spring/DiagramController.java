package spring;

import model.RelationshipList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import service.ParsePackage;

import java.io.IOException;

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
    @RequestMapping(value="/relationship",  method = RequestMethod.GET, produces = "application/json")

    @ResponseBody

    public ResponseEntity<RelationshipList> responseRela() throws IOException {
        String packagePath = "C:\\Users\\Nguyen Hieu\\IdeaProjects\\javaSourceCodeAnalysis\\src\\main\\java\\model";
        ParsePackage pp = new ParsePackage();
        pp.parseFilesInPackage(packagePath);
        RelationshipList rl = new RelationshipList();
        rl.getRelationshipListInPackage(pp);
        return new ResponseEntity<>(rl, HttpStatus.OK);
    }

}

