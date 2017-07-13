package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.xsd.v4.hronline.*;

/**
 * Created by 8565 on 23/01/2016.
 */
public class UpdateHireConversionContext {

    private TerminationCType termination;
    private AnnulationCType annulation;
    private DeleteTerminationCType deleteTermination;
    private ChangeStudentCType changeStudent;
    private ChangeExtraFlexiCType changeExtraFlexi;

    public TerminationCType getTermination() {
        return termination;
    }

    public void setTermination(TerminationCType termination) {
        this.termination = termination;
    }

    public AnnulationCType getAnnulation() {
        return annulation;
    }

    public void setAnnulation(AnnulationCType annulation) {
        this.annulation = annulation;
    }

    public DeleteTerminationCType getDeleteTermination() {
        return deleteTermination;
    }

    public void setDeleteTermination(DeleteTerminationCType deleteTermination) {
        this.deleteTermination = deleteTermination;
    }

    public ChangeStudentCType getChangeStudent() {
        return changeStudent;
    }

    public void setChangeStudent(ChangeStudentCType changeStudent) {
        this.changeStudent = changeStudent;
    }

    public ChangeExtraFlexiCType getChangeExtraFlexi() {
        return changeExtraFlexi;
    }

    public void setChangeExtraFlexi(ChangeExtraFlexiCType changeExtraFlexi) {
        this.changeExtraFlexi = changeExtraFlexi;
    }
}
