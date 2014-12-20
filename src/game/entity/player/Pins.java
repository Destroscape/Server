package game.entity.player;

import engine.util.Misc;

public class Pins {

	private enum State {
		ONE,
		TWO,
		THREE,
		FOUR,
	}

	private State state = State.ONE;

	private String enteredPin = "";

	private String fullPin = "";
	
	private Player client;
	
	public Pins(Player client) {
		this.client = client;
	}

	public void reset() {
		client.bankPin = "";
		client.attempts = 3;
		enteredPin = "";
		fullPin = "";
	}

	public void close() {
		enteredPin = "";
		client.getPA().removeAllWindows();
		state = State.ONE;
	}

	public void open() {
		if (!(fullPin.equalsIgnoreCase(""))) {
			client.getPA().openUpBank(0);
			return;
		}
			if(!client.setPin) {
				client.sendMessage("@red@Please set a bank PIN");
			}
		client.getPA().showInterface(7424);
		client.getPA().sendFrame126("SWE Prime Security", 14923);
		client.getPA().sendFrame126("?", 14913);
		client.getPA().sendFrame126("?", 14914);
		client.getPA().sendFrame126("?", 14915);
		client.getPA().sendFrame126("?", 14916);
		resend();
		state = State.ONE;
	}

	private void resend() {
		if (!(fullPin.equalsIgnoreCase(""))) {
			client.getPA().openUpBank(0);
			return;
		}
		mixNumbers();
		switch(state) {
		case ONE:
			client.getPA().sendFrame126("First click the FIRST digit", 15313);
			break;
		case TWO:
			client.getPA().sendFrame126("Then click the SECOND digit", 15313);
			client.getPA().sendFrame126("*", 14913);
			break;
		case THREE:
			client.getPA().sendFrame126("Then click the THIRD digit", 15313);
			client.getPA().sendFrame126("*", 14914);
			break;
		case FOUR:
			client.getPA().sendFrame126("And lastly click the FOURTH digit", 15313);
			client.getPA().sendFrame126("*", 14915);
			break;
		}
		sendPins();
	}

	public void pinEnter(int button) {
		switch(state) {
		case ONE:
		case TWO:
		case THREE:
		case FOUR:
			enterPin(button, state);
			break;
		}
	}

	private void enterPin(int button, State which) {
		for(int i = 0; i < getActionButtons().length; i++) {
			if(getActionButtons()[i] == button) {
				enteredPin += getBankPins()[i]+"";
			}
		}
		switch(which) {
		case ONE:
			state = State.TWO;
			resend();
			break;
		case TWO:
			state = State.THREE;
			resend();
			break;
		case THREE:
			state = State.FOUR;
			resend();
			break;
		case FOUR:
			if(!client.setPin) {
				client.sendMessage("@red@You have successfully set your bank PIN");
				client.bankPin = enteredPin.trim();
				client.sendMessage("@red@Your PIN is now "+ client.bankPin +" write it up to remember it");
				fullPin = enteredPin.trim();
				client.setPin = true;
				resend();
			} else {
				if(client.bankPin.equalsIgnoreCase(enteredPin.trim())) {
					client.sendMessage("@red@You have successfully entered your bank PIN");
					fullPin = enteredPin.trim();
					resend();
				} else {
					client.sendMessage("@red@The pin you entered is incorrect");
					close();
				}
			}
			state = State.ONE;
			break;
		}
	}

	private void sendPins() {
		if (!(fullPin.equalsIgnoreCase(""))) {
			client.getPA().openUpBank(0);
			return;
		}
		for(int i = 0; i < getBankPins().length; i++) {
			client.getPA().sendFrame126(""+getBankPins()[i], 14883+i);
		}
	}

	private void mixNumbers() {
		for(int i = 0; i < bankPins.length; i++) {
			bankPins[i] = -1;
		}
		for(int i = 0; i < bankPins.length; i++) {
			for(int i2 = 0; i2 < 9999; i2++) {
				boolean can = true;
				int random = Misc.random(9);
				for(int i3 = 0; i3 < bankPins.length; i3++) {
					if(random == bankPins[i3]) {
						can = false;
						random = Misc.random(9);
					}
				}
				if(!can) {
					continue;
				} else {
					bankPins[i] = random;
					break;
				}
			}
		}
		sendPins();
	}

	private int[] getBankPins() {
		return bankPins;
	}

	private int[] getActionButtons() {
		return actionButtons;
	}


	private int bankPins[] = {
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1
	};

	private int actionButtons[] = { 
		58025, 58026, 58027, 58028, 
		58029, 58030, 58031, 58032, 
		58033, 58034
	};

	public String getFullPin() {
		return fullPin;
	}
}