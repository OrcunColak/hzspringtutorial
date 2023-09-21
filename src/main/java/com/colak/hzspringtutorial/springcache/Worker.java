package com.colak.hzspringtutorial.springcache;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class Worker implements Serializable {

    private final int id;

    private final String name;
}
