package xadrezmedievauuau;

import java.io.FileNotFoundException;
import static java.lang.Math.abs;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class Legau_uau extends Piece_ece{
    int Mpmax, Mp;
    public Legau_uau(String path, int Hpmax, String nome, int player, Casas_asas pos, int Mpmax, int width, int height){
        super(path, Hpmax, nome, player, pos);
        //setImage(new Image(path, width, height, true, true));
        this.Mpmax = Mpmax;
        Mp = 0;
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
    
    boolean canMove(GridPane p, Casas_asas[][] table ,int x ,int y){
        if(abs(x - this.pos.getX() + y - this.pos.getY()) <= 3){
        return true;
        }
        return false;
    }

    @Override
    boolean atack(GridPane p, Casas_asas[][] table ,int x ,int y) throws FileNotFoundException {
        return true;
    }

    @Override
    boolean poderzinho() throws FileNotFoundException {
        return true;
    }
    
}