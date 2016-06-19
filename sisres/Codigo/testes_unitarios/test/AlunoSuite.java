package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.control.TestStudentPersist;
import test.model.AlunoTest;
import test.persistence.AlunoDAOTest;


@RunWith(Suite.class)
@SuiteClasses({AlunoTest.class, AlunoDAOTest.class, TestStudentPersist.class })
public class AlunoSuite {

}
