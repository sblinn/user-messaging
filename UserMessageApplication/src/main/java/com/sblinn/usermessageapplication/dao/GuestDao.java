

package com.sblinn.usermessageapplication.dao;

import com.sblinn.usermessageapplication.dto.Guest;
import java.util.List;

/**
 *
 * @author Sara Blinn
 */
public interface GuestDao {

    // Create, update and delete operations ommitted as they were not 
    // necessary to meet requirements
    
    Guest getGuest(int id);
    
    List<Guest> getGuests();
    
}
