package com.example.coffeemachine.common.request;

import com.example.coffeemachine.common.model.Drink;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MachineRequest {
    private int outlets;
    private Map<String,Integer> maxCapacity;
    private List<Drink> beverages;
}
