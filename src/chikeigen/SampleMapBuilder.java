package chikeigen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SampleMapBuilder {
    private int[][] mFieldMapArrs;

    public void execute() {
        int[][] field =initFieldMap2();
        int[][] dummy =initFieldMap2();

        int orgNum = 7;
        for (int i = 0; i < orgNum; i++) {
            field[(int)(Math.random()*100)][(int)(Math.random()*100)] = i+1;
        }
        boolean isFinished = false;
        while (!isFinished) {
//        for (int k = 0; k < 1000; k++) {
            for (int i = 1; i < 100-1; i++) {
                for (int j = 1; j < 100-1; j++) {
                    if (field[j][i] != 0) {
                        dummy[j][i] = field[j][i];
                        if (field[j-1][i] == 0 && Math.random() < 0.9) dummy[j-1][i] = field[j][i];
                        if (field[j][i-1] == 0 && Math.random() < 0.9) dummy[j][i-1] = field[j][i];
                        if (field[j+1][i] == 0 && Math.random() < 0.9) dummy[j+1][i] = field[j][i];
                        if (field[j][i+1] == 0 && Math.random() < 0.9) dummy[j][i+1] = field[j][i];
                    }
                }
            }
            for (int i = 1; i < 100-1; i++) {
                for (int j = 1; j < 100-1; j++) {
                    field[j][i] = dummy[j][i];
                }
            }
            int zeroCount = 0;
            for (int i = 1; i < 100-1; i++) {
                for (int j = 1; j < 100-1; j++) {
                    if (field[j][i] == 0) {
                        zeroCount ++;
                    }
                }
            }
            if (zeroCount == 0) isFinished = true;
        }
        printFieldMap(field);
        mFieldMapArrs = field;
    }


    public void printFieldMap(int[][] field) {
        for (int i = 0; i < 100; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 100; j++) {
                if (0 < sb.length()) sb.append(",");
                sb.append(field[j][i]);
            }
            System.out.println(sb.toString());
        }
    }

    private int[][] initFieldMap2() {
        int[][] ret = new int[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                ret[j][i] = 0;
            }
        }
        return ret;
    }

    public BufferedImage getFieldMapImage2(int chipSize) {
        BufferedImage img = new BufferedImage(100 * chipSize, 100 * chipSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Color c = Color.black;
                switch (mFieldMapArrs[j][i]) {
                case 0: c = Color.decode("#9090a0"); break;
                case 1: c = Color.decode("#c0ffc0"); break;
                case 2: c = Color.decode("#90f090"); break;
                case 3: c = Color.decode("#f0d090"); break;
                case 4: c = Color.decode("#f0f090"); break;
                case 5: c = Color.decode("#d8ffd8"); break;
                case 6: c = Color.decode("#ffffd8"); break;
                case 7: c = Color.decode("#ff90d8"); break;
                default:
                    break;
                }
                g2.setPaint(c);
                g2.fillRect(j * chipSize, i * chipSize, chipSize, chipSize);
            }
        }

        return img;
    }

}
