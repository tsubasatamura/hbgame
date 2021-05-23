package resource;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HBcontroller
 */
@WebServlet("/HBcontroller")
public class HBcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int count = 0;//回数
	Info info=new Info();//回数や入力情報などの情報を持ったBean
	int[] correctAnswer=Info.correctAnswer();//CPUが3桁の答えを設定



    /**
     * @see HttpServlet#HttpServlet()
     */
    public HBcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//リセットボタンを押されたら下のパラメータに"reset"が入る
		String reset=request.getParameter("reset");
		String yourAnswerstr=request.getParameter("youranswer");

		//リセットボタンを押されたら回数、CPUの答え、入力情報などを初期化
		if(Objects.equals(reset,"reset")){
			count=0;
			correctAnswer=Info.correctAnswer();
			info=new Info();
		}else{
			int[] yourAnswer=Info.yourAnswer(yourAnswerstr);//入力された答えをint型の配列に変換
			count++;//ゲーム回数をプラス
			int hit=Info.countHit(correctAnswer, yourAnswer);//Hit数をカウント
			int blow=Info.countBlow(correctAnswer, yourAnswer);//Blow数をカウント

			info.setInfo(yourAnswerstr,count,hit,blow);//上記の情報をArrayListにセットする
		}

		List<Info> result=info.getInfo();//ArrayListに入っている今までの結果をListに入れる

		HttpSession session=request.getSession();
		session.setAttribute("result", result);

		RequestDispatcher rd=request.getRequestDispatcher("/play.jsp");
		rd.forward(request, response);
	}

}
