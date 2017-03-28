package meng.spring.jdbc.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import meng.spring.jdbc.dao.NamedParameterJdbcTemplateDemo;
import meng.spring.jdbc.dto.User;

public class NamedParameterJdbcTemplateDemoTest extends TestCase {
	NamedParameterJdbcTemplateDemo jdbcTemplateDemo = new NamedParameterJdbcTemplateDemo();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	public void testInsert() throws ParseException {
		User user = new User();
		user.setName("李笑笑");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		user.setBirthday(sdf.parse("1993-04-05"));
		user.setWeight(47.8F);
		jdbcTemplateDemo.insert(user);
	}

	@Test
	public void testDelete() {
		jdbcTemplateDemo.delete(121);
	}

	@Test
	public void testUpdate() throws ParseException {
		User user = new User();
		user.setId(110);
		user.setName("李笑笑");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		user.setBirthday(sdf.parse("1993-04-05"));
		user.setWeight(47.8F);
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

	@Test
	public void testFindByParams() {
		List<User> list = jdbcTemplateDemo.findByParams(null, null);
		for (User u : list) {
			System.out.println(u.toString());
		}

	}

}
