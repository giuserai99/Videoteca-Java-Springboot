package it.cgmconsulting.raineri.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class CustomerStoreResponse {

    private String storeName;

    private long totalCustomers;

}
