package resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
public class Info implements Serializable{
	private static final long serialVersionUID=1L;
	private String yourAnswer;//入力された解答
	private int count;//ゲームを行った回数
	private int hit;//HIT数
	private int blow;//BLOW数
	private ArrayList<Info> info =new ArrayList<>();//結果を入れるList

	public Info(){
		super();
	}

	//コンストラクターで値をセットする
	public Info(String yourAnswer,int count,int hit,int blow){
		this.yourAnswer = yourAnswer;
		this.count = count;
		this.hit = hit;
		this.blow = blow;
	}

	public String getYourAnswer() {
		return yourAnswer;
	}

	public int getCount() {
		return count;
	}

	public int getHit() {
		return hit;
	}

	public int getBlow() {
		return blow;
	}

	public ArrayList<Info> getInfo() {
		return info;
	}

	public void setInfo(String yourAnswer,int count,int hit,int blow) {
		Info result =new Info(yourAnswer,count,hit,blow);

		this.info.add(result);
	}

	public static int[] correctAnswer(){
		int[] correctAnswer=new int[3];

		ArrayList<Integer> ans=new ArrayList<>();
		for(int i=0;i<10;i++){
			ans.add(i);
		}

		Collections.shuffle(ans);

		for(int i=0;i<correctAnswer.length;i++){
			correctAnswer[i]=ans.get(i);
		}

		return correctAnswer;
	}

	public static int[] yourAnswer(String yourAnswer){
		int[] arrayYourAnswer=new int[3];
		for(int i=0;i<arrayYourAnswer.length;i++){
			arrayYourAnswer[i]=Integer.valueOf(yourAnswer.substring(i,i+1));
		}
		return arrayYourAnswer;
	}

	public static int countHit(int[] correctAnswer,int[] yourAnswer){
		int hit=0;
		for(int i=0;i<correctAnswer.length;i++){
			if(correctAnswer[i]==yourAnswer[i]){
				hit++;
			}
		}
		return hit;
	}

	public static int countBlow(int[] correctAnswer,int[] yourAnswer){
		int blow=0;
		for(int i=0;i<correctAnswer.length;i++){
			for(int j=0;j<correctAnswer.length;j++){
				if(correctAnswer[i]==yourAnswer[j]){
					blow++;
				}
			}
		}
		//上記の計算だとHitした場合も含まれているのでHit数をマイナスする
		return blow-Info.countHit(correctAnswer, yourAnswer);
	}

}
