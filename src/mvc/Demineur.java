package mvc;
 
import java.awt.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 *
 * @author p1310939
 */
public class Demineur implements Observer{
    
    private Controleur C;
    private Stage primaryStage;
    private Timeline ticker ;
    AnchorPane root = new AnchorPane();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int width = (int) screenSize.getWidth();
    final int height = (int) screenSize.getHeight();
    Scene scene = new Scene(root, 500, 600);
    GridPane rootJ = new GridPane ();
    char mode= 'E';
    Button btnQuitter;
    Button btnRetour;
    
    public Demineur(Controleur C, Stage primaryStage){
        this.C = C;
        this.start(primaryStage);
    }
    Button []plateau;
    
    
   // @Override
    public void start(Stage primaryStage) {        
        Text t = new Text("Bienvenue sur Demineur");
        Button btn = new Button();
        btn.setText("Jouer");
        Image image = new Image("ressources/acceuil.jpg");
        ImageView iv = new ImageView();
        iv.setImage(image);
        iv.setFitWidth(200);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        Button btnM = new Button();
        btnM.setText("Mode");

        
        Button btnQ = new Button();
        btnQ.setText("Quitter");
        C.NonBouton(btnQ, primaryStage);
        
        
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnM.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnQ.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        
        VBox vbButtons = new VBox();
        vbButtons.setSpacing(60);
        vbButtons.getChildren().addAll(t, btn, btnM, btnQ);
        vbButtons.setCursor(Cursor.HAND);
        
        
        
        root.getChildren().add(iv);
        root.getChildren().add(vbButtons);
        root.setTopAnchor(vbButtons, 170.0);
        root.setLeftAnchor(vbButtons, 55.0);        
        root.setTopAnchor(iv, 5.0);
        root.setLeftAnchor(iv, 100.0);
    

        root.setPadding(new Insets(30, 50, 30, 50));
        

                

        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                BorderPane border = new BorderPane();
                
                C.m.initialiseJeu(mode);
                plateau = new Button[C.m.getJeu().getP().getLongueur()*C.m.getJeu().getP().getLargeur()];
                int largeur = C.m.getJeu().getP().getLargeur();
                int longueur = C.m.getJeu().getP().getLongueur();
                int bombe = C.m.getJeu().getP().getNbBombe();

                
                
                Text affichage = new Text("");
                Image imageC = new Image("ressources/clock.png");
                ImageView ivC = new ImageView();
                ivC.setImage(imageC);
                ivC.setFitWidth(45);
                ivC.setPreserveRatio(true);
                ivC.setSmooth(true);
                ivC.setCache(true);
                ivC.getStyleClass().add("clock");
                Image imageB = new Image("ressources/bombsh.png");
                ImageView ivB = new ImageView();
                ivB.setImage(imageB);
                ivB.setFitWidth(45);
                ivB.setPreserveRatio(true);
                ivB.setSmooth(true);
                ivB.setCache(true);
                ivB.getStyleClass().add("bomb");


                HBox time_bo = new HBox();
                
                setTicker(new Timeline());

                getTicker().getKeyFrames().add(new KeyFrame(Duration.seconds(1), (ActionEvent e) -> {
                    C.m.getJeu().incrSecond();
                    affichage.setText("" + C.m.getJeu().getDureeSeconde());
                    affichage.getStyleClass().add("timer");

                }));

                getTicker().setCycleCount(Timeline.INDEFINITE);

                time_bo.getChildren().addAll(ivC, affichage, ivB);
                border.setTop(time_bo);
                border.getStyleClass().add("border");

                Text bmb = new Text(""+ bombe);
                bmb.getStyleClass().add("bmb");
                time_bo.getChildren().add(bmb);
            
                border.setCenter(rootJ);
                Scene sceneJ = new Scene(border, width, height);
                sceneJ.getStylesheets().add("ressources/style.css");
                rootJ.getStyleClass().add("jeu");
                rootJ.setVgap(2);
                rootJ.setHgap(2); 
                rootJ.getChildren().clear();
                
                for(int i=0; i<longueur; i++){
                    for(int j=0; j<largeur; j++){
                            plateau[j*longueur+i]=new Button(" ");
                            plateau[j*longueur+i].getStyleClass().add("case");
                            final int longue = i;
                            final int large = j;
                            plateau[j*longueur+i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                    @Override
                                                    public void handle(MouseEvent event) {
                                                        if(MouseButton.PRIMARY.equals(event.getButton())){
                                                            C.ClickGauche(longue, large);
                                                        }
                                                        else if(MouseButton.SECONDARY.equals(event.getButton())){
                                                            C.ClickDroit(longue, large);
                                                        }
                                                    }
                                                });
                            rootJ.add(plateau[j*longueur+i], j,i);
                            
                            btnQuitter = new Button();
                            btnQuitter.setText("NON");
                            btnQuitter.getStyleClass().add("btn");
                            
                            btnQuitter.setOnAction(new EventHandler<ActionEvent>() 
                            {
                                @Override
                                public void handle(ActionEvent event) 
                                {
                                    primaryStage.close();
                                }
                            });
                            
                            btnRetour = new Button();
                            btnRetour.setText("OUI");
                            btnRetour.getStyleClass().add("btn");
                            C.OuiBouton(btnRetour, border, scene, primaryStage); 
                        
                    }
                }
                primaryStage.setTitle("Jeu");
                primaryStage.setScene(sceneJ);
                primaryStage.show();
                String pl = "";
                pl += C.m.getJeu().getP().toStrings();
                System.out.println(pl);


            }
        });
        
        btnM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GridPane rootM = new GridPane();
                Scene sceneM = new Scene(rootM, 500, 500);
                sceneM.getStylesheets().add("ressources/style.css");
                rootM.getStyleClass().add("mode");
                
                ObservableList typ = FXCollections.observableArrayList();
                ComboBox Type = new ComboBox();

                typ.addAll("Rectangulaire","Losange");
                Type.setItems(typ);
                Type.setCellFactory(ComboBoxListCell.forListView(typ));
                Type.setValue("Rectangulaire");               
                Type.getStyleClass().add("combo-box2");
                
                Type.valueProperty().addListener(new ChangeListener<String>() {
                    @Override 
                    public void changed(ObservableValue ov, String t, String t1) {
                        System.out.println(Type.getValue());
                    }
                });
                
                ObservableList mod = FXCollections.observableArrayList();
                ComboBox Mode = new ComboBox();
                Text label_type = new Text("Type");
                label_type.getStyleClass().add("label_type");
                Text label_mode = new Text("Mode");
                label_mode.getStyleClass().add("label_mode");
                mod.addAll("Debutant","Intermediaire","Malade Mental");
                Mode.setItems(mod);
                Mode.setCellFactory(ComboBoxListCell.forListView(mod));
                Mode.setValue("Debutant");
                Mode.getStyleClass().add("combo-box1");
                
                Mode.valueProperty().addListener(new ChangeListener<String>() {
                    @Override 
                    public void changed(ObservableValue ov, String t, String t1) {
                        System.out.println(Mode.getValue());
                    }
                });
                
                
                Button btnR = new Button("Retour");
                Button btnV = new Button("Valider");
                btnR.getStyleClass().add("btnR");
                btnR.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        primaryStage.setTitle("Demineur");
                        primaryStage.setScene(scene);
                    }
                });
                
                
                btnV.getStyleClass().add("btnV");
                btnV.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        if(Mode.getValue() == "Debutant")
                            mode = 'E';
                        else if(Mode.getValue() == "Intermediaire")
                            mode = 'M';
                        else if(Mode.getValue() == "Malade Mental")
                            mode = 'H';
                        else{
                             //POP-UP VEUILLEZ REMPLIR LES CHAMPS SVP 
                        }
                        primaryStage.setTitle("Demineur");
                        primaryStage.setScene(scene);
                    }
                });
                
                
                rootM.getChildren().addAll(label_type, Type, Mode, label_mode, btnR, btnV);
                primaryStage.setTitle("Mode");
                primaryStage.setScene(sceneM);
                primaryStage.show();
            }
        
        });       
        
            
        
        
        
        scene.getStylesheets().add("ressources/style.css");
                
        btn.getStyleClass().add("btn");
        btnM.getStyleClass().add("btn");
        btnQ.getStyleClass().add("btn");
        
        t.getStyleClass().add("texte");
        
        root.getStyleClass().add("menu");
        
        Image image3 = new Image("ressources/acceuil.jpg");
        ImageView iv3 = new ImageView();
        iv3.setImage(image3);
        primaryStage.getIcons().add(image3);
        primaryStage.setTitle("Demineur");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void update() {
        for(int i=0; i<C.m.getJeu().getP().getLongueur();i++){
            for(int j=0; j<C.m.getJeu().getP().getLargeur();j++){
                plateau[j*C.m.getJeu().getP().getLongueur()+i].setText(C.m.getPl().getEtatIdPlateau()[i][j].getValue());
                if(C.m.getPl().getEtatIdPlateau()[i][j].isDevoilee()){
                    plateau[j*C.m.getJeu().getP().getLongueur()+i].getStyleClass().add("case-uncovered");
                }
            }
        }
    }
    
    @Override
    public void update(Boolean fini) {
        if(fini){
            this.ticker.play();
        }
        else{
            this.ticker.stop();
            
        }
    }
    
    @Override
    public void updateCase(int longueur, int largeur) {
        ImageView ivf = new ImageView();
        Image imagef = new Image("ressources/red_flag.png");
        ivf.setImage(imagef);
        ivf.setFitWidth(20);
        ivf.setFitHeight(25);
        ivf.setPreserveRatio(false);
        plateau[largeur*C.m.getJeu().getP().getLongueur()+longueur].setGraphic(ivf);
    }
    
    @Override
    public void updateUnFlag(int longueur, int largeur) {
        plateau[largeur*C.m.getJeu().getP().getLongueur()+longueur].setGraphic(null);
    }
    
    @Override
    public void updateBombe(int longueur, int largeur, boolean mort){
        ImageView ivb = new ImageView();
        Image imageb = new Image("ressources/bomb2.png");
        ivb.setImage(imageb);
        ivb.setFitWidth(20);
        ivb.setFitHeight(25);
        ivb.setPreserveRatio(false);
        plateau[largeur*C.m.getJeu().getP().getLongueur()+longueur].setGraphic(ivb);
        ImageView ivS = new ImageView();
        Text looser = new Text();
        Timeline attend = new Timeline();
        attend.getKeyFrames().add(new KeyFrame(Duration.seconds(2), (ActionEvent e) -> {
            rootJ.getChildren().clear();
            C.m.getJeu().getP().reinitialise();
            if(mort){
                Image imageS = new Image("ressources/skull.png");
                ivS.setImage(imageS);
                ivS.setFitWidth(200);
                ivS.setPreserveRatio(true);
                ivS.setSmooth(true);
                ivS.setCache(true);
                looser.setText("Vous avez perdu! \n Voulez-vous rejouer?");
            }
            else
            {
                Image imageS = new Image("ressources/victory.png");
                ivS.setImage(imageS);
                ivS.setFitWidth(200);
                ivS.setPreserveRatio(true);
                ivS.setSmooth(true);
                ivS.setCache(true);
                looser.setText("Vous avez gagné! \n Voulez-vous rejouer?");
            }
            looser.getStyleClass().add("looser");
            rootJ.getChildren().addAll(ivS, looser, btnQuitter, btnRetour);
            rootJ.setPadding(new Insets(20,0,0,150)); 
            rootJ.setConstraints(looser, 3, 5);
            rootJ.setConstraints(ivS, 2, 5);
            rootJ.setConstraints(btnQuitter, 3, 25);
            rootJ.setConstraints(btnRetour, 2, 25);
                    
        }));
        attend.play();         
    }
    /**
     * @return the ticker
     */
    public Timeline getTicker() {
        return ticker;
    }

    /**
     * @param ticker the ticker to set
     */
    public void setTicker(Timeline ticker) {
        this.ticker = ticker;
    }

    /**
     * @return the primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * @param primaryStage the primaryStage to set
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }



    /**
     * @param args the command line arguments
     */
 
}
