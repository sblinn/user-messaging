


package com.sblinn.usermessageapplication.service;

import com.sblinn.usermessageapplication.dto.Company;
import com.sblinn.usermessageapplication.dto.Guest;
import com.sblinn.usermessageapplication.dto.MessageTemplate;
import java.util.List;

/**
 *
 * @author Sara Blinn
 */
public interface MessageService {
    
    String getGreeting(Company company);
    
    String getMessage(MessageTemplate messageTemplate, 
            Guest guest, Company company);
    
    MessageTemplate getMessageTemplate(int id);
    
    List<MessageTemplate> getMessageTemplates();
    
    void createMessageTemplate(String message);
    
    
    Guest getGuest(int id);
    
    List<Guest> getGuests();
    
    Company getCompany(int id);
    
    List<Company> getCompanies();
    
}
