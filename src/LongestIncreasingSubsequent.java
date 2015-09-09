import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LongestIncreasingSubsequent {

    public static int Answer;
    public static int[] array, lis;

    public static void main(String[] args) throws FileNotFoundException {

	Scanner sc = new Scanner(new FileInputStream(
		"inputLongestIncreasingSubsequent.txt"));
	int N = sc.nextInt();
	array = new int[N];

	for (int i = 0; i < N; i++) {
	    array[i] = sc.nextInt();
	}

	lis = new int[N];

	for (int i = 0; i < N; i++) {// initialize LIS array
	    lis[i] = 1;
	}

	for (int i = 1; i < N; i++) {
	    for (int j = 0; j < i; j++) {
		if (array[i] > array[j] && lis[i] < lis[j] + 1) {
		    lis[i] = lis[j] + 1;
		}
	    }
	}

	for (int i = 0; i < lis.length; i++) {
	    if (Answer < lis[i]) {
		Answer = lis[i];
	    }
	}

	System.out.println(Answer);
	System.out.println();

    }
}
