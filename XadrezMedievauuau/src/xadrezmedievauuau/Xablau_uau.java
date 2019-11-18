package xadrezmedievauuau;

import java.io.FileNotFoundException;
import javafx.scene.layout.GridPane;

public class Xablau_uau extends Piece_ece{
    int Mpmax, Mp;
    public Xablau_uau(String path, int Hpmax, String nome, int player, Casas_asas pos, int Mpmax){
        super(path, Hpmax, nome, player, pos);
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
                return true;
            }
        }
        return false;
    }
    
    boolean canMove(GridPane p, Casas_asas[][] table ,int x ,int y){
        
        return true;
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