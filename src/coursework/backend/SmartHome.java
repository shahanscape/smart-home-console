package coursework.backend;

public class SmartHome {

    private SmartPlug [] plugs;
    private Room [] rooms;
    private AttachedDevice [] attachedDevices;
    private int plugIndex;
    private int roomIndex;
    private int deviceIndex;


    public SmartHome(int totalPlugs, int totalRooms) {
        this.plugs = new SmartPlug[totalPlugs];
        this.rooms = new Room[totalRooms];
        this.attachedDevices = new AttachedDevice[5];
        this.plugIndex = 0;
        this.roomIndex = 0;
        this.deviceIndex = 0;
        appendDevices();
    }

    public int getSize() {return rooms.length;
    }

    public int getPlugSize() {return plugs.length;}

    public void appendRooms(String location) {
        if (roomIndex >= getSize()) {return;}
        Room room = new Room(location, roomIndex);
        rooms[roomIndex] = room;
        roomIndex++;
    }

    public String displayRooms() {
        String string = "";
        for (Room room : rooms) {
            string += room.toString();
        }
        return string;
    }

    public void appendDevices() {
        String [] devices = {"Lamp", "TV", "Computer", "PhoneCharger", "Heater"};
        for(int i = 0; i<devices.length; i++) {
            AttachedDevice attachedDevice = new AttachedDevice(devices[i], deviceIndex);
            attachedDevices[deviceIndex] = attachedDevice;
            deviceIndex++;
        }
    }

    public void appendPlugs(int deviceID, int roomID) {
        if (plugIndex >= getPlugSize()) {
            return;
        }
        for(Room room : rooms) {
            if(room.getRoomID() == roomID) {
                SmartPlug plug
                        = new SmartPlug(deviceIdToString(deviceID), roomIdToString(roomID), plugIndex, false);
                plugs[plugIndex] = plug;
            }
        }
        plugIndex++;
    }

    public String displayPlugs() {
        String string = "";
        for (SmartPlug plug : plugs) {
            string += "\n" + plug.toString();
        }
        return string;
    }

    public String displayDevices() {
        String string = "";
        for (AttachedDevice attachedDevice : attachedDevices) {
            string += attachedDevice.toString() + "\n";
        }
        return string;
    }

    private String roomIdToString(int roomID) {
        String roomName = "";
        for (Room room : rooms) {
            if(roomID == room.getRoomID()) {
                roomName = room.getRoomName();
            }
        }
        return roomName;
    }

    private String deviceIdToString(int deviceID) {
        String deviceName = "";
        for(AttachedDevice attachedDevice : attachedDevices) {
            if (deviceID == attachedDevice.getDeviceID()) {
                deviceName = attachedDevice.getDeviceName();
            }
        }
        return deviceName;
    }

    public void allPlugsOff() {
        for(SmartPlug plug : plugs) {
            plug.off();
        }
    }

    public void allPlugsOn() {
        for(SmartPlug plug : plugs) {
            plug.on();
        }
    }

    public void allRoomsOn(int roomID) {
        for(SmartPlug plug : plugs) {
            if (plug.getLocation() == roomIdToString(roomID)) {
                plug.on();
            }
        }
    }

    public void allRoomsOff(int roomID) {
        for(SmartPlug plug : plugs) {
            if (plug.getLocation() == roomIdToString(roomID)) {
                plug.off();
            }
        }
    }

    public void toggleDevice(int deviceId) {
        for(SmartPlug plug : plugs) {
            if(plug.getID() == deviceId) {
                plug.toggle();
            }
        }
    }

    public void plugOn(int plugID) {
        for(SmartPlug plug : plugs) {
            if(plug.getID() == plugID) {
                plug.on();
            }
        }
    }

    public void plugOff(int plugID) {
        for(SmartPlug plug : plugs) {
            if(plug.getID() == plugID) {
                plug.off();
            }
        }
    }

    public String displayDevicesInRoom(int roomId) {
        String string = "";
        for(SmartPlug plug : plugs) {
            if(plug.getLocation() == roomIdToString(roomId)) {
                string += "\n" + plug.toString() + "";
            }
        }
        return string;
    }
    public void changeDevice(int plugID, int deviceId) {
        for(SmartPlug plug : plugs) {
            if(plug.getID() == plugID)
                plug.setDevice(deviceIdToString(deviceId));
        }
    }

    public void changeLocation(int plugID, int roomId) {
        for(SmartPlug plug : plugs) {
            if(plug.getID() == plugID)
                plug.setLocation(roomIdToString(roomId));
        }
    }

    public String displayDashboard() {
        String string = "";
        string+= "                    ----------------Dashboard----------------";
        for (int i = 0; i < roomIndex; i++) {
            string += ("\nROOM: " + (i+1));
            int counter = 0;
            for (SmartPlug plug : plugs) {
                if (rooms[i].getRoomName() == plug.getLocation()) {
                    string += ("\n" + plug.toString() + "\r");
                    counter++;
                }
            }
            if (counter == 0){
                string += "\nNo smart plugs available in this room!";
            }
        }
        return string;
    }

    public void addRoom(String newRoom) {
        Room [] tempRooms;
        tempRooms = new Room[rooms.length + 1];
        for(int i = 0; i < rooms.length; i++) {
            tempRooms[i] = rooms[i];
        }
        Room room  = new Room(newRoom, roomIndex);
        tempRooms[roomIndex] = room;
        roomIndex++;
        rooms = tempRooms;
    }

    public void addDevice(String newDevice) {
        AttachedDevice[] tempDevices;
        tempDevices = new AttachedDevice[attachedDevices.length + 1];
        for(int i = 0; i < attachedDevices.length; i++) {
            tempDevices[i] = attachedDevices[i];
        }
        AttachedDevice attachedDevice  = new AttachedDevice(newDevice, deviceIndex);
        tempDevices[deviceIndex] = attachedDevice;
        deviceIndex++;
        attachedDevices = tempDevices;

    }

    public void addPlug(int roomID, int deviceID) {
        SmartPlug [] tempPlugs;
        tempPlugs = new SmartPlug[plugs.length + 1];
        for(int i = 0; i < plugs.length; i++) {
            tempPlugs[i] = plugs[i];
        }
        for(Room room : rooms) {
            if(room.getRoomID() == roomID) {
                SmartPlug plug  =
                        new SmartPlug(deviceIdToString(deviceID), roomIdToString(roomID), plugIndex, false);
                tempPlugs[plugIndex] = plug;
            }
        }
        plugIndex++;
        plugs = tempPlugs;
    }

}
