/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xadrezmedievauuau;

import java.io.FileNotFoundException;
import static java.lang.Math.abs;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Gean Almeida
 */
public class Guerrero_rero extends Piece_ece {
    public Guerrero_rero(String path, int Hpmax, String nome, int player, Casas_asas pos, int width, int height){
        super(path, Hpmax, nome, player, pos);
        //setImage(new Image(path, width, height, true, true));
        moveAble = true;
        DMG = 25;
    }
    
    
    
    @Override
    boolean moving(GridPane p, Casas_asas[][] table ,int x ,int y) throws FileNotFoundException {
        if(ismoveAble()){
            if(canMove(p, table , x , y)){
                pos.setPiece(null);
                pos.setPiece(null);
                p.getChildren().remove(this);
                p.add(this,x,y);
                table[x][y].setPiece(this);
                pos = table[x][y];   
                System.out.println("Deu bom");
                return true;
            }
        }
        return false;
    }
    
    @Override
    boolean canMove(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        int dx = this.pos.getposX() - x, dy = this.pos.getposY() - y;
        if(table[x][y].getPiece() == null){
            if(abs(dx) <= 1 && abs(dy) <= 1){
                return true;
            }
            if(player == 0){
                if(abs(dx) + abs(dy) <= 3){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    boolean atack(GridPane p, Casas_asas[][] table ,int x ,int y) throws FileNotFoundException {
        return true;
    }

    @Override
    boolean poderzinho(Casas_asas target) throws FileNotFoundException {
        return true;
    }
    @Override
    boolean canAttack(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        return false;
    }

    @Override
    boolean canSpell(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        return false;
    }
}