package testing.BigDecimalCalculations;

import java.util.HashMap;
import java.math.BigDecimal;
import java.math.MathContext;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class realtesting {

    private static int startingValue = 44350;
	private static int endingValue = 100000;
	
    private static HashMap < Integer, BigDecimal > Storage = new HashMap < Integer, BigDecimal > ();
    private static MathContext precision = new MathContext(24);
    private static BigDecimal prevNCR = BigDecimal.ZERO;
    
    private static final File pointsSource = new File("C:\\Users\\krish\\OneDrive\\Desktop\\V2PointsOfProbabilityDONOTTOUCH.txt");
    private static File pointsOutput = new File("C:\\Users\\krish\\OneDrive\\Desktop\\V2PointsOfProbability.txt");

    public static void main(String args[]) throws FileNotFoundException{
    	
    	if(startingValue < endingValue) {
    		
    		clearFile();
    		extractPoints();

    		for (int i = startingValue; i <= endingValue; i++) 
    			System.out.println("(" + i + ", " + function(i) + ")");
    	}else {
    		System.out.println("ERROR: Starting value is larger than ending value");
    	}
      
    }

    private static BigDecimal function (int t) {

            BigDecimal functionValue = BigDecimal.ZERO;

            if(Storage.containsKey(t)){
            	
            	functionValue = Storage.get(t);
            	
            }else{
            	for (int i = 1; i < t; i++) 
            		functionValue = functionValue.add(nCr(t, i).multiply(Storage.get(i)));
            	
            	functionValue = functionValue.divide(powTwo(t), precision);
            	Storage.put(t, functionValue);
            }
            return functionValue;

        } 

    private static BigDecimal nCr(int n, int r) {
    	
    	 if (r == 1) { 	
         	prevNCR = BigDecimal.valueOf(n);
         } else {
         	prevNCR = prevNCR.multiply(BigDecimal.valueOf(n - r + 1)).divide(BigDecimal.valueOf(r), precision);
         }
     	return prevNCR;
    	
    	
    }

    private static BigDecimal powTwo(double powOf) {
    	
    	BigDecimal two = BigDecimal.valueOf(2);
    	BigDecimal powerTwo = BigDecimal.ONE;
    	
    	for(int i = 0; i < powOf; i++)
    			powerTwo = powerTwo.multiply(two);
    	
    	return powerTwo.subtract(BigDecimal.ONE);
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

