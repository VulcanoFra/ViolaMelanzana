window.addEventListener("load", function(){
	checkRules();
})


function checkRules(){
	var button = document.querySelector("#insertChef");
	
	button.addEventListener("click", function(e){
		e.preventDefault();
		
		var res = true;
		var regex = /^[a-zA-Z]+$/;
		
		var nome = $("#nome");
		var lblNome = document.querySelector("#lblNome");
		if(nome.val().length === 0){
			nome.addClass("makeRed");
			lblNome.innerHTML = "Devi inserire un nome";
			res = false;
		} else if(!regex.test(nome.val())){
			nome.addClass("makeRed");
			lblNome.innerHTML = "Ci sono caratteri non ammessi. Soltanto lettere sono consentite";
			res = false;
		} else{
			nome.removeClass("makeRed");
			lblNome.innerHTML = "";
		}
		
		
		var cognome = $("#cognome");
		var lblCognome = document.querySelector("#lblCognome");
		if(cognome.val().length === 0){
			cognome.addClass("makeRed");
			lblCognome.innerHTML = "Devi inserire un cognome";
			res = false;
		} else if(!regex.test(cognome.val())){
			cognome.addClass("makeRed");
			lblCognome.innerHTML = "Ci sono caratteri non ammessi. Soltanto lettere sono consentite";
			res = false;
		} else{
			cognome.removeClass("makeRed");
			lblCognome.innerHTML = "";
		}
		
		var descrizione = $("#descrizione");
		var lblDescrizione = document.querySelector("#lblDescrizione");
		if(descrizione.val().length === 0){
			descrizione.addClass("makeRed");
			lblDescrizione.innerHTML = "Devi inserire unna descrizione";
			res = false;
		} else if(descrizione.val().length > 350){
			descrizione.addClass("makeRed");
			lblDescrizione.innerHTML = "Descrizione troppo lunga. Al max 350 caratteri";
			res = false;
		} else{
			descrizione.removeClass("makeRed");
			lblDescrizione.innerHTML = "";
		}
		
		if(res){
			var form = document.querySelector("#formChef");
			form.submit();
		}
	})
}
