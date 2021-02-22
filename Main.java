import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main {
    static HashMap<Long, Long> cachedCycleInfo = new HashMap<>();

    void getDataAndSolve() {
        try {
            Scanner scanner = new Scanner(System.in);
            StringTokenizer tokenizer;
            String myString;
            int firstNumber, secondNumber, min, max;
            cachedCycleInfo.put(1l, 1l);

            while (scanner.hasNextLine()) {
                myString = scanner.nextLine();
                tokenizer = new StringTokenizer(myString);
                firstNumber = Integer.parseInt(tokenizer.nextToken());
                secondNumber = Integer.parseInt(tokenizer.nextToken());
                if (firstNumber < secondNumber) {
                    min = firstNumber;
                    max = secondNumber;
                } else {
                    min = secondNumber;
                    max = firstNumber;
                }

                long maxCycleLength = 0;
                for (int i = min; i <= max; i++) {
                    long cycleLength = cycleCount(i);
                    if (cycleLength > maxCycleLength) {
                        maxCycleLength = cycleLength;
                    }
                }
                System.out.println(firstNumber + " " + secondNumber + " " + maxCycleLength);
            }
            scanner.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static long cycleCount(long n) {
        try {
            if (cachedCycleInfo.containsKey(n)) {
                return cachedCycleInfo.get(n);
            }
            else if (n > 1) {
                long length = 1 + cycleCount((n % 2) == 0 ? n >> 1 : 3 * n + 1);
                cachedCycleInfo.put(n, length);
                return length;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.getDataAndSolve();
    }
}
