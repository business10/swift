package com.swift.optimization;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.OperationsException;

/**
 * @author Lynch
 * @since
 */
public class SpringBoot {
  // 使用@ConfigurationProperties 代替@Value
  @Data
  // 指定前缀
  @ConfigurationProperties(prefix = "developer")
  @Component
  public static class DeveloperProperty {
    private String name;
    private String website;
    private String qq;
    private String phoneNumber;
  }

  // 用@RequiredArgsConstructor代替@Autowired
  @RestController
  @RequiredArgsConstructor  //使用时注入bean，lombok提供
  public class PropertyController {

    final DeveloperProperty developerProperty;

    @GetMapping("/property")
    public Object index() {
      return developerProperty.getName();
    }
  }

  //代码模块化
  public void overview(){
    dealOne();
    dealTwo();
    dealThree();
  }

  private void dealThree() {
  }

  private void dealTwo() {
  }

  private void dealOne() {
  }


  // 抛异常而不是返回
  public void delete() throws OperationsException {
    boolean flag = false;
    if(!flag){
      throw new OperationsException("删除失败");
    }
  }

  //不要返回null

}
