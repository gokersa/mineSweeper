import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int row, column;
    int mine[][], covered[][];
    int size;
    Scanner input = new Scanner(System.in);
    Random rand = new Random();
    boolean game = true;

    //
    public MineSweeper(int c, int r) {
        this.mine = new int[r][c];
        this.covered = new int[r][c];
        this.column = c;
        this.row = r;
        this.size = c * r;
    }

    public void run() {
        int c, r;
        prepare();
        show(mine);
        System.out.println("====OYUN BAŞLADI====");
        while (game) {
            show(covered);
            System.out.print("Satır sayısını giriniz : ");
            r = input.nextInt();
            System.out.print("Sütun sayısını giriniz : ");
            c = input.nextInt();
            if (mine[r][c] != -1) {
                checkMine(r, c);
            } else {
                game = false;
                System.out.print("====OYUNU KAYBETTİNİZ===");
            }
        }

    }

    public void prepare() {
        int randC, randR;
        int count = 0;
        while (count != size / 4) {
            randC = rand.nextInt(row);
            randR = rand.nextInt(column);
            if (mine[randR][randC] != -1) {
                mine[randR][randC] = -1;
                count++;
            }
        }

    }

    public void show(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] >= 0) {
                    System.out.print(" ");
                }
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void checkMine(int r, int c) {
        if (mine[r][c] == 0) {
            if ((c < column - 1) && (mine[r][c + 1] == -1)) {
                covered[r][c + 1]++;
            }
            if ((c > 0) && mine[r][c - 1] == -1) {
                covered[r][c - 1]++;
            }
            if ((r < row - 1) && mine[r + 1][c] == -1) {
                covered[r + 1][c]++;
            }
            if ((row > 0) && mine[r - 1][c] == -1) {
                covered[r - 1][c]++;
            }
            if (covered[r][c] == 0) {
                covered[r][c] = -2;
            }
        }
    }
}



