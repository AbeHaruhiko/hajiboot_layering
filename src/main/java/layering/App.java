package layering;

import layering.domain.Customer;
import layering.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class App implements CommandLineRunner {

	// @Autowired
	// CustomerService customerService;

	// @Autowired
	// NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	CustomerRepository customerRepository;

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

		Customer created = customerRepository.save(new Customer(null, "Hidetoshi", "Dekisugu"));

		System.out.println(created + "is created");

		customerRepository.findAll().forEach(System.out::println);
	}
}
