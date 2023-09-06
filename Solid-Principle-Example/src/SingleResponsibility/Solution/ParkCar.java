package SingleResponsibility.Solution;

public class ParkCar {
	
	String AssignParkingSlot(int vehicleType)
	{
		//logic to assign parking slot
		Slot slot = new Slot();
		Character slotType = slot.getSlotType(vehicleType);
		if(slotType == 'T')
		{
			//logic to assign slot for 2-wheeler
		}
		else if(slotType == 'F')
		{
			//logic to assign slot for 4-wheeler
		}
		return "";
	}

}
