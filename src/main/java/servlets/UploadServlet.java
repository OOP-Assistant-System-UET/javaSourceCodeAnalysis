package servlets;

import service.GetJsonObject;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;

public class UploadServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String packagePath = "C:\\Users\\Admin\\Downloads\\Compressed\\tuan6\\src\\tuan6";
        String packageJSon = GetJsonObject.getPackageJSon(packagePath);
        System.out.println(packageJSon);
        try (PrintStream out = new PrintStream(new FileOutputStream("filename.txt"))) {
            out.print(packageJSon);

        }
        request.setAttribute("json",packageJSon);
        getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);



    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
