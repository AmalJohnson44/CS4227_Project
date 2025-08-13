
package com.smarthealth.user.repo;
import com.smarthealth.user.domain.User;
import org.springframework.stereotype.Repository;
import java.util.*; import java.util.concurrent.ConcurrentHashMap;
@Repository public class UserRepo{
  private final Map<Integer,User> store = new ConcurrentHashMap<>();
  public UserRepo(){ store.put(1,new User(1,"Patient One","patient1","pass")); }
  public Optional<User> find(int id){ return Optional.ofNullable(store.get(id)); }
  public void save(User u){ store.put(u.getId(), u); }
}
