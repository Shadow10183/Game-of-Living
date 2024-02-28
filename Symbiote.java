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
    private static final Color DEFAULT_COLOR = Color.SKYBLUE;

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
        Neighbours neighbours = getField().getLivingNeighbours(getLocation(), 1);
        if (neighbours.getTypeCount("Mycoplasma") == 0) {
            if (getAge() > 10) {
                morphCell("EmptyCell");
            }
            setAge(getAge() + 1);
        }
        Neighbours extendedNeighbours = getField().getLivingNeighbours(getLocation(), 5);
        if (extendedNeighbours.getTypeCount("InfectedCell") > 0) {
            int distance = 1;
            while (getField().getLivingNeighbours(getLocation(), distance).getTypeCount("InfectedCell") == 0) {
                distance += 1;
            }
            if (distance > 1) {
                Neighbours deadNeighbours = getField().getDeadNeighbours(getLocation(), 1);
                for (Cell cell : deadNeighbours.getNeighbours()) {
                    EmptyCell emptyCell = (EmptyCell) cell;
                    if (getField().getLivingNeighbours(emptyCell.getLocation(), distance - 1)
                            .getTypeCount("InfectedCell") > 0 && emptyCell.occupy()) {
                        morphCell("EmptyCell");
                        setLocation(emptyCell.getLocation());
                        emptyCell.morphCell(this);
                        break;
                    }
                }
            }
        }
    }
}
