public class App {
    public static void main(String[] args) throws Exception {
        String[] commands = new String[]
        {
            "5 5",
            "1 2 N",
            "LMLMLMLMM",
            "3 3 E",
            "MMRMMRMRRM"
        };
        Map m = new Map(commands);
        m.Run();
        
    }
}
