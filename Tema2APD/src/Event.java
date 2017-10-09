
public class Event implements Runnable{
	public String type;
	private int N;

	public Event(String type, int N){
		this.type = type;
		this.N = N;
	}
	
	//efficient way to check primes
	private boolean isPrime(int n) {
	    int i;
	    if ( 0 == n || 1 == n)
		return false;

	    //check if n is a multiple of 2
	    if ( 0 == n%2 ) return false;
	   
	    //if not, then just check the odds
	    for(i=3 ; i*i <= n ; i += 2) {
	        if( 0 == n % i )
	            return false;
	    }
	    return true;
	}
	
	private int factorial(int i) {
		// calculate factorial of a number
		int rez = 1;
		
		for (int j = 1 ; j <= i ; j++) {
			rez *= j;
		}
		
		return rez;
	}
	
	// PRIME
	private int prime(int N) {
		int M = N;
		while (!isPrime(M)) {
			M--;
		}
		return M;
	}
	
	// FACT
	private int fact(int N) {
		int i = 1;
		
		while(factorial(i) <= N) {
			i++;
		}
		
		return i-1;
	}
	
	// SQUARE
	private int square(int N) {
		double rez = Math.sqrt(N);
		return (int) Math.floor(rez);
	}
	
	// FIB
	private int fib(int N) {
		int f0 = 0, f1 = 1, aux, i = 0;
		while (f1 < N) {
			aux = f1;
			f1 += f0;
			f0 = aux;
			i++;
		}
		return i;
	}
	
	// run to calculate the specific value, and then add it to a synchronized array
	public void run() {
		int res = 0;
		switch(type) {
			case "PRIME":
				res = prime(N);
				synchronized(Main.primes) {
					Main.primes.add(new Integer(res));
				}
				break;
			case "FIB":
				res = fib(N);
				synchronized(Main.fibs) {
					Main.fibs.add(new Integer(res));
				}
				break;
			case "SQUARE":
				res = square(N);
				synchronized(Main.squares) {
					Main.squares.add(new Integer(res));
				}
				break;
			case "FACT":
				res = fact(N);
				synchronized(Main.factorials) {
					Main.factorials.add(new Integer(res));
				}
				break;
			default:
				throw new IllegalArgumentException("Invalid type\n");
		}
	
	}
}
