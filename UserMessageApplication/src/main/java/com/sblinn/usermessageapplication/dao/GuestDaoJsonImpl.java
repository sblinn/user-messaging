

package com.sblinn.usermessageapplication.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sblinn.usermessageapplication.dto.Guest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


/**
 *
 * @author Sara Blinn
 */
public class GuestDaoJsonImpl implements GuestDao {
    
    private final String GUEST_DIR_FILEPATH;
    
    // Map of Guests from the Guests.json file
    private HashMap<Integer, Guest> guests = new HashMap<>();
    
    
    public GuestDaoJsonImpl() {
        final String GUEST_DIR_FILENAME = "Guests.json";
        this.GUEST_DIR_FILEPATH 
                = "../UserMessageApplication/src/main/resources/json/"
                + GUEST_DIR_FILENAME;
    }
    
    public GuestDaoJsonImpl(String guestDirectoryFilepath) {
        this.GUEST_DIR_FILEPATH = guestDirectoryFilepath;
    }
    
    
    /**
     * Returns a Guest if one exists with such id. 
     * @param id
     * @return Guest
     */
    @Override
    public Guest getGuest(int id) {
        loadGuests();
        return guests.get(id);
    }
    
    /**
     * Returns a List of all Guests, sorted by id.
     * @return 
     */
    @Override
    public List<Guest> getGuests() {
        loadGuests();
        // create a list from the guests map and sort it by id
        List<Guest> sortedGuests = new ArrayList(guests.values());
        sortedGuests.sort(Comparator.comparing((guest) -> guest.getId()));
        
        return sortedGuests;
    }

    
    /**
     * Loads the Guests from the JSON file into the HashMap by converting
     * the JSON objects into Java Guest objects. 
     */
    private void loadGuests() {
        
        try {
            JsonReader jsonReader 
                    = Json.createReader(new BufferedReader(
                            new FileReader(GUEST_DIR_FILEPATH)));
            
            JsonArray guestsArray = jsonReader.readArray();
            
            for (int i = 0; i < guestsArray.size(); i++) {
                JsonObject guestJsonObj = guestsArray.getJsonObject(i);
                Guest guest = mapJsonGuest(guestJsonObj);
                
                guests.put(guest.getId(), guest);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Guest directory could not be found.");
        }
    }

    /**
     * Maps an individual JSON Guest object into a Java Guest. 
     * @param jsonObjGuest
     * @return Guest
     */
    private Guest mapJsonGuest(JsonObject jsonObjGuest) {
        
        Guest guest = null;
        try {
            ObjectMapper guestMapper = new ObjectMapper();
            guest = guestMapper.readValue(
                    jsonObjGuest.toString(), Guest.class);
            
        } catch (JsonProcessingException e) {
            System.out.println("Error: Unable to process Guest from JSON.");
        }
        
        return guest;
    }

}
