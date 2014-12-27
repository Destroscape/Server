package game.net.packets.clickobject.areas;

import game.entity.Animation;
import game.entity.player.Player;

public class Stronghold {

	private static int UNLOCKDOOR = 412;

	public static void handleObjects(final Player c, final int objectId, final int x, final int y, final int face) {
		switch(objectId) {
		case 16124:
			if(c.absX == 1859 && c.absY == 5239) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1859, 5238, 0);
			} else if(c.absX == 1859 && c.absY == 5238) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1859, 5239, 0);
			} else if(c.absX == 1859 && c.absY == 5236) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1859, 5235, 0);
			} else if(c.absX == 1859 && c.absY == 5235) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1859, 5236, 0);
			} else if(c.absX == 1864 && c.absY == 5227) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1865, 5227, 0);
			} else if(c.absX == 1865 && c.absY == 5227) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1864, 5227, 0);
			} else if(c.absX == 1867 && c.absY == 5227) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1868, 5227, 0);
			} else if(c.absX == 1868 && c.absY == 5227) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1867, 5227, 0);
			} else if(c.absX == 1875 && c.absY == 5240) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1876, 5240, 0);
			} else if(c.absX == 1876 && c.absY == 5240) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1875, 5240, 0);
			} else if(c.absX == 1878 && c.absY == 5240) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1879, 5240, 0);
			} else if(c.absX == 1879 && c.absY == 5240) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1878, 5240, 0);
			} else if(c.absX == 1883 && c.absY == 5244) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1884, 5244, 0);
			} else if(c.absX == 1884 && c.absY == 5244) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1883, 5244, 0);
			} else if(c.absX == 1886 && c.absY == 5244) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1887, 5244, 0);
			} else if(c.absX == 1887 && c.absY == 5244) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1886, 5244, 0);
			} else if(c.absX == 1903 && c.absY == 5243) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1904, 5243, 0);
			} else if(c.absX == 1904 && c.absY == 5243) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1903, 5243, 0);
			} else if(c.absX == 1907 && c.absY == 5243) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1908, 5243, 0);
			} else if(c.absX == 1908 && c.absY == 5243) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1907, 5243, 0);
			} else if(c.absX == 1905 && c.absY == 5234) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1905, 5233, 0);
			} else if(c.absX == 1905 && c.absY == 5233) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1905, 5234, 0);
			} else if(c.absX == 1905 && c.absY == 5231) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1905, 5230, 0);
			} else if(c.absX == 1905 && c.absY == 5230) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1905, 5231, 0);
			}


			else if(c.absX == 1866 && c.absY == 5218) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1867, 5218, 0);
			} else if(c.absX == 1867 && c.absY == 5218) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1866, 5218, 0);
			} else if(c.absX == 1869 && c.absY == 5218) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1870, 5218, 0);
			} else if(c.absX == 1870 && c.absY == 5218) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1869, 5218, 0);
			} else if(c.absX == 1889 && c.absY == 5236) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1888, 5236, 0);
			} else if(c.absX == 1889 && c.absY == 5236) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1888, 5236, 0);
			} else if(c.absX == 1888 && c.absY == 5236) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1889, 5236, 0);
			} else if(c.absX == 1886 && c.absY == 5236) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1885, 5236, 0);
			} else if(c.absX == 1885 && c.absY == 5236) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1886, 5236, 0);
			} else if(c.absX == 1878 && c.absY == 5226) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1878, 5225, 0);
			} else if(c.absX == 1878 && c.absY == 5225) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1878, 5226, 0);
			} else if(c.absX == 1879 && c.absY == 5223) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1879, 5222, 0);
			} else if(c.absX == 1879 && c.absY == 5222) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1879, 5223, 0);
			} else if(c.absX == 1875 && c.absY == 5208) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1875, 5207, 0);
			} else if(c.absX == 1875 && c.absY == 5207) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1875, 5208, 0);
			}

			else if(c.absX == 1861 && c.absY == 5213) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1860, 5213, 0);
			} else if(c.absX == 1860 && c.absY == 5213) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1861, 5213, 0);
			} else if(c.absX == 1861 && c.absY == 5210) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1861, 5209, 0);
			} else if(c.absX == 1861 && c.absY == 5209) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1861, 5210, 0);
			}
			break;

		case 16123:
			if(c.absX == 1858 && c.absY == 5239) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1858, 5238, 0);
			} else if (c.absX == 1858 && c.absY == 5238) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1858, 5239, 0);
			} else if (c.absX == 1858 && c.absY == 5236) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1858, 5235, 0);
			} else if(c.absX == 1858 && c.absY == 5235) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1858, 5236, 0);
			} else if(c.absX == 1864 && c.absY == 5226) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1865, 5226, 0);
			} else if(c.absX == 1865 && c.absY == 5226) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1864, 5226, 0);
			} else if(c.absX == 1867 && c.absY == 5226) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1868, 5226, 0);
			} else if(c.absX == 1868 && c.absY == 5226) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1867, 5226, 0);
			} else if(c.absX == 1875 && c.absY == 5239) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1876, 5239, 0);
			} else if(c.absX == 1876 && c.absY == 5239) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1875, 5239, 0);
			} else if(c.absX == 1878 && c.absY == 5239) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1879, 5239, 0);
			} else if(c.absX == 1879 && c.absY == 5239) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1878, 5239, 0);
			} else if(c.absX == 1883 && c.absY == 5243) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1884, 5243, 0);
			} else if(c.absX == 1884 && c.absY == 5243) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1883, 5243, 0);
			} else if(c.absX == 1886 && c.absY == 5243) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1887, 5243, 0);
			} else if(c.absX == 1887 && c.absY == 5243) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1886, 5243, 0);
			} else if(c.absX == 1903 && c.absY == 5242) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1904, 5242, 0);
			} else if(c.absX == 1904 && c.absY == 5242) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1903, 5242, 0);
			} else if(c.absX == 1907 && c.absY == 5242) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1908, 5242, 0);
			} else if(c.absX == 1908 && c.absY == 5242) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1907, 5242, 0);
			} else if(c.absX == 1904 && c.absY == 5234) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1904, 5233, 0);
			} else if(c.absX == 1904 && c.absY == 5233) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1904, 5234, 0);
			} else if(c.absX == 1904 && c.absY == 5231) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1904, 5230, 0);
			} else if(c.absX == 1904 && c.absY == 5230) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1904, 5231, 0);
			}

			else if(c.absX == 1866 && c.absY == 5217) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1867, 5217, 0);
			} else if(c.absX == 1867 && c.absY == 5217) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1866, 5217, 0);
			} else if(c.absX == 1869 && c.absY == 5217) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1870, 5217, 0);
			} else if(c.absX == 1870 && c.absY == 5217) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1869, 5217, 0);
			} else if(c.absX == 1889 && c.absY == 5235) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1888, 5235, 0);
			} else if(c.absX == 1888 && c.absY == 5235) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1889, 5235, 0);
			} else if(c.absX == 1886 && c.absY == 5235) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1885, 5235, 0);
			} else if(c.absX == 1885 && c.absY == 5235) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1886, 5235, 0);
			} else if(c.absX == 1879 && c.absY == 5226) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1879, 5225, 0);
			} else if(c.absX == 1879 && c.absY == 5225) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1879, 5226, 0);
			} else if(c.absX == 1878 && c.absY == 5223) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1878, 5222, 0);
			} else if(c.absX == 1878 && c.absY == 5222) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1878, 5223, 0);
			} else if(c.absX == 1874 && c.absY == 5208) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1874, 5207, 0);
			} else if(c.absX == 1874 && c.absY == 5207) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1874, 5208, 0);
			}

			else if(c.absX == 1860 && c.absY == 5213) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1860, 5211, 0);
			} else if(c.absX == 1860 && c.absY == 5211) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1860, 5213, 0);
			} else if(c.absX == 1860 && c.absY == 5210) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1860, 5209, 0);
			} else if(c.absX == 1860 && c.absY == 5209) {
				c.setAnimation(Animation.create(UNLOCKDOOR));
				c.getPA().movePlayer(1860, 5210, 0);
			}
			break;
		}
	}

}
