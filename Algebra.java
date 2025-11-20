public class Algebra {
    public static void main(String args[]) {
        // Tests some of the operations
        System.out.println(plus(2,3));   // 2 + 3
        System.out.println(minus(7,2));  // 7 - 2
        System.out.println(minus(2,7));  // 2 - 7
        System.out.println(times(3,4));  // 3 * 4
        System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
        System.out.println(pow(5,3));      // 5^3
        System.out.println(pow(3,5));      // 3^5
        System.out.println(div(12,3));   // 12 / 3    
        System.out.println(div(5,5));    // 5 / 5  
        System.out.println(div(25,7));   // 25 / 7
        System.out.println(mod(25,7));   // 25 % 7
        System.out.println(mod(120,6));  // 120 % 6    
        System.out.println(sqrt(36));
        System.out.println(sqrt(263169));
        System.out.println(sqrt(76123));
    }  

    // Returns x1 + x2
    public static int plus(int x1, int x2) {
        if (x2 < 0) {
            for (int i = 0; i > x2; i--) {
                x1--;
            }
        } else {
            for (int i = 0; i < x2; i++) {
                x1++;
            }
        }
        return x1;
    }

    // Returns x1 - x2
    public static int minus(int x1, int x2) {
        if (x2 < 0) {
            // Subtracting a negative number is like adding a positive
            for (int i = 0; i > x2; i--) {
                x1++;
            }
        } else {
            for (int i = 0; i < x2; i++) {
                x1--;
            }
        }
        return x1;
    }

    // Returns x1 * x2
    public static int times(int x1, int x2) {
        int result = 0;
        // Determine the sign of the result
        boolean negative = (x1 < 0 && x2 > 0) || (x1 > 0 && x2 < 0);
        
        // Convert both to positive for the loop (using our fixed minus function)
        int absX1 = x1 < 0 ? minus(0, x1) : x1;
        int absX2 = x2 < 0 ? minus(0, x2) : x2;

        for (int i = 0; i < absX2; i++) {
            result = plus(result, absX1);
        }
        
        return negative ? minus(0, result) : result;
    }

    // Returns x^n (for n >= 0)
    public static int pow(int x, int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result = times(result, x);
        }
        return result;
    }

    // Returns the integer part of x1 / x2 
    public static int div(int x1, int x2) {
        boolean negative = (x1 < 0 && x2 > 0) || (x1 > 0 && x2 < 0);
        int absX1 = x1 < 0 ? minus(0, x1) : x1;
        int absX2 = x2 < 0 ? minus(0, x2) : x2;

        int count = 0;
        while (absX1 >= absX2) {
            absX1 = minus(absX1, absX2);
            count++;
        }

        return negative ? minus(0, count) : count;
    }

    // Returns x1 % x2
    public static int mod(int x1, int x2) {
        int absX1 = x1 < 0 ? minus(0, x1) : x1;
        int absX2 = x2 < 0 ? minus(0, x2) : x2;

        while (absX1 >= absX2) {
            absX1 = minus(absX1, absX2);
        }

        return absX1; // Usually mod result is positive in this context
    }

    // Returns the integer part of sqrt(x) 
    public static int sqrt(int x) {
        int result = 0;
        while (times(result + 1, result + 1) <= x) {
            result++;
        }
        return result;
    }         
}