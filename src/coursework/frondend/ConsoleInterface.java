package coursework.frondend;

import coursework.backend.SmartHome;

import java.util.Scanner;

public class ConsoleInterface {
    private SmartHome home;

    public void attachServerObject(SmartHome home) {this.home = home;}
    public void displayDashboard(){
        out(home.displayDashboard());
    }

    public int getInt(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextInt();
    }

    public String getString(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.next();
    }

    //Public methods
    public void populateRooms(int totalRooms) {
        for (int i = 0; i < totalRooms; i++) {
            String roomName = getString("Please provide a name for your room " + (i+1) + ": ");
            home.appendRooms(roomName);
        }
    }

    public void populateDevices() {
        for (int i = 0; i < home.getPlugSize(); i++) {
            out("ENTER PLUG INFORMATION BELOW\n");
            out("ROOMS AVAILABLE: " + home.displayRooms());
            int roomID = getInt("Using the above list, please select the room for this" +
                    " plug (integer only): ") - 1;
            out("AVAILABLE DEVICE LIST OPTIONS\n" + home.displayDevices());
            int deviceID = getInt("Using the above list, please select the device to attach" +
                    " to the smart plug (integer only): ") - 1;
            home.appendPlugs(deviceID, roomID);
        }
    }

    public void houseLevelOptions() {
        out("""
                HOUSE LEVEL OPTIONS
                1 - Switch all plugs off
                2 - Switch all plugs on
                3 - Back to Main Menu""");
        int houseOptions  = getInt("Select an option");

        switch (houseOptions) {
            case 1:
                home.allPlugsOff();
                displayDashboard();
                break;
            case 2:
                home.allPlugsOn();
                displayDashboard();
                break;
            case 3:
                break;
        }
    }

    public void roomLevelOne(){
        out("ROOMS AVAILABLE: " + home.displayRooms());
        int roomID = getInt("Please select room (integer only)") -1;
        home.allRoomsOff(roomID);
        displayDashboard();
    }
    public void roomLevelTwo(){
        out("ROOMS AVAILABLE: " + home.displayRooms());
        int roomID = getInt("Please select room (integer only)") - 1;
        home.allRoomsOn(roomID);
        displayDashboard();
    }
    public void roomLevelThree(){
        out("ROOMS AVAILABLE: " + home.displayRooms());
        int roomID = getInt("Please select room (integer only)") - 1;
        out(home.displayDevicesInRoom(roomID));
        int deviceID = getInt("Please select device ID (integer only)") - 1;
        home.toggleDevice(deviceID);
        displayDashboard();
    }

    public void roomLevelOptions() {
        out("""
                ROOM LEVEL OPTIONS
                1 - Switch all plugs off in room
                2 - Switch all plugs on in room
                3 - Select a plug in the room and toggle its on/off
                4 - Back to Main Menu""");

        int roomOptions = getInt("Select an option");
        switch(roomOptions) {
            case 1:
                roomLevelOne();
                break;
            case 2:
                roomLevelTwo();
                break;
            case 3:
                roomLevelThree();
                break;
            case 4:
                break;
        }
    }

    public void plugLevelOne(){
        out("\n" + home.displayPlugs());
        int deviceID = getInt("Please select device ID (integer only)") - 1;
        home.plugOff(deviceID);
        displayDashboard();
    }
    public void plugLevelTwo(){
        out("\n" + home.displayPlugs());
        int deviceID = getInt("Please select device ID (integer only)") - 1;
        home.plugOn(deviceID);
        displayDashboard();
    }
    public void plugLevelThree(){
        out(home.displayPlugs());
        int deviceID = getInt("Please select device ID (integer only)") - 1;
        out(home.displayDevices());
        int deviceIndex = getInt("Using the above list, please select a new device to attach to the smart plug (integer only)") - 1;
        home.changeDevice(deviceID,deviceIndex);
        displayDashboard();
    }

    public void plugLevelFour(){
        out(home.displayPlugs());
        int deviceID = getInt("Please select device ID (integer only)") - 1;
        out("ROOMS AVAILABLE: " + home.displayRooms());
        int roomID = getInt("Please select new room (integer only)") - 1;
        home.changeLocation(deviceID, roomID);
        displayDashboard();
    }

    public void plugLevelOptions() {
        out("""
                PLUG LEVEL OPTIONS
                1 - Switch plug off
                2 - Switch plug on
                3 - Change attached device
                4 - Move plug to different room
                5 - Back to Main Menu""");

        int plugOptions = getInt("Please select option");

        switch(plugOptions){
            case 1:
                plugLevelOne();
                break;
            case 2:
                plugLevelTwo();
                break;
            case 3:
                plugLevelThree();
                break;
            case 4:
                plugLevelFour();
                break;
            case 5:
                break;
        }
    }

    public void systemLevelOne(){
        out("ROOMS AVAILABLE: " + home.displayRooms());
        int roomID = getInt("Please select room (integer only)") - 1;
        out(home.displayDevices());
        int deviceIndex = getInt("Using the above list, please select the device to attach to the smart plug (integer only)") - 1;
        home.addPlug(roomID,deviceIndex);
        displayDashboard();
    }
    public void systemLevelTwo(){
        String newDevice = getString("Please enter a new device");
        home.addDevice(newDevice);
        out("\nNEW DEVICE LIST: ");
        out(home.displayDevices());
    }
    public void systemLevelThree(){
        String newRoom = getString("Please enter a new room");
        home.addRoom(newRoom);
        out("\nROOMS: " + home.displayRooms());
    }

    public void systemLevelOptions() {
        out("""
                SYSTEM LEVEL OPTIONS
                1 - Add new Smart Plug
                2 - Add new device
                3 - Add new Room
                4 - Back to Main Menu""");

        int systemOptions = getInt("Select an option");
        switch(systemOptions){

            case 1:
                systemLevelOne();
                break;
            case 2:
                systemLevelTwo();
                break;
            case 3:
                systemLevelThree();
                break;
            case 4:
                break;
        }
    }

    public void displayOptions(){
        out("""
                                            ---------------MENU OPTIONS---------------
                                            -----------please select option:----------
                    1 - House Level Options
                    2 - Room Level Options
                    3 - Smart Plug Level Options
                    4 - System Level Options
                    5 - Exit Program""");
    }

    //Outputting to console
    public void out(String prompt) { System.out.println(prompt);}
    public void out(int prompt) {System.out.println(prompt);}


}


