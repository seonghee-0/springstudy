package com.sh.app06.service;

import java.util.List;

import com.sh.app06.dto.ContactDTO;

public interface ContactService {
  List<ContactDTO> getContactList();
  ContactDTO getContactByNo(int contactNo);
  int tx_registerContact(ContactDTO contact);
  int tx_modifyContact(ContactDTO contact);
  int tx_removeContact(int contactNo);
  int tx_removeContactList(String[] contactNoList);
  // transaction 테스트
  void tx_txTest(); 
}
