package ProgramPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class CsvParser {
	
	public void setRoomDescs(String path, Cell[][] grid) throws IOException
	{
		 BufferedReader csvReader
		   = new BufferedReader(new FileReader(path));
		
		LinkedList<String[]> rows = new LinkedList<String[]>();
		String dataRow = csvReader.readLine();
		// Read the number of the lines in .csv file 
		// i = row of the .csv file 
		while (dataRow != null) {
		    rows.addLast(dataRow.split("¤"));
		    dataRow = csvReader.readLine();
		}

		String[][] csvMatrix = rows.toArray(new String[rows.size()][]);
		
		for (int i=0; i < csvMatrix.length; i++) {
			for (int j=0; j < csvMatrix[i].length; j++) {
				grid[j][grid[j].length - i -2].setDesc(csvMatrix[i][j]);
			}
		}
	}
}