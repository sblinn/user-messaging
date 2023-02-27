


package com.sblinn.usermessageapplication.dao;

import com.sblinn.usermessageapplication.dto.MessageTemplate;
import java.util.List;

/**
 *
 * @author Sara Blinn
 */
public interface MessageTemplateDao {
    
    // Only create and read methods written to meet requirements
    
    MessageTemplate getMessageTemplate(int id);
    
    List<MessageTemplate> getAllMessageTemplates();
    
    void createMessageTemplate(MessageTemplate messageTemplate);
    
}
