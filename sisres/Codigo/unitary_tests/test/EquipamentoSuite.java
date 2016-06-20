package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.control.TestEquipamentPersist;
import test.model.EquipamentTest;
import test.persistence.EquipamentoDAOTest;


@RunWith(Suite.class)
@SuiteClasses({EquipamentTest.class, EquipamentoDAOTest.class, TestEquipamentPersist.class })
public class EquipamentoSuite {

}
