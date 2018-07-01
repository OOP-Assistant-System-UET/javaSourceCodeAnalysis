package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import model.RelationshipList;
import org.json.simple.JSONArray;
/*import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;*/

public class GetJsonObject {
    public static String getPackageJSon(String packagePath) throws IOException {
        ParsePackage pp = new ParsePackage();
        pp.parseFilesInPackage(packagePath);
        Gson gson = new Gson();
        String ppJson = gson.toJson(pp);
        /*System.out.println(ppJson);
        ParsePackage pp2 = gson.fromJson(ppJson,ParsePackage.class);*/
        return ppJson;
    }

    public static String getRelationshipListJson(RelationshipList rl) {
        Gson gson = new Gson();
        String rlJson = gson.toJson(rl);
        /*RelationshipList rl2 = gson.fromJson(rlJson,RelationshipList.class);
        rl2.showRelationships();*/
        return rlJson;

    }
    public static void main(String[] args) throws IOException {

        //link package
        String packagePath = "C:\\Users\\Admin\\IdeaProjects\\studyJDT\\src\\main\\java";
        String packageJSon = GetJsonObject.getPackageJSon(packagePath);
        System.out.println(packageJSon);


        //thu Lay doi tuong  ParsePackage java tu JSon string de kiem tra
        Gson gson = new Gson();
        ParsePackage pp = gson.fromJson(packageJSon,ParsePackage.class);
        pp.printInfor();

        //Lay doi tuong RelationshipJson tu ParsePackage
        RelationshipList rl = new RelationshipList();
        rl.getRelationshipListInPackage(pp);
        String relationshipsJson = GetJsonObject.getRelationshipListJson(rl);
        System.out.println(relationshipsJson);

        //Lay doi tuong RelationshipList tu json va kiem tra
        RelationshipList rl2 = gson.fromJson(relationshipsJson,RelationshipList.class);
        rl2.showRelationships();


    }
}