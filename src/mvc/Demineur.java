package mvc;
 
import demineur.Modele.Case;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
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
import javafx.scene.layout.Pane;
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
import javax.swing.JButton;

/**
 *
 * @author p1310939
 */
public class Demineur implements Observer{
    
    private Controleur C;
    private Stage primaryStage;
    private Timeline ticker ;
    BorderPane border = new BorderPane();
    AnchorPane root = new AnchorPane();
    Scene scene = new Scene(root, 500, 600);
    GridPane rootJ = new GridPane ();
    Button []plateau= new Button[81];
    public Demineur(Controleur C, Stage primaryStage){
        this.C = C;
        this.start(primaryStage);
    }
    
    
    
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
                Scene sceneJ = new Scene(border, 700, 600);
                sceneJ.getStylesheets().add("ressources/style.css");
                rootJ.getStyleClass().add("jeu");
                rootJ.setVgap(2);
                rootJ.setHgap(2); 
                
                
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
                                                            System.out.println("Click Gauche");
                                                        }
                                                        else if(MouseButton.SECONDARY.equals(event.getButton())){
                                                            C.ClickDroit(longue, large);
                                                            System.out.println("Click Droite");
                                                        }
                                                    }
                                                });
                            rootJ.add(plateau[j*longueur+i], j,i);
                            
                            
                            
                            
                            
                            /*plateau[j*longueur+i].setOnMouseClicked(new EventHandler<MouseEvent>(){
                                
                                @Override
                                public void handle(MouseEvent event) {
                                    if(C.m.getJeu().isJeuFini() == false){
                                       getTicker().play();
                                       if(MouseButton.PRIMARY.equals(event.getButton())){
                                          C.m.jouerDemineur(longue, large);
                                          if(C.m.getJeu().getP().getEtatIdPlateau()[longue][large].getBombe())
                                          {
                                            ImageView ivb = new ImageView();
                                            Image imageb = new Image("ressources/bomb2.png");
                                            ivb.setImage(imageb);
                                            ivb.setFitWidth(30);
                                            ivb.setFitHeight(35);
                                            ivb.setPreserveRatio(false);
                                            plateau[large*longueur+longue].setGraphic(ivb);
                                            getTicker().stop();
                                          }
                                        
                                        }
                                        if(MouseButton.SECONDARY.equals(event.getButton()))
                                        {
                                            if(!C.m.getJeu().getP().getEtatIdPlateau()[longue][large].isDrapeau())
                                            {
                                                if(!C.m.getJeu().getP().getEtatIdPlateau()[longue][large].isDevoilee()){
                                                    C.m.getJeu().getP().getEtatIdPlateau()[longue][large].setDrapeau();

                                                    ImageView ivf = new ImageView();
                                                    Image imagef = new Image("ressources/red_flag.png");
                                                    ivf.setImage(imagef);
                                                    ivf.setFitWidth(30);
                                                    ivf.setFitHeight(30);
                                                    ivf.setPreserveRatio(false);
                                                    plateau[large*longueur+longue].setGraphic(ivf);
                                                }  
                                            }
                                            else
                                            {
                                                C.m.getJeu().getP().getEtatIdPlateau()[longue][large].setDrapeau();
                                                plateau[large*longueur+longue].setGraphic(null);
                                            }
                                        }
                                    }
                                    else
                                    {
                                        
                                        rootJ.getChildren().clear();
                                        C.m.getJeu().getP().reinitialise();
                                        ImageView ivS = new ImageView();
                                        Text looser = new Text();
                
                                        if(C.m.getJeu().isPlayerMort())
                                        {
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
                                            getTicker().stop();
                                            Image imageS = new Image("ressources/victory.png");
                                            ivS.setImage(imageS);
                                            ivS.setFitWidth(200);
                                            ivS.setPreserveRatio(true);
                                            ivS.setSmooth(true);
                                            ivS.setCache(true);
                                            looser.setText("Vous avez gagné! \n Voulez-vous rejouer?");
                                        }
                                        looser.getStyleClass().add("looser");
                                        
                                        Button btnQ = new Button();
                                        btnQ.setText("NON");
                                        btnQ.getStyleClass().add("btn");
                                        C.NonBouton(btnQ, primaryStage);
                                        
                                        Button btnR = new Button();
                                        btnR.setText("OUI");
                                        btnR.getStyleClass().add("btn");
                                        C.OuiBouton(btnR, border, scene, primaryStage); 
                                        C.m.getJeu().setJeuFini(false);
                                        C.m.getJeu().getP().setNbNonDevoilee(C.m.getJeu().getP().getLongueur() * C.m.getJeu().getP().getLargeur());
                                        rootJ.getChildren().addAll(ivS, looser, btnQ, btnR);
                                        rootJ.setPadding(new Insets(20,0,0,150)); 
                                        rootJ.setConstraints(looser, 3, 5);
                                        rootJ.setConstraints(ivS, 2, 5);
                                        rootJ.setConstraints(btnQ, 3, 25);
                                        rootJ.setConstraints(btnR, 2, 25); 
                                    }
                                }
                            }); */
                        
                    }
                }
                primaryStage.setTitle("Jeu");
                primaryStage.setScene(sceneJ);
                primaryStage.show();
                String pl = "";

                pl += C.m.getJeu().getP().toStrings();


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
    public void update(String str) {
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
            /*rootJ.getChildren().clear();
            C.m.getJeu().getP().reinitialise();
            ImageView ivS = new ImageView();
            Text looser = new Text();

            if(C.m.getJeu().isPlayerMort())
            {
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
                getTicker().stop();
                Image imageS = new Image("ressources/victory.png");
                ivS.setImage(imageS);
                ivS.setFitWidth(200);
                ivS.setPreserveRatio(true);
                ivS.setSmooth(true);
                ivS.setCache(true);
                looser.setText("Vous avez gagné! \n Voulez-vous rejouer?");
            }
            looser.getStyleClass().add("looser");

            Button btnQ = new Button();
            btnQ.setText("NON");
            btnQ.getStyleClass().add("btn");
            C.NonBouton(btnQ, primaryStage);

            Button btnR = new Button();
            btnR.setText("OUI");
            btnR.getStyleClass().add("btn");
            C.OuiBouton(btnR, border, scene, primaryStage); 
            C.m.getJeu().setJeuFini(false);
            C.m.getJeu().getP().setNbNonDevoilee(C.m.getJeu().getP().getLongueur() * C.m.getJeu().getP().getLargeur());
            rootJ.getChildren().addAll(ivS, looser, btnQ, btnR);
            rootJ.setPadding(new Insets(20,0,0,150)); 
            rootJ.setConstraints(looser, 3, 5);
            rootJ.setConstraints(ivS, 2, 5);
            rootJ.setConstraints(btnQ, 3, 25);
            rootJ.setConstraints(btnR, 2, 25); */
        }
    }
    
    @Override
    public void updateCase(int longueur, int largeur) {
        if(C.m.getPl().getEtatIdPlateau()[longueur][largeur].isDrapeau()){
            System.out.println("dev true");
            ImageView ivf = new ImageView();
            Image imagef = new Image("ressources/red_flag.png");
            ivf.setImage(imagef);
            ivf.setFitWidth(30);
            ivf.setFitHeight(30);
            ivf.setPreserveRatio(false);
            plateau[largeur*C.m.getJeu().getP().getLongueur()+longueur].setGraphic(ivf);
        }
        else{
            System.out.println("dev false");
            plateau[largeur*C.m.getJeu().getP().getLongueur()+longueur].setGraphic(null);
        }
    }
    
    @Override
    public void updateBombe(int longueur, int largeur){
        ImageView ivb = new ImageView();
        Image imageb = new Image("ressources/bomb2.png");
        ivb.setImage(imageb);
        ivb.setFitWidth(30);
        ivb.setFitHeight(35);
        ivb.setPreserveRatio(false);
        plateau[largeur*C.m.getJeu().getP().getLongueur()+longueur].setGraphic(ivb);
        rootJ.getChildren().clear();
        C.m.getJeu().getP().reinitialise();
        ImageView ivS = new ImageView();
        Text looser = new Text();
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
     * @param args the command line arguments
     */
 
}
