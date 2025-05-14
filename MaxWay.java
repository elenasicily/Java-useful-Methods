/**
 * Computes the maximum sum obtainable by moving through a 2D grid 
 * from the top-left corner to the bottom-right corner, 
 * using only movements to the right or downward. 
 * Also reconstructs the path that leads to this maximum sum.
 * 
 * The method uses dynamic programming to calculate the optimal values 
 * and backtracking to recover the corresponding path.
 *
 * @param grid the 2D integer array representing the value in each cell
 * @return a result object or data structure containing the maximum sum 
 *         and the list of coordinates (or directions) representing the path
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MaxWay {


    public static void main(String[] args) throws IOException {
        
      
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

        String input = reader.readLine();
        String [] arrStrings = input.trim().split(" ");
        int n = Integer.parseInt(arrStrings[0]);
        int m = Integer.parseInt(arrStrings[1]);

        if ((n<1 || n>20) || (m<1 || m>20)) System.exit(1);
        int [][] table = new int[n][m];
            
        for (int i=1; i<= n; i++){
            input = reader.readLine();
            arrStrings = input.trim().split(" ");

            for (int j=1; j<=m; j++){
                int elem = Integer.parseInt(arrStrings[j-1]);
                if (elem <0 || elem > 100) System.exit(1); 

                if ((i-1)==0 && (j-1)==0){ 
                table [i-1][j-1] = elem;
                continue;
                }
               
                if ((i-1)==0) {                
                table [i-1][j-1] = table [i-1][j-2] + elem;
                continue;
                } 

                if (j-1 == 0) {
                table [i-1][j-1] = table [i-2][j-1] + elem;
                continue;
                }

                table [i-1][j-1] = Math.max((table[i-1][j-2] + elem),(table[i-2][j-1] + elem)); 
            }
        }

        writer.write(String.valueOf(table[n-1][m-1]) );
        writer.newLine();
        writer.flush();

        StringBuilder path = new StringBuilder();
        int i= n-1;
        int j= m-1;
        while (i>0 || j>0) {          
           
            if (i>0 && j>0) {
               if (table [i-1][j] >= table [i][j-1]) {
                path.append("D ");
                i--;
                } else {
                path.append("R ");
                j--;
                }
            } else if (i==0) {
                 path.append("R ");
                 j--;
            } else {
                 path.append("D ");
                 i--;
            }
               
        }
    

         writer.write(path.reverse().toString());
         writer.flush();
            
        
       } catch (IOException e) {
          System.out.println(e);
    }      
  
    }

}
    
