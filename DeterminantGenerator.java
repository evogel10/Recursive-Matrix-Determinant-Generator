import java.io.*;
import java.util.Scanner;

/**
 * 
 * This class receives input from a file containing N amount of matrices of
 * varying order and writes to a output file the order of the matrix, the matrix
 * itself, and the determinant of that matrix.
 * 
 * @version 24 March 2016
 * @author Eric Vogel
 *
 */
public class DeterminantGenerator {

    /**
     * Main class to the program.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        // Prompts the User for the input file.
        System.out.print("Please Enter input File Name: ");
        String inputFileName = sc.next();
        // Prompts the User for the output file.
        System.out.print("Please Enter output File Name: ");
        String outputFileName = sc.next();
        sc.close();

        // Created a MatrixReader object to be used to take in the matrix in
        // string form and the order.
        MatrixReader matrix = new MatrixReader();

        // This will reference one line at a time.
        String line = null;
        int order = 0;
        int matrixCounter = 0;

        try {
            // FileReader reads text files in.
            FileReader fileReader = new FileReader(inputFileName);
            // FileWriter writes the text files into the output file.
            FileWriter fileWriter = new FileWriter(outputFileName);

            // Reads text from a character-input stream.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // Writes text to a character-output stream.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Created a StringBuilder object to stored the contents of the
            // matrix.
            StringBuilder MatrixStrBuilder = new StringBuilder();

            // While the file still is not empty, the program will continue to
            // read it.
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();

                // If there is a blank space in the file, the order is set equal
                // to zero representing no matrix.
                if (line.equals("")) {
                    order = 0;
                }
                // Checks to see if the file contains a letter for the order.
                else if (line.matches("[a-zA-Z]+")
                        || !(line.matches("[-+]?\\d*\\.?\\d+"))
                        || line.matches("[^\\dA-Za-z ]")) {
                    bufferedWriter.write("Invalid Matrix.\n");
                } else {
                    order = Integer.parseInt(line);
                }

                // If the is greater than zero, the program will calculate the
                // determinant
                if (order > 0) {
                    for (int x = 0; x < order; x++) {
                        if ((line = bufferedReader.readLine()) != null) {
                            if (line.matches("[a-zA-Z]+")) {
                                bufferedWriter.write("Invalid Matrix.\n");
                            } else {
                                MatrixStrBuilder.append(line);
                                MatrixStrBuilder.append(";");
                            }
                        }
                    }
                    // If there is a blank space in the file, the order is set
                    // equal
                    // to zero representing no matrix.
                    if (line.equals("")) {
                        order = 0;
                    }
                    // A String of the Matrix with spaced out by the delimiter
                    // ";".
                    String delimiterMatrix = MatrixStrBuilder.toString()
                            .replaceAll("\\s+", ";");
                    // An 1D String array of the Matrix.
                    String[] arrayMatrix = delimiterMatrix.split(";");

                    for (String element : arrayMatrix) {
                        matrixCounter++;
                        if (element.matches("[a-zA-Z]+")
                                || element.matches("[^\\dA-Za-z ]")) {
                            order = 0;
                            bufferedWriter.write("\nInvalid Matrix.\n");
                        }

                    }
                    // If the order is zero you can not perform the modulo
                    // operation. If the order is 1, then the matrixCounter
                    // should also be 1.
                    if (order == 1) {
                        if (matrixCounter != 1) {
                            order = 0;
                            bufferedWriter.write("\nInvalid Matrix.\n");
                        }
                    }
                    if (order > 1) {
                        // The order should divide evenly into the amount of
                        // element in the matrix.
                        if (!(matrixCounter % order == 0)) {
                            order = 0;
                            bufferedWriter.write("\nInvalid Matrix.\n");
                        }
                    }
                    // if (!contains) {
                    // Error Handling.
                    if (delimiterMatrix.equals(""))
                        order = 0;
                    bufferedWriter.write(matrix
                            .matrixReader(arrayMatrix, order));
                    // Clears the string builder.
                    MatrixStrBuilder.setLength(0);
                    matrixCounter = 0;
                    // }
                }
            }

            // Closes files.
            bufferedReader.close();
            bufferedWriter.close();

            // Used if the input file can't opened.
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + inputFileName + "'");

            // Used if there is an error reading the input file.
        } catch (IOException ex) {
            System.out.println("Error reading file '" + inputFileName + "'");

        }
    }
}
