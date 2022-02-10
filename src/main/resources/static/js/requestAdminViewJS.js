var selectedUserMail = null;

var msga = "'Sei sicuro di voler ACCETTARE questa richesta?'";
var msgr = "'Sei sicuro di voler RIFIUTARE questa richesta?'";
var msgb = "'Sei sicuro di voler RIMUOVERE questa ricetta?'"
var rowId= null;


function addClickOnRow(mail_utente,i) {
	$("#rowUser_" + i).click(function() {
		document.getElementById("userReqTable").style.display="block";
		document.getElementById("banTable").style.display="none";
		clearUsersTable();
		selectedUserMail = mail_utente;
		rowId=i;
		getRequest();
	});

}

function addClickOnRequest(id_ricetta) {
		    $('.modal-content').empty();
			modalRecipe (id_ricetta);
			$('#modalRecipe').modal();

}


function modalRecipe(id_ricetta){
	$.ajax({

		url: "/getRecipe",
		contentType: "application/json",
		type: "post",
		data: JSON.stringify(id_ricetta),
		dataType: "json",

	}).done(function(ricetta) {

			$(".modal-content").append ($(
					'<div class="modal-header">' +
						'<h1>Inserisci la tua ricetta</h1>'+
						'<span class="close" id="closeModal" data-dismiss="modal">&times;</span>'+
					'</div>'+
					'<div class="modal-body">'+
						'<div class="line">'+
							'<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>'+
							'<label>Titolo: </label>'+
							'<text name="testo" >'+ ricetta.titolo + '</text>'+
						'</div>'+
						
						'<div class="line">'+
							'<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>'+	
							'<label>Descrizione</label>'+
							'<textarea rows="6" cols="90" id="descrizione">'+ ricetta.descrizione + '</textarea>'+
							'<label class="label lbl-warning" for="descrizione">Max 5000 characters</label>'+	
						'</div>'+	
						
					//	'<div class="line">'+
					//		'<ul>' +
					//			'<c:forEach items="${"' + ricetta.listaIngredientiConQuantita + '} var="ingrediente">' +
					//				'<li class="ing">${' + ingrediente.ingrediente.nome + '}' + '${' + ingrediente.quantita + '}</li>'+
					//			'</c:forEach>' +
					//		'</ul>' +
						
					//	'</div>'+
						'<div class="line">'+
							'<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>'+
							'<label>Preparazione</label>'+
							'<textarea rows="6" cols="90" id="preparazione" >'+ ricetta.preparazione+ '</textarea>'+
							'<label class="label lbl-warning" for="preparazione">Max 10000 characters</label>'+			
						'</div>'+
						
						'<div class="line">'+
							'<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>'+	
							'<label>Consiglio</label>'+
							'<textarea rows="6" cols="90" id="descrizione">'+ ricetta.consiglio+ '</textarea>'+
							'<label class="label lbl-warning" for="descrizione">Max 1000 characters</label>'+	
						'</div>'+
							
							'<div class="line">'+
								'<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>'+	
								'<label>Curiosità</label>'+
								'<textarea rows="6" cols="90" id="descrizione">'+ ricetta.curiosita + '</textarea>'+
								'<label class="label lbl-warning" for="descrizione">Max 1000 characters</label>'+	
							'</div>'+	
						
						'<div class="line">'+
							'<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>'+
							'<label>Tempo preprazione:&nbsp</label>'+
							'<text name="testo" >' + ricetta.tempoPreparazione + " minuti" + '</text>'+
						'</div>'+
						
						
						
						'<div class="line">'+
							'<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>'+
							'<label>Tempo cottura:&nbsp</label>'+
							'<text name="testo" >'+ ricetta.tempoCottura + " minuti" + '</text>'+
						'</div>'+
						
						'<div class="line">'+
							'<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>'+
							'<label>Dosi:&nbsp</label>'+
							'<text name="testo" >'+ ricetta.dosi + '</text>'+
						'</div>'+
						'<div class="line">'+
							'<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>'+
							'<label>Difficoltà:&nbsp</label>'+
							'<text name="testo" >'+ ricetta.difficolta + '</text>'+
						'</div>'+
					'<div class="row">'+
						'<div class="col-sm-10">'+
							'<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>'+
							'<label>Immagine</label>'+
							'<img class="recipeImage" src= "'+ ricetta.base64Image + '"> </img>'+
						'</div>'+
						'<div class="col-sm-2">'+
							'<label>Video</label>'+
							'<iframe src="https://www.youtube.com/embed/' + ricetta.video + '"> </iframe>'+
						'</div>'+
					'</div>'+
					'</div>'+
				'</div>'));
			});
}

function clearUsersTable() {
	$("#requestTableView").empty();
}

function getRequest() {
	if (selectedUserMail == null) {
		return;
	}
	else {
		$('#user').empty();
		$('#user').append($('<p class="text-primary m-0 fw-bold" id="user">Richieste di: '+selectedUserMail + ' </p>'));
	}
		
	$.ajax({

		url: "/getRequestForUser",
		contentType: "application/json",
		type: "post",
		data: JSON.stringify(selectedUserMail),
		dataType: "json",

	}).done(function(selectedUserRequest) {
			
			if (selectedUserRequest.length != 0) {
				for (var j = 0; j < selectedUserRequest.length; j++) {
					if (selectedUserRequest[j][0] != "promozione a master" && selectedUserRequest[j][0] != "declassare utente master"){
						var request= "'" + selectedUserRequest[j][2] + "','" + selectedUserRequest[j][0] + "'" ;
						$('#requestTableView').append($('<tr class="shadow-sm p-3 mb-5" id="row_' +selectedUserRequest[j][2] + '">'+
						'<td onClick="addClickOnRequest(' + selectedUserRequest[j][2] + ')">' + selectedUserRequest[j][0] + '</td>'+
						'<td onClick="addClickOnRequest(' + selectedUserRequest[j][2] + ')">' + selectedUserRequest[j][1] + '</td>'+
						'<td onClick="addClickOnRequest(' + selectedUserRequest[j][2] + ')">' + selectedUserRequest[j][2] + '</td>'+
						'<td onClick="addClickOnRequest(' + selectedUserRequest[j][2] + ')">' + selectedUserRequest[j][3] + '</td>'+
						'<td>'+
							'<button id="accept" name="accept" onClick="acceptRequest(' + request + ')">Accetta</button>'+
						'</td>'+
						'<td>'+
							'<button id="refuse" name="refuse" onClick="refuseRequest(' + request + ')">Rifiuta</button>'+
						'</td>'+
						'</tr>'));
					}
				
					else{
							var request= "'" + selectedUserMail + "','" + selectedUserRequest[j][0] + "','" + j + "'" ;
							$('#requestTableView').append($('<tr class="shadow-sm p-3 mb-5" id="row_' +j+ '">'+
							'<td>' + selectedUserRequest[j][0] + '</td>'+
							'<td>' + selectedUserRequest[j][1] + '</td>'+
							'<td>' + selectedUserRequest[j][2] + '</td>'+
							'<td>' + selectedUserRequest[j][3] + '</td>'+
							'<td>'+
								'<button id="accept" name="accept" onClick="acceptRequest(' + request + ')">Accetta</button>'+
							'</td>'+
				
							'</tr>'));
						
					}
				}
			}
			
			else {
				$('#requestTableView').append($('<h3>Nessuna richiesta in sospeso</h3>'));
			}
			
	});
}

$(document).ready(function(){	
	$.ajax({
		type: "GET",
		url: "/allUsers",

		success: function(risposta) {
			for (var i = 0; i <risposta.length; i++) {
				if (risposta[i].request == true ){
						$('#allUsersTableView').append($('<tr id="rowUser_'+ i + '">'+
										    	'<td >' + risposta[i].username + '</td>' +
												'<td id = "spanReq_'+i + '">' +'<span class="fa fa-minus-square-o" style="color:red">' + '</span></td>' +
												'</tr>'));
				}
				else{
					$('#allUsersTableView').append($('<tr id="rowUser_'+ i + '">'+
										    	'<td >' + risposta[i].username + '</td>' +
												'<td >' +'<span class="fa fa-check-square-o" style="color:green">' + '</span></td>' +
												'</tr>'));
					
				}
				addClickOnRow(risposta[i].mail,i);
				
					
			}
		}
	});
	
	banList();
});

function banList (){
	
	$.ajax({
		type: "GET",
		url: "/allBanRequest",

		success: function(risposta) {
				
			for (var i = 0; i <risposta.length; i++) {
			$('#banView').append($('<tr class="shadow-sm p-3 mb-5" id="rowBan_' +risposta[i].id + '">'+
				 
				'<td>' + '<a href="recipePage?ricetta_id=' + risposta[i].id + '">'+ risposta[i].titolo + '</a>'+'</td>'+
				'<td>' + risposta[i].id + '</td>'+
				'<td>' + risposta[i].utentePubblicatore.mail + '</td>'+
				'<td>' + risposta[i].segnalazioni + '</td>'+
				'<td>'+
						'<button id="banRecipe_' +risposta[i].id +  +'" name="banRecipe" value="' +risposta[i].id + '" onClick="banRecipe(' + risposta[i].id + ')">Elimina</button>'+
				'</td>'+
				'</tr>'));
			}
		}
	});
}


function banRecipe (idRicetta){
	var res = confirm('Sicuro di voler eliminare definitivamente questa ricetta?');
	if(res){
			$.ajax({
				type: "POST",
				url: "/banRecipe",
				data:{
					banRecipe:idRicetta
				},
				success: function(risposta){
					if(risposta == "ELIMINATA"){
						alert('Ricetta eliminata correttamente');
   						 $("#rowBan_"+idRicetta).remove();
					}
					else
						alert('Si è verificato un errore. Riprovare più tardi');
				}
			});
	}
}
	
function acceptRequest (idRicetta,tipo,riga){
	
	var res = confirm('Sicuro di voler accettare questa richiesta?');
	if(res){
			$.ajax({
				type: "POST",
				url: "/accept",
				data:{
					accept:idRicetta,
					requestType:tipo
				},
				success: function(risposta){
					if(risposta == "OK"){
						alert('Richiesta accettata ');
						if (tipo=="declassare utente master" || tipo=="promozione a master")
   							$("#row_"+ riga).remove();
						else
							$("#row_"+ idRicetta).remove()
						if ( $('#dataTable1 >tbody >tr').length < 1){
							$("#spanReq_" + rowId).replaceWith("<td><span class='fa fa-check-square-o' style='color:green'></span></td>");
							$('#requestTableView').append($('<h3>Nessuna richiesta in sospeso</h3>'));
						}
					}
					else
						alert('Si è verificato un errore. Riprovare più tardi');
				}
			});
	}
}

	
function refuseRequest (idRicetta,tipo){
	var res = confirm('Sicuro di voler declinare questa richiesta?');
	if(res){
			$.ajax({
				type: "POST",
				url: "/refuse",
				data:{
					refuse:idRicetta,
					requestType:tipo
				},
				success: function(risposta){
					if(risposta == "OK"){
						alert('Richiesta declinata ');
							$("#row_"+ idRicetta).remove()
						if ( $('#dataTable1 >tbody >tr').length < 1){
							$("#spanReq_" + rowId).replaceWith("<td><span class='fa fa-check-square-o' style='color:green'></span></td>");
							$('#requestTableView').append($('<h3>Nessuna richiesta in sospeso</h3>'));
						}
					}
					else
						alert('Si è verificato un errore. Riprovare più tardi');
				}
			});
	}
}



