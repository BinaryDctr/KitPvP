package repo.binarydctr.attacks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerEvent;
import repo.binarydctr.attacks.activator.Activator;

/**
 * The Knock Players Attack knocks players away with a given distance and velocity, also makes damage <br>
 * Created by Florian Hergenhahn at 2019-10-06 <br>
 *
 * @author Florian Hergenhahn
 */
public class KnockPlayersAttack extends SpecialAttack {

    private final double radius;
    private final int damage;
    private final double velocity;

    /**
     * @param activator The activator to use
     * @param radius    The radius to make the kickback work
     * @param damage    The damage to make
     * @param velocity  The velocity
     */
    public KnockPlayersAttack(Activator activator, double radius, int damage, double velocity) {
        super(activator);
        this.radius = radius;
        this.damage = damage;
        this.velocity = velocity;
    }

    @Override
    public void activate(Activator activator, Event event) {

        if (!(event instanceof PlayerEvent)) return;

        Player player = ((PlayerEvent) event).getPlayer();

        for (Entity entity : player.getNearbyEntities(5.0, 5.0, 5.0)) {
            if (!(entity instanceof Player)) continue;

            entity.setVelocity(((Player) entity).getEyeLocation().getDirection().multiply(-2.0));
            ((Player) entity).damage(5);
        }

        player.sendMessage(ChatColor.BLUE + "You used ground pound.");

    }
}
