package game.content;

import game.Server;
import engine.event.*;
import game.entity.npc.NPC;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.object.Objects;
import game.object.MultiCannonObject;
import engine.util.Misc;
import engine.task.*;
import game.entity.Position;
import game.entity.UpdateFlags;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
/**
 * Class that contains all of the dwarf multi-cannon functions. Has support for
 * loading and reloading, single and multicombat, setting and picking up and
 * anything else I probably missed. Have fun with this and if you use be sure to
 * give relaxlawl and I credits.
 *
 * If there are any problems with the system please contact me (lare96 on r-s;
 * clawz fury on mopar) so I may fix them.
 *
 * @author lare96
 * @author relaxlawl
 */
public class DwarfCannon {
 
    /**
     * The damage range the cannon may deal (between 0 and this number). This
     * can (and should) be edited to deal damage based on your skill levels.
     */
    private static final int MAX_DAMAGE = 30;
 
    /**
     * The range of a cannon (how far it can shoot) in tiles. This is to ensure
     * that only NPC's within the distance are hit by the cannon.
     */
    private static final int MAX_RANGE = 5;
 
    /**
     * How close you are allowed to build to another cannon (in tiles)
     */
    private static final int DISTANCE_TO_BUILD = 10;
 
    /**
     * List of other cannons set up by other players in the {@link World}. We
     * need this so that cannons cannot be built too close to each other (that
     * would cause problems wouldn't it?).
     */
    private static List<MultiCannonObject> cannons = new ArrayList<MultiCannonObject>();
 
    /**
     * The player using the cannon
     */
    private Player player;
 
    /**
     * How far the player has come in setting up the cannon. There are 6 stages
     * in total, the default stage being that the player has not started setting
     * up a cannon.
     */
    private Setup stage = Setup.NO_CANNON;
 
    /**
     * The direction the cannon is facing. This is very important for us to have
     * because we need it to turn the cannon and target npcs in the specific
     * direction its facing.
     */
    private Rotation rotation;
 
    /**
     * The instance of the cannon object you (the player instanced in this
     * class) are in control of.
     */
    public MultiCannonObject cannon;
 
    /**
     * The amount of cannonballs that are currently loaded and being fired in
     * this cannon.
     */
    private int balls;
 
    /**
     * Flag that returns if your cannon is currently shooting or not.
     */
    private boolean shooting;
 
    /**
     * Construct a new {@link DwarfCannon} Object to be used to handle the
     * functions of the multi-cannon.
     */
    public DwarfCannon(Player player) {
        this.player = player;
    }
 
    /**
     * The six cannon setup stages in chronological order (note: do not change
     * order, order is crucial!).
     */
    private enum Setup {
        NO_CANNON, BASE, STAND, BARRELS, FURNACE, COMPLETE_CANNON
    }
 
    /**
     * The eight directions a cannon can face, in chronological order (note: do
     * not change order, order is crucial!).
     */
    private enum Rotation {
        NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST
    }
 
    /**
     * Set up a cannon for the player and add the cannon to the list of active
     * cannons.
     */
    public void setup() {
 
        /**
         * If we already have a cannon setup/completed, then we cannot make
         * another one.
         */
        if (stage.ordinal() != 0) {
            player.sendMessage("You have already started setting up a cannon!");
            return;
        }
 
        /**
         * If we are too close to another cannon, do not build.
         */
        for (MultiCannonObject other : cannons) {
            if (other.getPosition().withinDistance(new Position(player.absX, player.absY, player.heightLevel), DISTANCE_TO_BUILD)) {
                player.sendMessage("You are trying to build too close to another cannon!");
                return;
            }
        }
 
        /**
         * Checks if you have the items to build the cannon.
         */
        if (player.getItems().playerHasItem(6, 1) && player.getItems().playerHasItem(8, 1) && player.getItems().playerHasItem(10, 1) && player.getItems().playerHasItem(12, 1)) {
 
            /**
             * Stop movement to ensure cannon is built in the right place
             * (crucial).
             */
            player.stopMovement();
 
            /**
             * Set up the base
             */
            stage = Setup.BASE;
            player.startAnimation(827);
            MultiCannonObject base = new MultiCannonObject(7, player);
            player.facePoint(base.getPosition().getX(), base.getPosition().getY());
            Server.objectHandler.addObject(base);
            Server.objectHandler.placeObject(base);
            player.getItems().deleteItem(6, 1);
            cannons.add(base);
            cannon = base;
            stage = Setup.STAND;
 
            /**
             * Set up the stand
             */
            Server.getTaskScheduler().schedule(new Task(3, false) {
                @Override
                protected void execute() {
                    if (player.disconnected) {
                        this.stop();
                        return;
                    }
 
                    player.startAnimation(827);
                    MultiCannonObject stand = new MultiCannonObject(8, player);
                    Server.objectHandler.removeObject(cannon);
                    Server.objectHandler.addObject(stand);
                    Server.objectHandler.placeObject(stand);
                    player.getItems().deleteItem(8, 1);
                    cannon = stand;
                    stage = Setup.BARRELS;
                    this.stop();
                }
            });
 
            /**
             * Set up the barrel
             */
            Server.getTaskScheduler().schedule(new Task(6, false) {
                @Override
                protected void execute() {
                    if (player.disconnected) {
                        this.stop();
                        return;
                    }
 
                    player.startAnimation(827);
                    MultiCannonObject barrel = new MultiCannonObject(9, player);
                    Server.objectHandler.removeObject(cannon);
                    Server.objectHandler.addObject(barrel);
                    Server.objectHandler.placeObject(barrel);
                    player.getItems().deleteItem(10, 1);
                    cannon = barrel;
                    stage = Setup.FURNACE;
                    this.stop();
                }
            });
 
            /**
             * Set up the furnace and complete the cannon
             */
            Server.getTaskScheduler().schedule(new Task(9, false) {
                @Override
                protected void execute() {
                    if (player.disconnected) {
                        this.stop();
                        return;
                    }
 
                    player.startAnimation(827);
                    MultiCannonObject newCannon = new MultiCannonObject(6, player);
                    Server.objectHandler.removeObject(cannon);
                    Server.objectHandler.addObject(newCannon);
                    Server.objectHandler.placeObject(newCannon);
                    player.getItems().deleteItem(12, 1);
                    cannon = newCannon;
                    stage = Setup.COMPLETE_CANNON;
                    this.stop();
                }
            });
        }
    }
 
    /**
     * Pick up a cannon for the player and remove the cannon from the list of
     * active cannons.
     */
    public void pickup(Position p) {
 
        /**
         * Checks if the cannon we are picking up is our cannon
         */
        if (cannon.getPosition().getX() == p.getX() && cannon.getPosition().getY() == p.getY() && cannon.getPosition().getZ() == p.getZ()) {
 
            /**
             * Stop firing and remove the cannon from the rs2 world, and the
             * list of active cannons. Then, add all the parts and unfired
             * cannonballs to your inventory or bank.
             */
            shooting = false;
            player.startAnimation(827);
            Objects empty = new Objects(6951, new Position(cannon.getPosition().getX(), cannon.getPosition().getY(), cannon.getPosition().getZ()), 0, 10);
            Server.objectHandler.addObject(empty);
            Server.objectHandler.placeObject(empty);
            Server.objectHandler.removeObject(cannon);
            remove();
            cannon = null;
 
            if (player.getItems().freeSlots() > 3) {
                player.getItems().addItem(6, 1);
                player.getItems().addItem(8, 1);
                player.getItems().addItem(10, 1);
                player.getItems().addItem(12, 1);
            } else {
                player.getItems().bankItem(6, player.getItems().getItemSlot(6), 1);
                player.getItems().bankItem(8, player.getItems().getItemSlot(8), 1);
                player.getItems().bankItem(10, player.getItems().getItemSlot(10), 1);
                player.getItems().bankItem(12, player.getItems().getItemSlot(12), 1);
                player.sendMessage("You did not have enough inventory space, so this cannon was banked.");
            }
 
            if (balls > 0) {
                if (player.getItems().freeSlots() > 0) {
                    player.getItems().addItem(2, balls);
                } else {
                    player.getItems().bankItem(2, player.getItems().getItemSlot(2), balls);
                    player.sendMessage("You did not have enough inventory space, so your cannonballs have been banked.");
                }
 
                balls = 0;
            }
 
            player.sendMessage("You have picked up your cannon!");
            stage = Setup.NO_CANNON;
        } else {
            player.sendMessage("This is not your cannon to pickup!");
        }
    }
 
    /**
     * Load the cannon and begin firing
     */
    public void shoot(Position p) {
 
        /**
         * Checks if the cannon we are shooting is our cannon
         */
        if (cannon.getPosition().getX() == p.getX() && cannon.getPosition().getY() == p.getY() && cannon.getPosition().getZ() == p.getZ()) {
 
            /**
             * Do not shoot if we are already firing
             */
            if (shooting) {
                player.sendMessage("Your cannon is already firing!");
                return;
            }
 
            /**
             * Load cannonballs and begin rotating the cannon and firing at mobs
             * based on the direction the cannon is facing.
             */
            if (balls < 1) {
                int amountOfCannonBalls = player.getItems().itemAmount(2) > 30 ? 30 : player.getItems().itemAmount(2);
 
                if (amountOfCannonBalls < 1) {
                    player.sendMessage("You need ammo to shoot this cannon!");
                    return;
                }
 
                balls = amountOfCannonBalls;
                player.getItems().deleteItem(2, amountOfCannonBalls);
                shooting = true;
 
                Server.getTaskScheduler().schedule(new Task((player.getRegion().inMulti() ? 2 : 4), false) {
                    @Override
                    protected void execute() {
                        if (player.disconnected || !shooting) {
                            this.stop();
                            return;
                        }
 
                        if (balls < 1) {
                            player.sendMessage("Your cannon has run out of ammo!");
                            shooting = false;
                            this.stop();
                            return;
                        } else {
                            if (rotation == null) {
                                rotation = Rotation.NORTH;
                                rotate(cannon);
                                fireAtMobs();
                                return;
                            }
 
                            switch (rotation) {
                                case NORTH: // north
                                    rotation = Rotation.NORTH_EAST;
                                    break;
                                case NORTH_EAST: // north-east
                                    rotation = Rotation.EAST;
                                    break;
                                case EAST: // east
                                    rotation = Rotation.SOUTH_EAST;
                                    break;
                                case SOUTH_EAST: // south-east
                                    rotation = Rotation.SOUTH;
                                    break;
                                case SOUTH: // south
                                    rotation = Rotation.SOUTH_WEST;
                                    break;
                                case SOUTH_WEST: // south-west
                                    rotation = Rotation.WEST;
                                    break;
                                case WEST: // west
                                    rotation = Rotation.NORTH_WEST;
                                    break;
                                case NORTH_WEST: // north-west
                                    rotation = null;
                                    break;
                            }
 
                            rotate(cannon);
                            fireAtMobs();
                        }
                    }
                });
            }
        } else {
            player.sendMessage("This is not your cannon to fire!");
        }
    }
 
    /**
     * Rotate the cannon and change the object animation based on the direction
     * we are facing.
     */
    private void rotate(MultiCannonObject cannon) {
        switch (rotation) {
            case NORTH: // north
                player.getPA().objectAnim(cannon.getPosition().getX(), cannon.getPosition().getY(), 516, 10, -1);
                break;
            case NORTH_EAST: // north-east
                player.getPA().objectAnim(cannon.getPosition().getX(), cannon.getPosition().getY(), 517, 10, -1);
                break;
            case EAST: // east
                player.getPA().objectAnim(cannon.getPosition().getX(), cannon.getPosition().getY(), 518, 10, -1);
                break;
            case SOUTH_EAST: // south-east
                player.getPA().objectAnim(cannon.getPosition().getX(), cannon.getPosition().getY(), 519, 10, -1);
                break;
            case SOUTH: // south
                player.getPA().objectAnim(cannon.getPosition().getX(), cannon.getPosition().getY(), 520, 10, -1);
                break;
            case SOUTH_WEST: // south-west
                player.getPA().objectAnim(cannon.getPosition().getX(), cannon.getPosition().getY(), 521, 10, -1);
                break;
            case WEST: // west
                player.getPA().objectAnim(cannon.getPosition().getX(), cannon.getPosition().getY(), 514, 10, -1);
                break;
            case NORTH_WEST: // north-west
                player.getPA().objectAnim(cannon.getPosition().getX(), cannon.getPosition().getY(), 515, 10, -1);
                rotation = null;
                break;
        }
    }
 
    /**
     * Fires at a targeted mob and decrements the amount of cannonballs the
     * cannon has by one for each mob hit.
     */
    private void fireAtMobs() {
 
        /**
         * Target a mob
         */
        NPC hit = targetMob(player, new Position(cannon.getPosition().getX(), cannon.getPosition().getY(), cannon.getPosition().getZ()));
 
        /**
         * Get the damage to deal
         */
        int damage = Misc.random(MAX_DAMAGE);
 
        /**
         * Deal the damage based on if you are in a single or multi-combat area.
         */
        if (hit != null) {
            if (player.getRegion().inMulti() && player.getRegion().inWild()) {
                cannonProjectile(player, cannon, hit);
                hit.hitDiff = damage;
                hit.HP -= damage;
                hit.getUpdateFlags().set(UpdateFlag.HIT, true);
                hit.killerId = player.index;
                hit.faceUpdate(player.index);
            } else {
                if (hit.underAttack && hit.killerId != player.index)
                    return;
 
                cannonProjectile(player, cannon, hit);
                hit.hitDiff = damage;
                hit.HP -= damage;
                hit.getUpdateFlags().set(UpdateFlag.HIT, true);
                hit.killerId = player.index;
                hit.faceUpdate(player.index);
            }
 
            balls--;
        }
    }
 
    /**
     * Targets a specific mob based on the cannon's distance to it, and the
     * cannon's direction relative to the mob.
     */
    private NPC targetMob(Player player, Position p) {
        for (int i = 0; i < NPCHandler.maxNPCs; i++) {
            if (NPCHandler.npcs[i] == null) {
                continue;
            }
 
            NPC npc = NPCHandler.npcs[i];
 
            int myX = cannon.getPosition().getX();
            int myY = cannon.getPosition().getY();
            int theirX = npc.absX;
            int theirY = npc.absY;
 
            Position npcPos = new Position(theirX, theirY);
 
            if (!npc.isDead && npc.heightLevel == p.getZ() && !npc.isDead && npc.HP != 0 && npc.npcType != 1266 && npc.npcType != 1268 && npcPos.withinDistance(new Position(myX, myY), MAX_RANGE)) {
 
                if (rotation == null) {
                    rotation = Rotation.NORTH;
                }
 
                switch (rotation) {
                    case NORTH:
                        if (theirY > myY && theirX >= myX - 1 && theirX <= myX + 1)
                            return npc;
                        break;
                    case NORTH_EAST:
                        if (theirX >= myX + 1 && theirY >= myY + 1)
                            return npc;
                        break;
                    case EAST:
                        if (theirX > myX && theirY >= myY - 1 && theirY <= myY + 1)
                            return npc;
                        break;
                    case SOUTH_EAST:
                        if (theirY <= myY - 1 && theirX >= myX + 1)
                            return npc;
                        break;
                    case SOUTH:
                        if (theirY < myY && theirX >= myX - 1 && theirX <= myX + 1)
                            return npc;
                        break;
                    case SOUTH_WEST:
                        if (theirX <= myX - 1 && theirY <= myY - 1)
                            return npc;
                        break;
                    case WEST:
                        if (theirX < myX && theirY >= myY - 1 && theirY <= myY + 1)
                            return npc;
                        break;
                    case NORTH_WEST:
                        if (theirX <= myX - 1 && theirY >= myY + 1)
                            return npc;
                        break;
                }
            }
        }
        return null;
    }
 
    /**
     * Fires the cannonballs (well not really, it just makes it so that we
     * actually see cannonball projectiles)
     */
    private void cannonProjectile(Player player, Objects cannon, NPC n) {
        int oX = cannon.getPosition().getX();
        int oY = cannon.getPosition().getY();
        int offX = ((oX - n.absX) * -1);
        int offY = ((oY - n.absY) * -1);
 
        player.getPA().createPlayersProjectile(oX, oY, offY, offX, 50, 60, 53, 20, 20, -player.oldNpcIndex + 1, 30);
    }
 
    /**
     * Gets if the player is building or not based on what {@link Setup} stage
     * they are on.
     */
    public boolean isBulding() {
        return stage.ordinal() == 0 || stage.ordinal() == 5 ? false : true;
    }
 
    /**
     * Iterate through the list of active cannons and remove this players
     * cannon.
     */
    public void remove() {
        for (Iterator<MultiCannonObject> iter = cannons.iterator(); iter.hasNext();) {
            MultiCannonObject m = iter.next();
 
            if (m == null) {
                continue;
            }
 
            if (m.getOwner().playerName.equals(player.playerName)) {
                iter.remove();
            }
        }
    }
 
    /**
     * @return the cannons
     */
    public List<MultiCannonObject> getCannons() {
        return cannons;
    }
}