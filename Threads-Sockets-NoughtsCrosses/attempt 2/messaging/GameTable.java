// Each nickname has a different incomming-message queue.

import java.util.concurrent.*;

public class GameTable 
{

  private ConcurrentMap<String,NoughtsCrosses> oxoTable  = new ConcurrentHashMap<String,NoughtsCrosses>();

  // The following overrides any previously existing nickname, and
  // hence the last client to use this nickname will get the messages
  // for that nickname, and the previously exisiting clients with that
  // nickname won't be able to get messages. Obviously, this is not a
  // good design of a messaging system. So I don't get full marks:

  public void add(String nickname)
  {
    oxoTable.put(nickname, new NoughtsCrosses());
  }

  // Returns null if the nickname is not in the table:
  public NoughtsCrosses getGame(String nickname) 
  {
    return oxoTable.get(nickname);
  }
  public void replace (String key, NoughtsCrosses oxo)
  {
      oxoTable.replace(key,oxo);
  }

}
