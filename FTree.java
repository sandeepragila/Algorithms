/**
 * FTree.java
 * 
 * Revision: initial
 * 
 * Version: 1.0
 */

import java.util.Scanner;

/**
 * This class is used to store the edges  of the graph and the cost of the
 * edges 
 * 
 * @author Ragila Sandeep Kumar 	
 * @author Niharika Bandla 
 */
class edge{
	int vert1;
	int vert2;
	int cost;
	boolean inserted;
	edge(int vert1,int vert2,int cost){
		this.vert1=vert1;
		this.vert2=vert2;
		this.cost=cost;
	}
	void setInserted(){
		this.inserted=true;
	}
}

/**
 * This class is the implementation of the kruskals algorithm 
 * to find the minimum cost spanning tree given an subtree F
 * 
 * @author Ragila Sandeep Kumar
 * @author Niharika Bandla 
 */
class FTree{
	static int n;
	static int m;
	static edge Nodes[];
	static int boss[];
	static int size[];
	static int mincost;
	
	/**
	 * This method will calculate the kruskals algorithm to find the 
	 * minimum spanning tree 
	 */
	public void kruskal(){
		MergeSort s= new MergeSort();
		s.merge(Nodes, 0, m-1);
		for(int i=0;i<m;i++){ //for all edges in sorting order 
			if(!Nodes[i].inserted){ //if an edge is not already added to MST
				if(size[boss[Nodes[i].vert1]]==n|| size[boss[Nodes[i].vert2]]==n)
					break;
				if(boss[Nodes[i].vert1]!=boss[Nodes[i].vert2]){//if boss is not same 
					if(size[boss[Nodes[i].vert1]]>=size[boss[Nodes[i].vert2]]){
						size[boss[Nodes[i].vert1]]+=size[boss[Nodes[i].vert2]];
						for(int j=0;j<n;j++){
							if(boss[j]==boss[Nodes[i].vert2]){
								boss[j]=boss[Nodes[i].vert1];
							}
						}
						mincost+=Nodes[i].cost;
						Nodes[i].setInserted();
					} //if size of first node boss is less 
					else if(size[boss[Nodes[i].vert1]]<size[boss[Nodes[i].vert2]]){
						size[boss[Nodes[i].vert2]]+=size[boss[Nodes[i].vert1]];
						for(int j=0;j<n;j++){
							if(boss[j]==boss[Nodes[i].vert1]){
								boss[j]=boss[Nodes[i].vert2];
							}
						}
						mincost+=Nodes[i].cost;
						Nodes[i].setInserted();
					}
				}
			}
		}
		System.out.println(mincost);
	}
	
	/**
	 * This is the main method which will take the input and does the 
	 * kruskals algorithm 
	 * 
	 * @param args no command line arguments 
	 */
	public static void main(String[] args){
		Scanner s= new Scanner(System.in);
		n=s.nextInt();
		m=s.nextInt();
		Nodes=new edge[m];
		boss= new int[n];
		size= new int[n];
		for(int i=0;i<n;i++){
			boss[i]=i;
			size[i]=1;
		}
		for(int i=0;i<m;i++){ //take the input and add the F graph to MST
			edge e= new edge(s.nextInt(),s.nextInt(),s.nextInt());
			Nodes[i]=e;
			if(s.nextInt()==1){ //if the edge has to be in MST
				if(boss[Nodes[i].vert1]!=boss[Nodes[i].vert2]){
					if(size[boss[Nodes[i].vert1]]>=size[boss[Nodes[i].vert2]]){
						size[boss[Nodes[i].vert1]]+=size[boss[Nodes[i].vert2]];
						for(int j=0;j<n;j++){
							if(boss[j]==boss[Nodes[i].vert2]){
								boss[j]=boss[Nodes[i].vert1];
							}
						}
						mincost+=Nodes[i].cost;
						Nodes[i].setInserted();
					}//for different size of bosses 
					else if(size[boss[Nodes[i].vert1]]<size[boss[Nodes[i].vert2]]){
						size[boss[Nodes[i].vert2]]+=size[boss[Nodes[i].vert1]];
						for(int j=0;j<n;j++){
							if(boss[j]==boss[Nodes[i].vert1]){
								boss[j]=boss[Nodes[i].vert2];
							}
						}
						mincost+=Nodes[i].cost;
						Nodes[i].setInserted();
					}
				}
				else{
					System.out.println("-1");
					System.exit(1);
				}
			}
		}
		FTree k= new FTree();
		k.kruskal();
	}
}