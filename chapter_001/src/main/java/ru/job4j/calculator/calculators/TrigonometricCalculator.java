package ru.job4j.calculator.calculators;


import ru.job4j.calculator.operations.AbstractOperation;
import ru.job4j.calculator.operations.trigonometric.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 08.12.2019.
 */
public class TrigonometricCalculator extends AbstractCalculator {

    @Override
    public void fillActions() {
        AbstractOperation cos = new Cosine();
        this.actions.put(cos.getName(), cos);
        AbstractOperation sin = new Sinus();
        this.actions.put(sin.getName(), sin);
        AbstractOperation tg = new Tangent();
        this.actions.put(tg.getName(), tg);
        AbstractOperation ctg = new Cotangent();
        this.actions.put(ctg.getName(), ctg);
        AbstractOperation asin = new ASinus();
        this.actions.put(asin.getName(), asin);
        AbstractOperation acos = new ACosine();
        this.actions.put(acos.getName(), acos);
        AbstractOperation atg = new ArcTangent();
        this.actions.put(atg.getName(), atg);
        AbstractOperation sinh = new HyperbolicSinus();
        this.actions.put(sinh.getName(), sinh);
        AbstractOperation cosh = new HyperbolicCosine();
        this.actions.put(cosh.getName(), cosh);
        AbstractOperation tanh = new HyperbolicTangent();
        this.actions.put(tanh.getName(), tanh);
    }
}
