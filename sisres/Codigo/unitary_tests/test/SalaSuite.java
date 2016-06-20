package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.control.TestClassroomPersist;
import test.model.ClassroomTest;
import test.persistence.SalaDAOTest;

@RunWith(Suite.class)
@SuiteClasses({ClassroomTest.class, SalaDAOTest.class, TestClassroomPersist.class })
public class SalaSuite {

}
