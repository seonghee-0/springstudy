package com.min.app05.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.app05.dto.ContactDTO;

@Repository
public class ContactDAOImpl implements ContactDAO {

  private SqlSessionTemplate sqlSessionTemplate;
  
  @Autowired
  public ContactDAOImpl(SqlSessionTemplate sqlSessionTemplate) {
    super();
    this.sqlSessionTemplate = sqlSessionTemplate;
  }

  @Override
  public List<ContactDTO> getContactList() {
    return sqlSessionTemplate.selectList("com.min.app05.dao.ContactMapper.getContactList");
  }

  @Override
  public ContactDTO getContactByNo(int contactNo) {
    return sqlSessionTemplate.selectOne("com.min.app05.dao.ContactMapper.getContactByNo", contactNo);
  }

  @Override
  public int registerContact(ContactDTO contact) {
    return sqlSessionTemplate.insert("com.min.app05.dao.ContactMapper.registerContact", contact);
  }

  @Override
  public int modifyContact(ContactDTO contact) {
    return sqlSessionTemplate.update("com.min.app05.dao.ContactMapper.modifyContact", contact);
  }

  @Override
  public int removeContact(int contactNo) {
    return sqlSessionTemplate.delete("com.min.app05.dao.ContactMapper.removeContact", contactNo);
  }

}
