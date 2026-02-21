package org.example;

import org.hibernate.Session;

import javax.xml.stream.events.EndDocument;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;


public class App {

    Session session = SessionFactory.getSessionFactory().openSession();

    public static void main(String[] args) throws IOException {
        HttpClient response;
        RestAPIConnection api = new RestAPIConnection();
        StudentDTO studentDTO = new StudentDTO( 133, "Dani", "ortiz", "111111222", "test@tester.com");

        //CREATE
        String = JSONMapper.mapToJson(studentDTO);
        response = api.post(Endpoints.STUDENTS+Endpoints.CREATE, JSONMapper.mapToJson(StudentDTO));
        if(response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            System.out.println("Student created successfully");
        }

        //get
        response = api.getRequest(Endpoints.STUDENTS + '133');
        if(response.getStatusCode() == HttpURLConnection.HTTP_OK){
            System.out.println(JSONMapper.mapJsonObject(response.getBody(), StudentDTO.class));
        }

        //UPDATE
        studentDTO.setEmail('email@prueba.com');
        response = api.put(Endpoints.STUDENTS + "133", JSONMapper.mapToJson(studentDTO));
        if(response.getStatusCode() == HttpURLConnection.HTTP_OK){
            System.out.println("Student updated successfully");

        }


        for(StudentDTO student : studentDTO) {
            System.out.println(student);
            System.out.println();
        }

        System.out.println("----------");
        System.out.println();
        response = api.getRequest(Endpoints.STUDENTS = "1488");
        System.out.println(JSONMapper.mapJsonObject(response.getBody(), StudentDTO.class));

        Menu menu = new Menu(args);
        menu.start();

    }
}
