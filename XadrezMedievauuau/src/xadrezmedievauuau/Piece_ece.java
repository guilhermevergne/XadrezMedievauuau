package xadrezmedievauuau;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public abstract class Piece_ece extends ImageView{
    protected int Hpmax, Hp;
    protected Casas_asas pos;
    protected boolean canmove, canatack, canskill;
    protected String nome;
    protected int player;
    protected String path;
    @FXML ImageView png;
    public Piece_ece(String path, int Hpmax, String nome, int player) {
        super(path);
        this.Hpmax = Hpmax;
        this.Hp = Hpmax;
        this.canmove = false;
        this.canatack = false;
        this.canskill = false;
        this.nome = nome;
        this.player = player;
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

    public boolean isCanmove() {
        return canmove;
    }

    public void setCanmove(boolean canmove) {
        this.canmove = canmove;
    }

    public boolean isCanatack() {
        return canatack;
    }

    public void setCanatack(boolean canatack) {
        this.canatack = canatack;
    }

    public boolean isCanskill() {
        return canskill;
    }

    public void setCanskill(boolean canskill) {
        this.canskill = canskill;
    }
    
    abstract void moving();
    abstract void atack();
    abstract void poderzinho();
    
    
}