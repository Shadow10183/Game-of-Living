import javafx.scene.paint.Color;

/**
 * Simplest form of life.
 * Fun Fact: Symbiote cells are one of the simplest forms of life. A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
 */

public class Symbiote extends Cell {
    private static final Color DEFAULT_COLOR = Color.YELLOW;

    /**
     * Create a new Symbiote.
     *
     * @param field    The field currently occupied.
     * @param location The location within the field.
     */
    public Symbiote(Field field, Location location) {
        super(field, location, DEFAULT_COLOR, "Symbiote");
        setNextState(true);
    }

    /**
     * This is how the Symbiote decides if it's alive or not
     */
    public void act() {
        setAge(getAge() + 1);
        Neighbours neighbours = getField().getLivingNeighbours(getLocation(), 1);
        for (int i = 0; i < neighbours.size(); i++) {
            Cell cell = neighbours.getNeighbours().get(i);
            if (cell.getName() == "InfectedCell") {
                cell.morphCell("Mycoplasma");
            }
        }

    }
}
