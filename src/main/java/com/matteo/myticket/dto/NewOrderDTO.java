package com.matteo.myticket.dto;

import lombok.Data;

import java.util.List;

@Data
public class NewOrderDTO {

    private List<OrderItemDTO> items;
}
