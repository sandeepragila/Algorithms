/**
 * NoThree.java
 * 
 * Revision: initial
 * 
 * Version: 1.0
 */
import java.util.Scanner;

/**
 * This program will give the maximum sum possible by the subsequence such that
 * no three elements are consecutive 
 * 
 * @author Ragila Sandeep Kumar
 * @author Niharika Bandla 
 */
public class NoThree {
	static int[] arr;
	static int size;
	static int[] S;
	/**
	 * This method will calculate the max sum using the dynamic array 
	 * where each subproblem will calculate the max sum till that element 
	 */
	public void LCS(){
		S[0]=0;
		S[1]=arr[0];
		S[2]=arr[0]+arr[1];
		for(int i=3;i<size+1;i++){
			int a=arr[i-1]+S[i-2];
			int b=S[i-3]+arr[i-1]+arr[i-2];
			if(a>b)
				S[i]=a;
			else
				S[i]=b;
			if(S[i-1]>S[i]){
				S[i]=S[i-1];
			}
		}
	}
	/**
	 * This is the main method which will take the input from the user and calls the 
	 * LCS method 
	 * 
	 * @param args no command line arguments 
	 */
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		size=s.nextInt();
		arr=new int[size];
		S= new int[size+1];
		for(int i=0;i<size;i++)
			arr[i]=s.nextInt();
		NoThree n= new NoThree();
		n.LCS();
		System.out.println(S[size]);
	}	
}
