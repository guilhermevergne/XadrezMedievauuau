package xadrezmedievauuau;

import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public abstract class Piece_ece extends ImageView{
    protected int Hpmax, Hp;
    protected Casas_asas pos;
    protected boolean moveAble, atackAble, skillAble;
    protected String nome;
    protected int player;

    public int getPlayer() {
        return player;
    }
    protected String path;
    //@FXML ImageView png;
    public Piece_ece(String path, int Hpmax, String nome, int player, Casas_asas pos) {
        super(path);
        this.Hpmax = Hpmax;
        this.Hp = Hpmax;
        this.moveAble = false;
        this.atackAble = false;
        this.skillAble = false;
        this.nome = nome;
        this.player = player;
        this.pos = pos;
    }
    
    
    public int getHp() {
        return Hp;
    }

    public void setHp(int Hp) {
        this.Hp = Hp;
    }

    public Casas_asas getPos() {
        return pos;
    }

    public void setPos(Casas_asas pos) {
        this.pos = pos;
    }

    public boolean ismoveAble() {
        return moveAble;
    }

    public void setmoveAble(boolean canmove) {
        this.moveAble = canmove;
    }

    public boolean isatackAble() {
        return atackAble;
    }

    public void setatackAble(boolean canatack) {
        this.atackAble = canatack;
    }

    public boolean isskillAble() {
        return skillAble;
    }

    public void setskillAble(boolean canskill) {
        this.skillAble = canskill;
    }
    
    abstract boolean moving(GridPane p,Casas_asas[][] table ,int x ,int y)throws FileNotFoundException;
    abstract boolean atack(GridPane p,Casas_asas[][] table ,int x ,int y)throws FileNotFoundException;
    abstract boolean poderzinho(Casas_asas target)throws FileNotFoundException;
    abstract boolean canMove(GridPane p, Casas_asas[][] table ,int x ,int y)  throws FileNotFoundException;
    abstract boolean canAttack(GridPane p, Casas_asas[][] table ,int x ,int y)  throws FileNotFoundException;
    abstract boolean canSpell(GridPane p, Casas_asas[][] table ,int x ,int y)  throws FileNotFoundException;
    
    
}