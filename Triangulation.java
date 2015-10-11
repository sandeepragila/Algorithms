/**
 * Triangulation.java
 * 
 * Revision: initial 
 * 
 * Version: 1.0
 */
import java.util.Scanner;

/**
 * This class is to store the points 
 */
class Point{
	float x,y;
	Point(float x,float y){
		this.x=x;
		this.y=y;
	}
}

/**
 * This program will calculate the minimum length triangulation possible from the 
 * given polygon vertices 
 * 
 * @author Ragila Sandeep Kumar 
 * @author Niharika Bandla 
 */
class Triangulation{

	static Point[] points;
	static int size;
	static int[] vert;
	static double[][] S;
	static int newsize;
	/**
	 * This is the main method which will take the input and store the points in the array 
	 * and then calls the method to find the minimum length of triangulation
	 *  
	 * @param args no command line arguments 
	 */
	public static void main(String[] args){
		Scanner s= new Scanner(System.in);
		size=s.nextInt();
		vert= new int[size];
		points= new Point[size];
		S= new double[size][size];
		newsize=size+1;
		for(int i=0;i<size;i++){
			float x=s.nextFloat();
			float y=s.nextFloat();
			points[i]= new Point(x,y);
			vert[i]=i;
		}
		Triangulation t= new Triangulation();
		t.method();
		System.out.println((int)S[1][size-1]);
	}
	
	/**
	 * This method will calculate the min length traingulation by using the 
	 * dynamic array 
	 */
	public void method(){
		//set all the diagonal values to 0 
		for(int i=0;i<size;i++){
			S[i][i]=0;
		}
		//for all the other positions calculate the triangulation 
		for(int s=0;s<size;s++){
			int i=0; 
			for(int j=s+1;j<size;j++){ //taking the next vertex other than the present 
				double min = 0;
				for(int k=i;k<j;k++){ //go through all the verteces from i to j 
					double one;
					if((i-1)<0){
						int x=size-1;
						one=(S[x][k]+S[k+1][j]+weight(x-1,k)+weight(k,j));
					}
					else{
						one=(S[i][k]+S[k+1][j]+weight(i-1,k)+weight(k,j));
					}
					if(k==(i)) 
						min=one;
					else{
						if(min>one){
							min=one;
						}
					}
				}
				S[i][j]=min; //store the minimum
				i++;
			}
		}
	}

	/**
	 * This method will calculate the length of the sides of the triangile 
	 * but if the vertexes are adjacnet it will return 0 
	 * 
	 * @param a index of vertex 1 
	 * @param b index of vertex 2
	 * 
	 * @return return the length of the triangle 
	 */
	public double weight(int a,int b){
		if(Math.abs(a-b)<=1)
			return 0;
		double aby= Math.pow((points[b].y-points[a].y), 2);
		double abx=Math.pow((points[b].x-points[a].x), 2);
		double dist1= Math.sqrt(aby+abx);
		return dist1;
	}
}