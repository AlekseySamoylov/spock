package com.alekseysamoylov.spock.entity;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {

  private Long id;

  private String name;

  public Role role;
}
