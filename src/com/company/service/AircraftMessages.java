package com.company.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marius on 3/9/2017.
 */
public class AircraftMessages {

    private static AircraftMessages aircraftMessages = new AircraftMessages();

    private AircraftMessages() {
        setMessages();
    }

    public static AircraftMessages getInstance(){
        return aircraftMessages;
    }

    public Map<String, String> getHelicopterMessages(){
        return helicopterMessages;
    }

    public Map<String, String> getJetPlaneMessages(){
        return jetPlaneMessages;
    }

    public Map<String, String> getBaloonMessages(){
        return baloonMessages;
    }


    private Map<String, String> baloonMessages = new HashMap<>();
    private Map<String, String> helicopterMessages = new HashMap<>();
    private Map<String, String> jetPlaneMessages = new HashMap<>();

    private void setMessages(){

        baloonMessages.put("RAIN", ": So beautiful when it rains, let's keep flying.");
        baloonMessages.put("FOG", ": I can't see anything, this fog is so bad.");
        baloonMessages.put("SUN", ": Lovely sunny day.");
        baloonMessages.put("SNOW", ": So cold now, I'm freezing.");

        helicopterMessages.put("RAIN", ": I love when it rains.");
        helicopterMessages.put("FOG", ": Flying in fog it's not for faint of heart.");
        helicopterMessages.put("SUN", ": Sun is for pussies, but I love it.");
        helicopterMessages.put("SNOW", ": Now we are in trouble, we have snow on windows.");

        jetPlaneMessages.put("RAIN", ": I hope we can escape this rain.");
        jetPlaneMessages.put("FOG", ": Going really slow in fog.");
        jetPlaneMessages.put("SUN", ": Full throttle to the sun.");
        jetPlaneMessages.put("SNOW", ": We are going down!!! Mayday Mayday!!!");
    }
}
