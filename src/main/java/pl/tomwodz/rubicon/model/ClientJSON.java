package pl.tomwodz.rubicon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class ClientJSON {
    String customer;
    String country;
    String order;
    String status;
    String  group;
}
