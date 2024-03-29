package com.swift.pool;

import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @author Lynch
 * @since
 */
@Data
@Service
public class Cat {
  private String catName;
  public Cat setCatName(String name) {
    this.catName = name;
    return this;
  }
}
