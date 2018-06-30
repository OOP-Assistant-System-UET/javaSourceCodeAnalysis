package service;

import model.ClassDecration;
import org.eclipse.jdt.core.dom.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static service.FileService.readFileToString;

public class ParseSingleFile {
    private  ArrayList<ClassDecration> listClasses;
    public ParseSingleFile() {
        listClasses = new ArrayList <ClassDecration>();
    }

    public ArrayList<ClassDecration> getListClasses() {
        return listClasses;
    }

    public void setListClasses(ArrayList<ClassDecration> listClasses) {
        this.listClasses = listClasses;
    }

    private int numberClass= 0;

    public int getNumberClass() {
        return numberClass;
    }

    public void setNumberClass(int numberClass) {
        this.numberClass = numberClass;
    }

    int count = 0;
    public void parseFile (String str) {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource(str.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        cu.accept(new ASTVisitor() {
            public boolean visit(TypeDeclaration node) {
                ClassDecration cd = new ClassDecration();
                if (node.isInterface() == true) cd.setInterface(true);
                String name = node.getName().getIdentifier();
                cd.setName(name);
                cd.setPublic(false);
                List <Modifier> modifiers = node.modifiers();
                if (modifiers.size()==0) {
                    cd.setPublic(false);
                }
                else {
                    for (Modifier o : modifiers) {
                        //System.out.println(o.getKeyword().toString());
                        if (o.getKeyword().toFlagValue() == Modifier.ModifierKeyword.PUBLIC_KEYWORD.toFlagValue()) {
                            cd.setPublic(true);
                        }
                    }
                }
                FieldDeclaration[] fieldList = node.getFields();
                MethodDeclaration[] methodList = node.getMethods();
                cd.setPropertyInfor(fieldList);
                cd.setMethodInfor(methodList);
                listClasses.add(cd);
                numberClass++;
                return super.visit(node);
            }
        });
    }


    public void ParseFilesInDir() throws IOException {
        File dirs = new File(".");
        String dirPath = dirs.getCanonicalPath() + File.separator+"src"+File.separator+"main\\java";
        System.out.println(dirPath);
        dirPath = "C:\\Users\\Admin\\IdeaProjects\\jlickr\\src\\main\\java\\com\\jcia\\jlickr\\database";
        dirPath = "C:\\Users\\Admin\\IdeaProjects\\studyJDT\\src\\main\\java";
        File root = new File(dirPath);
        //System.out.println(rootDir.listFiles());
        File[] files = root.listFiles ( );
        String filePath = null;

        for (File f : files ) {
            filePath = f.getAbsolutePath();
            if(f.isFile()){
                parseFile(readFileToString(filePath));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String file = FileService.readFileToString("C:\\Users\\Admin\\IdeaProjects\\studyJDT\\src\\main\\java\\DBUtils.java");
        ParseSingleFile pf = new ParseSingleFile();
        pf.parseFile(file);
        /*ArrayList <ClassDecration> list = pf.listClasses;
        for (int i = 0; i < list.size(); i++) {
            list.get(i).printInfor();
        }*/
        pf.ParseFilesInDir();


    }
}
