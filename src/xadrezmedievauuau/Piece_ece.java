package xadrezmedievauuau;

import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public abstract class Piece_ece extends ImageView{
    protected int Hpmax, Mpmax, Hp, DMG, Mp, ManaRegen, lvl;
    protected Casas_asas pos;
    protected boolean moveAble, atackAble, skillAble;
    protected boolean stunState = false;
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
        lvl = 0;
        Mpmax = 0;
        Mp = 0;
        ManaRegen = 10;
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

    public int getDamage(){
        return DMG;
    }
    
    public void setDamage(int DMG){
        this.DMG = DMG;
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
    void manaFill() {
        Mp += ManaRegen;
        if (Mp > Mpmax) {
            Mp = Mpmax;
        }
    }

    public boolean getStunState() {
        return stunState;
    }

    public void setStunState(boolean stunState) {
        this.stunState = stunState;
    }
    
    
    
    boolean moving(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        if (moveAble && canMove(p, table, x, y)) {
            pos.setPiece(null);
            p.getChildren().remove(this);
            p.add(this, x, y);
            table[x][y].setPiece(this);
            pos = table[x][y];
            moveAble = false;
            return true;
        }
        return false;
    }
    
    boolean atack(GridPane p, Casas_asas[][] table, int x, int y) throws FileNotFoundException {
        if (atackAble && canAttack(p, table, x, y)) {
            if (table[x][y].getPiece() != null && table[x][y].getPiece().player != player) {
                table[x][y].getPiece().setHp(table[x][y].getPiece().getHp() - DMG);
                atackAble = false;
                return true;
            }
        }
        return false;
    }
    
    abstract boolean poderzinho(GridPane p, Casas_asas[][] table, int x, int y, Player_ayer[] Player)throws FileNotFoundException;
    abstract boolean canMove(GridPane p, Casas_asas[][] table ,int x ,int y)  throws FileNotFoundException;
    abstract boolean canAttack(GridPane p, Casas_asas[][] table ,int x ,int y)  throws FileNotFoundException;
    abstract boolean canSpell(GridPane p, Casas_asas[][] table ,int x ,int y)  throws FileNotFoundException;
    abstract void apagarPoderzinho(GridPane p, Casas_asas[][] table, int x, int y, Player_ayer[] Player) throws FileNotFoundException;
    abstract void levelUp()throws FileNotFoundException;
    
    
}