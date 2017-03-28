package meng.spring.jdbc.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import meng.spring.jdbc.dto.User;
import meng.spring.jdbc.utils.JdbcTemplateUtills;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.StringUtils;

/**
 * 使用Map进行保存参数，value=:key(使用冒号进行取值)
 * 
 * @author mengzhang6
 *
 */
public class NamedParameterJdbcTemplateDemo implements UserDao {
	private NamedParameterJdbcTemplate npJdbcTemplate = JdbcTemplateUtills
			.getNamedParameterJdbcTemplate();

	private static final String INSERT_SQL = "INSERT INTO user(name,birthday,weight,createTime) VALUES(:name,:birthday,:weight,NOW())";
	private static final String DELETE_SQL = "DELETE FROM user WHERE id=:id";
	private static final String UPDATE_SQL = "UPDATE user SET name=:name,birthday=:birthday,weight=:weight WHERE id=:id";
	private static final String FINDBYID_SQL = "SELECT id,name,birthday,weight FROM user WHERE id=:id";
	private static final String FIND_SQL = "SELECT id,name,birthday,weight FROM user ";

	public void insert(User user) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", user.getName());
		paramMap.put("birthday", new Date(user.getBirthday().getTime()));
		paramMap.put("weight", user.getWeight());
		npJdbcTemplate.update(INSERT_SQL, paramMap);
	}

	public void delete(int id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		npJdbcTemplate.update(DELETE_SQL, paramMap);
	}

	public void update(User user) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", user.getId());
		paramMap.put("name", user.getName());
		paramMap.put("birthday", new Date(user.getBirthday().getTime()));
		paramMap.put("weight", user.getWeight());
		npJdbcTemplate.update(UPDATE_SQL, paramMap);
	}

	public User findById(int id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		List<User> users = npJdbcTemplate.query(FINDBYID_SQL, paramMap,
				new BeanPropertyRowMapper<User>(User.class));
		if (users == null)
			return null;
		if (users.size() == 1)
			return users.get(0);
		return null;
	}

	public List<User> findByAll() {
		List<User> users = npJdbcTemplate.query(FIND_SQL, new UserRowMapper());
		return users;
	}

	public List<User> findByParams(String id, String name) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("name", name);

		StringBuilder findByParamsSQL = new StringBuilder();
		findByParamsSQL.append(FIND_SQL);
		if (!(StringUtils.isEmpty(id) && StringUtils.isEmpty(name))) {
			findByParamsSQL.append(" WHERE 1=1");
		}
		if (!StringUtils.isEmpty(id)) {
			findByParamsSQL.append(" AND id = :id ");
		}
		if (!StringUtils.isEmpty(name)) {
			findByParamsSQL.append(" AND  name = :name ");
		}
		findByParamsSQL.append(" ORDER  BY createTime DESC");
		List<User> users = npJdbcTemplate.query(findByParamsSQL.toString(),
				paramMap, new UserRowMapper());
		if (users.size() > 0)
			return users;
		return null;
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
