package com.omdasoft.orderonline.dao.person;

import java.util.List;

import com.omdasoft.orderonline.common.BaseDao;
import com.omdasoft.orderonline.domain.person.Person;

public class PersonDao extends BaseDao<Person> {

	@SuppressWarnings("unchecked")
	public Person findPersonByPhone(String phone) {
		List<Person> personList = getEm().createQuery("FROM Person u WHERE u.phone = :phone ").setParameter("phone", phone).getResultList();
		Person person=null;
		if(personList.size()>0)
			person=personList.get(0);
		
		return person;
	}
	
}
