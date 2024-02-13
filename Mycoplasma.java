import javafx.scene.paint.Color;

/**
 * Simplest form of life.
 * Fun Fact: Mycoplasma are one of the simplest forms of life. A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
 */

public class Mycoplasma extends Cell {

    /**
     * Create a new Mycoplasma.
     *
     * @param field    The field currently occupied.
     * @param location The location within the field.
     */
    public Mycoplasma(Field field, Location location) {
        super(field, location, Color.ORANGE, "Mycoplasma");
    }

    /**
     * This is how the Mycoplasma decides if it's alive or not
     */
    public void act() {
        Neighbours neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);

        if (isAlive()) {
            if (neighbours.getTypeCount("Mycoplasma") > 1 && neighbours.getTypeCount("Mycoplasma") < 4) {
                setNextState(true);
            } else if (neighbours.size() >= 4) {
                morphCell("TestCell");
            }
        } else if (neighbours.getTypeCount("Mycoplasma") == 3) {
            setNextState(true);
        }
    }
}
