import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.*;

public class PlayerKeyboardHandler implements KeyboardHandler {

    private Cursor cursor;
    private int colorCounter = 0;

    public PlayerKeyboardHandler(Cursor cursor) {

        this.cursor = cursor;

        //keyboard is deaf
        Keyboard keyboard = new Keyboard(this);

        //create event
        KeyboardEvent left = new KeyboardEvent();
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        left.setKey(KeyboardEvent.KEY_LEFT);

        KeyboardEvent right = new KeyboardEvent();
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKey(KeyboardEvent.KEY_RIGHT);

        KeyboardEvent down = new KeyboardEvent();
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        down.setKey(KeyboardEvent.KEY_DOWN);

        KeyboardEvent up = new KeyboardEvent();
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        up.setKey(KeyboardEvent.KEY_UP);

        KeyboardEvent space = new KeyboardEvent();
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        space.setKey(KeyboardEvent.KEY_SPACE);

        KeyboardEvent clear = new KeyboardEvent();
        clear.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        clear.setKey(KeyboardEvent.KEY_C);

        KeyboardEvent clearAll = new KeyboardEvent();
        clearAll.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        clearAll.setKey(KeyboardEvent.KEY_E);

        KeyboardEvent changeColor = new KeyboardEvent();
        changeColor.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        changeColor.setKey(KeyboardEvent.KEY_Z);

        KeyboardEvent copy = new KeyboardEvent();
        copy.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        copy.setKey(KeyboardEvent.KEY_J);

        KeyboardEvent paste = new KeyboardEvent();
        paste.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        paste.setKey(KeyboardEvent.KEY_K);

        KeyboardEvent save = new KeyboardEvent();
        save.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        save.setKey(KeyboardEvent.KEY_S);

        KeyboardEvent load = new KeyboardEvent();
        load.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        load.setKey(KeyboardEvent.KEY_L);



        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(down);
        keyboard.addEventListener(up);
        keyboard.addEventListener(space);
        keyboard.addEventListener(clear);
        keyboard.addEventListener(clearAll);
        keyboard.addEventListener(changeColor);
        keyboard.addEventListener(copy);
        keyboard.addEventListener(paste);
        keyboard.addEventListener(save);
        keyboard.addEventListener(load);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                cursor.moveRight();
                break;
            case KeyboardEvent.KEY_LEFT:
                cursor.moveLeft();
                break;
            case KeyboardEvent.KEY_DOWN:
                cursor.moveDown();
                break;
            case KeyboardEvent.KEY_UP:
                cursor.moveUp();
                break;
            case KeyboardEvent.KEY_SPACE:
                if(cursor.getPositions()[cursor.getCurrentColumn()][cursor.getCurrentRow()].getType() != PositionType.FILLED) {
                    cursor.getPositions()[cursor.getCurrentColumn()][cursor.getCurrentRow()].setType(PositionType.FILLED);
                    cursor.getField().paintCell(cursor.getCurrentColumn(), cursor.getCurrentRow(), cursor.getRectangle().getColor());
                }
                else {
                    cursor.getPositions()[cursor.getCurrentColumn()][cursor.getCurrentRow()].getCell().delete();
                    cursor.getField().paintCell(cursor.getCurrentColumn(), cursor.getCurrentRow(), cursor.getRectangle().getColor());
                }
                break;
            case KeyboardEvent.KEY_C:
                if(cursor.getPositions()[cursor.getCurrentColumn()][cursor.getCurrentRow()].getCell()!=null) {
                    cursor.getPositions()[cursor.getCurrentColumn()][cursor.getCurrentRow()].setType(PositionType.EMPTY);
                    cursor.getPositions()[cursor.getCurrentColumn()][cursor.getCurrentRow()].getCell().delete();
                }
                break;
            case KeyboardEvent.KEY_E:
                for (int i = 0; i < cursor.getField().getCOLUMNS(); i++) {
                    for (int j = 0; j < cursor.getField().getROWS(); j++) {
                        if(cursor.getPositions()[i][j].getCell()!=null) {
                            cursor.getPositions()[i][j].setType(PositionType.EMPTY);
                            cursor.getPositions()[i][j].getCell().delete();
                        }
                    }
                }
                break;

            case KeyboardEvent.KEY_Z:

                if(colorCounter < cursor.getColorArray().length - 1){
                    colorCounter++;
                }
                else {
                    colorCounter = 0;
                }

                cursor.getRectangle().setColor(cursor.getColorArray()[colorCounter]);

                break;

            case KeyboardEvent.KEY_J:

                //INCOMPLETE
                cursor.getField().copyImage();
                cursor.getField().setPositions(null);

                break;

            case KeyboardEvent.KEY_K:

                //INCOMPLETE
                cursor.getField().setPositions(cursor.getField().getCopiedPositions());
                for (int i = 0; i < cursor.getField().getCOLUMNS(); i++) {
                    for (int j = 0; j < cursor.getField().getROWS(); j++) {
                        if(cursor.getField().getPositions()[i][j].getCell()!=null) {
                            cursor.getField().getPositions()[i][j].getCell().fill();
                        }
                    }
                }
                break;

            case KeyboardEvent.KEY_S:

                try {
                    FileWriter fileWriter = new FileWriter("resources/savefile.txt");
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    for (int i = 0; i < cursor.getField().getCOLUMNS(); i++) {

                        for (int j = 0; j < cursor.getField().getROWS(); j++) {
                            if(cursor.getField().getPositions()[i][j].getType() == PositionType.FILLED){

                                Color color = cursor.getField().getPositions()[i][j].getCell().getColor();

                                if(color == Color.GREEN){
                                    bufferedWriter.write("green\n");
                                } else if(color == Color.RED){
                                    bufferedWriter.write("red\n");
                                } else if(color == Color.BLUE) {
                                    bufferedWriter.write("blue\n");
                                } else if(color == Color.BLACK) {
                                    bufferedWriter.write("black\n");
                                } else if(color == Color.YELLOW) {
                                    bufferedWriter.write("yellow\n");
                                } else if(color == Color.PINK) {
                                    bufferedWriter.write("pink\n");
                                } else if(color == Color.ORANGE) {
                                    bufferedWriter.write("orange\n");
                                } else if(color == Color.MAGENTA) {
                                    bufferedWriter.write("magenta\n");
                                } else if(color == Color.CYAN) {
                                    bufferedWriter.write("cyan\n");
                                }

                            }
                            else {
                                bufferedWriter.write("empty\n");
                            }
                        }
                    }

                    bufferedWriter.flush();
                    bufferedWriter.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case KeyboardEvent.KEY_L:

                try {
                    FileReader fileReader = new FileReader("resources/savefile.txt");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    for (int i = 0; i < cursor.getField().getCOLUMNS(); i++) {

                        for (int j = 0; j < cursor.getField().getROWS(); j++) {

                            String word = bufferedReader.readLine();

                            if(word.contains("green")){
                                cursor.getPositions()[i][j].setType(PositionType.FILLED);
                                cursor.getField().paintCell(i,j, Color.GREEN);
                            } else if(word.contains("red")){
                                cursor.getPositions()[i][j].setType(PositionType.FILLED);
                                cursor.getField().paintCell(i,j, Color.RED);
                            } else if(word.contains("blue")){
                                cursor.getPositions()[i][j].setType(PositionType.FILLED);
                                cursor.getField().paintCell(i,j, Color.BLUE);
                            } else if(word.contains("black")){
                                cursor.getPositions()[i][j].setType(PositionType.FILLED);
                                cursor.getField().paintCell(i,j, Color.BLACK);
                            } else if(word.contains("yellow")){
                                cursor.getPositions()[i][j].setType(PositionType.FILLED);
                                cursor.getField().paintCell(i,j, Color.YELLOW);
                            } else if(word.contains("pink")){
                                cursor.getPositions()[i][j].setType(PositionType.FILLED);
                                cursor.getField().paintCell(i,j, Color.PINK);
                            } else if(word.contains("orange")){
                                cursor.getPositions()[i][j].setType(PositionType.FILLED);
                                cursor.getField().paintCell(i,j, Color.ORANGE);
                            } else if(word.contains("magenta")){
                                cursor.getPositions()[i][j].setType(PositionType.FILLED);
                                cursor.getField().paintCell(i,j, Color.MAGENTA);
                            } else if(word.contains("cyan")){
                                cursor.getPositions()[i][j].setType(PositionType.FILLED);
                                cursor.getField().paintCell(i,j, Color.CYAN);
                            } else {
                                if (cursor.getPositions()[i][j].getCell() != null) {
                                    cursor.getPositions()[i][j].getCell().delete();
                                }

                            }
                        }
                    }

                    bufferedReader.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

}
