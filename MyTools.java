
import java.util.*;
import java.lang.*;

public final class MyTools {

	public static int n;
	public static int cost = 0;
	static int counter = 0;
	public static int[][] towers;
	
	private MyTools() {

	}
	
	public static int removeDuplicates(ArrayList A) {

		for (int i = A.size() - 1; i > 0; i--) {

			for (int j = i - 1; j >= 0; j--) {
				String start = A.get(i).toString();
				String finish = A.get(j).toString();

				if (start.equalsIgnoreCase(finish)) {

					A.remove(j);

					break;
				}
			}
		}
		return 0;

	}

	
	public static void moveDisks(int n) {

		
		String source = "A";
		String dest = "B";
		String aux = "C";
		int newN = 0;

		System.out.println("List of the moves for " + n + " disks:");
		moveDisksCore(n, newN, source, dest, aux);
		System.out.println("The desttal number of moves is " + counter);
	}

	public static void moveDisksCore(int n, int newN, String source, String dest, String aux) {
		counter++;

		if (newN == n) {
			System.out.println("Move disk 1 source " + source + " dest " + dest);
		} else {
			moveDisksCore(newN + 1, newN, source, aux, dest);
			System.out.println("Move disk " + newN + " source " + source + " dest " + dest);
			moveDisksCore(newN + 1, newN, aux, dest, source);
		}

	}
				
		public static void moveDisksStatus(int n) {
			MyTools hanoiTower = new MyTools(n);
			System.out.print("The original position: ");
			print();
			solve(n, 'A', 'B', 'C');
			System.out.println("The total cost is " + cost);
		}

		public MyTools(int n) {
			MyTools.n = n;
			towers = new int[n][];

			for (int i = 0; i < n; i++) {
				towers[i] = new int[3];

				for (int j = 0; j < 3; j++) {
					towers[i][j] = 0;
				}

			}
			for (int i = n - 1; i >= 0; i--) {
				towers[i][0] = i + 1;
			}
		}
		
		public static void solve(int n, char source, char inter, char dest) {
			
			if (n == 1) {
				cost += 1;
				System.out.print("Move disk a from " + source + " to " + dest + ": ");
				updateTowers(n, source, dest);
				print();
			} else {
				cost = cost + n;
				solve(n - 1, source, dest, inter);
				System.out.print("Move disk " + (char)(n+'a'-1) + " from " + source + " to " + dest + ": ");
				updateTowers(n, source, dest);
				print();
				solve(n - 1, inter, source, dest);
			}
		}
		
		
		public static int topRow(char tower) {
			int i = n - 1;

			while (i >= 0 && towers[i][tower - 65] != 0) {
				i--;
			}
			return i;
		}
		
		public static void updateTowers(int n, char source, char dest) {
			int topTo = topRow(dest);
			int topFrom = topRow(source);
			towers[topTo][dest - 65] = towers[topFrom + 1][source - 65];
			towers[topFrom + 1][source - 65] = 0;
		}

		public static void print() {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < 3; j++) {
					if(towers[j][i] == 0) {
						System.out.print("-");
					}
					else {
						System.out.print((char)((towers[j][i])+'a'-1));
					}
				}
				System.out.print(" ");
			}
			System.out.println();
		}

		public static void main(String[] args) {

			moveDisksStatus(3);
		}
	}
