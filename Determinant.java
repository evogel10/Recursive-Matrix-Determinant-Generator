/**
 * 
 * This class contains a recursive algorithm that takes in a 2D array matrix and
 * its order and calculates the determinant.
 * 
 * @version 24 March 2016
 * @author Eric Vogel
 *
 */
class Determinant {

    /**
     * 
     * The method recursively calculates the determinant of a matrix.
     * 
     * @param twoDMatrix
     * @param order
     * @return Returns the determinant of the matrix.
     */
    public static double determinent(int[][] twoDMatrix, int order) {
        int minor[][] = new int[order][order];

        double determinant = 0;
        // If there is only one element in the matrix, the determinant is the
        // element.
        if (order == 1) {
            determinant = twoDMatrix[0][0];
            // If the order of the matrix is 2 there is a simple calculation to
            // determine the determinant.
        } else if (order == 2) {
            determinant = ((twoDMatrix[0][0] * twoDMatrix[1][1]) - (twoDMatrix[0][1] * twoDMatrix[1][0]));
            // Orders greater than 2 require the recursive algorithm.
        } else {
            for (int x = 0; x < order; x++) {
                int h = 0;
                int k = 0;
                for (int i = 1; i < order; i++) {
                    for (int j = 0; j < order; j++) {
                        if (j == x)
                            continue;
                        minor[h][k] = twoDMatrix[i][j];
                        k++;
                        if (k == order - 1) {
                            h++;
                            k = 0;
                        }
                    }
                }
                // Method recursively calls itself.
                determinant = determinant + twoDMatrix[0][x]
                        * Math.pow((-1), x) * determinent(minor, order - 1);
            }
        }
        return determinant;
    }
}