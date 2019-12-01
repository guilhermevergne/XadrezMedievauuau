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

    public Guerrero_rero(String path, int Hpmax, String nome, int player, Casas_asas pos, int Mpmax, int width, int height) {
        super(path, Hpmax, nome, player, pos);
        //setImage(new Image(path, width, height, true, true));
        this.Mpmax = Mpmax;
        Mp = 0;
        moveAble = true;
        DMG = 25;
    }


    @Override
    boolean canMove(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        int dx = this.pos.getposX() - x, dy = this.pos.getposY() - y;
        if (table[x][y].getPiece() == null) {
            if (abs(dx) + abs(dy) < 3) {
                return true;
            }
        }
        return false;
    }


    @Override
    boolean poderzinho(GridPane p, Casas_asas[][] table, int x, int y, Player_ayer[] Player) throws FileNotFoundException {
        if(Mp >= 20 && skillAble && canSpell(p, table, x, y)){
            Mp -= 20;
            if(table[x][y].getPiece() != null && table[x][y].getPiece().player != player){
                table[x][y].getPiece().setHp(table[x][y].getPiece().getHp() - DMG);
                if (table[x][y].getPiece().getHp() <= 0) {
                    Player[table[x][y].getPiece().getPlayer()].getPieces().remove(table[x][y].getPiece());
                    p.getChildren().remove(table[x][y].getPiece());
                    table[x][y].getPiece().pos.setPiece(null);
                }
                DMG += 5;
            }
            skillAble = false;
            return true;
        }
        return true;
    }

    @Override
    boolean canAttack(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        int dx =this.pos.getposX() -x, dy = this.pos.getposY() -y;
        if(this.player == 1){
            if(abs(dx) == 1 || abs(dy) == 1){
                    if(abs(dx) == 1 && dy == 0) return true;
                    if(dx == 0 && abs(dy) == 1) return true;
                    if(dx == -1 && dy == 1) return true;
                }
                else return false;
        }
        else{            
            if(abs(dx) == 1 || abs(dy) == 1){
                    if(abs(dx) == 1 && dy == 0) return true;
                    if(dx == 0 && abs(dy) == 1) return true;
                    if(dx == 1 && dy == -1) return true;                    
                }
                else return false;
        }
        return false;
    }

    @Override
    boolean canSpell(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        int dx =this.pos.getposX() -x, dy = this.pos.getposY() -y;
        if(this.player == 1){
            if(abs(dx) == 1 || abs(dy) == 1){
                    if(abs(dx) == 1 && dy == 0) return true;
                    if(dx == 0 && abs(dy) == 1) return true;
                    if(dx == -1 && dy == 1) return true;
                }
                else return false;
        }
        else{            
            if(abs(dx) == 1 || abs(dy) == 1){
                    if(abs(dx) == 1 && dy == 0) return true;
                    if(dx == 0 && abs(dy) == 1) return true;
                    if(dx == 1 && dy == -1) return true;                    
                }
                else return false;
        }
        return false;
    }
    
    @Override
    void apagarPoderzinho(GridPane p, Casas_asas[][] table, int x, int y, Player_ayer[] Player){
        
    }
    @Override
    void levelUp()throws FileNotFoundException{
        lvl++;
        DMG += 5;
        Hpmax += 15;
        Hp += 15;
    }
}
