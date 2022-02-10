window.addEventListener("load", function(){
	controlloCarrelloVuoto();
	inserimentoDatiPrezzo();
	aggiungiEventi();
	
	window.addEventListener('resize', function(){
		
		var w = window.screen.width;
		var elem = document.getElementById("tabella_prodotti");
		
  		if( w <= 696) {
			elem.className = "table-responsive";	
		} else {
			elem.className = "cart-container prod-container";
		}
	});

});



function aggiungiEventi(){
	btnIndirizzo.addEventListener("click",function(){
	
		if(!controlloQuantita())
			return;

		let prodotto_quantita = new Map();
		var nomeProdotti_label = document.getElementsByClassName('nomeProdotti');
		var quantita_label = document.getElementsByClassName('quantita');
		
		for(let i = 0; i < nomeProdotti_label.length; ++i){		
			prodotto_quantita.set(nomeProdotti_label[i].textContent, parseInt(quantita_label[i].value));
		}
		
		if(!controlloQuantita() || !controlloNumerico())		
			return;
			
		
		
		const out = Object.create(null)
		prodotto_quantita.forEach((value, key) => {
			if(value instanceof Map){
				out[key] = map_to_object(value)
			}
			else{
				out[key] = value;
			}
		})
		
		console.log(prodotto_quantita);
		
		var prodotto_quantita_class = new ProdottoQuantita(out,localStorage.getItem("prezzo_totale"));
		$.ajax({
				type: "POST",
				url: "/changeStatus",
				contentType: "application/json",
				data: JSON.stringify(prodotto_quantita_class),
				success: function(risposta){
					console.log(risposta);
					if (risposta.status === "OK"){
						window.location.href = 'shippingList';
					}
				},
				error: function(xhr){
					console.log(xhr);
					var res = JSON.parse(xhr.responseText);
					alert(res.messaggio);
					//window.location.href = '/';
				}
			});
		});	

	var delete_btn = document.querySelectorAll(".delete_btn");
	
	for(let i = 0; i < delete_btn.length; ++i){
		delete_btn[i].addEventListener("click",function(e){
			var result = confirm("Conferma eliminazione del prodotto?");
			if(result){
				$.ajax({
					type: "POST",
					url: "/deleteProduct",
					data: {
						"nome_prodotto" : e.target.name
					},
					success: function(risposta){
						console.log(risposta);
						if (risposta.status === "OK"){
							//window.location.href = 'cart';
							document.querySelector("#tr_"+e.target.name).remove();
							controlloCarrelloVuoto();
							inserimentoDatiPrezzo();
						}
					},
					error: function(xhr){
						console.log(xhr);
						var res = JSON.parse(xhr.responseText);
						alert(res.messaggio);
						//window.location.href = '/';
					}
				});
			}
		});
	}
}



function controlloCarrelloVuoto(){
	var element = document.getElementById("cart_prodotti");
	var numberOfChildren = element.getElementsByTagName("*").length;
	if(numberOfChildren === 0){
		document.getElementById("carrelloVuoto").style.display = "block";
		document.getElementById("tabella_prodotti").style.display = "none";
		document.getElementById("card_paga").style.display = "none";
	}
}

function controlloQuantita(){
	var quantita_label = document.getElementsByClassName('quantita');
	var quantitaMax_label = document.getElementsByClassName('quantitaMax');
	var disponibile = true;
	for(i = 0; i < quantita_label.length; i++){
		if(Number(quantita_label[i].value) > Number(quantitaMax_label[i].textContent) || Number(quantitaMax_label[i].textContent) === 0){
			quantita_label[i].value = quantitaMax_label[i].textContent;
			alert("Quantità non disponibile in magazzino");
			disponibile = false;
		}
	}
	return disponibile;
}

function controlloNumerico(){
	var quantita_label = document.getElementsByClassName('quantita');
	for(i = 0; i < quantita_label.length; i++){
		if(!is_int(quantita_label[i].value)){
			quantita_label[i].value=0;
			alert("controllare valori numerici");
			return false;
		}
	}
	return true;
}

function is_numeric(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
}

function is_int(n){
	if (!is_numeric(n)) return false
	else return (n % 1 == 0);
}

function inserimentoDatiPrezzo(){

	controlloQuantita();
	controlloNumerico();
	
	var spedizione;
	var prezzo_label = document.getElementsByClassName('prezzo');
	var quantita_label = document.getElementsByClassName('quantita');
	var i, sum = 0, parsum = 0, total = 0;
	for(i = 0; i < prezzo_label.length; i++){
		prezzo = Number(prezzo_label[i].textContent);
		quantita = Number(quantita_label[i].value);
		parsum += prezzo*quantita;
	}
	
	sum = Number(parsum.toFixed(2));
	
	document.getElementById('subtotale').innerHTML = sum + ' \u20AC';
	
	if(sum > 29.99) {
		partotal = sum;
		document.getElementById('spedizione').innerHTML = "Gratuita";
	} else {
		spedizione = Number(3.50);
		partotal = sum + spedizione;
		document.getElementById('spedizione').innerHTML = spedizione + ' \u20AC';
	}
	total = Number(partotal.toFixed(2));
	document.getElementById('totale').innerHTML = total + ' \u20AC';
	
	localStorage.setItem("prezzo_totale", total);
}
