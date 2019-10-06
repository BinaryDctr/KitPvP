package repo.binarydctr.attacks;

import repo.binarydctr.attacks.activator.Activator;
import repo.binarydctr.attacks.activator.ActivatorCallback;

/**
 * Created by Florian Hergenhahn at 2019-10-06 <br>
 *
 * @author Florian Hergenhahn
 */
public abstract class SpecialAttack implements ActivatorCallback {

    private final Activator activator;

    public SpecialAttack(Activator activator) {
        this.activator = activator;
    }

    public Activator getActivator() {
        return activator;
    }
}
