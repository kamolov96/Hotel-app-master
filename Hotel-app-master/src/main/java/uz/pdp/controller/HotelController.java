package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.HotelDTO;
import uz.pdp.entity.Hotel;
import uz.pdp.service.HotelService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    HotelService hotelService;


    @PostMapping
    public HttpEntity<?> save(@Valid @RequestBody HotelDTO hotelDTO) {
        ApiResponse response = hotelService.add(hotelDTO);
        return ResponseEntity.status(response.isSuccess() ?
                HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        List<Hotel> all = hotelService.getAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Hotel> one = hotelService.getOne(id);
        if (one.isPresent()) {
           return ResponseEntity.status(200).body(one.get());
        }else{
           return ResponseEntity.status(404).body(new Hotel());
        }
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@Valid @RequestBody HotelDTO hotelDTO){
        ApiResponse response = hotelService.edit(id, hotelDTO);
        return ResponseEntity.status(response.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = hotelService.delete(id);
        return ResponseEntity.status(response.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }
}
