package com.business.manager.nomina.daos.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RedisHash("Parametro")
public class Parametro implements Serializable {
    @Id
    private String nombre;

    @NonNull
    private String valor;
}
