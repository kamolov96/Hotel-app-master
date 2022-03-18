package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.entity.Hotel;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RoomDTO {

    private Integer number;
    private Integer floor;
    private Double size;
    private Integer hotelId;

}
