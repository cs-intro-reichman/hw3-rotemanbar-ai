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
		int sum = x1;
		for (int i = 0; i < x2; i++) {
			sum++;
		}
		return sum;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		int sum = x1;
		for (int i = 0; i < x2; i++) {
			sum--;
		}
		return sum;
	}

	// Returns x1 * x2
    public static int times(int x1, int x2) {
    boolean negative = false;
    if (x2 < 0) {
        negative = true;
        x2 = minus(0, x2);
    }

    int result = 0;
    int current = x1;

    while (x2 > 0) {
        if (mod(x2, 2) == 1) {
            result = plus(result, current);
        }
        current = plus(current, current);
        x2 = div(x2, 2);
    }

    return negative ? minus(0, result) : result;
}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
    boolean negative = (x < 0 && mod(n, 2) == 1);
    if (x < 0) x = minus(0, x);

    int result = 1;
    int base = x;

    while (n > 0) {
        if (mod(n, 2) == 1) {
            result = times(result, base);
        }
        base = times(base, base);
        n = div(n, 2);
    }

    return negative ? minus(0, result) : result;
}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
	boolean negative = (x1 < 0 && x2 > 0) || (x1 > 0 && x2 < 0);
	if (x1 < 0) x1 = minus(0, x1);
	if (x2 < 0) x2 = minus(0, x2);

	int count = 0;
	int temp = x1;
	while (temp >= x2) {
		temp = minus(temp, x2);
		count++;
	}

	if (negative) count = minus(0, count);
	return count;
}


	// Returns x1 % x2
	public static int mod(int x1, int x2) {
    int q = div(x1, x2);
    int product = times(q, x2);
    return minus(x1, product);
}
	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		int n = 0;
		while (times(n, n) <= x) {
			n++;
		}
		return minus(n, 1);
	}	  	  
}