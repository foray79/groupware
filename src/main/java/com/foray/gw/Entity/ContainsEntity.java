package com.foray.gw.Entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import jakarta.persistence.*;

@Data
@Component
public class ContainsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String Name;
}
