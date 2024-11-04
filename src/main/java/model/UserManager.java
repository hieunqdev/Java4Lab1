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
			String id =  user.getId();
			System.out.println(id + " " + fullname + " " + admin);
		});
	}
	
	//	Tìm kiếm User theo id
	public void findById() {
//		find(): dùng để tìm kiếm một đối tượng trong cơ sở dữ liệu 
//		dựa trên khóa chính (primary key). 
		User user = em.find(User.class, "user2");
		String fullname = user.getFullname();
		boolean admin = user.getAdmin();
		System.out.println(fullname + ": " + admin);
	}
	
	// Tạo bản ghi User
	public void create() {
		User user = new User("U01", "123", "teo@gmail.com", "Tèo", false);
		try {
			// getTransaction(): truy vấn JPA cần thực hiện trong getTransaction
			// begin(): mở CSDL
			em.getTransaction().begin();
			// persist(): tạo bản ghi mới trong csdl
			em.persist(user);
			// commit(): đóng csdl
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}
	
	public void update() {
		User user = em.find(User.class, "U01");
		user.setFullname("Nguyễn Văn Tèo"); 
		user.setEmail("teonv@gmail.com");
		try {
			em.getTransaction().begin(); 
			// merge(): update bản ghi
			em.merge(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteById() {
		User user = em.find(User.class, "U01");
		try {
			em.getTransaction().begin();
			// remove(): xóa bản ghi
			em.remove(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}
}
