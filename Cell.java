import javafx.scene.paint.Color;

/**
 * A class representing the shared characteristics of all forms of life.
 *
 * @author Lance Eric Castro So K21055616, Leung Yau Hei K23093432
 */

public abstract class Cell {

    private boolean nextAlive; // The state of the cell in the next iteration
    private Field field;
    private Location location;
    private Color color = Color.WHITE;
    private String cellName;
    private int age;

    /**
     * Create a new cell at location in field.
     *
     * @param field    The field currently occupied.
     * @param location The location within the field.
     */
    public Cell(Field field, Location location, Color col, String cellName) {
        nextAlive = true;
        this.field = field;
        this.cellName = cellName;
        age = 0;
        setLocation(location);
        setColor(col);
    }

    /**
     * Transforms itself into a new life form.
     * 
     * @param cellName The type of cell to change to.
     */
    protected void transform(String cellName) {
        Cell newCell = null;
        switch (cellName) {
            case "Mycoplasma":
                newCell = new Mycoplasma(field, location);
                break;
            case "InfectedCell":
                newCell = new InfectedCell(field, location);
                break;
            case "Symbiote":
                newCell = new Symbiote(field, location);
                break;
            case "EmptyCell":
                newCell = new EmptyCell(field, location);
                break;
        }
        if (newCell == null) {
            return;
        }
        CellEditor.edit(this, newCell);
    }

    /**
     * Transforms itself into a pre-existing life form.
     * 
     * @param cell The cell to change to.
     */
    protected void transform(Cell cell) {
        CellEditor.edit(this, cell);
    }

    /**
     * Make this cell act - that is: the cell decides it's status in the
     * next generation.
     */
    abstract public void act();

    /**
     * Indicate that the cell will be alive or dead in the next generation.
     */
    public void setNextState(boolean value) {
        nextAlive = value;
    }

    /**
     * Return the next state of a cell.
     */
    protected boolean getNextState() {
        return nextAlive;
    }

    /**
     * Changes the color of a cell.
     */
    public void setColor(Color col) {
        color = col;
    }

    /**
     * Return the cell's color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Return the cell's location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Place the cell at the new location in the given field.
     * 
     * @param location The cell's location.
     */
    protected void setLocation(Location location) {
        this.location = location;
        field.place(this, location);
    }

    /**
     * Return the cell's field.
     */
    protected Field getField() {
        return field;
    }

    /**
     * Set the age of a cell.
     */
    protected void setAge(int age) {
        this.age = age;
    }

    /**
     * Return the age of a cell.
     */
    protected int getAge() {
        return age;
    }

    /**
     * Return the name of a cell.
     */
    public String getName() {
        return cellName;
    }
}