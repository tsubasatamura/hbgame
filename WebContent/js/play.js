function check(){
	if(document.game.youranswer.value==""){
		window.alert("数字を3桁入力してください。");
		return false;
	}else if(!document.game.youranswer.value.match(/^[0-9]*$/)){
		window.alert("数字以外が入力されています。数字を入力してください。");
		return false;
	}else if(!document.game.youranswer.value.match(/^[0-9]{3}$/)){
		window.alert("数字を「3桁」入力してください。");
		return false;
	}
}