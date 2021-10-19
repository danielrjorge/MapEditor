import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Field {

    private final int PADDING;
    private final int WIDTH;
    private final int HEIGHT;
    private final int COLUMNS;
    private final int ROWS;
    private final int CELLSIZE;
    private Position[][] positions;
    private Position[][] copiedPositions;

    public Field(int columns, int rows, int cellsize){
        this.COLUMNS = columns;
        this.ROWS = rows;
        this.PADDING = 10;
        this.WIDTH = colToX(columns);
        this.HEIGHT = rowToY(rows);
        this.CELLSIZE = cellsize;
        this.positions = new Position[columns][rows];
    }

    public void init(){
        createField();
        drawBorders();
    }

    public void createField(){

        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                positions[i][j] = new Position();
                positions[i][j].setType(PositionType.EMPTY);
            }
        }

    }

    public void drawBorders(){
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                Rectangle rectangle = new Rectangle(colToX(i),rowToY(j),CELLSIZE,CELLSIZE);
                rectangle.draw();
            }
        }
    }

    public void paintCell(int col, int row, Color color){
        positions[col][row].setCell(new Rectangle(colToX(col)+1,rowToY(row)+1, CELLSIZE-1, CELLSIZE-1));
        positions[col][row].getCell().setColor(color);
        positions[col][row].getCell().fill();
    }

    public int colToX(int col){
        return col*CELLSIZE + PADDING;
    }

    public int rowToY(int row){
        return row*CELLSIZE + PADDING;
    }

    public Position[][] getPositions() {
        return positions;
    }

    public int getCOLUMNS() {
        return COLUMNS;
    }

    public int getROWS() {
        return ROWS;
    }

    public int getCELLSIZE() {
        return CELLSIZE;
    }

    public void copyImage(){
        copiedPositions = positions;
    }

    public void pasteImage(){
        positions = copiedPositions;
    }

    public Position[][] getCopiedPositions() {
        return copiedPositions;
    }

    public void setPositions(Position[][] positions) {
        this.positions = positions;
    }
}

