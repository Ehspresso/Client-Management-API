package com.riley.haircutAPI.ResponseObjects;

import com.riley.haircutAPI.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponse  {

    private List<Client> content;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean last;
}
