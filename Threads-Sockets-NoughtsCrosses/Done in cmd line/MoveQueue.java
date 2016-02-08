import java.util.concurrent.*;

public class MoveQueue
{
    private BlockingQueue<Move> queue = new  LinkedBlockingQueue<Move>();
    
    public void offer(Move move)
    {
        queue.offer(move);
    }
    
    public Move take()
    {
    
        while (true)
        {
            try
            {
                return(queue.take());
            }
           catch(InterruptedException e)
            {
            
            
            }
        }
    }

}