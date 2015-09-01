/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johndorsey.reversi3.core;

import playn.core.Surface;
import playn.scene.Layer;
import pythagoras.f.IDimension;

/**
 *
 * @author John
 */

public class BoardView extends Layer {
    
    private final Reversi game;
    
    public BoardView (Reversi game, IDimension viewSize) {
        this.game = game;
        float maxBoardSize = Math.min(viewSize.width(), viewSize.height()) - 20;
        System.out.println("BoardView: cellSize is " + Settings.cellSize);
    }

    // we want %linewidth% extra pixels in width/height to account for the grid lines
    @Override public float width() { return Settings.cellSize * Settings.boardSize + Settings.LINE_WIDTH; }
    @Override public float height() { return width(); } 

    @Override protected void paintImpl (Surface surf) {
        surf.setFillColor(0xFF000000); // black with full alpha
        float top = 0, bot = height(), left = 0, right = width();

        // draw lines from top to bottom for each vertical grid line
        for (int yy = 0; yy <= Settings.boardSize; yy++) {
            float ypos = yy * Settings.cellSize + 1;
            //surf.drawLine(left, ypos, right, ypos, Settings.LINE_WIDTH);
        }

        // draw lines from left to right for each horizontal grid line
        for (int xx = 0; xx <= Settings.boardSize; xx++) {
            float xpos = xx * Settings.cellSize + 1;
            //surf.drawLine(xpos, top, xpos, bot, Settings.LINE_WIDTH);
        }
    }
}


//public class BoardView extends Layer {
//
//    private static final float LINE_WIDTH = 2;
//    private final Reversi game;
//  
//  
//    public final float cellSize;
//    
//    public BoardView (Reversi game, IDimension viewSize) {
//        this.game = game;
//        float maxBoardSize = Math.min(viewSize.width(), viewSize.height()) - 20;
//        this.cellSize = (float)Math.floor(maxBoardSize / game.boardSize);
//  }
//    
//    @Override public float width () { return cellSize * game.boardSize + LINE_WIDTH; }
//    @Override public float height () { return width(); } // width == height
//    
//    
//    
//    
//    @Override protected void paintImpl (Surface surf) {
//        surf.setFillColor(0xFF000000); // black with full alpha
//        float top = 0, bot = height(), left = 0, right = width();
//
//        // draw lines from top to bottom for each vertical grid line
//        for (int yy = 0; yy <= game.boardSize; yy++) {
//            float ypos = yy*cellSize+1;
//            surf.drawLine(left, ypos, right, ypos, LINE_WIDTH);
//        }
//
//        // draw lines from left to right for each horizontal grid line
//        for (int xx = 0; xx <= game.boardSize; xx++) {
//            float xpos = xx*cellSize+1;
//            surf.drawLine(xpos, top, xpos, bot, LINE_WIDTH);
//        }
//    }
//    
//}
