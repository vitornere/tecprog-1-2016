package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.control.TestReserveClassroomForProfessorPersist;
import test.model.ReserveClassroomForProfessorTest;
import test.persistence.ReservationRoomForTeacherDAOTest;

@RunWith(Suite.class)
@SuiteClasses({ReserveClassroomForProfessorTest.class, ReservationRoomForTeacherDAOTest.class, TestReserveClassroomForProfessorPersist.class})
public class ReservaSalaProfessorSuite {

}
