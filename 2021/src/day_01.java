import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class day_01 {
	public static void main(String[]args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int count = 0;
		int[]arr=new int[3];
		int sum = 0;
		for(int i=0;i<arr.length;i++) {
			arr[i]=Integer.parseInt(reader.readLine());
			sum +=arr[i];
		}
		int last = sum;
		String str;
		while((str = reader.readLine())!= null && !str.isEmpty()) {
			sum-=arr[0];
			arr[0]=arr[1];
			arr[1]=arr[2];
			
			arr[2] = Integer.parseInt(str);
			sum+=arr[2];
			//System.out.println(sum);
			if(sum>last) {
				count++;
			}
			last = sum;
		}
		System.out.println(count);
	}
}