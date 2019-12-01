package xadrezmedievauuau;

import java.io.FileNotFoundException;
import static java.lang.Math.abs;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class Xablau_uau extends Piece_ece {

    public Xablau_uau(String path, int Hpmax, String nome, int player, Casas_asas pos, int Mpmax, int width, int height) {
        super(path, Hpmax, nome, player, pos);
        //setImage(new Image(path, width, height, true, true));
        this.Mpmax = Mpmax;
        Mp = 0;
        DMG = 25;
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
    boolean poderzinho(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        if (/*Mp>= 25 &&*/skillAble && canSpell(p, table, x, y)) {
            Mp -= 25;
            if (table[x][y].getPiece() != null && table[x][y].getPiece().player != player) {
                table[x][y].getPiece().setHp(table[x][y].getPiece().getHp() - 2 * DMG);
                if (table[x][y].getPiece().getHp() <= 0) {
                    p.getChildren().remove(table[x][y].getPiece());
                    table[x][y].getPiece().pos.setPiece(null);
                }
            }
            if (table[x + 1][y].getPiece() != null && table[x + 1][y].getPiece().player != player) {
                table[x + 1][y].getPiece().setHp(table[x + 1][y].getPiece().getHp() - 2 * DMG);
                if (table[x+1][y].getPiece().getHp() <= 0) {
                    p.getChildren().remove(table[x+1][y].getPiece());
                    table[x+1][y].getPiece().pos.setPiece(null);
                }
            }
            if (table[x][y + 1].getPiece() != null && table[x][y + 1].getPiece().player != player) {
                table[x][y + 1].getPiece().setHp(table[x][y + 1].getPiece().getHp() - 2 * DMG);
                if (table[x][y+1].getPiece().getHp() <= 0) {
                    p.getChildren().remove(table[x][y+1].getPiece());
                    table[x][y+1].getPiece().pos.setPiece(null);
                }
            }
            if (table[x - 1][y].getPiece() != null && table[x - 1][y].getPiece().player != player) {
                table[x - 1][y].getPiece().setHp(table[x - 1][y].getPiece().getHp() - 2 * DMG);
                if (table[x-1][y].getPiece().getHp() <= 0) {
                    p.getChildren().remove(table[x-1][y].getPiece());
                    table[x-1][y].getPiece().pos.setPiece(null);
                }
            }
            if (table[x][y - 1].getPiece() != null && table[x][y - 1].getPiece().player != player) {
                table[x][y - 1].getPiece().setHp(table[x][y - 1].getPiece().getHp() - 2 * DMG);
                if (table[x][y-1].getPiece().getHp() <= 0) {
                    p.getChildren().remove(table[x][y-1].getPiece());
                    table[x][y-1].getPiece().pos.setPiece(null);
                }
            }
            skillAble = false;
            return true;
        }
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

}
