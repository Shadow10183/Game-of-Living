import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 
 * 
 * @author
 * @version
 */

public class Neighbours {
    private List<Cell> neighbourCells;
    private HashMap<String, Integer> cellTypeCount;

    public Neighbours() {
        neighbourCells = new LinkedList<>();
        cellTypeCount = new HashMap<>();
    }

    public void add(Cell cell) {
        neighbourCells.add(cell);
    }

    public void typeCount(String cellName) {
        if (cellTypeCount.containsKey(cellName)) {
            cellTypeCount.put(cellName, cellTypeCount.get(cellName) + 1);
        } else {
            cellTypeCount.put(cellName, 1);
        }
    }

    public int size() {
        return neighbourCells.size();
    }

    public List<Cell> getNeighbours() {
        return neighbourCells;
    }

    public int getTypeCount(String cellName) {
        if (cellTypeCount.containsKey(cellName)) {
            return cellTypeCount.get(cellName);
        } else {
            return 0;
        }
    }

    public void shuffle() {
        Collections.shuffle(neighbourCells, Randomizer.getRandom());
    }
}
