package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Task;
import dto.myUser;

public class UserDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public void save(myUser user1) {
		transaction.begin();
		manager.persist(user1);
		transaction.commit();
	}

	public myUser fetchByEmail(String email) {
		List<myUser> list = manager.createQuery("select x from myUser x where email=?1").setParameter(1, email)
				// .createNativeQuery("select* from myUser where email=".myUser.class)
				.getResultList();
		if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	public void save(Task t) {
		transaction.begin();
		manager.persist(t);
		transaction.commit();
	}

	public List<Task> fetchAllTask() {
		return manager.createQuery("select x form Task x").getResultList();
	}
	public void update(myUser user) 
	{
		transaction.begin();
		manager.merge(user);
		transaction.commit();
	}
	public Task fetchTask(int id) {
		return manager.find(Task.class,id);
	}
	public void update(Task t) {
		transaction.begin();
		manager.merge(t);
		transaction.commit();
	}
	
	public void remove(Task t) {
		transaction.begin();
		manager.remove(t);
		transaction.commit();
	}
//	
	
	
	
	
	
}
	
//	public boolean save(myUser user1) {
//		transaction.begin();
//		try {
//			manager.persist(user1);
//			transaction.commit();
//			return false;
//		}
//		catch (Exception e) {
//			transaction.commit();
//			return true;
//		}
//}


