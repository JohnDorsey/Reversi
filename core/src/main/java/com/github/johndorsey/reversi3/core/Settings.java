/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johndorsey.reversi3.core;

import playn.core.Platform;

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
    
    public static int bgLight = 0xFFCCCCCC;
    public static int bgDark = 0xFFBBBBBB;
    
    
    public static int currentPlayer = 2;
    //public static boolean canPlay[] = new boolean[playerCount];
    
    
    public static final float LINE_WIDTH = 2;
    public static int cellSize = 57;
    
    public static Platform plat;
    
    public static void nextTurn() {
        currentPlayer++;
        if (currentPlayer > playerCount) { currentPlayer = 1; }
    }
    
    public static int colorFor(int who){
        int result = 0xFF992222;
        switch (who) {
            case 1: { result = 0xFF111111; } break;
            case 0: { result = 0xFF99BBBB; } break;
            case 2: { result = 0xFFEEEEEE; } break;
            default: { System.err.println("Piece: setOwner: no such person: " + who); } break;
        }
        return result;
    }
  
    public static int xFromI(int i) {
        return i % Settings.boardSize;
    }
  
    public static int yFromI(int i) {
        return (int) Math.floor((i / boardSize) + 0.1f);
    }
    
    //public static void updateDimensions() {
    //    cellSize = (int) plat.graphics().viewSize.height();
    //}
    
//    public static boolean isPlayable() {
//        boolean result = false;
//        for (boolean b : canPlay) {
//            result = result || b;
//            System.out.println("Settings.isPlayable: " + b);
//        }
//        return result;
//    }
  
    
}
