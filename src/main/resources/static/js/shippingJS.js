window.addEventListener("load", function(){
	getDati();
	eventiButtoni();
});

function getDati(){
	if(localStorage.getItem("id_indirizzo") != null && localStorage.getItem("id_indirizzo") != 0){
		$.ajax({
			type: "POST",
			url: "/getShipping",
			contentType: "application/json",
			data: JSON.stringify(localStorage.getItem("id_indirizzo")),
			success: function(risposta){
			console.log(risposta);
				if (risposta.status === "OK"){
					aggiungiIndirizzo(risposta.indirizzo);
				}
			},
			error: function(xhr){
				console.log(xhr);
				var res = JSON.parse(xhr.responseText);
				alert(res.messaggio);
				window.location.href = '/';
			}
		});
	}
}

function eventiButtoni(){

	btnIndietro.addEventListener("click", function(){
		window.location.href = '/shippingList';
	});
	
	
	btnSalva.addEventListener("click", function(){
		var n_civico = document.getElementById('n_civico');
		var cap = document.getElementById('cap');
		var tel = document.getElementById('telefono');
		
		if(!is_int(n_civico.value) || !is_int(cap.value) || !is_int(tel.value)){
			alert("Controllare valori numerici");
			return;
		}
		
		if(cap.value.length != 5){
			alert("CAP non corretto");
			return;
		}
		
		$.ajax({
			type: "POST",
			url: "/saveShipping",
			contentType: "application/json",
			data: JSON.stringify(getIndirizzo()),
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
			}
		});
	})
	
}

function getIndirizzo(){
	var id = localStorage.getItem("id_indirizzo");
	var indirizzo = document.getElementById("indirizzo").value;
	var n_civico = document.getElementById("n_civico").value;
	var cap = document.getElementById("cap").value;
	var citta = document.getElementById("citta").value;
	var provincia = document.getElementById("provincia").value;
	var telefono = document.getElementById("telefono").value;
	
	var indirizzo = new Indirizzo(id,indirizzo, n_civico, cap, citta, provincia, telefono, null, true);
	return indirizzo;
}

function aggiungiIndirizzo(indirizzo){
	document.getElementById("indirizzo").value = indirizzo.indirizzo;
	document.getElementById("n_civico").value = indirizzo.n_civico;
	document.getElementById("cap").value = indirizzo.cap;
	document.getElementById("citta").value = indirizzo.citta;
	document.getElementById("provincia").value = indirizzo.provincia;
	document.getElementById("telefono").value = indirizzo.telefono;
}

function is_numeric(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
}

function is_int(n){
	if (!is_numeric(n)) return false
	else return (n % 1 == 0);
}