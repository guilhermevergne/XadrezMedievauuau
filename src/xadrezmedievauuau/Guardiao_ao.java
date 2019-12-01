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
    public Guardiao_ao(String path, int Hpmax, String nome, int player, Casas_asas pos, int Mpmax, int width, int height){
        super(path, Hpmax, nome, player, pos);
        this.Mpmax = Mpmax;
        Mp = 0;
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
        Piece_ece targetPiece = table[x][y].getPiece();
        
        if( skillAble && canSpell(p, table, x, y)){
            //Mp -= 30;
            
            if(targetPiece != null && targetPiece.getPlayer() != this.getPlayer()){
                if(player == 1){
                    if(table[this.pos.getposX() + 1][this.pos.getposY() - 1].getPiece() == null){
                        System.out.println("Diagonal hook (P1)");
                        table[x][y].setPiece(null);
                        p.getChildren().remove(targetPiece);
                        p.add(targetPiece, this.pos.getposX() + 1, this.pos.getposY() - 1);
                        table[this.pos.getposX() + 1][this.pos.getposY() - 1].setPiece(targetPiece);
                        targetPiece.pos = table[this.pos.getposX() + 1][this.pos.getposY() - 1];
                    }
                    else if(table[this.pos.getposX() + 1][this.pos.getposY()].getPiece() == null){
                        System.out.println("Downwards hook (P1)");
                        table[x][y].setPiece(null);
                        p.getChildren().remove(targetPiece);
                        p.add(targetPiece, this.pos.getposX() + 1, this.pos.getposY());
                        table[this.pos.getposX() + 1][this.pos.getposY()].setPiece(targetPiece);
                        targetPiece.pos = table[this.pos.getposX() + 1][this.pos.getposY()];
                    }
                    else if(table[this.pos.getposX()][this.pos.getposY() - 1].getPiece() == null){
                        System.out.println("Upwards hook (P1)");
                        table[x][y].setPiece(null);
                        p.getChildren().remove(targetPiece);
                        p.add(targetPiece, this.pos.getposX(), this.pos.getposY() - 1);
                        table[this.pos.getposX()][this.pos.getposY() - 1].setPiece(targetPiece);
                        targetPiece.pos = table[this.pos.getposX()][this.pos.getposY() - 1];
                    }
                    else{
                        System.out.println("Sem local para puxar (P1)");
                    }
                    
                }
                else if(player == 0){
                    if(table[this.pos.getposX() - 1][this.pos.getposY() + 1].getPiece() == null){
                        System.out.println("Diagonal hook (P0)");
                        table[x][y].setPiece(null);
                        p.getChildren().remove(targetPiece);
                        p.add(targetPiece, this.pos.getposX() - 1, this.pos.getposY() + 1);
                        table[this.pos.getposX() - 1][this.pos.getposY() + 1].setPiece(targetPiece);
                        targetPiece.pos = table[this.pos.getposX() - 1][this.pos.getposY() + 1];
                    }
                    else if(table[this.pos.getposX()][this.pos.getposY() + 1].getPiece() == null){
                        System.out.println("Downwards hook (P0)");
                        table[x][y].setPiece(null);
                        p.getChildren().remove(targetPiece);
                        p.add(targetPiece, this.pos.getposX(), this.pos.getposY() + 1);
                        table[this.pos.getposX()][this.pos.getposY() + 1].setPiece(targetPiece);
                        targetPiece.pos = table[this.pos.getposX()][this.pos.getposY() + 1];
                    }
                    else if(table[this.pos.getposX() - 1][this.pos.getposY()].getPiece() == null){
                        System.out.println("Upwards hook (P0)");
                        table[x][y].setPiece(null);
                        p.getChildren().remove(targetPiece);
                        p.add(targetPiece, this.pos.getposX() - 1, this.pos.getposY());
                        table[this.pos.getposX() - 1][this.pos.getposY()].setPiece(targetPiece);
                        targetPiece.pos = table[this.pos.getposX() - 1][this.pos.getposY()];
                    }
                    else{
                        System.out.println("Sem local para puxar (P0)");
                    }
                }
            }
            skillAble = false;
            return true;
        }
        return true;
    }
    
    @Override
    boolean canAttack(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        return false;
    }

    @Override
    boolean canSpell(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        int dx = this.pos.getposX() - x, dy = this.pos.getposY() - y;
        if(abs(dx) + abs(dy) <= 5) return true;
        else return false;
    }
    
    @Override
    void apagarPoderzinho(GridPane p, Casas_asas[][] table, int x, int y, Player_ayer[] Player){
        
    }
}