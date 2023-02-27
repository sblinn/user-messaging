package com.sblinn.usermessageapplication.ui;

/**
 *
 * @author Sara Blinn
 */
public class UserMessageView {

    private UserIO io;

    public UserMessageView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("---User Messaging Menu---");
        io.print("1. Complete a Message Template");
        io.print("2. Create a Message Template");
        io.print("3. Exit");

        return io.readInt("Please enter the number from a selection above.", 1, 3);
    }

    public int getTemplateSelection() {
        return io.readInt("Please enter a message template id number.");
    }

    public int getGuestSelection() {
        return io.readInt("Please enter a guest id number.");
    }

    public int getCompanySelection() {
        return io.readInt("Please enter a company id number");
    }

    public void displayMessageResult(String msg) {
        io.print("Completed Message:");
        io.print(msg);
    }

    public String getMessageTemplateString() {

        io.print("---CREATE A NEW TEMPLATE--");
        io.print("Use the following placeholders in your template:");
        io.print("(surround the placeholder with :: on each end, i.e. "
                + "'::timezoneGreeting:: )");

        io.print("timezoneGreeting --> a greeting that is determined by "
                + "the time of day in the company timezone.");
        io.print("guest.firstName --> Guest first name.");
        io.print("guest.lastName --> Guest surname.");
        io.print("reservation.roomNumber --> Room number on the "
                + "guest reservation.");
        io.print("reservation.startTimestamp --> Reservation start date "
                + "and time, formatted from Epoch time in the JSON file.");
        io.print("reservation.endTimestamp --> Reservation end date and "
                + "time, formatted from Epoch time in the JSON file.");
        io.print("company.company --> Company name.");
        io.print("company.city --> City of company.");
        io.print("company.timezone --> Timezone that company "
                + "(and visiting guest) is in.");

        return io.readString("Enter a new message template.");
    }
    
    public void displayTemplateSuccessBanner() {
        io.print("MESSAGE TEMPLATE CREATED.");
    }

    public void displayContinuePrompt() {
        io.readString("Please hit ENTER to continue.");
    }

    public void displayUnknownCommandBanner() {
        io.print("UNKNOWN COMMAND");
    }

    public void displayExitBanner() {
        io.print("Good Bye.");
    }

}
