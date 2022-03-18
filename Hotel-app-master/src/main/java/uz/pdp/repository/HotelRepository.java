package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {

boolean existsByName(String name);


}
