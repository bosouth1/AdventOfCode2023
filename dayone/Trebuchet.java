import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trebuchet {

    public static void main(String args[]) {
    
        try {
            File file = new File("dayone/input.txt");
            Scanner scanner = new Scanner(file);

            Pattern pattern = Pattern.compile("\\d");
            int result1 = 0;
            int result2 = 0;
            List<Map<Integer, String>> wordMap = getWordMap();
            int count = 0;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                Matcher match = pattern.matcher(nextLine);
                Map<Integer, String> tempMap = wordMap.get(count);

                List<String> matches = new ArrayList<>();

                while (match.find()) {
                    String group = match.group();
                    matches.add(group);
                    for (int i = 0; i < nextLine.length(); i++) {
                        if (nextLine.substring(i, i+1).equals(group)) {
                            tempMap.put(i, group);
                        }
                    }
                    
                }
                wordMap.set(count, tempMap);

                result1 = part1(result1, matches);
                count++;
            }
            System.out.println(result1);


            

            result2 = part2(wordMap);
            System.out.println(result2);
        } catch (FileNotFoundException e) {
            System.out.println("Exception occurred");
        }
        
    }

    private static int part2(List<Map<Integer, String>> wordMap) {

        Map<String, String> numberMap = new HashMap<>();

        numberMap.put("one", "1");
        numberMap.put("two", "2");
        numberMap.put("three", "3");
        numberMap.put("four", "4");
        numberMap.put("five", "5");
        numberMap.put("six", "6");
        numberMap.put("seven", "7");
        numberMap.put("eight", "8");
        numberMap.put("nine", "9");


        int result = 0;
        for (Map<Integer, String> map : wordMap) {

            String lowestValue = "";
            String highestValue = "";
            int lowest = 1000;
            int highest = -1;
            for (Entry<Integer, String> entry : map.entrySet()) {
                if (entry.getKey() < lowest) {
                    lowestValue = entry.getValue();
                    lowest = entry.getKey();
                }
                if (entry.getKey() > highest) {
                    highestValue = entry.getValue();
                    highest = entry.getKey();
                }
            }
            
            // get lowest value

            for (Entry<String, String> entry : numberMap.entrySet()) {
                if (entry.getKey().equals(lowestValue)) {
                    lowestValue = entry.getValue();
                }
            }
            
            // get highest value

            for (Entry<String, String> entry : numberMap.entrySet()) {
                if (entry.getKey().equals(highestValue)) {
                    highestValue = entry.getValue();
                }
            }
            result += Integer.parseInt(lowestValue + highestValue);
            
        }
        
        return result;
    }
    private static int part1(int result, List<String> matches) {
        

        StringBuilder sb = new StringBuilder();
        if (matches.isEmpty()) {
            return result;
        } else if (matches.size() == 1){
            sb.append(matches.get(0));
            sb.append(matches.get(0));
        } else {
            sb.append(matches.get(0));
            sb.append(matches.get(matches.size() - 1));
        }
        result += Integer.parseInt(sb.toString());
        return result;
    }

    private static List<Map<Integer, String>> getWordMap() throws FileNotFoundException {

        String[] numbersAsWords = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        Scanner scanner = new Scanner(new File("dayone/input.txt"));

        List<Map<Integer, String>> result = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();

            Map<Integer, String> tempMap = new HashMap<>();
            for (String key : numbersAsWords) {
                int length = key.length();
                for (int i = 0; i < nextLine.length() - (length - 1); i++) {
                    if (nextLine.substring(i, i + key.length()).equals(key) ||
                            nextLine.substring(i).trim().equals(key)) {
                        tempMap.put(i, key);
                    }
                }
            }
            result.add(tempMap);
        }
        return result;

    }
}