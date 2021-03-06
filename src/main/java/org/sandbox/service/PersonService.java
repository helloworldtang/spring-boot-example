package org.sandbox.service;

import org.sandbox.orm.Person;
import org.sandbox.orm.PersonDao;
import org.sandbox.orm.PersonExample;
import org.sandbox.orm.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Author: zhangxin
 * Date:   15-8-28
 */
@Service
@Transactional
public class PersonService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonDao personDao;

    public int insert(Person person) {
        logger.info("add person");

        personMapper.insertSelective(person);

        //为了测试事务回滚
        //throw new IllegalArgumentException("roll back");

        return 1;
    }

    public int update(Person person) {
        logger.info("update person");

        PersonExample example = new PersonExample();
        PersonExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(person.getId());

        return personMapper.updateByExample(person, example);
    }

    public int delete(int id) {
        logger.info("delete person, id is " + id);

        return personMapper.deleteByPrimaryKey(id);
    }

    public Person select(int id) {
//        logger.info("select person, id is " + id);
//
//        PersonExample example = new PersonExample();
//        PersonExample.Criteria criteria = example.createCriteria();
//        criteria.andIdEqualTo(id);
//        List<Person> persons = personMapper.selectByExample(example);
//        if(persons != null && persons.size() > 0) {
//            return persons.get(0);
//        }
//        return null;
        return personDao.select(id);
    }
}
