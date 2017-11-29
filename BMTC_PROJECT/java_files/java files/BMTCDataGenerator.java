import java.io.*;
import java.util.*;

public class BMTCDataGenerator
{
	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader("AllRoute.txt"));
		HashMap<String,Integer> map = new HashMap<>();
		String fileheader = "Route_name,Source,Destination,No_of_Stops_between_Source_to_Dest_including_Src_and_Dest,Total_number_of_Bus_Stops,Percentage_of_Bus_Stops_in_between,Maximum_number_of_Tickets_Sold,Total_Tickets_Sold,Pecentage_of_Tickets_Sold";
		String st;
		String source = "";
		String destination = "";
 		int sum = 0 ;
 		int rr = 0;
 		int routecount = 0;
 		String route_name = "";
 		File file = new File("FinalOutput.txt");
		file.createNewFile();
		FileWriter writer = new FileWriter(file);
	            
	    writer.write(fileheader);
	    writer.write("\n");
		 for(int i=0 ; i<358054 ; i++)
		 {   	
			    st = br.readLine();
			    String[] line = st.split(" ");
			  	if(line[0].equals("EndOfFile")) break;
			    if(st.length()!=0)
			    {
			    	/////////////////////////  condition for route name  //////////////////////////////
			    	if(line[0].equals("Route") && line[1].equals("Name"))
			         {
			         			route_name = "";
			         			for(int z = 2 ; z<line.length-5 ; z++)
			         			{
			         				if(route_name.equals(""))
			         					route_name = line[z];
			         				else
			         				route_name = route_name+ " " + line[z];
			         			}	
			         			routecount++;
			          			
			          			//System.out.println("route name : " + route_name);
			          			
			          			writer.write(route_name+",");
			          			
			          }
			          ////////////////////////////condition of populating hash map///////////////////
			          if(line[0].equals("Nothing") && line[1].equals("to") && line[2].equals("print"))
			          {
			          	writer.write("Nil");
			          	writer.write("\n");
			          	continue;
			          }
			         
			    	else if(line[0].equals("Route") && line[1].equals("Sequence"))
			    	{
			    		//System.out.println("I am here");
			    		rr = 1;
			    		String str;
			    		str = br.readLine();
			    	
			    		int count = 1;
			    		String[] strr = new String[100];


			    		do
			    		{
			    			str = br.readLine();
			    			//System.out.println(str);
			    			
			    			strr = str.split(" ");
			    			//if(str.length()!=0 && strr[0].equals("Matrix")) break;
			    			//if(strr[0].equals("Matrix")) continue;
			    			if(str.length() == 0 ) continue;
			    			String bus_stop="";
			    			for(int z = 0 ; z<strr.length-2 ; z++)
			    			{
			    				if(bus_stop.equals(""))
			    					bus_stop = strr[z];
			    				else
			    				bus_stop = bus_stop+" "+strr[z];

			    			}

			    			map.put(bus_stop,count);
			    			count++;
			    		}while(!strr[0].equals("Matrix"));
			    		//System.out.println(map);

			    	}
			    	//if((map.isEmpty() == true)) continue;


			    	//////////////////////////  condition for matrix ///////////////////// 		
			          		if(line[0].equals("0"))
			          		{
			          			
			          			for(int j = 1 ; j<line.length ; j++)
			          			{
			          				if(line[j].equals(" ") || line[j].equals("")) continue;
			          				int num = Integer.parseInt(line[j]);
			          				sum = sum + num;

			          			}
			          		}


			    	//////////////////// condition for finding source name and destination name //////////	
			    	else if(line[0].equals("Highest") && line[1].equals("number"))
			        {
			        			int n = 0; 
			          			for(int j=6  ; j < line.length ; j++)
				                {
				                  if(line[j].equals("for"))
				                  {
				                      n = j;
				                      break;
				                  }
				                }

				                for(int j= 6; j <n-2; j++)
				                {
				                	if(source.equals(""))
				                	source = line[j];
				                	else	
				                    source = source + " " + line[j];

				                }

				                for(int j = n+1; j < line.length-4 ; j ++)
				                {
				                	if(destination.equals(""))
				                		destination = line[j];
				                	else
				                	destination = destination + " " + line[j];

				                }
				                //System.out.println(" Source  "  +source);
				                //System.out.println( " destination " +  destination );
				                writer.write(source+",");
				                writer.write(destination+",");
				                int a = map.get(source);
				                int b = map.get(destination);
				                source = "";
				                destination = "";

				                System.out.println("Number of hops  " + (b-a+1));
				                writer.write(Integer.toString(b-a+1)+",");

				                System.out.println("total hop count = " + (map.size()-1));
				                writer.write(Integer.toString(map.size()-1)+",");

				                float percentage_of_hop_count = (((float)(b-a+1))/(map.size()-1))*100;
				                System.out.println(percentage_of_hop_count);
				                writer.write(Float.toString(percentage_of_hop_count)+",");
				                				                

				               
				                   map = new HashMap<>();
				                   rr = 0;
				                   

				                 String number_of_tickets_sold = line[line.length-1];
				                 System.out.println("NUmber of tickets sold " + number_of_tickets_sold);
				                 writer.write(number_of_tickets_sold+",");
				                 System.out.println("Total_Tickets_Sold " + sum);
				                 writer.write(sum + ",");
				                float per = 0;
				                if(sum == 0) per = 0;
				                else
				                per = ((float)Integer.parseInt(number_of_tickets_sold)/sum)*100;
				                System.out.println(per);
				            	writer.write(Float.toString(per));
				            	 writer.write("\n");
				           
				               System.out.println();
				               System.out.println();
				               System.out.println();

				                sum = 0;

				    }
				    
			    }
		 }
		 System.out.println(routecount);
		 writer.flush();
		 writer.close();
		 br.close();
	}
}