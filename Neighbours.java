import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class is responsible for identifying the cells around other cells (neighbours). 
 * 
 * @author Lance Eric Castro So K21055616, Leung Yau Hei K23093432
 */

public class Neighbours {
    
    private List<Cell> neighbourCells;
    private HashMap<String, Integer> cellTypeCount;

    /**
     * Create a linked list for neighbouring cells and a hash map for the counting
     * of a particular cell.
     */
    public Neighbours() {
        neighbourCells = new LinkedList<>();
        cellTypeCount = new HashMap<>();
    }

    /**
     * Add the neighbouring cell into the linked list called neighbourCells.
     */
    public void add(Cell cell) {
        neighbourCells.add(cell);
    }

    /**
     * Get the number of neighbours of a particular cell.
     */
    public void typeCount(String cellName) {
        if (cellTypeCount.containsKey(cellName)) {
            cellTypeCount.put(cellName, cellTypeCount.get(cellName) + 1);
        } else {
            cellTypeCount.put(cellName, 1);
        }
    }

    /**
     * Return the number of neighbouring cells.
     */
    public int size() {
        return neighbourCells.size();
    }
    
    /**
     * Return a random neighbour.
     */
    public Cell getRandomNeighbour() {
        return neighbourCells.get(0);
    }
    
    /**
     * Return the list of neighbouring cells.
     */
    public List<Cell> getNeighbours() {
        return neighbourCells;
    }

    /**
     * Return the number of neighbours of a particular cell.
     */
    public int getTypeCount(String cellName) {
        if (cellTypeCount.containsKey(cellName)) {
            return cellTypeCount.get(cellName);
        } else {
            return 0;
        }
    }

    /**
     * Shuffle the neighbouring cells.
     */
    public void shuffle() {
        Collections.shuffle(neighbourCells, Randomizer.getRandom());
    }
}