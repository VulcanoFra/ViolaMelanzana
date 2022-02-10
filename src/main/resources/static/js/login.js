window.addEventListener("load", function(){
	eventoShowPassword();
	checkRules();
})

function eventoShowPassword(){
	var toggle = document.querySelector("#togglePassword");
	toggle.addEventListener("click", function(){
		var input = document.querySelector("#password");
		if (input.type === "password") {
			input.type = "text";
			this.classList.remove("fa-eye");
			this.classList.add("fa-eye-slash");
		} else {
		 	input.type = "password";
			this.classList.add("fa-eye");
			this.classList.remove("fa-eye-slash");
		}
	})
}

function checkRules(){
	var button = document.querySelector("#buttonLogin");
	button.addEventListener("click", function(event){
		event.preventDefault();
		var ok = true;
		
		let regexPassword = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])");
		var password = $("#password");
		
		var lblUsername = document.querySelector("#lblUsername");
		var lblPassword = document.querySelector("#lblPassword");
		
		if(password.val().length < 8){
			password.addClass("makeRed");
			lblPassword.innerHTML = "Password troppo corta";
			ok = false;
		}
		else if(password.val().length > 20){
			password.addClass("makeRed");
			lblPassword.innerHTML = "Password troppo lunga";
			ok = false;
		}
		else if(!regexPassword.test(password.val())){
			password.addClass("makeRed");
			lblPassword.innerHTML = "La password deve contenere almeno una lettera minuscola, una maiuscola, un numero e $%@#!*^&";
			ok = false;
		} else{
			password.removeClass("makeRed");
			lblPassword.innerHTML = "";
		}
		
		var regexUsername = /^[a-zA-Z0-9._-]+$/;
		var username = $("#username");
		if(username.val().length < 3){
			username.addClass("makeRed");
			lblUsername.innerHTML = "Username troppo corto";
			ok = false;
		} else if(username.val().length > 10){
			username.addClass("makeRed");
			lblUsername.innerHTML = "Username troppo lungo";
			ok = false;
		} else if(!regexUsername.test(username.val())){
			username.addClass("makeRed");
			lblUsername.innerHTML = "Lo username può avere soltanto lettere, numeri o (_-.)";
			ok = false;
		} else{
			username.removeClass("makeRed");
			lblUsername.innerHTML = "";
		}
			
		if(ok){
			var form = document.querySelector("#formLogin");
			form.submit();
		}
	})
}