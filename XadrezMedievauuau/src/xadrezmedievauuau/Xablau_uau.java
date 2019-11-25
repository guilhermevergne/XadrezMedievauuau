package xadrezmedievauuau;

import java.io.FileNotFoundException;
import static java.lang.Math.abs;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class Xablau_uau extends Piece_ece {

    int Mpmax, Mp;

    public Xablau_uau(String path, int Hpmax, String nome, int player, Casas_asas pos, int Mpmax, int width, int height) {
        super(path, Hpmax, nome, player, pos);
        //setImage(new Image(path, width, height, true, true));
        this.Mpmax = Mpmax;
        Mp = 0;
        moveAble = true;
        DMG = 25;
    }

    @Override
    boolean moving(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        if (ismoveAble()) {
            if (canMove(p, table, x, y)) {
                pos.setPiece(null);
                p.getChildren().remove(this);
                p.add(this, x, y);
                table[x][y].setPiece(this);
                pos = table[x][y];
                return true;
            }
        }
        return false;
    }

    @Override
    boolean canMove(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        int dx = this.pos.getposX() - x, dy = this.pos.getposY() - y;
        if (table[x][y].getPiece() == null) {
            if (abs(dx) <= 1 && abs(dy) <= 1) {
                return true;
            }
            if (player == 0) {
                if (dx == -2) {
                    if (dy < 2 && dy >= 0) {
                        return true;
                    }
                }
                if (dy == 2) {
                    if (dx > -2 && dx <= 0) {
                        return true;
                    }
                }
            }
            if (player == 1) {
                if (dx == 2) {
                    if (dy > -2 && dy <= 0) {
                        return true;
                    }
                } else if (dy == -2) {
                    if (dx < 2 && dx >= 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    boolean atack(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        if(canAttack(p, table, x, y)){
            if(table[x][y].getPiece() != null && table[x][y].getPiece().player != player){
                table[x][y].getPiece().setHp(table[x][y].getPiece().getHp() - DMG);
                return true;
            }
        }
        return false;
    }

    @Override
    boolean poderzinho(Casas_asas target) throws FileNotFoundException {
        return true;
    }

    @Override
    boolean canAttack(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        int dx = this.pos.getposX() - x, dy = this.pos.getposY() - y;

        if (abs(dx) <= 1 && abs(dy) <= 1) {
            return true;
        }
        if (player == 0) {
            if ((dx == -2 && dy == 0) || (dx == 0 && dy == 2)) {
                return true;
            }
            if (dy < 0 && dy >= -3 && (dx == -1 || dx == 0 || dx == 1)) {
                return true;
            }
            if (dx > 0 && dx <= 3 && (dy == -1 || dy == 0 || dy == 1)) {
                return true;
            }
            if (dx == 2 && dy == -2) {
                return true;
            }
        }
        if (player == 1) {
            if ((dx == 2 && dy == 0) || (dx == 0 && dy == -2)) {
                return true;
            }
            if (dy > 0 && dy <= 3 && (dx == -1 || dx == 0 || dx == 1)) {
                return true;
            }
            if (dx < 0 && dx >= -3 && (dy == -1 || dy == 0 || dy == 1)) {
                return true;
            }
            if (dx == -2 && dy == 2) {
                return true;
            }
        }
        return false;
    }

    @Override
    boolean canSpell(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        return false;
    }

}
