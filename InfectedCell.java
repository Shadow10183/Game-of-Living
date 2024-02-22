import javafx.scene.paint.Color;

/**
 * Simplest form of life.
 * Fun Fact: Infected cells are one of the simplest forms of life. A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
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
     * This is how the InfectedCell decides if it's alive or not
     */
    public void act() {
        boolean cured = false;
        setAge(getAge() + 1);
        Neighbours neighbours = getField().getLivingNeighbours(getLocation(), 1);
        if (neighbours.getTypeCount("Symbiote") > 0) {
            morphCell("Mycoplasma");
            cured = true;
        }
        if (getAge() < 5) {
            return;
        }
        infectionProb = Math.min(0.9, infectionProb + 0.1 * (getAge() - 5) / 2);
        for (int i = 0; i < neighbours.size(); i++) {
            Cell cell = neighbours.getNeighbours().get(i);
            if (cell.getName() == "Mycoplasma") {
                if (Randomizer.getRandom().nextDouble() <= infectionProb) {
                    cell.morphCell("InfectedCell");
                }
            }
        }
        if (getAge() > 10 && !cured) {
            Neighbours deadNeighbours = getField().getDeadNeighbours(getLocation(), 1);
            if (deadNeighbours.size() > 0) {
                for (Cell cell : deadNeighbours.getNeighbours()) {
                    EmptyCell emptyCell = (EmptyCell) cell;
                    if (emptyCell.occupy()) {
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
