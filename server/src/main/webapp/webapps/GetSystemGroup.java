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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@WebServlet("/getSystemGroup")
public class GetSystemGroup extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        URL url = new URL("http://localhost/Networks2-Asignment2/php/getters.php?function=getSystemGroup");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.getResponseCode();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line, res = "";
        while ((line = bufferedReader.readLine()) != null) {
            res+=line;
            System.out.println("Sending Group system data");
            System.out.println(line);
        }
        bufferedReader.close();
        try {
            response.getWriter().write(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
