package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.IndefiniteTermType;

/**
 * Created by 8565 on 8/01/2016.
 */
public class VisitableIndefiniteTermType implements DimonaContractVisitable {

    private final IndefiniteTermType indefiniteTermType;
    private final IndefiniteTermType originalIndefiniteTermType;

    public VisitableIndefiniteTermType(IndefiniteTermType indefiniteTermType) {
        this.indefiniteTermType = indefiniteTermType;
        this.originalIndefiniteTermType = null;
    }

    public VisitableIndefiniteTermType(IndefiniteTermType indefiniteTermType, IndefiniteTermType originalIndefiniteTermType) {
        this.indefiniteTermType = indefiniteTermType;
        this.originalIndefiniteTermType = originalIndefiniteTermType;
    }

    @Override
    public void accept(DimonaContractVisitor visitor) {
        visitor.visit(indefiniteTermType, originalIndefiniteTermType);
    }
}
