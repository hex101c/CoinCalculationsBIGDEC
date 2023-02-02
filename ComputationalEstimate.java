package testing;

import java.util.HashMap;
import java.math.BigDecimal;
import java.math.MathContext;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ComputationalEstimate {

	
	private static int startingValue = 0;
	private static int endingValue = 10000;
	
	private static int estimPow = 1000000000;
	
    private static HashMap < Integer, BigDecimal > Storage = new HashMap < Integer, BigDecimal > ();
    private static MathContext precision = new MathContext(24);
    private static BigDecimal prevNCR = BigDecimal.ZERO;
    
    //private static final File pointsSource = new File("C:\\Users\\krish\\Desktop\\V2PointsOfProbabilityDONOTTOUCH.txt");
    //private static File pointsOutput = new File("C:\\Users\\krish\\Desktop\\V2PointsOfProbability.txt");
    
    private static final File pointsSource = new File("C:\\Users\\krish\\Desktop\\V2PointsOfProbability.txt");
    private static File pointsOutput = new File("C:\\Users\\krish\\Desktop\\V3PointsOfProbability.txt");
    
    
    public static void main(String args[]) throws FileNotFoundException{

    	if(startingValue <= endingValue) {
    		
    		clearFile();
    		extractPoints();

    		for (int i = startingValue + 1; i <= endingValue + 1; i++) 
    			System.out.println("(" + (i - 1) + ", " + Estimate(i) + ")");
    	}else {
    		System.out.println("ERROR: Starting value is larger than ending value");
    	}
    }

    private static BigDecimal Estimate(int n) {
    	
    	BigDecimal numnum = BigDecimal.ZERO;
    	for(int i = 0; i < n; i++) {
    		numnum = numnum.add(nCr(estimPow, i + 1).multiply(Storage.get(i), precision),precision);
    	}
    	
    	BigDecimal denden = BigDecimal.ONE;
    	for(int i = 1; i <= n; i++) {
    		denden = denden.add(nCr(estimPow, i), precision);
    	}
    	
    	BigDecimal ans = numnum.divide(denden, precision);
    	return ans;
    	
        } 

    private static BigDecimal nCr(int n, int r) {
    	
   	 if (r == 1) { 	
        	prevNCR = BigDecimal.valueOf(n);
        } else {
        	prevNCR = prevNCR.multiply(BigDecimal.valueOf(n - r + 1)).divide(BigDecimal.valueOf(r), precision);
        }
    	return prevNCR;
   	
   	
   }
    	
    private static void extractPoints() throws FileNotFoundException {
    	
    	Scanner scanner = new Scanner(pointsSource);
    	int i = 0;
    	
    	while(scanner.hasNext()) {
    		String line = scanner.nextLine();
    		
    		int starting = line.indexOf(" ") + 1;
    		int ending = line.indexOf(")");
    		line = line.substring(starting, ending);
    		
    		BigDecimal value = new BigDecimal(line);
    		Storage.put(i, value);
    		
    		i++;   		
    	}
    	
    	scanner.close();
    }
    
    private static void clearFile() throws FileNotFoundException {
    	PrintWriter writer = new PrintWriter(pointsOutput);
		writer.print("");
		writer.close();
    }
}


