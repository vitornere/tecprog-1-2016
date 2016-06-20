package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.control.TestTeacherPersist;
import test.model.ProfessorTest;
import test.persistence.ProfessorDAOTest;


@RunWith(Suite.class)
@SuiteClasses({ProfessorTest.class, ProfessorDAOTest.class, TestTeacherPersist.class })
public class ProfessorSuite {

}
