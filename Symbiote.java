import javafx.scene.paint.Color;

/**
 * A type of cell that has symbiotic relationship with mycoplasma
 *
 * @author Lance Eric Castro So K21055616, Leung Yau Hei K23093432
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
     * How the symbiote should act.
     */
    public void act() {
        // If the symbiote has zero neighbouring mycoplasma and it is at least age 11,
        // it will die and leave an empty cell behind.
        Neighbours neighbours = getField().getLivingNeighbours(getLocation(), 1);
        if (neighbours.getTypeCount("Mycoplasma") == 0) {
            if (getAge() > 10) {
                transform("EmptyCell");
            }
            setAge(getAge() + 1); // Symbiotes only age when there are no neighbouring mycoplasma.
        }

        // Allow symbiotes to move towards infected cells with a simplified Dijkstra's
        // algorithm.
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
                        transform("EmptyCell");
                        setLocation(emptyCell.getLocation());
                        emptyCell.transform(this);
                        break;
                    }
                }
            }
        }
    }
}