package game.content.clues;


public class CluescrollDefinition {
	
	public static int maxCS = 6000;
	public static CluescrollDefinition cs[] = new CluescrollDefinition[maxCS];
	
	public CluescrollDefinition(int ID, int X, int Y, String a, String b, String c, String d) {
		this.SetItemID(ID);
		this.SetCoordX(X);
		this.SetCoordY(Y);
		this.SetDes1(a);
		this.SetDes2(b);
		this.SetDes3(c);
		this.SetDes4(d);
	}
	
	int itemID;
	
	int CoordX;
	int CoordY;
	
	String Des1;
	String Des2;
	String Des3;
	String Des4;
	
	public int GetItemID() {
		return itemID;
	}
	
	public void SetItemID(int id) {
		itemID = id;
	}
	
	public int GetCoordX() {
		return CoordX;
	}
	
	public void SetCoordX(int X) {
		CoordX = X;
	}
	
	public int GetCoordY() {
		return CoordY;
	}
	
	public void SetCoordY(int Y) {
		CoordY = Y;
	}
	
	
	public String GetDes1() {
		return Des1;
	}
	
	public void SetDes1(String d1) {
		Des1 = d1;
	}
	
	public String GetDes2() {
		return Des2;
	}
	
	public void SetDes2(String d2) {
		Des2 = d2;
	}
	
	public String GetDes3() {
		return Des3;
	}
	
	public void SetDes3(String d3) {
		Des3 = d3;
	}
	
	public String GetDes4() {
		return Des4;
	}
	
	public void SetDes4(String d4) {
		Des4 = d4;
	}
	
	
}