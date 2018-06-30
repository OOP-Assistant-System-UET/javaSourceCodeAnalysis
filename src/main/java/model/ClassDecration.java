package model;

import model.Method;
import model.Property;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;
public class ClassDecration{
    private int key;
    private String name;
    private boolean isPublic = false;
    private boolean isInterface = false;
    private List<String> visibilities;
    private ArrayList<Property> properties ;
    private ArrayList<Method> methods ;
    public ClassDecration() {
        key = -1;
        name = "empty";
        isPublic = false;
        properties = new ArrayList<Property>();
        methods = new ArrayList<Method>();

    }
    public void setKey(int key){
        this.key = key;
    }
    public int getKey(){
        return this.key;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setInterface(boolean anInterface) {
        isInterface = anInterface;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public ArrayList<Method> getMethods() {
        return methods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getvisibilities() {
        return visibilities;
    }

    public void setvisibilities(List<String> visibilities) {
        this.visibilities = visibilities;
    }

    public boolean isPublic() {
        for (String s : visibilities) {
            if (s.equals("public")) isPublic = true;
        }
        return isPublic;
    }
    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public void addproperty(Property var) {
        this.properties.add(var);
    }

    public void addMethod(Method m) {
        this.methods.add(m);
    }


    public void setPropertyInfor(FieldDeclaration[] listNode) {
        for (int i = 0; i < listNode.length; i++) {
            Property var = new Property();
            var.setInfor(listNode[i]);
            this.properties.add(var);
        }
    }

    public void setMethodInfor(MethodDeclaration[] listNode) {
        for (int i = 0; i < listNode.length; i++) {
            Method method = new Method();
            method.setInfor(listNode[i]);
            this.methods.add(method);
        }
    }
    public void printInfor() {
        System.out.println("Class: " + getName() + "  Public: " + isPublic +"Key: " + key);
        for (int i = 0; i < properties.size(); i++) {
            this.properties.get(i).printInfor();
        }
        for (int i = 0; i < methods.size(); i++) {
            this.methods.get(i).printInfor();
        }
    }

}
