package meng.spring.jdbc.dao;

import java.util.List;

import meng.spring.jdbc.dto.User;

public interface UserDao {
	public void insert(User user);

	public void delete(int id);

	public void update(User user);

	public User findById(int id);

	public List<User> findByAll();

}
