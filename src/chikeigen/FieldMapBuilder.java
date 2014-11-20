package chikeigen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class FieldMapBuilder {
    private static final int DEFAULT_WIDTH = 100;
    private static final int DEFAULT_HEIGHT = 100;

    private int mWidth = DEFAULT_WIDTH;
    private int mHeight = DEFAULT_HEIGHT;
    private int[][] mFieldMapArrs;

    public void setFieldSize(int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    public void execute() {
        // 初期フィールド
        initFieldMap();

        // ランダム
        double land = 0.5;
        for (int i = 1; i < mHeight-1; i++) {
            for (int j = 1; j < mWidth-1; j++) {
                mFieldMapArrs[j][i] = (Math.random() < land) ? 1 : 0;
            }
        }

        // 走査
        for (int i = 0; i < mWidth/2; i++) {
            procFieldMap();
        }
    }

    private void procFieldMap() {
        for (int i = 1; i < mHeight-1; i++) {
            for (int j = 1; j < mWidth-1; j++) {
                int count = 0;
                count += mFieldMapArrs[j-1][i-1];
                count += mFieldMapArrs[j+0][i-1];
                count += mFieldMapArrs[j+1][i-1];
                count += mFieldMapArrs[j-1][i+0];
//                count += mFieldMapArrs[j+0][i+0];
                count += mFieldMapArrs[j+1][i+0];
                count += mFieldMapArrs[j-1][i+1];
                count += mFieldMapArrs[j+0][i+1];
                count += mFieldMapArrs[j+1][i+1];

                switch (count) {
                case 0: mFieldMapArrs[j][i] = 0; break;
                case 1: mFieldMapArrs[j][i] = (Math.random() < 0.1) ? 1 : 0; break;
                case 2: mFieldMapArrs[j][i] = (Math.random() < 0.2) ? 1 : 0; break;
                case 3: mFieldMapArrs[j][i] = (Math.random() < 0.3) ? 1 : 0; break;
                case 4: mFieldMapArrs[j][i] = (Math.random() < 0.5) ? 1 : 0; break;
                case 5: mFieldMapArrs[j][i] = (Math.random() < 0.7) ? 1 : 0; break;
                case 6: mFieldMapArrs[j][i] = (Math.random() < 0.8) ? 1 : 0; break;
                case 7: mFieldMapArrs[j][i] = (Math.random() < 0.9) ? 1 : 0; break;
                case 8: mFieldMapArrs[j][i] = 1; break;
                default:
                    break;
                }
            }
        }
    }

    private void initFieldMap() {
        mFieldMapArrs = new int[mWidth][mHeight];
        for (int i = 0; i < mHeight; i++) {
            for (int j = 0; j < mWidth; j++) {
                mFieldMapArrs[j][i] = 0;
            }
        }
    }

    public void printFieldMap() {
        for (int i = 0; i < mHeight; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < mWidth; j++) {
                if (0 < sb.length()) sb.append(",");
                sb.append(mFieldMapArrs[j][i]);
            }
            System.out.println(sb.toString());
        }
    }

    public BufferedImage getFieldMapImage() {

        BufferedImage img = new BufferedImage(mWidth, mHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();

        for (int i = 0; i < mHeight; i++) {
            for (int j = 0; j < mWidth; j++) {
                Color c = Color.black;
                switch (mFieldMapArrs[j][i]) {
                case 0:
                    c = Color.decode("#a0a0a0");
                    break;
                case 1:
                    c = Color.white;
                    break;
                default:
                    break;
                }
                g2.setPaint(c);
                g2.drawRect(j, i, 1, 1);
            }
        }

        return img;
    }

}
