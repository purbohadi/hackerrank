package com.hackerrank.contest.projecteuler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OptimumPolynomial {
    private static int N;

    public static void main(String[] args) throws FileNotFoundException {
	/*
	 * Enter your code here. Read input from STDIN. Print output to STDOUT.
	 * Your class should be named Solution.
	 */
	Scanner sc = new Scanner(new FileInputStream(
		"inputOptimumPolynomial.txt"));

	N = sc.nextInt();
	long[] input = new long[N + 1];

	sc.nextLine();
	for (int i = 0; i < N + 1; i++) {
	    input[i] = sc.nextLong();
	}

	Lagrange(input);

    }

    public static void Lagrange(long[] input) {
	// Generate the generator polynomial
	long[] coefficients = input;
	Polynomial poly = new Polynomial(coefficients);

	// Generate the points
	long[] y = new long[coefficients.length];
	for (int i = 0; i < y.length; i++) {
	    y[i] = poly.evaluate(i + 1);
	}
	int count = 0;
	for (int n = 1; n <= coefficients.length - 1; n++) {
	    long result = 0;
	    for (int i = 1; i <= n; i++) {

		long temp1 = 1;
		long temp2 = 1;

		for (long j = 1; j <= n; j++) {
		    if (i == j) {
			continue;
		    } else {
			temp1 *= n + 1 - j;
			temp2 *= i - j;
		    }
		}
		result += temp1 * y[(i - 1)] / temp2;
	    }
	    if (count == N)
		break;
	    System.out.print(Modulo(result) + " ");
	    count++;
	}
    }

    public static long Modulo(long n)

    {
	long Answer = 0;
	Answer = (long) (n % (Math.pow(10, 9) + 7));
	return Answer;

    }

}

class Polynomial {

    private long[] coefficients;
    public long Degree;

    public Polynomial(int deg) {
	Degree = deg;
	coefficients = new long[(deg + 1)];
    }

    public Polynomial(long[] coefficients) {
	Degree = coefficients.length - 1;
	this.coefficients = coefficients;
    }

    public long get(long i) {
	return coefficients[(int) i];
    }

    public void set(int i, long value) {
	coefficients[(int) i] = value;
    }

    public long evaluate(long x) {
	long result = 0;

	for (long i = this.Degree; i >= 0; i--) {
	    result = result * x + get(i);
	}
	return result;
    }

}