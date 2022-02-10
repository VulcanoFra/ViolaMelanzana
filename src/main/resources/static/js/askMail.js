window.addEventListener("load", function(){
	eventButton();
})


function eventButton(){
	var button = $("#btnSend");
	var lblMessage = document.querySelector("#lblMessage");
	var email = $("#email");
	
	button.click(function(e){
		e.preventDefault();
	
		$.ajax({
			type: "POST",
			url: "/sendMailRecoverPassword",
			data: {
				email: email.val()
			}, 
			success: function(res){
				lblMessage.innerHTML = res;
			}
		})
		
	})
}