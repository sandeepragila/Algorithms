/**
 * stringConvert.java
 * 
 * Revision: initial 
 * 
 * Version: 1.0
 */
import java.util.Scanner;

/**
 * This program will calculate the min cost needed to conver a string to another 
 * by using operations like delete, insert and replace. 
 * 
 * @author Ragila Sandeep Kumar
 * @author Niharika Bandla 
 */
public class stringConvert {

	static int S[][];
	int insert=4,delete=3,replace=5;
	int leftcell=0,bottomcell=0,cornercell=0;
	public void method(String s1,String s2){
		int m=s1.length()+1;
		int n=s2.length()+1;
		S= new int[s1.length()+1][s2.length()+1];
		//base case for the bottom elements 
		for(int i=0;i<m;i++){
			S[i][0]=i*delete;
		}
		//base case for the left elements 
		for(int i=0;i<n;i++){
			S[0][i]=i*insert;
		}
		//this loop is to calculate the min cost needed to convert 
		for(int i=1;i<m;i++){
			for(int j=1;j<n;j++){
				if(s1.charAt(i-1)!=s2.charAt(j-1)){
					leftcell=S[i-1][j]+delete;
					bottomcell=S[i][j-1]+insert;
					if(i>2){
						cornercell=S[i-2][j-1]+replace;
						S[i][j]=Math.min(leftcell, Math.min(bottomcell, cornercell));
					}
					else{
						S[i][j]=Math.min(leftcell, bottomcell);
					}
				}
				else{
					S[i][j]=S[i-1][j-1];
				}
			}
		}
	}
	
	/**
	 * This is the main method which will take the input from the user and calls the 
	 * min cost method 
	 * 
	 * @param args no command line arguments 
	 */
	public static void main(String[] args) {
		Scanner ss= new Scanner(System.in);
		String str1=ss.next();
		String str2=ss.next();
		stringConvert s= new stringConvert();
		s.method(str1, str2);
		System.out.println(S[1][1]);
		System.out.println(S[str1.length()][str2.length()]);
		
	}

}
