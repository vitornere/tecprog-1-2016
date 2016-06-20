package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.control.TestReserveClassroomForStudentPersist;
import test.model.ReserveClassroomForStudentTest;
import test.persistence.ReservationRoomForStudentDAOTest;

@RunWith(Suite.class)
@SuiteClasses({ReserveClassroomForStudentTest.class, ReservationRoomForStudentDAOTest.class, TestReserveClassroomForStudentPersist.class})
public class ReservaSalaAlunoSuite {

}
