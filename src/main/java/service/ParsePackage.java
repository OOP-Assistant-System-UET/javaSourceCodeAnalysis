package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static service.FileService.readFileToString;

public class ParsePackage {

    public ArrayList<ClassDecration> classes;

    ParsePackage() {
        this.classes = new ArrayList<ClassDecration>();
    }

    public void parseFilesInPackage(String packagePath) throws IOException {
        File root = new File(packagePath);
        //System.out.println(rootDir.listFiles());
        File[] files = root.listFiles ();
        String filePath = null;

        for (File f : files ) {
            filePath = f.getAbsolutePath();

            if(f.isFile()){
                ParseSingleFile parseSingleFile = new ParseSingleFile();
                parseSingleFile.parseFile(readFileToString(filePath));
                for (ClassDecration cd : parseSingleFile.getListClasses()) {
                    this.classes.add(cd);
                }
            }
        }
    }

    public void printInfor() {
        for (ClassDecration cd : this.classes) {
            cd.printInfor();
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
