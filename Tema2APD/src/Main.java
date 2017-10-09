import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static int filenum = 0;
	
	public static List<Integer> primes = new ArrayList<>();
	public static List<Integer> factorials = new ArrayList<>();
	public static List<Integer> squares = new ArrayList<>();
	public static List<Integer> fibs = new ArrayList<>();
	
	public static int number = 0;
	static BlockingQueue<Event> q; 

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		filenum = args.length - 2;
		int i;
		ExecutorService executor = Executors.newFixedThreadPool(4);
		q = new ArrayBlockingQueue<Event>(Integer.parseInt(args[0]));
		
		Thread threads[] = new Thread[args.length - 2];
		
		// create threads
		for(i = 2 ; i < args.length ; i++) {
			threads[i - 2] = new Thread(new Generator(args[i]));
		}
		
		// start producer threads
		for(i = 0 ; i < args.length - 2 ; i++) {
			threads[i].start();
		}
		
		// take elements from queue while until last element is read
		for(i = 0 ; i < (args.length - 2) * Integer.parseInt(args[1]) ; i++) {
			
			try {
				executor.execute(q.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// shutdown the executor after doing the job
		executor.shutdown();
        while (!executor.isTerminated()) {
        }
		
        
        // join the threads in the end
		for(i = 0 ; i < args.length - 2 ; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		// sort arrays containing the processed events
		Collections.sort(primes);
		Collections.sort(factorials);
		Collections.sort(squares);
		Collections.sort(fibs);
		
		File file1 = new File("PRIME.out");
		File file2 = new File("FACT.out");
		File file3 = new File("SQUARE.out");
		File file4 = new File("FIB.out");

		// if files don't exist, then create it
		if (!file1.exists()) {
			file1.createNewFile();
		}
		if (!file2.exists()) {
			file2.createNewFile();
		}
		if (!file3.exists()) {
			file3.createNewFile();
		}
		if (!file4.exists()) {
			file4.createNewFile();
		}

		// get files' absolute path
		FileWriter fw1 = new FileWriter(file1.getAbsoluteFile());
		FileWriter fw2 = new FileWriter(file2.getAbsoluteFile());
		FileWriter fw3 = new FileWriter(file3.getAbsoluteFile());
		FileWriter fw4 = new FileWriter(file4.getAbsoluteFile());
		
		// create buffers 
		BufferedWriter bw1 = new BufferedWriter(fw1);
		BufferedWriter bw2 = new BufferedWriter(fw2);
		BufferedWriter bw3 = new BufferedWriter(fw3);
		BufferedWriter bw4 = new BufferedWriter(fw4);
		
		// print all elements
		for(Integer it : primes) {
			bw1.write(Integer.toString(it.intValue()));
			bw1.newLine();
		}
		
		for(Integer it : factorials) {
			bw2.write(Integer.toString(it.intValue()));
			bw2.newLine();
		}
		
		for(Integer it : squares) {
			bw3.write(Integer.toString(it.intValue()));
			bw3.newLine();
		}
		
		for(Integer it : fibs) {
			bw4.write(Integer.toString(it.intValue()));
			bw4.newLine();
		}
		
		bw1.close();
		bw2.close();
		bw3.close();
		bw4.close();
		
		//System.out.println("Program finished successfully.\n");
		//System.exit(1);
	}

}
