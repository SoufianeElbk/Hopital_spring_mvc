package ma.enset.hopital.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.hopital.entities.Patient;
import ma.enset.hopital.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PatientController {
    private final PatientRepository patientRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }

    @GetMapping("/user/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "10") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Patient> patients = patientRepository.findByNameContains(keyword, PageRequest.of(page, size));
        model.addAttribute("patients", patients.getContent());
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "Patients";
    }

    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@RequestParam(name = "id") Long id,
                         @RequestParam(name="keyword", defaultValue = "") String keyword,
                         @RequestParam(name="page", defaultValue = "0") int page) {
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/formPatients")
    @PreAuthorize("hasRole('ADMIN')")
    public String formPatients(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }

    @PostMapping("/admin/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(name = "keyword", defaultValue = "") String keyword,
                       @RequestParam(name = "page", defaultValue = "0") int page) {
        if (bindingResult.hasErrors()) {
            return "formPatients";
        }
        patientRepository.save(patient);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/admin/editPatients")
    @PreAuthorize("hasRole('ADMIN')")
    public String editPatients(Model model, Long id, String keyword, int page) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) {
            throw new RuntimeException("Patient not found");
        }
        model.addAttribute("patient", patient);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editPatients";
    }

}