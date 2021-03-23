package ru.koryaking;

import ru.koryaking.service.CategoryServiceRemote;
import ru.koryaking.service.ProductServiceRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

public class EjbClient {

    public static void main(String[] args) throws IOException, NamingException {
        Context context = createInitialContext();

        String jndiServiceName = "ejb:/wildflytest/ProductServiceImpl!ru.koryaking.service.ProductServiceRemote";
        ProductServiceRemote productService = (ProductServiceRemote) context.lookup(jndiServiceName);

        productService.findAll()
                .forEach(prod -> System.out.println(prod.getId() + "\t" + prod.getName() + "\t" + prod.getPrice()));


        String jndiServiceName1 = "ejb:/wildflytest/CategoryServiceImpl!ru.koryaking.service.CategoryServiceRemote";
        CategoryServiceRemote categoryService = (CategoryServiceRemote) context.lookup(jndiServiceName1);

        categoryService.findAll()
                .forEach(cat -> System.out.println(cat.getId() + "\t" + cat.getName() + "\t" + cat.getDescription()));
    }

    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(EjbClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }
}

