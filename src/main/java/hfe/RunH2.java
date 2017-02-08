package hfe;

public class RunH2 {

    public static void main(String[] args) {
        try {
            Standalone.startH2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
