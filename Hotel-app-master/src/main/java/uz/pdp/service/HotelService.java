package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.HotelDTO;
import uz.pdp.entity.Hotel;
import uz.pdp.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    public ApiResponse add(HotelDTO hotelDTO) {
        if (hotelRepository.existsByName(hotelDTO.getName())) return new ApiResponse("Bunday Hotel bor!", false);
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotelRepository.save(hotel);
        return new ApiResponse("Added Hotel!", true);
    }

    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> getOne(Integer id) {
        return hotelRepository.findById(id);
    }

    public ApiResponse edit(Integer id, HotelDTO hotelDTO) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        Hotel hotel = optionalHotel.get();

        if (hotelRepository.existsByName(hotelDTO.getName())) return new ApiResponse("Bunday Hotel bor Bratan!", false);
        hotel.setName(hotelDTO.getName());
        hotelRepository.save(hotel);
        return new ApiResponse("Edded Hotel!", true);
    }

    public ApiResponse delete(Integer id){
        Optional<Hotel> byId = hotelRepository.findById(id);
        hotelRepository.delete(byId.get());
        return new ApiResponse("Deleted Hotel!",true,byId.get());
    }
}
