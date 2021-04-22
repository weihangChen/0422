import java.util.ArrayList;


public class Map {
    public ArrayList<Rover> Rovers;
        public int[][] Grid;

        public Map(String[] commands) throws Exception
        {
            if (commands == null || commands.length == 0)
                throw new Exception("invalid input");
            Rovers = new ArrayList<Rover>();
            // first line in commands set up grid
            var dimentions = commands[0].trim().split("\\s+");
            var row = Integer.parseInt(dimentions[1]);
            var col = Integer.parseInt(dimentions[0]);
            Grid = new int[row][];
            for (int j = 0; j < row; j++)
            {
                Grid[j] = new int[col];
            }
            // set up robots
            for (int i = 1; i < commands.length; i++)
            {
                if (i < commands.length && (i + 1) < commands.length)
                {
                    // create robot and set and command for the robot
                    String[] robotParams = commands[i].split("\\s+");
                    String robotCommand = commands[i + 1];
                    DIRECTION d = DIRECTION.valueOf(robotParams[2]);
                    Rover rover = new Rover(Integer.parseInt(robotParams[1]), Integer.parseInt(robotParams[0]), d, row, col, robotCommand);
                    Rovers.add(rover);
                    i++;
                }
            }
            // mark the robot position in grid as 1 so a robot should not hit on another robot
            for (Rover rover : Rovers) {
                Grid[rover.Row][rover.Col] = 1;
                
                
            }

           

        }
        public void Run()
        {
            for (Rover rover : Rovers) {
                rover.ExecuteCommands(Grid);   
                System.out.println(rover.Col + " "+ rover.Row + " "+ rover.FacingDirection );
            }
        }
}
