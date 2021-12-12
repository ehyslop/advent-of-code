import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class day_11 {
    public static void main(String[]args) throws NumberFormatException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//part1(reader);
		part2(reader);
    }

    public static void part1(BufferedReader reader) throws NumberFormatException, IOException {
		String str;
		int count = 0;
        int[][]arr=new int[10][10];
		for(int i=0;i<arr.length;i++){
            str = reader.readLine();
            for(int j=0;j<arr[i].length;j++){
                arr[i][j]=str.charAt(j)-'0';
            }
        }

        for(int k=0;k<100;k++){
            for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[i].length;j++){
                    arr[i][j]++;
                }
            }
            for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[i].length;j++){
                    if(arr[i][j]>9){
                        count += traverse(arr, i, j);
                    }
                }
            }
        }
		System.out.println(count);
	}

    static int[][]moves = new int[][]{{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,1},{1,-1},{-1,-1}};
    public static int traverse(int[][]arr, int y, int x){
        arr[y][x] = 0;
        int count = 1;
        for(int i=0;i<moves.length;i++){
            int currY = moves[i][0]+y;
            int currX = moves[i][1]+x;
            if(currY>=0&&currY<10&&currX>=0&&currX<10&&arr[currY][currX]!=0){
                arr[currY][currX]++;
                if(arr[currY][currX]>9)
                    count += traverse(arr, currY, currX);
            }
        }
        return count;
    }

	public static void part2(BufferedReader reader) throws NumberFormatException, IOException {
		String str;
        int[][]arr=new int[10][10];
		for(int i=0;i<arr.length;i++){
            str = reader.readLine();
            for(int j=0;j<arr[i].length;j++){
                arr[i][j]=str.charAt(j)-'0';
            }
        }

        for(int k=1;;k++){
            for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[i].length;j++){
                    arr[i][j]++;
                }
            }
            int count = 0;
            for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[i].length;j++){
                    if(arr[i][j]>9){
                        count += traverse(arr, i, j);
                    }
                }
            }
            if(count == 100){
                System.out.println(k);
                break;
            }
        }
	}
}
