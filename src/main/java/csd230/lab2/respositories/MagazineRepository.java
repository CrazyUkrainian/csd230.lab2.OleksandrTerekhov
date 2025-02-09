package csd230.lab2.respositories;

import csd230.lab2.entities.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {
    Magazine findById(Integer id);
}
