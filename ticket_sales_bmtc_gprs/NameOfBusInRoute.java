import java.io.*;
import java.util.*;
import java.lang.*;


class bus_stop
{
	String name ; 
	double lat ; 
	double lon ;
}

class NameOfBusInRoute

{
	public static void main(String args[])throws Exception
	{

		HashMap<String,HashMap<String,Integer>> hmap = new HashMap<String,HashMap<String,Integer>>() ;

		  InputStream input; 

      BufferedReader in ;

        String line = "";
        String route = "401DN" ;
        String route2 = "401DN" ;
        String blank = "" ;
          


          for(int i=1;i<=93;i++)
          {
              int c = 0 ; 
              String fileName = Integer.toString(i) ;
              fileName = fileName + ".csv" ;
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            in = new BufferedReader(new InputStreamReader(input));

        while ((line = in.readLine()) != null) {
            

        		if(c==0)
        		{
        			c = 1 ;
        			continue ;
        		}

 			String parts[] = line.split(",");

 			if((parts[5].equals(route)==false))//&&(parts[5].equals(route2)==false))
 				continue ;

 			
 			String from = parts[12] ;
 			String to = parts[15] ;

 			


 			if((from.equals(blank)==true)||(to.equals(blank)==true))
 				continue ;

 			HashMap<String,Integer> map ; 

 			if(hmap.containsKey(from))
 			{
 				map = hmap.get(from) ;
 			}
 			else
 			{
 				map = new HashMap<String,Integer>() ;
 			}

 			if(map.containsKey(to))
 			{
 				int x = map.get(to) ;
 				map.put(to,x+1) ;
 			}
 			else
 			{
 				map.put(to,1) ;
 			}

 			hmap.put(from,map) ;
        }
        in.close() ;
      }


      	int c = 0 ;
       input = Thread.currentThread().getContextClassLoader().getResourceAsStream("bus_stop_code.csv");
            in = new BufferedReader(new InputStreamReader(input));

            HashMap<String,bus_stop> bus_map = new HashMap<String,bus_stop>() ;

             while ((line = in.readLine()) != null) {
            

        		if(c<2)
        		{
        			c++ ;
        			continue ;
        		}

        		String parts[] = line.split(",");

        		String code = parts[2] ;

        		bus_stop obj = new bus_stop() ;
        		obj.name = parts[1] ;
        		obj.lat = Double.parseDouble(parts[3]) ;
        		obj.lon = Double.parseDouble(parts[4]) ;

        		bus_map.put(code,obj) ;

        	}


          HashSet<String> busStopName = new HashSet<String>() ;


        for (Map.Entry<String,HashMap<String,Integer>> entry : hmap.entrySet()) {
    String key = entry.getKey();
    

    if(bus_map.containsKey(key)==false)
    	continue ;

    bus_stop obj1 = bus_map.get(key) ;

    HashMap<String,Integer> map = entry.getValue();

   // System.out.println("KEY  "+key) ;
    String name1 = obj1.name + " " + obj1.lat + "," + obj1.lon ;

    busStopName.add(name1) ;

    //	System.out.println() ;

         for (Map.Entry<String,Integer> entry2 : map.entrySet()) {
         	String key2 = entry2.getKey() ;

         	if(bus_map.containsKey(key2)==false)
    	       continue ;

         	bus_stop obj2 = bus_map.get(key2) ;

         	int count = entry2.getValue() ;

          String name2 = obj2.name + " " + obj2.lat + "," + obj2.lon ;
        busStopName.add(name2) ;
         //	System.out.println() ;
    }
   // System.out.println() ;
    //System.out.println() ;
    //System.out.println() ;
}

 Iterator iterator = busStopName.iterator(); 
      
   // check values
   while (iterator.hasNext()){
   System.out.println(iterator.next());  
   }



}

}