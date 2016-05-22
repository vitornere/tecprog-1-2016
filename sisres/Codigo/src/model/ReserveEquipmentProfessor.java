package model;

import exception.ReserveException;

public class ReserveEquipmentProfessor extends ReservaEquipamento {

    private Professor professor;

    // Mensagens
    private final String PROFESSOR_NULO = "O professor esta nulo.";

    public ReserveEquipmentProfessor(String data, String hora, Equipment equipamento, Professor professor)
            throws ReserveException {
        super(data, hora, equipamento);
        this.setProfessor(professor);
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) throws ReserveException {
        if (professor == null)
            throw new ReserveException(PROFESSOR_NULO);
        this.professor = professor;
    }

    public boolean equals(ReserveEquipmentProfessor obj) {
        return (super.equals(obj) && this.getEquipment().equals(obj.getEquipment()));
    }

    @Override public String toString() {
        return "ReservaEquipamentoProfessor [professor=" + this.getEquipment().toString() + ", toString()=" + super.toString()
                + "]";
    }

}
