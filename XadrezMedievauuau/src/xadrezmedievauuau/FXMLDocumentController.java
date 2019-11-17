package xadrezmedievauuau;

import java.awt.SystemColor;
import static java.awt.SystemColor.window;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class FXMLDocumentController implements Initializable {
    
    @FXML private TextField Tam;
    @FXML private TextField Width;
    @FXML private TextField Height;
    @FXML private TextField P1;
    @FXML private TextField P2;
    
    
    //"define"
    Casas_asas[][] table;
    int tam;
    int width = 100;
    int height = 100;
    private Casas_asas tclicked;
    private Piece_ece actualPiece, attackedPiece;
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        tam = Integer.parseInt(Tam.getText());
        width = Integer.parseInt(Width.getText());
        height = Integer.parseInt(Height.getText());
        String nome1 = P1.getText();
        String nome2 = P2.getText();
        startgame(tam);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void startgame(int tam){
        //setPlayer();
        makeTable(tam);
    }
    
    void makeTable(int tam){
        table = new Casas_asas[tam][tam];
        Stage stage = new Stage();
        GridPane tab = new GridPane();
        for (int i = 0; i <= tam; i++) {
            
            RowConstraints linha = new RowConstraints();
            
            linha.setPrefHeight(height);
            
            tab.getRowConstraints().add(linha);
            
        }
        
        for (int i = 0; i <= tam; i++) {
            
            ColumnConstraints coluna = new ColumnConstraints();
            
            coluna.setPrefWidth(width);
            
            tab.getColumnConstraints().add(coluna);
            
        }
        
        for(int i=0; i<tam; i++){
            for(int j=0;j<tam;j++){
                              
                if((i%2==0 && j%2!=0) || (i%2!=0 && j%2==0)){
                    table[j][i]= new Casas_asas( Color.GRAY, j, i, width, height);
                    //addEventesToTable(table[j][i]);
                    table[j][i].setPiece(null);
                }
                else {
                    table[j][i]= new Casas_asas(Color.BISQUE, j, i, width, height);
                    //addEventesToTable(table[j][i]);
                    table[j][i].setPiece(null);
                }
                //GridPane.setConstraints(table[j][i], j, i);
                //tab.getChildren().addAll(table[j][i]);
                tab.add(table[j][i], j, i);
                
            }
        }
        //addPieces();
        Scene scene = new Scene(tab, width*tam, height*tam);
        stage.setScene(scene) ;
        stage.show();
    }
    
    //eventos relativos ao tabuleiro
    public void addEventesToTable(Casas_asas t1){
        EventHandler<javafx.scene.input.MouseEvent> eventHandler;
        eventHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tclicked= (Casas_asas) event.getSource();
                System.out.println(tclicked.getposX()+" , "+tclicked.getposY());
                if(tclicked.getPiece()!=null)System.out.println("Has Piece."); 
                if(event.getButton()== MouseButton.PRIMARY){
                    System.out.println("Primary.");
                    if(actualPiece!=null)Move();
                }
                if(event.getButton()== MouseButton.SECONDARY){
                    
                    if(actualPiece!=null && attackedPiece!=null){
                        Attack();
                    }
                    
                }
                
            }
            
            
        };
        t1.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
    }
    public void addEventesToPiece(Piece_ece p){
        EventHandler<javafx.scene.input.MouseEvent> eventHandlerPiece = new EventHandler<javafx.scene.input.MouseEvent>() { 
            @Override
            public void handle(MouseEvent event) {
                System.out.println("You clicked a Piece!"); 
                if(event.getButton()== MouseButton.PRIMARY){
                    if(actualPiece==null)actualPiece=p;
                    else{
                        attackedPiece = p;
                        tclicked = attackedPiece.getPos();
                        Move();
                    }
                    
                }
                if(event.getButton()== MouseButton.SECONDARY){
                    if(actualPiece!=null)attackedPiece=p;
                    if(actualPiece!=null && attackedPiece!=null){
                        Attack();
                    }
                    
                }
                
            }
   
   
        };
        p.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandlerPiece);
    }
    
    
    
    void Attack(){
        System.out.println("Atacou");
    }
    void Move(){
        System.out.println("Moveu");
    }
}
