package SingleResponsibility;

import java.sql.Time;

public class CarParking {
	
	String AssignParkingSlot(int vehicleType)
	{
		//logic to assign parking slot
		char slotType = getSlotType(vehicleType);
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
	
	String PrintTicket()
	{
		return "";
	}
	
	
	Character getSlotType(int vehicleType)
	{
		if(vehicleType == 2)
			return 'T';
		if(vehicleType == 4)
			return 'F';
		
		return null;
	}
	
	
	int calculateFare(Time endTime,int vehicleType)
	{
		char slotType = getSlotType(vehicleType);
		if(slotType == 'T')
		{
			//logic to calculate fare for 2-wheeler
		}
		else if(slotType == 'F')
		{
			//logic to calculate fare for 4-wheeler
		}
		return 0;
	}
	
	

}
