

package com.sblinn.usermessageapplication.dto;

/**
 *
 * @author Sara Blinn
 */
public class Reservation {

    private int roomNumber;
    
    // timestamps are Epoch time
    private long startTimestamp;
    
    private long endTimestamp;

    
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    
    @Override
    public String toString() {
        return "Reservation{" + "roomNumber=" + roomNumber 
                + ", startTimestamp=" + startTimestamp 
                + ", endTimestamp=" + endTimestamp + '}';
    }
    
}
