package service;

import model.ClassDecration;
import model.Relationship;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static service.FileService.readFileToString;

public class ParsePackage {

    public ArrayList<ClassDecration> classes;
    private ArrayList<Relationship> relationships;

    ParsePackage() {
        this.classes = new ArrayList<ClassDecration>();
        this.relationships = new ArrayList<Relationship>();
    }

    public ArrayList<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(ArrayList<Relationship> relationships) {
        this.relationships = relationships;
    }

    public void parseFilesInPackage(String packagePath) throws IOException {
        File root = new File(packagePath);
        //System.out.println(rootDir.listFiles());
        File[] files = root.listFiles ();
        String filePath = null;
        int num = 0;
        for (File f : files ) {
            filePath = f.getAbsolutePath();

            if(f.isFile()){
                ParseSingleFile parseSingleFile = new ParseSingleFile();
                parseSingleFile.parseFile(readFileToString(filePath));
                for (ClassDecration cd : parseSingleFile.getListClasses()) {
                    cd.setKey(num);
                    this.classes.add(cd);
                    num ++;
                }
            }
        }
        this.relationships = setupRelationships();
    }
    //thiet lap cac relationship tu cac class
    public ArrayList<Relationship> setupRelationships() {
        ArrayList <Relationship> relationshipList = new ArrayList<Relationship>();
        for (ClassDecration cd : this.classes) {
            //lay relationship kieu extend;
            if (cd.getSuperClassName() != null) {
                int keySuperClass = this.findKeyByName(cd.getSuperClassName());
                if (keySuperClass != -1) {
                    Relationship r = new Relationship();
                    r.setFrom(cd.getKey());
                    r.setTo(keySuperClass);
                    r.setType("extends");
                    relationshipList.add(r);
                }
            }


            //lay relationship kieu implements;
            if (cd.getSuperInterfaceName().size() > 0) {
                for (String s : cd.getSuperInterfaceName()) {
                    int keySuperInterface = this.findKeyByName(s);
                    if (keySuperInterface != -1) {
                        Relationship r = new Relationship();
                        r.setFrom(cd.getKey());
                        r.setTo(keySuperInterface);
                        if ( cd.isInterface() == false ) r.setType("implements");
                        else r.setType("extends");
                        relationshipList.add(r);
                    }
                }
            }
        }
        return relationshipList;
    }

    public int findKeyByName(String name) {
        if (this.classes.size() > 0) {
            for (ClassDecration cd : this.classes) {
                if (name.equals(cd.getName())) return cd.getKey();
            }
            return -1;
        }
        return -1;
    }
    public ArrayList<ClassDecration> getClasses(){
        return classes;
    }
    public void printInfor() {
        for (ClassDecration cd : this.classes) {
            cd.printInfor();
            System.out.println("---------");
        }
        showRelationships();

    }

    public void showRelationships() {
        if (this.relationships.size() == 0) {
            System.out.println("no relationship");
        }
        else {
            for (Relationship r : this.relationships) {
                r.printInfor();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        ParsePackage p = new ParsePackage();
        String packagePath = "C:\\Users\\Admin\\IdeaProjects\\studyJDT\\src\\main\\java";
        p.parseFilesInPackage(packagePath);
        for (ClassDecration cd : p.classes) {
            cd.printInfor();
        }

    }
}
