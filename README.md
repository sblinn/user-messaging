# user-messaging
 

RUN INSTRUCTIONS:
-------------------------------------------------------------------------------------------------

Runs from UserMessageApplication class.

1.) Run application from UserMessageApplication class -- options will print to console and you will be able to enter in options following instructions from the command prompt. 

2.) If you enter the number 1, you can fill in an existing message template using the id values for message templates, guests and companies. 

3.) If you enter the number 2, you can create create a message template, utilizing the following placeholders with delimiters. 

PLACEHOLDERS AVAILABLE: 

(separate with delimiters "::" at start and end, ex. "Hello, ::guest.firstName::")

"timezoneGreeting" --> a greeting that is determined by the time of day in the company timezone.
"guest.firstName" --> Guest first name.
"guest.lastName" --> Guest surname.
"reservation.roomNumber" --> Room number on the guest reservation.
"reservation.startTimestamp" --> Reservation start date and time, formatted from Epoch time in the JSON file.
"reservation.endTimestamp" --> Reservation end date and time, formatted from Epoch time in the JSON file.
"company.company" --> Company name.
"company.city" --> City of company.
"company.timezone" --> Timezone that company (and visiting guest) is in.

Example:

		"::timezoneGreeting:: ::guest.firstName:: "
                + "::guest.lastName::, "
                + "and welcome to ::company.company:: of ::company.city::! "
                + "Your reservation begins at ::reservation.startTimestamp:: "
                + "and ends on ::reservation.endTimestamp::."



LANGUAGE & DESIGN OVERVIEW:
-------------------------------------------------------------------------------------------------

I chose Java because I love Java. I went back and forth on the decision to use Java or JavaScript since that would make the most sense with JSON being used to store the data, but I knew Java had libraries for handling JSON and I had existing projects that I could use to build off of for this concept. Given the time constraints, I also wanted to use Java for it's strong typing. With JavaScript, I might have made a minor error and it would have taken a long time to find the source.

The way it is designed, it could be modified and used for other implementations. I utilized parts from another project I wrote and modified them to build the simple console UI.

Given the short amount of time to complete the project, it does not have many checks in place for potential errors (i.e. if a user does not correctly use placeholders), though there are some. If the user inputs an id value that does not exist, I allow the program to print an error message and end the program.

With more time, I would have made the UI a bit easier to use by at least showing the options that were tied to the id values and added more checks for invalid input and string contents. 
