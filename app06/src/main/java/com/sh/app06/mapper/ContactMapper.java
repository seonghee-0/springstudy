package com.sh.app06.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sh.app06.dto.ContactDTO;

@Mapper
public interface ContactMapper {
  List<ContactDTO> getContactList();
  ContactDTO getContactByNo(int contactNo);
  int registerContact(ContactDTO contact);
  int modifyContact(ContactDTO contact);
  int removeContact(int contactNo);
  int removeContactList(List<String> list);
}
