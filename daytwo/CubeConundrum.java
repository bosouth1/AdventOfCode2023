import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CubeConundrum {
   
    public static int RED = 12;
    public static int GREEN = 13;
    public static int BLUE = 14;

    public static void main(String[] args) {
        try {
            File file = new File("daytwo/input.txt");
            Scanner scanner = new Scanner(file);
            int id = 1;
            int result = 0;
            int power = 0;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();

                Pattern pattern = Pattern.compile("Game [0-9]+: ");
                Matcher match = pattern.matcher(nextLine);

                if (match.find()) {
                    nextLine = nextLine.replace(match.group(), "");
                    String[] rounds = nextLine.split("; ");
                    int blueMin = 0;
                    int redMin = 0;
                    int greenMin = 0;
                    boolean possible = true;
                    for (String round : rounds) {
                        Pattern bluePattern = Pattern.compile("[0-9]+ blue");
                        Pattern redPattern = Pattern.compile("[0-9]+ red");
                        Pattern greenPattern = Pattern.compile("[0-9]+ green");

                        Matcher blueMatch = bluePattern.matcher(round);
                        Matcher redMatch = redPattern.matcher(round);
                        Matcher greenMatch = greenPattern.matcher(round);

                        int blueNum = 0;
                        int greenNum = 0;
                        int redNum = 0;
                        if (blueMatch.find()) {
                            blueNum = Integer.parseInt(blueMatch.group().split(" ")[0]);
                            if (blueNum > blueMin) {
                                blueMin = blueNum;
                            }
                        }
                        if (redMatch.find()) {
                            redNum = Integer.parseInt(redMatch.group().split(" ")[0]);
                            if (redNum > redMin) {
                                redMin = redNum;
                            }
                        }
                        if (greenMatch.find()) {
                            greenNum = Integer.parseInt(greenMatch.group().split(" ")[0]);
                            if (greenNum > greenMin) {
                                greenMin = greenNum;
                            }
                        }

                        if (blueNum > BLUE || redNum > RED || greenNum > GREEN) {
                            possible = false;
                        }
                    }
                    if (possible) {
                        result += id;
                    }
                    power += greenMin * blueMin * redMin;
                    
                }

                
                id++;
            }
            System.out.println(result);
            System.out.println(power);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception occurred");
        }

    }
}
