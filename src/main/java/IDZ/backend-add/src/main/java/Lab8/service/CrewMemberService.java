package Lab8.service;

import Lab8.entity.CrewMember;
import Lab8.repository.CrewMemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// сервіс для роботи з членами екіпажу
@Service
public class CrewMemberService {

    private final CrewMemberRepository crewMemberRepository;

    public CrewMemberService(CrewMemberRepository crewMemberRepository) {
        this.crewMemberRepository = crewMemberRepository;
    }

    // отримання всіх членів екіпажу
    public List<CrewMember> findAll() {
        return crewMemberRepository.findAll();
    }

    // отримання за id
    public CrewMember findById(Long id) {
        return crewMemberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Член екіпажу не знайдено: id=" + id));
    }

    // створення нового
    public CrewMember create(CrewMember crewMember) {
        crewMember.setId(null); // гарантуємо, що це нова сутність
        return crewMemberRepository.save(crewMember);
    }

    // оновлення
    public CrewMember update(Long id, CrewMember updated) {
        CrewMember existing = findById(id);
        existing.setFullName(updated.getFullName());
        existing.setRole(updated.getRole());
        existing.setExperienceYears(updated.getExperienceYears());
        existing.setLicenseNumber(updated.getLicenseNumber());
        return crewMemberRepository.save(existing);
    }

    // видалення
    public void delete(Long id) {
        if (!crewMemberRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Член екіпажу не знайдено: id=" + id);
        }
        crewMemberRepository.deleteById(id);
    }
}
