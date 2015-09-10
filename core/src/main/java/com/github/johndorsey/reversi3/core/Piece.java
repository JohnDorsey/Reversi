/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johndorsey.reversi3.core;

import playn.core.Surface;
import playn.scene.Layer;
import playn.scene.Mouse;
import playn.scene.Pointer;

/**
 *
 * @author John
 */
public class Piece {
    
    public Layer pieceLayer;
    
    public boolean tinted = false;
    
    
    public int thisPiecesOwner;
    public boolean adjacent = false;
    
    public Piece(final int newOwner) {
        sPiece(newOwner, 9, Settings.cellSize - 16);
        
        
//        pieceLayer.events().connect(new Pointer.Listener() {
//        @Override public void onStart (Pointer.Interaction iact) {
//            //Settings.updateDimensions();
//            System.out.println("Piece: CLICK @" + iact.x() + " " + iact.y());
//        }
//    });
    }
    
    public void sPiece(final int newOwner, final int s, final int e) {
        create(s, e);
        setOwner(newOwner);
    }
    
    public void create() {
        create(9, Settings.cellSize - 16);
    }
    
    public void create(final int s, final int e) {
        pieceLayer = new Layer() { protected void paintImpl (Surface surf) {
            surf.setFillColor(0xFFFFFFFF).fillRect(s, s, e, e);
        }};
    }
    
    public void setTint(boolean state) {
        if (state != tinted) {
            pieceLayer.setTint(((state)? Settings.bgDark - 0x00111111 : Settings.colorFor(thisPiecesOwner)));
        }
        tinted = state;
    }
    
    
    
    public void setOwner(int newOwner) {
        thisPiecesOwner = newOwner;
        //System.out.println("Piece.setOwner: changing to " + newOwner);
            
        pieceLayer.setTint(Settings.colorFor(newOwner));
    }
}