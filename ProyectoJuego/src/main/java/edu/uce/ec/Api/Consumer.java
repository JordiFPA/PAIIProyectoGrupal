package edu.uce.ec.Api;

import edu.uce.ec.model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Consumer {


    public void getUser(){
        try {
            URL url = new URL("http://localhost:8080/users/getUser");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Invalid response code: " + responseCode);
            } else {
                StringBuilder sb = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    sb.append(sc.nextLine());
                }
                sc.close();
                JSONArray json = new JSONArray(sb.toString());
                JSONObject user = json.getJSONObject(0);
                System.out.println(user.getString("name") + " SCORE" + user.getInt("score"));
            }
        } catch (Exception e) {
        }
    }

    public void createUser(long id, String name, String password, int health, int score) {
        try {
            URL url = new URL("http://localhost:8080/users/createUser/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            JSONObject user = new JSONObject();
            user.put("id", id);
            user.put("name", name);
            user.put("password", password);
            user.put("health", health);
            user.put("score", score);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = user.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Invalid response code: " + responseCode);
            } else {
                StringBuilder sb = new StringBuilder();
                Scanner sc = new Scanner(connection.getInputStream());
                while (sc.hasNext()) {
                    sb.append(sc.nextLine());
                }
                sc.close();
                System.out.println("Response from server: " + sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUserInApi(User user) {
        try {
            // Convertir el objeto User a JSON usando JSONObject
            JSONObject userJson = new JSONObject();
            userJson.put("id", user.getId());
            userJson.put("name", user.getName());
            userJson.put("password", user.getPassword());
            userJson.put("health", user.getHealth());
            userJson.put("score", user.getScore());

            // Crear la URL de la API para actualizar el usuario
            URL url = new URL("http://localhost:8080/users/updateUser/?id=" + user.getId());

            // Crear la conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            // Enviar el JSON al cuerpo de la solicitud
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = userJson.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Verificar el código de respuesta
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Invalid response code: " + responseCode);
            } else {
                // Si la actualización fue exitosa, puedes imprimir o manejar la respuesta de la API
                StringBuilder sb = new StringBuilder();
                Scanner sc = new Scanner(connection.getInputStream());
                while (sc.hasNext()) {
                    sb.append(sc.nextLine());
                }
                sc.close();
                System.out.println("Response from server: " + sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
