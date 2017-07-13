package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.DefiniteTermType;

/**
 * Created by 8565 on 8/01/2016.
 */
public class VisitableDefiniteTermType implements  DimonaContractVisitable {

    private final DefiniteTermType definiteTermType;

    public VisitableDefiniteTermType(DefiniteTermType definiteTermType) {
        this.definiteTermType = definiteTermType;
    }

    @Override
    public void accept(DimonaContractVisitor visitor) {
        visitor.visit(definiteTermType);
    }
}
