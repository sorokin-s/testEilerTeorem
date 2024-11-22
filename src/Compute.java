import java.util.ArrayList;
import java.util.Arrays;
import  java.util.Date;

import static java.lang.Thread.currentThread;

public class Compute
{
    public static boolean success;
    static public String answer;
    public int d;
    static ArrayList<String> log = new ArrayList<String>(AppMain.size);
    public Compute(int _d, boolean init)
    {   d =_d;
        if(init)Computation(d);
    }

    public static void Computation(int d)
    {//System.out.println(currentThread().getName()+" " +d);

        long sum;
        //if(d>200) Thread.Sleep((int)d);
        // int a = 75, b = 41, c = 24, d = 24, e = 75; // 75
        if(d<150)for(int a = 2; a <=d ; a++)
        {
            for (int b = a; b <=d ; b++)
            {
                 for ( int c= b; c <=d; c++)
                {
                    sum = (long)(Math.pow( a, 5) + Math.pow(b, 5) + Math.pow(c, 5) + Math.pow(d, 5));
                    var e = Math.floor(Math.pow(sum, 0.2 ));
                    if (sum==Math.pow(e,5) )
                    {
                        success = true;
                        answer = c + " + " + b + " + " + a + " + " + d + " + " + e + " = " + (a + b + c + d + e);
                        System.out.println("Искомые числа: "+answer +"\nduration: "+((new Date().getTime())-AppMain.timeStart)/1000d +" seconds\n  поток id: "+ currentThread().threadId()+"; "+ currentThread().getName());

                    }
                }
                if (success) break;
            }
            if (success) break;

        }

    }
}
