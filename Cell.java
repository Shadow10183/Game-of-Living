import javafx.scene.paint.Color;

/**
 * A class representing the shared characteristics of all forms of life
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
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

    protected void morphCell(String cellName) {
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
            case "TestCell":
                newCell = new TestCell(field, location);
                break;
        }
        if (newCell == null) {
            return;
        }
        CellEditor.edit(this, newCell);
    }

    protected void morphCell(Cell cell) {
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

    protected boolean getNextState() {
        return nextAlive;
    }

    /**
     * Changes the color of the cell
     */
    public void setColor(Color col) {
        color = col;
    }

    /**
     * Returns the cell's color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Return the cell's location.
     * 
     * @return The cell's location.
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
     * 
     * @return The cell's field.
     */
    protected Field getField() {
        return field;
    }

    protected void setAge(int age) {
        this.age = age;
    }

    protected int getAge() {
        return age;
    }

    public String getName() {
        return cellName;
    }
}
