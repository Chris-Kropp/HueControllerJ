package Lights;

public class Light {
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
}
