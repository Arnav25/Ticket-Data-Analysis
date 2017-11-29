import java.io.*;
import java.util.*;
import java.lang.*;



class Bus

{
	public static void main(String args[])throws Exception
	{

	//HashMap<String,HashMap<String,Integer>> hmap = new HashMap<String,HashMap<String,Integer>>() ;

		HashSet<String> hset = new HashSet<String>() ;

		  InputStream input; 

      BufferedReader in ;

        String line = "";
        String route = "601UP" ;
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

        	String bus = parts[33] ;

        	hset.add(bus) ;
        }
        System.out.println(hset.size()) ;
    }

    System.out.println(hset.size()) ;

}
}
            