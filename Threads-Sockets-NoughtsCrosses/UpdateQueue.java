import java.util.concurrent.*;

public class UpdateQueue
{
    private BlockingQueue<Update> queue = new  LinkedBlockingQueue<Update>();
    
    public void offer(Update update)
    {
        queue.offer(update);
    }
    
    public Update take()
    {
    
        while (true)
        {
            try
            {
                return(queue.take());
            }
           catch(InterruptedExpection e)
            {
            
            
            }
        }
    }

}