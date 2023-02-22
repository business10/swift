package com.swift.cache;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @author Lynch
 * @since
 */

@Slf4j
@Service
public class UserInfoServiceImpl {
  /**
   * 模拟数据库存储数据
   */
  private HashMap<Integer, UserInfo> userInfoMap = new HashMap<>();

  @Autowired
  Cache<String, Object> caffeineCache;

  public void addUserInfo(UserInfo userInfo) {
    userInfoMap.put(userInfo.getId(), userInfo);
    // 加入缓存
    caffeineCache.put(String.valueOf(userInfo.getId()),userInfo);
  }

  public UserInfo getByName(Integer id) {
    // 先从缓存读取
    caffeineCache.getIfPresent(String.valueOf(id));
    UserInfo userInfo = (UserInfo) caffeineCache.asMap().get(String.valueOf(id));
    if (userInfo != null){
      return userInfo;
    }
    // 如果缓存中不存在，则从库中查找
    userInfo = userInfoMap.get(id);
    // 如果用户信息不为空，则加入缓存
    if (userInfo != null){
      caffeineCache.put(String.valueOf(userInfo.getId()),userInfo);
    }
    return userInfo;
  }

  public UserInfo updateUserInfo(UserInfo userInfo) {
    if (!userInfoMap.containsKey(userInfo.getId())) {
      return null;
    }
    // 取旧的值
    UserInfo oldUserInfo = userInfoMap.get(userInfo.getId());
    // 替换内容
    if (!StringUtils.isEmpty(oldUserInfo.getAge())) {
      oldUserInfo.setAge(userInfo.getAge());
    }
    if (!StringUtils.isEmpty(oldUserInfo.getName())) {
      oldUserInfo.setName(userInfo.getName());
    }
    if (!StringUtils.isEmpty(oldUserInfo.getSex())) {
      oldUserInfo.setSex(userInfo.getSex());
    }
    // 将新的对象存储，更新旧对象信息
    userInfoMap.put(oldUserInfo.getId(), oldUserInfo);
    // 替换缓存中的值
    caffeineCache.put(String.valueOf(oldUserInfo.getId()),oldUserInfo);
    return oldUserInfo;
  }


  public void deleteById(Integer id) {
    userInfoMap.remove(id);
    // 从缓存中删除
    caffeineCache.asMap().remove(String.valueOf(id));
  }

}
