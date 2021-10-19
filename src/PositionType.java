import org.academiadecodigo.simplegraphics.graphics.Color;

public enum PositionType {
    EMPTY(Color.WHITE),
    CURSOR(Color.GREEN),
    FILLED(Color.BLACK);

    private Color positionColor;

    PositionType(Color color){
        this.positionColor = color;
    }

    public Color getPositionColor() {
        return positionColor;
    }
}
