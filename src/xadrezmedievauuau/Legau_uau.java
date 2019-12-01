package xadrezmedievauuau;

import java.io.FileNotFoundException;
import static java.lang.Math.abs;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class Legau_uau extends Piece_ece {
    int HEAL;
    public Legau_uau(String path, int Hpmax, String nome, int player, Casas_asas pos, int Mpmax, int width, int height) {
        super(path, Hpmax, nome, player, pos);
        //setImage(new Image(path, width, height, true, true));
        this.Mpmax = Mpmax;
        Mp = 0;
        DMG = 15;
        HEAL = 20;
    }
    

    @Override
    boolean canMove(GridPane p, Casas_asas[][] table, int x, int y) {
        int dx = this.pos.getposX() - x, dy = this.pos.getposY() - y;
        if (table[x][y].getPiece() == null) {
            if (player == 1) {
                if ((dx == 2 || dx == -1) && dy <= 1 && dy >= -2) {
                    return true;
                }
                if (dx == 1 && dy <= 2 && dy >= -2) {
                    return true;
                }
                if (dx == 0 && dy <= 2 && dy >= -2) {
                    return true;
                }
                if (dx == -2 && dy <= 0 && dy >= -1) {
                    return true;
                }
            } else {
                if ((dx == -2 || dx == 1) && dy >= -1 && dy <= 2) {
                    return true;
                }
                if (dx == -1 && dy >= -2 && dy <= 2) {
                    return true;
                }
                if (dx == 0 && dy >= -2 && dy <= 2) {
                    return true;
                }
                if (dx == 2 && dy >= 0 && dy <= 1) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    boolean poderzinho(GridPane p, Casas_asas[][] table, int x, int y, Player_ayer[] Player) throws FileNotFoundException {
        if(Mp >= 20 && canSpell(p, table, x, y) && table[x][y].getPiece() != null && table[x][y].getPiece().getPlayer() == player){
            Mp -= 20;
            table[x][y].getPiece().setHp(table[x][y].getPiece().getHp() + HEAL);
            if(table[x][y].getPiece().getHp() > table[x][y].getPiece().getHpmax()){
                table[x][y].getPiece().setHp(table[x][y].getPiece().getHpmax());
            }
            skillAble = false;
        }
        return true;
    }

    @Override
    boolean canAttack(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        int dx = this.pos.getposX() - x, dy = this.pos.getposY() - y;
        if (abs(dx) <= 2 && abs(dy) <= 2) {
            if (player == 1 && !(dy == -2 && dx == 2)) {
                return true;
            }
            if (player == 0 && !(dy == 2 && dx == -2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    boolean canSpell(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        int dx = this.pos.getposX() - x, dy = this.pos.getposY() - y;
        if (abs(dx) + abs(dy) <= 3) {
            return true;
        }
        if(abs(dx) == 2 && abs(dy) == 2){
            return true;
        }
        return false;
    }
    
    @Override
    void apagarPoderzinho(GridPane p, Casas_asas[][] table, int x, int y, Player_ayer[] Player){
        
    }
    @Override
    void levelUp()throws FileNotFoundException{
        lvl++;
        HEAL += 15;
        Hpmax += 15;
        Hp += 15;
    }
}
