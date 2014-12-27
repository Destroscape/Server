package server.model.players;

import server.model.players.DialogueHandler;

public class DialogueAnimation {

	public enum FacialAnimation {
		/**
		 * Dialogue animations.
		 */
		HAPPY(588), // - Joyful/happy
		CALM_1(589), // - Speakingly calmly
		CALM_2(590), // - Calm talk
		DEFAULT(591), // - Default speech
		EVIL(592), // - Evil
		EVIL_CONTINUED(593), // - Evil continued
		DELIGHTED_EVIL(594), // - Delighted evil
		ANNOYED(595), // - Annoyed
		DISTRESSED(596), // - Distressed
		DISTRESSED_CONTINUED(597), // - Distressed continued
		ALMOST_CRYING(598), // - Almost crying
		BOWS_HEAD_WHILE_SAD(599), // - Bows head while sad
		DRUNK_TO_LEFT(600), // - Talks and looks sleepy/drunk
		// to left
		DRUNK_TO_RIGHT(601), // - Talks and looks sleepy/drunk
		// to right
		DISINTERESTED(602), // - Sleepy or disinterested
		SLEEPY(603), // - Tipping head as if sleepy.
		PLAIN_EVIL(604), // - Plain evil
		// (Grits teeth and
		// moves eyebrows)
		LAUGH_1(605), // - Laughing or yawning
		LAUGH_2(606), // - Laughing or yawning for longer
		LAUGH_3(607), // - Laughing or yawning for longer
		LAUGH_4(608), // - Laughing or yawning
		EVIL_LAUGH(609), // - Evil laugh then plain evil
		SAD(610), // - Slightly sad
		MORE_SAD(611), // - Quite sad
		ON_ONE_HAND(612), // - On one hand...
		NEARLY_CRYING(613), // - Close to crying
		ANGER_1(614), // - Angry
		ANGER_2(615), // - Angry
		ANGER_3(616), // - Angry
		ANGER_4(617); // - Angry

		private int animation;

		private FacialAnimation(int animation) {
			this.animation = animation;
		}

		/**
		 * @return the animation
		 */
		public int getAnimationId() {
			return animation;
		}

	}
}