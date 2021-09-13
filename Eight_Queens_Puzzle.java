/*
Finds solutions to the 8 queens problem using recursive backtracking search.
The Depth First Search (DFS) algorithm is defined in the Puzzle interface.
The Queen8 class implements the Puzzle interface.
*/
//==============================================================================
// Recursive Backtracking puzzle solving.
//
class Eight_Queens_Puzzle
{
    public static void main (String[] args) throws java.lang.Exception
    {
        Puzzle.solve( new Queen8() );

    }
}
//==============================================================================
// Interface the Puzzle representation must implement
//
interface Puzzle
{
    int action_count();
    boolean action(int move_number);
    void undo_last_action();
    boolean goal();
    void display_solution();
    
    static void solve(Puzzle P)
    {
        if(P.goal())
            P.display_solution();         
        else  
            for (int a = 0; a < P.action_count(); a++)
                if(P.action(a))
                {
                    solve(P);
                    P.undo_last_action();
                }
    }
}
//==============================================================================
// Implementation of the 8 queens problem
//
class Queen8 implements Puzzle
{
    //------------------------------------------------------------------------
    // Representation of the problem state
    //
    private int Q[] = new int[8];   // Q[row] is the column of the queen
    private int queen_count = 0;
    private int solution_count = 0;
    
    //------------------------------------------------------------------------
    // Interface implementation
    //
    public int action_count(){ return 8; }   
    public void undo_last_action(){ --queen_count; }
    public boolean goal(){ return queen_count == 8; }
    
    //------------------------------------------------------------------------
    // Helper method for the action method.
    // Returns true if a new queen can be placed in column col.
    //
    private boolean safe(int col)
    {
         for (int row = 0; row  < queen_count; row++)
             if (Q[row] == col || Math.abs(Q[row] - col) == queen_count - row)
                 return false;
          return true;    
    } 
    //------------------------------------------------------------------------
    public boolean action(int col)
    { 
        if (safe(col))
        {
            Q[queen_count++] = col; // place a new queen
            return true;
        }
        else
            return false;
    }
    //------------------------------------------------------------------------
    public void display_solution()
    {
        System.out.println("Solution " + (++solution_count)); 
        for (int row = 0; row < 8; row++)
         {
             for  (int col = 0; col < 8; col++)
                 if (Q[row] == col) 
                     System.out.print(" Q"); 
                 else 
                     System.out.print(" -");
             System.out.println(); 
         } 
         System.out.println("\n"); 
         System.out.println("Press Enter To Continue...");     
         new java.util.Scanner(System.in).nextLine(); 
    }
}