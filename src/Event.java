package src;

import static java.lang.Character.*;
import static java.lang.String.*;
import java.util.*;

/**
 *
 * @author treil
 */
public class Event extends Basic {

    private String eventName;
    private String eventDate;
    private int seats;
    private String eventLocation;
    private int eventParticipants;
    private ArrayList<String> attendee = new ArrayList();
    private ArrayList<String> donor = new ArrayList();
    private ArrayList<String> host = new ArrayList();

    String[] eventVal = {"Name", "Date", "Location", "Participants"};

    /**
     *
     */
    public Event() {

    }

    /**
     *
     * @return the specific values associated with the guest speaker
     * events to the main class
     */
    @Override
    public String[] init() {
        return eventVal;
    }

    /**
     *
     * @param variables is a 2D array:
     */
    @Override
    public void setVariables(String[][] variables) {
        for (String[] variable : variables) {
            int idx = toInt(variable[0]);
            String value = variable[1];
            switch (idx) {
                case 0:
                    this.eventName = value;
                    break;
                case 1:
                    this.eventDate = value;
                    break;
                case 2:
                    this.eventLocation = value;
                    break;
                case 3:
                    this.eventParticipants = toInt(value);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     *
     * @param extent describes length of report
     * @return array of values stored in instance variables
     */
    @Override
    public String[] createReport(int extent) {
        String[] report = new String[eventVal.length];
        switch (extent) {
            case 0:
                report[0] = this.eventName;
                break;
            case 1:
                report[0] = this.eventName;
                report[1] = this.eventDate;
                report[2] = this.eventLocation;
                report[3] = toString(this.eventParticipants);
                break;
            default:
                break;
        }
        return report;
    }

 
    /**
     *
     * @return true if there are enough seats, false if no
     */
    public boolean deSeats() {
        if (seats >= 1) {
            seats--;
            return true;
        }
        return false;
    }

    /**
     *
     * @param memberName is the name associated with the member signing up for a
     * role
     * @param role represents attendee, donor, or host
     * @return boolean true if sign up was successful, false if no
     */
    @Override
    public boolean setRole(String memberName, String role) {
        switch (role) {
            case "attendee":
                if(deSeats()){
                    attendee.add(memberName);
                    return true;
                }
                return false;
            case "donor":
                donor.add(memberName);
                return true;
            case "host":
                host.add(memberName);
                return true;
            default:
                break;
        }
        return false;
    }

    /**
     *
     * @return a formatted string of all values stored in training roles
     */
    @Override
    public String getRole() {
        String values = String.format("\t%s", this.eventName);
        values += "\nAttendees:\t";
        if (attendee.size()>0){
            for (String a: attendee) {
                values += a;
                values += ", ";
            }
        }
        else{
            values += "none";
        }

        values += "\nDonors:\t";
        if (donor.size()>0){
            for (String a: donor) {
                values += a;
                values += ", ";
            }
        }
        else{
            values += "none";
        }

        values += "\nHosts:\t";
        if (host.size()>0){
            for (String a: host) {
                values += a;
                values += ", ";
            }
        }
        else{
            values += "none";
        }
        return values;
    }
}
