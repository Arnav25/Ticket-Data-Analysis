
import java.io.*;
import java.util.*;
import java.lang.*;



class CSVReader

{
	public static void main(String args[])throws Exception
	{


		for(int i=1;i<=93;i++)
		{
			String fileName = Integer.toString(i) ;
			fileName = fileName + ".csv" ;
		HashMap<String,HashSet<Integer>> hmap = new HashMap<String,HashSet<Integer>>() ; 
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        String line = "";

        int count = 0 ; 
        while ((line = in.readLine()) != null) {
            

        		if(count==0)
        		{
        			count = 1 ;
        			continue ;
        		}

 			String parts[] = line.split(",");
 			String route_no = parts[5] ; 
 			//System.out.println(parts[16]) ;

 		int from ; int to ;
 			try
 			{
 			 from = Integer.parseInt(parts[13]) ;
 			 to =   Integer.parseInt(parts[16]) ; 
 		}
 		catch(Exception e)
 		{
 			continue ;
 		}
 			HashSet<Integer> hs ; 
 			if(hmap.containsKey(route_no)==true)
 			{
 				hs = hmap.get(route_no) ;
 			}
 			else
 			{
 				hs = new HashSet<Integer>() ;
 			}

 			hs.add(from) ;  hs.add(to) ;
 			hmap.put(route_no,hs) ;

 			  }
        	in.close();


        String max_route_no = "" ; 
        int size = 0 ;

       for (Map.Entry<String,HashSet<Integer>> entry : hmap.entrySet()) {
    String key = entry.getKey();
    HashSet<Integer> h = entry.getValue();
    // ...



        int s = h.size() ;

       // System.out.println(key+" "+s) ;

        if(s>size)
        {
        	size = s ;
        	max_route_no = key ;
        }

    }

    System.out.println(max_route_no+" "+size) ;

}
	}
}