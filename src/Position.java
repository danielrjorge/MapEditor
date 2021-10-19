import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Position {

    private PositionType type;
    private Rectangle cell;

    public Position(){
    }

    public void setType(PositionType type) {
        this.type = type;
    }

    public PositionType getType() {
        return type;
    }

    public Rectangle getCell() {
        return cell;
    }

    public void setCell(Rectangle cell) {
        this.cell = cell;
    }
}
