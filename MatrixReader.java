/**
 * 
 * This class reads in a string 1D array of the elements of a matrix and the
 * order of the matrix. The program then puts the contents of the into a int 2D
 * array and returns the order, matrix, and determinant to the main class.
 * 
 * @version 24 March 2016
 * @author Eric Vogel
 *
 */
public class MatrixReader {

    // Created a Determinant object to calculated the determinant from the
    // matrix.
    Determinant det = new Determinant();
    // Created a StringBuilder object to stored the order, matrix, and
    // determinant.
    StringBuilder output = new StringBuilder();

    /**
     * 
     * This method returns the order, matrix, and determinant to be written into
     * the output file.
     * 
     * @param arrayMatrix
     * @param order
     * @return Returns the output to the main class.
     */
    public String matrixReader(String[] arrayMatrix, int order) {

        // Error Handling.
        if (order > 0) {
            // Clears the string builder.
            output.setLength(0);

            // Appends the order of the matrix to the StringBuilder.
            output.append("\nThe order of your matrix is: " + order);

            // Creates a 2D matrix.
            int[][] twoDMatrix = new int[order][order];
            int x = 0;

            // Adds the contents of the 1D matrix into the 2D matrix.
            for (int i = 0; i < order; i++)
                for (int j = 0; j < order; j++) {
                    twoDMatrix[i][j] = Integer.parseInt(arrayMatrix[x]);
                    x++;
                }

            // Appends the 2D matrix to the StringBuilder.
            output.append("\nYour Matrix: ");
            for (int i = 0; i < order; i++) {
                output.append("\n");
                for (int j = 0; j < order; j++) {
                    output.append(twoDMatrix[i][j] + " ");
                }
            }
            output.append("\n");
            // Appends the determinant to the StringBuilder.
            output.append("\nThe determinant of you Matrix is: "
                    + Determinant.determinent(twoDMatrix, order) + "\n");

            // Returns the String form of the StringBuilder.
            return output.toString();
        }
        return "";

    }
}
