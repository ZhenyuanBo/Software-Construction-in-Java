package warmup;

import java.util.HashSet;
import java.util.Set;
import java.lang.*;

public class Quadratic {

    /**
     * Find the integer roots of a quadratic equation, ax^2 + bx + c = 0.
     * @param a coefficient of x^2
     * @param b coefficient of x
     * @param c constant term.  Requires that a, b, and c are not ALL zero.
     * @return all integers x such that ax^2 + bx + c = 0.
     */
    public static Set<Integer> roots(int a, int b, int c) {
        Set<Integer> ans = new HashSet<Integer>();
        int x1=0;
        int x2=0;
        double discriminant = b*b-4.0*a*c;
        System.out.println("b: "+b+" a: "+a + " c: "+c);
        System.out.println("Discriminant Value is: "+discriminant);
        
        if(discriminant < 0) {
            //throw new RuntimeException("Fail to calculate the roots due to the result of "+"(b^2-4*a*c)"+" is negative");
            System.out.println("Fail to calculate the roots due to the result of "+"(b^2-4*a*c)"+" is negative");
        }else{
            double tempVar = Math.sqrt(discriminant);
            x1 = (int)(-b + tempVar)/(2*a);
            x2 = (int)(-b - tempVar)/(2*a);
            System.out.println("The first value is: "+x1);
            System.out.println("The second value is: "+x2);
            //ans.add(x1);
            //ans.add(x2);
        }
        ans.add(x1);
        ans.add(x2);
        return ans;
        //throw new RuntimeException("not implemented yet;"); // TODO: delete this line when you implement it
    }
    
    /**
     * Main function of program.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("For the equation x^2 - 4x + 3 = 0, the possible solutions are:");
        Set<Integer> result = roots(1, -4, 3);
        System.out.println(result);
    }

    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
