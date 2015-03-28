package layering;

import java.sql.ResultSet;
import java.sql.SQLException;

import layering.domain.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class App implements CommandLineRunner {

	// @Autowired
	// CustomerService customerService;

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// // データ追加
		// customerService.save(new Customer(1, "Nobita", "Nobi"));
		// customerService.save(new Customer(2, "Takeshi", "Goda"));
		// customerService.save(new Customer(3, "Suneo", "Honekawa"));
		//
		// // データ表示
		// customerService.findAll().forEach(System.out::println);

		// String sql = "SELECT :a + :b";
		// SqlParameterSource param = new MapSqlParameterSource().addValue("a",
		// 100).addValue("b", 200);
		// Integer result = jdbcTemplate.queryForObject(sql, param,
		// Integer.class);
		//
		// System.out.println("result = " + result);

		String sql = "select id, first_name, last_name from customers where id = :id";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("id", 1);
		Customer result = jdbcTemplate.queryForObject(sql, param,
				new RowMapper<Customer>() {

					@Override
					public Customer mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						return new Customer(rs.getInt("id"), rs
								.getString("first_name"), rs
								.getString("last_name"));

					}

				});
		System.out.println("result = " + result);
	}
}
