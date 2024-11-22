
import java.io.IOException;
import java.sql.Array;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import  java.util.concurrent.Future;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.currentThread;

public class AppMain
{

    static public Compute[] compute;
    static int size;
    static Thread[] threads, threads0;
    static public long timeStart;
    Scanner sc;
    boolean resume = true;
    public AppMain()
    {
        size=150;
        sc = new Scanner(System.in);

         try{runComputation(Main.method);}catch(Exception _){}


    }

    void  runThreads()
    {
        threads = new Thread[150]; //30
        List<Thread> tList = Arrays.asList(threads);
      //  Stream<Thread> parallelStream = tList.stream().parallel();
        for(int i = 0 ;i<150;i++)   // 110 ; 150
        {   final var n = i;
            threads[i] = new Thread(()-> Compute.Computation(n)); //  0

            threads[i].setName("eiler_id "+String.valueOf(n));
            //threads[i].start();

        }
        tList.parallelStream().forEach(Thread::start);

    }


    public  void runComputation(int v) throws ExecutionException, InterruptedException {
        if(v==1)              // classes
        {
            timeStart= new Date().getTime();
            compute = new Compute[size];
            List<Compute> list= Arrays.asList(compute);
            for(int i=0;i<size;i++){ compute[i] = new Compute(i,true);

            }
         //   list.stream().parallel().forEach(Compute::Computation(6));
        }

        if(v==2)             // allThreads
        {   timeStart= new Date().getTime();
            runThreads();

        }
        if(v==3)            // allAsyncMethods
        {   //AtomicInteger coroutineCounter = new AtomicInteger(1);
            //CompletableFuture.runAsync(coroutineCounter::incrementAndGet);
            timeStart= new Date().getTime();

            for(int i = 0 ;i<size;i++)
            {   final int n = i;
             //  CompletableFuture.runAsync(() -> Compute.Computation(133)).thenRun(() -> System.out.println("Асинхронный поток "+ n));
                CompletableFuture<Void> compute = CompletableFuture.runAsync(() -> {try {Compute.Computation(n);}
                    catch ( Exception _) {System.out.println("Error");}} );
                  compute.get();

            }

        }
        if(v==4)     //
        {   timeStart= new Date().getTime();
             threads0= new Thread[7];
             threads = new Thread[150];
            List<Thread> tList = Arrays.asList(threads0);
            int _i = 0;
            for(int i = 0 ;i<7;i++)
            {   final var j =_i;
                threads0[i] = new Thread(()->runThreads5(j)); // 0 ,30 ,60 ,90 ,120\
                threads0[i].setName("Treads0"+String.valueOf(i));
               // threads0[i].start();
                _i+=20;
            }
            tList.parallelStream().forEach(Thread::start);
        }
        if(v==5)     //  последовательное выполнение в цикле
        {   timeStart= new Date().getTime();
            for(int i = 0 ;i<size;i++)
            {   Compute.Computation(i);
            }

        }

        if(v==6){
            timeStart= new Date().getTime();
            Test test = new Test(); threads0= new Thread[150];
           // List<Thread> tList = Arrays.asList(threads0);
            int n=0;
            for(int i = 0 ;i<150;i++)
            {  final var _n = n; threads0[i] = new Thread(()->{for(int j=0; j<1;j++)test.Computation(j+_n);  }   ); n+=1;
                threads0[i].start();
            }
            //tList.parallelStream().forEach(Thread::start);
        }
    }
    void  runThreads5(int s)            // 0 ,30 ,60 ,90 ,120
    {
       // System.out.println(currentThread().getName());
        for(int i = s ;i<20+s; i++)   // 110 ; 150
        {   final var n = i+s;
          try {
              threads[i] = new Thread(() -> Compute.Computation(n  )); //  0
              threads[i].setName("Treads"+String.valueOf(n));
              //threads[i].join();
              threads[i].start();
              //System.out.println(i+s);
          }catch (Exception _){System.out.println("Exception "+(n));}

        }
    }
    public class  Test
    {   boolean success;
        public Test(){}
        public  String Computation(int d) {//System.out.println(currentThread().getName()+" " +d);

            long sum;
            //if(d>200) Thread.Sleep((int)d);
            // int a = 75, b = 41, c = 24, d = 24, e = 75; // 75
            if (d < 150) for (int a = 2; a <= d; a++) {
                for (int b = a; b <= d; b++) {
                    for (int c = b; c <= d; c++) {
                        sum = (long) (Math.pow(a, 5) + Math.pow(b, 5) + Math.pow(c, 5) + Math.pow(d, 5));
                        var e = Math.floor(Math.pow(sum, 0.2));
                        if (sum == Math.pow(e, 5)) {
                            success = true;
                            var answer = c + " + " + b + " + " + a + " + " + d + " + " + e + " = " + (a + b + c + d + e);
                            System.out.println("Искомые числа: " + answer + "\nduration: " + ((new Date().getTime()) - AppMain.timeStart) / 1000d + " seconds\n  поток id: " + currentThread().threadId() + "; " + currentThread().getName());

                        }
                    }
                    if (success) break;
                }
                if (success) break;

            }
           return "";
        }
    }
}


