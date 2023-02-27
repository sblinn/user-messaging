


package com.sblinn.usermessageapplication.service;

import com.sblinn.usermessageapplication.dao.CompanyDao;
import com.sblinn.usermessageapplication.dao.GuestDao;
import com.sblinn.usermessageapplication.dao.MessageTemplateDao;
import com.sblinn.usermessageapplication.dto.Company;
import com.sblinn.usermessageapplication.dto.Guest;
import com.sblinn.usermessageapplication.dto.MessageTemplate;
import com.sblinn.usermessageapplication.dto.Reservation;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Sara Blinn
 */
public class MessageServiceJsonImpl implements MessageService {

    private final MessageTemplateDao messageTemplateDao;
    
    private final GuestDao guestDao;
            
    private final CompanyDao companyDao;
    
    
    // Delimiter for identifying placeholders for variables
    private static final String DELIMITER = "::";
    
    
    public MessageServiceJsonImpl(MessageTemplateDao messageTemplateDao, 
            GuestDao guestDao, CompanyDao companyDao) {
        
        this.messageTemplateDao = messageTemplateDao;
        this.guestDao = guestDao;
        this.companyDao = companyDao;
    }
    
    
    @Override
    public String getGreeting(Company company) {
        // determine greeting based on time of day -> provided by Company(timezone).
        String companyTimezone = company.getTimezone();
        
        ZoneId zoneId = ZoneId.of(companyTimezone);
        ZonedDateTime zonedDateTime 
                = ZonedDateTime.ofInstant(Instant.now(), zoneId);
        
        int hour = zonedDateTime.getHour();
        
        if (hour >= 1 && hour < 12) {
            return "Good morning";
        } else if (hour >= 12 && hour < 18) {
            return "Good afternoon";
        } else {
            return "Good evening";
        }
        
    }
    
    @Override
    public String getMessage(MessageTemplate messageTemplate,
            Guest guest, Company company) {
        
        String message = messageTemplate.getMessage();
        
        Reservation reservation = guest.getReservation();
        
        DateTimeFormatter formatter 
                = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");
        ZoneId zoneId = ZoneId.of(company.getTimezone());
        Instant reservationStartInstant 
                = Instant.ofEpochSecond(reservation.getStartTimestamp());
        Instant reservationEndInstant 
                = Instant.ofEpochSecond(reservation.getEndTimestamp());
        
        Map<String, Object> params = new HashMap<>();
        
        params.put("timezoneGreeting", getGreeting(company));
        params.put("guest.firstName", guest.getFirstName());
        params.put("guest.lastName", guest.getLastName());
        params.put("reservation.roomNumber", reservation.getRoomNumber());
        params.put("reservation.startTimestamp", 
                LocalDateTime
                        .ofInstant(reservationStartInstant, zoneId)
                        .format(formatter));
        params.put("reservation.endTimestamp", 
                LocalDateTime
                        .ofInstant(reservationEndInstant, zoneId)
                        .format(formatter));
        params.put("company.company", company.getCompany());
        params.put("company.city", company.getCity());
        params.put("company.timezone", company.getTimezone());
        
        
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String placeholder = entry.getKey();
            Object value = entry.getValue();
            
            String toReplace = this.DELIMITER + placeholder + this.DELIMITER;
            message = message.replaceAll(toReplace, value.toString());
        }
        
        return message;
    }
    
    @Override
    public MessageTemplate getMessageTemplate(int id) {
        return messageTemplateDao.getMessageTemplate(id);
    }

    @Override
    public List<MessageTemplate> getMessageTemplates() {
        return messageTemplateDao.getAllMessageTemplates();
    }

    @Override
    public void createMessageTemplate(String message) {
        List<MessageTemplate> templates = getMessageTemplates();
        
        Integer maxId = 0;
        if (!templates.isEmpty()) {
            maxId = templates
                    .stream()
                    .mapToInt(template -> template.getId())
                    .max()
                    .getAsInt();
        }
        
        MessageTemplate template = new MessageTemplate();
        template.setId(maxId + 1);
        template.setMessage(message);
        
        messageTemplateDao.createMessageTemplate(template);
    }
    
    
    @Override
    public Guest getGuest(int id) {
        return guestDao.getGuest(id);
    }

    @Override
    public List<Guest> getGuests() {
        return guestDao.getGuests();
    }
    
    @Override
    public Company getCompany(int id) {
        return companyDao.getCompany(id);
    }

    @Override
    public List<Company> getCompanies() {
        return companyDao.getCompanies();
    }

}
