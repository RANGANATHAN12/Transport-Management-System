package com.demo.project.HibernateHelper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateHelper {

static SessionFactory sf;

static {
try {
sf = new Configuration().configure().buildSessionFactory();
} catch (Exception e) {
System.out.println(e);
}
}

public static SessionFactory getSessionFactory() {
return sf;
}
}

