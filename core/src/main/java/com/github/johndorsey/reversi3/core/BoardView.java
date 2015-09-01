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

    }
}

