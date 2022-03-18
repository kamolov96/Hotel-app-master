package uz.pdp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Hotel;
import uz.pdp.entity.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
boolean existsByNumber(Integer number);

    List<Room> findAllByNumber(Integer number);

    Page<Room> findAllByHotel_Id(Integer hotel_id, Pageable pageable);
}
