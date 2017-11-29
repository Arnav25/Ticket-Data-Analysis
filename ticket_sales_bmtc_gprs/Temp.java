import java.io.*;
import java.util.*;
import java.lang.*;


class bus_stop
{
  String name ; 
  double lat ; 
  double lon ;
}

class Temp

{
  public static void main(String args[])throws Exception
  {


    HashSet<String> bus_route = new HashSet<String>() ;

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

        System.out.println(bus_route.size()) ;
      }
    }
