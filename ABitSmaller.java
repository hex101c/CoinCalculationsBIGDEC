package testing;
import java.util.HashMap;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ABitSmaller {

    public static HashMap < Integer, Double > Storage = new HashMap < Integer, Double > ();
    
    private static int startingValue = 2;
    private static int endingValue = 100000;
    private static double prevNCR = 0;
    private static final double LN2 = Math.log(2);
    
    private static File pointsSource = new File("C:\\Users\\krish\\OneDrive\\Desktop\\V2PointsOfProbabilityDONOTTOUCH.txt");
    private static File pointsOutput = new File("C:\\Users\\krish\\OneDrive\\Desktop\\V2PointsOfProbability.txt");

    public static void main(String args[]) throws FileNotFoundException {

    	if(startingValue < endingValue) {
    		
    		clearFile();
    		extractPoints();

    		for (int i = startingValue; i <= endingValue; i++) 
    			System.out.println("(" + i + ", " + function(i) + ")");
    	}else {
    		System.out.println("ERROR: Starting value is larger than ending value");
    	}

    }

    private static double function (int t) {
    	
    	double functionTotal = 0;

    	 if(Storage.containsKey(t)){
         	
         	functionTotal = Storage.get(t);
         	
         }else{
        	 if (t <= 1010) {

        		 for (int i = 1; i < t; i++) {
        			 functionTotal += nCr(t, i) * Storage.get(i);
            }

            functionTotal /= powTwo(t);
        	 } else {

        		 for (int i = 1; i < t; i++) {
        			 functionTotal += Math.pow(Math.E, nCr(t, i) + Math.log(Storage.get(i)) - t * LN2);
            }

        }
        	 
        	 Storage.put(t, functionTotal);
         }
    	 
    	 return functionTotal;
    }

    private static double nCr(int n, int r) {

        double numerator = 1;
        double denominator = 1;
        
        if (n <= 1010) {
        	
            for (int i = 0; i < r; i++) { 	
                numerator = numerator * (n - i);
                denominator = i + 1;
                numerator = numerator / denominator;    
            }
            
            return numerator;

        } else {
        	
            if (r == 1) { 	
            	prevNCR = Math.log(n);
            } else {
            	prevNCR += Math.log(n - r + 1) - Math.log(r);
            	
            }
        	return prevNCR;
        }
    }

    private static double powTwo(double powOf) {
        return Math.pow(2, powOf) - 1;
    }
    
    private static void extractPoints() throws FileNotFoundException {
    	
    	Scanner scanner = new Scanner(pointsSource);
    	int i = 0;
    	
    	while(scanner.hasNext()) {
    		String line = scanner.nextLine();
    		
    		int starting = line.indexOf(" ") + 1;
    		int ending = line.indexOf(")");
    		line = line.substring(starting, ending);
    		
    		double value = Double.valueOf(line);
    		Storage.put(i, value);
    		
    		if(i == endingValue)
    			break;
    		
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
