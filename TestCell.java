import javafx.scene.paint.Color;

/**
 * Simplest form of life.
 * Fun Fact: TestCell are one of the simplest forms of life. A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
 */

public class TestCell extends Cell {
    private static final Color DEFAULT_COLOR = Color.GRAY;

    /**
     * Create a new TestCell.
     *
     * @param field    The field currently occupied.
     * @param location The location within the field.
     */
    public TestCell(Field field, Location location) {
        super(field, location, DEFAULT_COLOR, "TestCell");
        setNextState(true);
    }

    /**
     * This is how the TestCell decides if it's alive or not
     */
    public void act() {
    }
}
