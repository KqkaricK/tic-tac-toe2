import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    interface Basic{
        char getChar();
    }
    class XXX implements Basic{
        public char getChar(){
            return 'X';
        }
    }
    class OOO implements Basic{
        public char getChar(){
            return  '0';
        }
    }
    class Empty implements Basic{
        public char getChar(){
            return '*';
        }
    }
    final Basic SIGN_X = new XXX();
    final Basic SIGN_O = new OOO();
    final Basic SIGN_EMPTY = new Empty();
    Basic[][] table;
    Random random;
    Scanner scanner;

    public static void main(String[] args) {
        new TicTacToe().game();
    }

    TicTacToe() {
        random = new Random();
        scanner = new Scanner(System.in);
        table = new Basic[3][3];
    }

    void game() {
        initTable();
        while (true) {
            turnHuman();
            if (checkWin(SIGN_O)) {
                System.out.println("AI LOSE!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Sorry, DRAW!");
                break;
            }
            turnAI();
            printTable();
            if (checkWin(SIGN_X)) {
                System.out.println("AI WIN!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Sorry, DRAW!");
                break;
            }
        }
        System.out.println("GAME OVER");
        printTable();
    }
    void initTable() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                table[row][col] = SIGN_EMPTY;
    }
    void printTable() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++)
                System.out.print(table[row][col].getChar() + " ");
            System.out.println();
        }
    }
    void turnHuman() {
        int x, y;
        do {
            System.out.println("Enter X and Y (1..3):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[y][x] = SIGN_O;
    }
    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3 || y >= 3) {
            System.out.println("Error!");
            return false;
        }
        return table[y][x] == SIGN_EMPTY;
    }
    void turnAI() {
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isCellValid(x, y));
        table[y][x] = SIGN_X;
    }
    boolean checkWin(Basic dot) {
        for (int i = 0; i < 3; i++)
            if ((table[i][0].equals(dot) && table[i][1].equals(dot) &&
                    table[i][2].equals(dot)) ||
                    (table[0][i].equals(dot) && table[1][i].equals(dot) &&
                            table[2][i].equals(dot)))
                return true;
        return (table[0][0].equals(dot) && table[1][1].equals(dot) &&
                table[2][2].equals(dot)) ||
                (table[2][0].equals(dot) && table[1][1].equals(dot) &&
                        table[0][2].equals(dot));
    }
    boolean isTableFull() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (table[row][col] == SIGN_EMPTY)
                    return false;
        return true;
    }
}