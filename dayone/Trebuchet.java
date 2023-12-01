import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trebuchet {
    public static void main(String args[]) {
        try {
            File file = new File("dayone/input.txt");
            Scanner scanner = new Scanner(file);

            Pattern pattern = Pattern.compile("\\d");
            int result = 0;
            while (scanner.hasNextLine()) {
                StringBuilder sb = new StringBuilder();
                String nextLine = scanner.nextLine();
                Matcher match = pattern.matcher(nextLine);

                List<String> matches = new ArrayList<>();

                while (match.find()) {
                    matches.add(match.group());
                }
                if (matches.isEmpty()) {
                    continue;
                } else if (matches.size() == 1){
                    sb.append(matches.get(0));
                    sb.append(matches.get(0));
                } else {
                    sb.append(matches.get(0));
                    sb.append(matches.get(matches.size() - 1));
                }
                result += Integer.parseInt(sb.toString());
            }
            System.out.print(result);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception occurred: " + e);
        }

    }
}