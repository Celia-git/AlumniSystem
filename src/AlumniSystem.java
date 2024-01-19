package src;

import java.util.*;
import static java.lang.String.*;

/**
 *
 * @author celia
 */
public class AlumniSystem {

    /*Instance Variables:
    // Create an arrraylist in which to hold an array of each category of objects
     */
    private static ArrayList memberList = new ArrayList<>();
    private static ArrayList trainingList = new ArrayList<>();
    private static ArrayList eventList = new ArrayList<>();
    private static ArrayList speakerList = new ArrayList<>();

    /**
     *
     * @param args main method takes no arguments
     */
    public static void main(String[] args) {

        // arrays: store input instructions
        String[] categories = {"Member", "Training", "Event", "Guest Speaker", "Exit"};
        String[] commands = {"Add", "Edit", "Delete", "Info", "Back"};

        //while (getCategory<0 || getCategory>categories.length)
        categoryLoop:
        while (true) {
            // Print commands -- prompt user for category
            System.out.println("Which category are you updating?");
            for (int i = 0; i < categories.length; i++) {
                System.out.println("\tEnter " + i + " for " + categories[i]);
            }

            // Take input
            Scanner in = new Scanner(System.in);
            int getCategory = -1;
            try {
                getCategory = in.nextInt();
            } catch (Exception InputMismatchException) {
                System.out.println("Wrong entry type. Must be a number");
                break;
            }

            // Validate input
            if (getCategory >= 0 && getCategory < categories.length) {
                if (getCategory != 4) {
                    int getCommand = -1;
                    commandLoop:
                    do {
                        // Next prompt: add/edit/delete/info
                        System.out.println("Select an action to take for " + categories[getCategory]);
                        for (int i = 0; i < commands.length; i++) {
                            System.out.println("\tEnter: " + i + " --> " + commands[i]);
                        }
                        // Take input
                        try {
                            getCommand = in.nextInt();
                        } catch (Exception InputMismatchException) {
                            System.out.println("Wrong entry type. Must be a number");
                            break categoryLoop;
                        }

                        // Validate input
                        if (getCommand >= 0 && getCommand < commands.length) {
                            // Respond to command
                            switch (getCommand) {
                                case 0:
                                    addObj(categories[getCategory]);
                                    break;
                                case 1:
                                    editObj(categories[getCategory]);
                                    break;
                                case 2:
                                    deleteObj(categories[getCategory]);
                                    break;
                                case 3:
                                    printReport(categories[getCategory], 1);
                                    break;
                                // Return to prompt for categoryEntry
                                default:
                                    break commandLoop;
                            }

                        }

                    } while (getCommand < 0 || getCommand >= commands.length);

                } else {
                    break;
                }
                // Repeat prompt
                System.out.println("Continue? \ty/n");
                if (!in.next().equalsIgnoreCase("y")) {
                    break;
                }
            }
        }
    }

    /**
     *
     * @param category is String representing object type, ie: 'Member'
     */
    @SuppressWarnings("unchecked")
    public static void addObj(String category) {
        // Create new object, upcast as basic class 
        Basic obj = null;
        switch (category) {
            case "Member":
                Member mem = new Member();
                obj = (Basic) mem;
                break;
            case "Training":
                Training train = new Training();
                obj = (Basic) train;
                break;
            case "Event":
                Event event = new Event();
                obj = (Basic) event;
                break;
            case "Guest Speaker":
                GuestSpeaker guest = new GuestSpeaker();
                obj = (Basic) guest;
                break;
        }

        // Assign a value to new object, store in appropriate ArrayList
        ArrayList objects = getList(category);
        objects.add(obj);

        // Prompt user input
        String[] values = obj.init();
        int len = values.length;
        System.out.println("Enter values. Type !skip to skip a value\n");

        // Set all object values
        String varSets[][] = new String[len][2];
        Scanner scn = new Scanner(System.in);

        for (int i = 0; i < len; i++) {
            System.out.println("Enter value for: " + values[i]);

            String input = valueOf(scn.next());
            String idx = valueOf(i);

            if (input.equalsIgnoreCase("!skip")) {
                input = "";
            }
            varSets[i][0] = idx;
            varSets[i][1] = input;
        }

        obj.setVariables(varSets);

        // Add confirmation message with object information
        String[] confirmation = obj.createReport(1);
        System.out.println("New " + category + " created: " + confirmation[0]);
        for (int i = 0; i < values.length; i++) {
            System.out.println(String.format("\t %s:\t%-5s", values[i], confirmation[i]));
            //System.out.println("\t" + values[i] + ":\t" + confirmation[i]);
        }

        // Check if obj is Member subclass, then call memberParticipate
        if (obj instanceof Member) {
            memberParticipate((Member) obj);
        }

    }

    /**
     *
     * @param category is String representing object type, ie: 'Member'
     */
    public static void editObj(String category) {
        // Find an object to edit
        while (true) {

            // Get the user's choice of object to edit
            Basic currentObj;
            try {
                currentObj = getObj(category);
            } catch (Except Except) {
                break;
            }

            // 2D array attributes: [0] names, [1] values
            String[][] attributes = {currentObj.init(), currentObj.createReport(1)};

            System.out.println(category + ": " + attributes[1][0]);

            // Iterate all attributes of object: names and values
            for (int j = 0; j < attributes[0].length; j++) {
                System.out.println(String.format("%d- %s : %s", j, attributes[0][j], attributes[1][j]));
            }

            // Create array for storing new attribute values
            String[][] newAttribute = new String[attributes[0].length][2];
            int newAttributeSize = 0;

            // Prompt user to set new attribute:
            while (true) {
                int attributeIdx = -1;
                do {
                    System.out.println("Enter the number of the value you wish to change");
                    Scanner nameIn = new Scanner(System.in);
                    try {
                        attributeIdx = nameIn.nextInt();
                    } catch (Exception InputMismatchException) {
                    }

                } while (attributeIdx < 0 || attributeIdx >= attributes[0].length);

                Scanner valueIn = new Scanner(System.in);
                System.out.println(String.format("New %s for %s: ", attributes[0][attributeIdx], attributes[1][0]));
                String attributeVal = valueIn.next();

                // Add new values to newAttribute array
                newAttribute[newAttributeSize][0] = valueOf(attributeIdx);
                newAttribute[newAttributeSize][1] = attributeVal;
                newAttributeSize++;

                // Reprompt
                System.out.println(String.format("Continue editing %s? \ty/n", attributes[1][0]));
                Scanner confirmContinue = new Scanner(System.in);
                if (!confirmContinue.next().equalsIgnoreCase("y")) {
                    break;
                }
            }
            // update and display updated object
            String[][] listAttributes = Arrays.copyOf(newAttribute, newAttributeSize);
            currentObj.setVariables(listAttributes);
            System.out.println(String.format("Updated %s '%s'!", category, attributes[1][0]));
            String[] display = currentObj.createReport(1);
            for (String val : display) {
                System.out.println("\t" + val);
            }

            // Check if obj is Member subclass, then call memberParticipate
            if (currentObj instanceof Member) {
                memberParticipate((Member) currentObj);
            }

            // Reprompt
            System.out.println(String.format("Select another %s to edit? \ty/n", category));
            Scanner confirmRepeat = new Scanner(System.in);
            if (!confirmRepeat.next().equalsIgnoreCase("y")) {
                break;
            }

        }

    }

    /**
     *
     * @param category is String representing object type, ie: 'Member'
     */
    public static void deleteObj(String category) {
        // Find an object to delete
        while (true) {

            // Get the user's choice of object to edit
            Basic currentObj;
            try {
                currentObj = getObj(category);
            } catch (Except Except) {
                break;
            }

            // Confirm delete
            String name = currentObj.createReport(0)[0];
            System.out.println(String.format("Delete %s: %s? \ty/n", category, name));
            Scanner in = new Scanner(System.in);

            // If user confirms delete, remove object
            if (in.next().equalsIgnoreCase("y")) {
                getList(category).remove(currentObj);
            }

            // Re-prompt
            printReport(category, 0);
            System.out.println(String.format("Delete another %s?\ty/n", category));
            // Break if no
            if (!in.next().equalsIgnoreCase("y")) {
                break;
            }
            printReport(category, 0);
        }

    }

    /**
     * Prints values associated with instance variables,
     *
     * @param category is String representing object type, ie: 'Member'
     * @param extent specifies length of the report 0 denotes only names 1
     * denotes all instance variables associated with an object
     * @return Array of String values: value associated with instance's 'name'
     * variable in each instance of class
     */
    public static String[] printReport(String category, int extent) {

        ArrayList<Basic> list = getList(category);
        String[] nameReport = new String[list.size()];

        // Confirm that list is not empty
        if (list.size() >= 1) {
            System.out.println("All the current " + category + "s: ");
        } else {
            System.out.println("There are no " + category + "s");
        }

        // Repeat for each instance in Array
        for (int i = 0; i < list.size(); i++) {
            // Store report, variable names in an array of strings
            String[] report = list.get(i).createReport(extent);
            String[] varNames = list.get(i).init();

            // Print name of object, add to report
            System.out.println(String.format("%s %d: %s", category, i, report[0]));
            nameReport[i] = report[0];
            // if extended report is warranted, print all other info associated with instance
            if (extent == 1) {
                for (int j = 0; j < report.length; j++) {
                    System.out.println(String.format("\t%s: \t%s", varNames[j], report[j]));
                }
            }
        }
        // return value: report values from each instance
        return nameReport;
    }

    /**
     *
     * @param category is String representing object type, ie: 'Member'
     * @return appropriate ArrayList containing all instances of Class
     */
    public static ArrayList<Basic> getList(String category) {
        @SuppressWarnings("unchecked")
        ArrayList<Basic> tempList = new ArrayList();

        // Create array list of objects: specify object type
        switch (category) {
            case "Member":
                @SuppressWarnings("unchecked") ArrayList<Basic> list0 = memberList;
                return list0;
            case "Training":
                @SuppressWarnings("unchecked") ArrayList<Basic> list1 = trainingList;
                return list1;
            case "Event":
                @SuppressWarnings("unchecked") ArrayList<Basic> list2 = eventList;
                return list2;
            case "Guest Speaker":
                @SuppressWarnings("unchecked") ArrayList<Basic> list3 = speakerList;
                return list3;
            default:
                break;
        }

        // Default return value: empty list
        return tempList;
    }

    /**
     *
     * @param category is String representing object type, ie: 'Member'
     * @return specific object of type specified in param
     * @throws Except if object specified is not found or if there are no
     * objects of the given type
     */
    public static Basic getObj(String category) throws Except {

        ArrayList<Basic> list = getList(category);

        // Show names of all objects in category
        String[] instanceNames = printReport(category, 0);

        // check if there are any objects of type
        if (instanceNames.length != 0) {

            // prompt user to enter the name or place of an object
            System.out.println("Enter a " + category + " name or its index");
            Scanner in = new Scanner(System.in);
            String input = in.next();

            // find object value in ArrayList whose name or index matches user input
            for (int i = 0; i < instanceNames.length; i++) {
                // Compare input to instance names
                if (input.equals(valueOf(i)) || input.equalsIgnoreCase(instanceNames[i])) {
                    return list.get(i);
                }
            }
        } else {
            throw new Except(category + " list empty");
        }

        // Default -- return exception if objct not found
        throw new Except(category + " not found");
    }

    /**
     * When adding or editing a member: prompt for donations; print current
     * amount donated invite to take a role at an event or training
     *
     * @param mem is a Member object which may add donation or participate in
     * event
     */
    public static void memberParticipate(Member mem) {
        String name = mem.createReport(0)[0];
        /*
        prompt  for donos
         */
        System.out.println(String.format("Add donation for %s? \ty/n", name));
        Scanner in = new Scanner(System.in);
        if (in.next().equalsIgnoreCase("y")) {
            // Prompt user to enter donation amount
            System.out.println("Enter a donation amount");
            double dono = -1.0;
            do {
                Scanner amtIn = new Scanner(System.in);
                try {
                    dono = amtIn.nextDouble();
                } catch (Exception InputMismatchException) {
                    System.out.println("Not a number");
                }
            } while (dono < 0);

            // Confirm donation amount
            System.out.println(String.format("Add a donation of $%.2f for %s? \ty/n", dono, name));
            if (in.next().equalsIgnoreCase("y")) {
                System.out.println("Thanks!");
                System.out.println(String.format("%.2f is %s's total donation amount", mem.addDono(dono), name));
            } else {
                System.out.println(String.format("No donation added for %s", name));
            }
        }

        /* Prompt to play a role in event or training */
        String[] eventraining = {"Event", "Training"};

        // Repeat for events and trainings
        for (int i = 0; i < 2; i++) {
            // Check if there are currently events or trainings
            if (printReport(eventraining[i], 1).length > 0) {

                System.out.println(String.format("Would you like to participate in any of these %ss?\ty/n", eventraining[i]));
                if (in.next().equalsIgnoreCase("y")) {
                    // Prompt user to enter the specific training or event
                    Basic obj = null;
                    while(obj == null){
                        try {
                            obj = getObj(eventraining[i]);
                        } catch (Except Except) {
                            System.out.println(String.format("Quit searching for a %s?\ty/n", eventraining[i]));
                            Scanner e = new Scanner(System.in);
                            String exit = e.next();
                            if(exit.equalsIgnoreCase("y")){
                                return;
                            }
                        }

                    }

                    String objName = obj.createReport(0)[0];
                    // Display all roles:
                    System.out.println(obj.getRole());

                    // Prompt to enter a role
                    System.out.println(String.format("Enter a role for %s to do in %s\n\tOr enter '!skip' to exit", name, objName));

                    while (true) {
                        Scanner roleIn = new Scanner(System.in);
                        String response = roleIn.next();
                        
                        if (response.equals("!skip")) {
                            // User skips this part
                            break;
                            
                        } else if (obj.setRole(name, response)) {
                            // User successfully applies for role
                            System.out.println("Updated "+objName);
                            obj.getRole();
                            break;
                        } else {
                            // User is denied
                            if(response.equals("attendee")){
                                // User attempted to sign up for a full class
                                System.out.println(String.format("%s is full of %ss", objName, response));
                            }
                            else{
                                // User entered invalid input
                                System.out.println(String.format("%s is invalid role", response));
                            }
                        }

                    }
                }
            }
        }

    }

}
