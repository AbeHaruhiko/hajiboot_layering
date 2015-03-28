package layering.repository;

import java.util.List;

import layering.domain.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomerRepository {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	private final RowMapper<Customer> customerRowMapper = (rs, i) -> {
		Integer id = rs.getInt("id");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		return new Customer(id, firstName, lastName);
	};

	public List<Customer> findAll() {
		List<Customer> customers = jdbcTemplate.query("select id, first_name, last_name from customers order by id",
				customerRowMapper);
		return customers;
	}

	public Customer findOne(String id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return jdbcTemplate.queryForObject("select id, first_name, last_name from customer where id = :id", param,
				customerRowMapper);
	}

	public Customer save(Customer customer) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
		if (customer.getId() == null) {
			jdbcTemplate.update("insert into customers(first_name, last_name) values(:firstName, :lastName)", param);
		} else {
			jdbcTemplate.update("update customers set first_name = :firstName, last_name = :lastName where id = :id",
					param);
		}
		return customer;
	}

	public void delete(Integer id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		jdbcTemplate.update("delete from customers where id = :id", param);
	}
}
