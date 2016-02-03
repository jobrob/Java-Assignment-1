import java.util.concurrent.*;

public class PlayerTable
{
    private ConcurrentMap<String,UpdateQueue> queueTable = new ConcurrentHashMap<String,UpdateQueue>();
    
    public void add(String nickname)
    {
        queueTable.put.nickname,new UpdateQueue;
    }
    
    public UpdateQueue getQueue(String nickname)
    {
        return queueTable.get(nickname);
    }

}