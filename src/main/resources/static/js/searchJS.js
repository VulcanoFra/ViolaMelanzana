function remove(ricetta, chef) {
	
	$.ajax({
		type: "GET",
		url: "/removeRecipe",
		data: {
			"recipe" : ricetta,
			"chef" : chef
		},
		
		success: function(risposta) {
			if (risposta.status === "Auth") {
				var elem = document.getElementById("recipe-"+ ricetta);
				elem.innerHTML = "";
			} else {
				alert("Siamo spiacenti. " + risposta.messaggio);
			}	
		}
	});
}

