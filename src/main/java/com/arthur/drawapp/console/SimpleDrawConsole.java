package com.arthur.drawapp.console;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by laia on 11/24/2017.
 */
@Singleton
public class SimpleDrawConsole implements IDrawConsole {

    public static final int MAX_WIDTH = 800;
    public static final int MAX_HEIGHT = 250;
    public static final char COLOR_BLANK = ' ';
    private char[][] canvas;
    private BufferedReader bufferedReader;

    @Getter
    @Inject
    @Named("drawconsole.border.color.horizontal")
    protected String borderhorizontal;

    @Getter
    @Inject
    @Named("drawconsole.border.color.vertical")
    protected String borderVertial;

    @Getter
    @Inject
    @Named("drawconsole.line.color.default")
    protected String lineColor;

    @Getter
    @Inject
    @Named("drawconsole.fill.color.default")
    protected String fillColor;

    @Inject
    @Named("CmdInput")
    protected InputStream cmdInputStream;

    private int width;
    private int height;

    @Inject
    void init() {
        bufferedReader = new BufferedReader(new InputStreamReader(cmdInputStream));
    }
    public SimpleDrawConsole() {

    }

    @Override
    public boolean createCanvas(int w, int h) {
        if (!checkInbound(w, h)) {
            System.out.println("invalid dimension entered");
            return false;
        }
        this.width = w;
        this.height = h;
        canvas = new char[w+2][h+2];
        for (int i =0; i < w + 2; ++i ) {
            Arrays.fill(canvas[i], COLOR_BLANK);
        }
        drawLineImpl(0, 0, 0, h + 1, borderVertial.charAt(0));
        drawLineImpl(w + 1, 0, w + 1, h + 1, borderVertial.charAt(0));
        drawLineImpl(0, 0, w + 1, 0, borderhorizontal.charAt(0));
        drawLineImpl(0, h + 1, w + 1, h + 1, borderhorizontal.charAt(0));

        return true;
    }

    private boolean checkInbound(int x, int y) {
        int maxWidth = MAX_WIDTH;
        int maxHeight = MAX_HEIGHT;

        if (canvas != null) {
            maxWidth = width;
            maxHeight = height;
        }

        if (x <= 0 || y <= 0 || x > maxWidth || y > maxHeight) {
            return false;
        }
        return true;
    }

    @Override
    public boolean drawLine(int x1, int y1, int x2, int y2) {
        if (canvas == null)
            return false;
        if (x1 != x2  && y1 != y2) {
            System.out.println("Only horizontal or vertial line supported!");
            return false;
        }
        if (x1 > x2  || y1 > y2) {
            System.out.println("start point must be on the top or left from the end point");
            return false;
        }

        if (!checkInbound(x1, y1)) {
            System.out.println("start point out of bound");
            return false;
        }
        if (!checkInbound(x2, y2)) {
            System.out.println("end point out of bound");
            return false;
        }

        drawLineImpl(x1, y1, x2, y2, lineColor.charAt(0));


        return true;
    }

    @Override
    public boolean drawRect(int x1, int y1, int x2, int y2) {
        if (canvas == null)
            return false;
        if (x1 > x2  || y1 > y2) {
            System.out.println("start point must be on the top left from the end point");
            return false;
        }
        if (!checkInbound(x1, y1)) {
            System.out.println("start point out of bound");
            return false;
        }
        if (!checkInbound(x2, y2)) {
            System.out.println("end point out of bound");
            return false;
        }
        drawLineImpl(x1, y1, x1, y2, lineColor.charAt(0));
        drawLineImpl(x2, y1, x2, y2, lineColor.charAt(0));
        drawLineImpl(x1, y1, x2, y1, lineColor.charAt(0));
        drawLineImpl(x1, y2, x2, y2, lineColor.charAt(0));
        return true;
    }

    @Override
    public boolean bucketFill(int x, int y, char color) {
        if (canvas == null)
            return false;
        if (!checkInbound(x, y)) {
            System.out.println("start point out of bound");
            return false;
        }
        char bgColor = getColor(x, y);
        fill(x, y, color, bgColor);
        return true;
    }

    private void fill(int x, int y, char color, char bgColor) {
        if (canvas[x][y] == bgColor) {
            canvas[x][y] = color;
            fill(x + 1, y, color, bgColor);
            fill(x - 1, y, color, bgColor);
            fill(x , y+ 1, color, bgColor);
            fill(x , y- 1, color, bgColor);
        }
    }

    @Override
    public char getColor(int i, int y) {
        return canvas[i][y];
    }

    @Override
    public char getLineColor() {
        return lineColor.charAt(0);
    }

    @Override
    public void clearCanvas() {
        createCanvas(width, height);
    }

    @Override
    public char getBlankColor() {
        return COLOR_BLANK;
    }

    @Override
    public void printCanvas() {
        for (int y = 0; y <= height+1; ++y) {
            for (int x = 0; x<=width +1; ++x) {
                System.out.print(canvas[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public String getCmd() {
        try {
            System.out.print("enter command: ");
            return bufferedReader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void drawLineImpl(int x1, int y1, int x2, int y2, char color) {
        for (int x = x1; x <= x2; ++x) {
            for (int y = y1; y <= y2; ++y) {
                canvas[x][y] = color;
            }
        }
    }
}
