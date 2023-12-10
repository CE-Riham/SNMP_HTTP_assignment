<%@page import="java.io.*"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.Scanner"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    System.out.println("Hello");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    System.out.println(username);
    System.out.println(password);

    boolean status = false;
    String filePath = "C:/Users/Msys/Desktop/server/src/main/webapp/webapps/database.txt";
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String []values = line.split(" ");
                if(values[1].equals(username) && values[2].equals(password)){
                    status = true;
                    break;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "{\"result\":\"" + (status ? "Permit" : "Deny") + "\"}";
        System.out.println(status ? "Permit" : "Deny");
%>
<%=result %>

