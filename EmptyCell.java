import javafx.scene.paint.Color;

/**
 * No form of life.
 *
 * @author
 * @version
 */

public class EmptyCell extends Cell {
    private static final Color DEFAULT_COLOR = Color.WHITE;
    private boolean occupiedNext = false;

    /**
     * Create a new TestCell.
     *
     * @param field    The field currently occupied.
     * @param location The location within the field.
     */
    public EmptyCell(Field field, Location location) {
        super(field, location, DEFAULT_COLOR, "EmptyCell");
    }

    /**
     * This is how the TestCell decides if it's alive or not
     */
    public void act() {
        Neighbours neighbours = getField().getLivingNeighbours(getLocation(), 1);
        if (neighbours.getTypeCount("Mycoplasma") == 3) {
            morphCell("Mycoplasma");
            setAge(0);
            setColor(DEFAULT_COLOR);
        }
    }

    public boolean occupy() {
        if (!occupiedNext) {
            occupiedNext = true;
            return true;
        } else {
            return false;
        }
    }
}
