package game.content;

import game.Server;
import engine.event.*;
import game.entity.npc.NPC;
import game.entity.player.Player;
import game.object.Objects;
import engine.util.Misc;

public class DwarfMultiCannon {

	private final Player player;

	public DwarfMultiCannon(final Player client)
	{
		this.player = client;
	}

	private static final int CANNON_BASE = 7, CANNON_STAND = 8,
			CANNON_BARRELS = 9, CANNON = 6;
	private static final int CANNONBALL = 2, CANNON_BASE_ID = 6,
			CANNON_STAND_ID = 8, CANNON_BARRELS_ID = 10,
			CANNON_FURNACE_ID = 12;

	public void setUpCannon()
	{
		if (!this.canSetUpCannon())
		{
			System.out.println("First: " + inGoodArea() + " Second: " + (player.playerLevel[3] > 0) + "Third: " + player.hasCannon + " Four: " + player.settingUpCannon);
			return;
		}

		EventManager.getSingleton().addEvent(new Event()
		{

			int time = 4;

			@Override
			public void execute(final EventContainer setup)
			{
				/*if (!DwarfMultiCannon.this.canSetUpCannon())
				{
					System.out.println("Again debug : " + time);
					setup.stop();
				}*/
				switch (time)
				{
				case 4:
					if (!DwarfMultiCannon.this.player.getItems().playerHasItem(
							DwarfMultiCannon.CANNON_BASE_ID))
					{
						DwarfMultiCannon.this.player.settingUpCannon = false;
						setup.stop();
						return;
					}
					DwarfMultiCannon.this.player.hasCannon = true;
                    player.startAnimation(827);
					DwarfMultiCannon.this.player.settingUpCannon = true;
					DwarfMultiCannon.this.player.setUpBase = true;
					final Objects base = new Objects(
							DwarfMultiCannon.CANNON_BASE,
							DwarfMultiCannon.this.player.absX,
							DwarfMultiCannon.this.player.absY, 0, 0, 10, 0);
					Server.objectHandler.addObject(base);
					Server.objectHandler.placeObject(base);
					DwarfMultiCannon.this.player.oldCannon = base;
					DwarfMultiCannon.this.player.getItems().deleteItem(
							DwarfMultiCannon.CANNON_BASE_ID, 1);
					base.belongsTo = DwarfMultiCannon.this.player.playerName;
					break;

				case 3:
					if (!DwarfMultiCannon.this.player.getItems().playerHasItem(
							DwarfMultiCannon.CANNON_STAND_ID))
					{
						DwarfMultiCannon.this.player.settingUpCannon = false;
						setup.stop();
						return;
					}
                    			player.startAnimation(827);
					DwarfMultiCannon.this.player.setUpStand = true;
					final Objects stand = new Objects(
							DwarfMultiCannon.CANNON_STAND,
							DwarfMultiCannon.this.player.absX,
							DwarfMultiCannon.this.player.absY, 0, 0, 10, 0);
					Server.objectHandler.removeObject(DwarfMultiCannon.this.player.oldCannon);
				Server.objectHandler.addObject(stand);
					Server.objectHandler.placeObject(stand);
					DwarfMultiCannon.this.player.oldCannon = stand;
					DwarfMultiCannon.this.player.getItems().deleteItem(
							DwarfMultiCannon.CANNON_STAND_ID, 1);
					stand.belongsTo = DwarfMultiCannon.this.player.playerName;
					break;

				case 2:
					if (!DwarfMultiCannon.this.player.getItems().playerHasItem(
							DwarfMultiCannon.CANNON_BARRELS_ID))
					{
						DwarfMultiCannon.this.player.settingUpCannon = false;
						setup.stop();
						return;
					}
                    player.startAnimation(827);
					DwarfMultiCannon.this.player.setUpBarrels = true;
					final Objects barrel = new Objects(
							DwarfMultiCannon.CANNON_BARRELS,
							DwarfMultiCannon.this.player.absX,
							DwarfMultiCannon.this.player.absY, 0, 0, 10, 0);
					Server.objectHandler
							.removeObject(DwarfMultiCannon.this.player.oldCannon);
					Server.objectHandler.addObject(barrel);
					Server.objectHandler.placeObject(barrel);
					DwarfMultiCannon.this.player.oldCannon = barrel;
					DwarfMultiCannon.this.player.getItems().deleteItem(
							DwarfMultiCannon.CANNON_BARRELS_ID, 1);
					barrel.belongsTo = DwarfMultiCannon.this.player.playerName;
					break;

				case 1:
					if (!DwarfMultiCannon.this.player.getItems().playerHasItem(
							DwarfMultiCannon.CANNON_FURNACE_ID))
					{
						DwarfMultiCannon.this.player.settingUpCannon = false;
						setup.stop();
						return;
					}
                    player.startAnimation(827);
					DwarfMultiCannon.this.player.setUpFurnace = true;
					final Objects cannon = new Objects(
							DwarfMultiCannon.CANNON,
							DwarfMultiCannon.this.player.absX,
							DwarfMultiCannon.this.player.absY, 0, 0, 10, 0);
					DwarfMultiCannon.this.player.cannonBaseX = DwarfMultiCannon.this.player.absX;
					DwarfMultiCannon.this.player.cannonBaseY = DwarfMultiCannon.this.player.absY;
					DwarfMultiCannon.this.player.cannonBaseH = DwarfMultiCannon.this.player.heightLevel;
					Server.objectHandler
							.removeObject(DwarfMultiCannon.this.player.oldCannon);
					Server.objectHandler.addObject(cannon);
					Server.objectHandler.placeObject(cannon);
					DwarfMultiCannon.this.player.oldCannon = cannon;
					DwarfMultiCannon.this.player.getItems().deleteItem(
							DwarfMultiCannon.CANNON_FURNACE_ID, 1);
					cannon.belongsTo = DwarfMultiCannon.this.player.playerName;
					break;

				case 0:
					DwarfMultiCannon.this.player.settingUpCannon = false;
					setup.stop();
					break;
				}
				   if (this.time > 0)
	                {
	                    this.time--;
	                }
			}
		}, 1000); // changed from 2000 to try speed up the process
	}

	public void shootCannon()
	{
		Objects cannon = null;
		for (final Objects o : Server.objectHandler.globalObjects)
		{
			if (o.objectX == this.player.cannonBaseX
					&& o.objectY == this.player.cannonBaseY
					&& o.objectHeight == this.player.cannonBaseH)
			{
				cannon = o;
			}
		}
		if (cannon == null)
		{
			this.player.sendMessage("This is not your cannon!");
			return;
		}
		if (this.player.cannonIsShooting)
		{
			if (this.player.getItems().playerHasItem(
					DwarfMultiCannon.CANNONBALL))
			{
				final int amountOfCannonBalls = this.player.getItems()
						.getItemAmount(DwarfMultiCannon.CANNONBALL) > 30 ? 30
						: this.player.getItems().getItemAmount(
								DwarfMultiCannon.CANNONBALL);
				this.player.cannonBalls += amountOfCannonBalls;
			}
			else
			{
				this.player.sendMessage("Your cannon is already firing!");
				return;
			}
		}
		if (this.player.cannonBalls < 1) {
			final int amountOfCannonBalls = this.player.getItems()
					.getItemAmount(DwarfMultiCannon.CANNONBALL) > 30 ? 30
					: this.player.getItems().getItemAmount(
							DwarfMultiCannon.CANNONBALL);
			if (amountOfCannonBalls < 1) {
				this.player.sendMessage("You need ammo to shoot this cannon!");
				return;
			} else if(DwarfMultiCannon.this.player.cannonBalls > 30) {
				player.sendMessage("Your cannon is already full!");
			}
			this.player.cannonBalls = amountOfCannonBalls;
			this.player.getItems().deleteItem(
					DwarfMultiCannon.CANNONBALL,
					this.player.getItems().getItemSlot(
							DwarfMultiCannon.CANNONBALL), amountOfCannonBalls);
		}
		else
		{
			this.startFiringCannon(cannon);
		}
	}
	
	public void loadCannon() {
		if(DwarfMultiCannon.this.player.cannonBalls < 30 && this.player.getItems().playerHasItem(DwarfMultiCannon.CANNONBALL)) {
			final int amountOfCannonBalls = this.player.getItems().getItemAmount(DwarfMultiCannon.CANNONBALL) > 30 ? 30 : this.player.getItems().getItemAmount(DwarfMultiCannon.CANNONBALL);
			this.player.getItems().deleteItem(DwarfMultiCannon.CANNONBALL,
			this.player.getItems().getItemSlot(DwarfMultiCannon.CANNONBALL), amountOfCannonBalls);
			this.player.cannonBalls += amountOfCannonBalls;
			player.sendMessage("You load the cannon with " + amountOfCannonBalls + " cannonballs.");
		} else {
			player.sendMessage("Your cannon is already full!");
		}
	}

	private void startFiringCannon(final Objects cannon)
	{
        player.startAnimation(827);
		this.player.cannonIsShooting = true;
		EventManager.getSingleton().addEvent(new Event() {

			@Override
			public void execute(final EventContainer fire)
			{
				if (DwarfMultiCannon.this.player.cannonBalls < 1) {
					DwarfMultiCannon.this.player.sendMessage("Your cannon is out of ammo!");
					DwarfMultiCannon.this.player.cannonIsShooting = false;
					fire.stop();
				}
				else
				{
					DwarfMultiCannon.this.player.rotation++;
					DwarfMultiCannon.this.rotateCannon(cannon);
				}
			}
		}, this.player.inMulti() ? 800 : 2500 / 2);
	}

	private void rotateCannon(final Objects cannon)
	{
		switch (this.player.rotation)
		{
		case 1: // north
			this.player.getPA().objectAnim(cannon.objectX, cannon.objectY, 516,
					10, -1);
			break;
		case 2: // north-east
			this.player.getPA().objectAnim(cannon.objectX, cannon.objectY, 517,
					10, -1);
			break;
		case 3: // east
			this.player.getPA().objectAnim(cannon.objectX, cannon.objectY, 518,
					10, -1);
			break;
		case 4: // south-east
			this.player.getPA().objectAnim(cannon.objectX, cannon.objectY, 519,
					10, -1);
			break;
		case 5: // south
			this.player.getPA().objectAnim(cannon.objectX, cannon.objectY, 520,
					10, -1);
			break;
		case 6: // south-west
			this.player.getPA().objectAnim(cannon.objectX, cannon.objectY, 521,
					10, -1);
			break;
		case 7: // west
			this.player.getPA().objectAnim(cannon.objectX, cannon.objectY, 514,
					10, -1);
			break;
		case 8: // north-west
			this.player.getPA().objectAnim(cannon.objectX, cannon.objectY, 515,
					10, -1);
			this.player.rotation = 0;
			break;
		}
		this.shootNpc();
	}

	public void pickUpCannon()
	{
		Objects cannon = null;
		for (final Objects o : Server.objectHandler.globalObjects)
		{
			if (o.objectX == this.player.cannonBaseX
					&& o.objectY == this.player.cannonBaseY
					&& o.objectHeight == this.player.cannonBaseH)
			{
				cannon = o;
			}
		}
		if (cannon == null)
		{
			this.player.sendMessage("This is not your cannon!");
			return;
		}
        player.startAnimation(827);
		final Objects empty = new Objects(100, cannon.objectX,
				cannon.objectY, 0, 0, 10, 0);
		Server.objectHandler.addObject(empty);
		Server.objectHandler.placeObject(empty);
		Server.objectHandler.removeObject(empty);
		if (this.player.setUpBase)
		{
			if (this.player.getItems().freeSlots() > 0)
			{
				this.player.getItems().addItem(DwarfMultiCannon.CANNON_BASE_ID,
						1);
			}
			else
			{
				this.player.getItems().addItemToBank(
						DwarfMultiCannon.CANNON_BASE_ID, 1);
				this.player
						.sendMessage("You did not have enough inventory space, so this cannon part was banked.");
			}
			this.player.setUpBase = false;
		}
		if (this.player.setUpStand)
		{
			if (this.player.getItems().freeSlots() > 0)
			{
				this.player.getItems().addItem(
						DwarfMultiCannon.CANNON_STAND_ID, 1);
			}
			else
			{
				this.player.getItems().addItemToBank(
						DwarfMultiCannon.CANNON_STAND_ID, 1);
				this.player
						.sendMessage("You did not have enough inventory space, so this cannon part was banked.");
			}
			this.player.setUpStand = false;
		}
		if (this.player.setUpBarrels)
		{
			if (this.player.getItems().freeSlots() > 0)
			{
				this.player.getItems().addItem(
						DwarfMultiCannon.CANNON_BARRELS_ID, 1);
			}
			else
			{
				this.player.getItems().addItemToBank(
						DwarfMultiCannon.CANNON_BARRELS_ID, 1);
				this.player
						.sendMessage("You did not have enough inventory space, so this cannon part was banked.");
			}
			this.player.setUpBarrels = false;
		}
		if (this.player.setUpFurnace)
		{
			if (this.player.getItems().freeSlots() > 0)
			{
				this.player.getItems().addItem(
						DwarfMultiCannon.CANNON_FURNACE_ID, 1);
			}
			else
			{
				this.player.getItems().addItemToBank(
						DwarfMultiCannon.CANNON_FURNACE_ID, 1);
				this.player
					.sendMessage("You did not have enough inventory space, so this cannon part was banked.");
			}
			this.player.setUpFurnace = false;
		}
		if (this.player.cannonBalls > 0)
		{
			if (this.player.getItems().freeSlots() > 0)
			{
				this.player.getItems().addItem(DwarfMultiCannon.CANNONBALL,
						this.player.cannonBalls);
			}
			else
			{
				this.player.getItems().addItemToBank(
						DwarfMultiCannon.CANNONBALL, this.player.cannonBalls);
				this.player
						.sendMessage("You did not have enough inventory space, so your cannonballs have been banked.");
			}
			this.player.cannonBalls = 0;
		}
	}

	/**
	 * Rewrite the old way, was very ugly
	 */
	public void shootNpc()
	{
		final NPC n = player.getPA().getNpcWithinDistance(this.player, 8);
		if (n == null)
		{
			return;
		}
		final int damage = Misc.random(30);
		if (this.player.inMulti() && n.inMulti()) {
		DwarfMultiCannon.startCannonballProjectile(this.player, this.player.oldCannon, n);
		CycleEventHandler.getSingleton().addEvent(this.player, new CycleEvent() {
			@Override
		public void execute(CycleEventContainer container) {
			n.hitDiff = damage;
			n.HP -= damage;
			n.hitUpdateRequired = true;
			n.underAttack = true;
		
			container.stop();
		}
				@Override
				public void stop() {
					// TODO Auto-generated method stub
					
				}
			}, 2);
		n.facePlayer(this.player.playerId);
		n.killerId = this.player.playerId;
		}
		else
		{
			if (n.underAttackBy > 0 && n.underAttackBy != this.player.playerId)
			{
				return;
			}
			DwarfMultiCannon.startCannonballProjectile(this.player,
					this.player.oldCannon, n);
			CycleEventHandler.getSingleton().addEvent(this.player, new CycleEvent() {
				@Override
			public void execute(CycleEventContainer container) {
			n.hitDiff = damage;
			n.HP -= damage;
			n.hitUpdateRequired = true;
			n.underAttack = true;
			container.stop();
		}
				@Override
				public void stop() {
					// TODO Auto-generated method stub
					
				}
			}, 2);
			n.killerId = this.player.playerId;
			n.facePlayer(this.player.playerId);
		}
		player.getPA().addSkillXP(damage, 4);
		this.player.cannonBalls--;
	}

	private static void startCannonballProjectile(final Player player,
			final Objects cannon, final NPC n)
	{
		final int oX = cannon.objectX;
		final int oY = cannon.objectY;
		final int offX = (oX - n.getX()) * -1;
		final int offY = (oY - n.getY()) * -1;
        player.getPA().createPlayersProjectile(oX, oY, offY, offX, 50, 60, 53, 20, 20, - player.oldNpcIndex + 1, 30);
		//player.getPA().createPlayersProjectile(oX, oY, offY, offX, 50, 90, 53, 20, 20, - player.oldNpcIndex + 1, 30);
	}

	public static int distanceToSquare(final int x, final int y, final int tx,
			final int ty)
	{
		return (int) Math.sqrt(Math.abs(x - tx) + Math.abs(y - ty));
	}

	private boolean canSetUpCannon()
	{
		return this.inGoodArea() && this.player.playerLevel[3] > 0
				&& !this.player.hasCannon && !this.player.settingUpCannon;
	}
	

	private boolean inGoodArea()
	{
		return true;
	}
}