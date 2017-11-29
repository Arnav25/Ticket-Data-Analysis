import java.io.*;
import java.util.*;

public class Ticket100PercentStopNot100Percent
{
	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader("FinalOutput.txt"));
		String st = "";
		String fileheader = "Route_name,Source,Destination,No_of_Stops_between_Source_to_Dest_including_Src_and_Dest,Total_number_of_Bus_Stops,Percentage_of_Bus_Stops_in_between,Maximum_number_of_Tickets_Sold,Total_Tickets_Sold,Pecentage_of_Tickets_Sold";

		st = br.readLine();
		File file = new File("Ticket100PercentStopNot100Percent.txt");
		file.createNewFile();
		FileWriter writer = new FileWriter(file);
		writer.write(fileheader);
	    writer.write("\n");

		while((st = br.readLine()) != null)
		{
			String[] line = st.split(",");
			String ticketpercentage = line[8];
			String stoppercentage = line[5];
			
			if(ticketpercentage.equals("Nil")) continue;
			float per = Float.parseFloat(ticketpercentage);

			float per2 = Float.parseFloat(stoppercentage);
			//System.out.println(line[0]);
			//System.out.println(per);
			if(per==100.00 && per2!=100)
			{
					writer.write(st);
					writer.write("\n");
			}





		}
		writer.flush();
		writer.close();
		br.close();

	}
}