package ma.enset.hopital;

import ma.enset.hopital.entities.Patient;
import ma.enset.hopital.repository.PatientRepository;
import ma.enset.hopital.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.Date;

@SpringBootApplication
public class HopitalApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(HopitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // No arguments constructor
//        Patient patient1 = new Patient();
//        patient1.setId(null);
//        patient1.setName("Soufiane Elboubkari");
//        patient1.setBirthday(new Date());
//        patient1.setSick(false);
//        patient1.setScore(230);

        // With arguments constructor
//        Patient patient2 = new Patient(null, "Ali Alaoui", new Date(), true, 150);

//        // Using Builder
//        Patient patient3 = Patient.builder()
//                .name("Ahmed Mostafa")
//                .birthday(new Date())
//                .isSick(false)
//                .score(100)
//                .build();

//        patientRepository.save(patient1);
//        patientRepository.save(patient2);
//        patientRepository.save(patient3);

//        for (int i = 0; i < 100; i++) {
//            patientRepository.save(new Patient(null, "Patient " + i, new Date(), Math.random() > 0.5, (int) (Math.random() * 100)));
//        }
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

//    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

//    @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder = passwordEncoder();
//        return args -> {
//
//            UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user11");
//            if (u1 == null) {
//                jdbcUserDetailsManager.createUser(User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build());
//            }
//
//            UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user22");
//            if (u2 == null) {
//                jdbcUserDetailsManager.createUser(User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build());
//            }
//
//            UserDetails a2 = jdbcUserDetailsManager.loadUserByUsername("admin2");
//            if (a2 == null) {
//                jdbcUserDetailsManager.createUser(User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build());
//            }
//
//        };

        return args -> {
            if (!jdbcUserDetailsManager.userExists("user11")) {
                jdbcUserDetailsManager.createUser(User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build());
            }
            if (!jdbcUserDetailsManager.userExists("user22")) {
                jdbcUserDetailsManager.createUser(User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build());
            }
            if (!jdbcUserDetailsManager.userExists("admin2")) {
                jdbcUserDetailsManager.createUser(User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build());
            }
        };
    }


//    @Bean
    CommandLineRunner commandLineRunner2(AccountService accountService) {
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user1", "1234", "user1@gmail.com", "1234");
            accountService.addNewUser("user2", "1234", "user2@gmail.com", "1234");
            accountService.addNewUser("admin1", "1234", "admin1@gmail.com", "1234");

            accountService.addRoleToUser("user1", "USER");
            accountService.addRoleToUser("user2", "USER");
            accountService.addRoleToUser("admin1", "USER");
            accountService.addRoleToUser("admin1", "ADMIN");

        };
    }

}