import java.io.*;
import java.util.*;
import java.lang.*;


class bus_stop
{
  String name ; 
  double lat ; 
  double lon ;
}

class RouteAnalysis

{
  public static void main(String args[])throws Exception
  {

    int counter =  1 ;

    HashSet<String> bus_route = new HashSet<String>() ;

    System.out.println("Route_S.No,Route_Name,Total_Tickets,Origin,Destination,Percentage") ;

    InputStream input; BufferedReader in ;
      String line = "";
        
        String blank = "" ;
        String zero = "0" ;

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

            String r = parts[5] ;

             String from = parts[12] ;
            String to = parts[15] ;
  
        if((from.equals(blank)==true)||(to.equals(blank)==true)||(from.equals(zero)==true)||(to.equals(zero)==true))
          continue ;



            bus_route.add(r) ;
          }
          in.close() ;
        }



        Iterator<String> route_iterator = bus_route.iterator() ;

        while(route_iterator.hasNext())

        {

          String route = route_iterator.next() ;
        String route2 = route ;



    HashMap<String,HashMap<String,Integer>> hmap = new HashMap<String,HashMap<String,Integer>>() ;

      
      /*
        System.out.println("Route Name  "+route+"   Route no "+counter) ;
        counter++ ;

        System.out.println() ;
        System.out.println() ;

        System.out.println("Route Sequence") ;
        System.out.println() ;
      */
       

       System.out.print(counter+","+route+",") ;        
       counter++ ;



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

      


      if((from.equals(blank)==true)||(to.equals(blank)==true)||(from.equals(zero)==true)||(to.equals(zero)==true))
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
          HashSet<String> busStopName2 = new HashSet<String>() ;
          HashSet<String> allBusStopName = new HashSet<String>() ;


        for (Map.Entry<String,HashMap<String,Integer>> entry : hmap.entrySet()) {
    String key = entry.getKey();
    

    if(bus_map.containsKey(key)==false)
      continue ;

    bus_stop obj1 = bus_map.get(key) ;

    HashMap<String,Integer> map = entry.getValue();

   // System.out.println("KEY  "+key) ;
    String name1 = key + " "+ obj1.name + " " + obj1.lat + " " + obj1.lon ;

    busStopName.add(name1) ;

    //  System.out.println() ;

         for (Map.Entry<String,Integer> entry2 : map.entrySet()) {
          String key2 = entry2.getKey() ;

          if(bus_map.containsKey(key2)==false)
             continue ;

          bus_stop obj2 = bus_map.get(key2) ;

          int count = entry2.getValue() ;

          String name2 = key2 + " " + obj2.name + " " + obj2.lat + " " + obj2.lon ;
        busStopName.add(name2) ;
        busStopName2.add(name2) ;
         // System.out.println() ;
    }
   // System.out.println() ;
    //System.out.println() ;
    //System.out.println() ;
}


    ArrayList<String> list = new ArrayList<String>() ;
    String topLine = "" ;

   Iterator<String> iterator = busStopName.iterator(); 
      String busStop = "";
   // check values
   while (iterator.hasNext()){
    busStop = iterator.next() ;
   if(busStopName2.contains(busStop)==false)
   {
   // System.out.println(busStop) ;
    topLine+=busStop ;
    list.add(busStop) ;
    break ;
   } 
   }

   while(busStopName2.size()!=0)
   {
    String a[] = busStop.split(" ") ;
    int l = a.length ;
    double lat1 = Double.parseDouble(a[l-2]) ;
    double lon1 = Double.parseDouble(a[l-1]) ;
    double minDist = Double.MAX_VALUE ;


    Iterator<String> i = busStopName2.iterator() ;
     while(i.hasNext()){
      String bus = i.next() ;
      String b[] = bus.split(" ") ;
      l = b.length ;
      double lat2 = Double.parseDouble(b[l-2]) ;
      double lon2 = Double.parseDouble(b[l-1]) ;

      double dist = distance(lat1,lon1,lat2,lon2) ;
      if(dist<minDist)
      {
        minDist = dist ;
        busStop = bus ;
      }

    }

    busStopName2.remove(busStop) ; 
    topLine+=busStop ;
  //  System.out.println(busStop) ;
    list.add(busStop) ;

   }

  // System.out.println() ;
  // System.out.println("Matrix for the route ") ;
   //System.out.println() ;


   int elements = list.size() ;
   int matrix[][] = new int[elements][elements] ;
   int maximum = 0 ; int maxrow = 0; int maxcol = 0 ;

   int totalTickets = 0 ;


   for(int i=0;i<elements;i++)
   {
    String output[] = list.get(i).split(" ") ; 
    
    String from = output[0] ;

     if(hmap.containsKey(from)==false)
      {
        for(int j=0;j<elements;j++)
          System.out.print("") ;
      }

      else
      {
        HashMap<String,Integer> map = hmap.get(from) ;

    for(int j=0;j<elements;j++)
    {
      if(j<=i)
        System.out.print("") ;
      else
      {
        String output2[] = list.get(j).split(" ") ;
        String to = output2[0] ;

        if(map.containsKey(to)==false)
          System.out.print("");
        else
          {
            matrix[i][j] = map.get(to) ;
            totalTickets+=matrix[i][j] ;
            if(matrix[i][j]>maximum)
            {
              maximum = matrix[i][j] ;
              maxrow = i ; maxcol = j ;
            }

            System.out.print("") ;
          }

      }
    }
  }

   // System.out.println() ;

   }

  // System.out.println() ;
   //System.out.println() ;

   if(list.size()!=0)
   {
   // System.out.println("Highest number of tickets bought from "+list.get(maxrow)+" for "+list.get(maxcol)+"  "+matrix[maxrow][maxcol]) ;
   	System.out.print(totalTickets+",") ;
    String source[] = list.get(maxrow).split(" ") ;
    String destination[] = list.get(maxcol).split(" ") ;

    for(int z=0;z<source.length-2;z++)
    {
      System.out.print(source[z]+" ") ;
    }
    System.out.print(",") ;

    for(int z=0;z<destination.length-2;z++)
    {
      System.out.print(destination[z]+" ") ;
    }
    System.out.print(",") ;    

    float v1 = matrix[maxrow][maxcol] ;
    v1 = v1/totalTickets ;
    v1 = v1*100 ;
    System.out.println(v1) ;


  }
    else
      System.out.println("N/A,N/A,N/A,N/A") ;


/*

   int max_sum = 0 ; int max_index = 0 ;

   for(int i=0;i<elements;i++)
   {
    int sum = 0 ; 
    for(int j=0;j<elements;j++)
    {

      sum = sum + matrix[i][j] ;
    }

    if(sum>max_sum)
    {
      max_sum = sum ;
      max_index = i ;
    }
   }

   System.out.println() ;

   if(list.size()!=0)
   System.out.println("Most Tickets bought from "+list.get(max_index)+"   "+max_sum) ;

   max_sum = 0 ; max_index = 0 ;


      for(int i=0;i<elements;i++)
   {
    int sum = 0 ; 
    for(int j=0;j<elements;j++)
    {

      sum = sum + matrix[j][i] ;
    }

    if(sum>max_sum)
    {
      max_sum = sum ;
      max_index = i ;
    }
   }

   System.out.println() ;

   if(list.size()!=0)
   System.out.println("Most Tickets bought for "+list.get(max_index)+"   "+max_sum) ;

   System.out.println() ;
   System.out.println() ;
   System.out.println() ;
   System.out.println() ;
*/


}



}

public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;


        return (dist);
    }
    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}