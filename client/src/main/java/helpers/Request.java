package helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {

    public static String sendGetRequest(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.getResponseCode();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line, res = "";
        while ((line = bufferedReader.readLine()) != null) {
            res+=line;
        }
        bufferedReader.close();
        try {
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
