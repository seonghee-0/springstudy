package com.sh.app06.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.app06.dto.ContactDTO;
import com.sh.app06.mapper.ContactMapper;

@Service
public class ContactServiceImpl implements ContactService {

  private ContactMapper contactMapper;
  
  @Autowired  
  public ContactServiceImpl(ContactMapper contactMapper) {
    super();
    this.contactMapper = contactMapper;
  }

  @Override
  public List<ContactDTO> getContactList() {
    return contactMapper.getContactList();
  }

  @Override
  public ContactDTO getContactByNo(int contactNo) {
    return contactMapper.getContactByNo(contactNo);
  }

  @Override
  public int tx_registerContact(ContactDTO contact) {
    return contactMapper.registerContact(contact);
  }

  @Override
  public int tx_modifyContact(ContactDTO contact) {
    return contactMapper.modifyContact(contact);
  }

  @Override
  public int tx_removeContact(int contactNo) {
    return contactMapper.removeContact(contactNo);
  }
  
  @Override
  public int tx_removeContactList(String[] contactNoList) {
    return contactMapper.removeContactList(Arrays.asList(contactNoList));
  }

  @Override
  public void tx_txTest() {
    //Insert 성공
    contactMapper.registerContact(new ContactDTO(0, "test1", "test1@test.com", "010-1111-1111", null));
    contactMapper.registerContact(new ContactDTO(0, "test2", "test2@test.com", "010-1111-1111", null));
    contactMapper.registerContact(new ContactDTO(0, "test3", "test3@test.com", "010-1111-1111", null));
    
    //Insert 실패
    contactMapper.registerContact(new ContactDTO(0, null, null, null, null));
    
    //트랜잭션 정상 동작 결과 == 모든 INSERT는 실패상태여야 함
  }
  
}