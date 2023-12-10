package main.webapp.webapps;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/Step1")
public class Step1 extends HttpServlet {
    private boolean checkUser(String ID, String password){
        boolean status = false;
        String filePath = "C:\\Users\\Msys\\Desktop\\server\\src\\main\\webapp\\webapps\\database.txt";

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String []values = line.split(" ");
                if(values[0].equals(ID) && values[2].equals(password)){
                    status = true;
                    break;
                }
            }
            bufferedReader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(status? "Permit":"Deny");
        return status;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ID = request.getParameter("ID");
        String password = request.getParameter("password");
        System.out.println(ID);
        System.out.println(password);
        response.addHeader("Content-Type", "application/json;UTF-8");
        if (checkUser(ID, password)) {
            response.getWriter().write("{\"result\":\"Permit\"}");
        } else {
            response.getWriter().write("{\"result\":\"Deny\"}");
        }
    }
}
