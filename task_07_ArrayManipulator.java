import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class task_07_ArrayManipulator {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> numbers =
                Stream.of(reader.readLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        String line = reader.readLine();
        while (!line.equals("end")) {
            String[] commandArgs = line.split("\\s+");
            String command = commandArgs[0];

            switch (command) {
                case "exchange":
                    int index = Integer.parseInt(commandArgs[1]);
                    if(index < 0 || index > numbers.size() - 1) {
                        System.out.println("Invalid index");
                    } else {
                        numbers = exchange(index, numbers);
                    }
                    break;
                case "max":
                    max(commandArgs[1], numbers);
                    break;
                case "min":
                    min(commandArgs[1], numbers);
                    break;
                default:
                    get(command, Integer.parseInt(commandArgs[1]), commandArgs[2], numbers);
                    break;
            }

            line = reader.readLine();
        }

        StringBuilder resultList = new StringBuilder();
        resultList.append("[");
        for (Integer number : numbers) {
            resultList.append(number).append(", ");
        }
        System.out.println(resultList.substring(0, resultList.length() - 2) + "]");
    }

    private static List<Integer> exchange (int index, List<Integer> list) {
        List<Integer> subList = list.subList(index + 1, list.size());
        List<Integer> resultList = new ArrayList<>();;
        resultList.addAll(0, list.subList(0, index + 1));
        resultList.addAll(0, subList);

        return resultList;
    }

    private static void get(String command, Integer count, String condition, List<Integer> list) {
        List<Integer> needed = new ArrayList();
        if (count >  list.size()) {
            System.out.println("Invalid count");
            return;
        } else {
            if (condition.equals("even")) {
                for (int i = 0; i < list.size(); i++) {
                     if(list.get(i) % 2 == 0) {
                         needed.add(list.get(i));
                     }
                }
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i) % 2 != 0) {
                        needed.add(list.get(i));
                    }
                }
            }
        }

        if (needed.size() == 0) {
            System.out.println(needed);
        } else if (needed.size() <= count) {
            System.out.println(needed);
        } else {
            if (command.equals("first")) {
                System.out.println(needed.subList(0, count));
            } else {
                System.out.println(needed.subList(needed.size() - count, needed.size()));
            }
        }
    }

    private static void min (String condition, List<Integer> list) {
        int index = -1;
        int min = 1001;
        switch (condition) {
            case "even":
                for (int i = 0; i < list.size(); i++) {
                    int currentNumber = list.get(i);
                    if (currentNumber % 2 == 0 && currentNumber <= min) {
                        index = i;
                        min = currentNumber;
                    }
                }
                break;
            case "odd":
                for (int i = 0; i < list.size(); i++) {
                    int currentNumber = list.get(i);
                    if (currentNumber % 2 != 0 && currentNumber <= min) {
                        index = i;
                        min = currentNumber;
                    }
                }
                break;
        }

        if (index == -1) {
            System.out.println("No matches");
        } else {
            System.out.println(index);
        }
    }

    private static void max(String condition, List<Integer> list) {
        int index = -1;
        int max = -1;
        switch (condition) {
            case "even":
                for (int i = 0; i < list.size(); i++) {
                     int currentNumber = list.get(i);
                     if (currentNumber % 2 == 0 && currentNumber >= max) {
                         index = i;
                         max = currentNumber;
                     }
                }
                break;
            case "odd":
                for (int i = 0; i < list.size(); i++) {
                    int currentNumber = list.get(i);
                    if (currentNumber % 2 != 0 && currentNumber >= max) {
                        index = i;
                        max = currentNumber;
                    }
                }
                break;
        }

        if (index == -1) {
            System.out.println("No matches");
        } else {
            System.out.println(index);
        }
    }
}
