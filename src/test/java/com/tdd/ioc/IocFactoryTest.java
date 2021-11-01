package com.tdd.ioc;

import com.tdd.ioc.entity.Address;
import com.tdd.ioc.entity.Person;
import org.junit.Assert;
import org.junit.Test;

public class IocFactoryTest {

    @Test
    public void testGetInstance() {
        IocFactory iocFactory = new IocFactory(binder -> {
            binder.bind(Address.class);
            binder.bind(Person.class);
        });


        Address address = iocFactory.getInstance(Address.class);
        Person person = iocFactory.getInstance(Person.class);

        Assert.assertNotNull(address);
        Assert.assertNotNull(person);
    }

    @Test
    public void testGetSingleton() {
        IocFactory iocFactory = new IocFactory(binder -> {
            binder.bind(Address.class);
        });

        Address address1 = iocFactory.getInstance(Address.class);
        Address address2 = iocFactory.getInstance(Address.class);

        Assert.assertNotNull(address1);
        Assert.assertNotNull(address2);
        Assert.assertSame(address1, address2);
    }

    @Test(expected = Exception.class)
    public void testNotBind() {
        IocFactory iocFactory = new IocFactory();

        iocFactory.getInstance(Address.class);
    }

    @Test
    public void testProtoType() {
        IocFactory iocFactory = new IocFactory(binder -> {
            binder.bind(Address.class).scope(Scope.PROTOTYPE);
        });

        Address address1 = iocFactory.getInstance(Address.class);
        Address address2 = iocFactory.getInstance(Address.class);

        Assert.assertNotNull(address1);
        Assert.assertNotNull(address2);
        Assert.assertNotSame(address1, address2);
    }

    @Test
    public void testByInstance() {
        Address preDefinedAddress = new Address();

        IocFactory iocFactory = new IocFactory(binder -> {
            binder.bind(Address.class).byInstance(preDefinedAddress);
        });

        Address address = iocFactory.getInstance(Address.class);

        Assert.assertSame(preDefinedAddress, address);
    }

    @Test
    public void testByCreator() {
        Address preDefinedAddress1 = new Address();
        Address preDefinedAddress2 = new Address();

        IocFactory iocFactory = new IocFactory(binder -> {
            binder.bind(Address.class).byCreator(new Creator<Address>() {

                int counter = 0;

                @Override
                public Address get() {
                    if (counter % 2 == 0) {
                        counter++;
                        return preDefinedAddress1;
                    } else {
                        counter++;
                        return preDefinedAddress2;
                    }
                }
            });
        });

        Address address1 = iocFactory.getInstance(Address.class);
        Address address2 = iocFactory.getInstance(Address.class);
        Address address3 = iocFactory.getInstance(Address.class);
        Address address4 = iocFactory.getInstance(Address.class);

        Assert.assertSame(preDefinedAddress1, address1);
        Assert.assertSame(preDefinedAddress2, address2);
        Assert.assertSame(preDefinedAddress1, address3);
        Assert.assertSame(preDefinedAddress2, address4);
    }
}
