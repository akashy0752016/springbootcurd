package com.example.postgresDemo.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.example.postgresDemo.model.User;
import com.example.postgresDemo.dao.Userdao;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements Userdao {
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO public.users(name, phone) VALUES (?, ?)";
		getJdbcTemplate().update(sql, new Object[] {user.getName(), user.getPhone()});
	}

	@Override
	public void inserBatch(List<User> users) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO public.users(name, phone) VALUES (?, ?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				// TODO Auto-generated method stub
				User user = users.get(i);
				//ps.setLong(1, user.getId());
				ps.setString(1, user.getName());
				ps.setString(2, user.getPhone());
			}
			
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return users.size();
			}
		});
	}

	@Override
	public List<User> loadAllUser() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM public.users";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		List<User> result = new ArrayList<User>();
		for (Map<String, Object> row: rows) {
			User user = new User();
			user.setId((Long)row.get("id"));
			user.setName((String)row.get("name"));
			user.setPhone((String)row.get("phone"));
			result.add(user);
		}
		return result;
	}

	@Override
	public User findUserById(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM public.users WHERE id = ?";
		return (User)getJdbcTemplate().queryForObject(sql, new Object[] {id}, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int i) throws SQLException {
				// TODO Auto-generated method stub
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				return user;
			}
			
		});
	}

	@Override
	public String findNameById(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT name FROM public.users WHERE id = ?";
		return getJdbcTemplate().queryForObject(sql, new Object[] {id}, String.class);
	}

	@Override
	public int getTotalNumberUser() {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM public.users";
		return getJdbcTemplate().queryForObject(sql, Integer.class);
	}

	@Override
	public Long getLastInsertId() {
		// TODO Auto-generated method stub
		String sql = "SELECT currval(pg_get_serial_sequence('users','id'))";
		return getJdbcTemplate().queryForObject(sql, Long.class);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		String sql = "UPDATE public.users SET name=?, phone=? WHERE id=?";
		getJdbcTemplate().update(sql, new Object[] {user.getName(), user.getPhone(), user.getId()});
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM public.users WHERE id=?";
		getJdbcTemplate().update(sql, new Object[] {id});
	}
}
