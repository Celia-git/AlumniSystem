package src;

import java.util.*;

/**
 * @author newman0179
 */
public class Training extends Basic {

    private String name;
    private String presenter;
    private String presenterL;
    private int seats = 100;
    private String date;
    private String time;
    private String duration;
    private String location;
    private ArrayList<String> attendee = new ArrayList();
    private ArrayList<String> donor = new ArrayList();
    private ArrayList<String> host = new ArrayList();

    private final String[] trainingVal = {"Training Name ", "Instructor First Name ", "Instructor Last Name ", "Remaining seats ", "Date ", "Time ", "Duration of session", "Room number"};

    /**
     * Default Constructor
     */
    public Training() {

    }

    /**
     *
     * @return  the specific values associated with the guest speaker
     * events to the main class
     */
    @Override
    public String[] init() {

        return trainingVal;
    }

    /**
     * sets the values of all instance variables
     *
     * @param variables is a 2D array: 1: includes the index of the instance var
     * to be changed 2: includes the value to which the instance var must be set
     */
    @Override
    public void setVariables(String[][] variables) {
        // Loop all values in the 2D array
        for (String[] variable : variables) {
            // Save current index and value in variables idx, value
            int idx = toInt(variable[0]); // Convert type String to int
            String value = variable[1];
            // Set matching variables
            switch (idx) {
                case 0:
                    this.name = value;
                    break;
                case 1:
                    this.presenter = value;
                    break;
                case 2:
                    this.presenterL = value;
                    break;
                case 3:
                    this.seats = toInt(value);
                    break;
                case 4:
                    this.date = value;
                    break;
                case 5:
                    this.time = value;
                    break;
                case 6:
                    this.duration = value;
                    break;
                case 7:
                    this.location = value;
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
        String[] report = new String[trainingVal.length];
        // create extent 0 report (just name) or extent 1 report(all instance variables)
        switch (extent) {
            case 0:
                report[0] = this.name;
                break;
            case 1:
                report[0] = this.name;
                report[1] = this.presenter;
                report[2] = this.presenterL;
                report[3] = toString(seats);
                report[4] = this.date;
                report[5] = this.time;
                report[6] = this.duration;
                report[7] = this.location;
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
     * 
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
        String values = String.format("\t%s", this.name);
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
