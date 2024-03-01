import java.util.HashMap;
import java.util.Random;

/**
 * A Life (Game of Life) simulator, first described by British mathematician
 * John Horton Conway in 1970.
 * 
 * This class is responsible for the simulation concepts: simulating
 * generations, starting/reseting the simulation, and creating the
 * mycoplasmas.
 *
 * @author Lance Eric Castro So K21055616, Leung Yau Hei K23093432
 */

public class Simulator {
    
    private static final double MYCOPLASMA_ALIVE_PROB = 0.25;
    private HashMap<Location, Cell> cells;
    private HashMap<Location, Cell> nextCells;
    private Field field;
    private int generation;

    /**
    * Construct a simulation field with default size.
    */
    public Simulator() {
        this(SimulatorView.GRID_HEIGHT, SimulatorView.GRID_WIDTH);
    }

    /**
    * Create a simulation field with the given size.
    * 
    * @param depth Depth of the field. Must be greater than zero.
    * @param width Width of the field. Must be greater than zero.
    */
    public Simulator(int depth, int width) {
        cells = new HashMap<>();
        field = new Field(depth, width);
        reset();
    }

    /**
    * Run the simulation from its current state for a single generation.
    * Iterate over the whole field updating the state of each life form.
    */
    public void simOneGeneration() {
        for (Location loc : cells.keySet()) {
            cells.get(loc).act();
        }
        for (Location loc : nextCells.keySet()) {
            cells.put(loc, nextCells.get(loc));
        }
        field.updateCells();
        generation++;
    }
    
    /**
    * Reset the simulation to a starting position.
    */
    public void reset() {
        generation = 0;
        cells.clear();
        populate();
    }

    /**
    * Randomly populate the field live/dead life forms.
    */
    private void populate() {
        Random rand = Randomizer.getRandom();
        field.clear();
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Location location = new Location(row, col);
                if (rand.nextDouble() <= MYCOPLASMA_ALIVE_PROB) {
                    cells.put(location, new Mycoplasma(field, location));
                } else {
                    cells.put(location, new EmptyCell(field, location));
                }
            }
        }
        field.updateCells();
        nextCells = new HashMap<>(cells);
    }

    /**
    * Pause for a given time.
    * 
    * @param millisec The time to pause for, in milliseconds.
    */
    public void delay(int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException ie) {
          // wake up
        }
    }
    
    /**
    * Change the location of a cell to allow some life forms (symbiotes
    * or infected cells) to move around the grid.
    */
    public void replace(Cell original, Cell replacement) {
        nextCells.put(original.getLocation(), replacement);
    }

    /**
    * Return the field.
    */
    public Field getField() {
        return field;
    }

    /**
    * Return the generation number.
    */
    public int getGeneration() {
        return generation;
    }
}