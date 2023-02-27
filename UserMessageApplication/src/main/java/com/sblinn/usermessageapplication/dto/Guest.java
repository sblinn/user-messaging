

package com.sblinn.usermessageapplication.dto;

/**
 *
 * @author Sara Blinn
 */
public class Guest {
    
    private int id;
    
    private String firstName;
    
    private String lastName;
    
    private Reservation reservation;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    
    @Override
    public String toString() {
        return "Guest{" + "id=" + id + ", firstName=" + firstName 
                + ", lastName=" + lastName 
                + ", reservation=" + reservation.toString() + '}';
    }
    
}
