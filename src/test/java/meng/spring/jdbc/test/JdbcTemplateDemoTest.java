package meng.spring.jdbc.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import meng.spring.jdbc.dao.JdbcTemplateDemo;
import meng.spring.jdbc.dto.User;
import junit.framework.TestCase;

public class JdbcTemplateDemoTest extends TestCase {

	private JdbcTemplateDemo jdbcTemplateDemo = new JdbcTemplateDemo();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	public void testInsert() throws ParseException {
		User user = new User();
		user.setName("王晓敏");
		user.setBirthday(sdf.parse("1993-04-05"));
		user.setWeight(47.8F);
		jdbcTemplateDemo.insert(user);
	}

	@Test
	public void testDelete() {
		jdbcTemplateDemo.delete(2);
	}

	@Test
	public void testUpdate() throws ParseException {
		User user = new User();
		user.setId(102);
		user.setName("李哈哈");
		user.setBirthday(sdf.parse("1992-04-05"));
		user.setWeight(49.8F);
		jdbcTemplateDemo.update(user);
	}

	@Test
	public void testFindById() {
		User user = jdbcTemplateDemo.findById(102);
		System.out.println(user.toString());
	}

	@Test
	public void testFindByAll() {
		List<User> list = jdbcTemplateDemo.findByAll();
		for (User u : list) {
			System.out.println(u.toString());
		}
	}

}
