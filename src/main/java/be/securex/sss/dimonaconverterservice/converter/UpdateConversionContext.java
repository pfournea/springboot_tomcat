package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.enums.DeclarationType;
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.StudentType;
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.UpdateContractType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 8565 on 23/12/2015.
 */
public class UpdateConversionContext {

    private UpdateContractType updateContractType;

    private UpdateConversionContext(UpdateContractType updateContractType) {
        this.updateContractType = updateContractType;
        initDeclarationTypes();
    }

    public static UpdateConversionContext create(UpdateContractType updateContractType) {
        return new UpdateConversionContext(updateContractType);
    }

    private List<DeclarationType> declarationTypes = new ArrayList<>();

    public List<DeclarationType> getDeclarationTypes() {
        return declarationTypes;
    }

    private void initDeclarationTypes() {
        if (updateContractType.getOriginalContractInfo().getContract().getStudent() != null) {
            StudentType originalStudent = updateContractType.getOriginalContractInfo().getContract().getStudent();
            StudentType updateStudent = updateContractType.getContractInfo().getContract().getStudent();
            if (originalStudent.getNumberOfDays() != updateStudent.getNumberOfDays() ||
                    originalStudent.getNumberOfHours() != updateStudent.getNumberOfHours() ||
                    !originalStudent.getFAONumber().equals(updateStudent.getFAONumber())) {
                declarationTypes.add(DeclarationType.SIGNALITIEK);
            }
            if (originalStudent.getEndDate().compare(updateStudent.getEndDate()) != 0) {
                declarationTypes.add(DeclarationType.WIJZIGING);
            }
        } else {
            declarationTypes.add(DeclarationType.WIJZIGING);
        }
    }

}
