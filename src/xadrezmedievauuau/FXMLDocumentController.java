package xadrezmedievauuau;


import java.awt.SystemColor;
import static java.awt.SystemColor.window;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField Tam;
    @FXML
    private TextField Width;
    @FXML
    private TextField Height;
    @FXML
    private TextField P1;
    @FXML
    private TextField P2;

    //"define"
    Casas_asas[][] table;
    int round;
    int tam;
    int width = 100;
    int height = 100;
    private Casas_asas tclicked;
    private Piece_ece actualPiece, attackedPiece, selectedPiece;
    boolean attacking, spelling, moving;
    GridPane tab;
    AnchorPane statTab;

    @FXML
    private void handleButtonAction(ActionEvent event) throws FileNotFoundException {
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

    void startgame(int tam) throws FileNotFoundException {
        round = 0;
        //setPlayer();
        makeTable(tam);
        makeStatusTable();
    }

    void addSelectHandler(Button b) {
        EventHandler<ActionEvent> eventHandler = new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (actualPiece != null && selectedPiece == null) {
                    selectedPiece = actualPiece;
                    //setStatusTable();
                    selectedPiece.setmoveAble();
                    selectedPiece.setatackAble();
                    selectedPiece.setskillAble();
                } else if (selectedPiece != null) {
                    System.out.println("Não pode selecionar outra peça!");
                } else if (actualPiece == null) {
                    System.out.println("Primeiro clique em uma peça");
                }
            }
        };
        b.addEventHandler(ActionEvent.ACTION, eventHandler);
    }

    void addFinishTurnHandler(Button b) {
        EventHandler<ActionEvent> eventHandler = new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (selectedPiece != null) {
                    selectedPiece = null;
                    actualPiece = null;
                    //acrescentar mana
                    round++;
                }
            }
        };
        b.addEventHandler(ActionEvent.ACTION, eventHandler);
    }
    Button SelectPiece, endTurn;
    Label label_actualPiece, label_selectedPiece;
    Label label_actualPieceName, label_selectedPieceName;
    Label label_actualPiecePlayer, label_selectedPiecePlayer;
    Label label_actualPieceHp, label_selectedPieceHp;
    Label label_actualPieceMp, label_selectedPieceMp;
    ImageView actualPieceImg, selectedPieceImg;

    void makeStatusTable() {
        Stage stage = new Stage();
        statTab = new AnchorPane();

        /*  actualPiece  */
        //Botão para selecionar a peça
        SelectPiece = new Button("Select");
        statTab.getChildren().addAll(SelectPiece);
        SelectPiece.setPrefSize(75, 20);
        SelectPiece.setTranslateY(180);
        SelectPiece.setTranslateX(143);

        addSelectHandler(SelectPiece);
        //Label actualPiece Title
        label_actualPiece = new Label("actualPiece");
        label_actualPiece.setTranslateX(140);
        label_actualPiece.setTranslateY(0);
        label_actualPiece.setFont(new Font("Times New Roman", 20));
        statTab.getChildren().addAll(label_actualPiece);
        //Label actualPiece Name
        label_actualPieceName = new Label("Name: ");
        label_actualPieceName.setTranslateX(10);
        label_actualPieceName.setTranslateY(120);
        label_actualPieceName.setFont(new Font("Times New Roman", 20));
        statTab.getChildren().addAll(label_actualPieceName);
        //Label actualPiece Class
        label_actualPiecePlayer = new Label("Player: ");
        label_actualPiecePlayer.setTranslateX(10);
        label_actualPiecePlayer.setTranslateY(150);
        label_actualPiecePlayer.setFont(new Font("Times New Roman", 20));
        statTab.getChildren().addAll(label_actualPiecePlayer);
        //Label actualPiece Hp
        label_actualPieceHp = new Label("Hp: ");
        label_actualPieceHp.setTranslateX(250);
        label_actualPieceHp.setTranslateY(120);
        label_actualPieceHp.setFont(new Font("Times New Roman", 20));
        statTab.getChildren().addAll(label_actualPieceHp);
        //Label actualPiece Name
        label_actualPieceMp = new Label("Mp: ");
        label_actualPieceMp.setTranslateX(250);
        label_actualPieceMp.setTranslateY(150);
        label_actualPieceMp.setFont(new Font("Times New Roman", 20));
        statTab.getChildren().addAll(label_actualPieceMp);
        //Fotinha actualPiece
        actualPieceImg = new ImageView("imgs/Xablau_uau2.png");
        statTab.getChildren().addAll(actualPieceImg);
        actualPieceImg.setX(150);
        actualPieceImg.setY(30);

        /*  selectedPiece  */
        //Botão para selecionar a peça
        endTurn = new Button("End Turn");
        statTab.getChildren().addAll(endTurn);
        endTurn.setPrefSize(75, 20);
        endTurn.setTranslateY(180 + 210);
        endTurn.setTranslateX(143);

        addFinishTurnHandler(endTurn);
        //Label selectedPiece Title
        label_selectedPiece = new Label("selectedPiece");
        label_selectedPiece.setTranslateX(140);
        label_selectedPiece.setTranslateY(0 + 210);
        label_selectedPiece.setFont(new Font("Times New Roman", 20));
        statTab.getChildren().addAll(label_selectedPiece);
        //Label selectedPiece Name
        label_selectedPieceName = new Label("Name: ");
        label_selectedPieceName.setTranslateX(10);
        label_selectedPieceName.setTranslateY(120 + 210);
        label_selectedPieceName.setFont(new Font("Times New Roman", 20));
        statTab.getChildren().addAll(label_selectedPieceName);
        //Label selectedPiece Class
        label_selectedPiecePlayer = new Label("Player: "); //Arruma isso aq e na declaração em cima
        label_selectedPiecePlayer.setTranslateX(10);
        label_selectedPiecePlayer.setTranslateY(150 + 210);
        label_selectedPiecePlayer.setFont(new Font("Times New Roman", 20));
        statTab.getChildren().addAll(label_selectedPiecePlayer);
        //Label selectedPiece Hp
        label_selectedPieceHp = new Label("Hp: ");
        label_selectedPieceHp.setTranslateX(250);
        label_selectedPieceHp.setTranslateY(120 + 210);
        label_selectedPieceHp.setFont(new Font("Times New Roman", 20));
        statTab.getChildren().addAll(label_selectedPieceHp);
        //Label selectedPiece Name
        label_selectedPieceMp = new Label("Mp: ");
        label_selectedPieceMp.setTranslateX(250);
        label_selectedPieceMp.setTranslateY(150 + 210);
        label_selectedPieceMp.setFont(new Font("Times New Roman", 20));
        statTab.getChildren().addAll(label_selectedPieceMp);
        //Fotinha selectedPiece
        selectedPieceImg = new ImageView("imgs/Xablau_uau2.png");
        statTab.getChildren().addAll(selectedPieceImg);
        selectedPieceImg.setX(150);
        selectedPieceImg.setY(30 + 210);

        Scene scene = new Scene(statTab, 360, 420);
        stage.setScene(scene);
        stage.setY(50);
        stage.setX(10);
        stage.show();
    }

    void refreshStatusTable() {  // <-- AQUI! AQUI! KRAI. ARRUMA ISSAQUI!
        //troca os = new label("..."); pra .setText("...");
        /*  actualPiece  */
        //Botão para selecionar a peça
        SelectPiece = new Button("Select");
        statTab.getChildren().addAll(SelectPiece);
        SelectPiece.setPrefSize(75, 20);
        SelectPiece.setTranslateY(180);
        SelectPiece.setTranslateX(143);

        //Label actualPiece Title
        label_actualPiece = new Label("actualPiece");
        label_actualPiece.setTranslateX(140);
        label_actualPiece.setTranslateY(0);
        label_actualPiece.setFont(new Font("Times New Roman", 20));
        //Label actualPiece Name
        label_actualPieceName = new Label("Name: ");
        label_actualPieceName.setTranslateX(10);
        label_actualPieceName.setTranslateY(120);
        label_actualPieceName.setFont(new Font("Times New Roman", 20));
        //Label actualPiece Player
        //label_actualPieceClass = new Label("Player: ");
        //Label actualPiece Hp
        label_actualPieceHp = new Label("Hp: ");
        label_actualPieceHp.setTranslateX(250);
        label_actualPieceHp.setTranslateY(120);
        label_actualPieceHp.setFont(new Font("Times New Roman", 20));
        //Label actualPiece Name
        label_actualPieceMp = new Label("Mp: ");
        label_actualPieceMp.setTranslateX(250);
        label_actualPieceMp.setTranslateY(150);
        label_actualPieceMp.setFont(new Font("Times New Roman", 20));
        //Fotinha actualPiece
        actualPieceImg = new ImageView("imgs/Xablau_uau2.png");
        actualPieceImg.setX(150);
        actualPieceImg.setY(30);

        /*  selectedPiece  */
        //Label selectedPiece Name
        label_selectedPieceName = new Label("Name: ");
        label_selectedPieceName.setTranslateX(10);
        label_selectedPieceName.setTranslateY(120 + 210);
        label_selectedPieceName.setFont(new Font("Times New Roman", 20));
        //Label selectedPiece Player
        label_selectedPiecePlayer = new Label("Player: ");//arruma aq e na declaração em cima
        label_selectedPiecePlayer.setTranslateX(10);
        label_selectedPiecePlayer.setTranslateY(150 + 210);
        label_selectedPiecePlayer.setFont(new Font("Times New Roman", 20));
        //Label selectedPiece Hp
        label_selectedPieceHp = new Label("Hp: ");
        label_selectedPieceHp.setTranslateX(250);
        label_selectedPieceHp.setTranslateY(120 + 210);
        label_selectedPieceHp.setFont(new Font("Times New Roman", 20));
        //Label selectedPiece Name
        label_selectedPieceMp = new Label("Mp: ");
        label_selectedPieceMp.setTranslateX(250);
        label_selectedPieceMp.setTranslateY(150 + 210);
        label_selectedPieceMp.setFont(new Font("Times New Roman", 20));
        //Fotinha selectedPiece
        selectedPieceImg = new ImageView("imgs/Xablau_uau2.png");
        selectedPieceImg.setX(150);
        selectedPieceImg.setY(30 + 210);

        //Se não for null
        if (actualPiece != null) {
            //Label actualPiece Name
            label_actualPieceName = new Label("Name: " + actualPiece.nome);
            label_actualPieceName.setTranslateX(10);
            label_actualPieceName.setTranslateY(120);
            label_actualPieceName.setFont(new Font("Times New Roman", 20));
            //Label actualPiece Player
            label_actualPiecePlayer.setText("Player: " + actualPiece.player);
            //Label actualPiece Hp
            label_actualPieceHp.setText("Hp: " + actualPiece.getHp() + "/" + actualPiece.getHpmax());
            //Label actualPiece Name
            
            label_actualPieceMp = new Label("Mp: ");
            
            //Fotinha actualPiece
            actualPieceImg = new ImageView(actualPiece.getpath());
        }
        if(selectedPiece != null){
            //Label actualPiece Name
            label_selectedPieceName = new Label("Name: " + selectedPiece.nome);
            label_selectedPieceName.setTranslateX(10);
            label_selectedPieceName.setTranslateY(120);
            label_selectedPieceName.setFont(new Font("Times New Roman", 20));
            //Label actualPiece Player
            label_selectedPieceName.setText("Player: " + selectedPiece.player);
            //Label actualPiece Hp
            label_selectedPieceName.setText("Hp: " + selectedPiece.getHp() + "/" + selectedPiece.getHpmax());
            //Label actualPiece Name
            
            label_selectedPieceName = new Label("Mp: ");
            
            //Fotinha actualPiece
            selectedPieceImg = new ImageView(selectedPiece.getpath());
        }
    }

    void makeTable(int tam) throws FileNotFoundException {
        table = new Casas_asas[tam][tam];
        Stage stage = new Stage();
        tab = new GridPane();

        for (int i = 0; i <= tam; i++) {

            RowConstraints linha;
            linha = new RowConstraints(height, height, height, Priority.ALWAYS, VPos.CENTER, true);

            linha.setPrefHeight(height);

            tab.getRowConstraints().add(linha);

        }

        for (int i = 0; i <= tam; i++) {

            ColumnConstraints coluna;
            coluna = new ColumnConstraints(width, width, width, Priority.ALWAYS, HPos.CENTER, true);

            coluna.setPrefWidth(width);

            tab.getColumnConstraints().add(coluna);

        }

        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if ((i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0)) {
                    table[j][i] = new Casas_asas(Color.GRAY, j, i, width, height);
                    addEventesToTable(table[j][i]);
                    table[j][i].setPiece(null);
                } else {
                    table[j][i] = new Casas_asas(Color.BISQUE, j, i, width, height);
                    addEventesToTable(table[j][i]);
                    table[j][i].setPiece(null);
                }
                tab.add(table[j][i], j, i);

            }
        }
        //addPieces();

        //LEGAUS
        //GUARDIAOS
        //GUERREROS
        makePiece("Guerrero_rero", "imgs/Guerrero_rero2.png", 100, "jin", 0, tam - 2, 1, 0);

        //XABLAUS 
        makePiece("Xablau_uau", "imgs/Xablau_uau2.png", 100, "arme", 0, tam - 1, 1, 50);
        makePiece("Xablau_uau", "imgs/Xablau_uau2.png", 100, "arme2", 0, tam - 2, 0, 50);
        makePiece("Xablau_uau", "imgs/Xablau_uau2.png", 100, "arme3", 1, 1, tam - 1, 50);
        makePiece("Xablau_uau", "imgs/Xablau_uau2.png", 100, "arme4", 1, 0, tam - 2, 50);

        Scene scene = new Scene(tab, width * tam, height * tam);
        stage.setScene(scene);
        stage.show();

    }

    //eventos relativos ao tabuleiro
    public void addEventesToTable(Casas_asas t1) throws FileNotFoundException {
        EventHandler<javafx.scene.input.MouseEvent> eventHandler;
        eventHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tclicked = (Casas_asas) event.getSource();
                System.out.println("(" + tclicked.getposX() + " ; " + tclicked.getposY() + ")");
                if (tclicked.getPiece() != null) {
                    System.out.println("Has Piece.");
                }
                if (event.getButton() == MouseButton.PRIMARY) {
                    if (moving && selectedPiece == actualPiece && selectedPiece.player == round % 2) {
                        try {
                            Move();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (attacking && tclicked.getPiece() != null && actualPiece == selectedPiece && actualPiece.player == round % 2) {
                        selectedPiece = actualPiece;
                        try {
                            Attack();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        actualPiece = null;
                    }
                    moving = false;
                    attacking = false;
                    spelling = false;
                    try {
                        repintar();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    if (spelling && actualPiece == selectedPiece && actualPiece.player == round % 2) {
                        try {
                            Poderzinho(tclicked);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
        };
        t1.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    public void addEventesToPiece(Piece_ece p) throws FileNotFoundException {
        EventHandler<javafx.scene.input.MouseEvent> eventHandlerPiece = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //System.out.println("You clicked a Piece!");
                if (event.getButton() == MouseButton.PRIMARY) {
                    if ((actualPiece != p && (!attacking && !spelling)) || (actualPiece == p && (attacking || spelling))) {
                        try {
                            repintar();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        actualPiece = p;
                        //setStatusTable();
                        moving = true;
                        spelling = false;
                        attacking = false;
                        try {
                            pintarMovimento();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (p == actualPiece && moving == true) {
                        try {
                            repintar();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            pintarAttack();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        moving = false;
                        spelling = false;
                        attacking = true;
                    } else if (attacking && p.player != actualPiece.player && actualPiece.player == round % 2) {
                        attackedPiece = p;
                        tclicked = attackedPiece.getPos();
                        try {
                            Attack();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    if (!spelling) {
                        actualPiece = p;
                        moving = false;
                        spelling = true;
                        attacking = false;
                        //pintar alcance da habilidade e despintar os outros
                    } else if (actualPiece != null && spelling && actualPiece.player == round % 2) {
                        try {
                            Poderzinho(actualPiece.getPos());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }

            }

        };
        p.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandlerPiece);
    }

    void repintar() throws FileNotFoundException {
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if ((i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0)) {
                    table[j][i].setFill(Color.GRAY);
                } else {
                    table[j][i].setFill(Color.BISQUE);
                }

            }
        }
    }

    void pintarMovimento() throws FileNotFoundException {
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if (actualPiece.canMove(tab, table, j, i)) {
                    if ((i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0)) {
                        table[j][i].setFill(Color.AQUAMARINE);
                    } else {
                        table[j][i].setFill(Color.AQUA);
                    }
                }

            }
        }
    }

    void pintarAttack() throws FileNotFoundException {
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if (actualPiece.canAttack(tab, table, j, i)) {
                    if ((i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0)) {
                        table[j][i].setFill(Color.CHOCOLATE);
                    } else {
                        table[j][i].setFill(Color.BROWN);
                    }
                }

            }
        }
    }

    void Attack() throws FileNotFoundException {
        System.out.println("Atacou");
        selectedPiece.atack(tab, table, tclicked.getposX(), tclicked.getposY());
        repintar();
        System.out.println(attackedPiece.getHp());
        //Kill
        if (attackedPiece.getHp() <= 0) {
            attackedPiece.pos.setPiece(null);
            tab.getChildren().remove(attackedPiece);
        }
        actualPiece = null;
        tclicked = null;
        attacking = false;
    }

    void Move() throws FileNotFoundException {
        selectedPiece.moving(tab, table, tclicked.getposX(), tclicked.getposY());
        repintar();
        actualPiece = null;
        tclicked = null;
        moving = false;
    }

    void Poderzinho(Casas_asas target) throws FileNotFoundException {
        selectedPiece.poderzinho(target);
        System.out.println("Spellou");
        repintar();
        actualPiece = null;
        spelling = false;
    }

    void makePiece(String classe, String foto, int hpMax, String name, int playerID, int initPosX, int initPosY, int mpMax) throws FileNotFoundException {
        if (classe.equals("Guerrero_rero")) {
            Guerrero_rero newGuerreiro = new Guerrero_rero(foto, hpMax, name, playerID, table[initPosX][initPosY], width, height);
            tab.add(newGuerreiro, initPosX, initPosY);
            table[initPosX][initPosY].setPiece(newGuerreiro);
            addEventesToPiece(newGuerreiro);
            //    return newGuerreiro;
        } else if (classe.equals("Xablau_uau")) {
            Xablau_uau newMago = new Xablau_uau(foto, hpMax, name, playerID, table[initPosX][initPosY], mpMax, width, height);
            tab.add(newMago, initPosX, initPosY);
            table[initPosX][initPosY].setPiece(newMago);
            addEventesToPiece(newMago);
            //    return newMago;
        } else if (classe.equals("Guardiao_ao")) {
            System.out.println("FALTA CRIAR O TANK PORAR");
        } else if (classe.equals("Legau_uau")) {
            System.out.println("TBM FALTA CRIAR O HEALER AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        }
    }
}
