package com.tdd.ioc;

import com.tdd.ioc.entity.Address;
import com.tdd.ioc.entity.Person;
import org.junit.Assert;
import org.junit.Test;

public class IocFactoryTest {

    @Test
    public void testGetInstance() {
        IocFactory iocFactory = new IocFactory();
        iocFactory.bind(Address.class);
        iocFactory.bind(Person.class);

        Address address = iocFactory.getInstance(Address.class);
        Person person = iocFactory.getInstance(Person.class);

        Assert.assertNotNull(address);
        Assert.assertNotNull(person);
    }
}
