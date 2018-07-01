package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
/*import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;*/

public class PackageJson {
    public static String getPackageJSon(String packagePath) throws IOException {
        ParsePackage pp = new ParsePackage();
        pp.parseFilesInPackage(packagePath);
        Gson gson = new Gson();
        String ppJson = gson.toJson(pp);
        /*System.out.println(ppJson);
        ParsePackage pp2 = gson.fromJson(ppJson,ParsePackage.class);*/
        return ppJson;
    }

    public static void main(String[] args) throws IOException {

        //link package
        String packagePath = "C:\\Users\\Admin\\IdeaProjects\\studyJDT\\src\\main\\java";
        String packageJSon = PackageJson.getPackageJSon(packagePath);
        System.out.println(packageJSon);

        //Lay doi tuong java tu JSon string
        Gson gson = new Gson();
        ParsePackage pp = gson.fromJson(packageJSon,ParsePackage.class);
        pp.printInfor();
    }
}