package com.github.johndorsey.reversi3.core;

import java.util.Arrays;
import playn.core.Image;
import playn.core.Platform;
import playn.core.Surface;
import playn.core.Tile;
import playn.scene.ImageLayer;
import playn.scene.Layer;
import playn.scene.Mouse;
import playn.scene.Pointer;
import playn.scene.SceneGame;
import pythagoras.f.IDimension;
import react.RMap;
import react.Value;

public class Reversi extends SceneGame {

    public final Pointer pointer;
    
    
    
    public Reversi (Platform plat) {
        super(plat, 30);
        
        pointer = new Pointer(plat, rootLayer, false);
        plat.input().mouseEvents.connect(new Mouse.Dispatcher(rootLayer, false));

        
        
        
        
        final IDimension size = plat.graphics().viewSize;

        rootLayer.add(new Layer() {
            protected void paintImpl (Surface surf) {
                surf.setFillColor(0xFFCCCCCC).fillRect(0, 0, size.width(), size.height());
            }
        });
    
        rootLayer.add(new Layer() {
            protected void paintImpl (Surface surf) {
                surf.setFillColor(0xFFBBBBBB).fillRect(0, 10, size.width(), size.height() - 20);
            }
        });
    
        
    

        GameView gameview = new GameView(this, size);
        rootLayer.add(gameview);
    
        
    
  
        gameview.addSeeds();
//    
//        gameview.pieces[4][2].setOwner(1);
//        gameview.pieces[5][2].setOwner(1);
//        gameview.pieces[6][2].setOwner(1);
//        gameview.pieces[7][2].setOwner(2);
//        
//        gameview.pieces[3][3].setOwner(1);
//        gameview.pieces[3][4].setOwner(1);
//        gameview.pieces[3][5].setOwner(1);
//        gameview.pieces[3][6].setOwner(1);
//        gameview.pieces[3][7].setOwner(2);
//        //gameview.doClick(1, 3, 2);
//        //gameview.doClick(1, 3, 2);
//        //gameview.doClick(2, 2, 3);
//        gameview.pieces[1][0].setOwner(2);
//        gameview.pieces[1][1].setOwner(1);
//        gameview.pieces[2][1].setOwner(1);
        //gameview.pieces[0][2].setOwner(2);
        //gameview.doClick(1, 0, 2);
        //gameview.doClick(1, 2, 2);
        
        //gameview.doClick(2, 2, 2);
        //gameview.doTurn(3, 2);
        //gameview.onBoardClick(200, 143);
        //gameview.onBoardClick(143, 143);
        Settings.currentPlayer = 2;
    
    }
    
    
    
}