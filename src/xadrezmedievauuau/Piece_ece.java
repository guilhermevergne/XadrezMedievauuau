package xadrezmedievauuau;

import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public abstract class Piece_ece extends ImageView{
    protected int Hpmax, Hp, DMG, Mpmax, Mp;
    protected Casas_asas pos;
    protected boolean moveAble, atackAble, skillAble;
    protected String nome;
    protected int player;

    public int getPlayer() {
        return player;
    }
    protected String path;
    
    public Piece_ece(String path, int Hpmax, String nome, int player, Casas_asas pos) {
        super(path);
        this.path = path;
        this.Hpmax = Hpmax;
        this.Hp = Hpmax;
        this.moveAble = false;
        this.atackAble = false;
        this.skillAble = false;
        this.nome = nome;
        this.player = player;
        this.pos = pos;
        Mpmax = 0;
        Mp = 0;
    }
    
    public String getName(){
        return nome;
    }
    
    public void setName(String nome){
        this.nome = nome;
    }
    
    public String getpath(){
        return path;
    }
    
    public int getHp() {
        return Hp;
    }
    
    public int getHpmax(){
        return Hpmax;
    }

    public int getMpmax() {
        return Mpmax;
    }

    public int getMp() {
        return Mp;
    }

    public void setHp(int Hp) {
        this.Hp = Hp;
    }

    public void setMp(int Mp) {
        this.Mp = Mp;
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

    public void setmoveAble() {
        this.moveAble = true;
    }

    public boolean isatackAble() {
        return atackAble;
    }

    public void setatackAble() {
        this.atackAble = true;
    }

    public boolean isskillAble() {
        return skillAble;
    }

    public void setskillAble() {
        this.skillAble = true;
    }
    
    abstract boolean moving(GridPane p,Casas_asas[][] table ,int x ,int y)throws FileNotFoundException;
    abstract boolean atack(GridPane p,Casas_asas[][] table ,int x ,int y)throws FileNotFoundException;
    abstract boolean poderzinho(Casas_asas target)throws FileNotFoundException;
    abstract boolean canMove(GridPane p, Casas_asas[][] table ,int x ,int y)  throws FileNotFoundException;
    abstract boolean canAttack(GridPane p, Casas_asas[][] table ,int x ,int y)  throws FileNotFoundException;
    abstract boolean canSpell(GridPane p, Casas_asas[][] table ,int x ,int y)  throws FileNotFoundException;
    
    
}