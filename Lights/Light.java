package Lights;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Light {
    private int id;
    private State state;    //state data of the light (color, hue, saturation, brightness, etc.)
    private String type;    //A fixed name describing the type of light.
    private String name;    //A unique, editable name.
    private String modelid; //The model of the light.
    private String uniqueid;    //the MAC address of the device with an endpoint id. (In the format: AA:BB:CC:DD:EE:FF:00:11-XX)
    private String manufacturername;    //The name of the manufacturer.
    private String luminaireuniqueid;   //Unique id of the luminaire the light is a part of. (In the format: AA:BB:CC:DD-XX-YY)
    private boolean streaming;  //Whether or not the light supports streaming features.
    private boolean renderer;   //Whether or not the light can be used for entertainment streaming as a renderer.
    private boolean proxy;      //Whether or not the light can be used for entertainment streaming as a proxy node.
    private String swversion;   //Software version of the light.
    String bridgeURL = Main.Master.bridgeURL + "lights/" + id + "/";

    public int getId() {
        return id;
    }

    public void toggleLight(){

    }

    public void deleteLight(){
        try {
            URL url2 = new URL(bridgeURL);
            HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
            connection2.setRequestMethod("DELETE");
            StringBuilder content;
//            connection2.send
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection2.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            connection2.disconnect();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }

}
