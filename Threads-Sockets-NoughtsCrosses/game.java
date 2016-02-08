import java.util.*;



public class game
{
    public game()
    {
        NoughtsCrosses oxo = new NoughtsCrosses;
        int turncounter = 0;
    }
 
      
    
    public void play (int x, int y)
      
        while (turncounter !=10)
        {
             
             oxo.turn(x,y);
             System.out.println(oxo.get(1,1));
             //System.out.println("at the start");
            for(int i = 0; i <3; i++)
            {
                for (int j =0;j <3;j ++)
                {
                    //System.out.println("looping");
                    //System.out.println(oxo.get(i,j));
                    if (oxo.get(i,j) == NoughtsCrosses.BLANK)
                    {
                        System.out.print(" (" + i + ","+ j+ ") ");
                        //System.out.println("Checking for blanks");
                
                    }
                    else if (oxo.get(i,j)==NoughtsCrosses.CROSS)
                    {
                        System.out.print(" (" + i + ","+ j+ ") + ");
                    }
                    else if (oxo.get(i,j)== NoughtsCrosses.NOUGHT)
                    {
                        System.out.print(" (" + i + ","+ j+ ")- ");
                    }
                    System.out.println("");
                }
            turncounter = turncounter + 1;
            }
        }
}