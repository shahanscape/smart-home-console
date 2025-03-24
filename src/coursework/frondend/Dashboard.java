package coursework.frondend;

import coursework.backend.SmartHome;

public class Dashboard {

    public static void main(String[] args) {

	ConsoleInterface console = new ConsoleInterface();

    // Receiving input from console for creating backend objects
	int totalRooms = console.getInt("How many rooms are there in this property?");
	int totalPlugs = console.getInt("How many plugs do yo want to place in this property?");

	//Instantiating SmartHome object
	SmartHome home = new SmartHome(totalPlugs, totalRooms);

	//Populate SmartHome
	console.attachServerObject(home);
	console.populateRooms(totalRooms);
	console.populateDevices();
	console.displayDashboard();

		boolean loopSwitch = true;
		while(loopSwitch) {
			//Display Options
			console.displayOptions();

			int menuOptions = console.getInt("");

			switch (menuOptions) {
				case 1:
					console.houseLevelOptions();
					break;
				case 2:
					console.roomLevelOptions();
					break;
				case 3:
					console.plugLevelOptions();
					break;
				case 4:
					console.systemLevelOptions();
					break;
				case 5:
					console.out("Program Terminated!");
					loopSwitch = false;
					break;
				default:
					console.out("Please enter a valid option");
			}
		}

    }
}
