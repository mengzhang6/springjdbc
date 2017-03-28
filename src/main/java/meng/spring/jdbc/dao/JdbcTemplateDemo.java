package meng.spring.jdbc.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import meng.spring.jdbc.dto.User;
import meng.spring.jdbc.utils.JdbcTemplateUtills;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * JdbcTemplate使用?占位符来替代参数，传参时顺序不能乱
 * 
 * @author mengzhang6
 *
 */
public class JdbcTemplateDemo implements UserDao {

	private JdbcTemplate jdbcTemplate = JdbcTemplateUtills.getJdbcTemplate();
	private static final String INSERT_SQL = "insert into user(name,birthday,weight,createTime) values(?,?,?,now())";
	private static final String DELETE_SQL = "DELETE FROM user WHERE id=?";
	private static final String UPDATE_SQL = "UPDATE user SET name=?,birthday=?,weight=? WHERE id=?";
	private static final String FINDBYID_SQL = "SELECT id,name,birthday,weight FROM user WHERE id=?";
	private static final String FINDALL_SQL = "SELECT id,name,birthday,weight FROM user ";

	public void insert(User user) {
		jdbcTemplate.update(INSERT_SQL, new Object[] { user.getName(),
				new Date(user.getBirthday().getTime()), user.getWeight() });
	}

	public void delete(int id) {
		jdbcTemplate.update(DELETE_SQL, new Object[] { id });
	}

	public void update(User user) {
		jdbcTemplate.update(
				UPDATE_SQL,
				new Object[] { user.getName(),
						new Date(user.getBirthday().getTime()),
						user.getWeight(), user.getId() });
	}

	public User findById(int id) {
		List<User> users = jdbcTemplate.query(FINDBYID_SQL,
				new Object[] { id },
				new BeanPropertyRowMapper<User>(User.class));
		if (users == null)
			return null;
		if (users.size() == 1)
			return users.get(0);
		return null;
	}

	public List<User> findByAll() {
		List<User> users = jdbcTemplate.query(FINDALL_SQL, new UserRowMapper());
		return users;
	}

	/**
	 * <pre>
	 * 将结果处理成User对象
	 * </pre>
	 */
	class UserRowMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setWeight(rs.getFloat("weight"));
			user.setBirthday(rs.getDate("birthday"));
			return user;
		}
	}

}
