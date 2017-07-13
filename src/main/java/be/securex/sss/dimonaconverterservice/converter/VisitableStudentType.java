package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.StudentType;

/**
 * Created by 8565 on 2/11/2015.
 */
public class VisitableStudentType implements DimonaContractVisitable {

    private final StudentType studentType;
    private final StudentType originalStudentType;

    public VisitableStudentType(StudentType studentType) {
        this.studentType = studentType;
        this.originalStudentType = null;
    }

    public VisitableStudentType(StudentType studentType, StudentType originalStudentType) {
        this.studentType = studentType;
        this.originalStudentType = originalStudentType;
    }

    @Override
    public void accept(DimonaContractVisitor visitor) {
        visitor.visit(studentType, originalStudentType);
    }
}
