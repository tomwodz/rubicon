package pl.tomwodz.rubicon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Client {
    String customer;
    String country;
    String order;
    String status;
    Integer group;
    String typeOfFile;
}
