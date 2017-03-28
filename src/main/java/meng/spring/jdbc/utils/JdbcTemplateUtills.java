package meng.spring.jdbc.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcTemplateUtills {

	/**
	 * 单例(懒汉模式)
	 */
	private static JdbcTemplate jdbcTemplate;
	private static NamedParameterJdbcTemplate npJdbcTemplate;
	private static DriverManagerDataSource dataSource = new DriverManagerDataSource();
	static {
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3307/meng?useUnicode=true&amp;characterEncoding=utf-8");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
	}

	public static JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}

	public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		if (npJdbcTemplate == null) {
			npJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		}
		return npJdbcTemplate;
	}

}
