package Data;

public class Ingredient {
	private int nType;
	private int nId;
	private String name;
	private int nCheck;
	private int nFavorite;
	private String str;
	 
	public Ingredient() {
		nType=0;
		nId=0;
		name="";
		nCheck=0;
		nFavorite=0;
		str="";
	}
	public Ingredient(int type, int id, String name,int check,int favor,String Str) {
		nType=type;
		nId=id;
		this.name=name;
		nCheck=check;
		nFavorite=favor;
		str=Str;
	}
	
	public Ingredient(Ingredient data) {
		nType=data.getType();
		nId=data.getId();
		name=data.getName();
		nCheck=data.getCheck();
		nFavorite=data.getFavorite();
	}
	
	
	//get,set
	public int getType() {return nType;}
	public int  getId() {return nId;	}
	public String  getName() {return name;	}
	public int  getCheck() {return nCheck;	}
	public int  getFavorite() {return nFavorite;	}
	
	public void setString(String Str) {str=Str;}
	public void setType(int type) {nType=type;}
	public void setId(int id) {nId=id;}
	public void setName(String name) {this.name=name;}
	public void setCheck(int check) {nCheck=check;}
	public void setFavorite(int favorite) {nFavorite=favorite;}
	public void setData(int type,int id,String name,int check,int favorite) {
		nType=type;
		nId=id;
		this.name=name;
		nCheck=check;
		nFavorite=favorite;
	}
	//3
	
	
	//4
	public String toString() {
		String str="종류: "+nType+" id: "+nId+" name: "+name+" 체크: "+nCheck+" 즐겨찾기: "+nFavorite;
		return str;
	}
}
