package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.control.TestReserveClassroomForStudentPersist;
import test.model.ReserveClassroomForStudentTest;
import test.persistence.ResSalaAlunoDAOTest;

@RunWith(Suite.class)
@SuiteClasses({ReserveClassroomForStudentTest.class, ResSalaAlunoDAOTest.class, TestReserveClassroomForStudentPersist.class})
public class ReservaSalaAlunoSuite {

}
