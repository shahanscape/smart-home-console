package coursework.backend;

public class SmartPlug {
    private String device;
    private String location;
    private int ID;
    private boolean status;

    public SmartPlug(String device, String location, int ID, boolean status) {
        this.device = device;
        this.location = location;
        this.ID = ID;
        this.status = status;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String toString() {
        String s = "Smart Plug |attached to:" + device;

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count++;
        }

        int spaces = 45 - count;
        if (count<45) {
            s += " ".repeat(spaces) + " |room: " + location +
                    "|ID: " + (ID + 1) +
                    "|status: " + booleanToString(status) + "|";
        }
        else{
            s += " |room: " + location +
                    "|ID: " + (ID + 1) +
                    "|status: " + booleanToString(status) + "|";

        }
        return s;
    }

    public void on() {setStatus(true);}

    public void off() {setStatus(false);}

    public void toggle() {setStatus(!isStatus());}

    private String booleanToString(boolean status) {
        if(!status) {return "off";}
        return "on";
    }
}
