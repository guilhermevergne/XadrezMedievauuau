package xadrezmedievauuau;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Casas_asas extends Rectangle{
    private Piece_ece piece;
    private final int width;
    private final int height;
    private final int x, y;
    private Color c;

    public Piece_ece getPiece() {
        return piece;
    }

    public void setPiece(Piece_ece piece) {
        this.piece = piece;
    }

    public int getposX() {
        return x;
    }

    public int getposY() {
        return y;
    }

    public Color getC() {
        return c;
    }

    public void setC(Color c) {
        this.c = c;
    }

    public Casas_asas(Color c, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.width = width;
        this.height = height;
        setFill(c); 
        setHeight(this.height);
        setWidth(this.width);
        relocate(x*width, y*height);
    }
}
