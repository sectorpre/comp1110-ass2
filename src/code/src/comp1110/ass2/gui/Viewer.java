package comp1110.ass2.gui;

import comp1110.ass2.BlueLagoon;
import comp1110.ass2.Board;
import comp1110.ass2.Model;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Authored by Kenney Siu
 * A visual representation of a given stateString
 */

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField stateTextField;

    // instance of our game
    private Model model = new Model();



    // Path to get to our resource folder
    private static final String URI_BASE = "Resources/";
    // Margins of the board
    private static final int MARGIN_X = 200;
    private static final int MARGIN_Y = 10;
    // spacing between images
    private static final double TILE_SPACING_X = 60.0;
    // offset used for moving tile son even rows
    private static final double OFFSET = TILE_SPACING_X/2.0;
    private static final double TILE_SPACING_Y = 42.0;
    // Used to calculate current board relative to default board size
    private static final double DEFAULT_BOARD = 13;
    // Used to modify the size of the tiles for different board sizes
    private static double SIZING_RATIO;

    // Used to convert an arrayList with positions to coordinates.
    public String toPositionString(ArrayList<int[]> arrayList) {
        String result = "";
        for (int[] array : arrayList) {
            result += Arrays.toString(array) + " ";
        }

        return result;
    }
    // displays the board, islands, stones, resources
    private void makeBoard() {
        int boardSize = this.model.board.boardSize;
        Board.Tile[][] tiles = this.model.board.tiles;
        SIZING_RATIO = DEFAULT_BOARD / boardSize;

        for (int row = 0; row < boardSize; row++) {
            int var = 0;
            if (row % 2 == 0) {
                var = 1;
            }
            for (int col = 0; col < boardSize - var; col++) {
                Board.Tile curr = tiles[row][col];
                String path = URI_BASE + curr.toURL();
                Image image = new Image(getClass().getResource(path).toString());
                ImageView tileImage = new ImageView(image);
                tileImage.setFitWidth(65 * SIZING_RATIO);
                tileImage.setFitHeight(89 * SIZING_RATIO);
                tileImage.setPreserveRatio(true);
                tileImage.setLayoutX((col * TILE_SPACING_X * SIZING_RATIO) +
                        (MARGIN_X) + var * OFFSET * SIZING_RATIO);
                tileImage.setLayoutY((row * TILE_SPACING_Y * SIZING_RATIO) +
                        (MARGIN_Y));


                root.getChildren().add(tileImage);
            }
        }
    }

    /**
     * Given a state string, draw a representation of the state
     * on the screen.
     * <p>
     * This may prove useful for debugging complex states.
     *
     * @param stateString a string representing a game state
     */
    void displayState(String stateString) {
        // FIXME Task 5
        root.getChildren().clear();
        root.getChildren().add(controls);
        legend();
        if (BlueLagoon.isStateStringWellFormed(stateString)
                && !stateString.isBlank()) {

            this.model.toModel(stateString);
            makeControls();
            makeBoard();
            displayArrangement();
            makeResources();
            currentState();
            displayPlayers();

        }
    }

    /**
     * Display a legend in the top right corner showing what each square or hexagon represents
     */

    private void legend() {
        Rectangle backdrop = new Rectangle(1040,0, 200,210);
        root.getChildren().add(backdrop);
        backdrop.setFill(Color.BLUE);
        String[] legend = new String[]
                {"Water Tile", "Island", "Stones", "Coconuts",
                "Bamboo", "Water", "Precious Stone", "Statuettes", "Settlers", "Villages"};
        int initialY = 20;
        for (int i = 0; i < legend.length; i++) {
            Text text = new Text(legend[i]);
            text.setLayoutX(1050);
            text.setLayoutY(initialY);
            legendToPic(legend[i], 1160, initialY - 20,30);
            initialY += 20;



            root.getChildren().add(text);

        }
    }

    // create a legend showing what icons represent
    private void legendToPic(String legend, int x, int y, double size) {
        switch (legend) {
            case "Water Tile" -> {
                Image image = new Image(Game.class.getResource( URI_BASE  +
                        "water.png").toString());
                ImageView tileImage = new ImageView(image);
                tileImage.setFitWidth(size);
                tileImage.setFitHeight(size);
                tileImage.setLayoutX(x);
                tileImage.setLayoutY(y);
                root.getChildren().add(tileImage);
            }
            case "Island" -> {
                Image image = new Image(Game.class.getResource( URI_BASE  +
                        "grass.png").toString());
                ImageView tileImage = new ImageView(image);
                tileImage.setFitWidth(size);
                tileImage.setFitHeight(size);
                tileImage.setLayoutX(x);
                tileImage.setLayoutY(y);
                root.getChildren().add(tileImage);
            }

            case "Stones" -> {
                Image image = new Image(Game.class.getResource( URI_BASE  +
                        "stone.png").toString());
                ImageView tileImage = new ImageView(image);
                tileImage.setFitWidth(size);
                tileImage.setFitHeight(size);
                tileImage.setLayoutX(x);
                tileImage.setLayoutY(y);
                root.getChildren().add(tileImage);
            }
            case "Settlers" -> {
                Image image = new Image(Game.class.getResource( URI_BASE  +
                        "settler0.png").toString());
                ImageView tileImage = new ImageView(image);
                tileImage.setFitWidth(size);
                tileImage.setFitHeight(size);
                tileImage.setLayoutX(x);
                tileImage.setLayoutY(y);
                root.getChildren().add(tileImage);
            }
            case "Villages" -> {
                Image image = new Image(Game.class.getResource( URI_BASE  +
                        "village0.png").toString());
                ImageView tileImage = new ImageView(image);
                tileImage.setFitWidth(size);
                tileImage.setFitHeight(size);
                tileImage.setLayoutX(x);
                tileImage.setLayoutY(y);
                root.getChildren().add(tileImage);
            }
            case "Coconuts" -> {
                resourceToShape(x+20,y+10,
                        Board.Tile.Resource.COCO,size/5);
            }
            case "Bamboo" -> {
                resourceToShape(x+20,y+10,
                        Board.Tile.Resource.BBOO,size/5);
            }
            case "Water" -> {
                resourceToShape(x+20,y+10,
                        Board.Tile.Resource.WATR,size/5);
            }
            case "Precious Stone" -> {
                resourceToShape(x+20,y+10,
                        Board.Tile.Resource.STON,size/5);
            }
            case "Statuettes" -> {
                resourceToShape(x+20,y+10,
                        Board.Tile.Resource.STAT,size/5);
            }


        }
    }
    /**
     * Given the Game Arrangement Statement of a state String, display game information and
     * display the board as water tiles
     */
    private void displayArrangement() {

        Text layout = new Text("Layout: " + model.board.boardSize + " high" +
                " Players: " + model.board.playerList.size());
        layout.setLayoutX(0);
        layout.setLayoutY(20);
        root.getChildren().addAll(layout);
    }
    /**
     * Given the stones Statement of a state String, display the stones as grey hexagons
     */
    private void makeResources() {
        ArrayList<int[]> stoneCoords = model.board.gettiles(1,0,0);
        for (int[] coords : stoneCoords) {
            int row = coords[0];
            int col = coords[1];
            double boardX;
            double boardY;
            Board.Tile.Resource resource = this.model.board.tiles[row][col].resource;
            if (resource != null) {
                if (row % 2 == 0) {
                    boardX = (col * TILE_SPACING_X * SIZING_RATIO) + (TILE_SPACING_X/2) *
                            SIZING_RATIO + OFFSET * SIZING_RATIO+ MARGIN_X;
                } else boardX = (col * TILE_SPACING_X * SIZING_RATIO) + (TILE_SPACING_X/2) *
                        SIZING_RATIO + MARGIN_X;
                boardY = row * TILE_SPACING_Y * SIZING_RATIO + (3*TILE_SPACING_Y/4) *
                        SIZING_RATIO + MARGIN_Y;
                resourceToShape(boardX,boardY,resource, 20 * SIZING_RATIO);

            }
        }

    }

    // Convert resources into shapes that can be displayed
    public void resourceToShape(double x, double y, Board.Tile.Resource resource, double size) {
        switch (resource) {
            case STON -> {
                Rectangle rectangle = new Rectangle(x,y,size/2,size);
                rectangle.setFill(Color.GREEN);
                root.getChildren().add(rectangle);
            }
            case COCO -> {
                Circle circle = new Circle(x,y,size,Color.WHITE);
                root.getChildren().add(circle);
            }
            case BBOO -> {
                Rectangle rectangle = new Rectangle(x,y,size/2,size);
                rectangle.setFill(Color.YELLOW);
                root.getChildren().add(rectangle);
            }
            case STAT -> {
                Rectangle rectangle = new Rectangle(x,y,size/2,size);
                rectangle.setFill(Color.FIREBRICK);
                root.getChildren().add(rectangle);
            }
            case WATR -> {
                Circle circle = new Circle(x,y,size,Color.FIREBRICK);
                root.getChildren().add(circle);
            }
        }
    }




// display player statistics
    private void displayPlayers() {
        int initialY = 60;
        int playerSpacing = 20;
        int initialY2 = 600;
        int initialX = VIEWER_WIDTH/3;
        List<Board.Player> players = model.board.playerList;
        for (Board.Player player : players) {

            Text playerText = new Text(0,initialY + playerSpacing *
                    players.indexOf(player), "Player: " + player.id + " Statistics");
            initialY += 20;
            Text score = new Text(0,initialY + playerSpacing *
                    players.indexOf(player), "Score: " + player.getPoints());
            initialY += 20;
            Text resourcesInstructions  = new Text(0,initialY +
                    playerSpacing *  players.indexOf(player), "COCO,BBOO,WATR,STON,STAT");
            initialY += 20;
            Text resources = new Text(0,initialY + playerSpacing *
                    players.indexOf(player), "Resources: " + Arrays.toString(player.resources));
            initialY += 20;
            Text settlers = new Text(initialX,initialY2 + (playerSpacing/2) *
                    players.indexOf(player), "Settlers Placed Player " + player.id + ":" +
                    toPositionString(model.board.gettiles(0,player.id,0)));
            initialY2 += 8;
            Text villages = new Text(initialX,initialY2 + (playerSpacing/2) *
                    players.indexOf(player), "Villages Placed Player "  + player.id + ":"  +
                    toPositionString(model.board.gettiles(0, player.id,1)));
            settlers.setFont(Font.font("verdana",
                    FontWeight.BOLD, FontPosture.REGULAR, 8));
            villages.setFont(Font.font("verdana",
                    FontWeight.BOLD, FontPosture.REGULAR, 8));


            root.getChildren().addAll(playerText,score,
                    resources, resourcesInstructions,settlers,villages);
        }
    }



    // display information about the current state
    private void currentState() {
        int currentPlayer = model.currentPlayer;
        String gamestate = "Settler";
        if (model.gamestate == 0) {
            gamestate = "Exploration";
        }
        Text state = new Text("Player to Move: " + currentPlayer + " , Phase: " + gamestate);
        state.setLayoutX(0);
        state.setLayoutY(40);
        root.getChildren().add(state);
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label playerLabel = new Label("Game State:");
        stateTextField = new TextField();
        stateTextField.setPrefWidth(200);
        Button button = new Button("Refresh");
        button.setOnAction(e -> displayState(stateTextField.getText()));
        HBox hb = new HBox();
        hb.getChildren().addAll(playerLabel, stateTextField, button);
        hb.setSpacing(10);
        hb.setLayoutX(50);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Blue Lagoon Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        root.getChildren().add(controls);
        legend();
        makeControls();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
