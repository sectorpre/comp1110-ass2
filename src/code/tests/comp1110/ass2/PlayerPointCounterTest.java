package comp1110.ass2;

import comp1110.ass2.stringcomparator.StringComparator;
import comp1110.ass2.testdata.DataLoader;
import comp1110.ass2.testdata.GameDataLoader;
import comp1110.ass2.testdata.PlacePieceDataLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class PlayerPointCounterTest {

    @Test
    public void linkCounterTest(){
        //for (Board.Tile[] k: test.board.tiles) {
        //            System.out.println("");
        //            for (var i: k) {
        //                if (i != null) {
        //                    System.out.print(i.island + " ");
        //                }
        //            }
        //        }

        Model test = new Model();
        String IN_A_COL = "a 13 2; c 0 E; i 6 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 1,4 2,0 2,1; i 6 0,5 0,6 0,7 1,6 1,7 1,8 2,6 2,7 2,8 3,7 3,8; i 6 7,12 8,11 9,11 9,12 10,10 10,11 11,10 11,11 11,12 12,10 12,11; i 8 0,9 0,10 0,11 1,10 1,11 1,12 2,10 2,11 3,10 3,11 3,12 4,10 4,11 5,11 5,12; i 8 4,0 5,0 5,1 6,0 6,1 7,0 7,1 7,2 8,0 8,1 8,2 9,0 9,1 9,2; i 8 10,3 10,4 11,0 11,1 11,2 11,3 11,4 11,5 12,0 12,1 12,2 12,3 12,4 12,5; i 10 3,3 3,4 3,5 4,2 4,3 4,4 4,5 5,3 5,4 5,5 5,6 6,3 6,4 6,5 6,6 7,4 7,5 7,6 8,4 8,5; i 10 5,8 5,9 6,8 6,9 7,8 7,9 7,10 8,7 8,8 8,9 9,7 9,8 9,9 10,6 10,7 10,8 11,7 11,8 12,7 12,8; s 0,0 0,5 0,9 1,4 1,8 1,12 2,1 3,5 3,7 3,10 3,12 4,0 4,2 5,9 5,11 6,3 6,6 7,0 7,8 7,12 8,2 8,5 9,0 9,9 10,3 10,6 10,10 11,0 11,5 12,2 12,8 12,11; r C 1,8 3,5 3,12 7,12 12,8 12,11 B 0,9 1,4 3,7 3,10 5,11 9,0 W 1,12 4,0 4,2 8,2 10,6 12,2 P 0,0 2,1 5,9 6,6 9,9 11,5 S 0,5 6,3 7,0 7,8 8,5 10,3 10,10 11,0; p 0 0 0 0 0 0 0 S 0,0 1,0 2,0 3,0 4,0 5,0 6,0 7,0 8,0 9,0 10,0 11,0 12,0 T; p 1 0 0 0 0 0 0 S T;";
        test.toModel(IN_A_COL);
        PlayerPointCounter pointCounter = new PlayerPointCounter(0, test.board.tiles, test.board.numOfIslands );
        Assertions.assertEquals(15, pointCounter.linkCounter(), "Whoops");

        String IN_A_ROW = "a 13 2; c 0 E; i 6 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 1,4 2,0 2,1; i 6 0,5 0,6 0,7 1,6 1,7 1,8 2,6 2,7 2,8 3,7 3,8; i 6 7,12 8,11 9,11 9,12 10,10 10,11 11,10 11,11 11,12 12,10 12,11; i 8 0,9 0,10 0,11 1,10 1,11 1,12 2,10 2,11 3,10 3,11 3,12 4,10 4,11 5,11 5,12; i 8 4,0 5,0 5,1 6,0 6,1 7,0 7,1 7,2 8,0 8,1 8,2 9,0 9,1 9,2; i 8 10,3 10,4 11,0 11,1 11,2 11,3 11,4 11,5 12,0 12,1 12,2 12,3 12,4 12,5; i 10 3,3 3,4 3,5 4,2 4,3 4,4 4,5 5,3 5,4 5,5 5,6 6,3 6,4 6,5 6,6 7,4 7,5 7,6 8,4 8,5; i 10 5,8 5,9 6,8 6,9 7,8 7,9 7,10 8,7 8,8 8,9 9,7 9,8 9,9 10,6 10,7 10,8 11,7 11,8 12,7 12,8; s 0,0 0,5 0,9 1,4 1,8 1,12 2,1 3,5 3,7 3,10 3,12 4,0 4,2 5,9 5,11 6,3 6,6 7,0 7,8 7,12 8,2 8,5 9,0 9,9 10,3 10,6 10,10 11,0 11,5 12,2 12,8 12,11; r C 1,8 3,5 3,12 7,12 12,8 12,11 B 0,9 1,4 3,7 3,10 5,11 9,0 W 1,12 4,0 4,2 8,2 10,6 12,2 P 0,0 2,1 5,9 6,6 9,9 11,5 S 0,5 6,3 7,0 7,8 8,5 10,3 10,10 11,0; p 0 0 0 0 0 0 0 S 0,0 0,1 0,2 0,3 0,4 0,5 0,6 0,7 0,8 0,9 0,10 0,11 T; p 1 0 0 0 0 0 0 S T;";
        test.toModel(IN_A_ROW);
        pointCounter = new PlayerPointCounter(0, test.board.tiles, test.board.numOfIslands );
        Assertions.assertEquals(15, pointCounter.linkCounter(), "Whoops");

        String ACROSS= "a 13 2; c 0 E; i 6 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 1,4 2,0 2,1; i 6 0,5 0,6 0,7 1,6 1,7 1,8 2,6 2,7 2,8 3,7 3,8; i 6 7,12 8,11 9,11 9,12 10,10 10,11 11,10 11,11 11,12 12,10 12,11; i 8 0,9 0,10 0,11 1,10 1,11 1,12 2,10 2,11 3,10 3,11 3,12 4,10 4,11 5,11 5,12; i 8 4,0 5,0 5,1 6,0 6,1 7,0 7,1 7,2 8,0 8,1 8,2 9,0 9,1 9,2; i 8 10,3 10,4 11,0 11,1 11,2 11,3 11,4 11,5 12,0 12,1 12,2 12,3 12,4 12,5; i 10 3,3 3,4 3,5 4,2 4,3 4,4 4,5 5,3 5,4 5,5 5,6 6,3 6,4 6,5 6,6 7,4 7,5 7,6 8,4 8,5; i 10 5,8 5,9 6,8 6,9 7,8 7,9 7,10 8,7 8,8 8,9 9,7 9,8 9,9 10,6 10,7 10,8 11,7 11,8 12,7 12,8; s 0,0 0,5 0,9 1,4 1,8 1,12 2,1 3,5 3,7 3,10 3,12 4,0 4,2 5,9 5,11 6,3 6,6 7,0 7,8 7,12 8,2 8,5 9,0 9,9 10,3 10,6 10,10 11,0 11,5 12,2 12,8 12,11; r C 1,8 3,5 3,12 7,12 12,8 12,11 B 0,9 1,4 3,7 3,10 5,11 9,0 W 1,12 4,0 4,2 8,2 10,6 12,2 P 0,0 2,1 5,9 6,6 9,9 11,5 S 0,5 6,3 7,0 7,8 8,5 10,3 10,10 11,0; p 0 0 0 0 0 0 0 S 0,0 1,1 2,1 3,2 4,2 5,3 6,3 7,4 8,4 9,5 10,5 11,6 T; p 1 0 0 0 0 0 0 S T;";
        test.toModel(ACROSS);
        pointCounter = new PlayerPointCounter(0, test.board.tiles, test.board.numOfIslands );
        Assertions.assertEquals(10, pointCounter.linkCounter(), "Whoops");
    }

    @Test
    public void majorityIslandTest() {
        Model test = new Model();

        String SPLITPOINTS = "a 13 2; c 0 E; i 6 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 1,4 2,0 2,1; i 6 0,5 0,6 0,7 1,6 1,7 1,8 2,6 2,7 2,8 3,7 3,8; i 6 7,12 8,11 9,11 9,12 10,10 10,11 11,10 11,11 11,12 12,10 12,11; i 8 0,9 0,10 0,11 1,10 1,11 1,12 2,10 2,11 3,10 3,11 3,12 4,10 4,11 5,11 5,12; i 8 4,0 5,0 5,1 6,0 6,1 7,0 7,1 7,2 8,0 8,1 8,2 9,0 9,1 9,2; i 8 10,3 10,4 11,0 11,1 11,2 11,3 11,4 11,5 12,0 12,1 12,2 12,3 12,4 12,5; i 10 3,3 3,4 3,5 4,2 4,3 4,4 4,5 5,3 5,4 5,5 5,6 6,3 6,4 6,5 6,6 7,4 7,5 7,6 8,4 8,5; i 10 5,8 5,9 6,8 6,9 7,8 7,9 7,10 8,7 8,8 8,9 9,7 9,8 9,9 10,6 10,7 10,8 11,7 11,8 12,7 12,8; s 0,0 0,5 0,9 1,4 1,8 1,12 2,1 3,5 3,7 3,10 3,12 4,0 4,2 5,9 5,11 6,3 6,6 7,0 7,8 7,12 8,2 8,5 9,0 9,9 10,3 10,6 10,10 11,0 11,5 12,2 12,8 12,11; r C 1,8 3,5 3,12 7,12 12,8 12,11 B 0,9 1,4 3,7 3,10 5,11 9,0 W 1,12 4,0 4,2 8,2 10,6 12,2 P 0,0 2,1 5,9 6,6 9,9 11,5 S 0,5 6,3 7,0 7,8 8,5 10,3 10,10 11,0; p 0 0 0 0 0 0 0 S 0,0 0,1 0,2 0,3 T; p 1 0 0 0 0 0 0 S 1,0 1,1 1,2 1,3 T;";
        test.toModel(SPLITPOINTS);
        PlayerPointCounter pointCounter = new PlayerPointCounter(0, test.board.tiles, test.board.numOfIslands );
        Assertions.assertEquals(3, pointCounter.majorityIslandsCounter(test.board.islandToPoints), "Whoops");

        String PLAYER_0_WINS = "a 13 2; c 0 E; i 6 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 1,4 2,0 2,1; i 6 0,5 0,6 0,7 1,6 1,7 1,8 2,6 2,7 2,8 3,7 3,8; i 6 7,12 8,11 9,11 9,12 10,10 10,11 11,10 11,11 11,12 12,10 12,11; i 8 0,9 0,10 0,11 1,10 1,11 1,12 2,10 2,11 3,10 3,11 3,12 4,10 4,11 5,11 5,12; i 8 4,0 5,0 5,1 6,0 6,1 7,0 7,1 7,2 8,0 8,1 8,2 9,0 9,1 9,2; i 8 10,3 10,4 11,0 11,1 11,2 11,3 11,4 11,5 12,0 12,1 12,2 12,3 12,4 12,5; i 10 3,3 3,4 3,5 4,2 4,3 4,4 4,5 5,3 5,4 5,5 5,6 6,3 6,4 6,5 6,6 7,4 7,5 7,6 8,4 8,5; i 10 5,8 5,9 6,8 6,9 7,8 7,9 7,10 8,7 8,8 8,9 9,7 9,8 9,9 10,6 10,7 10,8 11,7 11,8 12,7 12,8; s 0,0 0,5 0,9 1,4 1,8 1,12 2,1 3,5 3,7 3,10 3,12 4,0 4,2 5,9 5,11 6,3 6,6 7,0 7,8 7,12 8,2 8,5 9,0 9,9 10,3 10,6 10,10 11,0 11,5 12,2 12,8 12,11; r C 1,8 3,5 3,12 7,12 12,8 12,11 B 0,9 1,4 3,7 3,10 5,11 9,0 W 1,12 4,0 4,2 8,2 10,6 12,2 P 0,0 2,1 5,9 6,6 9,9 11,5 S 0,5 6,3 7,0 7,8 8,5 10,3 10,10 11,0; p 0 0 0 0 0 0 0 S 0,0 0,1 0,2 0,3 T; p 1 0 0 0 0 0 0 S 1,0 1,2 1,3 T;";
        test.toModel(PLAYER_0_WINS);
        pointCounter = new PlayerPointCounter(0, test.board.tiles, test.board.numOfIslands );
        Assertions.assertEquals(6, pointCounter.majorityIslandsCounter(test.board.islandToPoints), "Whoops");

        String PLAYER_1_WINS = "a 13 2; c 0 E; i 6 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 1,4 2,0 2,1; i 6 0,5 0,6 0,7 1,6 1,7 1,8 2,6 2,7 2,8 3,7 3,8; i 6 7,12 8,11 9,11 9,12 10,10 10,11 11,10 11,11 11,12 12,10 12,11; i 8 0,9 0,10 0,11 1,10 1,11 1,12 2,10 2,11 3,10 3,11 3,12 4,10 4,11 5,11 5,12; i 8 4,0 5,0 5,1 6,0 6,1 7,0 7,1 7,2 8,0 8,1 8,2 9,0 9,1 9,2; i 8 10,3 10,4 11,0 11,1 11,2 11,3 11,4 11,5 12,0 12,1 12,2 12,3 12,4 12,5; i 10 3,3 3,4 3,5 4,2 4,3 4,4 4,5 5,3 5,4 5,5 5,6 6,3 6,4 6,5 6,6 7,4 7,5 7,6 8,4 8,5; i 10 5,8 5,9 6,8 6,9 7,8 7,9 7,10 8,7 8,8 8,9 9,7 9,8 9,9 10,6 10,7 10,8 11,7 11,8 12,7 12,8; s 0,0 0,5 0,9 1,4 1,8 1,12 2,1 3,5 3,7 3,10 3,12 4,0 4,2 5,9 5,11 6,3 6,6 7,0 7,8 7,12 8,2 8,5 9,0 9,9 10,3 10,6 10,10 11,0 11,5 12,2 12,8 12,11; r C 1,8 3,5 3,12 7,12 12,8 12,11 B 0,9 1,4 3,7 3,10 5,11 9,0 W 1,12 4,0 4,2 8,2 10,6 12,2 P 0,0 2,1 5,9 6,6 9,9 11,5 S 0,5 6,3 7,0 7,8 8,5 10,3 10,10 11,0; p 0 0 0 0 0 0 0 S 0,0 0,1 0,2 0,3 T; p 1 0 0 0 0 0 0 S 1,0 1,1 1,2 1,3 1,4 T;";
        test.toModel(PLAYER_1_WINS);
        pointCounter = new PlayerPointCounter(1, test.board.tiles, test.board.numOfIslands );
        Assertions.assertEquals(6, pointCounter.majorityIslandsCounter(test.board.islandToPoints), "Whoops");
    }

    @Test
    public void islandCounterTest() {
        Model test = new Model();

        String SEVEN_ISLANDS = "a 13 2; c 0 E; i 6 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 1,4 2,0 2,1; i 6 0,5 0,6 0,7 1,6 1,7 1,8 2,6 2,7 2,8 3,7 3,8; i 6 7,12 8,11 9,11 9,12 10,10 10,11 11,10 11,11 11,12 12,10 12,11; i 8 0,9 0,10 0,11 1,10 1,11 1,12 2,10 2,11 3,10 3,11 3,12 4,10 4,11 5,11 5,12; i 8 4,0 5,0 5,1 6,0 6,1 7,0 7,1 7,2 8,0 8,1 8,2 9,0 9,1 9,2; i 8 10,3 10,4 11,0 11,1 11,2 11,3 11,4 11,5 12,0 12,1 12,2 12,3 12,4 12,5; i 10 3,3 3,4 3,5 4,2 4,3 4,4 4,5 5,3 5,4 5,5 5,6 6,3 6,4 6,5 6,6 7,4 7,5 7,6 8,4 8,5; i 10 5,8 5,9 6,8 6,9 7,8 7,9 7,10 8,7 8,8 8,9 9,7 9,8 9,9 10,6 10,7 10,8 11,7 11,8 12,7 12,8; s 0,0 0,5 0,9 1,4 1,8 1,12 2,1 3,5 3,7 3,10 3,12 4,0 4,2 5,9 5,11 6,3 6,6 7,0 7,8 7,12 8,2 8,5 9,0 9,9 10,3 10,6 10,10 11,0 11,5 12,2 12,8 12,11; r C 1,8 3,5 3,12 7,12 12,8 12,11 B 0,9 1,4 3,7 3,10 5,11 9,0 W 1,12 4,0 4,2 8,2 10,6 12,2 P 0,0 2,1 5,9 6,6 9,9 11,5 S 0,5 6,3 7,0 7,8 8,5 10,3 10,10 11,0; p 0 0 0 0 0 0 0 S 0,0 0,6 0,10 3,5 4,0 5,9 7,12 T; p 1 0 0 0 0 0 0 S T;";
        test.toModel(SEVEN_ISLANDS);
        PlayerPointCounter pointCounter = new PlayerPointCounter(0, test.board.tiles, test.board.numOfIslands );
        Assertions.assertEquals(10, pointCounter.islandsCounter(), "Whoops");

        String NO_POINTS = "a 13 2; c 0 E; i 6 0,0 0,1 0,2 0,3 1,0 1,1 1,2 1,3 1,4 2,0 2,1; i 6 0,5 0,6 0,7 1,6 1,7 1,8 2,6 2,7 2,8 3,7 3,8; i 6 7,12 8,11 9,11 9,12 10,10 10,11 11,10 11,11 11,12 12,10 12,11; i 8 0,9 0,10 0,11 1,10 1,11 1,12 2,10 2,11 3,10 3,11 3,12 4,10 4,11 5,11 5,12; i 8 4,0 5,0 5,1 6,0 6,1 7,0 7,1 7,2 8,0 8,1 8,2 9,0 9,1 9,2; i 8 10,3 10,4 11,0 11,1 11,2 11,3 11,4 11,5 12,0 12,1 12,2 12,3 12,4 12,5; i 10 3,3 3,4 3,5 4,2 4,3 4,4 4,5 5,3 5,4 5,5 5,6 6,3 6,4 6,5 6,6 7,4 7,5 7,6 8,4 8,5; i 10 5,8 5,9 6,8 6,9 7,8 7,9 7,10 8,7 8,8 8,9 9,7 9,8 9,9 10,6 10,7 10,8 11,7 11,8 12,7 12,8; s 0,0 0,5 0,9 1,4 1,8 1,12 2,1 3,5 3,7 3,10 3,12 4,0 4,2 5,9 5,11 6,3 6,6 7,0 7,8 7,12 8,2 8,5 9,0 9,9 10,3 10,6 10,10 11,0 11,5 12,2 12,8 12,11; r C 1,8 3,5 3,12 7,12 12,8 12,11 B 0,9 1,4 3,7 3,10 5,11 9,0 W 1,12 4,0 4,2 8,2 10,6 12,2 P 0,0 2,1 5,9 6,6 9,9 11,5 S 0,5 6,3 7,0 7,8 8,5 10,3 10,10 11,0; p 0 0 0 0 0 0 0 S 0,0 0,6 T; p 1 0 0 0 0 0 0 S T;";
        test.toModel(NO_POINTS);
        pointCounter = new PlayerPointCounter(0, test.board.tiles, test.board.numOfIslands );
        Assertions.assertEquals(0, pointCounter.islandsCounter(), "Whoops");

    }
}
