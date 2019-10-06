package repo.binarydctr.attacks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerEvent;
import repo.binarydctr.attacks.activator.Activator;

/**
 * Created by Florian Hergenhahn at 2019-10-06 <br>
 *
 * @author Florian Hergenhahn
 */
public class LeapAttack extends SpecialAttack {

    private final double velocity;

    public LeapAttack(Activator activator, double velocity) {
        super(activator);
        this.velocity = velocity;
    }

    public double getVelocity() {
        return velocity;
    }

    @Override
    public void activate(Activator activator, Event event) {

        if(!(event instanceof PlayerEvent))
            return;

        Player player = ((PlayerEvent) event).getPlayer();
        player.setVelocity(player.getEyeLocation().getDirection().multiply(velocity));
        player.sendMessage(ChatColor.BLUE + "You used leap.");
    }
}
