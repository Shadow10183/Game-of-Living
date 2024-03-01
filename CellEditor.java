/**
 * This class requires the simulator to be set. Once set, it is used by cells to
 * interact with the simulator.
 *
 * @author Lance Eric Castro So K21055616, Leung Yau Hei K23093432
 */
public abstract class CellEditor {

    private static Simulator sim;

    /**
     * Set the simulator.
     * 
     * @param sim The simulator to be referenced.
     */
    public static void setSimulator(Simulator sim) {
        CellEditor.sim = sim;
    }

    /**
     * Tells the simulator how to edit its cells.
     * 
     * @param original
     * @param replacement
     */
    public static void edit(Cell original, Cell replacement) {
        sim.replace(original, replacement);
    }
}