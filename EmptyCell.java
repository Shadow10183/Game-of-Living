import javafx.scene.paint.Color;

/**
 * Empty cells represent blank locations in the field.
 * i.e. dead cell
 *
 * @author Lance Eric Castro So K21055616, Leung Yau Hei K23093432
 */

public class EmptyCell extends Cell {

    private static final Color DEFAULT_COLOR = Color.WHITE;
    private boolean occupiedNext = false;

    /**
     * Create a new EmptyCell.
     *
     * @param field    The field currently occupied.
     * @param location The location within the field.
     */
    public EmptyCell(Field field, Location location) {
        super(field, location, DEFAULT_COLOR, "EmptyCell");
    }

    /**
     * An empty cell will become a mycoplasma when there are exactly three
     * mycoplasma around the empty cell.
     * i.e. a mycoplasma is created in its location.
     */
    public void act() {
        Neighbours neighbours = getField().getLivingNeighbours(getLocation(), 1);
        if (neighbours.getTypeCount("Mycoplasma") == 3) {
            transform("Mycoplasma");
            setAge(0);
            setColor(DEFAULT_COLOR);
        }
    }

    /**
     * Allows living cells to occupy its location with first come, first serve.
     * 
     * @return true if occupation was successful.
     * @return false if it was already occupied.
     */
    public boolean occupy() {
        if (!occupiedNext) {
            occupiedNext = true;
            return true;
        } else {
            return false;
        }
    }
}