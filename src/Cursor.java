import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cursor {

    private int currentColumn;
    private int currentRow;
    private Field field;
    private Position[][] positions;
    private PlayerKeyboardHandler pkh;
    private Rectangle rectangle;
    private Color[] colorArray;

    public Cursor(Field field){
        this.currentColumn = 1;
        this.currentRow = 1;
        this.field = field;
        this.positions = field.getPositions();
        positions[currentColumn][currentRow].setType(PositionType.CURSOR);
        pkh = new PlayerKeyboardHandler(this);
        rectangle = new Rectangle(field.colToX(currentColumn), field.rowToY(currentRow), field.getCELLSIZE()+1, field.getCELLSIZE()+1);
        colorArray = new Color[] {
            Color.GREEN,
            Color.RED,
            Color.BLUE,
            Color.BLACK,
            Color.YELLOW,
            Color.PINK,
            Color.ORANGE,
            Color.MAGENTA,
            Color.CYAN
        };
        rectangle.setColor(colorArray[0]);
        rectangle.fill();

    }

    public void move(){

    }

    public void moveRight(){

        if(currentColumn == field.getCOLUMNS()-1) return;

        rectangle.translate(field.getCELLSIZE(), 0);
        currentColumn++;
    }

    public void moveLeft(){

        if(currentColumn == 0) return;

        rectangle.translate(-field.getCELLSIZE(), 0);
        currentColumn--;

    }

    public void moveUp(){

        if(currentRow == 0) return;

        rectangle.translate(0, -field.getCELLSIZE());
        currentRow--;

    }

    public void moveDown(){

        if(currentRow == field.getROWS()-1) return;

        rectangle.translate(0, field.getCELLSIZE());
        currentRow++;

    }

    public Position[][] getPositions() {
        return positions;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public Field getField() {
        return field;
    }

    public Color[] getColorArray() {
        return colorArray;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
