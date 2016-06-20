package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.control.TestReserveClassroomForProfessorPersist;
import test.model.ReserveClassroomForProfessorTest;
import test.persistence.ResSalaProfessorDAOTest;

@RunWith(Suite.class)
@SuiteClasses({ReserveClassroomForProfessorTest.class, ResSalaProfessorDAOTest.class, TestReserveClassroomForProfessorPersist.class})
public class ReservaSalaProfessorSuite {

}
