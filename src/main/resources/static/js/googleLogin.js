//QUELLO GIUSTO. FUNZIONA CON IL /googleSignIn del SignUpController.
/*function onSignIn(googleUser) {
	
	var f = $("#formGoogleLogin");
	
	var profilo = googleUser.getBasicProfile();
	console.log(profilo);
		
	var usernameField = $("#usernameGoogle");
	usernameField.val(profilo.getGivenName()) ;
	
	var mail = $("#mailGoogle");
	mail.val(profilo.getEmail());
	
	var nome = $("#nomeGoogle");
	nome.val(profilo.getGivenName());
	
	var cognome = $("#cognomeGoogle");
	cognome.val(profilo.getFamilyName());

    googleUser.disconnect()

    f.submit();
	$.ajax({
		type: "POST",
		url: "googleSignIn",
		contentType: "application/json",
		data: JSON.stringify(u),	
		success: function(risposta){
			console.log(risposta);
			if(risposta.status === "OK"){
				googleUser.disconnect();
			}
		}
	})
}*/

//GIUSTO CON AJAX. Usa ajax e va col /googleSignIn del LoginController
function onSignIn(googleUser) {
	var profilo = googleUser.getBasicProfile();
	
	$.ajax({
		type: "POST",
		url: "/googleSignIn",
		data:{
			mail:profilo.getEmail(),
			nome:profilo.getGivenName(),
			cognome:profilo.getFamilyName()
		},	
		success: function(risposta){
			console.log(risposta);
			if(risposta === "OK"){
				googleUser.disconnect();
				window.location.href = '/';
			} else{
				alert("Qualcosa è andato storto, riprova");
			}
		},
		error: function(xhr){
			alert(xhr.responseJSON.message);
			googleUser.disconnect();
			window.location.href = 'loginPage';
		}
	})
}