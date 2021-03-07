import java.io.File;
import java.util.Scanner;

class Main {
    int TOTAL_BINS = 3;
    int TOTAL_COLORS = 3;
    int[][] POSSIBLE_ORDERS = new int[][] {
            {0,1,2},
            {0,2,1},
            {1,0,2},
            {1,2,0},
            {2,0,1},
            {2,1,0}
    };
    char[] COLORS = new char[]{'B','G','C'};
    int MIN_MOVE_COUNT;
    String BEST_ORDER;


    String getBestResult(Integer[][] configuration) {
        MIN_MOVE_COUNT = -1;
        BEST_ORDER = "";

        for (int currentOrder = 0; currentOrder < POSSIBLE_ORDERS.length; currentOrder++){
            int currentMoveCount = 0;

            for (int currentBin = 0; currentBin < TOTAL_BINS; currentBin++){
                int currentBinType = POSSIBLE_ORDERS[currentOrder][currentBin];
                for(int binType = 0; binType < TOTAL_COLORS; binType++){
                    if (currentBinType != binType){
                        currentMoveCount += configuration[currentBin][binType];
                    }
                }
            }

            String currentBinOrder = getColorOrder(currentOrder);

            if (MIN_MOVE_COUNT == -1 || (currentMoveCount < MIN_MOVE_COUNT) ||
                    ((currentMoveCount == MIN_MOVE_COUNT) && (currentBinOrder.compareTo(BEST_ORDER) < 0))){

                MIN_MOVE_COUNT = currentMoveCount;
                BEST_ORDER = currentBinOrder;
            }
        }

        return BEST_ORDER + " " + MIN_MOVE_COUNT;
    }

    String getColorOrder(int currentOrder){
        String colors = "";
        for(int i = 0; i < TOTAL_BINS; i++){
            colors += COLORS[POSSIBLE_ORDERS[currentOrder][i]];
        }
        return colors;
    }

    void getDataAndSolve() {
        try {
            Scanner scanner = new Scanner(System.in);
            /*File f = new File("/Users/hasham/workspace/personal/input.txt");
            Scanner scanner = new Scanner(f);*/
            Integer[][] inputBottles = new Integer[TOTAL_BINS][TOTAL_COLORS];

            while (scanner.hasNextInt()) {
                for (Integer i = 0; i < TOTAL_BINS; i++) {
                    for (int j = 0; j < TOTAL_COLORS; j++) {
                        inputBottles[i][j] = scanner.nextInt();
                    }
                }
                System.out.println(getBestResult(inputBottles));
            }
            scanner.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.getDataAndSolve();
    }
}
