package edu.uce.ec.Api;

import edu.uce.ec.model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Consumer {


    public void getUser() {
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

    public List<User> getRanking() {
        List<User> rankingList = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8080/users/getRanking");
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
                for (int i = 0; i < json.length(); i++) {
                    JSONObject jsonUser = json.getJSONObject(i);
                    User user = new User(
                            jsonUser.getLong("id"),
                            jsonUser.getString("name"),
                            jsonUser.getString("password"),
                            jsonUser.getInt("health"),
                            jsonUser.getInt("score")
                    );
                    rankingList.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rankingList;
    }


    public long createUser(String name, String password, int health, int score) {
        try {
            URL url = new URL("http://localhost:8080/users/createUser/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            JSONObject user = new JSONObject();
            // No incluyas el ID aquí

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
                // Leer la respuesta del servidor
                StringBuilder sb = new StringBuilder();
                try (Scanner sc = new Scanner(connection.getInputStream())) {
                    while (sc.hasNext()) {
                        sb.append(sc.nextLine());
                    }
                }
                System.out.println("Response from server: " + sb.toString());

                // Extraer el ID del usuario de la respuesta del servidor
                JSONObject response = new JSONObject(sb.toString());
                long userId = response.getLong("id");
                return userId;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Retorna un valor negativo si ocurre un error
        }
    }

    public void updateUser(User user) {

        try {
            long id = user.getId();
            // Establecer la URL del endpoint de actualización
            URL url = new URL("http://localhost:8080/users/updateUser/" + id);

            // Abrir conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar la solicitud como PUT
            connection.setRequestMethod("PUT");

            // Establecer las cabeceras de la solicitud
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");

            // Habilitar la escritura de datos en el cuerpo de la solicitud
            connection.setDoOutput(true);

            // Crear el objeto JSON con los datos actualizados del usuario
            JSONObject userJson = new JSONObject();
            userJson.put("name", user.getName());
            userJson.put("password", user.getPassword());
            userJson.put("health", user.getHealth());
            userJson.put("score", user.getScore());

            // Escribir los datos JSON en el cuerpo de la solicitud
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = userJson.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Obtener el código de respuesta HTTP
            int responseCode = connection.getResponseCode();

            // Verificar si la respuesta es exitosa (código 200)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Leer la respuesta del servidor si es necesario
                StringBuilder sb = new StringBuilder();
                try (Scanner sc = new Scanner(connection.getInputStream())) {
                    while (sc.hasNext()) {
                        sb.append(sc.nextLine());
                    }
                }
                System.out.println("Response from server: " + sb.toString());
            } else {
                // Si la respuesta no es exitosa, lanzar una excepción
                throw new RuntimeException("Invalid response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public User getUserByUsernameAndPassword(String username, String password) {
        try {
            URL url = new URL("http://localhost:8080/users/getUserByUsernameAndPassword?name=" + username + "&password=" + password);
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
                JSONObject json = new JSONObject(sb.toString());
                long id = json.getLong("id");
                String name = json.getString("name");
                int health = json.getInt("health");  // Obtener la salud del usuario
                int score = json.getInt("score");
                return new User(id, name, password, health, score);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }







}





