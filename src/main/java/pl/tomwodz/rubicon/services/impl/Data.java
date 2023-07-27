package pl.tomwodz.rubicon.services.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    List<String> cols;
    List<String[]> data;
}
