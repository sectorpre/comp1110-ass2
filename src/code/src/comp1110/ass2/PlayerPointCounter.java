package comp1110.ass2;

import java.util.*;

/**
 * Authored by Tay Shao An
 * Used to count the number of points obtained by a particular player
 */
public class PlayerPointCounter {
    Integer player;
    Integer numOfIslands;
    Set<Integer> islands;
    List<PieceNode> allPieces;
    ArrayList<Integer>[] playersOnIslands;

    public PlayerPointCounter(int player, Board.Tile[][] tiles, int numOfIslands) {
        this.player = player;
        this.numOfIslands = numOfIslands;
        this.islands = new HashSet<>();
        this.allPieces = new ArrayList<>();
        this.playersOnIslands = new ArrayList[numOfIslands + 1];

        for (int i = 0; i < numOfIslands + 1; i++) {
            playersOnIslands[i] = new ArrayList<>();
        }

        int len;
        int x = 0;

        for (Board.Tile[] k: tiles) {
            len = 0;
            if (x % 2 == 0) {len = -1;}

            for (int y = 0; y < k.length + len; y ++) {
                Board.Tile tile = k[y];

                // if tile is occupied by player
                if (tile.occupier == player) {
                    // set of all islands occupied by (number of islands occupied)
                    if (tile.island != 0) {islands.add(tile.island);};

                    // adds all pieces on the board to a list (linked islands)
                    addNodePieces(tile, x, y, len);
                }
                // adds to a counter to the number of pieces which appear per island per player (majority islands)
                if (tile.occupier != -1) {
                    playersOnIslands[tile.island].add(tile.occupier);
                }
            }
            x += 1;
        }
    }

    public void addNodePieces(Board.Tile tile, int x, int y, int shortlong) {
        PieceNode currentNode = new PieceNode(tile.island, x, y, shortlong);
        this.allPieces.add(currentNode);
        for (PieceNode node: this.allPieces) {
            if (node.x == x && node.y - y == 1
                    || node.x == x && node.y - y == -1
                    || node.x - x == 1 && node.y == y
                    || node.x - x == -1 && node.y == y
                    || node.x == x + 1 + 2*(shortlong) && node.y - y == 1
                    || node.x + 1 + 2*(shortlong) == x && node.y - y == -1) {
                node.edges.add(currentNode);
                currentNode.edges.add(node);
            }
        }
    }

    public int islandsCounter() {
        if (islands.size() >= 8) {return 20;}
        else if (islands.size() == 7) {return 10;}
        return 0;
    }

    /**
     * Used to calculate the points from links
     */
    public int linkCounter() {
        // processing nodes
        List<Integer> branchLen = new ArrayList<>();
        for (PieceNode node: this.allPieces) {
            branchLen.add(node.nodeRunner(new HashSet<>(), new ArrayList<>()).size());
        }
        Collections.sort(branchLen);
        if (branchLen.size() >= 1) {
            return branchLen.get(branchLen.size() - 1)*5;
        }
        return 0;
    }

    /**
     * Used to calculate the number of points won from being the majority on islands
     */
    public int majorityIslandsCounter(List<Integer> islandToPoints) {
        Integer points = 0;
        List<Integer> islandsWon = new ArrayList<>();
        for (int k = 1; k < playersOnIslands.length; k ++ ) {
            var island = playersOnIslands[k];
            List<majorityIslandNode> nodes = new ArrayList<>();
            for (int p: allUniquePlayer(island)) {
                nodes.add(new majorityIslandNode(p, 0));
            }
            for (Integer i: island) {
                for (majorityIslandNode n: nodes) {
                    if (n.id == i) {
                        n.count += 1;
                        break;
                    }
                }
            }
            List<majorityIslandNode> islandResult = mostCounts(nodes);
            if (island.size() > 0) {
                for (majorityIslandNode node: islandResult) {
                    if (node.id == player) {
                        islandsWon.add(islandToPoints.get(k - 1)/islandResult.size());
                    }
                }
            }
        }

        for (Integer num: islandsWon) {
            points += num;
        }
        return points;
    }

    /**
     * Finds all unique players which appear on an island
     */
    public List<Integer> allUniquePlayer(ArrayList<Integer> array) {
        List<Integer> returnArray = new ArrayList<>();
        for (var k: array) {
            boolean add = true;
            for (var i: returnArray) {
                if (Objects.equals(i, k)) {
                    add = false;
                    break;
                }
            }
            if (add) {
                returnArray.add(k);
            }
        }
        return returnArray;
    }

    public static class majorityIslandNode {
        int id;
        int count;

        public majorityIslandNode(int id, int count) {
            this.id = id;
            this.count = count;
        }
    }

    /**
     * Used for checking if a player occupies majority tiles on an island
     */
    public List<majorityIslandNode> mostCounts(List<majorityIslandNode> nodes) {
        List<majorityIslandNode> largestNode = null;
        for (majorityIslandNode p: nodes) {
            if (largestNode == null) {
                largestNode = new ArrayList<>();
                largestNode.add(p);
            }
            else {
                if (p.count > largestNode.get(0).count) {
                    largestNode = new ArrayList<>();
                    largestNode.add(p);
                }
                else if (p.count == largestNode.get(0).count) {
                    largestNode.add(p);
                }
            }
        }
        return largestNode;
    }

    /**
     * Used to detect links between tiles
     */
    public static class PieceNode {
        int island;
        int x;
        int y;
        int shortlong;
        List<PieceNode> edges;

        public PieceNode(int island, int x, int y, int shortlong) {
            this.island = island;
            this.x = x;
            this.y = y;
            this.shortlong = shortlong;
            this.edges = new ArrayList<>();
        }

        /**
         * returns all islands that the edges of this node spans
         * @param islands - islands that this particular link spans
         * @param previousNodes - nodes that we have already checked
         * @return set containing all islands that this link spans
         */
        public Set<Integer> nodeRunner(Set<Integer> islands, List<PieceNode> previousNodes) {
            previousNodes.add(this);
            if (this.island != 0) {
                islands.add(this.island);
            }

            // for each node in this nodes' edges
            for (PieceNode edge : this.edges) {
                var getnode = 1;
                // if this node has already appeared before, ignore
                for (PieceNode node : previousNodes) {
                    if (node.equals(edge)) {
                        getnode = 0;
                        break;
                    }
                }
                // runs noderunner on each of the nodes
                if (getnode == 1) {
                    islands.addAll(edge.nodeRunner(islands, previousNodes));
                }
            }
            return islands;
        }

        @Override
        public String toString() {
            return island + ":" + x + "," + y + ":" + this.edges.size();
        }
    }


}

