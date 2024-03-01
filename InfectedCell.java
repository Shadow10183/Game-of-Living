import javafx.scene.paint.Color;

/**
 * Infected cells are diseased mycoplasma.
 *
 * @author Lance Eric Castro So K21055616, Leung Yau Hei K23093432
 */

public class InfectedCell extends Cell {

    private static final Color DEFAULT_COLOR = Color.LIMEGREEN;
    private double infectionProb = 0.6;

    /**
     * Create a new InfectedCell.
     *
     * @param field    The field currently occupied.
     * @param location The location within the field.
     */
    public InfectedCell(Field field, Location location) {
        super(field, location, DEFAULT_COLOR, "InfectedCell");
        setNextState(true);
    }

    /**
     * How the infected cell should act.
     */
    public void act() {
        // If the infected cell is neighbouring a symbiote, it will be cured into a
        // mycoplsma.
        boolean cured = false;
        setAge(getAge() + 1);
        Neighbours neighbours = getField().getLivingNeighbours(getLocation(), 1);
        if (neighbours.getTypeCount("Symbiote") > 0) {
            transform("Mycoplasma");
            cured = true;
        }

        // Infected cells remain dormant for 5 generations.
        if (getAge() < 5) {
            return;
        }

        // Has a chance to infect any neighbouring mycoplasma. Probability based on its
        // own age.
        infectionProb = Math.min(0.9, infectionProb + 0.1 * (getAge() - 5) / 2);
        for (int i = 0; i < neighbours.size(); i++) {
            Cell cell = neighbours.getNeighbours().get(i);
            if (cell.getName() == "Mycoplasma") {
                if (Randomizer.getRandom().nextDouble() <= infectionProb) {
                    cell.transform("InfectedCell");
                }
            }
        }

        // When an infected cell lives past 10 generations, it will be able to move
        if (getAge() > 10 && !cured) {
            Neighbours deadNeighbours = getField().getDeadNeighbours(getLocation(), 1);
            if (deadNeighbours.size() > 0) {
                for (Cell cell : deadNeighbours.getNeighbours()) {
                    EmptyCell emptyCell = (EmptyCell) cell;
                    if (emptyCell.occupy()) {
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