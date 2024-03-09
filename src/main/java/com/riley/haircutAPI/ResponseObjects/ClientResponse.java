package com.riley.haircutAPI.ResponseObjects;

import com.riley.haircutAPI.entity.Client;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Data
public class ClientResponse  {

    private List<Client> content;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean last;
}
