import java.io.*;
import java.util.*;
import java.lang.*;

class Pair{
  String bus_stop ; 
  int no_of_bus ;
}
class PairComparator implements Comparator<Pair>
{
  @Override
  public int compare(Pair a,Pair b)
  {
     if(a.no_of_bus>b.no_of_bus)
      return 1 ;
    else if(a.no_of_bus<b.no_of_bus)
      return -1;
    return 0 ;
  }
}

class NoOfTickets

{
	public static void main(String args[])throws Exception
	{

		HashMap<String,HashMap<String,Integer>> hmap = new HashMap<String,HashMap<String,Integer>>() ;

		  InputStream input; 
      HashMap<String,Integer> map = new HashMap<String,Integer>() ;

      BufferedReader in ;

        String line = "";
        String route = "V-335EUP" ;
        String blank = "" ;
          String max = "" ; int maxcount = 0 ; 
          int count = 0 ; 

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
            System.out.println(parts.length+" ") ;

          String r = parts[5] ;

          if(map.containsKey(r))
          {
            int x = map.get(r) ;
            map.put(r,x+1) ;
          }
          else
          {
            map.put(r,1) ;
          }

          int temp = map.get(r) ;
          if(temp>maxcount)
          {
            max = r ; maxcount  = temp ;
          }
    }
 
  }
    int size = map.size() ;

    Comparator<Pair> comparator = new PairComparator();
        PriorityQueue<Pair> pq = 
            new PriorityQueue<Pair>(size,comparator);
  
      for (Map.Entry<String,Integer> entry : map.entrySet()) {
        Pair obj = new Pair() ;
    obj.bus_stop = entry.getKey();
    obj.no_of_bus = entry.getValue();
    pq.add(obj) ;
}

long all = 0 ;

    while(pq.size()!=0)
    {
      Pair obj = pq.poll() ;

     // System.out.println(obj.bus_stop+" "+obj.no_of_bus) ;
      all+=obj.no_of_bus ;
    }

   // System.out.println("Total "+all) ;

/*
System.out.println("MAXIMUM") ;
System.out.println(max+" "+maxcount) ;
*/

}
}