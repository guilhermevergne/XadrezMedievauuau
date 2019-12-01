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
public class Guardiao_ao extends Piece_ece {
    public Guardiao_ao(String path, int Hpmax, String nome, int player, Casas_asas pos, int width, int height){
        super(path, Hpmax, nome, player, pos);
        //setImage(new Image(path, width, height, true, true));
        DMG = 10;
    }
    
    
    
    @Override
    boolean canMove(GridPane p, Casas_asas[][] table ,int x ,int y){
        int dx = this.pos.getposX() - x, dy = this.pos.getposY() - y;
        if (table[x][y].getPiece() == null) {
            if(player == 1){
                if(abs(dx) == 1 || abs(dy) == 1){
                    if(dx == -1 && dy == 0) return true;
                    if(dx == 0 && dy == 1) return true;
                    if(abs(dx) == 1 && abs(dy) == 1) return true;
                }
                else return false;
            }
            else{
                if(abs(dx) == 1 || abs(dy) == 1){
                    if(dx == 1 && dy == 0) return true;
                    if(dx == 0 && dy == -1) return true;
                    if(abs(dx) == 1 && abs(dy) == 1) return true;
                }
                else return false;
            }
        }
        return false;
    }


    @Override
    boolean poderzinho(GridPane p, Casas_asas[][] table, int x, int y, Player_ayer[] Player) throws FileNotFoundException {
        
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
    
    @Override
    void apagarPoderzinho(GridPane p, Casas_asas[][] table, int x, int y, Player_ayer[] Player){
        
    }
}