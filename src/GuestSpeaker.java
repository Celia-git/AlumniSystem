package src;

/**
 *
 * @author caleb
 */
public class GuestSpeaker extends Basic {

    private final String[] speakerVal = {"Speaker first name", "Speaker last name", "Topic", "Speaker Phone Number ", "Speaker Email ", "Speaker Event Date ", "Speaker Event Time", "Speaker Event Room"};
    private String speakerNameF;
    private String speakerNameL;
    private String speakerTopic;
    private String speakerPhoneNum;
    private String speakerEmail;
    private String speakerDate;
    private String speakerTime;
    private String speakerRoom;

    /**
     * default constructor for the guest speaker events
     */
    public GuestSpeaker() {
    }

    /**
     *
     * @return the specific values associated with the guest speaker
     * events to the main class
     */
    @Override
    public String[] init() {
        return speakerVal;
    }

    /**
     * sets the different parameters for the guest speaker events
     *
     * @param variables is a 2D array:
     *  1: includes the index of the instance var to be changed
     *  2: includes the value to which the instance var must be set
     */
    @Override
    public void setVariables(String[][] variables) {
        for (int i = 0; i < variables.length; i++) {
            String value = variables[i][1];
            switch (i) {
                case 0:
                    this.speakerNameF = value;
                    break;
                case 1:
                    this.speakerNameL = value;
                    break;
                case 2:
                    this.speakerTopic = value;
                    break;
                case 3:
                    this.speakerPhoneNum = value;
                    break;
                case 4:
                    this.speakerEmail = value;
                    break;
                case 5:
                    this.speakerDate = value;
                    break;
                case 6:
                    this.speakerTime = value;
                    break;
                case 7:
                    this.speakerRoom = value;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * displays a report of the guest speaker events
     *
     * @param extent describes length of report
     * @return array of values stored in instance variables
     */
    @Override
    public String[] createReport(int extent) {
        String[] report = new String[speakerVal.length];

        switch (extent) {
            case 0:
                report[0] = this.speakerNameF;
                report[1] = this.speakerNameL;
                break;
            case 1:
                report[0] = this.speakerNameF;
                report[1] = this.speakerNameL;
                report[2] = this.speakerTopic;
                report[3] = this.speakerPhoneNum;
                report[4] = this.speakerEmail;
                report[5] = this.speakerDate;
                report[6] = this.speakerTime;
                report[7] = this.speakerRoom;
                break;
            default:
                break;

        }
        return report;

    }
}
