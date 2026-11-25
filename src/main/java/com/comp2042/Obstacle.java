package com.comp2042;

import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Obstacle {
    private static final int MAX_OBSTACLES = 50;
    private Color color = Color.BLACK;
    private ArrayList<Point> obstacles = new ArrayList<>();
    private int x;
    private int y;

    public Obstacle(int x, int y) {
       this.x = x;
       this.y = y;
    }

    public void newObstacles(Snake snek, Coin coin, int amount) {
        if (obstacles.size() >= MAX_OBSTACLES) {
            return;
        }

        int remainingCapacity = MAX_OBSTACLES - obstacles.size();
        int obstaclesToAdd = Math.min(amount, remainingCapacity);
        Random rnd = new Random();
        ArrayList<Point> availablePositions = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Point p = new Point(i, j);

                if (!coin.getPos().equals(p)
                        && !snek.getBody().contains(p)
                        && !snek.getHead().equals(p)
                        && !obstacles.contains(p)) {

                    availablePositions.add(p);
                }
            }
        }

        for (int i = 0; i < obstaclesToAdd && !availablePositions.isEmpty(); i++) {
            int index = rnd.nextInt(availablePositions.size());
            obstacles.add(availablePositions.remove(index));
        }
    }

    public Color getColor() {
        return color;
    }

    public ArrayList<Point> getObstacles() {
        return obstacles;
    }

    public void reset() {
        obstacles.clear();
    }
}
