

package com.sblinn.usermessageapplication.dao;

import com.sblinn.usermessageapplication.dto.Company;
import java.util.List;

/**
 *
 * @author Sara Blinn
 */
public interface CompanyDao {
    
    // Create, update and delete operations ommitted as they were not 
    // necessary to meet requirements
    
    Company getCompany(int id);
    
    List<Company> getCompanies();
    
}
