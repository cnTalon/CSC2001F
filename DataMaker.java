import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class DataMaker {

    private DataMaker() {}

    final static String FILE_NAME = "src/students.txt";
    private static boolean isInteger(final String string) {
        for(int i=0; i<string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        String[] input = s.nextLine().split(" ");

        if (input.length!=2 || input[0].length()!=9 || !isInteger(input[1])) {
            System.out.println("java DataMaker <username> <quantity>");
        }
        else {
            final String username = input[0];

            final List<String> allNames = new ArrayList<String>();
            try {
                final Scanner scanner = new Scanner(new File(FILE_NAME));
                while (scanner.hasNextLine()) { allNames.add(scanner.nextLine().trim()); }
                scanner.close();
            }
            catch (FileNotFoundException fnfE) {
                System.out.printf("File '%s' not found.\n", FILE_NAME);
                System.exit(-1);
            }

            // Output personalised list
            int index = allNames.indexOf(username);
            if (index == -1) {
                System.out.printf("Username '%s' not found.", username);
                System.exit(-1);
            }

            // write data to new file
            try {
                FileWriter w = new FileWriter("src/mydata.txt", false);
                for(int size = Integer.parseInt(input[1]); size>0; size--) {
                    w.write(allNames.get(index) + "\n");
                    index = (index+1)%allNames.size();
                }
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        s.close();
    }
}