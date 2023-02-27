package com.sblinn.usermessageapplication;


import com.sblinn.usermessageapplication.controller.UserMessageController;
import com.sblinn.usermessageapplication.dao.CompanyDao;
import com.sblinn.usermessageapplication.dao.CompanyDaoJsonImpl;
import com.sblinn.usermessageapplication.dao.GuestDao;
import com.sblinn.usermessageapplication.dao.GuestDaoJsonImpl;
import com.sblinn.usermessageapplication.dao.MessageTemplateDao;
import com.sblinn.usermessageapplication.dao.MessageTemplateDaoJsonImpl;
import com.sblinn.usermessageapplication.service.MessageService;
import com.sblinn.usermessageapplication.service.MessageServiceJsonImpl;
import com.sblinn.usermessageapplication.ui.UserIO;
import com.sblinn.usermessageapplication.ui.UserIOConsoleImpl;
import com.sblinn.usermessageapplication.ui.UserMessageView;



/**
 *
 * @author Sara Blinn
 */
public class UserMessageApplication {

    public static void main(String[] args) {
        
        UserIO io = new UserIOConsoleImpl();
        
        UserMessageView view = new UserMessageView(io);
        
        GuestDao guestDao = new GuestDaoJsonImpl();
        CompanyDao companyDao = new CompanyDaoJsonImpl();
        MessageTemplateDao templateDao = new MessageTemplateDaoJsonImpl();
        
        MessageService service 
                = new MessageServiceJsonImpl(
                        templateDao,
                        guestDao,
                        companyDao
                );
        
        UserMessageController controller 
                = new UserMessageController(service, view);
        
        controller.run();
        
        
        // TEST TIMEZONE GREETINGS
//        System.out.println("Greeting for Eastern Timezone: " 
//                + service.getGreeting(service.getCompany(5)));
//        
//        System.out.println("Greeting for Central Timezone: "
//                + service.getGreeting(service.getCompany(4)));
//        
//        System.out.println("Greeting for Indochina Timezone: "
//                + service.getGreeting(service.getCompany(6)));
        
        
        // CREATE A NEW MESSAGE TEMPLATE USING THE CORRECT PLACEHOLDERS
//        String message = "::timezoneGreeting:: ::guest.firstName:: "
//                + "::guest.lastName::, "
//                + "and welcome to ::company.company:: of ::company.city::! "
//                + "Your reservation begins at ::reservation.startTimestamp:: "
//                + "and ends on ::reservation.endTimestamp::.";
//        service.createMessageTemplate(message);
        
        
        // INPUT DESIRED MESSAGE TEMPLATE, GUEST AND COMPANY AND 
        // PRINT OUT THE MESSAGE WITH PLACEHOLDERS FILLED.
        // EXPECTED OUTPUT: 
        // Good morning Melisa Preston, and welcome to 
        // The Intercontinental Bangkok of Bangkok! Your reservation begins 
        // at Feb 9 2017  11:32 AM and ends on Feb 12 2017  12:02 AM.
//        String completedMessage 
//                = service.getMessage(templateDao.getMessageTemplate(1), 
//                        guestDao.getGuest(4), companyDao.getCompany(6));
//        
//        System.out.println(completedMessage);
        
        
    }
    
}
