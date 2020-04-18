
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class rawShop extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    String filePath = "";
    String fileName = "";
    String a = "";
    int b = 0;
    int c = 0;
    int d = 0;

    @Override
    public void start(Stage stage) {
        //main pane
        BorderPane mainPane = new BorderPane();
        //left pane
        BorderPane leftPane = new BorderPane();
        GridPane[] leftPane1 = new GridPane[7];
        for (int i = 0; i < 7; i++) {
            leftPane1[i] = new GridPane();
            leftPane1[i].setVgap(15);
            leftPane1[i].setPadding(new Insets(15));
            leftPane1[i].setStyle("-fx-background-color:lightgray;-fx-border-color:black;");
            leftPane1[i].setPrefSize(1000, 650);
        }
        StackPane leftPane2 = new StackPane();
        //rigt pane
        BorderPane rightPane = new BorderPane();
        HBox rightPane1 = new HBox();
        GridPane rightPane2 = new GridPane();
        HBox rightPane3 = new HBox();
        //elements inside pane
        Button[] btn = new Button[9];
        btn[0] = new Button("Import");
        btn[1] = new Button("Infomation");
        btn[2] = new Button("Patterning");
        btn[3] = new Button("Dithering");
        btn[4] = new Button("Histogram");
        btn[5] = new Button("Convolution");
        btn[6] = new Button("Low Pass Filtering");
        btn[7] = new Button("High Pass Filtering");
        btn[8] = new Button("Export");
        Label credit = new Label("Credited by: Tan Meng Wee, Teoh Suen Cheng");
        Label[] lblbtn = new Label[6];
        Label[] lblSuccess = new Label[6];

        for (int i = 1; i < 9; i++) {
            btn[i].setDisable(true);
        }

        for (int i = 0; i < 6; i++) {
            lblbtn[i] = new Label();
            lblbtn[i].setFont(Font.font(25));
            lblSuccess[i] = new Label();
            lblSuccess[i].setFont(Font.font(25));;
            lblbtn[i].setText(btn[i + 2].getText() + " is completed.\n"
                    + "Press Export to generate raw file.");
            leftPane1[i + 1].addRow(0, lblbtn[i]);
        }

        //file
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TIFF File", "*.tif"));
        fileChooser.setTitle("Select File");

        //constructor
        information info = new information();
        patterning pattern = new patterning();
        dithering dithering = new dithering();
        histogram histogram = new histogram();
        convolution convolution = new convolution();
        lowPassFilters lowPassFilters = new lowPassFilters();
        highPassFilters highPassFilters = new highPassFilters();

        //action
        Popup popup = new Popup();
        EventHandler<ActionEvent> event = (ActionEvent e) -> {
            if (!popup.isShowing()) {
                popup.show(stage);
            } else {
                popup.hide();
            }
        };

        Label[] lbl = new Label[12];
        for (int i = 0; i < 12; i++) {
            lbl[i] = new Label();
            lbl[i].setFont(Font.font(25));
        }

        btn[0].setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(stage);
            filePath = selectedFile.getPath();
            fileName = selectedFile.getName();
            System.out.println("file path: " + filePath);
            System.out.println("file name: " + fileName);
            try {
                info.method1(filePath);
                a = filePath;
                b = info.getWidth();
                c = info.getHeight();
                d = info.getStripOffsets();
                lbl[0].setText("File name: " + fileName);
                lbl[1].setText("File path: " + filePath);
                lbl[2].setText("Byte order: " + info.getOrder());
                lbl[3].setText("Version: " + info.getVersion());
                lbl[4].setText("Offset: " + info.getOffset());
                lbl[5].setText("Total IDE: " + info.getTotalDE() + " bytes");
                lbl[6].setText("Size of IFD: " + info.getSizeIFD() + " bytes");
                lbl[7].setText("Consecutive Offset IFD: " + info.getOffset1());
                lbl[8].setText("Width: " + b);
                lbl[9].setText("Height: " + c);
                lbl[10].setText("Strip offsets: " + d);
                lbl[11].setText("Strip byte counts: " + info.getStripByteCounts() + " bytes");
                for (int i = 0; i < 12; i++) {
                    leftPane1[0].addRow(i, lbl[i]);
                }
                for (int i = 1; i < 8; i++) {
                    btn[i].setDisable(false);
                }
            } catch (IOException ex) {
                Logger.getLogger(rawShop.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btn[1].setOnAction(e -> {
            leftPane.setTop(leftPane1[0]);
            btn[8].setDisable(true);
        });

        for (int i = 2; i < 8; i++) {
            final int iii = i;
            btn[i].setOnAction(e -> {
                leftPane.setTop(leftPane1[iii - 1]);
                btn[8].setDisable(false);
                btn[8].setOnAction(e1 -> {
                    try {
                        switch (iii) {
                            case 2:
                                pattern.method2(a, b, c, d);
                                lblSuccess[iii - 2].setText("-------------"
                                        + "-------------------------------\n"
                                        + pattern.getName1() + " is generated.\n"
                                        + "path: location of imageProcessingProject.exe");
                                leftPane1[iii - 1].addRow(1, lblSuccess[iii - 2]);
                                break;
                            case 3:
                                dithering.method3(a, b, c, d);
                                lblSuccess[iii - 2].setText("-------------"
                                        + "-------------------------------\n"
                                        + dithering.getName1() + " is generated.\n"
                                        + dithering.getName2() + " is generated.\n"
                                        + "path: location of imageProcessingProject.exe");
                                leftPane1[iii - 1].addRow(1, lblSuccess[iii - 2]);
                                break;
                            case 4:
                                histogram.method4(a, d);
                                lblSuccess[iii - 2].setText("-------------"
                                        + "-------------------------------\n"
                                        + histogram.getName1() + " is generated.\n"
                                        + "path: location of imageProcessingProject.exe");
                                leftPane1[iii - 1].addRow(1, lblSuccess[iii - 2]);
                                break;
                            case 5:
                                convolution.method5(a, b, c, d);
                                lblSuccess[iii - 2].setText("-------------"
                                        + "-------------------------------\n"
                                        + convolution.getName1() + " is generated.\n"
                                        + "path: location of imageProcessingProject.exe");
                                leftPane1[iii - 1].addRow(1, lblSuccess[iii - 2]);
                                break;
                            case 6:
                                lowPassFilters.method6(a, b, c, d);
                                lblSuccess[iii - 2].setText("-------------"
                                        + "-------------------------------\n"
                                        + lowPassFilters.getName1() + " is generated.\n"
                                        + "path: location of imageProcessingProject.exe");
                                leftPane1[iii - 1].addRow(1, lblSuccess[iii - 2]);
                                break;
                            case 7:
                                highPassFilters.method7(a, b, c, d);
                                lblSuccess[iii - 2].setText("-------------"
                                        + "-------------------------------\n"
                                        + highPassFilters.getName1() + " is generated.\n"
                                        + "path: location of imageProcessingProject.exe");
                                leftPane1[iii - 1].addRow(1, lblSuccess[iii - 2]);
                                break;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(rawShop.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            });
        }

        //style
        rightPane1.setPrefSize(235, 80);
//        rightPane2.setPrefSize(235, 80);
        rightPane3.setPrefSize(235, 80);
        btn[0].setStyle("-fx-font-size:20;");
        btn[8].setStyle("-fx-font-size:20;");
        rightPane1.setAlignment(Pos.CENTER);
        rightPane3.setAlignment(Pos.CENTER);
        rightPane2.setAlignment(Pos.CENTER);
        rightPane2.setVgap(15);
        mainPane.setStyle("-fx-background-color:gray;");
        mainPane.setPadding(new Insets(15));
        leftPane2.setPadding(new Insets(5));
        rightPane1.setStyle("-fx-border-color:black;");
        rightPane2.setStyle("-fx-border-color:black;");
        rightPane3.setStyle("-fx-border-color:black;");
        leftPane2.setStyle("-fx-background-color:lightgray;-fx-border-color:black;");
        rightPane.setStyle("-fx-background-color:lightgray;-fx-border-color:black;");
        leftPane.setPrefSize(1000, 690);
        leftPane2.setPrefSize(1000, 25);
        rightPane.setPrefSize(235, 690);

        //set place
        leftPane2.getChildren().add(credit);
        rightPane1.getChildren().add(btn[0]);
        rightPane3.getChildren().add(btn[8]);
        for (int i = 1; i < 8; i++) {
            rightPane2.addRow(i - 1, btn[i]);
            btn[i].setStyle("-fx-font-size:20;");
            btn[i].setPrefWidth(200);
        }
        leftPane.setTop(leftPane1[0]);
        leftPane.setBottom(leftPane2);
        rightPane.setTop(rightPane1);
        rightPane.setCenter(rightPane2);
        rightPane.setBottom(rightPane3);
        mainPane.setLeft(leftPane);
        mainPane.setRight(rightPane);

        Scene scene = new Scene(mainPane, 1280, 720);
        stage.setScene(scene);
        stage.setTitle("TiffShop");
        stage.show();
    }
}
