/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johndorsey.reversi3.core;

import playn.core.Surface;
import playn.scene.Layer;
import playn.scene.Mouse;

/**
 *
 * @author John
 */
public class Piece {
    
    public Layer pieceLayer;
    //public Mouse.LayerListener pointerListener;
        //addListener(Pointer.Listener pointerListener)
    
    //public static enum owner {BLACK, WHITE, EMPTY};
    
    
    public int thisPiecesOwner;
    public boolean adjacent = false;
    
    public Piece(final int newOwner) {
        create();
        setOwner(newOwner);
        
    }
    
    public void create() {
        
        pieceLayer = new Layer() { protected void paintImpl (Surface surf) {
            //surf.setFillColor(0xFF99BBBB).fillRect(Settings.xFromI(Settings.i) * bview.cellSize, Settings.yFromI(Settings.i) * bview.cellSize, 20, 20);
            
            surf.setFillColor(0xFFFFFFFF).fillRect(9, 9, Settings.cellSize - 16, Settings.cellSize - 16);
        }};
        
        
     
    }
    
    public void setOwner(int newOwner) {
        thisPiecesOwner = newOwner;
        //System.out.println("Piece.setOwner: changing to " + newOwner);
        int newColor = 0xFF992222;
            switch (newOwner) {
                case 1: { newColor = 0xFF111111; } break;
                case 0: { newColor = 0xFF99BBBB; } break;
                case 2: { newColor = 0xFFEEEEEE; } break;
                default: { System.err.println("Piece: setOwner: no such person: " + newOwner); } break;
            }    
        pieceLayer.setTint(newColor);
    }
}
//    public void setColor(int newOwner) {
//        thisPiecesOwner = newOwner;
//        switch (thisPiecesOwner) {
//            case 1: {
//                
//            } 
//        }
//    }

    //@Override
    //protected void paintImpl(Surface surf) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    

