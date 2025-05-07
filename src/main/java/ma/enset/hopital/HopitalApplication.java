package ma.enset.hopital;

import ma.enset.hopital.entities.Patient;
import ma.enset.hopital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
//        patient1.setScore(23);

        // With arguments constructor
//        Patient patient2 = new Patient(null, "Ali Alaoui", new Date(), true, 20);

//        // Using Builder
//        Patient patient3 = Patient.builder()
//                .name("Ahmed Mostafa")
//                .birthday(new Date())
//                .isSick(false)
//                .score(25)
//                .build();
//
//        patientRepository.save(patient1);
//        patientRepository.save(patient2);
//        patientRepository.save(patient3);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

}