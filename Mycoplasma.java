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
    private static final Color DEFAULT_COLOR = Color.ORANGE;
    private double infectedProb = 0;

    /**
     * Create a new Mycoplasma.
     *
     * @param field    The field currently occupied.
     * @param location The location within the field.
     */
    public Mycoplasma(Field field, Location location) {
        super(field, location, DEFAULT_COLOR, "Mycoplasma");
    }

    /**
     * This is how the Mycoplasma decides if it's alive or not
     */
    public void act() {
        Neighbours neighbours = getField().getLivingNeighbours(getLocation(), 1);
        setNextState(false);

        if (getAge() >= 5) {
            infectedProb = Math.min(0.8, Math.max(0.5, infectedProb + 0.1));
            if (Randomizer.getRandom().nextDouble() <= infectedProb) {
                morphCell("InfectedCell");
            }
        }
        Neighbours extendedNeighbours = getField().getLivingNeighbours(getLocation(), 2);
        int infectedNeighbours = extendedNeighbours.getTypeCount("InfectedCell");
        if (infectedNeighbours > 0) {
            Neighbours deadNeighbours = getField().getDeadNeighbours(getLocation(), 1);
            if (deadNeighbours.size() > 0
                    && Randomizer.getRandom().nextDouble() < ((double) infectedNeighbours) / 25) {
                deadNeighbours.getRandomNeighbour().morphCell("Symbiote");
            }
        }
        if ((neighbours.getTypeCount("Mycoplasma") > 1 || neighbours.getTypeCount("Symbiote") > 1)
                && neighbours.getTypeCount("Mycoplasma") < 4) {
            setNextState(true);
            setAge(getAge() + 1);
            updateColor();
            return;
        }

        morphCell("EmptyCell");
    }

    private void updateColor() {
        int redValue = (int) (255 - 153 * Math.min(1, (double) getAge() / 10));
        int greenValue = (int) (165 - 114 * Math.min(1, (double) getAge() / 10));
        setColor(Color.rgb(redValue, greenValue, 0));
    }
}
