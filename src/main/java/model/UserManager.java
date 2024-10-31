package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UserManager {
	// kết nối csdl
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
	
	// truy vấn csdl
	EntityManager em = factory.createEntityManager();
	
	// CRUD
	// Tìm kiếm tất cả bàn ghi trong bảng Users
	public void findAll() {
		// truy vấn jpql, lấy tất cả bản ghi của lớp User
		String jpql = "SELECT o FROM User o";
		
		// TypedQuery<User>: kiểu trả về khi truy vấn vào class User sẽ là kiểu <User>
		// createQuery(): tạo câu truy vấn
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		
		// getResultList(): lấy List đối tượng trả về trong class User 
		List<User> list = query.getResultList();
		
		// forEach: lấy từng đối tượng trong List
		list.forEach(user -> {
			String fullname = user.getFullname();
			boolean admin = user.getAdmin();
			System.out.println(fullname + " " + admin);
		});
	}
}
