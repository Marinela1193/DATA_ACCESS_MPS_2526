package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class RestAPIConnection {
    protected String server = Endpoints.SERVER;
    protected String apiPath = Endpoints.API_PATH;

    public String getServer() { return server;}
    public String getApiPath() { return apiPath;}

    public void setApiPath(String server, String apiPath) {
        this.server = server;
        this.apiPath = apiPath;}

    public RestAPIConnection(String server, String apiPath) {setApiPath(server,apiPath);}

    public boolean isServerAvailable() {
        try{
            URL url = new URL(getServer());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            int responseCode = connection.getResponseCode();
            return (responseCode == HttpURLConnection.HTTP_OK);
        }catch(IOException e){
            return false;
        }
    }

    public HttpResponse getRequest(String endpoint) throws IOException {
        HttpURLConnection connection = null;

        URL ulr = new URL(getApiPath() + endpoint);
        connection = (HttpURLConnection) ulr.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        HttpResponse response = new HttpResponse(connection.getResponseCode(), connection.getResponseMessage(),'');

        InputStream stream = null;

        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
            stream = connection.getInputStream();
        }else {
            stream = connection.getErrorStream();
        }

        if(stream != null) {
            Scanner scanner = new Scanner(stream, "UTF-8");
            String body = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
            scanner.close();
            response.setBody(body);
        }

        if(connection != null){
            connection.disconnect();
        }
        return response;
    }


}
