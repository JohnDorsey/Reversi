/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johndorsey.reversi3.core;

/**
 *
 * @author John
 */
public class Settings {
    
    public static int i;
    public static int boardSize = 8;
    public static int centerX = 4;
    public static int centerY = 4;
    public static boolean autoCenterSeeds = true;
    public static int playerCount = 2;
    
    public static int currentPlayer = 2;
    
    
    public static final float LINE_WIDTH = 2;
    public static int cellSize = 57;
    
    public static void nextTurn() {
        currentPlayer++;
        if (currentPlayer > playerCount) { currentPlayer = 1; }
    }
  
    public static int xFromI(int i) {
        return i % Settings.boardSize;
    }
  
    public static int yFromI(int i) {
        return (int) Math.floor((i / boardSize) + 0.1f);
    }
  
    
}
