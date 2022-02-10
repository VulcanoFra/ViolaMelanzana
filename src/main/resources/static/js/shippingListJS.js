window.addEventListener("load", function(){
	localStorage.setItem("id_indirizzo", null);
	aggiungiEventi();
	controlloListaIndirizzi();
});

function aggiungiEventi(){
	var btnModifica = document.querySelectorAll(".btnModifica");

	for(let i = 0; i < btnModifica.length; ++i){
		btnModifica[i].addEventListener("click",function(e){
	        	localStorage.setItem("id_indirizzo", e.target.name);  
				window.location.href = '/shipping';
		});
	}
	
	btnNuovo.addEventListener("click",function(){
		localStorage.setItem("id_indirizzo", 0); 
		window.location.href = '/shipping';
	});
	
	
	var btnElimina = document.querySelectorAll(".btnElimina");

	for(let i = 0; i < btnModifica.length; ++i){
		btnElimina[i].addEventListener("click",function(e){
	        	$.ajax({
					type: "POST",
					url: "/deleteShipping",
					contentType: "application/json",
					data: JSON.stringify(e.target.name),
					success: function(risposta){
						console.log(risposta);
						if (risposta.status === "OK"){
							window.location.href = '/shippingList';
						}
					},
					error: function(xhr){
						console.log(xhr);
						var res = JSON.parse(xhr.responseText);
						alert(res.messaggio);
						window.location.href = '/';
					}
			});
		});
	}
	
	btnPaga.addEventListener("click",function(){
		var radiobuttonID = document.querySelector( 'input[name="indirizzi"]:checked');
			if(radiobuttonID != null) {   
        	$.ajax({
				type: "POST",
				url: "/changeStatusAddress",
				contentType: "application/json",
				data: JSON.stringify(Number(radiobuttonID.id)),
				success: function(risposta){
					console.log(risposta);
					if (risposta.status === "OK"){
						window.location.href = '/pay';
					}
				},
				error: function(xhr){
					console.log(xhr);
					var res = JSON.parse(xhr.responseText);
					alert(res.messaggio);
					window.location.href = '/';
				}
		});
 		}else {  
        	alert("Selezionare indirizzo");
		}
	});	
}

function controlloListaIndirizzi(){
	var element = document.getElementById("indirizzi");
	var numberOfChildren = element.getElementsByTagName("*").length;
	if(numberOfChildren === 0){
		document.getElementById("IndirizziAssenti").style.display = "block";
		document.getElementById("pulsantiSecondari").style.display = "none";
	}
}
