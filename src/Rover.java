import java.util.HashMap;
public class Rover
{
    
    public DIRECTION FacingDirection;
    public int Row;
    public int Col;
    private int RowMax;
    private int ColMax;
    private String Commands;
    private int CommandIndex;
    private HashMap<DIRECTION, DIRECTION> TurnLeftDic;
    private HashMap<DIRECTION, DIRECTION> TurnRightDic;
    // init robot position and facing direct, also let it know the boundary so it
    // won't get out of the grid.
    public Rover(int row, int col, DIRECTION d, int rowMax, int colMax, String commands)
    {
        TurnLeftDic = new HashMap<DIRECTION, DIRECTION>();
        TurnLeftDic.put(DIRECTION.N, DIRECTION.W);
        TurnLeftDic.put(DIRECTION.W, DIRECTION.S);
        TurnLeftDic.put(DIRECTION.S, DIRECTION.E);
        TurnLeftDic.put(DIRECTION.E, DIRECTION.N);
        TurnRightDic = new HashMap<DIRECTION, DIRECTION>();
        TurnRightDic.put(DIRECTION.N, DIRECTION.E );
        TurnRightDic.put(DIRECTION.E, DIRECTION.S);
        TurnRightDic.put(DIRECTION.S, DIRECTION.W);
        TurnRightDic.put(DIRECTION.W, DIRECTION.N);
        FacingDirection = d;
        RowMax = rowMax;
        ColMax = colMax;
        Row = row;
        Col = col;
        Commands = commands;
    }

    public void ExecuteCommands(int[][] grid)
    {
        try
        {
            grid[Row][Col] = 0;
            for (int i = CommandIndex; i < Commands.length(); i++)
            {
                if (Commands.charAt(i) == 'L')
                {
                    TurnLeft();
                }
                else if (Commands.charAt(i)== 'R')
                {
                    TurnRight();
                }
                else if (Commands.charAt(i) == 'M')
                {
                    MoveForward(grid);
                }
                CommandIndex = i;

            }
            // rover has finished its moves, update the grid with the robot coordinate
            grid[Row][Col] = 1;
        }
        catch (Exception e)
        {
            // if machine got into mechanic problem not able to execute command, try again with latest commandindex
            ExecuteCommands(grid);
        }
    }
    private void TurnLeft()
    {
        FacingDirection = TurnLeftDic.get(FacingDirection);
    }

    private void TurnRight()
    {
        FacingDirection = TurnRightDic.get(FacingDirection);
    }

    private void MoveForward(int[][] grid)
    {
        int row = Row, col = Col;
        if (FacingDirection == DIRECTION.N)
        {
            row++;
        }
        else if (FacingDirection == DIRECTION.S)
        {
            row--;
        }
        else if (FacingDirection == DIRECTION.E)
        {
            col++;
        }
        else if (FacingDirection == DIRECTION.W)
        {
            col--;
        }
        // only assign the new positions if it is whitn boundary
        if (IsGoZone(row, col, grid))
        {
            Row = row;
            Col = col;
        }
    }

    private boolean IsGoZone(int row, int col, int[][] grid)
    {
        if (row < 0 || col < 0 || row >= RowMax || col >= ColMax || grid[row][col] == 1)
        {
            return false;
        }
        return true;
    }
}