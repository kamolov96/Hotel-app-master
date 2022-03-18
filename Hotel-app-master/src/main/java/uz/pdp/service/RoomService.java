package uz.pdp.service;

import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.RoomDTO;
import uz.pdp.entity.Hotel;
import uz.pdp.entity.Room;
import uz.pdp.repository.HotelRepository;
import uz.pdp.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public Optional<Room> getOne(Integer id) {
        return roomRepository.findById(id);
    }

    public ApiResponse save(RoomDTO roomDTO) {
        if (roomRepository.existsByNumber(roomDTO.getNumber()))
            return new ApiResponse("Bunday raqamli xona bor !", false);
        Room room = new Room();
        room.setNumber(roomDTO.getNumber());
        room.setFloor(roomDTO.getFloor());
        room.setSize(roomDTO.getSize());

        Optional<Hotel> hotelOptional = hotelRepository.findById(roomDTO.getHotelId());
        Hotel hotel = hotelOptional.get();
        room.setHotel(hotel);

        Room save = roomRepository.save(room);
        return new ApiResponse("Added Room!", true);

    }

    public ApiResponse edit(Integer id, RoomDTO roomDTO) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        Room room = roomOptional.get();

        if (roomRepository.existsByNumber(roomDTO.getHotelId()))
            return new ApiResponse("Bunday raqamli xona bor bratan!", false);

        room.setNumber(roomDTO.getNumber());
        room.setFloor(roomDTO.getFloor());
        room.setSize(roomDTO.getSize());

        Hotel hotel = room.getHotel();
        Optional<Hotel> hotelOptional = hotelRepository.findById(roomDTO.getHotelId());
        if (!hotelOptional.isPresent()) {
            return new ApiResponse("Xatolik ", false);
        }
        Hotel edit = hotelOptional.get();
        room.setHotel(edit);

        hotelRepository.save(hotel);
        room.setHotel(hotel);
        roomRepository.save(room);
        return new ApiResponse("Edited Room!", true);
    }

    public ApiResponse delete(Integer id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        Room room = roomOptional.get();
        roomRepository.delete(room);
        return new ApiResponse("Deleted Room!", true, room);
    }

    public ApiResponse getAllByHotelId(int page, Integer id) {

        Pageable pageable=PageRequest.of(page,10);
        Page<Room> all = roomRepository.findAllByHotel_Id(id, pageable);
        return new ApiResponse("All hotel",true,all);

    }
}
