/**
 * CountShortestPath.java
 * 
 * Version: 1.0
 * 
 * Revision: initial
 */
import java.util.Scanner;

/**
 * This class is to store all the edges as an adjacency list 
 * 
 * @author Ragila Sandeep Kumar
 */
class Node1 {
	int data;
	Node1 next;
	public Node1(int data) {
		this.data = data;
	}
}

/**
 * This program will calculate number of shortest paths from the 
 * source to destination 
 *  
 * @author Ragila Sandeep Kumar
 * @author Niharika Bandla 
 */
class CountShortestPaths {
	static Node1 list[];
	boolean[] visit;
	int [] myQ;
	static int start=0,end=0,s,t;
	static int count=-1;
	static int[] routes,dist,prev,path;
	
	/**
	 * This method will calculate the number of shortest paths from s
	 * using the bfs search 
	 * 
	 * @param rootNode is the adjacency list which has all the nodes 
	 */
	public void method(Node1[] rootNode){
		visit=new boolean[rootNode.length];
		dist= new int[rootNode.length];
		prev= new int[rootNode.length];
		path= new int[rootNode.length];
		myQ=new int[rootNode.length];
		myQ[end]=s; //s as starting node 
		end++;
		visit[s]=true;
		dist[s]=0;
		path[s]=1;
		for(int j=start;j<end;j++){ //for dequeuing the queue until no more elements are left  
			int v = myQ[j];
			start++;
			Node1 adj=rootNode[v];
			while(adj!=null){ //to go thorugh all the adjacent edges 
				if(!visit[adj.data]){
					dist[adj.data] = dist[v] + 1;
					path[adj.data]=path[v];
					myQ[end]=adj.data;
					end++;
					visit[adj.data]=true;
				} 
				else{ //if the node is already visited 
					if(dist[adj.data]==dist[v]+1){
						path[adj.data]+=path[v];
					}
					else if(dist[adj.data]>dist[v]+1){
						dist[adj.data]=dist[v]+1;
						path[adj.data]=path[v];
					}
				}
				adj=adj.next;
			}
		}
	}
	
	/**
	 * this is the main method which will take the input graph and caluclatet
	 * the shortest paths from s to t 
	 * @param args
	 */
	public static void main(String[] args){
		Scanner ss= new Scanner(System.in);
		int n=ss.nextInt();
		int m=ss.nextInt();
		s=ss.nextInt();
		t=ss.nextInt();
		list=new Node1[n];
		Node1[] endnode=new Node1[n];
		for(int i=0;i<m;i++){
			int index=ss.nextInt();
			int second=ss.nextInt();
			if(list[index]==null) { //insert in the first position 
				Node1 newnode=new Node1(second);
				newnode.next=null;
				list[index]=newnode;
				endnode[index]=list[index];
			}
			else {  //insert in the last positions of the specified position 
				Node1 newnode= new Node1(second);
				newnode.next=null;
				endnode[index].next=newnode; //this will hold the last positions 
				endnode[index]=newnode;
			}
			if(list[second]==null){
				Node1 newnode=new Node1(index);
				newnode.next=null;
				list[second]=newnode;
				endnode[second]=list[second];
			}
			else {  //insert in the last positions of the specified position 
				Node1 newnode= new Node1(index);
				newnode.next=null;
				endnode[second].next=newnode; //this will hold the last positions 
				endnode[second]=newnode;
			}

		}
		CountShortestPaths c= new CountShortestPaths();
		c.method(list);
		System.out.println(path[t]);
	}
}
