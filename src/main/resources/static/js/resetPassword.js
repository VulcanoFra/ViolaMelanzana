window.addEventListener("load", function(){
	eventButton();
	eventoShowPassword();
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

function eventButton(){
	var button = document.querySelector("#btnChange");
	
	button.addEventListener("click", function(event){
		event.preventDefault();
		
		var success = true;
		
		let regexPassword = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])");

		var password = $("#password");
		var confirm = $("#confirmPassword");
		
		var lblPassword = document.querySelector("#info");
		var lblConfirm = document.querySelector("#infoConfirm");
		
		if(password.val().length < 8){
			password.addClass("makeRed");
			lblPassword.innerHTML = "Password troppo corta";
			success = false;
		}
		else if(password.val().length > 20){
			password.addClass("makeRed");
			lblPassword.innerHTML = "Password troppo lunga";
			success = false;
		}
		else if(!regexPassword.test(password.val())){
			password.addClass("makeRed");
			lblPassword.innerHTML = "La password deve contenere almeno una lettera minuscola, una maiuscola, un numero e $%@#!*^&";
			success = false;
		} else{
			password.removeClass("makeRed");
			lblPassword.innerHTML = "";
		}
		
		
		if(confirm.val().length < 8){
			confirm.addClass("makeRed");
			lblConfirm.innerHTML = "Password troppo corta";
			success = false;
		}
		else if(confirm.val().length > 20){
			confirm.addClass("makeRed");
			lblConfirm.innerHTML = "Password troppo lunga";
			success = false;
		}
		else if(!regexPassword.test(confirm.val())){
			confirm.addClass("makeRed");
			lblConfirm.innerHTML = "La password deve contenere almeno una lettera minuscola, una maiuscola, un numero e $%@#!*^&";
			success = false;
		} else{
			confirm.removeClass("makeRed");
			lblConfirm.innerHTML = "";
		}
		
		if(success){
			if(password.val() !== confirm.val()){
				password.addClass("makeRed");
				confirm.addClass("makeRed");
				lblPassword.innerHTML = "Password diverse";
				lblConfirm.innerHTML = "Password Diverse";
				success = false;
			} else{
				password.removeClass("makeRed");
				confirm.removeClass("makeRed");
				lblPassword.innerHTML = "";
				lblConfirm.innerHTML = "";
			}	
		}
		
		if(success){
			var form = document.querySelector("#formReset");
			form.submit();
		}
	})
}