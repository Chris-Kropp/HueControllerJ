package Main;

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
    Hashtable dataHash = new Hashtable();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Button Chair = new Button("Chair");

        try {
            URL url2 = new URL(Master.bridgeURL);
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
//            System.out.println(content);
//            Hashtable light1 = makeHash(content.toString());

            connection2.disconnect();
        }
        catch (Exception e2) {
        e2.printStackTrace();
        }

        Chair.setOnAction(e -> {
            try {
                URL url = new URL("http://192.168.0.20/api/1dum7N67DnTLeFjxGcZrQHp38RmniQE86jjPR06R/lights/1/state/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("PUT");
                String putData = "{\"on\":false}";
                byte[] putDataBytes = putData.getBytes(StandardCharsets.UTF_8);
                connection.setDoOutput(true);
                DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
                writer.write(putDataBytes);
                writer.flush();
                writer.close();
                connection.getInputStream();
                connection.disconnect();
            }
            catch (Exception eas) {
                eas.printStackTrace();
            }



//            try {
//                URL url = new URL("http://192.168.0.20/api/1dum7N67DnTLeFjxGcZrQHp38RmniQE86jjPR06R/lights/1/state/");
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("PUT");
//
//                String putData = "{\"on\":false}";
//                StringBuilder c2;
//
//                byte[] putDataBytes = putData.getBytes(StandardCharsets.UTF_8);
//                connection.setDoOutput(true);
//                try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
//                    writer.write(putDataBytes);
//                    writer.flush();
//                    writer.close();
//
//                    try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
////                        String line;
////                        c2 = new StringBuilder();
////                        while ((line = in.readLine()) != null) {
////                            c2.append(line);
////                            c2.append(System.lineSeparator());
////                        }
//                    }
//                } finally {
//                    connection.disconnect();
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }




        });
        HBox hb = new HBox();
        hb.getChildren().add(Chair);
        Scene scn = new Scene(hb);
        primaryStage.setScene(scn);
        primaryStage.show();
    }

    public Hashtable makeHash(String string) {
        Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
        String k1;
        String v1;
        char[] chars = string.toCharArray();
        returnStructured(string);
        return new Hashtable();
    }

    public void returnStructured(String string) {
        ArrayList<String> currentKey = new ArrayList<>();
        int cDepth = 0;
        ArrayList obj = new ArrayList();
        int depth = 0;
        boolean run = true;
        String[] nstring = string.split(",");
        for (String str : nstring) {
            String[] str2 = str.split("\\{");
            if (str2.length > 1) {
                run = false;
                depth--;
                for (String str3 : str2) {
                    depth++;

                    for (int i = 0; i < depth; i++) {
//                        System.out.print("⬛");
                        str3 = "⬛" + str3;
                    }
                    currentKey.add(str3.replaceAll("}", ""));
//                    System.out.println(str3.replaceAll("}", ""));
                    String[] strsplit = str3.split(":");
                    if (strsplit.length == 1) {
                        cDepth = depth;

                    }
                }
            }
            if (run) {
                for (int i = 0; i < depth; i++) {
//                    System.out.print("⬛");
                    str = "⬛" + str;
                }
                currentKey.add(str.replaceAll("}", ""));
//                System.out.println(str.replaceAll("}", ""));
            }
            run = true;
            if (str.contains("}")) {
                for (int i = 0; i < countChar(str, '}'); i++) {
                    depth--;
                }
            }
        }
        prnt(currentKey);
    }

    public int countChar(String str, char c) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c)
                count++;
        }
        return count;
    }

    //TODO: this should probably be done recursively
    public void prnt(ArrayList ckey){
        System.out.println("one");
//        for (Object s : ckey){
//            System.out.println((String) s);
//        }
//        ArrayList<String> keys = new ArrayList<>();
//        int prevDepth = 0;
//        Hashtable ht = new Hashtable();
//        String prevKey;
//        for (Object str:ckey) {
//            String item = (String) str;
//            TODO: Check if depth has decreased from the previous depth.
//
//            if ((item.endsWith(":"))) {
//                item = item.replaceAll("\"","").replace(":","").replaceAll("⬛","");
//                ht.put(item, new Hashtable<>());
//                if(keys.size()-1 == index){
//                    index++;
//                    keys.add(item);
//                }
//                else{
//                    keys.get(index) =
//                }
//
//            }
//
//            else{
//                if (countChar(item, '⬛') < prevDepth){
//                    index--;
//                ((Hashtable) ht.get(histList.get(index))).put();
//                }
//
//                else if (countChar(item, '⬛') > prevDepth){
//
//                }
//
//                else if (countChar(item, '⬛') == prevDepth){
//
//                }
//            }
//        }
    }

//    public void prnnt(Hashtable ht)

    int index = 0;
}