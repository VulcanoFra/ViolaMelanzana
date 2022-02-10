window.addEventListener("load", function(){
	checkRules();
	eventoShowPassword();
})

function checkRules(){
	var button = document.querySelector("#buttonRegister");
	
	button.addEventListener("click", function(event){
		event.preventDefault();
		var ok = true;
	
		var nome = $("#nome");
		var lblNome = document.querySelector("#lblNome");
		var regex = /^[a-zA-Z]+$/;
		if(nome.val().length < 2){
			nome.addClass("makeRed");
			lblNome.innerHTML = "Nome troppo corto";
			ok = false;
		} else if(nome.val().length > 30){
			nome.addClass("makeRed");
			lblNome.innerHTML = "Nome troppo lungo";
			ok = false;
		} else if(!regex.test(nome.val())){
			nome.addClass("makeRed");
			lblNome.innerHTML = "Sono ammesse soltanto lettere";
			ok = false;
		} else{
			nome.removeClass("makeRed");
			lblNome.innerHTML = "";
		}
		
		var cognome = $("#cognome");
		var lblCognome = document.querySelector("#lblCognome");
		var regex = /^[a-zA-Z]+$/;
		if(cognome.val().length < 2){
			cognome.addClass("makeRed");
			lblCognome.innerHTML = "Cognome troppo corto";
			ok = false;
		} else if(cognome.val().length > 30){
			cognome.addClass("makeRed");
			lblCognome.innerHTML = "Cognome troppo lungo";
			ok = false;
		} else if(!regex.test(cognome.val())){
			cognome.addClass("makeRed");
			lblCognome.innerHTML = "Sono ammesse soltanto lettere";
			ok = false;
		} else{
			cognome.removeClass("makeRed");
			lblCognome.innerHTML = "";
		}
		
		var mail = $("#mail");
		var lblMail = document.querySelector("#lblMail");
		if(mail.val().length === 0){
			lblMail.innerHTML = "Inserisci una email";
			ok = false;
			mail.addClass("makeRed");
		} else{
			mail.removeClass("makeRed");
			lblMail.innerHTML = "";
		}
		
		var username = $("#username");
		var lblUsername = document.querySelector("#lblUsername");
		var regexUsername = /^[a-zA-Z0-9._-]+$/
		if(username.val().length < 4){
			username.addClass("makeRed");
			lblUsername.innerHTML = "Username troppo corto";
			ok = false;
		} else if(username.val().length > 20){
			username.addClass("makeRed");
			lblUsername.innerHTML = "Username troppo lungo";
			ok = false;
		} else if(!regexUsername.test(username.val())){
			username.addClass("makeRed");
			lblUsername.innerHTML = "Lo Username può avere solo lettere, numeri o (_-.)";
			ok = false;
		} else{
			username.removeClass("makeRed");
			lblUsername.innerHTML = "";
		}
		
		let regexPassword = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])");
		var password = $("#password");
		var lblPassword = document.querySelector("#lblPassword");
		if(password.val().length < 8 ){
			password.addClass("makeRed");
			lblPassword.innerHTML = "Password troppo corta";
			ok = false;
		} else if(password.val().length > 20){
			password.addClass("makeRed");
			lblPassword.innerHTML = "Password troppo lunga";
			ok = false;
		} else if(!regexPassword.test(password.val())){
			password.addClass("makeRed");
			lblPassword.innerHTML = "La password deve contenere almeno una lettera minuscola, una maiuscola, un numero e $%@#!*^&";
			ok = false;
		} else{
			password.removeClass("makeRed");
			lblPassword.innerHTML = "";
		}
		
		var confirmPassword = $("#confirmPassword");
		var lblConfirmPassword = document.querySelector("#lblConfirmPassword");
		if(confirmPassword.val().length < 8 ){
			confirmPassword.addClass("makeRed");
			lblConfirmPassword.innerHTML = "Password troppo corta";
			ok = false;
		} else if(confirmPassword.val().length > 20){
			confirmPassword.addClass("makeRed");
			lblConfirmPassword.innerHTML = "Password troppo lunga";
			ok = false;
		} else if(!regexPassword.test(confirmPassword.val())){
			confirmPassword.addClass("makeRed");
			lblConfirmPassword.innerHTML = "La password deve contenere almeno una lettera minuscola, una maiuscola, un numero e $%@#!*^&";
			ok = false;
		} else{
			confirmPassword.removeClass("makeRed");
			lblConfirmPassword.innerHTML = "";
		}
		
		
		if(ok){
			if(password.val() !== confirmPassword.val()){
				lblPassword.innerHTML = "Password diverse";
				lblConfirmPassword.innerHTML = "Password diverse";
				password.addClass("makeRed");
				confirmPassword.addClass("makeRed");
				ok = false;
			} else{
				password.removeClass("makeRed");
				confirmPassword.removeClass("makeRed");
			}
		}
		
		
		if(ok){
			var formRegister = document.querySelector("#formRegister");
			formRegister.submit();
		}
	})
}

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
	
	var toggleConfirm = document.querySelector("#toggleConfirmPassword");
	toggleConfirm.addEventListener("click", function(){
		var input = document.querySelector("#confirmPassword");
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
