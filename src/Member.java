package src;

/**
 *
 * @author Hezehiah
 */
public class Member extends Basic {

    private String Fname;
    //Fname = Member first name
    private String Lname;
    //Lname = Member Last Name
    private String add;
    //add = Member Address
    private String major;
    //major = Member Major
    private int grad;
    //grad = Member Graduation Year
    private String job;
    //job = the Member's Job
    private String org;
    //org = any organization's the Member is in
    private double donation = 0;
    //holds the amount of money donated
    private final String[] memberVal = {"First Name", "Last Name", "Address", "Major", "Graduation Year", "Job", "Organization"};

    /**
     *
     */
    public Member() {
    }

    /**
     *
     * @return  the specific values associated with the guest speaker
     * events to the main class
     */
    @Override
    public String[] init() {
        return memberVal;
    }

    //setters
    /**
     * sets the values of all instance variables
     *
     * @param variables is a 2D array: 1: includes the index of the instance var
     * to be changed 2: includes the value to which the instance var must be set
     */
    @Override
    public void setVariables(String[][] variables) {
        for (int i = 0; i < variables.length; i++) {

            // Convert type String to int
            int idx = toInt(variables[i][0]);
            String value = variables[i][1];

            // Set matching variables
            switch (idx) {
                case 0:
                    this.Fname = value;
                    break;
                case 1:
                    this.Lname = value;
                    break;
                case 2:
                    this.add = value;
                    break;
                case 3:
                    this.major = value;
                    break;
                case 4:
                    this.grad = toInt(value);
                    break;
                case 5:
                    this.job = value;
                    break;
                case 6:
                    this.org = value;
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
        String[] report = new String[memberVal.length];
        // create extent 0 report (just name) or extent 1 report(all instance variables)
        switch (extent) {
            case 0:
                report[0] = this.Fname;
                break;
            case 1:
                report[0] = this.Fname;
                report[1] = this.Lname;
                report[2] = this.add;
                report[3] = this.major;
                report[4] = toString(this.grad);
                report[5] = this.job;
                report[6] = this.org;
                break;
            default:
                break;

        }
        return report;

    }

    /**
     * add amount member donates to instance var donation
     *
     * @return current amount member has donated in total
     */
    public double addDono(double dono) {
        donation += dono;
        return donation;
    }
}
