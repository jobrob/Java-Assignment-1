import java.util.concurrent.*;

public class PlayerNames
{

    private ConcurrentMap<String,UpdateQueue> queueTable = new ConcurrentHashMap<String,MessageQueue>();
    
    public void add(String Nickname)
    {
        
        queueTable.put(nickname, new MessageQueue());
        
    }
    public MessageQueue getQueue (String nickname)
    {
        return queueTable.get(nickname);
        
    }
}