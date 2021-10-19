public class Main {

    public static void main(String[] args) throws InterruptedException {
        Field field = new Field(30, 30, 20);
        field.init();
        Cursor cursor = new Cursor(field);

        while(true){

            Thread.sleep(30);

        }
    }
}
