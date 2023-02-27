

package com.sblinn.usermessageapplication.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sblinn.usermessageapplication.dto.MessageTemplate;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

/**
 *
 * @author Sara Blinn
 */
public class MessageTemplateDaoJsonImpl implements MessageTemplateDao {

    private final String TEMPLATE_DIR_FILEPATH;
    
    // Map of Templates from the MessageTemplates.json file
    private HashMap<Integer, MessageTemplate> templates = new HashMap<>();
    
    
    public MessageTemplateDaoJsonImpl() {
        final String TEMPLATE_DIR_FILENAME = "MessageTemplates.json";
        this.TEMPLATE_DIR_FILEPATH 
                = "../UserMessageApplication/src/main/resources/json/" 
                + TEMPLATE_DIR_FILENAME;
    }
    
    public MessageTemplateDaoJsonImpl(String templateDirectoryFilepath) {
        this.TEMPLATE_DIR_FILEPATH = templateDirectoryFilepath;
    }
    

    /**
     * Returns a MessageTemplate, if one exists by the given id.
     * @param id
     * @return MessageTemplate
     */
    @Override
    public MessageTemplate getMessageTemplate(int id) {
        loadTemplates();
        return templates.get(id);
    }

    /**
     * Returns a List of MessageTemplates.
     * @return List
     */
    @Override
    public List<MessageTemplate> getAllMessageTemplates() {
        loadTemplates();
        // create a list from the templates map and sort it by id
        List<MessageTemplate> sortedTemplates = new ArrayList(templates.values());
        sortedTemplates.sort(Comparator.comparing((template) -> template.getId()));
        
        return sortedTemplates;
    }

    /**
     * Adds a message template to the JSON file.
     * @param messageTemplate 
     */
    @Override
    public void createMessageTemplate(MessageTemplate messageTemplate) {
        loadTemplates();
        templates.put(messageTemplate.getId(), messageTemplate);
        writeTemplates();
    }
    
    /**
     * Loads the message templates from the JSON file into the HashMap by
     * converting the JSON objects into Java MessageTemplate objects.
     */
    private void loadTemplates() {
        
        try {
            File file = new File(this.TEMPLATE_DIR_FILEPATH);
            JsonReader jsonReader
                    = Json.createReader(new BufferedReader(
                            new FileReader(file)));
            
            if (file.length() != 0) {
                JsonArray templatesArray = jsonReader.readArray();

                for(int i = 0; i < templatesArray.size(); i++) {
                    JsonObject templateJsonObj = templatesArray.getJsonObject(i);
                    MessageTemplate template 
                            = mapJsonToMessageTemplate(templateJsonObj);

                    templates.put(template.getId(), template);
                }
            
            }
            
            // Reader autocloses, this is just to be safe.
            jsonReader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Template directory could not be found.");
        }
    }
    
    /**
     * Writes the message templates from the HashMap to the JSON file.
     */
    private void writeTemplates() {
        
        try {
            File file = new File(this.TEMPLATE_DIR_FILEPATH);
            JsonWriter jsonWriter 
                = Json.createWriter(new PrintWriter(
                        new FileWriter(file)));
            
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            
            for(MessageTemplate template : templates.values()) {
                JsonObject jsonTemplate = mapMessageTemplateToJson(template);
                jsonArrayBuilder.add(jsonTemplate);
            }
            
            JsonArray jsonArray = jsonArrayBuilder.build();
            jsonWriter.write(jsonArray);
            jsonWriter.close();
            
        } catch (IOException e) {
            System.out.println("Error: Unable to write message "
                    + "templates to JSON file.");
        }
        
    }
    
    /**
     * Maps/converts a MessageTemplate object into a JsonObject.
     * @param template
     * @return JsonObject
     */
    private JsonObject mapMessageTemplateToJson(MessageTemplate template) {
        JsonObject jsonObj = Json.createObjectBuilder()
                .add("id", template.getId())
                .add("message", template.getMessage())
                .build();
        
        return jsonObj;
    }
    
    /**
     * Maps/converts an individual JSON message template object into a Java 
     * MessageTemplate.
     * @param jsonObjMessageTemplate
     * @return MessageTemplate
     */
    private MessageTemplate mapJsonToMessageTemplate(
            JsonObject jsonObjMessageTemplate) {
        
        MessageTemplate template = null;
        try {
            ObjectMapper templateMapper = new ObjectMapper();
            template = templateMapper.readValue(
                        jsonObjMessageTemplate.toString(), 
                        MessageTemplate.class);
        } catch (JsonProcessingException e) {
            System.out.println("Error: Unable to process Template from JSON.");
        }
        
        return template;
    }
    
}
