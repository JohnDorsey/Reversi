

package com.github.johndorsey.reversi3.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import playn.core.Canvas;
import playn.core.Surface;
import playn.core.Texture;
import playn.core.Tile;
import playn.scene.GroupLayer;
import playn.scene.ImageLayer;
import playn.scene.Layer;
import playn.scene.Pointer;
import pythagoras.f.IDimension;

/**
 *
 * @author John
 */
public class GameView extends GroupLayer {
    
    
    private final Reversi game;
    private final BoardView bview;
    public final Piece[][] pieces = new Piece[Settings.boardSize][Settings.boardSize];
    private final GroupLayer pgroup = new GroupLayer();

    private final Tile[] ptiles = new Tile[3];
    
    
    public GameView (Reversi game, IDimension viewSize) {
        this.game = game;
        this.bview = new BoardView(game, viewSize);
        addCenterAt(bview, bview.width()/2, viewSize.height()/2);
        addAt(pgroup, bview.tx(), bview.ty());
    
        
        Canvas canvas = game.plat.graphics().createCanvas(2*Settings.cellSize, Settings.cellSize);
    canvas.setFillColor(0x00000000).fillCircle(Settings.cellSize, Settings.cellSize, Settings.cellSize).
      setStrokeColor(0x00FFFFFF).setStrokeWidth(2).strokeCircle(Settings.cellSize, Settings.cellSize, Settings.cellSize-1);
    canvas.setFillColor(0xFFFFFFFF).fillCircle(Settings.cellSize+Settings.cellSize, Settings.cellSize, Settings.cellSize).
      setStrokeColor(0xFF000000).setStrokeWidth(2).strokeCircle(Settings.cellSize+Settings.cellSize, Settings.cellSize, Settings.cellSize-1);
    canvas.setFillColor(0xFFFFFFFF).fillCircle(Settings.cellSize * 3, Settings.cellSize, Settings.cellSize).
      setStrokeColor(0xFF000000).setStrokeWidth(2).strokeCircle(Settings.cellSize * 3, Settings.cellSize, Settings.cellSize-1);

    // convert the image to a texture and extract a texture region (tile) for each piece
    Texture ptex = canvas.toTexture(Texture.Config.UNMANAGED);
    ptiles[0] = ptex.tile(0, 0, Settings.cellSize, Settings.cellSize);
    ptiles[1] = ptex.tile(Settings.cellSize, 0, Settings.cellSize, Settings.cellSize);
    ptiles[2] = ptex.tile(Settings.cellSize * 2, 0, Settings.cellSize, Settings.cellSize);
    
   

    
    Layer top = addPiece();
    
        top.setInteractive(true);
        game.rootLayer.setInteractive(true);
        game.rootLayer.add(top);
    
        
        top.events().connect(new Pointer.Listener() {
            @Override public void onStart (Pointer.Interaction iact) {
                System.out.println("Reversi: CLICK @" + iact.x() + " " + iact.y());
                onBoardClick((int) iact.x(), (int) iact.y());
            }
        });
        
        
  
    addPieces();
    
  }
    
     
private ImageLayer addPiece () {
    ImageLayer pview = new ImageLayer(ptiles[0]);
    pview.setOrigin(0, 0);
    pview.setSize(2048f, 2048f);

    return pview;
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
    
    
    
    public void onBoardClick(int x, int y) { //called by click event for board
        doTurn((int) Math.floor((float) x / Settings.cellSize), (int) Math.floor((float) y / Settings.cellSize));
    }
    
    public void doTurn(int x, int y) {
        //System.out.println("doTurn called for " + x + ", " + y);
        if (doClick(Settings.currentPlayer, x, y)) { Settings.nextTurn(); } else { System.out.println("GameView.doTurn: click failed"); }
    }
    
    
    
    
    
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
    
    
    

    
}
