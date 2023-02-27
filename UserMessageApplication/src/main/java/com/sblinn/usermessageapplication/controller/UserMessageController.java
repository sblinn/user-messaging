package com.sblinn.usermessageapplication.controller;

import com.sblinn.usermessageapplication.service.MessageService;
import com.sblinn.usermessageapplication.ui.UserMessageView;

/**
 *
 * @author Sara Blinn
 */
public class UserMessageController {

    private final MessageService service;

    private final UserMessageView view;

    
    public UserMessageController(MessageService service,
            UserMessageView view) {

        this.service = service;
        this.view = view;
    }

    public void run() {

        boolean keepGoing = true;

        try {
            while (keepGoing) {
                int menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        completeTemplate();
                        break;
                    case 2:
                        createTemplate();
                        break;
                    case 3:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();

        } catch (Exception e) {
            System.out.println("ERROR ENCOUNTERED.");
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void completeTemplate() {
        int templateId = view.getTemplateSelection();
        int guestId = view.getGuestSelection();
        int companyId = view.getCompanySelection();
        
        String message = service.getMessage(
                service.getMessageTemplate(templateId), 
                service.getGuest(guestId), 
                service.getCompany(companyId));
        
        view.displayMessageResult(message);
        view.displayContinuePrompt();
    }
    
    private void createTemplate() {
        String template = view.getMessageTemplateString();
        service.createMessageTemplate(template);
        view.displayTemplateSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
