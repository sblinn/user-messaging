


package com.sblinn.usermessageapplication.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sblinn.usermessageapplication.dto.Company;
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
public class CompanyDaoJsonImpl implements CompanyDao {

    private final String COMPANY_DIR_FILEPATH;
    
    // Map of Companies from the Companies.json file
    private HashMap<Integer, Company> companies = new HashMap<>();
    
    
    public CompanyDaoJsonImpl() {
        final String COMPANY_DIR_FILENAME = "Companies.json";
        this.COMPANY_DIR_FILEPATH
                = "../UserMessageApplication/src/main/resources/json/"
                + COMPANY_DIR_FILENAME;
    }
    
    public CompanyDaoJsonImpl(String companyDirectoryFilepath) {
        this.COMPANY_DIR_FILEPATH = companyDirectoryFilepath;
    }
    
    
    /**
     * Returns a Company if one exists with such id.
     * @param id
     * @return Company
     */
    @Override
    public Company getCompany(int id) {
        loadCompanies();
        return companies.get(id);
    }
    
    /**
     * Returns a List of all companies, sorted by id.
     * @return List
     */
    @Override
    public List<Company> getCompanies() {
        loadCompanies();
        // create a list from the companies map and sort it by id
        List<Company> sortedCompanies = new ArrayList(companies.values());
        sortedCompanies.sort(Comparator.comparing((company) -> company.getId()));
        
        return sortedCompanies;
    }
    
    
    /**
     * Loads the Companies from the JSON file into the HashMap by 
     * converting the JSON objects into Java Company objects.
     */
    private void loadCompanies() {
        
        try {
            JsonReader jsonReader 
                    = Json.createReader(new BufferedReader(
                            new FileReader(COMPANY_DIR_FILEPATH)));
            
            JsonArray companiesArray = jsonReader.readArray();
            
            for (int i = 0; i < companiesArray.size(); i++) {
                JsonObject companyJsonObj = companiesArray.getJsonObject(i);
                Company company = mapJsonCompany(companyJsonObj);
                
                companies.put(company.getId(), company);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Company directory could not be found.");
        }
    }
    
    /**
     * Maps an individual JSON Company object into a Java Company.
     * @param jsonObjCompany
     * @return Company
     */
    private Company mapJsonCompany(JsonObject jsonObjCompany) {
        
        Company company = null;
        try {
            ObjectMapper companyMapper = new ObjectMapper();
            company = companyMapper.readValue(
                    jsonObjCompany.toString(), Company.class);
            
        } catch (JsonProcessingException e) {
            System.out.println("Error: Unable to process Company from JSON.");
        }
        
        return company;
    }

}
