import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MatrixMultiplication {
	public static void main(String[] args) {
		try {
			FileInputStream fstream = new FileInputStream("input.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;

			int amount = 0, counter = 0;

			while ((strLine = br.readLine()) != null) {
				amount = Integer.parseInt(strLine);
				break;
			}

			int[][] arr = new int[amount][2];

			while ((strLine = br.readLine()) != null) {
				arr[counter][0] = Integer.parseInt(strLine.split(" ")[0]);
				arr[counter][1] = Integer.parseInt(strLine.split(" ")[1]);
				counter++;
			}

			int count = 0, cycle = 1, ak = 0;
			int[][] resultMatrix = new int[amount][amount];
			// diagonal cycle
			for (int c = 0; c <= amount - 1; c++) {
				for (int i = 0, j = c; i >= 0 && j <= amount - 1; i++, j++) {					
					int min[] = new int[count + 1];	
					for (int z = count; z >= 0; z--) {
						min[z] = resultMatrix[i][j - z] + resultMatrix[i + cycle][j]
								+ arr[i][0] * arr[i + cycle][0] * arr[i + cycle][1];
						if(count == 1 && z <= 2) {
							resultMatrix[i][j] = min[z];
							continue;
						}
						if (count >= 0 && z <= 2 && min[z] < min[z + 1]) {
							resultMatrix[i][j] = min[z];
						}
						cycle++;
					}
					count++;
					System.out.println(resultMatrix[i][j]);
					ak = resultMatrix[i][j];
				}
				count++;
				cycle = 0;
			}
			OutputStream f = new FileOutputStream("output.txt", true);
			OutputStreamWriter w = new OutputStreamWriter(f);
			BufferedWriter o = new BufferedWriter(w);
			o.write(Long.toString(ak));
			o.flush();
			br.close();
		} catch (IOException e) {
			System.out.println("Îøèáêà");
		}
	}
}
