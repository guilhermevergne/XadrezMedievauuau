package xadrezmedievauuau;

import java.util.ArrayList;

class Player_ayer {
    private String nome;
    private ArrayList<Piece_ece> Pieces;

    public Player_ayer() {
        this.Pieces = new ArrayList<>();
    }
    
    public void addPiece(Piece_ece piece){
        Pieces.add(piece);
    }

    public ArrayList<Piece_ece> getPieces() {
        return Pieces;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void manaFill(){
        for(int i = 0; i < Pieces.size(); i++){
            Pieces.get(i).manaFill();
        }
    }
    
    //public void
    
}
