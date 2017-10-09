import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.concurrent.BlockingQueue;

public class Generator implements Runnable{
	Buffer buffer;
	

	private BufferedReader rd = null;
	private String currentLine;
	String[] parts = null;
	
	Generator(String file) throws FileNotFoundException {
	
		this.rd = new BufferedReader(new FileReader(file));
	}

	// read file line by line
	// parse strings and then "offer" a new event to the queue
	@Override
	public void run() {
		try {
			while((currentLine = rd.readLine()) != null) {
				parts = currentLine.split(",");
				int time = Integer.parseInt(parts[0]);
				String type = parts[1];
				int n = Integer.parseInt(parts[2]);
				
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Event ev = new Event(type, n);
				//System.out.println(ev.type + " put");
				Main.q.offer(ev);
			}
			Main.number += 1;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rd != null) rd.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		//System.out.println("Producer "  + " finished Correctly");
	}

}

