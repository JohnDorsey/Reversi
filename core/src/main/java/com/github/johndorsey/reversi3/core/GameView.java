/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johndorsey.reversi3.core;

import java.util.Arrays;
import playn.core.Canvas;
import playn.core.Surface;
import playn.core.Texture;
import playn.core.Tile;
import playn.scene.GroupLayer;
import playn.scene.Layer;
import pythagoras.f.IDimension;

/**
 *
 * @author John
 */
public class GameView extends GroupLayer {
    
    //private final Tile[] ptiles = new Tile[Reversi.Piece.values().length];
    
    private final Reversi game;
    private final BoardView bview;
    public final Piece[][] pieces = new Piece[Settings.boardSize][Settings.boardSize];
    private final GroupLayer pgroup = new GroupLayer();

    
    public GameView (Reversi game, IDimension viewSize) {
        this.game = game;
        this.bview = new BoardView(game, viewSize);
        addCenterAt(bview, viewSize.width()/2, viewSize.height()/2);
        addAt(pgroup, bview.tx(), bview.ty());
    
        
        // draw a black piece and white piece into a single canvas image
//    final float size = Settings.cellSize-2, hsize = size/2;
//    Canvas canvas = game.plat.graphics().createCanvas(2*size, size);
//    canvas.setFillColor(0xFF000000).fillCircle(hsize, hsize, hsize).
//      setStrokeColor(0xFFFFFFFF).setStrokeWidth(2).strokeCircle(hsize, hsize, hsize-1);
//    canvas.setFillColor(0xFFFFFFFF).fillCircle(size+hsize, hsize, hsize).
//      setStrokeColor(0xFF000000).setStrokeWidth(2).strokeCircle(size+hsize, hsize, hsize-1);

    // convert the image to a texture and extract a texture region (tile) for each piece
//    Texture ptex = canvas.toTexture(Texture.Config.UNMANAGED);
//    ptiles[Reversi.Piece.BLACK.ordinal()] = ptex.tile(0, 0, size, size);
//    ptiles[Reversi.Piece.WHITE.ordinal()] = ptex.tile(size, 0, size, size);

    addPieces();
    // dispose our pieces texture when this layer is disposed
    //onDisposed(ptex.disposeSlot());
    //final Reversi.Coord ic;
    //final int i;
    
  }
    

    
    public void addPieces() {
        for (int x = 0; x < Settings.boardSize; x++) {
            for (int y = 0; y < Settings.boardSize; y++) {
                pieces[x][y] = new Piece(0);
                pieces[x][y].pieceLayer.setOrigin(-x * Settings.cellSize, -y * Settings.cellSize);
                pgroup.add(pieces[x][y].pieceLayer);
            }
        }
        System.out.println("GameView.addPieces: Size is " + pgroup.children());
    }
    
    
    
    public void addSeeds() {
        int usedCenterX = ((Settings.autoCenterSeeds)? (Settings.boardSize / 2) : (Settings.centerX)) - 1;
        int usedCenterY = ((Settings.autoCenterSeeds)? (Settings.boardSize / 2) : (Settings.centerY)) - 1;
        pieces[usedCenterX][usedCenterY].setOwner(1);
        pieces[usedCenterX + 1][usedCenterY + 1].setOwner(1);
        pieces[usedCenterX + 1][usedCenterY].setOwner(2);
        pieces[usedCenterX][usedCenterY + 1].setOwner(2);
        
    }
    
    
    //public void doTurn(int player) {
    //}
    
    public void onBoardClick(int x, int y) { //called by click event for board
        doTurn((int) Math.floor((float) x / Settings.cellSize), (int) Math.floor((float) y / Settings.cellSize));
    }
    
    public void doTurn(int x, int y) {
        //System.out.println("doTurn called for " + x + ", " + y);
        if (doClick(2, x, y)) { Settings.nextTurn(); }
    }
    
//    public boolean isClickOnBoard(int x, int y) {
//        //if (x < game.size.width() - game.size.height() / 2
//        return true;
//    }
    
    
    
    
    public boolean doClick(int player, int x, int y) {
        //System.out.println("doClick called for " + x + ", " + y);
        if (pieces[x][y].thisPiecesOwner != 0) { System.out.println("player " + player + " clicked a piece at " + x + ", " + y); return false; }
        boolean result = false;
        result = doDirectional(player, x, y, 1, 0) || result;
        result = doDirectional(player, x, y, 1, 1) || result;
        result = doDirectional(player, x, y, 0, 1) || result;
        result = doDirectional(player, x, y, -1, 1) || result;
        result = doDirectional(player, x, y, -1, 0) || result;
        result = doDirectional(player, x, y, -1, -1) || result;
        result = doDirectional(player, x, y, 0, -1) || result;
        result = doDirectional(player, x, y, 1, -1) || result;
        //System.out.println("GameView.doClick: done playing Up");
        //result = doDirectional(player, x, y, 1) || result;
        //System.out.println("GameView.doClick: done playing Right");
        //result = doDirectional(player, x, y, 2) || result;
        //System.out.println("GameView.doClick: done playing Down");
        //result = doDirectional(player, x, y, 3) || result;
        //System.out.println("GameView.doClick: done playing Left");
        if (result) { pieces[x][y].setOwner(player); }
        return result;
    }
    
    public boolean doDirectional(int player, int x, int y, int vDirection, int hDirection) {
        //System.out.println("GameView.doDirectional: now testing " + direction);
        if (sequenceIsPlayable(player, getSequence(x, y, vDirection, hDirection))) {
            //System.out.println("GameView.doDirectional: " + direction + " is playable");
            doPlayDirection(player, x, y, vDirection, hDirection);
            return true;
        } else {
            //System.out.println("GameView.doDirectional: " + direction + " is not playable");
            return false;
        }
    }
    
    public boolean sequenceIsPlayable(int player, int[] sequence) {
        if (sequence[0] == player) { return false; }
        boolean result = false;
        test:
        for (int i = 0; i < Settings.boardSize; i++) {
            if (sequence[i] == 0) { result = false; break test; }
            if (sequence[i] == player) {result = true; break test; }
        }
        //System.out.println("GameView.sequenceIsPlayable: just tested the sequence " + Arrays.toString(sequence) + " for player " + player
        //        + " and decided: " + result);
        return result;
    }
    
    public void doPlayDirection(int player, int x, int y, int vDirection, int hDirection) {
        //System.out.println("GameView.doPlayDirection: running for direction " + direction);
        int currentX = x;
        int currentY = y;
        play:
        for (int i = 0; i < Settings.boardSize; i++) {
            //if (direction > 0 && direction
//            switch (direction) {
//                case 0: { currentY--; } break;
//                case 1: { currentY++; } break;
//                case 2: { currentX--; } break;
//                case 3: { currentX++; } break;
//                default: { System.out.println("GameView.doPlayDirection: unrecognised direction: " + direction); } break;
//            }
            currentX += hDirection;
            currentY += vDirection;
            if (currentX < 0 || currentX >= Settings.boardSize || currentY < 0 || currentY >= Settings.boardSize) {
                System.err.println("GameView.doPlayDirection: unplayable!");
                System.err.println("GameView.doPlayDirection: player: " + player + ", x: " + x + ", y: " + y + ", direction: " + vDirection + ", " + hDirection);
                System.err.println("GameView.doPlayDirection: currentX: " + currentX + ", currentY: " + currentY);
                break play;
            }
            if (pieces[currentX][currentY].thisPiecesOwner != player) {
                pieces[currentX][currentY].setOwner(player);
            } else {
                //System.out.println("GameView.doPlayDirection: played " + direction);
                break play;
            }
        }
    }
    
    public int[] getSequence(int x, int y, int vDirection, int hDirection) {
        int currentX = x;
        int currentY = y;
        int[] sequence = new int[Settings.boardSize];
        for (int i = 0; i < Settings.boardSize; i++) { sequence[i] = 0; }
        //System.out.println("first loop of gameview.getsequence completed");
        get:
        for (int i = 0; i < Settings.boardSize; i++) {
//            switch (direction) {
//                case 0: { currentY--; } break;
//                case 1: { currentY++; } break;
//                case 2: { currentX--; } break;
//                case 3: { currentX++; } break;
//                default: { System.out.println("GameView.getSequence: unrecognised direction: " + direction); } break;
//            }
            currentX += hDirection;
            currentY += vDirection;
            //System.out.println("GameView.getSequence: second loop: i is " + i + ", currentX is " + currentX + ", currentY is " + currentY);
            //+ ", this pieces owner should be " + pieces[currentX][currentY].thisPiecesOwner);
            if (currentX < 0 || currentX >= Settings.boardSize || currentY < 0 || currentY >= Settings.boardSize) {
                /* System.out.println("breaking get!"); */
                break get;
            }
            sequence[i] = pieces[currentX][currentY].thisPiecesOwner;
        }
        //System.out.println("GameView.getSequence: I just got the sequence " + Arrays.toString(sequence));
        return sequence;
    }
    
    
    

//  @Override public void close () {
//    super.close();
//    ptiles[0].texture().close(); // both ptiles reference the same texture
//  }
    
}
