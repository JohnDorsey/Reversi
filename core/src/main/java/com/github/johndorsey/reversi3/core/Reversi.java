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
    
    
        //private final Tile[] theTile = new Tile[1];
    
    public Reversi (Platform plat) {
        super(plat, 33); // update our "simulation" 33ms (30 times per second)
        
        pointer = new Pointer(plat, rootLayer, false);
        plat.input().mouseEvents.connect(new Mouse.Dispatcher(rootLayer, false));

        // figure out how big the game view is
        final IDimension size = plat.graphics().viewSize;

        // create a layer that just draws a grey background
        rootLayer.add(new Layer() {
            protected void paintImpl (Surface surf) {
                surf.setFillColor(0xFFCCCCCC).fillRect(0, 0, size.width(), size.height());
            }
        });
    
        rootLayer.add(new Layer() {
            protected void paintImpl (Surface surf) {
                surf.setFillColor(0xFFBBBBBB).fillRect(10, 10, size.width() - 20, size.height() - 20);
            }
        });
    
    

        // create and add a game view
        GameView gameview = new GameView(this, size);
        rootLayer.add(gameview);
    
    
//        Layer top = new Layer() {
//            protected void paintImpl (Surface surf) {
//                surf.setFillColor(0x0066BBBB).fillRect(30, 30, size.width() - 60, size.height() - 60);
//            }
//        };
    
        //top.setInteractive(true);
        rootLayer.setInteractive(true);
        //rootLayer.add(top);
    
//        top.events().connect(new Mouse.Listener() {
//            @Override public void onButton(Mouse.ButtonEvent event, Mouse.Interaction inact){
//                System.out.println("Reversi.onButton: click!");
//            }
//        });
        
//        final ImageLayer top = new ImageLayer(theTile[0]);
//        top.setOrigin(Layer.Origin.CENTER);
//        rootLayer.addAt(top, 5, 5);
//        top.setInteractive(true);
//        rootLayer.add(top);
//        top.events().connect(new Pointer.Listener() {
//            @Override public void onStart (Pointer.Interaction iact) {
//                System.out.println("CLICK!!!");
//            }
//        });
        
        //gameview.addSeeds();
    
        gameview.pieces[4][2].setOwner(1);
        gameview.pieces[5][2].setOwner(1);
        gameview.pieces[6][2].setOwner(1);
        gameview.pieces[7][2].setOwner(2);
        
        gameview.pieces[3][3].setOwner(1);
        gameview.pieces[3][4].setOwner(1);
        gameview.pieces[3][5].setOwner(1);
        gameview.pieces[3][6].setOwner(1);
        gameview.pieces[3][7].setOwner(2);
        //gameview.doClick(1, 3, 2);
        //gameview.doClick(1, 3, 2);
        //gameview.doClick(2, 2, 3);
        gameview.pieces[0][0].setOwner(1);
        gameview.pieces[0][1].setOwner(2);
        gameview.pieces[1][1].setOwner(2);
        //gameview.pieces[0][2].setOwner(2);
        //gameview.doClick(1, 0, 2);
        //gameview.doClick(1, 2, 2);
        
        //gameview.doClick(2, 3, 2);
        //gameview.doTurn(3, 2);
        gameview.onBoardClick(200, 143);
    
    }
    
    
    
}
  //public static enum Piece { BLACK, WHITE }
  
  
  

//  public static class Coord {
//    public final int x, y;
//
//    public Coord (int x, int y) {
//   //   assert x >= 0 && y >= 0;
//      this.x = x;
//      this.y = y;
//    }
//  }

//    public boolean equals (Coord other) {
//      return other.x == x && other.y == y;
//    }
//    @Override public boolean equals (Object other) {
//      return (other instanceof Coord) && equals((Coord)other);
//    }
//    @Override public int hashCode () { return x ^ y; }
//    @Override public String toString () { return "+" + x + "+" + y; }
//  }

  //public final RMap<Coord,Piece> pieces = RMap.create();
  //public final Value<Piece> turn = Value.create(null);

  // ...


//public class Reversi extends SceneGame {
//
//    public static enum Piece { BLACK, WHITE }
//    
//    //public static void main() {
//    //    new BoardView(new Reversi(), new IDimension(256, 256));
//    //}
//    
//    public Reversi (Platform plat) {
//        super(plat, 33); // update our "simulation" 33ms (30 times per second)
//
//        // create and add background image layer
//        Image bgImage = plat.assets().getImage("images/bg.png");
//        ImageLayer bgLayer = new ImageLayer(bgImage);
//        // scale the background to fill the screen
//        bgLayer.setSize(plat.graphics().viewSize);
//        rootLayer.add(bgLayer);
//    }
//  
//    public final int boardSize = 8;
//  
//}
