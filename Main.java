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
import java.util.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Button Chair = new Button("Chair");
        Chair.setOnAction(e -> {
            try {
                URL url2 = new URL("http://192.168.0.20/api/1dum7N67DnTLeFjxGcZrQHp38RmniQE86jjPR06R/");
                HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
                connection2.setRequestMethod("GET");
                StringBuilder content;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection2.getInputStream()))) {
                    String line;
                    content = new StringBuilder();
                    while ((line = in.readLine()) != null) {
                        content.append(line);
                        content.append(System.lineSeparator());
                    }
                }
                Hashtable light1 = makeHash(content.toString());
//                System.out.println(content.toString());
                connection2.disconnect();

//                System.out.println(content.toString());
                URL url = new URL("http://192.168.0.20/api/1dum7N67DnTLeFjxGcZrQHp38RmniQE86jjPR06R/lights/1/state/1");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("PUT");

                String putData = "{\"on\":true}";

                byte[] putDataBytes = putData.getBytes(StandardCharsets.UTF_8);
                connection.setDoOutput(true);
                try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
                    writer.write(putDataBytes);
                    writer.flush();
                    writer.close();

                    try (BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()))) {
                        String line;
                        content = new StringBuilder();
                        while ((line = in.readLine()) != null) {
                            content.append(line);
                            content.append(System.lineSeparator());
                        }
                    }
//                    System.out.println(content.toString());
                } finally {
                    connection.disconnect();
                }
            }
            catch (Exception e2){
                e2.printStackTrace();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().add(Chair);
        Scene scn = new Scene(hb);
        primaryStage.setScene(scn);
        primaryStage.show();
    }

    public Hashtable makeHash(String string){
        Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
        String k1;
        String v1;
        char[] chars = string.toCharArray();
        hashHelpRecursive(string, 0);
        return new Hashtable();
    }

    public void hashHelpRecursive(String string, int depth){
        hashRec(string);
    }

    public void hashRec(String string) {
        hashHelp(string);
    }

    public void hashHelp(String string){
        int depth = 0;
        boolean run = true;
        String[] nstring = string.split(",");
        for (String str:nstring) {
            String[] str2 = str.split("\\{");
            if(str2.length > 1) {
//                if (str.startsWith("{")) {
//                    depth--;
//                    run = false;
//                    for (String str3 : str2) {
//                        for (int i = 0; i < depth; i++) {
//                            System.out.print("[]");
//                        }
//                        System.out.println(str3);
//                        depth++;
//                    }
//                } else {
                run = false;
                depth--;
                for (String str3 : str2) {
                    depth++;
                    for (int i = 0; i < depth; i++) {
                        System.out.print("⬛");
                    }
                    System.out.println(str3.replaceAll("}",""));
//                    }
                }
            }
//            if(str.contains("{")){
//                for (int i = 0; i < countChar(str,'{'); i++) {
//                    depth++;
//                    System.out.println();
//                }
//            }
            if(run) {
                for (int i = 0; i < depth; i++) {
                    System.out.print("⬛");
                }
                System.out.println(str.replaceAll("}",""));
            }
            run = true;
            if(str.contains("}")){
                for (int i = 0; i < countChar(str,'}'); i++) {
                    depth--;
                }
            }
        }
    }

    public int countChar(String str, char c)
    {
        int count = 0;

        for(int i=0; i < str.length(); i++)
        {    if(str.charAt(i) == c)
            count++;
        }

        return count;
    }

    public void hashMaker(String string) {
        //TODO: turn json into hash table, by matching pairs of { }, then cut them off and recurse deeper, until none occur.
        int starting = 0;
        int ending = 0;
        int bracketCounter = 0;
        char[] chars = string.toCharArray();
        if(string.contains("\\{") || string.contains("}")) {
            for (int i = 0; i < string.length(); i++) {
                if (chars[i] == '{') {
                    if(bracketCounter == 0) {
                        starting = i+1;
                    }
                    bracketCounter++;
                }
                if (chars[i] == '}') {
                    bracketCounter--;
                    if (bracketCounter == 0) {
                        ending = i;
//                        System.out.println(i);
                    }
                }
            }
            String nextString = string.replaceFirst("\\{","");
//            hashRec(string.substring(starting, ending));
//            System.out.println(string);
            //TODO: handle adding to hashMap (containing a hashmap).
        }
        else{
            //TODO: handle adding to hashMap (deepest level).
        }
    }
}
