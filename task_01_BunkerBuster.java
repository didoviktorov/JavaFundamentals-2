import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class task_01_BunkerBuster {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Reading dimensions
        String[] dimensions = reader.readLine().split("\\s+");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        // Filling the matrix
        int[][] matrix = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            String[] cells = reader.readLine().split("\\s+");;
            for (int col = 0; col < cells.length; col++) {
                matrix[row][col] = Integer.parseInt(cells[col]);
            }
        }

        String line = reader.readLine();
        while (!line.equals("cease fire!")) {
            String[] bombCords = line.split("\\s+");
            int bombRow = Integer.parseInt(bombCords[0]);
            int bombCol = Integer.parseInt(bombCords[1]);
            int bombPower = bombCords[2].charAt(0);
            int reducedPower = (int)Math.ceil(bombPower / 2f);

            // Bombing :)
            matrix[bombRow][bombCol] -= bombPower;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    // Left side
                     if (row == bombRow - 1 && col == bombCol - 1) {
                         matrix[row][col] -= reducedPower;
                     } else if (row == bombRow && col == bombCol - 1) {
                         matrix[row][col] -= reducedPower;
                     } else if (row == bombRow + 1 && col == bombCol - 1) {
                         matrix[row][col] -= reducedPower;
                     }

                     // Right side
                    if (row == bombRow - 1 && col == bombCol + 1) {
                        matrix[row][col] -= reducedPower;
                    } else if (row == bombRow && col == bombCol + 1) {
                        matrix[row][col] -= reducedPower;
                    } else if (row == bombRow + 1 && col == bombCol + 1) {
                        matrix[row][col] -= reducedPower;
                    }

                    // Middle
                    if (row == bombRow - 1 && col == bombCol) {
                        matrix[row][col] -= reducedPower;
                    } else if (row == bombRow + 1 && col == bombCol) {
                        matrix[row][col] -= reducedPower;
                    }
                }
            }

            line = reader.readLine();
        }

        int cellsDestroyed = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] <= 0) {
                    cellsDestroyed++;
                }
            }
        }

        System.out.println("Destroyed bunkers: " + cellsDestroyed);
        System.out.println(String.format("Damage done: %.1f ", 100f * cellsDestroyed / (rows * cols)) + "%");
    }
}
