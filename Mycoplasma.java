import javafx.scene.paint.Color;

/**
 * Simplest form of life.
 * Fun Fact: Mycoplasma are one of the simplest forms of life. A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author Lance Eric Castro So K21055616, Leung Yau Hei K23093432
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
     * How the mycoplasma should act.
     */
    public void act() {
        setNextState(false);

        // If a mycoplasma is at least age 5, there will be an increasing chance to be
        // infected.
        if (getAge() >= 5) {
            infectedProb = Math.min(0.8, Math.max(0.5, infectedProb + 0.1));
            if (Randomizer.getRandom().nextDouble() <= infectedProb) {
                transform("InfectedCell");
            }
        }

        /**
         * Chance to create a symbiote in neighboring empty locations.
         * Chance based on number of nearby infected cells.
         */
        Neighbours extendedNeighbours = getField().getLivingNeighbours(getLocation(), 2);
        int infectedNeighbours = extendedNeighbours.getTypeCount("InfectedCell");
        if (infectedNeighbours > 0) {
            Neighbours deadNeighbours = getField().getDeadNeighbours(getLocation(), 1);
            if (deadNeighbours.size() > 0
                    && Randomizer.getRandom().nextDouble() < ((double) infectedNeighbours) / 25) {
                deadNeighbours.getRandomNeighbour().transform("Symbiote");
            }
        }

        /**
         * Remains alive if there are at least 2 neighbouring mycoplasma or at least 3
         * neighbouring symbiotes
         * If there are 4 or more neighbouring mycoplasma, it will die regardless
         * If it remains alive, it increments its age and changes colour
         */
        Neighbours neighbours = getField().getLivingNeighbours(getLocation(), 1);
        if ((neighbours.getTypeCount("Mycoplasma") > 1 || neighbours.getTypeCount("Symbiote") > 2)
                && neighbours.getTypeCount("Mycoplasma") < 4) {
            setNextState(true);
            setAge(getAge() + 1);
            updateColor();
            return;
        }

        // Dies and leaves an empty spot behind
        transform("EmptyCell");
    }

    /**
     * When a mycoplasma ages, it's colour will become darker.
     */
    private void updateColor() {
        int redValue = (int) (255 - 153 * Math.min(1, (double) getAge() / 10));
        int greenValue = (int) (165 - 114 * Math.min(1, (double) getAge() / 10));
        setColor(Color.rgb(redValue, greenValue, 0));
    }
}