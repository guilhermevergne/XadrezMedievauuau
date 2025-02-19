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
    Stage stageVictory = new Stage();
    Scene sceneVictory;
    Stage stageTab = new Stage();
    Scene sceneTab;
    Stage stageStat = new Stage();
    Scene sceneStat;
    Casas_asas[][] table;
    Player_ayer player[] = new Player_ayer[2];
    int round;
    int tam;
    int width = 100;
    int height = 100;
    private Casas_asas tclicked;
    private Piece_ece pclicked;
    private Piece_ece actualPiece, attackedPiece, selectedPiece;
    boolean attacking, spelling, moving;
    boolean gameStarted = false;
    GridPane tab;
    AnchorPane statTab, victoryTab;

    @FXML
    private void handleButtonAction(ActionEvent event) throws FileNotFoundException {
        if (Integer.parseInt(Tam.getText()) >= 8) {
            tam = Integer.parseInt(Tam.getText());
            width = Integer.parseInt(Width.getText());
            height = Integer.parseInt(Height.getText());
            startgame(tam);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void startgame(int tam) throws FileNotFoundException {
        round = 0;
        setPlayer();
        makeTable(tam);
        makeStatusTable();
        gameStarted = true;
        player[0].manaFill();

    }

    void addSelectHandler(Button b) {
        EventHandler<ActionEvent> eventHandler = new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                attacking = false;
                spelling = false;
                moving = false;

                if (actualPiece != null && selectedPiece == null && actualPiece.getPlayer() == round % 2 && !actualPiece.getStunState()) {
                    selectedPiece = actualPiece;
                    selectedPiece.setmoveAble();
                    selectedPiece.setatackAble();
                    selectedPiece.setskillAble();

                } else if (selectedPiece != null) {
                    System.out.println("Não pode selecionar outra peça!");
                } else if (actualPiece == null) {
                    System.out.println("Primeiro clique em uma peça");
                }
                actualPiece = null;
                refreshStatusTable();
                try {
                    repintar();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
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
                    if (player[(round + 1) % 2].getPieces().size() == 0 || player[round%2].getNome().equals("EXODIA")) {
                        makeVictoryTable();
                    }
                    try {
                        attacking = false;
                        spelling = false;
                        moving = false;
                        repintar();
                        selectedPiece.levelUp();
                        selectedPiece = null;
                        actualPiece = null;
                        refreshStatusTable();
                        player[round % 2].cleanseStun();
                        round++;
                        player[round % 2].manaFill();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("Você ainda não jogou, ao menos selecione uma peça");
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
    Label label_actualPieceAtk, label_selectedPieceAtk;
    Label label_actualPieceStun, label_selectedPieceStun;
    Label label_actualPieceLvl, label_selectedPieceLvl;
    ImageView actualPieceImg, selectedPieceImg, Para_Bens;
    Label label_Win, label_ParaBens, label_Loser;

    void makeVictoryTable() {
        victoryTab = new AnchorPane();

        label_Win = new Label("VITORIA");
        label_Win.setFont(new Font("Times New Roman", 70));
        label_Win.setTranslateX(70 + 150);
        label_Win.setTranslateY(120);
        label_Win.setTextFill(Color.YELLOW);
        victoryTab.getChildren().addAll(label_Win);

        label_ParaBens = new Label("PARA-BENS, VC GANHÔ " + player[round % 2].getNome() + "\nE AGR TA FELIZAO");
        label_ParaBens.setFont(new Font("Times New Roman", 30));
        label_ParaBens.setTranslateX(30 + 150);
        label_ParaBens.setTranslateY(195);
        label_ParaBens.setTextFill(Color.MAGENTA);
        victoryTab.getChildren().addAll(label_ParaBens);

        label_Loser = new Label("JJKJK " + player[(round + 1) % 2].getNome() + " PERDEU \nE TA TILTADO AGR");
        label_Loser.setFont(new Font("Times New Roman", 30));
        label_Loser.setTranslateX(0);
        label_Loser.setTranslateY(500);
        label_Loser.setTextFill(Color.DARKGOLDENROD);
        victoryTab.getChildren().addAll(label_Loser);

        Para_Bens = new ImageView("imgs/Para-Bens.png");
        victoryTab.getChildren().addAll(Para_Bens);
        Para_Bens.setTranslateX(140);
        Para_Bens.setTranslateY(250);

        sceneVictory = new Scene(victoryTab, 800, 600);
        stageVictory.setScene(sceneVictory);
        stageVictory.setY(200);
        stageVictory.setX(200);
        stageVictory.show();
        stageStat.close();
        stageTab.close();
    }

    void makeStatusTable() {
        if (!gameStarted) {
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
            //Label actualPiece Stun
            label_actualPieceStun = new Label("");
            label_actualPieceStun.setTranslateX(10);
            label_actualPieceStun.setTranslateY(60);
            label_actualPieceStun.setFont(new Font("Times New Roman", 20));
            statTab.getChildren().addAll(label_actualPieceStun);
            //Label actualPiece Lvl
            label_actualPieceLvl = new Label("Lvl: ");
            label_actualPieceLvl.setTranslateX(250);
            label_actualPieceLvl.setTranslateY(90);
            label_actualPieceLvl.setFont(new Font("Times New Roman", 20));
            statTab.getChildren().addAll(label_actualPieceLvl);
            //Label actualPiece Atk;
            label_actualPieceAtk = new Label("Atk: ");
            label_actualPieceAtk.setTranslateX(10);
            label_actualPieceAtk.setTranslateY(90);
            label_actualPieceAtk.setFont(new Font("Times New Roman", 20));
            statTab.getChildren().addAll(label_actualPieceAtk);
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
            actualPieceImg = new ImageView("imgs/Vazio.png");
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
            //Label selectedPiece Stun
            label_selectedPieceStun = new Label("");
            label_selectedPieceStun.setTranslateX(10);
            label_selectedPieceStun.setTranslateY(60 + 210);
            label_selectedPieceStun.setFont(new Font("Times New Roman", 20));
            statTab.getChildren().addAll(label_selectedPieceStun);
            //Label selectedPiece Lvl
            label_selectedPieceLvl = new Label("Lvl: ");
            label_selectedPieceLvl.setTranslateX(250);
            label_selectedPieceLvl.setTranslateY(90 + 210);
            label_selectedPieceLvl.setFont(new Font("Times New Roman", 20));
            statTab.getChildren().addAll(label_selectedPieceLvl);
            //Label slectedPiece Atk
            label_selectedPieceAtk = new Label("Atk: ");
            label_selectedPieceAtk.setTranslateX(10);
            label_selectedPieceAtk.setTranslateY(90 + 210);
            label_selectedPieceAtk.setFont(new Font("Times New Roman", 20));
            statTab.getChildren().addAll(label_selectedPieceAtk);
            //Label selectedPiece Name
            label_selectedPieceName = new Label("Name: ");
            label_selectedPieceName.setTranslateX(10);
            label_selectedPieceName.setTranslateY(120 + 210);
            label_selectedPieceName.setFont(new Font("Times New Roman", 20));
            statTab.getChildren().addAll(label_selectedPieceName);
            //Label selectedPiece Class
            label_selectedPiecePlayer = new Label("Player: ");
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
            selectedPieceImg = new ImageView("imgs/Vazio.png");
            statTab.getChildren().addAll(selectedPieceImg);
            selectedPieceImg.setX(150);
            selectedPieceImg.setY(30 + 210);

            sceneStat = new Scene(statTab, 360, 420);
        }
        stageStat.setScene(sceneStat);
        stageStat.setY(50);
        stageStat.setX(10);
        stageStat.show();
    }

    void refreshStatusTable() {
        /*  actualPiece  */
        //Se não for null
        if (actualPiece != null) {
            //Label actualPiece Title
            label_actualPiece = new Label("actualPiece");
            //Label actualPiece Stun
            if (actualPiece.getStunState()) {
                label_actualPieceStun.setText("STUNNED");
                label_actualPieceStun.setTextFill(Color.CYAN);
            }
            //Label actualPiece Atk
            label_actualPieceAtk.setText("Atk: " + actualPiece.getDamage());
            //Label actualPiece Lvl
            label_actualPieceLvl.setText("Lvl: "+ actualPiece.getLvl());
            //Label actualPiece Name
            label_actualPieceName.setText("Name: " + actualPiece.getName());
            //Label actualPiece Player
            label_actualPiecePlayer.setText("Player: " + player[actualPiece.getPlayer()].getNome());
            //Label actualPiece Hp
            label_actualPieceHp.setText("Hp: " + actualPiece.getHp() + "/" + actualPiece.getHpmax());
            //Label actualPiece Name
            label_actualPieceMp.setText("Mp: " + actualPiece.getMp() + "/" + actualPiece.getMpmax());
            //Fotinha actualPiece
            actualPieceImg.setImage(new Image("imgs/" + actualPiece.getpath()));
        } //se for null
        else {
            //Label actualPiece Title
            label_actualPiece = new Label("actualPiece");
            //Label actualPiece Stun
            label_actualPieceStun.setText("");
            //Label actualPiece Lvl
            label_actualPieceLvl.setText("Lvl: ");
            //Label actualPiece ATK
            label_actualPieceAtk.setText("Atk: ");
            //Label actualPiece Name
            label_actualPieceName.setText("Name: ");
            //Label actualPiece Player
            label_actualPiecePlayer.setText("Player: ");
            //Label actualPiece Hp
            label_actualPieceHp.setText("Hp: ");
            //Label actualPiece Name
            label_actualPieceMp.setText("Mp: ");
            //Fotinha actualPiece
            actualPieceImg.setImage(new Image("imgs/Vazio.png"));
        }

        /*  selectedPiece  */
        //se não for null
        if (selectedPiece != null) {
            //Label selectedPiece Stun
            if (selectedPiece.getStunState()) {
                label_selectedPieceStun.setText("STUNNED");
                label_selectedPieceStun.setTextFill(Color.CYAN);
            }
            //Label actualPiece Lvl
            label_selectedPieceLvl.setText("Lvl: "+ actualPiece.getLvl());
            //Label selectedPiece ATK
            label_selectedPieceAtk.setText("Atk: " + selectedPiece.getDamage());
            //Label selectedPiece Name
            label_selectedPieceName.setText("Name: " + selectedPiece.getName());
            //Label selectedPiece Player
            label_selectedPiecePlayer.setText("Player: " + player[selectedPiece.getPlayer()].getNome());
            //Label selectedPiece Hp
            label_selectedPieceHp.setText("Hp: " + selectedPiece.getHp() + "/" + selectedPiece.getHpmax());
            //Label selectedPiece Name
            label_selectedPieceMp.setText("Mp: " + selectedPiece.getMp() + "/" + selectedPiece.getMpmax());
            //Fotinha selectedPiece
            selectedPieceImg.setImage(new Image("imgs/" + selectedPiece.getpath()));
        } //se for null
        else {
            //Label selectedPiece Stun
            label_selectedPieceStun.setText("");
            //Label selectedPiece Name
            label_selectedPieceName.setText("Name: ");
            //Label actualPiece Lvl
            label_selectedPieceLvl.setText("Lvl: ");
            //Label selectedPiece ATK
            label_selectedPieceAtk.setText("Atk: ");
            //Label selectedPiece Player
            label_selectedPiecePlayer.setText("Player: ");
            //Label selectedPiece Hp
            label_selectedPieceHp.setText("Hp: ");
            //Label selectedPiece Name
            label_selectedPieceMp.setText("Mp: ");
            //Fotinha selectedPiece
            selectedPieceImg.setImage(new Image("imgs/Vazio.png"));
        }
    }

    void makeTable(int tam) throws FileNotFoundException {
        if (!gameStarted) {
            table = new Casas_asas[tam][tam];
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
            makePiece("Legau_uau", "imgs/Legau_uau0.png", 100, "Holy", 0, tam - 2, 1, 50);
            makePiece("Legau_uau", "imgs/Legau_uau1.png", 100, "Holy", 1, 1, tam - 2, 50);

            //GUARDIAOS
            //String classe, String foto, int hpMax, String name, int playerID, int initPosX, int initPosY, int mpMax
            makePiece("Guardiao_ao", "imgs/Guardiao_ao0.png", 350, "Jin", 0, tam - 3, 2, 30);
            makePiece("Guardiao_ao", "imgs/Guardiao_ao1.png", 350, "Jin", 1, 2, tam-3, 30);
            //GUERREROS
            makePiece("Guerrero_rero", "imgs/Guerrero_rero0.png", 200, "Sieghart", 0, tam - 1, 4, 40);
            makePiece("Guerrero_rero", "imgs/Guerrero_rero0.png", 200, "Sieghart", 0, tam - 5, 0, 40);
            makePiece("Guerrero_rero", "imgs/Guerrero_rero1.png", 200, "Sieghart", 1, 4, tam - 1, 40);
            makePiece("Guerrero_rero", "imgs/Guerrero_rero1.png", 200, "Sieghart", 1, 0, tam - 5, 40);

            //XABLAUS 
            makePiece("Xablau_uau", "imgs/Xablau_uau0.png", 100, "Arme", 0, tam - 1, 1, 50);
            makePiece("Xablau_uau", "imgs/Xablau_uau0.png", 100, "Arme", 0, tam - 2, 0, 50);
            makePiece("Xablau_uau", "imgs/Xablau_uau1.png", 100, "Arme", 1, 1, tam - 1, 50);
            makePiece("Xablau_uau", "imgs/Xablau_uau1.png", 100, "Arme", 1, 0, tam - 2, 50);
            sceneTab = new Scene(tab, width * tam, height * tam);
        }
        stageTab.setScene(sceneTab);
        stageTab.show();

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
                            Poderzinho();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                refreshStatusTable();
            }
        };
        t1.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    public void addEventesToPiece(Piece_ece p) throws FileNotFoundException {
        EventHandler<javafx.scene.input.MouseEvent> eventHandlerPiece = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    if ((actualPiece != p && !attacking && !spelling) || (actualPiece == p && (attacking || spelling))) {
                        try {
                            repintar();
                            actualPiece = p;
                            refreshStatusTable();
                            moving = true;
                            spelling = false;
                            attacking = false;
                            pintarMovimento();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (p == actualPiece && moving == true) {
                        try {
                            repintar();
                            pintarAttack();
                            moving = false;
                            spelling = false;
                            attacking = true;
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (attacking && actualPiece == selectedPiece && p.player != actualPiece.player && actualPiece.player == round % 2) {
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
                        refreshStatusTable();
                        moving = false;
                        spelling = true;
                        attacking = false;
                        try {
                            pintarPoderzinho();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (selectedPiece != null && actualPiece == selectedPiece && spelling && actualPiece.player == round % 2) {
                        tclicked = p.getPos();
                        try {
                            Poderzinho();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
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

    void pintarPoderzinho() throws FileNotFoundException {
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if (actualPiece.canSpell(tab, table, j, i)) {
                    if ((i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0)) {
                        table[j][i].setFill(Color.CRIMSON);
                    } else {
                        table[j][i].setFill(Color.FIREBRICK);
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
            player[attackedPiece.player].getPieces().remove(attackedPiece);
            attackedPiece.pos.setPiece(null);
            tab.getChildren().remove(attackedPiece);
            attackedPiece = null;
        }
        actualPiece = null;
        refreshStatusTable();
        tclicked = null;
        attacking = false;
    }

    void Move() throws FileNotFoundException {
        selectedPiece.moving(tab, table, tclicked.getposX(), tclicked.getposY());
        repintar();
        actualPiece = null;
        refreshStatusTable();
        tclicked = null;
        moving = false;
    }

    void Poderzinho() throws FileNotFoundException, InterruptedException {
        Casas_asas casa = tclicked;
        selectedPiece.poderzinho(tab, table, tclicked.getposX(), tclicked.getposY(), player);
        repintar();
        actualPiece = null;
        refreshStatusTable();
        spelling = false;

        //selectedPiece.apagarPoderzinho(tab, table, casa.getposX(), casa.getposY(), player);
    }

    void makePiece(String classe, String foto, int hpMax, String name, int playerID, int initPosX, int initPosY, int mpMax) throws FileNotFoundException {
        if (classe.equals("Guerrero_rero")) {
            Guerrero_rero newGuerreiro = new Guerrero_rero(foto, hpMax, name, playerID, table[initPosX][initPosY], mpMax, width, height);
            tab.add(newGuerreiro, initPosX, initPosY);
            table[initPosX][initPosY].setPiece(newGuerreiro);
            addEventesToPiece(newGuerreiro);
            player[playerID].addPiece(newGuerreiro);
            //    return newGuerreiro;
        } else if (classe.equals("Xablau_uau")) {
            Xablau_uau newMago = new Xablau_uau(foto, hpMax, name, playerID, table[initPosX][initPosY], mpMax, width, height);
            tab.add(newMago, initPosX, initPosY);
            table[initPosX][initPosY].setPiece(newMago);
            addEventesToPiece(newMago);
            player[playerID].addPiece(newMago);
            //    return newMago;
        } else if (classe.equals("Guardiao_ao")) {
            Guardiao_ao newTank = new Guardiao_ao(foto, hpMax, name, playerID, table[initPosX][initPosY], mpMax, width, height);
            tab.add(newTank, initPosX, initPosY);
            table[initPosX][initPosY].setPiece(newTank);
            addEventesToPiece(newTank);
            player[playerID].addPiece(newTank);
        } else if (classe.equals("Legau_uau")) {
            Legau_uau newsacer = new Legau_uau(foto, hpMax, name, playerID, table[initPosX][initPosY], mpMax, width, height);
            tab.add(newsacer, initPosX, initPosY);
            table[initPosX][initPosY].setPiece(newsacer);
            addEventesToPiece(newsacer);
            player[playerID].addPiece(newsacer);
        }
    }

    private void setPlayer() {
        if (!gameStarted) {
            player[0] = new Player_ayer();
            player[0].setNome(P1.getText());
            player[1] = new Player_ayer();
            player[1].setNome(P2.getText());
        }
    }
}
