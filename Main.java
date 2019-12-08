import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Hashtable;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Button Chair = new Button("Chair");
        Chair.setOnAction(e -> {
            try {
                URL url2 = new URL("http://192.168.0.20/api/1dum7N67DnTLeFjxGcZrQHp38RmniQE86jjPR06R/lights/1/");
                HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
                connection2.setRequestMethod("GET");
                StringBuilder content;
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection2.getInputStream()))) {
                    String line;
                    content = new StringBuilder();
                    while ((line = in.readLine()) != null) {
                        content.append(line);
                        content.append(System.lineSeparator());
                    }
                }
                Hashtable light1 = makeHash(content.toString());
                System.out.println(content.toString());
                connection2.disconnect();

                System.out.println(content.toString());
                URL url = new URL("http://192.168.0.20/api/1dum7N67DnTLeFjxGcZrQHp38RmniQE86jjPR06R/lights/1/state/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("PUT");

                String putData = "{\"on\":false}";

                byte[] putDataBytes = putData.getBytes(StandardCharsets.UTF_8);
                connection.setDoOutput(true);
                try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
                    writer.write(putDataBytes);
                    writer.flush();
                    writer.close();

//                    StringBuilder content;

                    try (BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()))) {
                        String line;
                        content = new StringBuilder();
                        while ((line = in.readLine()) != null) {
                            content.append(line);
                            content.append(System.lineSeparator());
                        }
                    }
                    System.out.println(content.toString());
                } finally {
                    connection.disconnect();
                }
            }
            catch (Exception e2){
                e2.printStackTrace();
            }
        });
//        Button Chair2 = new Button("Chair2");
//        Chair2.setOnAction(e -> {
//            try {
//                URL url = new URL("http://192.168.0.20/api/1dum7N67DnTLeFjxGcZrQHp38RmniQE86jjPR06R/lights/1/state");
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("GET");
//
//                String putData = "{\"on\":false}";
//
//                byte[] putDataBytes = putData.getBytes(StandardCharsets.UTF_8);
//                connection.setDoOutput(true);
//                try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
//                    writer.write(putDataBytes);
//                    writer.flush();
//                    writer.close();
//
//                    StringBuilder content;
//
//                    try (BufferedReader in = new BufferedReader(
//                            new InputStreamReader(connection.getInputStream()))) {
//                        String line;
//                        content = new StringBuilder();
//                        while ((line = in.readLine()) != null) {
//                            content.append(line);
//                            content.append(System.lineSeparator());
//                        }
//                    }
//                    System.out.println(content.toString());
//                } finally {
//                    connection.disconnect();
//                }
//            }
//            catch (Exception e2){
//                e2.printStackTrace();
//            }
//        });
        HBox hb = new HBox();
        hb.getChildren().add(Chair);
        Scene scn = new Scene(hb);
        primaryStage.setScene(scn);
        primaryStage.show();
    }

    public Hashtable makeHash(String string){

        return new Hashtable();
    }
}
