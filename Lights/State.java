package Lights;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

//TODO: consider making light extend state, not sure if better though.
    //TODO: Maybe don't do that ^
public class State extends Light {
    //named in lowercase in order to match the variables in the Hue API
    private boolean on; //On/Off state (true - On, false - Off)
    private int bri;    //Brightness: 1-254.
    private boolean reachable;  //Whether or not the light is reachable.

    //hardware dependent
    private int hue;    //Wrapping value, 0-65535. (RED: 0/65535, GREEN: 21845, BLUE: 43690).
    private int sat;    //Saturation: 0-254. (LOW SATURATION: 0, HIGH SATURATION: 254).

    private int[] xy;   //The x and y coordinates of a color in CIE color space: 0-1.
    private int ct;     //The mired color temperature. 153(6500k)-500(2000k).
    private String alert;   //Most recent alert whether current or expired. ("none" the light hasn't performed an alert affect, “select” The light is performing one breathe cycle, “lselect” The light is performing breathe cycles for 15 seconds or until an "alert": "none" command is received)
    private String effect;  //The dynamic effect of the light. ("none", "colorloop" the light cycles through all hues with current brightness and saturation)
    private String colormode;   //Color mode of the light, last command type received, only present when at least one of the modes is supported by the light ("hs" Hue and Saturation, "xy" CIE coordinate mode, "ct" Color Temperature)

    //transitions - temporary, any of the increments can be PUT with a value of 0 in order to stop current transitions.
    private int transitiontime; //Duration of the transition, in multiples of 100ms.
    private int bri_inc;    //Increments/Decrements brightness: -254-254. Ignored if bri is provided. Current brightness transitions are stopped.
    private int sat_inc;    //Increments/Decrements saturation: -254-254. Ignored if sat is provided. Current saturation transitions are stopped.
    private int hue_inc;    //Increments/Decrements hue, wrapping: -65534-65534. Ignored if hue is provided. Current hue transitions are stopped.
    private int ct_inc;     //Increments/Decrements the mired color temperature: -65534-65534. Ignored if ct is provided. Current ct transitions are stopped.
    private int[] xy_inc;     //Increments/Decrements CIE color: list of length 2. Ignored if sat is provided. Current xy transitions are stopped.

    String bridgeURL = super.bridgeURL + "state/";

    public void toggleLight(){
        try {
            String putData;
            URL url = new URL(bridgeURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            if(on == true) {
                putData = "{\"on\":false}";
            }
            else{
                putData = "{\"on\":true}";
            }
            byte[] putDataBytes = putData.getBytes(StandardCharsets.UTF_8);
            connection.setDoOutput(true);
            DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
            writer.write(putDataBytes);
            writer.flush();
            writer.close();
            connection.getInputStream();    //required for PUT to be completed.
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void turnLightOff(){
        try {
            String putData;
            URL url = new URL(bridgeURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            putData = "{\"on\":false}";
            byte[] putDataBytes = putData.getBytes(StandardCharsets.UTF_8);
            connection.setDoOutput(true);
            DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
            writer.write(putDataBytes);
            writer.flush();
            writer.close();
            connection.getInputStream();    //required for PUT to be completed.
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void turnLightOn(){
        try {
            String putData;
            URL url = new URL(bridgeURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            putData = "{\"on\":true}";
            byte[] putDataBytes = putData.getBytes(StandardCharsets.UTF_8);
            connection.setDoOutput(true);
            DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
            writer.write(putDataBytes);
            writer.flush();
            writer.close();
            connection.getInputStream();    //required for PUT to be completed.
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
