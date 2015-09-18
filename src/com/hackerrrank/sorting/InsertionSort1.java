package com.hackerrrank.sorting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InsertionSort1 {

    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new FileInputStream("inputInsertionSort1.txt"));
	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {

	}
	int N = sc.nextInt();
	int[] arr = new int[N];

	for (int i = 0; i < N; i++) {
	    arr[i] = sc.nextInt();
	}

	insertIntoSorted(arr);
    }

    public static void insertIntoSorted(int[] ar) {
	// Fill up this function

	for (int i = 1; i < ar.length; i++) {
	    int temp = ar[i];
	    int j;
	    for (j = i - 1; j >= 0 && temp < ar[j]; j--) {
		ar[j + 1] = ar[j];
		for (int k = 0; k < ar.length; k++) {
		    System.out.print(ar[k] + " ");
		}
		System.out.println();
	    }
	    ar[j + 1] = temp;
	}
	
	for (int i = 0; i < ar.length; i++) {
	    System.out.print(ar[i]+" ");
	}

    }
}
