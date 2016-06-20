package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.control.TestStudentPersist;
import test.model.StudentTest;
import test.persistence.AlunoDAOTest;


@RunWith(Suite.class)
@SuiteClasses({StudentTest.class, AlunoDAOTest.class, TestStudentPersist.class })
public class AlunoSuite {

}
