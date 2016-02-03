// Update class used to send new board positions to the openent
public class Update
{
   // private String sender; 
    private int x;
    private int y;
    private int value;
    
    Update(int x, int y, int value)
    {
        //this.sender = sender;
        this.x = x;
        this.y = y;
        this.value = value;
        
    
    }
    
   // public String getSender()
   // {
    //    return sender;
   // }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getValue()
    {
        return value;
    }


}