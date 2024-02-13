
/**
 * Write a description of class CellEditor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class CellEditor {
    // instance variables - replace the example below with your own
    private static Simulator sim;

    public static void setSimulator(Simulator sim) {
        CellEditor.sim = sim;
    }

    public static void edit(Cell original, Cell replacement) {
        sim.replace(original, replacement);
    }
}
