// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		iterationCounter = 0;
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		iterationCounter = 0;
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {
    double actualRate = rate / 100.0;
    for (int i = 0; i < n; i++) {
        // הסדר הנכון לפי המטלה: קודם מפחיתים תשלום, ואז מוסיפים ריבית
        loan = loan - payment;
        loan = loan * (1 + actualRate);
    }
    return loan;
}}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
    double g = loan / n;
    double step = epsilon;
    iterationCounter = 0;

    // כל עוד נשאר חוב (היתרה גדולה מ-0), נגדיל את התשלום
    // אין צורך ב-Math.abs כאן, כי אנחנו מחפשים את המעבר מחיובי לשלילי
    while (endBalance(loan, rate, n, g) > 0) {
        g += step;
        iterationCounter++;
    }

    return g;
}


    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        iterationCounter = 0;
        double H = loan;
		while (endBalance(loan, rate, n, H) > 0) {
			H = H + 100;
		}
		double L = loan / n;
		double g = (L + H) / 2;
		while (Math.abs(H - L) > epsilon) {
        iterationCounter++;
		double f_g = endBalance(loan, rate, n, g);
		if (Math.abs(f_g) <= epsilon) {
            return g;
        }
		double f_L = endBalance(loan, rate, n, L);
		if (f_g * f_L > 0) {
			L = g;
		}
		else {
			H = g;
		}
		g = (L + H) / 2;

		}
		return g;
    }
}