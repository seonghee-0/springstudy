package com.sh.app06;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.sh.app06.config.AppConfig;
import com.sh.app06.service.ContactService;

@SpringJUnitConfig(classes = {AppConfig.class})

class TxTestCase {

  @Autowired
  private ContactService contactService;
  
  @Test
  void test() {
    contactService.tx_txTest();
  }
  
}
